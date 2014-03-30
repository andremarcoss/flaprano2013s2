<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/erro404.css" rel="stylesheet">
    <title>SVBE - Página não encontrada - Erro</title>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand">Sistema de Voto Binário Eletrônico</a>
				<ul class="nav navbar-nav">
          			<li><a href="index.jsp">Home</a></li>
      	  			<li><a href="cadastro-sessao.jsp">Cadastro de Sessão</a></li>
      	  			<li><a href="sessoes-cadastradas.jsp">Sessões Cadastradas</a></li>
      	  		</ul>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-9">
				<div class="jumbotron">
					<h1>Ocorreu um erro</h1>
					<p>Ocorreu um erro na sua requisição.<br/>
					Volte e tente novamente.</p>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container">
		 <a class="btn btn-lg btn-default" onclick="history.go(-1)">Voltar</a>
	</div>
</body>
</html>