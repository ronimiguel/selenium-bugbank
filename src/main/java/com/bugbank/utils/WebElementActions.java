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
            executor.executeScript("arguments[0].click();", elementoParaClicar);
        } catch (Exception e) {
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
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao escrever no elemento: " + elemento.toString(), e);
        }
    }
}