package com.example.UP_PR2.Models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "client")

public class Client {
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String FIO;
    private String passport;
    @ManyToMany
    @JoinTable(name = "Client_Employee",
    joinColumns = @JoinColumn(name = "client_ID"),
    inverseJoinColumns = @JoinColumn(name = "employee_ID"))
    private List<Employee> employees;

    public Long getId() {return Id;}
    public void setID(Long id) {this.Id = id;}

    public String getFIO(){return FIO;}
    public void setFIO(String FIO){this.FIO = FIO;}
    public String getpassport(){return passport;}
    public void setpassport(String passport){this.passport = passport; }
    private String username;
    private String password;
    private Boolean active;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Tovar tovar;
    public Tovar getTovar(){return tovar;}
    public void setTovar(Tovar tovar){this.tovar = tovar; }

    public String getNaimenovanie(){return tovar.getnaimenovanie();}
    public String getCena(){return tovar.getcena();}

    public Client() {}

    public Client(String FIO, String passport, Tovar tovar,Set<Role> roles,String username, String password, Boolean active)
    {
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.FIO = FIO;
        this.passport = passport;
        this.tovar = tovar;
    }
}
