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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Double price;
    private RentType rentType;
    private String zipCode;
    private Boolean isPhoneVisible;
    @OneToMany(mappedBy = "product")
    private List<Image> images;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Address address;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @OneToMany(mappedBy = "product")
    private List<Complaint> complaints;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "favorites")
    private List<User> userFavorites;
}
