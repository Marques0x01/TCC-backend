package com.tcc.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String zipCode;
    private String district;
    private String city;
    private String state;
    private String country;
    @OneToMany(mappedBy = "address")
    private List<Product> products;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "address")
    private List<User> user;
}
