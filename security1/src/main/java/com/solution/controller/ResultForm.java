package com.solution.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultForm<T> {

    private int errorCode;
    private String errorMessage;
    private T data;

    public ResultForm (int errorCode, String errorMessage, T data) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }
}

