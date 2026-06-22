package com.minipix.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID da conta", example = "1")
    private Long id;

    @Schema(description = "Nome do titular", example = "Ana Teixeira")
    @Column(name = "nome_titular")
    private String nomeTitular;

    @Schema(description = "CPF do titular", example = "123.456.789-00")
    private String cpf;

    @Schema(description = "Nome do banco", example = "Banco XPTO")
    private String banco;

    @Schema(description = "Número da agência", example = "0001")
    private String agencia;

    @Schema(description = "Número da conta", example = "12345-6")
    @Column(name = "numero_conta")
    private String numeroConta;

    @Schema(description = "Saldo da conta", example = "1500.50")
    private BigDecimal saldo;

    @Schema(description = "Limite diário", example = "1000.00")
    @Column(name = "limite_diario")
    private BigDecimal limiteDiario;

    @Schema(description = "Data de cadastro", example = "2026-05-21")
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getLimiteDiario() {
        return limiteDiario;
    }

    public void setLimiteDiario(BigDecimal limiteDiario) {
        this.limiteDiario = limiteDiario;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
