package com.app.model.dto.get;

import com.app.model.dto.CategoryDto;
import com.app.model.enums.Quality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetFilmDto {

    private Long id;

    private String title;
    private Integer yearProduction;
    private String description;
    private BigDecimal price;
    private Quality quality;

    private CategoryDto category;
}
