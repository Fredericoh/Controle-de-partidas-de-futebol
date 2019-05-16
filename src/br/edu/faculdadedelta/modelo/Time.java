package br.edu.faculdadedelta.modelo;

import java.util.Objects;

public class Time {

    private Long idTime;
    private String nomeTime;

    public Time() {
    }

    public Time(Long idTime, String nomeTime) {
        this.idTime = idTime;
        this.nomeTime = nomeTime;
    }

    public Long getIdTime() {
        return idTime;
    }

    public void setIdTime(Long idTime) {
        this.idTime = idTime;
    }

    public String getNomeTime() {
        return nomeTime;
    }

    public void setNomeTime(String nomeTime) {
        this.nomeTime = nomeTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Time time = (Time) o;
        return Objects.equals (idTime, time.idTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash (idTime);
    }
}
