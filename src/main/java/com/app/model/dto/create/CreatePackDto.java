package com.app.model.dto.create;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePackDto {

    private String name;
    private String description;
    private Integer discount;

    private Long packPhoneId;
    private Long packInternetId;
    private Long packTvId;
}
