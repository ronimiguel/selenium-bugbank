package com.bugbank.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bugbank.utils.DriverFactory.getDriver;

public class DadosContaUtils {
    private final WebDriver driver = getDriver();

    public String extrairTextoMensagemComDadosDaConta(By elemento) {
       return driver.findElement(elemento).getAttribute("innerText");
    }

    /**
     * Extrai o número da conta e o dígito da mensagem fornecida.
     *
     * @param mensagem A mensagem que contém os dados da conta no formato "A conta XXX-YY foi criada com sucesso".
     * @return Um array de String contendo o número da conta na posição 0 e o dígito na posição 1. Retorna null se não encontrar nenhum padrão.
     */
    public String[] extrairContaEDigito(String mensagem) {
        Pattern pattern = Pattern.compile("A conta (\\d{2,3})-(\\d+) foi criada com sucesso");
        Matcher matcher = pattern.matcher(mensagem);

        if (matcher.find()) {
            String[] result = new String[2];
            result[0] = matcher.group(1);
            result[1] = matcher.group(2);
            return result;
        }
        return null;
    }

    /**
     * Salva o número da conta e o dígito no objeto Cliente fornecido.
     *
     * @param cliente O objeto Cliente onde os dados da conta serão salvos.
     * @param dadosContaEDigito Um array de String contendo o número da conta na posição 0 e o dígito na posição 1.
     */
    public void salvarContaEDigitoCliente(Cliente cliente, String[] dadosContaEDigito) {
        cliente.setNumeroConta(dadosContaEDigito[0]);
        cliente.setNumeroDigito(dadosContaEDigito[1]);
    }

    /**
     * Extrai os dados da conta da mensagem e salva-os no objeto Cliente fornecido.
     *
     * @param cliente O objeto Cliente onde os dados da conta serão salvos.
     * @param mensagemComDados A mensagem que contém os dados da conta no formato "A conta XXX-YY foi criada com sucesso".
     */
    public void extrairESalvarContaEDigitoCliente(Cliente cliente, String mensagemComDados) {
        String[] dadosConta = extrairContaEDigito(mensagemComDados);
        salvarContaEDigitoCliente(cliente, dadosConta);
    }
}