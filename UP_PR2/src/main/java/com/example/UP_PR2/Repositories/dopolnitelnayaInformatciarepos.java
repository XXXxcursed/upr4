package com.example.UP_PR2.Repositories;

import com.example.UP_PR2.Models.Dopolnitelnaya_informatcia;
import org.springframework.data.repository.CrudRepository;



public interface dopolnitelnayaInformatciarepos extends CrudRepository<Dopolnitelnaya_informatcia, Long> {
    Dopolnitelnaya_informatcia findByEmail(String email);
}
