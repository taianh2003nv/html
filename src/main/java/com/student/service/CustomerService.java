package com.student.service;

import com.student.model.Customer;

import java.util.ArrayList;

public interface CustomerService {
    ArrayList<Customer> read();

    void create(Customer customers);

    void update(Customer customers);

    void delete(int id);

    void checkID(Customer customer);

    boolean checkCId(int cId);
    Customer findByID(int id);
}
