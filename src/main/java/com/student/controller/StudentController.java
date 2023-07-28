package com.student.controller;

import com.student.model.MyFile;
import com.student.model.Student;
import com.student.model.StudentsDto;
import com.student.service.FileService;
import com.student.service.impl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService service;
    @Autowired
    private FileService fileService;
    @GetMapping
    public String getHome(Model model) {
        ArrayList<StudentsDto> list = service.read();
        model.addAttribute("list",list);

        return "student/students";
    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("student", new Student());
        return "student/register";
    }
    @GetMapping("/update/{id}")
    public String update(Model model ,@PathVariable int id){
        Student student = service.findByID(id);
        model.addAttribute("student",student);
        return "student/register";
    }
    @PostMapping("/save")
    @Transactional
    public String save(@RequestParam("file") MultipartFile file, @ModelAttribute @Validated Student student,
                       BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "student/register";
        }
        Student studentNew =  service.save(student);
        fileService.save(file,studentNew.getSid());
        return "redirect:/student";
    }

    @GetMapping("/profile/{id}")
    public String profile(Model model ,@PathVariable int id){
        Student student =  service.findByID(id);
        model.addAttribute("student",student);
        return "student/profile";
    }

}
