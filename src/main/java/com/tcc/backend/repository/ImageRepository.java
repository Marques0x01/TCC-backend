package com.tcc.backend.repository;

import com.tcc.backend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Long, Image> {
}
