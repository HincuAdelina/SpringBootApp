package com.fmiunibuc.ProiectJava.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name cannot be missing.")
    @Size(min = 3, max = 50)
    private String name;

    @Column(name = "phonenumber", nullable = false)
    @NotBlank(message = "Phone number cannot be missing.")
    @Pattern(regexp = "[0-9]{10}", message = "The phone number must be valid.")
    private String phonenumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;

    @JsonIgnore
    @OneToMany(mappedBy = "driver")
    private List<Order> orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Driver() {
    }

    public Driver(String name, String phonenumber) {
        this.name = name;
        this.phonenumber = phonenumber;
    }
}
