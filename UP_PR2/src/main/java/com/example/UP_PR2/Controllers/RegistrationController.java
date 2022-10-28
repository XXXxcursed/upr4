package com.example.UP_PR2.Controllers;

import com.example.UP_PR2.Models.Client;
import com.example.UP_PR2.Models.Employee;
import com.example.UP_PR2.Models.Role;
import com.example.UP_PR2.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;


@Controller
public class RegistrationController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String reg(Client client) {
        return ("registration");
    }

    @PostMapping("/registration")
    public String reg(Client client,
                      Model model) {

        if (clientRepository.findByFIO(client.getUsername()) != null) {
            model.addAttribute("error", "Логин занят!");
            return ("registration");
        }
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        client.setActive(true);
        client.setRoles(Collections.singleton(Role.USER));

        clientRepository.save(client);

        return ("redirect:/login");
    }
}
