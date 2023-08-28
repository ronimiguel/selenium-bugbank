package com.bugbank.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.bugbank.utils.DriverFactory.getDriver;

public class WebElementActions {
    private final WebDriver driver = getDriver();
    private final Logger logger = Logger.getLogger(WebElementActions.class.getName());
    Screenshot screenshot = Screenshot.getInstance();

    /**
     * Abre o navegador e navega para a URL especificada.
     *
     * @param url A URL para a qual o navegador deve navegar.
     */
    public void abrirNavegador(String url) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);
    }

    /**
     * Clica no elemento da web especificado.
     *
     * @param elemento O localizador By do elemento a ser clicado.
     */
    public void clicar(By elemento) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(elemento));
            screenshot.capturarScreenshot();
            webElement.click();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao clicar no elemento: " + elemento.toString(), e);
        }
    }

    /**
     * Clica no elemento da web usando JavaScriptExecutor.
     *
     * @param elemento O localizador By do elemento a ser clicado.
     */
    public void clicarComJS(By elemento) {
        try {
            WebElement elementoParaClicar = driver.findElement(elemento);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            screenshot.capturarScreenshot();
            executor.executeScript("arguments[0].click();", elementoParaClicar);
        } catch (Exception e) {
            screenshot.capturarScreenshot();
            logger.log(Level.SEVERE, "Erro ao clicar no elemento com JavaScript: " + elemento.toString(), e);
        }
    }

    /**
     * Insere texto no elemento da web especificado após limpá-lo.
     *
     * @param elemento O localizador By do elemento de entrada de texto.
     * @param texto O texto a ser inserido no elemento.
     */
    public void escrever(By elemento, String texto) {
        try {
            WebElement webElement = driver.findElement(elemento);
            webElement.clear();
            webElement.sendKeys(texto);
            screenshot.capturarScreenshot();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao escrever no elemento: " + elemento.toString(), e);
        }
    }

    /**
     * Extrai atributo innertext do elemento da web especificado.
     *
     * @param elemento O localizador By do elemento de entrada de texto.
     */
    public String extrairInnerText(By elemento) {
        return driver.findElement(elemento).getAttribute("innerText");

    }

    /**
     * Clica na alternância (toggle) de um botão "Criar Conta com Saldo" se estiver desativada.
     *
     * @param elemento O localizador do elemento do botão "Criar Conta com Saldo".
     */
    public void clicarAlternanciaBotaoCriarContaComSaldo(By elemento, boolean criarContaComSaldo) {
        try {
            WebElement clicarAlternanciaIsContaComSaldo = driver.findElement(elemento);
            WebElement labelElement = clicarAlternanciaIsContaComSaldo.findElement(By.xpath("./ancestor::label"));
            String classAttribute = labelElement.getAttribute("class");

            boolean botaoDesativado = classAttribute.contains("kIwoPV");
            boolean botaoAtivado = classAttribute.contains("hsmFIT");

            if ((criarContaComSaldo && botaoDesativado) || (!criarContaComSaldo && botaoAtivado)) {
                WebElementActions webElementActions = new WebElementActions();
                screenshot.capturarScreenshot();
                webElementActions.clicarComJS(elemento);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao clicar no toggle button: " + elemento, e);
        }
    }


}
