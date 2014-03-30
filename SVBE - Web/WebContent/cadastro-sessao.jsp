<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link  type="text/css" href="css/bootstrap.css" rel="stylesheet" />
  <link type="text/css" href="css/cadastro-sessao.css" rel="stylesheet" />
  <script src="js/bootstrap.js"></script>

<title>SVBE - Cadastro de Sess�o</title>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand">Sistema de Voto Bin�rio Eletr�nico</a>
				<ul class="nav navbar-nav">
          			<li><a href="index.jsp">Home</a></li>
      	  			<li class="active"><a href="cadastro-sessao.jsp">Cadastro de Sess�o</a></li>
      	  			<li><a href="sessoes-cadastradas.jsp">Sess�es Cadastradas</a></li>
      	  		</ul>
			</div>
		</div>
	</div>
	
	<div class="container">
		<form class="form-horizontal" method="post" action="cadastrar-sessao">
		
			<h3 class="form-cadastro-head">Cadastro de Sess�o</h3>
			
			<div class="form-group">
      			<label for="inputNomeSessao" class="col-sm-2 control-label">Nome</label>
      			<div class="col-sm-10">
      				<input type="text" class="form-control" id="inputNomeSessao" placeholder="Nome da Sess�o" name="nomeSessao" required></input>
      			</div>
      		</div>
      		
      		<div class="form-group">
      			<label for="inputDescricaoSessao" class="col-sm-2 control-label">Descri��o</label>
      				<div class="col-sm-10">
      					<textarea class="form-control" id="inputDescricaoSessao" placeholder="Descri��o da Sess�o" name="descricaoSessao" maxlength="250" required></textarea>
      				</div>
      		</div>
      		
      		<div class="form-group">
      			<label class="col-sm-2 control-label">Op��o</label>
      			<div class="col-sm-10">
      				<label class="radio"> <input type="radio" name="opcao" id="aprovacao" value="A" checked="checked"></input> Aprova��o </label>
					<label class="radio"> <input type="radio" name="opcao" id="deliberacao" value="D"></input> Delibera��o </label>
        			<label class="radio"> <input type="radio" name="opcao" id="sim-nao" value="C"></input> Confirma��o (Sim / N�o) </label>
        			<label class="radio"> <input type="radio" name="opcao" id="alfanumerico" value="E"></input> Alfanum�rico </label>
					<div class="col-sm-10">
  						<div class="col-sm-3">
    						<input type="text" class="form-control" id="center" placeholder="ABC" name="opcaoEscolha1" maxlength="3"></input>
  						</div>
  						<div class="col-sm-1">
    						<label class="control-label" id="center" >ou</label>
  						</div>
  						<div class="col-sm-3">
    						<input type="text" class="form-control" id="center" placeholder="XYZ" name="opcaoEscolha2" maxlength="3"></input>
  						</div>
					</div>
           		</div>
           	</div>
           	
           	<div class="form-group">
      			<label class="col-sm-2 control-label">Participa��o</label>
      			<div class="col-sm-10">
      				<label class="radio"> <input type="radio" name="participacao" id="aberta" value="A" checked="checked"></input> Aberta </label>
					<label class="radio"> <input type="radio" name="participacao" id="fechada" value="F"></input> Fechada </label>
           		</div>
           	</div>
           	
            <div class="form-group">
      			<label class="col-sm-2 control-label">Per�odo</label>
					<div class="col-sm-10">
  						<div class="col-sm-3">
    						<input type="date" class="form-control" name="dataFinal" id="datepicker"></input>
  						</div>
  					</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="col-sm-2 control-label">Limitado</label>
					<div class="col-sm-10">
  						<div class="col-sm-7">
  							<select class="form-control" style="width: auto" name="limitado">
  								<option></option>
  								<option>Finaliza antecipadamente ao atingir o limite</option>
							</select>
  						</div>
  						<div class="col-sm-3">
    						<input type="number" class="form-control" name="qtdMaxVotos"></input>
  						</div>
  					</div>
  			</div>
  			
  			<div class="form-group">
  				<div class="col-sm-2"></div>
      			<div class="col-sm-10">
  					<button type="reset" class="btn btn-primary">Limpar</button>
  					<button type="submit" class="btn btn-primary">Cadastrar</button>
				</div>
  			</div>
  			
		</form>
	</div>
</body>
</html>