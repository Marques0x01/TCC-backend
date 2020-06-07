package com.tcc.backend.repository;

import com.tcc.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByEmail(String username);

  User findByEmail(String username);

}
