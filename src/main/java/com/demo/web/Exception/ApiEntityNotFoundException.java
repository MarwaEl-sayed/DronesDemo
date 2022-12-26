package com.demo.web.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiEntityNotFoundException extends RuntimeException {
    private String message;
}
