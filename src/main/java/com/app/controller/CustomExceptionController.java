package com.app.controller;

import com.app.exception.MyException;
import com.app.model.dto.InfoDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionController {

    @ExceptionHandler({MyException.class})
    public InfoDto exceptionInfo(MyException e) {
        return InfoDto.builder().error(e.getMessage()).build();
    }

}
