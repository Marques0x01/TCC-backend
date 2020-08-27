package com.tcc.backend.dto;

import com.tcc.backend.model.Category;
import com.tcc.backend.model.RentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchDTO {

    private Long id;
    private String title;
    private Double minPrice;
    private Double maxPrice;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private RentType rentType;
    private String city;
    private String state;
}
