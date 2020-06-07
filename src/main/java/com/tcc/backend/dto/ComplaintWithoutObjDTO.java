package com.tcc.backend.dto;

import com.tcc.backend.model.Advertising;
import com.tcc.backend.model.Complaint;
import com.tcc.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintWithoutObjDTO {

    private Long id;
    private Long advertisingId;
    private String description;
    private Long userId;
    private Date date;

    public static ComplaintWithoutObjDTO from(Complaint complaint){
        if(complaint == null){
            return null;
        }

        return ComplaintWithoutObjDTO.builder()
                .id(complaint.getId())
                .advertisingId(complaint.getAdvertising().getId())
                .description(complaint.getDescription())
                .userId(complaint.getUser().getId())
                .date(complaint.getDate())
                .build();
    }
}
