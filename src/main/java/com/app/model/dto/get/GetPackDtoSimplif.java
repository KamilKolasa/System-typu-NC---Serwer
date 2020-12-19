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
public class GetPackDtoSimplif {

    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private Integer discount;

    private String namePackPhone;
    private Long idPackPhone;
    private String namePackInternet;
    private Long idPackInternet;
    private String namePackTv;
    private Long idPackTv;
}
