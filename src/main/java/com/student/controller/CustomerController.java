package com.student.controller;

import com.student.model.Customer;
import com.student.service.CustomerService;
import com.student.service.dao.CustomerDao;
import com.student.test.ClassA;
import com.student.test.ClassB;
import com.student.test.ClassC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/customer")
public class CustomerController {

   private final CustomerService customerService;
    private ClassB b;
    private final ClassC c;
    public CustomerController(CustomerDao customerDao, ClassC c) {
        this.customerService = customerDao;
        this.c = c;
    }

    @Autowired
    public void setB(ClassB b) {
        this.b = b;
    }

    CustomerService dao = new CustomerDao();

    @GetMapping
    public String getHome(Model model,@CookieValue(value = "username",defaultValue = "") String username,
                          @CookieValue(value = "duytest",defaultValue = "") String duytest) {
        System.out.println(duytest);
        System.out.println(username);
        ArrayList<Customer> customersList = dao.read();
        model.addAttribute("list", customersList);
        return "customer/home";
    }

    @Autowired
    ClassA classA;

    @GetMapping("/test")
    public String test(Model model) {
        classA.getHello();
        System.out.println(classA);
        ClassA a = classA;
        ClassA a2 = a;
        System.out.println(a);
        System.out.println(a2);
        return "customer/home";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer/create";
    }

    @GetMapping("/del/{id}")
    public String dal(Model model, @PathVariable int id) {
        customerService.delete(id);
        ArrayList<Customer> customersList = customerService.read();
        model.addAttribute("list", customersList);

        return "customer/home";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable int id) {
        Customer customer = customerService.findByID(id);
        model.addAttribute("customer", customer);
        return "customer/update";

    }

    @PostMapping("/save")
    public String postCreate(@ModelAttribute Customer customer) {
        dao.create(customer);
        return "redirect:/customer";
    }


}
