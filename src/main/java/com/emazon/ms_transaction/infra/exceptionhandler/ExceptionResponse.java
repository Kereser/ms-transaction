package com.emazon.ms_transaction.infra.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    public static final String INVALID_TOKEN = "Invalid token.";
    public static final String FIELD_VALIDATION_ERRORS = "Request has field validation errors";

    private String message;
    private Map<String, Object> fieldErrors;
}
