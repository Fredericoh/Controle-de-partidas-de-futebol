package br.edu.faculdadedelta.dao;

import br.edu.faculdadedelta.modelo.Time;
import br.edu.faculdadedelta.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimeDAO {

    private void executarSqlSemRetorno(PreparedStatement preparedStatement, Object... params) throws SQLException {
        if (preparedStatement != null) {

            for (int i = 0; i < params.length; i++) {

                Object param = params[i];

                if (param instanceof String) {
                    preparedStatement.setString (i + 1, ((String) param).trim ());

                } else if (param instanceof Long) {
                    preparedStatement.setLong (i + 1, (Long) param);
                }

            }
            preparedStatement.executeUpdate ();
        }
    }

    public void incluir(Time time) throws SQLException, ClassNotFoundException {

        Connection connection = Conexao.conectarNoBancoDeDados ();

        final String sql = "INSERT INTO times (nome) VALUES (?)";

        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = connection.prepareStatement(sql);

            executarSqlSemRetorno (preparedStatement, time.getNomeTime ().trim ());

        } catch (SQLException e){

            throw new SQLException (e);

        } finally {
            Conexao.fecharConexao (preparedStatement, connection,null);
        }
    }

    public void alterar(Time time) throws SQLException, ClassNotFoundException {

        Connection connection = Conexao.conectarNoBancoDeDados ();

        String sql = "UPDATE times SET nome = ? WHERE id_time = ?";

        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = connection.prepareStatement(sql);

            executarSqlSemRetorno (preparedStatement, time.getNomeTime ().trim (), time.getIdTime ());

        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            Conexao.fecharConexao (preparedStatement,connection,null);
        }

    }

    public void excluir(Time time) throws SQLException, ClassNotFoundException {

        Connection connection = Conexao.conectarNoBancoDeDados ();

        String sql = "DELETE FROM times WHERE id_time = ?";

        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = connection.prepareStatement(sql);

            executarSqlSemRetorno (preparedStatement, time.getIdTime ());

        } catch (SQLException e){

            throw new SQLException (e);

        } finally {

            Conexao.fecharConexao (preparedStatement, connection, null);

        }
    }

    private Time popularTime(ResultSet resultSet) throws SQLException {

        Time time = new Time ();
        time.setIdTime (resultSet.getLong ("id_time"));
        time.setNomeTime (resultSet.getString ("nome").trim ());

        return time;

    }

    public List<Time> listar() throws SQLException, ClassNotFoundException {

        Connection connection = Conexao.conectarNoBancoDeDados ();

        String sql = "SELECT id_time, nome FROM times ";

        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;

        List<Time> listaRetorno = new ArrayList<> ();

        try {

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next ()){

                listaRetorno.add (popularTime (resultSet));
            }

        } catch (SQLException e){

            throw new SQLException (e);

        } finally {

            Conexao.fecharConexao (preparedStatement, connection, resultSet);

        }

        return listaRetorno;
    }

    public Time pesquisarPorId(Long id) throws SQLException, ClassNotFoundException {

        Connection connection = Conexao.conectarNoBancoDeDados ();

        String sql = "SELECT id_time, nome FROM times WHERE id_time = ?";

        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;

        Time retorno = new Time ();

        try {

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong (1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next ()){
                retorno = popularTime (resultSet);

            }

        } catch (SQLException e){

            throw new SQLException (e);

        } finally {
            Conexao.fecharConexao (preparedStatement, connection, resultSet);
        }

        return retorno;
    }
}
