package com.example.UP_PR2.Models;


import com.mysql.cj.xdevapi.Collection;

import javax.persistence.*;

@Entity
@Table(name="tovar")
public class Tovar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String naimenovanie;
    private String cena;

    @OneToMany(mappedBy = "tovar", fetch = FetchType.EAGER)
    private java.util.Collection<Client> employeeCollection;

    public Long getId() {return ID;}
    public void setId(Long id) {this.ID = id;}
    public String getnaimenovanie(){return naimenovanie;}
    public void setnaimenovanie(String Naimenovanie){this.naimenovanie = Naimenovanie;}
    public String getcena(){return cena;}
    public void setcena(String Cena){this.cena = Cena; }

    public java.util.Collection<Client> getEmployeeCollection(){return employeeCollection;}
    public void setEmployeeCollection(java.util.Collection<Client> employees){this.employeeCollection = employees;}


    public Tovar() {}

    public Tovar(String naimenovanie, String cena)
    {
        this.naimenovanie = naimenovanie;
        this.cena = cena;
    }
}
