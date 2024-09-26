package com.emazon.ms_transaction.infra.input.rest;

import com.emazon.ms_transaction.ConsUtils;
import com.emazon.ms_transaction.application.dto.supply.SupplyReqDTO;
import com.emazon.ms_transaction.application.handler.TransactionHandler;
import com.emazon.ms_transaction.domain.spi.StockFeignPort;
import com.emazon.ms_transaction.infra.security.model.CustomUserDetails;
import com.emazon.ms_transaction.infra.security.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class TransactionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @SpyBean
    private TransactionHandler transactionHandler;

    @SpyBean
    private StockFeignPort stockFeignPort;

    private static final String USER = "testUser";
    private static final String AUX_DEPOT = "AUX_DEPOT";
    private static final String CLIENT = "CLIENT";
    private static final Long USER_ID = 1L;
    private static final String PASSWORD = "password";

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    private static final String SUPPLY_ENDPOINT = ConsUtils.builderPath().withSupply().build();
    private static final String SALE_ENDPOINT = ConsUtils.builderPath().withSales().build();

    private static final SupplyReqDTO SUPPLY_REQ_DTO = SupplyReqDTO.builder().userId(ConsUtils.LONG_1)
            .item(Set.of(SupplyReqDTO.ItemQuantity.builder().articleId(ConsUtils.LONG_1).quantity(ConsUtils.LONG_1).build()))
            .build();

    @Test
    void Should_ThrowsException_When_BodyNotSent() throws Exception {
        mockMvc.perform(post(SUPPLY_ENDPOINT).header(AUTHORIZATION, BEARER + getAuxDepotToken()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void Should_Get200_When_ValidRoleAndPayload() throws Exception {
        Mockito.doNothing().when(stockFeignPort).addSupply(Mockito.any());

        mockMvc.perform(post(SUPPLY_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(SUPPLY_REQ_DTO))
                        .header(AUTHORIZATION, BEARER + getAuxDepotToken()))
                .andExpect(status().isOk());
    }

    @Test
    void Should_ValidateToken_When_ValidRoles() throws Exception {
        Mockito.doNothing().when(transactionHandler).addSupply(Mockito.any());

        mockMvc.perform(post(SUPPLY_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(SUPPLY_REQ_DTO))
                        .header(AUTHORIZATION, BEARER + getAuxDepotToken()))
                .andExpect(status().isOk());

        mockMvc.perform(post(SALE_ENDPOINT)
                        .header(AUTHORIZATION, BEARER + getClientToken()))
                .andExpect(status().isOk());
    }

    @Test
    void Should_ThrowsException_When_NonValidRoles() throws Exception {
        mockMvc.perform(post(SUPPLY_ENDPOINT).header(AUTHORIZATION, BEARER + getClientToken()))
                .andExpect(status().isForbidden());
    }

    @Test
    void Should_ThrowsException_When_NonAuthenticated() throws Exception {
        mockMvc.perform(post(SUPPLY_ENDPOINT))
                .andExpect(status().isUnauthorized());
    }

    private String getAuxDepotToken() {
        CustomUserDetails userDetail = new CustomUserDetails(USER, PASSWORD, Set.of(new SimpleGrantedAuthority("ROLE_".concat(AUX_DEPOT))), USER_ID);
        return JwtUtils.createToken(new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities()));
    }

    private String getClientToken() {
        CustomUserDetails userDetail = new CustomUserDetails(USER, PASSWORD, Set.of(new SimpleGrantedAuthority("ROLE_".concat(CLIENT))), USER_ID);
        return JwtUtils.createToken(new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities()));
    }
}