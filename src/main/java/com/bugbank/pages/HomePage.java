package com.bugbank.pages;

import com.bugbank.utils.Cliente;
import com.bugbank.utils.DadosContaUtils;
import com.bugbank.utils.WebElementActions;
import org.openqa.selenium.By;
import static com.bugbank.pages.Locators.*;

public class HomePage extends WebElementActions {
    DadosContaUtils dadosContaUtils = new DadosContaUtils();

    public void clicarBotaoSair(){
        clicar(SAIR_BTN);
    }

    public void clicarBotaoTransferencia(){
        clicar(TRANSFERENCIA_BTN);
    }

    public void clicarBotaoExtrato(){
        clicar(EXTRATO_BTN);
    }
    public void atualizarSaldoInicialCliente(Cliente cliente){
        dadosContaUtils.atualizarSaldoInicial(cliente);
    }

    public void atualizarSaldoAtualCliente(Cliente cliente){
        dadosContaUtils.atualizarSaldoAtual(cliente);
    }

}
