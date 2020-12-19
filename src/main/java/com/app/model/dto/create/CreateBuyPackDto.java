package com.app.model.dto.create;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBuyPackDto {

    private LocalDate dateStart;
    private LocalDate dateEnd;

    private Long packId;
    private Long userId;
}
