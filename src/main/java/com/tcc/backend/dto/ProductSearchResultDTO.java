package com.tcc.backend.dto;

import com.tcc.backend.model.Image;
import com.tcc.backend.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Builder
public class ProductSearchResultDTO {

    private Long id;
    private String title;
    private Double price;
    private Timestamp creationDate;
    private Image image;
    private String district;
    private String city;
    private String state;


    public static ProductSearchResultDTO convertToDto(Product product){
        if(product == null){
            return null;
        }

        Image image = product.getImages().get(0) != null ? Image.builder()
                .id(product.getImages().get(0).getId())
                .name(product.getImages().get(0).getName())
                .type(product.getImages().get(0).getType())
                .picByte(Image.decompressBytes(product.getImages().get(0).getPicByte()))
                .build()
                : null;

        return ProductSearchResultDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .image(image)
                .creationDate(product.getCreationDate())
                .city(product.getAddress().getCity())
                .district(product.getAddress().getDistrict())
                .state(product.getAddress().getState())
                .build();
    }
}
