package com.bugbank.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.bugbank.pages.*;
import com.bugbank.utils.Screenshot;
import com.bugbank.utils.WebElementActions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.bugbank.testdata.TestData.*;
import static com.bugbank.utils.DriverFactory.killDriver;
import static org.junit.jupiter.api.Assertions.*;

public class RealizarTransferenciaEntreContasTest extends WebElementActions {
    RegistrarPage registrarPage = new RegistrarPage();
    AcessarPage acessarPage = new AcessarPage();
    HomePage homePage = new HomePage();
    ExtratoPage extratoPage = new ExtratoPage();
    TransferirPage transferirPage = new TransferirPage();
    Screenshot screenshotInstance = Screenshot.getInstance();
    private ExtentReports extent;

    @BeforeEach
    public void setup() {
        String Url = "https://bugbank.netlify.app/";
        abrirNavegador(Url);
        ExtentSparkReporter spark = new ExtentSparkReporter("RelatorioDeExecucao.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @AfterEach
    public void tearDown() {
        extent.flush();
        killDriver();
    }

    @Test
    public void transferirValorParaOutraConta() {
        ExtentTest test = extent.createTest("Transferência bem-sucedida entre contas com verificação de saldo", "Validar a saída e entrada de valores das contas envolvidas").assignAuthor("Roni Miguel");

        test.info("Registrando 2 contas com saldo...");
        registrarPage.registrarContaCompleta(CLIENTE_CREDITAR, true);
        registrarPage.registrarContaCompleta(CLIENTE_DEBITAR, true);
        assertTrue(CLIENTE_CREDITAR.isPossuiConta());
        assertTrue(CLIENTE_DEBITAR.isPossuiConta());

        test.info("Acessando conta do cliente creditar e atualizando saldo inicial...");
        acessarPage.acessarConta(CLIENTE_CREDITAR);
        homePage.atualizarSaldoInicialCliente(CLIENTE_CREDITAR);
        assertNotNull(CLIENTE_CREDITAR.getSaldoInicial());
        homePage.clicarBotaoSair();

        test.info("Acessando conta do cliente debitar e atualizando saldo inicial...");
        acessarPage.acessarConta(CLIENTE_DEBITAR);
        homePage.atualizarSaldoInicialCliente(CLIENTE_DEBITAR);
        assertNotNull(CLIENTE_DEBITAR.getSaldoInicial());
        homePage.clicarBotaoTransferencia();

        test.info("Realizando transferência de " + VALOR_TRANSFERENCIA + " para a conta de " + CLIENTE_CREDITAR.getNome() + "...");
        transferirPage.realizarTransferencia(CLIENTE_CREDITAR, VALOR_TRANSFERENCIA, DESCRICAO_TRANSFERENCIA);
        assertEquals(TRANSFERENCIA_COM_SUCESSO, transferirPage.extrairTextoMensagemModal());
        transferirPage.clicarBotaoFechar();
        transferirPage.clicarBotaoVoltar();
        homePage.atualizarSaldoAtualCliente(CLIENTE_DEBITAR);
        assertEquals(CLIENTE_DEBITAR.getSaldoInicial().subtract(VALOR_TRANSFERENCIA), CLIENTE_DEBITAR.getSaldoAtual());

        test.info("Verificando a entrada da transferência no extrato de " + CLIENTE_DEBITAR.getNome() + "...");
        homePage.clicarBotaoExtrato();
        assertEquals(DESCRICAO_TRANSFERENCIA, extratoPage.consultarDescricaoTransferenciaNoExtrato(DESCRICAO_TRANSFERENCIA));
        assertEquals(VALOR_TRANSFERENCIA.toString(), extratoPage.consultarValorTransferenciaNoExtrato(DESCRICAO_TRANSFERENCIA));
        homePage.clicarBotaoSair();

        test.info("Verificando saldo atualizado de " + CLIENTE_CREDITAR.getNome() + " após a transferência...");
        acessarPage.acessarConta(CLIENTE_CREDITAR);
        homePage.atualizarSaldoAtualCliente(CLIENTE_CREDITAR);
        assertEquals(CLIENTE_CREDITAR.getSaldoInicial().add(VALOR_TRANSFERENCIA), CLIENTE_CREDITAR.getSaldoAtual());

        test.info("Verificando a entrada da transferência no extrato de " + CLIENTE_CREDITAR.getNome() + "...");
        homePage.clicarBotaoExtrato();
        assertEquals(DESCRICAO_TRANSFERENCIA, extratoPage.consultarDescricaoTransferenciaNoExtrato(DESCRICAO_TRANSFERENCIA));
        assertEquals(VALOR_TRANSFERENCIA.toString(), extratoPage.consultarValorTransferenciaNoExtrato(DESCRICAO_TRANSFERENCIA));

        test.pass("Transferência entre contas realizada com sucesso!");
        homePage.clicarBotaoSair();

        ExtentTest testeDetalhado = extent.createTest("Screenshots de cada interacao - Transferência bem-sucedida entre contas com verificação de saldo", "Contem screenshot de cada interacao do teste").assignAuthor("Roni Miguel");
        screenshotInstance.adicionarCapturasAoRelatorio(testeDetalhado);
        testeDetalhado.pass("Transferência entre contas realizada com sucesso!");
    }
}
