package br.com.svbe.resource.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.svbe.resource.factory.ConnectionFactory;
import br.com.svbe.resource.model.Sessao;

/**
 * Classe respons�vel por conter os m�todos de CRUD para o objeto Sess�o
 * 
 * @author Felipe Laprano
 * 
 */
public class SessaoDAO extends ConnectionFactory {
	private static SessaoDAO instance;

	/**
	 * M�todo respons�vel por criar uma inst�ncia da classe SessaoDAO ( Padr�o Singleton )
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
	 * 
	 * M�todo respons�vel por consultar as sess�es em aberto para um determinado usu�rio
	 * 
	 * @param idPolitico
	 * @return List<Sessao> sessoes
	 */
	public List<Sessao> listaSessoes(String idPolitico) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlScript = null;

		List<Sessao> sessoes = null;
		List<String> listaSessoesCadastradas = new ArrayList<String>();
		List<String> listaSessoesUsuario = new ArrayList<String>();

		conexao = abreConexao();
		sessoes = new ArrayList<Sessao>();

		//Seleciona as sess�es cadastradas para um determinado usu�rio
		sqlScript = "SELECT `ID_Sessao` FROM `Voto` WHERE (`ID_Politico` = ?)";

		try {
			pstmt = conexao.prepareStatement(sqlScript);
			pstmt.setString(1, idPolitico);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listaSessoesUsuario.add(rs.getString("ID_Sessao"));
			}

		} catch (Exception exception) {
			System.out.println("Erro: " + exception);
			exception.printStackTrace();
		} finally {
			fechaConexao(conexao, pstmt, rs);
		}
		
		conexao = abreConexao();
		
		//Seleciona as sess�es cadastradas com participa��o = A e estado = I
		sqlScript = "SELECT `ID` FROM `Sessao` WHERE (`Participacao` = 'A' AND `Estado` = 'I')";

		try {
			pstmt = conexao.prepareStatement(sqlScript);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listaSessoesCadastradas.add(rs.getString("ID"));
			}

		} catch (Exception exception) {
			System.out.println("Erro: " + exception);
			exception.printStackTrace();
		} finally {
			fechaConexao(conexao, pstmt, rs);
		}

		//Insere as sess�es com participa��o = A e estado = I para um determinado usu�rio, desde que j� n�o estejam cadastradas
		conexao = abreConexao();
		String sqlScriptVoto = null;
		for (String id : listaSessoesCadastradas) {
			if (!listaSessoesUsuario.contains(id)) {
				sqlScriptVoto = "INSERT INTO `Voto`(`ID_Sessao`,`ID_Politico`,`Resposta`,`Estado`) VALUES (?,?,NULL,'N');";
				try {
					pstmt = conexao.prepareStatement(sqlScriptVoto);
					pstmt.setString(1, id);
					pstmt.setString(2, idPolitico);

					pstmt.execute();

					pstmt.close();
				} catch (SQLException exception) {
					System.out.println("Erro: " + exception);
					exception.printStackTrace();
				}
			}
		}

		//Seleciona todas as sess�es com voto em aberto para o usu�rio
		sqlScript = "SELECT sessao.* FROM `Sessao` as sessao INNER JOIN `Voto` as voto ON sessao.ID = voto.ID_Sessao WHERE (voto.ID_Politico = ? AND sessao.Estado = 'I' AND voto.Estado = 'N') ORDER by sessao.Nome;";
		try {
			pstmt = conexao.prepareStatement(sqlScript);
			pstmt.setString(1, idPolitico);
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
				sessao.setDataInicial(rs.getString("Data_Inicial"));
				sessao.setDataFinal(rs.getString("Data_Final"));
				sessao.setControle(rs.getString("Controle"));
				sessao.setQtdMax(rs.getInt("Qtd_Max"));
				sessao.setQtdVotosPos(rs.getInt("Qtd_VotosPos"));
				sessao.setQtdVotosNeg(rs.getInt("Qtd_VotosNeg"));
				sessao.setEstado("I");

				sessoes.add(sessao);
			}
		} catch (Exception exception) {
			System.out.println("Erro ao listar as sess�es: " + exception);
			exception.printStackTrace();
		} finally {
			fechaConexao(conexao, pstmt, rs);
		}
		return sessoes;
	}

	/**
	 * 
	 * M�todo respons�vel por atualizar a resposta de um pol�tico a uma determinada sess�o
	 * 
	 * @param idSessao
	 * @param idPolitico
	 * @param resposta
	 */
	public void atualizaSessao(String idSessao, String idPolitico, int resposta) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sqlScript1;
		sqlScript1 = "UPDATE `Voto` SET `Resposta` = ?, `Estado` = 'R' WHERE (`ID_Sessao` = ? AND `ID_Politico` = ?);";

		String sqlScript2;
		if (resposta == 1) {
			sqlScript2 = "UPDATE `Sessao` SET `Qtd_VotosPos` = `Qtd_VotosPos` + 1 WHERE (`ID` = ?);";
		} else {
			sqlScript2 = "UPDATE `Sessao` SET `Qtd_VotosNeg` = `Qtd_VotosNeg` + 1 WHERE (`ID` = ?);";
		}

		conexao = abreConexao();

		try {
			pstmt = conexao.prepareStatement(sqlScript1);

			pstmt.setInt(1, resposta);
			pstmt.setString(2, idSessao);
			pstmt.setString(3, idPolitico);

			pstmt.execute();

			pstmt.close();

			pstmt = conexao.prepareStatement(sqlScript2);
			pstmt.setString(1, idSessao);

			pstmt.execute();

		} catch (Exception exception) {
			System.out.println("Erro ao atualizar a resposta do pol�tico: "
					+ exception);
			exception.printStackTrace();
		} finally {
			fechaConexao(conexao, pstmt, rs);
		}
	}

}
