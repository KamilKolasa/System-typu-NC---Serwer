package com.app.model.dto.create;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBuyFilmDto {

    private LocalDate dateStart;
    private LocalDate dateEnd;

    private Long filmId;
    private Long userId;
}
