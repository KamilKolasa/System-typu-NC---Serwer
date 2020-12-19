package com.app.model.dto.create;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateChannelDto {

    private String name;
    private String description;

    private Long categoryId;
}
