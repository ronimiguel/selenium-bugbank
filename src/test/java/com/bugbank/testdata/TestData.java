package com.bugbank.testdata;

import com.bugbank.utils.Cliente;

import java.math.BigDecimal;

public class TestData {
    public static final Cliente CLIENTE_DEBITAR = new Cliente("jsilva@email.com", "Joao Silva", "senha1", "senha1");
    public static final Cliente CLIENTE_CREDITAR = new Cliente("maze@email.com", "Maria Jose", "senha2", "senha2");
    public static final String DESCRICAO_TRANSFERENCIA = "Vaquinha do churrasco";
    public static final String TRANSFERENCIA_COM_SUCESSO = "Transferencia realizada com sucesso";
    public static final BigDecimal VALOR_TRANSFERENCIA = new BigDecimal("123.45");
}
