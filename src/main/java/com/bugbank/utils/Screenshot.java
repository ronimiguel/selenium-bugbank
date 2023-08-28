package com.bugbank.utils;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.bugbank.utils.DriverFactory.getDriver;

public class Screenshot {
    private final Map<String, String> screenshotMap = new LinkedHashMap<>();
    private static Screenshot instance = null;
    private int screenshotCounter = 0;

    private Screenshot() {
    }

    /**
     * Retorna a instância singleton da classe Screenshot.
     *
     * @return A instância de Screenshot.
     */
    public static Screenshot getInstance() {
        if (instance == null) {
            instance = new Screenshot();
        }
        return instance;
    }

    /**
     * Adiciona uma screenshot ao mapa de screenshots com a mensagem fornecida.
     *
     * @param message         A descrição ou mensagem associada a screenshot.
     * @param screenshotBase64 A screenshot codificada em Base64.
     */
    private void addScreenshot(String message, String screenshotBase64) {
        screenshotMap.put(message, screenshotBase64);
    }

    /**
     * Recupera o mapa contendo os screenshots e suas mensagens associadas.
     *
     * @return O mapa de screenshots.
     */
    private Map<String, String> getScreenshotMap() {
        return screenshotMap;
    }

    /**
     * Captura um screenshot e a retorna como uma string codificada em Base64.
     *
     * @return Um screenshot codificada em Base64.
     */
    private String capturarScreenshotAsBase64() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length >= 3) {
            TakesScreenshot screenShot = ((TakesScreenshot) getDriver());
            return screenShot.getScreenshotAs(OutputType.BASE64);
        }
        return null;
    }

    /**
     * Captura um screenshot em cada etapa e adiciona ao mapa de screenshots.
     * @author Roni Miguel
     */
    public void capturarScreenshot() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String callingClassName = stackTrace[3].getClassName(); // O índice 3 representa a classe que chamou esse metodo
        String callingMethodName = stackTrace[3].getMethodName(); // O índice 3 representa o método que chamou esse metodo

        // Obter apenas o nome da classe sem o pacote
        String classNameWithoutPackage = callingClassName.substring(callingClassName.lastIndexOf(".") + 1);

        try {
            String screenshotBase64 = capturarScreenshotAsBase64();
            addScreenshot(classNameWithoutPackage + "_" + callingMethodName + "_" + screenshotCounter, screenshotBase64);
            screenshotCounter++;
        } catch (Exception e) {
            String screenshotBase64 = capturarScreenshotAsBase64();
            addScreenshot("Erro_" + classNameWithoutPackage + "_" + callingMethodName + "_" + screenshotCounter, screenshotBase64);
            screenshotCounter++;
        }
    }

    /**
     * Processa as capturas de tela e as adiciona ao relatório.
     *
     * @param test O objeto ExtentTest ao qual adicionar as capturas de tela.
     * @author Roni Miguel
     */
    public void adicionarCapturasAoRelatorio(ExtentTest test) {
        Map<String, String> screenshotMap = getScreenshotMap();
        for (Map.Entry<String, String> entry : screenshotMap.entrySet()) {
            test.info(entry.getKey())
                    .addScreenCaptureFromBase64String(entry.getValue(), entry.getKey());
        }
    }

}
