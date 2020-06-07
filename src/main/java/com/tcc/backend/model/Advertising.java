package com.tcc.backend.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advertising {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Double price;
//    PICTURE
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private AdvertisingStatus status;
    @OneToMany(mappedBy = "advertising")
    private List<Complaint> complaints;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "favorites")
    private List<User> userFavorites;
}
