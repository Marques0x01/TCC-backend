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
public class AdvertisingWithoutObjDTO {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private Category category;
    private AdvertisingStatus status;
    private List<Long> complaintIds;
    private Long userId;
    private List<Long> userFavoritesId;

    public static AdvertisingWithoutObjDTO from(Advertising advertising) {
        if (advertising == null) {
            return null;
        }

        return AdvertisingWithoutObjDTO.builder()
                .id(advertising.getId())
                .title(advertising.getTitle())
                .description(advertising.getDescription())
                .price(advertising.getPrice())
                .category(advertising.getCategory())
                .status(advertising.getStatus())
                .complaintIds(advertising.getComplaints().stream().map(Complaint::getId).collect(Collectors.toList()))
                .userId(advertising.getUser().getId())
                .userFavoritesId(advertising.getUserFavorites().stream().map(User::getId).collect(Collectors.toList()))
                .build();
    }
}
