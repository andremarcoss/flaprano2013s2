package br.com.svbe.resource.controller;

import java.util.List;

import br.com.svbe.resource.dao.SessaoDAO;
import br.com.svbe.resource.model.Sessao;

/**
 * Classe responsável por ser o controlador das chamadas aos métodos da camada DAO (SessaoDAO)
 * 
 * @author Felipe Laprano
 * 
 */
public class SessaoController {

	/**
	 * Método responsável por controlar o uso do método "listaSessoes"
	 * 
	 * @param idPolitico
	 * @return
	 */
	public List<Sessao> getSessoes(String idPolitico) {
		return SessaoDAO.getInstance().listaSessoes(idPolitico);
	}

	/**
	 *  Método responsável por controlar o uso do método "atualizaSessao"
	 * 
	 * @param idSessao
	 * @param idPolitico
	 * @param resposta
	 */
	public void setVoto(String idSessao, String idPolitico, int resposta) {
		SessaoDAO.getInstance().atualizaSessao(idSessao, idPolitico, resposta);
	}

}
