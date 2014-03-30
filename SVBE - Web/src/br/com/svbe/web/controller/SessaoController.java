package br.com.svbe.web.controller;

import java.util.List;

import br.com.svbe.web.dao.SessaoDAO;
import br.com.svbe.web.model.Politico;
import br.com.svbe.web.model.Sessao;

/**
 * Classe responsável por ser o controlador das chamadas aos métodos da camada DAO (SessaoDAO)
 * 
 * @author Felipe Laprano
 * 
 */
public class SessaoController {
	
	/**
	 * Método responsável por controlar o uso do método "insereSessao"
	 * 
	 * @param sessao
	 * @param lista
	 */
	public void setSessao(Sessao sessao, List<Politico> lista) {
		SessaoDAO.getInstance().insereSessao(sessao, lista);
	}
	
	/**
	 * Método responsável por controlar o uso do método "listaSessoes"
	 * 
	 * @return
	 */
	public List<Sessao> getSessoes() {
		return SessaoDAO.getInstance().listaSessoes();
	}
}
