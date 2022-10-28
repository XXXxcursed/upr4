package com.example.UP_PR2.Repositories;

import com.example.UP_PR2.Models.Client;
import com.example.UP_PR2.Models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findByFIO(String FIO);
}
