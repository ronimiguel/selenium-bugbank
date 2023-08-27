package com.bugbank.pages;
import com.bugbank.utils.Cliente;
import com.bugbank.utils.WebElementActions;

import java.math.BigDecimal;

import static com.bugbank.pages.Locators.*;
public class TransferirPage extends WebElementActions {

    public void preencherNumeroDaConta(Cliente cliente){
        escrever(TRANSFERENCIA_NUM_CONTA_INPUT,cliente.getNumeroConta());
    }
    public void preencherNumeroDoDigito(Cliente cliente){
        escrever(TRANSFERENCIA_NUM_DIGITO_INPUT,cliente.getNumeroDigito());
    }

    public void preencherValorDaTransferencia(BigDecimal valorTransferencia){
        escrever(TRANSFERENCIA_VALOR_INPUT,valorTransferencia.toString());
    }

    public void preencherDescricaoTransferencia(String descricao){
        escrever(TRANSFERENCIA_DESCRICAO_INPUT, descricao);
    }

    public void clicarBotaoTransferirAgora(){
        clicar(TRANSFERENCIA_TRANSFERIR_AGORA_BTN);
    }

    public String extrairTextoMensagemModal(){
        return extrairInnerText(TRANSFERENCIA_MSG_TRANSFERENCIA_TXT);
    }

    public void clicarBotaoFechar(){
        clicar(FECHAR_BTN);
    }

    public void clicarBotaoVoltar(){
        clicar(VOLTAR_BTN);
    }

    public void realizarTransferencia(Cliente clienteCreditado, BigDecimal valorTransferencia, String descricao){
        preencherNumeroDaConta(clienteCreditado);
        preencherNumeroDoDigito(clienteCreditado);
        preencherValorDaTransferencia(valorTransferencia);
        preencherDescricaoTransferencia(descricao);
        clicarBotaoTransferirAgora();

    }

}
