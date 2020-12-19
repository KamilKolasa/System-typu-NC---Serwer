package com.app.model.dto.create;

import com.app.model.enums.TypeInternet;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePackInternetDto {

    private String name;
    private String description;
    private Integer speedDownload; //in Mb/s
    private Integer speedUpload; //in Mb/s
    private TypeInternet typeInternet;
    private BigDecimal price;
}
