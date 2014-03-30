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
  	
	<title>SVBE - Selecionar Políticos</title>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand">Sistema de Voto Binário Eletrônico</a>
				<ul class="nav navbar-nav">
          			<li><a href="index.jsp">Home</a></li>
      	  			<li class="active"><a href="cadastro-sessao.jsp">Cadastro de Sessão</a></li>
      	  			<li><a href="sessoes-cadastradas.jsp">Sessões Cadastradas</a></li>
      	  		</ul>
			</div>
		</div>
	</div>
	
	<div class="container">
		<form class="form-horizontal" method="post" action="selecionar-politicos">
		<h3 class="form-cadastro-head">Selecionar Políticos</h3>
		
		<div class="panel panel-primary">
  			<div class="panel-heading">Políticos</div>
  				<jsp:useBean id="politicoController" class="br.com.svbe.web.controller.PoliticoController"/>
				<table class="table table-bordered">
					<thead>
						<tr class="danger">
							<th>Seleção</th>
							<th>Nome</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="politico" items="${politicoController.politicos}">
								<tr class="success">
									<td><input type="checkbox" name="selecao" value="${politico.imei}"></td>
									<td>${politico.nome}</td>
								</tr>
          				</c:forEach>
					</tbody>
				</table>
		</div>
		
		<div class="form-group">
      		<div class="col-sm-12"></div>
      			<div class="col-sm-1">
  					<button type="submit" class="btn btn-primary">Selecionar</button>
				</div>
  		</div>
  		</form>
	</div>
</body>
</html>