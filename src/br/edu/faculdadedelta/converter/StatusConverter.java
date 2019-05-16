package br.edu.faculdadedelta.converter;

import br.edu.faculdadedelta.dao.StatusDAO;
import br.edu.faculdadedelta.modelo.Status;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.sql.SQLException;

@FacesConverter(value = "statusConverter")
public class StatusConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String valor) {

        if (valor != null){

            try {
                return new StatusDAO ().pesquisarPorId (Long.valueOf (valor));
            } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
                e.printStackTrace ();
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object valor) {

        if (valor != null){

            return String.valueOf (((Status)valor).getIdStatus ());
        }

        return null;
    }
}
