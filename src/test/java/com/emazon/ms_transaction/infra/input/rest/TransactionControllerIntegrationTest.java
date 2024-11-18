package com.emazon.ms_transaction.infra.input.rest;

import com.emazon.ms_transaction.ConsUtils;
import com.emazon.ms_transaction.TestCreationUtils;
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

    private static final String SUPPLY_ENDPOINT = ConsUtils.builderPath().withSupply().build();
    private static final String SALE_ENDPOINT = ConsUtils.builderPath().withSales().build();

    private static final SupplyReqDTO SUPPLY_REQ_DTO = SupplyReqDTO.builder().userId(ConsUtils.LONG_1)
            .item(Set.of(SupplyReqDTO.ItemQuantity.builder().articleId(ConsUtils.LONG_1).quantity(ConsUtils.LONG_1).build()))
            .build();

    @Test
    void Should_ThrowsException_When_BodyNotSent() throws Exception {
        mockMvc.perform(post(SUPPLY_ENDPOINT).header(ConsUtils.AUTHORIZATION_HEADER, ConsUtils.BEARER + getAuxDepotToken()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void Should_Get200_When_ValidRoleAndPayload() throws Exception {
        Mockito.doNothing().when(stockFeignPort).addSupply(Mockito.any());

        mockMvc.perform(post(SUPPLY_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(SUPPLY_REQ_DTO))
                        .header(ConsUtils.AUTHORIZATION_HEADER, ConsUtils.BEARER + getAuxDepotToken()))
                .andExpect(status().isOk());
    }

    @Test
    void Should_ValidateToken_When_ValidRoles() throws Exception {
        Mockito.doNothing().when(transactionHandler).addSupply(Mockito.any());

        mockMvc.perform(post(SUPPLY_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(SUPPLY_REQ_DTO))
                        .header(ConsUtils.AUTHORIZATION_HEADER, ConsUtils.BEARER + getAuxDepotToken()))
                .andExpect(status().isOk());

        mockMvc.perform(post(SALE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TestCreationUtils.createSaleReqDTO()))
                        .header(ConsUtils.AUTHORIZATION_HEADER, ConsUtils.BEARER + getClientToken()))
                .andExpect(status().isOk());
    }

    @Test
    void Should_ThrowsException_When_NotValidRoles() throws Exception {
        mockMvc.perform(post(SUPPLY_ENDPOINT).header(ConsUtils.AUTHORIZATION_HEADER, ConsUtils.BEARER + getClientToken()))
                .andExpect(status().isForbidden());

        mockMvc.perform(post(SALE_ENDPOINT)
                        .header(ConsUtils.AUTHORIZATION_HEADER, ConsUtils.BEARER + getAuxDepotToken()))
                .andExpect(status().isForbidden());
    }

    @Test
    void Should_ThrowsException_When_NotAuthenticated() throws Exception {
        mockMvc.perform(post(SUPPLY_ENDPOINT))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(post(ConsUtils.builderPath().withSales().build()))
                .andExpect(status().isUnauthorized());
    }

    private String getAuxDepotToken() {
        CustomUserDetails userDetail = new CustomUserDetails(ConsUtils.USER, ConsUtils.PASSWORD, Set.of(new SimpleGrantedAuthority(ConsUtils.ROLE.concat(ConsUtils.AUX_DEPOT))), ConsUtils.USER_ID_1);
        return JwtUtils.createToken(new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities()));
    }

    private String getClientToken() {
        CustomUserDetails userDetail = new CustomUserDetails(ConsUtils.USER, ConsUtils.PASSWORD, Set.of(new SimpleGrantedAuthority(ConsUtils.ROLE.concat(ConsUtils.CLIENT))), ConsUtils.USER_ID_1);
        return JwtUtils.createToken(new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities()));
    }
}