package com.app.model.dto.create;

import com.app.model.enums.Quality;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateFilmDto {

    private String title;
    private Integer yearProduction;
    private String description;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Quality quality;

    private Long categoryId;
}
