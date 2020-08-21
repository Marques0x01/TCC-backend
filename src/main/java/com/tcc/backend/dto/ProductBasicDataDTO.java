package com.tcc.backend.dto;

import com.tcc.backend.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductBasicDataDTO {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private Boolean isPhoneVisible;
    private List<Image> images;
    private AddressBasicaDataDTO address;
    private RentType rentType;
    private ProductStatus status;
    private UserBasicDataDTO user;

    public static ProductBasicDataDTO convertToDto(Product product){
        if(product == null){
            return null;
        }

        return ProductBasicDataDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .isPhoneVisible(product.getIsPhoneVisible())
                .images(product.getImages().stream().map(Image::getImageDecompressed).collect(Collectors.toList()))
                .address(AddressBasicaDataDTO.convertToDto(product.getAddress()))
                .rentType(product.getRentType())
                .status(product.getStatus())
                .user(UserBasicDataDTO.convertToDto(product.getUser()))
                .build();
    }
}
