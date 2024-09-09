package com.emazon.ms_transaction.infra.exception;

import com.emazon.ms_transaction.infra.exceptionhandler.ExceptionResponse;
import org.springframework.security.core.AuthenticationException;

public class InvalidBearerTokenException extends AuthenticationException {
    public InvalidBearerTokenException() {
        super(ExceptionResponse.INVALID_TOKEN);
    }
}
