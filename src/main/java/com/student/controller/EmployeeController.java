package com.student.controller;

import com.student.model.Employee;
import com.student.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping
    public String getAll(Model model,@SessionAttribute("username-ss") String username) {
        System.out.println(username);
        List<Employee> list = service.getAll();
        model.addAttribute("list",list);
        return "/employee/list";
    }
    @GetMapping("/register")
    public String save(Model model) {
        model.addAttribute("employee",new Employee());
        return "/employee/register";
    }
    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable int id) {
        Employee employee = service.findById(id).get();
        model.addAttribute("employee",employee);
        return "/employee/register";
    }
    @PostMapping("/save")
    public String create(@ModelAttribute Employee employee) {
        service.save(employee);
        return "redirect:/employee";
    }
    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        service.delete(id);
        List<Employee> list = service.getAll();
        model.addAttribute("list",list);
        return "/employee/list";
    }
}
