package com.demo.web.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiBadRequestException extends RuntimeException {
    private String message;
}
