package com.tcc.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
@AllArgsConstructor
@Builder
public class Email {
    private String to;
    private String name;
    private String token;
    private Map<String, String> model;
}
