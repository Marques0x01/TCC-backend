package com.tcc.backend.dto;

import com.tcc.backend.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithoutObjDTO {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private Category category;
    private ProductStatus status;
    private List<Long> complaintIds;
    private Long userId;
    private List<Long> userFavoritesId;

    public static ProductWithoutObjDTO convertToDto(Product product) {
        if (product == null) {
            return null;
        }

        return ProductWithoutObjDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .status(product.getStatus())
                .complaintIds(product.getComplaints().stream().map(Complaint::getId).collect(Collectors.toList()))
                .userId(product.getUser() == null ? null : product.getUser().getId())
                .userFavoritesId(product.getUserFavorites().stream().map(User::getId).collect(Collectors.toList()))
                .build();
    }

    public static Product convertToModel(ProductWithoutObjDTO dto){
        if(dto == null) {
            return null;
        }

        return Product.builder()
                .id(dto.id)
                .title(dto.title)
                .description(dto.description)
                .price(dto.price)
                .category(dto.category)
                .status(dto.status)
                // TODO FINISH THIS LATER
                .build();
    }
}
