package br.com.svbe.web.controller;

import java.util.List;

import br.com.svbe.web.dao.PoliticoDAO;
import br.com.svbe.web.model.Politico;

/**
 * Classe responsável por ser o controlador das chamadas aos métodos da camada DAO (PoliticoDAO)
 * 
 * @author Felipe Laprano
 * 
 */
public class PoliticoController {

	/**
	 * Método responsável  por controlar o uso do método "listaPoliticos"
	 * 
	 * @return
	 */
 	public List<Politico> getPoliticos() {
		return PoliticoDAO.getInstance().listaPoliticos();
	}

}
