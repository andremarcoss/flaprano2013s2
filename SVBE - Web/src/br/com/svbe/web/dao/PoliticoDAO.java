package br.com.svbe.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.svbe.web.factory.ConnectionFactory;
import br.com.svbe.web.model.Politico;

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
	 * Método responsável por consultar um determinado político através do imei do aparelho
	 * 
	 * @param imei
	 * @return politico
	 */
	public List<Politico> listaPoliticos() {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Politico> politicos = null;

		conexao = abreConexao();
		politicos = new ArrayList<Politico>();
		
		String sqlScript;
		sqlScript = "SELECT * FROM `Politico` ORDER BY `Usuario`;";

		try {
			pstmt = conexao.prepareStatement(sqlScript);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Politico politico = new Politico();
				politico.setImei(rs.getString("ID"));
				politico.setNome(rs.getString("Usuario"));
				politico.setLogin(rs.getString("Login"));
				politico.setSenha(rs.getString("Senha"));
				
				politicos.add(politico);
			}
		} catch (Exception exception) {
			System.out.println("Erro ao consultar o politico: " + exception);
			exception.printStackTrace();
		} finally {
			fechaConexao(conexao, pstmt, rs);
		}
		return politicos;
	}
	
}