package com.example.orderservice.controller;

import com.example.orderservice.model.bean.*;
import com.example.orderservice.model.service.InventoryService;
import com.example.orderservice.model.service.OrderService;
import com.example.orderservice.model.service.ProductService;
import com.example.orderservice.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Controller
@SessionAttributes({"user"})
public class ShoppingController {
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;
    @Autowired
    InventoryService inventoryService;

    List<OrderItems> orderItemsList=new ArrayList<>();

    @RequestMapping("/")
    public ModelAndView login(){
        return new ModelAndView("index","user",new User());
    }

    @RequestMapping("/login")
    public ModelAndView loginCheck(@ModelAttribute("user") User user, HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        Optional<User> ourUser=userService.getUser(user.getUserEmail());
        ourUser.ifPresent(value -> modelAndView.addObject("user", value));
        if(!ourUser.isPresent()) {
            String error="Enter Valid Login Credentials";
            modelAndView.addObject("error", error);
            modelAndView.setViewName("index");
        }
        else if(!userService.validateUser(user.getUserEmail(),user)) {
            String error="Enter Valid Login Credentials";
            modelAndView.addObject("error", error);
            modelAndView.setViewName("index");
        }
        else {
            session.setAttribute("user", ourUser.get());
            modelAndView.addObject("userName",((User)session.getAttribute("user")).getName());
            System.out.println(user.getUserEmail());
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }

    @RequestMapping("/home")
    public ModelAndView home(){
        return new ModelAndView("home");
    }

    @RequestMapping("/products")
    public ModelAndView products(){
        ModelAndView modelAndView=new ModelAndView();

        modelAndView.addObject("productsList",productService.getAllProducts());
        modelAndView.setViewName("products");
        return modelAndView;
    }

    @RequestMapping("/orderHistory")
    public ModelAndView orderHistory(HttpSession session){
        ModelAndView modelAndView=new ModelAndView("orderHistory");
        User u=(User)session.getAttribute("user");
        System.out.println(u.toString());
        List<OrderItems> itemsList=new ArrayList<>();
        List<Orders> orders=orderService.getAllByCustomerEmail(u.getUserEmail());
        modelAndView.addObject("ordersList", Collections.singletonList(orders));
        orders.forEach(e-> itemsList.addAll(e.getItems()));
        return modelAndView;
    }

    @RequestMapping("/orderProducts")
    public ModelAndView placeOrder(){
        ModelAndView modelAndView=new ModelAndView("order","orderItems",new OrderItems());
        modelAndView.addObject("productsList",showProductsInStock());
        modelAndView.addObject("orderItemsList",orderItemsList);

        return modelAndView;
    }

    @RequestMapping("/addItems")
    public ModelAndView addItems(@ModelAttribute("item") OrderItems orderItems){
        ModelAndView modelAndView=new ModelAndView("order","orderItems",new OrderItems());
        OrderItems orderItems1=new OrderItems();

        try{
            productService.getProduct(orderItems.getProductCode());
        }
        catch (HttpClientErrorException.NotFound e){
            String error="Enter valid product code from the above list";
            modelAndView.addObject("error",error);
            modelAndView.addObject("productsList",showProductsInStock());
            modelAndView.addObject("orderItemsList",orderItemsList);
            return modelAndView;
        }

        orderItems1.setProductCode(orderItems.getProductCode());
        orderItems1.setQuantity(orderItems.getQuantity());

        if(orderItems.getQuantity()<1){
            String error="Enter valid quantity for product code:"+orderItems.getProductCode();
            modelAndView.addObject("error",error);
            modelAndView.addObject("productsList",showProductsInStock());
            modelAndView.addObject("orderItemsList",orderItemsList);
            return modelAndView;

        }
        else if(orderItems.getQuantity()>inventoryService.getInventoryItem(orderItems.getProductCode()).get().getAvailableQuantity()){
            String error="Enter valid quantity for product code:"+orderItems.getProductCode();
            modelAndView.addObject("productsList",showProductsInStock());
            modelAndView.addObject("orderItemsList",orderItemsList);
            modelAndView.addObject("error",error);
            return modelAndView;

        }
        try{
            orderItems1.setProductPrice(BigDecimal.valueOf(productService.getProduct(orderItems.getProductCode()).get().getPrice() * orderItems.getQuantity()));
        }
        catch(HttpClientErrorException.NotFound e){
            String error="Enter valid product code from the above list";
            modelAndView.addObject("productsList",showProductsInStock());
            modelAndView.addObject("orderItemsList",orderItemsList);
            modelAndView.addObject("error",error);
            return modelAndView;
        }
        orderItemsList.add(orderItems1);
        modelAndView.addObject("productsList",showProductsInStock());
        modelAndView.addObject("orderItemsList",orderItemsList);
        return modelAndView;
    }

    @RequestMapping("/confirmOrder")
    public ModelAndView confirmOrder(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("orderItems",orderItemsList);
        int total=orderItemsList.stream().mapToInt(e->e.getProductPrice().intValue()).sum();
        modelAndView.addObject("total",total);
        modelAndView.setViewName("confirmOrder");
        return modelAndView;
    }

    @RequestMapping("/orderSuccess")
    public  ModelAndView orderSuccess(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("orderSuccess");
        return modelAndView;
    }

    @RequestMapping("/placeOrder")
    public ModelAndView placeOrder(HttpSession httpSession, @RequestParam("address") String addr){
        ModelAndView modelAndView=new ModelAndView();
        User user=(User)httpSession.getAttribute("user");
        try{
            int total=orderItemsList.stream().mapToInt(e->e.getProductPrice().intValue()).sum();
            orderService.createOrder(new Orders(user.getUserEmail(), addr,orderItemsList,total));
            modelAndView.setViewName("orderSuccess");
            modelAndView.addObject("address", addr);
            modelAndView.addObject("total",total);
            modelAndView.addObject("expectedDelivery", LocalDate.now().plusDays(5).toString());
            return modelAndView;
        }
        catch (Exception e){
            String error=e.getMessage();
            modelAndView.addObject("orderItemsList",orderItemsList);
            modelAndView.addObject("error",error);
            modelAndView.setViewName("confirmOrder");
            return modelAndView;
        }
    }

    List<ProductInStock> showProductsInStock(){
        List<Product> productstList=productService.getAllProducts();
        List<InventoryItem> inventoryItemList=inventoryService.inventoryList();
        List<ProductInStock> productInStocks=new ArrayList<>();
        productstList.forEach(e->productInStocks.add(new ProductInStock(e.getId(),e.getProductCode(),e.getName(),e.getDescription(),e.getPrice(),inventoryItemList.get(productstList.indexOf(e)).getAvailableQuantity())));
        return  productInStocks;

    }
}
