package com.minipix.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.minipix.models.Conta;
import com.minipix.repositories.ContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ContaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @MockitoBean
    private JwtDecoder jwtDecoder;

    @Autowired
    private ContaRepository contaRepository;

    @BeforeEach
    void limparBanco() {

        contaRepository.deleteAll();
    }

    @Test
    void deveCriarConta() throws Exception {

        Conta conta = new Conta();

        conta.setNomeTitular("Maria Silva");
        conta.setCpf("12345678900");
        conta.setBanco("Banco XPTO");
        conta.setAgencia("0001");
        conta.setNumeroConta("12345-6");
        conta.setSenha("1234");
        conta.setSaldo(new BigDecimal("1000"));
        conta.setLimiteDiario(new BigDecimal("1500"));

        mockMvc.perform(
                        post("/contas")
                                .with(jwt())
                                .contentType("application/json")
                                .content(mapper.writeValueAsString(conta))
                )
                .andExpect(status().isOk());
    }

    @Test
    void deveListarContas() throws Exception {

        Conta conta = new Conta();

        conta.setNomeTitular("Maria Silva");
        conta.setCpf("12345678900");
        conta.setBanco("Banco XPTO");
        conta.setAgencia("0001");
        conta.setNumeroConta("12345-6");
        conta.setSenha("1234");
        conta.setSaldo(new BigDecimal("1000"));
        conta.setLimiteDiario(new BigDecimal("1500"));

        contaRepository.save(conta);

        mockMvc.perform(
                        get("/contas/list")
                                .with(jwt())
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$[0].nomeTitular")
                                .value("Maria Silva"),
                        jsonPath("$[0].cpf")
                                .value("12345678900"),
                        jsonPath("$[0].banco")
                                .value("Banco XPTO"),
                        jsonPath("$[0].agencia")
                                .value("0001"),
                        jsonPath("$[0].numeroConta")
                                .value("12345-6"),
                        jsonPath("$[0].senha")
                                .value("1234"),
                        jsonPath("$[0].saldo")
                                .value("1000.0"),
                        jsonPath("$[0].limiteDiario")
                                .value("1500.0")
                );
    }
}
