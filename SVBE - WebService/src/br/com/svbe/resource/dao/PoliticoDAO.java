package br.com.svbe.resource.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.svbe.resource.factory.ConnectionFactory;
import br.com.svbe.resource.model.Politico;

/**
 * Classe responsável por conter os métodos de CRUD para o objeto Politico
 * 
 * @author Felipe Laprano
 * 
 */
public class PoliticoDAO extends ConnectionFactory {
	private static PoliticoDAO instance;

	/**
	 * Método responsável por criar uma instância da classe PoliticoDAO ( Padrão Singleton )
	 * 
	 * @return instance
	 */
	public static PoliticoDAO getInstance() {
		if (instance == null) {
			instance = new PoliticoDAO();
		}
		return instance;
	}
	
	/**
	 * Método para inserir um objeto Politico no banco
	 * 
	 * @param politico
	 */
	public int inserePolitico(Politico politico) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conexao = abreConexao();

		String sqlScript;
		sqlScript = "INSERT INTO `Politico` (`ID`,`Usuario`,`Login`,`Senha`) VALUES (?,?,?,?);";
		
		try {
			pstmt = conexao.prepareStatement(sqlScript);
			pstmt.setString(1, politico.getImei());
			pstmt.setString(2, politico.getNome());
			pstmt.setString(3, politico.getLogin());
			pstmt.setString(4, politico.getSenha());
			pstmt.execute();
			return 1;
		} catch (Exception exception) {
			System.out.println("Erro ao inserir o politico: " + exception);
			exception.printStackTrace();
			return 0;
		} finally {
			fechaConexao(conexao, pstmt, rs);
		}
	}
	
	/**
	 * Método responsável por consultar um determinado politico através do imei do aparelho e usuario e senha cadastrados 
	 * 
	 * @param imei
	 * @param login
	 * @param senha
	 * 
	 * @return boolean
	 */
	public boolean consultaPolitico(String imei, String login, String senha) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conexao = abreConexao();

		String sqlScript;
		sqlScript = "SELECT * FROM `Politico` WHERE (`ID` = ? AND `Login` = ? AND `Senha` = ?);";

		try {
			pstmt = conexao.prepareStatement(sqlScript);
			pstmt.setString(1, imei);
			pstmt.setString(2, login);
			pstmt.setString(3, senha);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (Exception exception) {
			System.out.println("Erro ao consultar o politico: " + exception);
			exception.printStackTrace();
		} finally {
			fechaConexao(conexao, pstmt, rs);
		}
		return false;
	}
	
	/**
	 * Método responsável por consultar um determinado politico através do imei do aparelho
	 * 
	 * @param imei
	 * 
	 * @return boolean
	 */
	public boolean consultaPoliticoById(String imei) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conexao = abreConexao();

		String sqlScript;
		sqlScript = "SELECT * FROM `Politico` WHERE (`ID` = ?);";

		try {
			pstmt = conexao.prepareStatement(sqlScript);
			pstmt.setString(1, imei);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (Exception exception) {
			System.out.println("Erro ao consultar o politico: " + exception);
			exception.printStackTrace();
		} finally {
			fechaConexao(conexao, pstmt, rs);
		}
		return false;
	}
	
}