package br.com.svbe.resource.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Classe respons�vel por criar e fechar a conex�o com o banco de dados
 * 
 * @author Felipe Laprano
 * 
 */
public class ConnectionFactory {

	//Defini��o das propriedades de conex�o ao banco, usadas na conex�o com localhost
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/";
	private static final String USUARIO = "root";
	private static final String SENHA = "root";
	private static final String BANCO = "SVBE";

	/**
	 * M�todo respons�vel por criar a conex�o com o banco
	 * 
	 * @return conexao
	 */
	public Connection abreConexao() {
		Connection conexao = null;

		try {
			
			//Defini��o de conex�o do banco de dados para conex�o e testes com localhost
			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(URL + BANCO, USUARIO, SENHA);

			/*
			 * Defini��o das propriedades de conex�o ao banco, usadas na conex�o com o Jelastic
			 * Carrega-se as propriedades da conex�o atrav�s do arquivo "mydb.cfg" que est� no Jelastic
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
			System.out.println("Erro ao criar conex�o com o banco");// + URL);
			e.printStackTrace();
		}
		return conexao;
	}

	/**
	 * M�todo respons�vel por fechar a conex�o com o banco, com a consulta e o resultado da consulta
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
			System.out.println("Erro ao fechar conex�o com o banco");// + URL);
		}
	}
}