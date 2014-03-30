package br.com.svbe.web.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.svbe.web.factory.ConnectionFactory;
import br.com.svbe.web.model.Politico;
import br.com.svbe.web.model.Sessao;

/**
 * Classe responsável por conter os métodos de CRUD para o objeto Sessão
 * 
 * @author Felipe Laprano
 * 
 */
public class SessaoDAO extends ConnectionFactory {
	private static SessaoDAO instance;

	/**
	 * Método responsável por criar uma instância da classe SessaoDAO ( Padrão Singleton )
	 * 
	 * @return instance
	 */
	public static SessaoDAO getInstance() {
		if (instance == null) {
			instance = new SessaoDAO();
		}
		return instance;
	}

	/**
	 * Método responsável por inserir uma Sessão no banco de dados
	 * 
	 * @param sessao
	 */
	public void insereSessao(Sessao sessao, List<Politico> lista) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sqlScript;
		sqlScript = "INSERT INTO `Sessao`(`ID`,`Nome`,`Descricao`,`Tipo`,`Opcao1`,`Opcao2`,`Participacao`,`Data_Inicial`,`Data_Final`,`Controle`,`Qtd_Max`,`Qtd_VotosPos`,`Qtd_VotosNeg`,`Estado`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

		conexao = abreConexao();

		try {
			pstmt = conexao.prepareStatement(sqlScript);

			pstmt.setString(1, sessao.getIdSessao());
			pstmt.setString(2, sessao.getNome());
			pstmt.setString(3, sessao.getDescricao());
			pstmt.setString(4, sessao.getTipo());
			pstmt.setString(5, sessao.getOpcao1());
			pstmt.setString(6, sessao.getOpcao2());
			pstmt.setString(7, sessao.getParticipacao());
			pstmt.setString(8, sessao.getDataInicial());
			pstmt.setString(9, sessao.getDataFinal());
			pstmt.setString(10, sessao.getControle());
			pstmt.setInt(11, sessao.getQtdMax());
			pstmt.setInt(12, sessao.getQtdVotosPos());
			pstmt.setInt(13, sessao.getQtdVotosNeg());
			pstmt.setString(14, sessao.getEstado());

			pstmt.execute();
			
			pstmt.close();
			String sqlScriptVoto;
			for (Politico politico : lista) {
				sqlScriptVoto = "INSERT INTO `Voto`(`ID_Sessao`,`ID_Politico`,`Resposta`,`Estado`) VALUES (?,?,NULL,'N');";
				pstmt = conexao.prepareStatement(sqlScriptVoto);
				pstmt.setString(1,sessao.getIdSessao());
				pstmt.setString(2, politico.getImei());
				
				pstmt.execute();
				
				pstmt.close();
			}

		} catch (Exception exception) {
			System.out.println("Erro ao inserir a sessão: " + exception);
			exception.printStackTrace();
		} finally {
			fechaConexao(conexao, pstmt, rs);
		}
	}
	
	/**
	 * Método responsável por retornar uma lista com as Sessões cadastradas no banco de dados
	 * 
	 * @return
	 */
	public List<Sessao> listaSessoes() {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Sessao> sessoes = null;

		conexao = abreConexao();
		sessoes = new ArrayList<Sessao>();

		String sqlScript;
		sqlScript = "SELECT * FROM `Sessao` ORDER BY `Nome`;";
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Date dataInicial;
		Date dataFinal;

		try {
			pstmt = conexao.prepareStatement(sqlScript);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Sessao sessao = new Sessao();
				sessao.setIdSessao(rs.getString("ID"));
				sessao.setNome(rs.getString("Nome"));
				sessao.setDescricao(rs.getString("Descricao"));
				sessao.setTipo(rs.getString("Tipo"));
				sessao.setOpcao1(rs.getString("Opcao1"));
				sessao.setOpcao2(rs.getString("Opcao2"));
				sessao.setParticipacao(rs.getString("Participacao"));
				
				dataInicial = Date.valueOf(rs.getString("Data_Inicial"));
				sessao.setDataInicial(formatoData.format(dataInicial));
				if (rs.getString("Data_Final").equals(""))
					sessao.setDataFinal(rs.getString("Data_Final"));
				else {
					dataFinal = Date.valueOf(rs.getString("Data_Final"));
					sessao.setDataFinal(formatoData.format(dataFinal));
				}
				
				sessao.setControle(rs.getString("Controle"));
				sessao.setQtdMax(rs.getInt("Qtd_Max"));
				sessao.setQtdVotosPos(rs.getInt("Qtd_VotosPos"));
				sessao.setQtdVotosNeg(rs.getInt("Qtd_VotosNeg"));
				sessao.setEstado("I");

				sessoes.add(sessao);
			}
		} catch (Exception exception) {
			System.out.println("Erro ao listar as sessões: " + exception);
			exception.printStackTrace();
		} finally {
			fechaConexao(conexao, pstmt, rs);
		}
		return sessoes;
	}

}
