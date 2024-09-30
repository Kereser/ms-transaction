package com.emazon.ms_transaction.infra.input.rest;

import com.emazon.ms_transaction.ConsUtils;
import com.emazon.ms_transaction.application.dto.supply.SupplyReqDTO;
import com.emazon.ms_transaction.application.handler.TransactionHandler;
import com.emazon.ms_transaction.infra.exceptionhandler.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class TransactionControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private TransactionHandler transactionHandler;

    private static final String AUX_DEPOT = "AUX_DEPOT";
    private static final String CLIENT = "CLIENT";
    private static final String SUPPLY_URL = ConsUtils.builderPath().withSupply().build();

    @Test
    @WithMockUser(roles = AUX_DEPOT)
    void Should_ThrowsException_When_InvalidPayload() throws Exception {
        mockMvc.perform(post(SUPPLY_URL).contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(SupplyReqDTO.builder().build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(ConsUtils.FIELD_MESSAGE).value(ExceptionResponse.FIELD_VALIDATION_ERRORS))
                .andExpect(jsonPath(ConsUtils.FIELD_ITEM).value(ConsUtils.NOT_NULL))
                .andExpect(jsonPath(ConsUtils.FIELD_USER_ID).value(ConsUtils.NOT_NULL));
    }

    @Test
    @WithMockUser(roles = CLIENT)
    void Should_ThrowsException_When_InvalidRole() throws Exception {
        mockMvc.perform(post(SUPPLY_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(SupplyReqDTO.builder().build())))
                .andExpect(status().isForbidden());
    }

    @Test
    void Should_ThrowsException_When_NotAuthorized() throws Exception {
        mockMvc.perform(post(SUPPLY_URL)).andExpect(status().isUnauthorized());
    }
}
