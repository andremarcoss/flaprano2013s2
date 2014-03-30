<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  	<link  type="text/css" href="css/bootstrap.css" rel="stylesheet" />
 	<link type="text/css" href="css/cadastro-sessao.css" rel="stylesheet" />
 	<script src="js/bootstrap.js"></script>
  	
	<title>SVBE - Consulta de Sessões</title>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand">Sistema de Voto Binário Eletrônico</a>
				<ul class="nav navbar-nav">
          			<li><a href="index.jsp">Home</a></li>
      	  			<li><a href="cadastro-sessao.jsp">Cadastro de Sessão</a></li>
      	  			<li class="active"><a href="sessoes-cadastradas.jsp">Sessões Cadastradas</a></li>
      	  		</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<h3 class="form-cadastro-head">Sessões Cadastradas</h3>
		
		<div class="panel panel-primary">
  			<div class="panel-heading">Sessões</div>
  				<jsp:useBean id="sessaoController" class="br.com.svbe.web.controller.SessaoController"/>
				<table class="table table-bordered">
					<thead>
						<tr class="danger">
							<th>Nome da Sessão</th>
							<th>Descrição da Sessão</th>
							<th>Data de Ínicio</th>
							<th>Qtde. de Votos Positivos</th>
							<th>Qtde. de Votos Negativos</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sessao" items="${sessaoController.sessoes}">
								<tr class="success">
									<td>${sessao.nome}</td>
									<td>${sessao.descricao}</td>
									<td>${sessao.dataInicial}</td>
									<td>${sessao.qtdVotosPos}</td>
									<td>${sessao.qtdVotosNeg}</td>
								</tr>
          				</c:forEach>
					</tbody>
				</table>
		</div>
	</div>
	
</body>
</html>