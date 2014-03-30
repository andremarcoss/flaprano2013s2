package br.com.svbe.resource.controller;

import br.com.svbe.resource.dao.PoliticoDAO;
import br.com.svbe.resource.model.Politico;

/**
 * Classe respons�vel por ser o controlador das chamadas aos m�todos da camada DAO (PoliticoDAO)
 * 
 * @author Felipe Laprano
 * 
 */
public class PoliticoController {

	/**
	 * M�todo respons�vel por controlar o uso do m�todo "consultaPolitico"
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
	 * M�todo respons�vel por controlar o uso do m�todo "consultaPoliticoById"
	 * 
	 * @param imei
	 * @return
	 */
	public boolean getPoliticoById(String imei) {
		return PoliticoDAO.getInstance().consultaPoliticoById(imei);
	}

	/**
	 *  M�todo respons�vel por controlar o uso do m�todo "setPolitico"
	 * 
	 * @param politico
	 * @return
	 */
	public int setPolitico(Politico politico) {
		return PoliticoDAO.getInstance().inserePolitico(politico);
	}
}
