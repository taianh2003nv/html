package com.student.service.impl;

import com.student.model.Address;
import com.student.model.Student;
import com.student.model.StudentsDto;
import com.student.repository.StudentRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final EntityManagerFactory factory;


    private EntityManager entityManager;

    private EntityTransaction transaction;

    public StudentServiceImpl(EntityManagerFactory factory) {
        this.factory = factory;
        entityManager = factory.createEntityManager();
    }

    @Override
    public ArrayList<StudentsDto> read() {
        entityManager = factory.createEntityManager();
        String query = "select s.*, a.province from student as s ," +
                " address as a where s.aid = a.aid order by s.sid";

        ArrayList<StudentsDto> list = (ArrayList<StudentsDto>) entityManager
                .createNativeQuery(query, StudentsDto.class).getResultList();

        return list;
    }

    @Override

    public void create(Student student) {
        if (student.getSid() > 0) {
            update(student);
        } else {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(student);
            transaction.commit();
        }


    }

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student save(Student student) {

        return studentRepository.save(student);

    }

    @Override
    public List<Student> findByAID(int aid) {
        String queryHQL = "select s from  Student as s where address.aid =?1";
        String querySQL = "select * from student where aid = ?1 and id = :id";
//        List<Student> list = entityManager.createQuery(queryHQL,Student.class)
//                .setParameter(1,aid).getResultList();
        List<Student> list2 = entityManager.createNativeQuery(querySQL, Student.class)
                .setParameter(1, aid).
                setParameter("id", 2).getResultList();

        return list2;
    }

    @Override
    public void update(Student student) {
        transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(student);
        transaction.commit();


    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Student findByID(int id) {
        Student student = entityManager.find(Student.class, id);
        return student;
    }
}
