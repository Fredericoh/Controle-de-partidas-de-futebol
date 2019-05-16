package br.edu.faculdadedelta.controller;

import br.edu.faculdadedelta.dao.TimeDAO;
import br.edu.faculdadedelta.modelo.Time;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static br.edu.faculdadedelta.util.FacesUtil.exibirMensagem;

@ManagedBean
@SessionScoped
public class TimeController {

    private Time time = new Time ();
    private TimeDAO dao = new TimeDAO ();

    private static final String PAGINA_CADASTRO_TIME = "cadastroTime";
    private static final String PAGINA_LISTA_TIME = "listaTime";

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void limparCampos(){

        time = new Time ();
    }

    public String salvar(){

        try {
            if (time.getIdTime () == null){

                dao.incluir (time);
                limparCampos ();
                exibirMensagem ("Inclusão realizada com sucesso. ");


            } else {

                dao.alterar (time);
                limparCampos ();
                exibirMensagem ("Alteração realizada com sucesso. ");

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace ();
            exibirMensagem ("Erro ao realizar a operação " + e.getMessage ());
        }

        return PAGINA_CADASTRO_TIME;
    }

    public String editar(){

        return PAGINA_CADASTRO_TIME;
    }

    public String excluir(){

        try {
            dao.excluir (time);
            limparCampos ();
            exibirMensagem ("Exclusão realizada com sucesso. ");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace ();
            exibirMensagem ("Erro ao realizar a operação " + e.getMessage ());
        }

        return PAGINA_LISTA_TIME;
    }

    public List<Time> getLista(){

        List<Time> listaRetorno = new ArrayList<> ();

        try {
            listaRetorno = dao.listar ();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace ();
            exibirMensagem ("Erro ao realizar a operação " + e.getMessage ());
        }

        return listaRetorno;
    }
}
