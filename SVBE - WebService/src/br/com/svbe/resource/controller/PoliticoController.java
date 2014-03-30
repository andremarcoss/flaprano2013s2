package br.com.svbe.resource.controller;

import br.com.svbe.resource.dao.PoliticoDAO;
import br.com.svbe.resource.model.Politico;

/**
 * Classe responsável por ser o controlador das chamadas aos métodos da camada DAO (PoliticoDAO)
 * 
 * @author Felipe Laprano
 * 
 */
public class PoliticoController {

	/**
	 * Método responsável por controlar o uso do método "consultaPolitico"
	 * 
	 * @param imei
	 * @param login
	 * @param senha
	 * @return
	 */
	public boolean getPolitico(String imei, String login, String senha) {
		return PoliticoDAO.getInstance().consultaPolitico(imei, login, senha);
	}
	
	/**
	 * Método responsável por controlar o uso do método "consultaPoliticoById"
	 * 
	 * @param imei
	 * @return
	 */
	public boolean getPoliticoById(String imei) {
		return PoliticoDAO.getInstance().consultaPoliticoById(imei);
	}

	/**
	 *  Método responsável por controlar o uso do método "setPolitico"
	 * 
	 * @param politico
	 * @return
	 */
	public int setPolitico(Politico politico) {
		return PoliticoDAO.getInstance().inserePolitico(politico);
	}
}
