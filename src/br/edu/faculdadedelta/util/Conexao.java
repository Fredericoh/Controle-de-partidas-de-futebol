package br.edu.faculdadedelta.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {

    public static Connection conectarNoBancoDeDados()
            throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://localhost:5432/WEB2";

        String usuario = "postgres";
        String senha = "root";

        Connection conn = null;

        conn = DriverManager.getConnection(url, usuario, senha);

        return conn;

    }

    public static void fecharConexao(PreparedStatement ps,
                                     Connection conn, ResultSet rs)
            throws SQLException {
        if (ps != null) {
            ps.close();
        }
        if (rs != null) {
            rs.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public static void main(String[] args) {
        try {
            conectarNoBancoDeDados();
            System.out.println("Conectou no banco de dados!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}