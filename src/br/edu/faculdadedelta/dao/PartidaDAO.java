package br.edu.faculdadedelta.dao;

import br.edu.faculdadedelta.modelo.Partida;
import br.edu.faculdadedelta.modelo.Status;
import br.edu.faculdadedelta.modelo.Time;
import br.edu.faculdadedelta.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartidaDAO {

    private void executarSqlSemRetorno(PreparedStatement ps, Object... params) throws SQLException {

        if (ps != null){

            for (int i = 0; i < params.length; i++){

                Object param = params[i];

                if (param instanceof Integer){

                    ps.setInt (i + 1, ((Integer) param));

                } else if (param instanceof String){

                    ps.setString (i + 1, (String) param);

                } else if (param instanceof Long){

                    ps.setLong (i + 1, (Long)param);

                } else if (param instanceof java.util.Date){
                    Date data = (Date) param;
                    ps.setDate (i + 1, new java.sql.Date(data.getTime ()));
                }

            }

            ps.executeUpdate ();
        }

    }

    public void incluir(Partida partida) throws SQLException, ClassNotFoundException {

        Connection conn = Conexao.conectarNoBancoDeDados ();

        String sql = "INSERT INTO partidas (local_partida, horario_partida, id_time_casa, id_time_visitante, " +
                "gol_time_casa, gol_time_visitante, id_status_partida, data_partida) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(sql);

            executarSqlSemRetorno (ps,
                    partida.getLocal ().trim (),
                    partida.getHorarioPartida ().trim (),
                    partida.getTimeCasa ().getIdTime (),
                    partida.getTimeVisitante ().getIdTime (),
                    partida.getGolTimeCasa (),
                    partida.getGolTimeVisitante (),
                    partida.getStatus ().getIdStatus (),
                    partida.getDataPartida ());

        } catch (SQLException e){

            throw new SQLException (e);

        } finally {

            Conexao.fecharConexao (ps, conn, null);

        }
    }

    public void alterar(Partida partida) throws SQLException, ClassNotFoundException {

        Connection conn = Conexao.conectarNoBancoDeDados ();

        String sql = "UPDATE partidas SET local_partida = ?, horario_partida = ?, id_time_casa = ?, " +
                "id_time_visitante = ?, gol_time_casa = ?, gol_time_visitante = ?, id_status_partida = ?, " +
                "data_partida = ? WHERE id_partidas = ?";

        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(sql);

            executarSqlSemRetorno (ps, partida.getLocal ().trim (),
                    partida.getHorarioPartida ().trim (),
                    partida.getTimeCasa ().getIdTime (),
                    partida.getTimeVisitante ().getIdTime (),
                    partida.getGolTimeCasa (),
                    partida.getGolTimeVisitante (),
                    partida.getStatus ().getIdStatus (),
                    partida.getIdPartida (),
                    partida.getDataPartida ());

        } catch (SQLException e){

            throw new SQLException (e);

        } finally {

            Conexao.fecharConexao (ps, conn, null);

        }
    }

    public void excluir(Partida partida) throws SQLException, ClassNotFoundException {

        Connection conn = Conexao.conectarNoBancoDeDados ();

        String sql = "DELETE FROM partidas WHERE id_partidas = ?";

        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(sql);

            executarSqlSemRetorno (ps, partida.getIdPartida ());

        } catch (SQLException e){

            throw new SQLException (e);

        } finally {

            Conexao.fecharConexao (ps, conn, null);
        }

    }

    public List<Partida> listar() throws SQLException, ClassNotFoundException {

        Connection conn = Conexao.conectarNoBancoDeDados ();

        String sql = "SELECT p.id_partidas AS idPartida, " +
                " p.local_partida AS localPartida, " +
                " p.horario_partida AS horarioPartida, " +
                " p.gol_time_casa AS golCasa, " +
                " p.gol_time_visitante AS golVisitante, " +
                " p.data_partida AS dataPartida, " +
                " tc.id_time AS idTimeCasa, " +
                " tc.nome AS nomeTimeCasa, " +
                " tv.id_time AS idTimeVisitante, " +
                " tv.nome AS nomeTimeVisitante, " +
                " sp.id_status AS idStatus, " +
                " sp.descricao AS descricaoStatus " +
                "FROM partidas AS p " +
                "INNER JOIN times AS tc ON p.id_time_casa = tc.id_time " +
                "INNER JOIN times AS tv ON p.id_time_visitante = tv.id_time " +
                "INNER JOIN status_partida AS sp ON p.id_status_partida = sp.id_status " +
                "ORDER BY p.data_partida ASC ";

        PreparedStatement ps = null;

        ResultSet rs = null;

        List<Partida> listaRetorno = new ArrayList<> ();

        try {

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next ()){

                Partida partida = new Partida ();
                partida.setIdPartida (rs.getLong ("idPartida"));
                partida.setLocal (rs.getString ("localPartida").trim ());
                partida.setHorarioPartida (rs.getString ("horarioPartida").trim ());
                partida.setGolTimeCasa (rs.getInt ("golCasa"));
                partida.setGolTimeVisitante (rs.getInt ("golVisitante"));
                partida.setDataPartida (rs.getDate ("dataPartida"));

                Time timeCasa = new Time ();
                timeCasa.setIdTime (rs.getLong ("idTimeCasa"));
                timeCasa.setNomeTime (rs.getString ("nomeTimeCasa").trim ());

                Time timeVisitante = new Time ();
                timeVisitante.setIdTime (rs.getLong ("idTimeVisitante"));
                timeVisitante.setNomeTime (rs.getString ("nomeTimeVisitante").trim ());

                Status status = new Status ();
                status.setIdStatus (rs.getLong ("idStatus"));
                status.setDescricao (rs.getString ("descricaoStatus").trim ());

                partida.setTimeCasa (timeCasa);
                partida.setTimeVisitante (timeVisitante);
                partida.setStatus (status);

                listaRetorno.add (partida);
            }

        } catch (SQLException e){

            throw new SQLException (e);

        } finally {

            Conexao.fecharConexao (ps, conn, rs);
        }

        return listaRetorno;
    }
}
