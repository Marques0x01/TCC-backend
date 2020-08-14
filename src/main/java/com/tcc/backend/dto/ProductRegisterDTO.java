package com.tcc.backend.dto;

import com.tcc.backend.model.Category;
import com.tcc.backend.model.Product;
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
public class ProductRegisterDTO {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private Boolean isPhoneVisible;
    private AddressProductRegisterDTO address;
    private Long userId;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private RentType rentType;

    public static Product convertToModel(ProductRegisterDTO dto){
        if(dto == null){
            return null;
        }

        return Product.builder()
                .id(dto.id)
                .title(dto.title)
                .description(dto.description)
                .price(dto.price)
                .isPhoneVisible(dto.isPhoneVisible)
                .address(AddressProductRegisterDTO.convertToModel(dto.address))
                .category(dto.category)
                .rentType(dto.rentType)
                .build();
    }

    public static ProductRegisterDTO convertToDto(Product product){
        if(product == null){
            return null;
        }

        return ProductRegisterDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .isPhoneVisible(product.getIsPhoneVisible())
                .address(AddressProductRegisterDTO.convertToDto(product.getAddress()))
                .category(product.getCategory())
                .rentType(product.getRentType())
                .build();
    }

}
