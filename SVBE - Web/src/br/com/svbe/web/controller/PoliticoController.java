package br.com.svbe.web.controller;

import java.util.List;

import br.com.svbe.web.dao.PoliticoDAO;
import br.com.svbe.web.model.Politico;

/**
 * Classe respons�vel por ser o controlador das chamadas aos m�todos da camada DAO (PoliticoDAO)
 * 
 * @author Felipe Laprano
 * 
 */
public class PoliticoController {

	/**
	 * M�todo respons�vel  por controlar o uso do m�todo "listaPoliticos"
	 * 
	 * @return
	 */
 	public List<Politico> getPoliticos() {
		return PoliticoDAO.getInstance().listaPoliticos();
	}

}
