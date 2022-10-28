package com.example.UP_PR2.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Поле должно быть заполнено")
    @Size(min = 1, max = 100, message = "Поле должно содержать от одного до ста символов")
    private String surname;
    @NotBlank(message = "Поле должно быть заполнено")
    @Size(min = 1, max = 100, message = "Поле должно содержать от одного до ста символов")
    private String name;
    @NotBlank(message = "Поле должно быть заполнено")
    @Size(min = 1, max = 100, message = "Поле должно содержать от одного до ста символов")
    private String middleName;
    @NotNull(message = "Данное поле должно быть заполнено")
    private Integer passport;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "dopinfo_ID")
    private Dopolnitelnaya_informatcia dopinfo;

    @ManyToMany
    @JoinTable(name = "Client_Employee",
            joinColumns = @JoinColumn(name = "employee_ID"),
            inverseJoinColumns = @JoinColumn(name = "client_ID"))
    private List<Client> clients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Integer getPassport() {
        return passport;
    }

    public void setPassport(Integer passport) {
        this.passport = passport;
    }



    public Dopolnitelnaya_informatcia getDopinfo(){return dopinfo;}
    public void setDopinfo(Dopolnitelnaya_informatcia dopinfo)
    {
        this.dopinfo = dopinfo;
    }

    public List<Client> getClient() {return clients;}
    public void setClients(List<Client> clients1) {this.clients = clients1;}

    public String getEmail() {return dopinfo.getEmail();}
    public String getAdress() {return dopinfo.getAdress();}



    public Employee() { }

    public Employee(String surname, String name, String middleName, Integer passport, Dopolnitelnaya_informatcia dopinfo) {
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.passport = passport;
        this.dopinfo = dopinfo;
    }
}
