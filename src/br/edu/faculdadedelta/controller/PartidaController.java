package br.edu.faculdadedelta.controller;

import br.edu.faculdadedelta.dao.PartidaDAO;
import br.edu.faculdadedelta.modelo.Partida;
import br.edu.faculdadedelta.modelo.Status;
import br.edu.faculdadedelta.modelo.Time;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static br.edu.faculdadedelta.util.FacesUtil.exibirMensagem;

@ManagedBean
@SessionScoped
public class PartidaController {

    private Partida partida = new Partida ();
    private PartidaDAO dao = new PartidaDAO ();

    private Time timeSelecionado = new Time ();
    private Status statusSelecionado = new Status ();
    private Time timeVisitanteSelecionado = new Time ();

    private static final String PAGINA_CADASTRO_PARTIDA = "cadastroPartida";
    private static final String PAGINA_LISTA_PARTIDA = "listaPartida";

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Time getTimeSelecionado() {
        return timeSelecionado;
    }

    public void setTimeSelecionado(Time timeSelecionado) {
        this.timeSelecionado = timeSelecionado;
    }

    public Status getStatusSelecionado() {
        return statusSelecionado;
    }

    public void setStatusSelecionado(Status statusSelecionado) {
        this.statusSelecionado = statusSelecionado;
    }

    public Time getTimeVisitanteSelecionado() {
        return timeVisitanteSelecionado;
    }

    public void setTimeVisitanteSelecionado(Time timeVisitanteSelecionado) {
        this.timeVisitanteSelecionado = timeVisitanteSelecionado;
    }

    public void limparCampos(){

        partida = new Partida ();
        timeSelecionado = new Time ();
        statusSelecionado = new Status ();
        timeVisitanteSelecionado = new Time ();
    }

    public String salvar(){

        try {

            if (partida.getIdPartida () == null){

                partida.setTimeCasa (timeSelecionado);
                partida.setStatus (statusSelecionado);
                partida.setTimeVisitante (timeVisitanteSelecionado);
                dao.incluir (partida);
                limparCampos ();
                exibirMensagem ("Inclusão realizada com sucesso. ");


            } else {

                partida.setTimeCasa (timeSelecionado);
                partida.setStatus (statusSelecionado);
                partida.setTimeVisitante (timeVisitanteSelecionado);
                dao.alterar (partida);
                limparCampos ();
                exibirMensagem ("Alteração realizada com sucesso. ");

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace ();
            exibirMensagem ("Erro ao realizar a operação " + e.getMessage ());
        }
        return PAGINA_CADASTRO_PARTIDA;
    }

    public String editar(){

        timeSelecionado = partida.getTimeCasa ();
        statusSelecionado = partida.getStatus ();
        timeVisitanteSelecionado = partida.getTimeVisitante ();

        return PAGINA_CADASTRO_PARTIDA;
    }

    public String excluir(){

        try {
            dao.excluir (partida);
            limparCampos ();
            exibirMensagem ("Exclusão realizada com sucesso. ");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace ();
            exibirMensagem ("Erro ao realizar a operação " + e.getMessage ());
        }

        return PAGINA_LISTA_PARTIDA;
    }

    public List<Partida> getLista(){

        List<Partida> listaRetorno = new ArrayList<> ();

        try {
            listaRetorno = dao.listar ();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace ();
            exibirMensagem ("Erro ao realizar a operação " + e.getMessage ());
        }

        return listaRetorno;
    }
}
