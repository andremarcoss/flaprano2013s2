package br.com.svbe.resource.resource;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.svbe.resource.controller.PoliticoController;
import br.com.svbe.resource.model.Politico;

import com.google.gson.Gson;

/**
 * Classe responsável por conter os métodos REST de acesso ao webservice
 * 
 * @author Felipe Laprano
 *
 */
@Path("/politico")
public class PoliticoResource {
	
	/**
	 * Método responsável por fazer a chamada ao controller e buscar um político pelo imei do aparelho e usuario e senha cadastrados 
	 * 
	 * @param imei
	 * @param login
	 * @param senha
	 * 
	 * @return String
	 */
	@GET
	@Path("/getPolitico")
	@Produces("application/json")
	public String getPolitico(@QueryParam("imei") String imei, @QueryParam("login") String login, @QueryParam("senha") String senha) {
		return new Gson().toJson(new PoliticoController().getPolitico(imei, login, senha));
	}
	
	/**
	 * Método responsável por fazer a chamada ao controller e buscar um político pelo imei
	 * 
	 * @param imei
	 * @return String
	 */
	@GET
	@Path("/getPoliticoById")
	@Produces("application/json")
	public String getPoliticoById(@QueryParam("imei") String imei) {
		return new Gson().toJson(new PoliticoController().getPoliticoById(imei));
	}
	
	/**
	 * Método responsável por fazer a chamada ao controller e cadastrar um político no banco
	 * 
	 * @param politico
	 * @return mensagem
	 */
	@POST
	@Path("/setPolitico")
	public String setPolitico(@HeaderParam("imei") String imei, @HeaderParam("nome") String nome, @HeaderParam("login") String login, @HeaderParam("senha") String senha) {
		Politico politico = new Politico();
		politico.setImei(imei);
		politico.setNome(nome);
		politico.setLogin(login);
		politico.setSenha(senha);
		
		return new Gson().toJson(new PoliticoController().setPolitico(politico));
	}
}
