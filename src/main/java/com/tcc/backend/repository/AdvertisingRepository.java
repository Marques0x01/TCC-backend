package com.tcc.backend.repository;

import com.tcc.backend.model.Advertising;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisingRepository extends JpaRepository<Advertising, Long> {
}
