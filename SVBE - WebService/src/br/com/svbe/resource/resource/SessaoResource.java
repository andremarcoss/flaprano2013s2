package br.com.svbe.resource.resource;

import javax.servlet.ServletException;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.svbe.resource.controller.SessaoController;

import com.google.gson.Gson;

/**
 * Classe responsável por conter os métodos REST de acesso ao webservice
 * 
 * @author Felipe Laprano
 * 
 */
@Path("/sessao")
public class SessaoResource {

	/**
	 * Método responsável por fazer a chamada ao controller e buscar as sessões
	 * 
	 * @param idPolitico
	 * @return List<Sessao> sessoes
	 * @throws ServletException
	 */
	@GET
	@Path("/getSessoes")
	@Produces("application/json")
	public String getSessoes(@QueryParam("idPolitico") String idPolitico) {
		return new Gson().toJson(new SessaoController().getSessoes(idPolitico));
	}

	/**
	 * Método responsável por fazer a chamada ao controller e setar o voto do político
	 * 
	 * @param idSessao
	 * @param idPolitico
	 * @param resposta
	 */
	@PUT
	@Path("/setVoto")
	public void setVoto(@QueryParam("idSessao") String idSessao,
			@QueryParam("idPolitico") String idPolitico,
			@QueryParam("resposta") int resposta) {
		new SessaoController().setVoto(idSessao, idPolitico, resposta);
		;
	}

}
