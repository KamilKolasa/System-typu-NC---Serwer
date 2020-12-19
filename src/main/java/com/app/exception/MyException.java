package com.app.exception;

import lombok.AllArgsConstructor;

import java.util.function.Supplier;

@AllArgsConstructor
public class MyException extends RuntimeException {
    private ExceptionInfo exceptionInfo;

    public MyException(ExceptionCode code, String message) {
        this.exceptionInfo = new ExceptionInfo(code, message);
    }

    public ExceptionInfo getExceptionInfo() {
        return exceptionInfo;
    }
}
