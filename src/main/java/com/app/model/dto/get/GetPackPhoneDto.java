package com.app.model.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPackPhoneDto {

    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private String limitedConversation; //in minutes
    private String limitedInternet; //in Gb
}
