package com.student.service.impl;

import com.student.model.Customer;
import com.student.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service("svimpl")
public class CustomerServiceImpl implements CustomerService {
    ArrayList<Customer> customers = new ArrayList<>();
    public void checklist(){
        if (customers.isEmpty()){
            customers.add(new Customer(1,"duy","sadas","sadsada",1));
            customers.add(new Customer(2,"duy","sadas","sadsada",2));
            customers.add(new Customer(3,"duy","sadas","sadsada",3));
        }



    }
    @Override
    public ArrayList<Customer> read() {
        checklist();
        return customers;
    }

    @Override
    public void create(Customer customers) {
        checklist();
        customers.setCusId(this.customers.size()+1);
        this.customers.add(customers);

    }

    @Override
    public Customer findByID(int id) {
        checklist();
        for (Customer c: customers) {
            if (c.getCusId() == id){
                return c;
            }
        }
        return null;
    }

    @Override
    public void update(Customer customers) {
        for ( Customer c: this.customers) {
            if (c.getCusId() == customers.getCusId()){
                c.setCusName(customers.getCusName());
                c.setBirthDay(customers.getBirthDay());
                c.setPhone(customers.getPhone());
                c.setaId(customers.getaId());
                break;
            }

        }

    }

    @Override
    public void checkID(Customer customer) {
        if (customer.getCusId()==0){
            create(customer);
        }else {
            update(customer);
        }

    }

    @Override
    public void delete(int id) {
        checklist();
        for (int i = 0; i < customers.size(); i++) {
            if (id == customers.get(i).getaId()){
                customers.remove(i);
                break;
            }
        }

    }

    @Override
    public boolean checkCId(int cId) {
        return false;
    }
}
