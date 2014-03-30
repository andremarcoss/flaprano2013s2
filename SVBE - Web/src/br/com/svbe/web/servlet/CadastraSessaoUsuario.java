package br.com.svbe.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.svbe.web.controller.SessaoController;
import br.com.svbe.web.model.Politico;
import br.com.svbe.web.model.Sessao;

/**
 * Classe Servlet respons�vel pela a��o de cadastrar uma Sess�o para usu�rios espec�ficos
 * 
 * @author FLAPRANO
 *
 */
@SuppressWarnings("serial")
public class CadastraSessaoUsuario extends HttpServlet {

	/**
	 * M�todo respons�vel por executar a requisi��o de POST no momento do cadastro de uma Sess�o para determinados usu�rios
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] lista = request.getParameterValues("selecao");
		List<Politico> listaPoliticos = new ArrayList<Politico>();
		
		for (String id : lista) {
			Politico politico = new Politico();
			politico.setImei(id);
			listaPoliticos.add(politico);
		}
		
		Sessao sessao = (Sessao) request.getSession().getAttribute("sessao");
		
		SessaoController sessaoController = new SessaoController();
		sessaoController.setSessao(sessao, listaPoliticos);
		
		response.sendRedirect("/svbe/cadastro-sessao.jsp");
		
		request.getSession().invalidate();
	}

}
