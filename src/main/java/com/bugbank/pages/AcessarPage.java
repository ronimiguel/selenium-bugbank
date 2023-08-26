package com.bugbank.pages;

import com.bugbank.utils.WebElementActions;

import static com.bugbank.pages.Locators.ACESSAR_EMAIL_INPUT;
import static com.bugbank.pages.Locators.ACESSAR_SENHA_INPUT;
import static com.bugbank.pages.Locators.ACESSAR_BTN;
import com.bugbank.utils.Cliente;

public class AcessarPage extends WebElementActions {
    public void preencherEmail(String email) {
        escrever(ACESSAR_EMAIL_INPUT, email);
    }

    public void preencherSenha(String senha) {
        escrever(ACESSAR_SENHA_INPUT, senha);
    }

    public void clicarBotaoAcessar() {
        clicar(ACESSAR_BTN);
    }

    public void acessarConta(Cliente cliente) {
        preencherEmail(cliente.getEmail());
        preencherSenha(cliente.getSenha());
        clicarBotaoAcessar();
    }
}
