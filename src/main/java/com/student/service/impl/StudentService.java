package com.student.service.impl;

import com.student.model.Customer;
import com.student.model.Student;
import com.student.model.StudentsDto;

import java.util.ArrayList;
import java.util.List;

public interface StudentService {
    ArrayList<StudentsDto> read();

    void create(Student student);
    Student save (Student student);
    void update(Student student);

    void delete(int id);
    List<Student> findByAID(int aid);

    Student findByID(int id);
}
