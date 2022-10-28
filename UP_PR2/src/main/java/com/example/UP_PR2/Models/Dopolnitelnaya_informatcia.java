package com.example.UP_PR2.Models;

import javax.persistence.*;



@Entity
@Table(name="Dop")
public class Dopolnitelnaya_informatcia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String adress;
    @OneToOne(optional = true, mappedBy = "dopinfo")
    private Employee sotrydnik1;

    public Employee getSotrydnik() {return sotrydnik1;}
    public void setSotrydnik(Employee sotrydnik) {this.sotrydnik1 = sotrydnik;}

    public Dopolnitelnaya_informatcia() {}

    public Dopolnitelnaya_informatcia(String email, String adress){
        this.email = email;
        this.adress = adress;
    }

    public Long getId(){return id;}

    public void setId(Long id){this.id = id;}
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}
    public String getAdress(){return adress;}
    public void setAdress(String adress){this.adress = adress; }
}

