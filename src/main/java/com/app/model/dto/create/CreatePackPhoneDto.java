package com.app.model.dto.create;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePackPhoneDto {

    private String name;
    private String description;
    private BigDecimal price;
    private String limitedConversation; //in minutes
    private String limitedInternet; //in Gb
}
