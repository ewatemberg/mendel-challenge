package com.mendel.app.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppException extends RuntimeException {

    private ApiError apiError;

}
