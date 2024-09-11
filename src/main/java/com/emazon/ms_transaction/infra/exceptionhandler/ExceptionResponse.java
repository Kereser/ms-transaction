package com.emazon.ms_transaction.infra.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {
    public static final String INVALID_TOKEN = "Invalid token.";

    private String message;
    private Map<String, Object> fieldErrors;
}
