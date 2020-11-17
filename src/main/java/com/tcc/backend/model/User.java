package com.tcc.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String lastName;
  @Column(unique = true, nullable = false)
  private String email;
  @Column(nullable = false)
  private String password;
  @Column(unique = true)
  private String cpf;
  @Column(unique = true)
  private String phoneNumber;
  private String gender;
  @Enumerated(EnumType.STRING)
  private UserStatus status;
  @ElementCollection(fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  private List<Role> roles;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  private Address address;

  @OneToMany(mappedBy = "user")
  private List<Product> products;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "user_favorites",
          joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "advertising_id", referencedColumnName = "id"))
  private List<Product> favorites;

  @OneToMany(mappedBy = "user")
  private List<Complaint> complaints;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private TokenVerification verificationToken;
}
