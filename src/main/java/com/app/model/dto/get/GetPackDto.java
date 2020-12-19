package com.app.model.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPackDto {

    private Long id;

    private String name;
    private String description;
    private Integer discount;

    private GetPackPhoneDto packPhone;
    private GetPackInternetDto packInternet;
    private GetPackTvDto packTv;
}
