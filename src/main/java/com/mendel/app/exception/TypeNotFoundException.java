package com.mendel.app.exception;

public class TypeNotFoundException extends AppException {
    public TypeNotFoundException(ApiError apiError) {
        super(apiError);
    }
}
