package com.app.model.dto.get;

import com.app.model.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetChannelDto {

    private Long id;

    private String name;
    private String description;

    private CategoryDto category;
}
