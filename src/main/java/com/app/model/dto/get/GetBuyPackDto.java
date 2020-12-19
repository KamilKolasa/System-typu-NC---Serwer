package com.app.model.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetBuyPackDto {

    private Long id;

    private LocalDate dateStart;
    private LocalDate dateEnd;

    private GetPackDto pack;
    private GetUserDto user;
}
