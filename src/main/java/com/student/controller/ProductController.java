package com.student.controller;

import com.student.repository.ProductRepository;
import com.student.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository repository;

    @GetMapping
    @Transactional
    public String home(){
        repository.insertProduct(100,1,"tst1");
        repository.insertProduct(101,1,"tst1");
        repository.insertProduct(101,1,"tst1");
        repository.insertProduct(102,1,"tst1");
        return "home";
    }
}
