package br.edu.faculdadedelta.modelo;

import java.util.Objects;

public class Status {

    private Long idStatus;
    private String descricao;

    public Status() {
    }

    public Status(Long idStatus, String descricao) {
        this.idStatus = idStatus;
        this.descricao = descricao;
    }

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Status status = (Status) o;
        return Objects.equals (idStatus, status.idStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash (idStatus);
    }
}
