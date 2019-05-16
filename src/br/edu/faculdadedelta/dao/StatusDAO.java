package br.edu.faculdadedelta.dao;

import br.edu.faculdadedelta.modelo.Status;
import br.edu.faculdadedelta.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusDAO {

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

    public void incluir(final Status status) throws SQLException, ClassNotFoundException {

        Connection connection = Conexao.conectarNoBancoDeDados ();

        final String sql = "INSERT INTO status_partida (descricao) VALUES (?)";

        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = connection.prepareStatement(sql);

            executarSqlSemRetorno (preparedStatement, status.getDescricao ().trim ());

        } catch (SQLException e){

            throw new SQLException (e);

        } finally {
            Conexao.fecharConexao (preparedStatement, connection,null);
        }
    }

    public void alterar(Status status) throws SQLException, ClassNotFoundException {

        Connection connection = Conexao.conectarNoBancoDeDados ();

        String sql = "UPDATE status_partida SET descricao = ? WHERE id_status = ?";

        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = connection.prepareStatement(sql);

            executarSqlSemRetorno (preparedStatement, status.getDescricao ().trim (), status.getIdStatus ());

        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            Conexao.fecharConexao (preparedStatement,connection,null);
        }

    }

    public void excluir(Status status) throws SQLException, ClassNotFoundException {

        Connection connection = Conexao.conectarNoBancoDeDados ();

        String sql = "DELETE FROM status_partida WHERE id_status = ?";

        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = connection.prepareStatement(sql);

            executarSqlSemRetorno (preparedStatement, status.getIdStatus ());

        } catch (SQLException e){

            throw new SQLException (e);

        } finally {

            Conexao.fecharConexao (preparedStatement, connection, null);

        }
    }

    private Status popularStatus(ResultSet resultSet) throws SQLException {

        Status status = new Status ();
        status.setIdStatus (resultSet.getLong ("id_status"));
        status.setDescricao (resultSet.getString ("descricao").trim ());

        return status;

    }

    public List<Status> listar() throws SQLException, ClassNotFoundException {

        Connection connection = Conexao.conectarNoBancoDeDados ();

        String sql = "SELECT id_status, descricao FROM status_partida ";

        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;

        List<Status> listaRetorno = new ArrayList<> ();

        try {

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next ()){

                listaRetorno.add (popularStatus (resultSet));
            }

        } catch (SQLException e){

            throw new SQLException (e);

        } finally {

            Conexao.fecharConexao (preparedStatement, connection, resultSet);

        }

        return listaRetorno;
    }

    public Status pesquisarPorId(Long id) throws SQLException, ClassNotFoundException {

        Connection connection = Conexao.conectarNoBancoDeDados ();

        String sql = "SELECT id_status, descricao FROM status_partida WHERE id_status = ?";

        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;

        Status retorno = new Status ();

        try {

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong (1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next ()){
                retorno = popularStatus (resultSet);

            }

        } catch (SQLException e){

            throw new SQLException (e);

        } finally {
            Conexao.fecharConexao (preparedStatement, connection, resultSet);
        }

        return retorno;
    }
}
