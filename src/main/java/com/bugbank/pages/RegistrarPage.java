package com.bugbank.pages;

import com.bugbank.utils.Cliente;
import com.bugbank.utils.DadosContaUtils;
import com.bugbank.utils.WebElementActions;

import static com.bugbank.pages.Locators.*;

public class RegistrarPage extends WebElementActions {

    DadosContaUtils dadosContaUtils = new DadosContaUtils();

    public void clicarBotaoRegistrar() {
        clicar(REGISTRAR_BTN);
    }

    public void preencherEmail(String email) {
        escrever(REGISTRAR_EMAIL_INPUT, email);
    }

    public void preencherNome(String nome) {
        escrever(REGISTRAR_NOME_INPUT, nome);
    }

    public void preencherSenha(String senha) {
        escrever(REGISTRAR_SENHA_INPUT, senha);
    }

    public void confirmarSenha(String confirmarSenha) {
        escrever(REGISTRAR_CONFIRMAR_SENHA_INPUT, confirmarSenha);
    }

    public void selecionarCriarContaComSaldo(boolean contaComSaldo) {
        clicarAlternanciaBotaoCriarContaComSaldo(REGISTRAR_CRIAR_CONTA_COM_SALDO_BTN, contaComSaldo);
    }

    public void clicarBotaoCadastrar() {
        clicar(REGISTRAR_CADASTRAR_BTN);
    }

    public void fecharModal() {
        clicar(FECHAR_BTN);
    }

    public void registrarContaCompleta(Cliente cliente, boolean contaComSaldo) {
        clicarBotaoRegistrar();
        preencherEmail(cliente.getEmail());
        preencherNome(cliente.getNome());
        preencherSenha(cliente.getSenha());
        confirmarSenha(cliente.getConfirmarSenha());
        selecionarCriarContaComSaldo(contaComSaldo);
        clicarBotaoCadastrar();
        dadosContaUtils.extrairESalvarDadosContaCliente(cliente, REGISTRAR_MSG_CADASTRO_TXT);
        fecharModal();
    }
}
