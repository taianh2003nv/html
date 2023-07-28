package com.student.service.dao;

import com.student.conn.ConnectJdbc;
import com.student.model.Customer;
import com.student.service.CustomerService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
@Service("svdao")
public class CustomerDao implements CustomerService {
   private PreparedStatement preparedStatement;

    @Override
    public ArrayList<Customer> read() {
        ArrayList<Customer> arrayList = new ArrayList<>();
        try {
            preparedStatement = ConnectJdbc.con().prepareStatement("select * from customers");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int cusId = resultSet.getInt(1);
                String cusname = resultSet.getString(2);
                String birthday = resultSet.getString(3);
                String address = resultSet.getString(4);
                String phone = resultSet.getString(5);
                Customer customer = new Customer();
                customer.setCusId(cusId);
                customer.setCusName(cusname);
                customer.setPhone(phone);
                customer.setBirthDay(birthday);
                customer.setaId(1);
                arrayList.add(customer);
            }
            return arrayList;
        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }

    @Override
    public void create(Customer customers) {
        try {
            int id = (int) (Math.random() * ( 1000 - 1 ));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(simpleDateFormat.parse(customers.getBirthDay()).getTime());
            preparedStatement = ConnectJdbc.con().prepareStatement("insert into customers values (?,?,?,?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,customers.getCusName());
            preparedStatement.setDate(3,date);
            preparedStatement.setString(4,"Vp");
            preparedStatement.setString(5,customers.getPhone());
            preparedStatement.execute();
            System.out.println("Success!");

        }catch (Exception e){
            throw  new RuntimeException(e);
        }

    }

    @Override
    public void update(Customer customers) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void checkID(Customer customer) {

    }

    @Override
    public boolean checkCId(int cId) {
        return false;
    }

    @Override
    public Customer findByID(int id) {
        return null;
    }
}
