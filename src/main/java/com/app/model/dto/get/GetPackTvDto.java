package com.app.model.dto.get;

import com.app.model.Channel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPackTvDto {

    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
}
