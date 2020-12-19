package com.app.model.dto;

import com.app.exception.ExceptionInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoDto<T> {

    @Builder.Default
    private T data = null;

    @Builder.Default
    private String error = null;
}
