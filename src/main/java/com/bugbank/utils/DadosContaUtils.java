package com.bugbank.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
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
     * Marca o cliente como possuidor de uma conta existente.
     *
     * @param cliente O objeto Cliente a ser marcado como possuidor de conta.
     */
    public void marcarContaExistente(Cliente cliente){
        cliente.setPossuiConta(true);
    }

    /**
     * Extrai os dados da conta da mensagem e salva-os no objeto Cliente fornecido.
     *
     * @param cliente O objeto Cliente onde os dados da conta serão salvos.
     * @param mensagemTextoComDados A mensagem que contém os dados da conta no formato "A conta XXX-YY foi criada com sucesso".
     */
    public void extrairESalvarDadosContaCliente(Cliente cliente, By mensagemTextoComDados) {
        String mensagemComDados = extrairTextoMensagemComDadosDaConta(mensagemTextoComDados);
        String[] dadosConta = extrairContaEDigito(mensagemComDados);
        salvarContaEDigitoCliente(cliente, dadosConta);
        marcarContaExistente(cliente);
    }

    public BigDecimal consultarSaldo() {

        String dadosSaldo = driver.findElement(By.id("textBalance")).getText();
        int indexOfCifrao = dadosSaldo.indexOf("$");

        // +2 é usado para pular o caractere '$' e o espaço em branco
        String saldoAtualNumerico = dadosSaldo.substring(indexOfCifrao + 2);

        // Alterar formato brasileiro de moeda para padrao numerico com ponto
        String saldoAtualComPonto = saldoAtualNumerico.replace(".", ""); // Remove pontos
        String saldoAtualLimpo = saldoAtualComPonto.replaceAll("[^\\d.,]", ""); // Remove caracteres não numéricos

        // Saldo final em BigDecimal para valores monetarios
        BigDecimal saldoAtual = new BigDecimal(saldoAtualLimpo.replace(",", ".")); // Converte para BigDecimal

//		System.out.println("Saldo: " + saldoAtual);
        return saldoAtual;

    }
    public void atualizarSaldoInicial(Cliente cliente) {
        cliente.setSaldoInicial(consultarSaldo());
    }

    public void atualizarSaldoAtual(Cliente cliente) {
        cliente.setSaldoAtual(consultarSaldo());
    }

    public String consultarDescricaoTransferenciaNoExtrato(String descricao) {
        WebElement buscaTransf = driver
                .findElement(By.xpath("//p[contains(text(), '" + descricao + "')]"));
        return buscaTransf.getAttribute("innerText");

    }
    public String consultarExtrairValorTransferenciaNoExtrato(String descricao) {
            WebElement valorElement = driver.findElement(By.xpath("//p[contains(text(), '" + descricao + "')]/following-sibling::p"));
            String text = valorElement.getAttribute("innerText");

            // Remove todos os caracteres exceto dígitos, vírgula e ponto
            String cleanedText = text.replaceAll("[^\\d,.]", "");
            cleanedText = cleanedText.replace(",", "."); // Substitui ',' por '.' para formar o valor correto

            return cleanedText.isEmpty() ? null : cleanedText;
        }

}