package br.com.svbe.resource.controller;

import java.util.List;

import br.com.svbe.resource.dao.SessaoDAO;
import br.com.svbe.resource.model.Sessao;

/**
 * Classe respons�vel por ser o controlador das chamadas aos m�todos da camada DAO (SessaoDAO)
 * 
 * @author Felipe Laprano
 * 
 */
public class SessaoController {

	/**
	 * M�todo respons�vel por controlar o uso do m�todo "listaSessoes"
	 * 
	 * @param idPolitico
	 * @return
	 */
	public List<Sessao> getSessoes(String idPolitico) {
		return SessaoDAO.getInstance().listaSessoes(idPolitico);
	}

	/**
	 *  M�todo respons�vel por controlar o uso do m�todo "atualizaSessao"
	 * 
	 * @param idSessao
	 * @param idPolitico
	 * @param resposta
	 */
	public void setVoto(String idSessao, String idPolitico, int resposta) {
		SessaoDAO.getInstance().atualizaSessao(idSessao, idPolitico, resposta);
	}

}
