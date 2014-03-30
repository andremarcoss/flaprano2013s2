package br.com.svbe.android.task;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Classe responsável pela tarefa de setar o voto de uma determinada no banco de dados (através do uso do webservice)
 * 
 * String = tipo que passado para o método doInBackground(String… parametros)
 * Void = tipo que será passado para o método onProgressUpdate(Void… parametros)
 * Boolean tipo do retorno do método doInBackground() e tipo que será passado para o método onPostExecute(Boolean resultado)
 * 
 * @author Felipe Laprano
 *
 */
public class SetaVoto extends AsyncTask<String, Void, Boolean>{

	private ProgressDialog progresso;
    private Context contexto;
    private static final int HTTP_STATUS_OK = 204;

	//Método construtor
    public SetaVoto(Context contexto) {
        this.contexto = contexto;
    }
    
    // Ação executada antes de se executar a tarefa em si (geralmente usada para informar ao usuário o que está acontecendo)
    @Override
    protected void onPreExecute() {
        //Cria novo um ProgressDialogo e exibe
    	progresso = new ProgressDialog(contexto);
    	progresso.setTitle("Voto");
    	progresso.setMessage("Setando o voto, aguarde...");
    	progresso.setIndeterminate(true);
    	progresso.setCancelable(false);
    	progresso.show();
    }
    
   // Método que executa a tarefa de acessar o webservice
	@Override
	protected Boolean doInBackground(String... parametros) {
		HttpClient httpCliente = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		
		// URLs utilizada nos testes locais:
		// "http://10.0.2.2:8080/svbe-resource/sessao/setVoto?idSessao=" + parametros[0] + "&idPolitico=" + parametros[1] + "&resposta=" + parametros[2]
		// "http://192.168.1.100:8080/svbe-resource/sessao/setVoto?idSessao=" + parametros[0] + "&idPolitico=" + parametros[1] + "&resposta=" + parametros[2]
		
		// URL utilizada no acesso ao Jelastic:
		// "http://faccamptcc2013.jelastic.websolute.net.br/svbe-resource/sessao/setVoto?idSessao=" + parametros[0] + "&idPolitico=" + parametros[1] + "&resposta=" + parametros[2]
		HttpPut httpPut = new HttpPut("http://faccamptcc2013.jelastic.websolute.net.br/svbe-resource/sessao/setVoto?idSessao=" + parametros[0] + "&idPolitico=" + parametros[1] + "&resposta=" + parametros[2]);
		
		Boolean resultado = Boolean.FALSE;
		
		try {
			HttpResponse response = httpCliente.execute(httpPut, localContext);
			StatusLine status = response.getStatusLine();
			
			if (status.getStatusCode() == HTTP_STATUS_OK) {
				resultado = Boolean.TRUE;
			}
			else {
				resultado = Boolean.FALSE;
			}

		} catch (Exception e) {
			e.getLocalizedMessage();
		}

		return resultado;
	}
	
	// Método executado após a execução da tarefa (verifica se o voto foi cadastrado ou não)
	@Override
	protected void onPostExecute(Boolean resultado) {
		if (resultado.equals(Boolean.FALSE)) {
			progresso.dismiss();
			Toast aviso = Toast.makeText(contexto, "Erro ao cadastrar o voto", Toast.LENGTH_LONG);
			aviso.show();
		} else {
			progresso.dismiss();
			Toast aviso = Toast.makeText(contexto, "Voto cadastrado com sucesso", Toast.LENGTH_LONG);
			aviso.show();
		}
	}

}
