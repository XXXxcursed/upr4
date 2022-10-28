package com.example.UP_PR2.Repositories;

import com.example.UP_PR2.Models.Employee;
import com.example.UP_PR2.Models.Tovar;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TovarRepository extends CrudRepository<Tovar, Long> {

    Tovar findByNaimenovanie(String naim);
}
