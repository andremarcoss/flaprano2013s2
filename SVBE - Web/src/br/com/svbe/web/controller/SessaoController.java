package br.com.svbe.web.controller;

import java.util.List;

import br.com.svbe.web.dao.SessaoDAO;
import br.com.svbe.web.model.Politico;
import br.com.svbe.web.model.Sessao;

/**
 * Classe respons�vel por ser o controlador das chamadas aos m�todos da camada DAO (SessaoDAO)
 * 
 * @author Felipe Laprano
 * 
 */
public class SessaoController {
	
	/**
	 * M�todo respons�vel por controlar o uso do m�todo "insereSessao"
	 * 
	 * @param sessao
	 * @param lista
	 */
	public void setSessao(Sessao sessao, List<Politico> lista) {
		SessaoDAO.getInstance().insereSessao(sessao, lista);
	}
	
	/**
	 * M�todo respons�vel por controlar o uso do m�todo "listaSessoes"
	 * 
	 * @return
	 */
	public List<Sessao> getSessoes() {
		return SessaoDAO.getInstance().listaSessoes();
	}
}
