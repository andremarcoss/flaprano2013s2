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
 * Classe Servlet responsável pela ação de cadastrar uma Sessão para usuários específicos
 * 
 * @author FLAPRANO
 *
 */
@SuppressWarnings("serial")
public class CadastraSessaoUsuario extends HttpServlet {

	/**
	 * Método responsável por executar a requisição de POST no momento do cadastro de uma Sessão para determinados usuários
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
