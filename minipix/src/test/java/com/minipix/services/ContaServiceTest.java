package com.minipix.services;

import com.minipix.models.Conta;
import com.minipix.repositories.ContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ContaService contaService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarConta() {

        Conta conta = new Conta();

        conta.setNomeTitular("Maria Silva");
        conta.setCpf("12345678900");
        conta.setBanco("Banco XPTO");
        conta.setAgencia("0001");
        conta.setNumeroConta("12345-6");
        conta.setSenha("1234");
        conta.setSaldo(new BigDecimal("1000"));

        when(contaRepository.save(any()))
                .thenReturn(conta);

        Conta resultado =
                contaService.criarConta(conta);

        assertNotNull(resultado);

        assertEquals("Maria Silva",resultado.getNomeTitular());
        assertEquals("12345678900",resultado.getCpf());
        assertEquals("Banco XPTO",resultado.getBanco());
        assertEquals("0001",resultado.getAgencia());
        assertEquals("12345-6",resultado.getNumeroConta());
        assertEquals("1234",resultado.getSenha());
        assertEquals(new BigDecimal("1000"),resultado.getSaldo());

        verify(contaRepository)
                .save(any(Conta.class));
    }

    @Test
    void deveListarContas() {

        Conta conta = new Conta();

        conta.setId(1L);

        when(contaRepository.findAll())
                .thenReturn(List.of(conta));

        List<Conta> resultado =
                contaService.listarContas();

        assertEquals(1, resultado.size());

        verify(contaRepository)
                .findAll();
    }
}
