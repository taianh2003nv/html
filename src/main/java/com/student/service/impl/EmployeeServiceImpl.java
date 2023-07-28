package com.student.service.impl;

import com.student.model.Employee;
import com.student.repository.EmployeeRepository;
import com.student.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {
        List<Employee> list = employeeRepository.findAll();
        return list ;
    }

    @Override
    public void save(Employee employee) {
      employeeRepository.save(employee);

    }

    @Override
    public void delete(int id) {
        employeeRepository.deleteById(id);

    }

    @Override
    public Optional<Employee> findById(int id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (!optionalEmployee.isEmpty()){
            return optionalEmployee;
        }
        return Optional.empty();
    }
}
