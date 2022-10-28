package com.example.UP_PR2.Controllers;

import com.example.UP_PR2.Models.*;
import com.example.UP_PR2.Repositories.EmployeeRepository;
import com.example.UP_PR2.Repositories.dopolnitelnayaInformatciarepos;
import com.example.UP_PR2.Repositories.ClientRepository;
import com.example.UP_PR2.Repositories.TovarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    dopolnitelnayaInformatciarepos dopinfo;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    TovarRepository tovarRepository;

    @GetMapping("/employee")
    public String Employee(Model model) {

        Iterable<Employee> listEmployee = employeeRepository.findAll();
        model.addAttribute(("list_employee"), listEmployee);
        return ("employee/index");
    }

    @GetMapping("/employee/add")
    public String EmployeeAddView(Employee employee, Model model)
    {
        Iterable<Dopolnitelnaya_informatcia> dopolnitelnaya_informatcias = dopinfo.findAll();
        ArrayList<Dopolnitelnaya_informatcia> DopinfosArrayList = new ArrayList<>();

        for(Dopolnitelnaya_informatcia di: dopolnitelnaya_informatcias) {
            if(di.getSotrydnik() == null) {
                DopinfosArrayList.add(di);
            }
        }
        model.addAttribute("dopinf", DopinfosArrayList);


        return ("employee/employeeADD");
    }

    @PostMapping("/employee/add")
    public String EmployeeAdd(//@RequestParam String surname,
//                              @RequestParam String name,
//                              @RequestParam String middleName,
//                              @RequestParam Integer passport,
                              @Valid Employee employee,
                              BindingResult bindingResult,
                              @RequestParam String number
    ) {
        if (bindingResult.hasErrors())
        {
            return ("employee/employeeADD");
        }
        Dopolnitelnaya_informatcia dopolnitelnaya_informatcia =dopinfo.findByEmail(number);
        employee.setDopinfo(dopolnitelnaya_informatcia);

//        Employee employee = new Employee(surname, name, middleName, passport);
        employeeRepository.save(employee);
        return ("redirect:/employee");
    }

    @GetMapping("employee/edit/{id}")
    public String CarEdit(Model model,
                          @PathVariable long id) {

        Employee emp = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("employee", emp);
        return("/employee/employee-edit");
    }

    @PostMapping("/employee/edit/{id}")
    public String EmployeeEdit(@Valid Employee employee,
                               BindingResult bindingResult
                               ) {
        if(bindingResult.hasErrors())
        {
            return ("/employee/employee-edit");
        }
        employeeRepository.save(employee);
        return ("redirect:/employee/details/" + employee.getId());
    }

    @GetMapping("/employee/details/{id}")
    public String EmployeeDetails(Model model,
                                  @PathVariable long id) {

        Optional<Employee> car = employeeRepository.findById(id);
        ArrayList<Employee> result = new ArrayList<>();

        car.ifPresent(result::add);
        model.addAttribute("employee", result);
        return ("/employee/employee-details");
    }

    @GetMapping("employee/delete/{id}")
    public String EmployeeDelete(@PathVariable long id) {

        employeeRepository.deleteById(id);
        return("redirect:/employee");
    }

    @GetMapping("/employee/filterACCU")
    public String EmployeeFilterACCU(Model model,
                                     @RequestParam(name = "search") String surname) {

        List<Employee> employeeList = employeeRepository.findBySurname(surname);
        model.addAttribute("searchRes", employeeList);
        return ("employee/employeeFilter");
    }

    @GetMapping("/employee/filter")
    public String EmployeeFilter(Model model,
                                 @RequestParam(name = "search") String surname) {

        List<Employee> employeeList = employeeRepository.findBySurnameContains(surname);
        model.addAttribute("searchRes", employeeList);
        return ("employee/employeeFilter");
    }

    @GetMapping("/employee/DopInfoADD")
    public String Dop(Model model)
    {
        return ("/employee/DopInfoADD");
    }

    @PostMapping("/employee/DopInfoADD")
        public String DopInfoADD(
                    @RequestParam String email,
                    @RequestParam String adress) {
        Dopolnitelnaya_informatcia info = new Dopolnitelnaya_informatcia(email,adress);
        dopinfo.save(info);
        return("redirect:/employee");
    }

    @GetMapping("/employee/ClientADD")
    public String clientik(Model model, Client client)
    {
        Iterable<Tovar> tovars = tovarRepository.findAll();
        model.addAttribute("Tovar", tovars);
        return ("/employee/ClientADD");
    }

    @PostMapping("/employee/ClientADD")
    public String clientikADD(
            @Valid Client client,
            BindingResult bindingResult,
            @RequestParam String number) {
        if (bindingResult.hasErrors())
        {
            return ("employee/employeeADD");
        }
        Tovar tovar =tovarRepository.findByNaimenovanie(number);
        client.setTovar(tovar);
        clientRepository.save(client);
        return("redirect:/employee");
    }

    @GetMapping("/employee/Client_EmployeeADD")
    public String Office_employeeAdd(Model model)
    {
        Iterable<Employee> students = employeeRepository.findAll();
        model.addAttribute("employees", students);
        Iterable<Client> universities = clientRepository.findAll();
        model.addAttribute("clients", universities);

        return ("/employee/Client_EmployeeADD");
    }

    @PostMapping("/employee/Client_EmployeeADD")
    public String Client_employeeAdd(
            @RequestParam String client,
            @RequestParam String employee
    ) {
        Employee student2 = employeeRepository.findBySurname(Arrays.stream((employee.split(" "))).toList().get(0)).get(0);
        Client university2 = clientRepository.findByFIO(client);
        List<Client> of = student2.getClient();
        of.add(university2);
        student2.setClients(of);
        employeeRepository.save(student2);
        return ("redirect:/employee");
    }

    @GetMapping("/employee/TovarADD")
    public String tovarchik(Model model)
    {
        return ("/employee/TovarADD");
    }

    @PostMapping("/employee/TovarADD")
    public String tovarchikADD(
            @RequestParam String naimenovanie,
            @RequestParam String cena) {
        Tovar tovar = new Tovar(naimenovanie,cena);
        tovarRepository.save(tovar);
        return("redirect:/employee");
    }

    @GetMapping("/employee/admin")
    public String AdminPanel(Model model) {

        Iterable<Client> listClient = clientRepository.findAll();
        model.addAttribute(("list_employee"), listClient);
        return ("employee/adminPanel");
    }

    @GetMapping("/employee/admin/Edit-role/{id}")
    public String EmployeeRoleEdit(Model model,
                                   @PathVariable long id) {

        Client client = clientRepository.findById(id).orElseThrow();
        model.addAttribute("client", client);
        model.addAttribute("listRoles", Role.values());
        return("/employee/Edit-role");
    }

    @PostMapping("/employee/admin/Edit-role/{id}")
    public String EmployeeRoleEdit(@PathVariable long id,
                                   @RequestParam String[] roles) {

        Client client = clientRepository.findById(id).orElseThrow();
        client.getRoles().clear();

        for(String role: roles){
            client.getRoles().add(Role.valueOf(role));
        }

        clientRepository.save(client);

        return("redirect:/employee/admin");
    }

}
