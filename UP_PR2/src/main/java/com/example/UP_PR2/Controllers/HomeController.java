package com.example.UP_PR2.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return ("/employee/index");
    }

    @GetMapping("/main/")
    public String main(Model model,
                       @RequestParam(name="x1", defaultValue = "1") int x1,
                       @RequestParam(name="x2", defaultValue = "1") int x2,
                       @RequestParam(name="m") String method_) {

        model.addAttribute("res", getMethod(x1, x2, method_));
        return ("home");
    }

    @PostMapping("/main/")
    public String mainPost(Model model,
                           @RequestParam(name="x1", defaultValue = "1") int x1,
                           @RequestParam(name="x2", defaultValue = "1") int x2,
                           @RequestParam(name="m") String method_) {

        model.addAttribute("res", getMethod(x1, x2, method_));
        return ("home");
    }

    private int getMethod(int x1, int x2, String m) {
        int res;
        switch (m) {
            case "+" -> res = x1 + x2;
            case "-" -> res = x1 - x2;
            case "*" -> res = x1 * x2;
            default -> res = x1 / x2;
        }
        return res;
    }
}
