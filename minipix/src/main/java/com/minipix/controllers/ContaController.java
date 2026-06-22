package com.minipix.controllers;


import com.minipix.models.Conta;
import com.minipix.services.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contas")
@Tag(name = "Contas", description = "API responsável pelo gerenciamento de contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping("/sample")
    public Conta getContaSample(){
        return contaService.getContaSample();
    }

    @PostMapping
    @Operation(summary = "Criar conta",
            description = "Cria uma nova conta bancária")
    public Conta criarConta(@RequestBody Conta conta) {
        return contaService.criarConta(conta);
    }

    @GetMapping("/list")
    @Operation(summary = "Listar contas",
            description = "Retorna todas as contas cadastradas")
    public List<Conta> listarContas() {
        return contaService.listarContas();
    }

}
