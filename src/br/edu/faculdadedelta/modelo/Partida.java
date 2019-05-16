package br.edu.faculdadedelta.modelo;

import java.util.Objects;

public class Partida {

    private Long idPartida;
    private String local;
    private String horarioPartida;
    private Integer golTimeCasa;
    private Integer golTimeVisitante;
    private Status status;
    private Time timeCasa;
    private Time timeVisitante;

    public Partida() {
    }

    public Partida(Long idPartida, String local, String horarioPartida, Integer golTimeCasa,
                   Integer golTimeVisitante, Status status, Time timeCasa, Time timeVisitante) {
        this.idPartida = idPartida;
        this.local = local;
        this.horarioPartida = horarioPartida;
        this.golTimeCasa = golTimeCasa;
        this.golTimeVisitante = golTimeVisitante;
        this.status = status;
        this.timeCasa = timeCasa;
        this.timeVisitante = timeVisitante;
    }

    public Long getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Long idPartida) {
        this.idPartida = idPartida;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getHorarioPartida() {
        return horarioPartida;
    }

    public void setHorarioPartida(String horarioPartida) {
        this.horarioPartida = horarioPartida;
    }

    public Integer getGolTimeCasa() {
        return golTimeCasa;
    }

    public void setGolTimeCasa(Integer golTimeCasa) {
        this.golTimeCasa = golTimeCasa;
    }

    public Integer getGolTimeVisitante() {
        return golTimeVisitante;
    }

    public void setGolTimeVisitante(Integer golTimeVisitante) {
        this.golTimeVisitante = golTimeVisitante;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Time getTimeCasa() {
        return timeCasa;
    }

    public void setTimeCasa(Time timeCasa) {
        this.timeCasa = timeCasa;
    }

    public Time getTimeVisitante() {
        return timeVisitante;
    }

    public void setTimeVisitante(Time timeVisitante) {
        this.timeVisitante = timeVisitante;
    }

    public String getPlacar(){

        String golCasaConvertido = String.valueOf (golTimeCasa);
        String golVisitanteConvertido = String.valueOf (golTimeVisitante);
        String separador = " X ";

        if (status.getDescricao ().equals ("Não iniciada")) {

            golCasaConvertido = "0";
            golVisitanteConvertido = "0";

        }
        return golCasaConvertido + separador + golVisitanteConvertido ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Partida partida = (Partida) o;
        return Objects.equals (idPartida, partida.idPartida);
    }

    @Override
    public int hashCode() {
        return Objects.hash (idPartida);
    }
}
