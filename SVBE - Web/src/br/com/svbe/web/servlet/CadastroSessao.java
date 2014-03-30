package br.com.svbe.web.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.svbe.web.controller.PoliticoController;
import br.com.svbe.web.controller.SessaoController;
import br.com.svbe.web.model.Politico;
import br.com.svbe.web.model.Sessao;

/**
 * Classe Servlet responsável pela ação de cadastrar uma Sessão para todos os usuários
 * 
 * @author Felipe Laprano
 *
 */
@SuppressWarnings("serial")
public class CadastroSessao extends HttpServlet {

	/**
	 * Método responsável por executar a requisição de POST no momento do cadastro de uma Sessão para todos os usuários
	 * 
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Sessao sessao = new Sessao();
		
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString();

		sessao.setIdSessao(id);
		sessao.setNome(request.getParameter("nomeSessao"));
		sessao.setDescricao(request.getParameter("descricaoSessao"));
		sessao.setTipo(request.getParameter("opcao"));
		
		if (request.getParameter("opcao").equals("E")) {
			if (request.getParameter("opcaoEscolha1").equals(""))
				sessao.setOpcao1("ABC");
			else
				sessao.setOpcao1(request.getParameter("opcaoEscolha1"));
			if (request.getParameter("opcaoEscolha2").equals(""))
				sessao.setOpcao2("XYZ");
			else 
				sessao.setOpcao2(request.getParameter("opcaoEscolha2"));
		}
		else {
			sessao.setOpcao1("");
			sessao.setOpcao2("");
		}
		sessao.setParticipacao(request.getParameter("participacao"));
		sessao.setDataInicial(new java.sql.Date(Calendar.getInstance().getTimeInMillis()).toString());

		if (request.getParameter("dataFinal").equals("")) {
			sessao.setDataFinal("");
		} else {
			sessao.setDataFinal(Date.valueOf(request.getParameter("dataFinal")).toString());
		}

		if (request.getParameter("limitado").equals("")) {
			sessao.setControle("N");
			sessao.setQtdMax(0);
		} else {
			sessao.setControle("S");
			sessao.setQtdMax(Integer.parseInt(request.getParameter("qtdMaxVotos")));
		}
		sessao.setQtdVotosPos(0);
		sessao.setQtdVotosNeg(0);
		
		sessao.setEstado("I");

		try {
			if (sessao.getParticipacao().equals("A")) {
				SessaoController sessaoController = new SessaoController();
				PoliticoController politicoController = new PoliticoController();
				List<Politico> lista = new ArrayList<Politico>();
				lista = politicoController.getPoliticos();
				sessaoController.setSessao(sessao,lista);
				response.sendRedirect("/svbe/cadastro-sessao.jsp");
			}
			else {
				request.getSession().setAttribute("sessao", sessao);
				response.sendRedirect("/svbe/selecionar-politicos.jsp");
			}
		} catch (Exception ex) {
		}
	}

}
