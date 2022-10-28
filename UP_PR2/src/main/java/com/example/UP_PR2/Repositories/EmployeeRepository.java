package com.example.UP_PR2.Repositories;

import com.example.UP_PR2.Models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    public List<Employee> findBySurname(String surname);
    public List<Employee> findBySurnameContains(String surname);
}
