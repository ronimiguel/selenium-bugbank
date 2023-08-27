package com.bugbank.pages;
import com.bugbank.utils.DadosContaUtils;
import com.bugbank.utils.WebElementActions;

import static com.bugbank.pages.Locators.*;
public class ExtratoPage extends WebElementActions {
    DadosContaUtils dadosContaUtils = new DadosContaUtils();
    public void clicarVoltar(){
        clicar(VOLTAR_BTN);
    }
    public String consultarDescricaoTransferenciaNoExtrato(String descricao){
        return dadosContaUtils.consultarDescricaoTransferenciaNoExtrato(descricao);
    }

    public String consultarValorTransferenciaNoExtrato(String descricao){
        return dadosContaUtils.consultarExtrairValorTransferenciaNoExtrato(descricao);
    }
}
