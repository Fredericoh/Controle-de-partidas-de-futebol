package br.edu.faculdadedelta.controller;

import br.edu.faculdadedelta.dao.StatusDAO;
import br.edu.faculdadedelta.modelo.Status;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static br.edu.faculdadedelta.util.FacesUtil.exibirMensagem;

@ManagedBean
@SessionScoped
public class StatusController {

    private Status status = new Status ();
    private StatusDAO dao = new StatusDAO ();

    private static final String PAGINA_CADASTRO_STATUS = "cadastroStatus";
    private static final String PAGINA_LISTA_STATUS = "listaStatus";

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void limparCampos(){

        status = new Status ();
    }

    public String salvar(){

        try {
            if (status.getIdStatus () == null){

                dao.incluir (status);
                limparCampos ();
                exibirMensagem ("Inclusão realizada com sucesso. ");


            } else {

                dao.alterar (status);
                limparCampos ();
                exibirMensagem ("Alteração realizada com sucesso. ");

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace ();
            exibirMensagem ("Erro ao realizar a operação " + e.getMessage ());
        }

        return PAGINA_CADASTRO_STATUS;
    }

    public String editar(){

        return PAGINA_CADASTRO_STATUS;
    }

    public String excluir(){

        try {
            dao.excluir (status);
            limparCampos ();
            exibirMensagem ("Exclusão realizada com sucesso. ");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace ();
            exibirMensagem ("Erro ao realizar a operação " + e.getMessage ());
        }

        return PAGINA_LISTA_STATUS;
    }

    public List<Status> getLista(){

        List<Status> listaRetorno = new ArrayList<> ();

        try {
            listaRetorno = dao.listar ();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace ();
            exibirMensagem ("Erro ao realizar a operação " + e.getMessage ());
        }

        return listaRetorno;
    }
}
