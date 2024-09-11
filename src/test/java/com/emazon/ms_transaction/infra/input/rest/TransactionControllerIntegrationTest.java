package com.emazon.ms_transaction.infra.input.rest;

import com.emazon.ms_transaction.infra.security.model.CustomUserDetails;
import com.emazon.ms_transaction.infra.security.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

    private static final String USER = "testUser";
    private static final String AUX_DEPOT = "AUX_DEPOT";
    private static final String CLIENT = "CLIENT";
    private static final Long USER_ID = 1L;
    private static final String PASSWORD = "password";

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    private static final String SUPPLY_ENDPOINT = "/transactions/supply";
    private static final String SALE_ENDPOINT = "/transactions/sale";

    @Test
    void Should_ValidateToken_When_ValidRoles() throws Exception {
        mockMvc.perform(post(SUPPLY_ENDPOINT).header(AUTHORIZATION, BEARER + getAuxDepotToken()))
                .andExpect(status().isOk());

        mockMvc.perform(post(SALE_ENDPOINT).header(AUTHORIZATION, BEARER + getClientToken()))
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

    @Test
    void Should_ThrowsException_When_InvalidToken() throws Exception {
        mockMvc.perform(post(SUPPLY_ENDPOINT).header(AUTHORIZATION, BEARER + " "))
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