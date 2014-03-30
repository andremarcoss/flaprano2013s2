package br.com.svbe.resource.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Classe responsável por criar e fechar a conexão com o banco de dados
 * 
 * @author Felipe Laprano
 * 
 */
public class ConnectionFactory {

	//Definição das propriedades de conexão ao banco, usadas na conexão com localhost
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/";
	private static final String USUARIO = "root";
	private static final String SENHA = "root";
	private static final String BANCO = "SVBE";

	/**
	 * Método responsável por criar a conexão com o banco
	 * 
	 * @return conexao
	 */
	public Connection abreConexao() {
		Connection conexao = null;

		try {
			
			//Definição de conexão do banco de dados para conexão e testes com localhost
			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(URL + BANCO, USUARIO, SENHA);

			/*
			 * Definição das propriedades de conexão ao banco, usadas na conexão com o Jelastic
			 * Carrega-se as propriedades da conexão através do arquivo "mydb.cfg" que está no Jelastic
			 * 
			Properties prop = new Properties();
			prop.load(new FileInputStream(System.getProperty("user.home") + "/mydb.cfg"));
			
			String url = prop.getProperty("host").toString() + BANCO;
			String usuario = prop.getProperty("username").toString();
			String senha = prop.getProperty("password").toString();
			String driver = prop.getProperty("driver").toString(); Class.forName(driver); 
			conexao = DriverManager.getConnection(url, usuario, senha);
			
			*/

		} catch (Exception e) {
			System.out.println("Erro ao criar conexão com o banco");// + URL);
			e.printStackTrace();
		}
		return conexao;
	}

	/**
	 * Método responsável por fechar a conexão com o banco, com a consulta e o resultado da consulta
	 * 
	 * @param conexao
	 * @param pstmt
	 * @param rs
	 */
	public void fechaConexao(Connection conexao, PreparedStatement pstmt,
			ResultSet rs) {
		try {
			if (conexao != null) {
				conexao.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			System.out.println("Erro ao fechar conexão com o banco");// + URL);
		}
	}
}