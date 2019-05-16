package br.edu.faculdadedelta.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {

    public static void exibirMensagem(String mensagem){

        FacesMessage msg = new FacesMessage (mensagem);
        FacesContext.getCurrentInstance ().addMessage (null, msg);
    }


}