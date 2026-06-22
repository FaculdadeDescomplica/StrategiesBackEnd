package com.minipix.controllers;


import com.minipix.models.Conta;
import com.minipix.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping("/sample")
    public Conta getContaSample(){
        return contaService.getContaSample();
    }

    @PostMapping
    public Conta criarConta(@RequestBody Conta conta) {
        return contaService.criarConta(conta);
    }

    @GetMapping("/list")
    public List<Conta> listarContas() {
        return contaService.listarContas();
    }

}
