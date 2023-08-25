package com.bugbank.utils;

import java.math.BigDecimal;

public class Cliente {
    private String email;
    private String nome;
    private String senha;
    private String confirmarSenha;
    private String numeroConta;
    private String numeroDigito;
    private BigDecimal saldoInicial;
    private BigDecimal saldoAtual;

    /**
     * Construtor para inicializar uma instância de Cliente.
     *
     * @param email          O endereço de email do cliente.
     * @param nome           O nome do cliente.
     * @param senha          A senha do cliente.
     * @param confirmarSenha A confirmação da senha do cliente.
     */
    public Cliente(String email, String nome, String senha, String confirmarSenha) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.confirmarSenha = confirmarSenha;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getNumeroDigito() {
        return numeroDigito;
    }

    public void setNumeroDigito(String numeroDigito) {
        this.numeroDigito = numeroDigito;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public BigDecimal getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(BigDecimal saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }
}
