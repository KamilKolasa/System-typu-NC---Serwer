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
public class CreatePackTvDto {

    private String name;
    private String description;
    private BigDecimal price;
}
