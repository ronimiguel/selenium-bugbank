package com.bugbank.pages;

import org.openqa.selenium.By;

public class Locators {

    // RegistrarPage
    public static final By REGISTRAR_BTN = By.xpath("//button[contains(text(), 'Registrar')]");
    public static final By REGISTRAR_EMAIL_INPUT = By.xpath("(//input[@name='email'])[2]");
    public static final By REGISTRAR_NOME_INPUT = By.xpath("//input[@name='name']");
    public static final By REGISTRAR_SENHA_INPUT = By.xpath("(//input[@name='password'])[2]");
    public static final By REGISTRAR_CONFIRMAR_SENHA_INPUT = By.xpath("//input[@name='passwordConfirmation']");
    public static final By REGISTRAR_CRIAR_CONTA_COM_SALDO = By.id("toggleAddBalance");
    public static final By REGISTRAR_CADASTRAR_BTN = By.xpath("//button[contains(text(), 'Cadastrar')]");
    public static final By REGISTRAR_MSG_CADASTRO_TXT = By.xpath("//p[contains(text(), 'criada com sucesso')]");

    // Acoes comuns
    public static final By FECHAR_BTN = By.id("btnCloseModal");
    public static final By SAIR_BTN = By.id("btnExit");
    public static final By VOLTAR_BTN = By.id("btnBack");
    public static final By MSG_MODAL_TXT = By.id("modalText");

    // AcessarPage
    public static final By ACESSAR_EMAIL_INPUT = By.xpath("(//input[@name='email'])[1]");
    public static final By ACESSAR_SENHA_INPUT = By.xpath("(//input[@name='password'])[1]");
    public static final By ACESSAR_BTN = By.xpath("//button[contains(text(), 'Acessar')]");

    // HomePage
    public static final By HOME_DADOS_CONTA_TXT = By.id("toggleAddBalance");
    public static final By HOME_SALDO_CONTA_TXT = By.id("textBalance");
    public static final By HOME_MSG_BEM_VINDO_TXT = By.id("textName");

    // TransferenciaPage
    public static final By TRANSFERENCIA_BTN = By.id("btn-TRANSFERÊNCIA");
    public static final By TRANSFERENCIA_NUM_CONTA_INPUT = By.xpath("//input[@name='accountNumber']");
    public static final By TRANSFERENCIA_NUM_DIGITO_INPUT = By.xpath("//input[@name='digit']");
    public static final By TRANSFERENCIA_VALOR_INPUT = By.xpath("//input[@name='transferValue']");
    public static final By TRANSFERENCIA_DESCRICAO_INPUT = By.xpath("//input[@name='description']");
    public static final By TRANSFERENCIA_TRANSFERIR_AGORA_BTN = By.xpath("//button[contains(text(), 'Transferir agora')]");
    public static final By TRANSFERENCIA_MSG_TRANSFERENCIA_TXT = By.id("modalText");

    // ExtratoPage
    public static final By EXTRATO_BTN = By.id("btn-EXTRATO");
    public static final By EXTRATO_SALDO_DISPONIVEL_TXT = By.id("textBalanceAvailable");


}
