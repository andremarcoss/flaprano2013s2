package br.com.svbe.android.task;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import br.com.svbe.android.gui.ListaSessoes;

/**
 * Classe responsável pela tarefa de verificar se o usuário já está cadastrado no banco de dados (através do uso do webservice)
 * 
 * String = tipo que passado para o método doInBackground(String… parametros)
 * Void = tipo que será passado para o método onProgressUpdate(Void… parametros)
 * Boolean tipo do retorno do método doInBackground() e tipo que será passado para o método onPostExecute(Boolean resultado)
 * 
 * @author Felipe Laprano
 *
 */
public class VerificaUsuario extends AsyncTask<String, Void, Boolean> {
	private ProgressDialog progresso;
    private Context contexto;
    private static final int HTTP_STATUS_OK = 200;

	//Método construtor
    public VerificaUsuario(Context contexto) {
        this.contexto = contexto;
    }
    
    //Método para ler as informações de uma página (resposta do webservice) e transformar essas informações em uma string
    protected String getASCIIContentFromEntity(HttpEntity entity)
			throws IllegalStateException, IOException {
		InputStream entrada = entity.getContent();

		StringBuffer saida = new StringBuffer();
		int n = 1;
		while (n > 0) {
			byte[] b = new byte[4096];
			n = entrada.read(b);

			if (n > 0)
				saida.append(new String(b, 0, n));
		}

		return saida.toString();
	}
    
    // Ação executada antes de se executar a tarefa em si (geralmente usada para informar ao usuário o que está acontecendo)
    @Override
    protected void onPreExecute() {
        //Cria novo um ProgressDialogo e exibe
    	progresso = new ProgressDialog(contexto);
    	progresso.setTitle("Verificação de Usuário");
    	progresso.setMessage("Verificando usuário, aguarde...");
    	progresso.setIndeterminate(true);
    	progresso.setCancelable(false);
    	progresso.show();
    }

   // Método que executa a tarefa de acessar o webservice
	@Override
	protected Boolean doInBackground(String... parametros) {
		HttpClient httpCliente = new DefaultHttpClient();
		HttpContext HttpContext = new BasicHttpContext();
		
		// URLs utilizada nos testes locais:
		// "http://10.0.2.2:8080/svbe-resource/politico/getPolitico?imei=" + parametros[0] + "&login=" + parametros[1] + "&senha=" + parametros[2]
		// "http://192.168.1.100:8080/svbe-resource/politico/getPolitico?imei=" + parametros[0] + "&login=" + parametros[1] + "&senha=" + parametros[2]
		
		// URL utilizada no acesso ao Jelastic:
		// "http://faccamptcc2013.jelastic.websolute.net.br/svbe-resource/politico/getPolitico?imei=" + parametros[0] + "&login=" + parametros[1] + "&senha=" + parametros[2]
		HttpGet httpGet = new HttpGet("http://faccamptcc2013.jelastic.websolute.net.br/svbe-resource/politico/getPolitico?imei=" + parametros[0] + "&login=" + parametros[1] + "&senha=" + parametros[2]);
		
		String resultado = null;
		
		try {
			HttpResponse response = httpCliente.execute(httpGet, HttpContext);
			StatusLine status = response.getStatusLine();
			
			if (status.getStatusCode() == HTTP_STATUS_OK) {
				HttpEntity entity = response.getEntity();
				resultado = getASCIIContentFromEntity(entity);
			}
		} catch (Exception e) {
			e.getLocalizedMessage();
		}

		return Boolean.valueOf(resultado);
	}

	// Método executado após a execução da tarefa (verifica se o usuário foi verificado com sucesso ou não)
	@Override
	protected void onPostExecute(Boolean resultado) {
		if (resultado.equals(Boolean.FALSE)) {
			progresso.dismiss();
			Toast aviso = Toast.makeText(contexto, "Usuário não cadastrado para esse celular", Toast.LENGTH_LONG);
			aviso.show();
		} else {
			progresso.dismiss();
			contexto.startActivity(new Intent(contexto, ListaSessoes.class));
		}
	}
}
