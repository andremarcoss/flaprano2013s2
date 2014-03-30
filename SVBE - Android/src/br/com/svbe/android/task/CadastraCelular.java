package br.com.svbe.android.task;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.os.AsyncTask;
import br.com.svbe.android.model.Politico;

/**
 * Classe responsável pela tarefa de cadastrar um usuário no banco de dados (através do uso do webservice)
 * 
 * String = tipo que passado para o método doInBackground(String… parametros)
 * Void = tipo que será passado para o método onProgressUpdate(Void… parametros)
 * Boolean tipo do retorno do método doInBackground() e tipo que será passado para o método onPostExecute(Boolean resultado)
 * 
 * @author Felipe Laprano
 *
 */
public class CadastraCelular extends AsyncTask<Politico, Void, String> {
	
    //Método para ler as informações de uma página (resposta do webservice) e transformar essas informações em uma string
	protected String getASCIIContentFromEntity(HttpEntity entity)
			throws IllegalStateException, IOException {
		InputStream in = entity.getContent();

		StringBuffer out = new StringBuffer();
		int n = 1;
		while (n > 0) {
			byte[] b = new byte[4096];
			n = in.read(b);

			if (n > 0)
				out.append(new String(b, 0, n));
		}

		return out.toString();
	}
	
	// Método que executa a tarefa de acessar o webservice
	@Override
	protected String doInBackground(Politico... parametro) {
		HttpClient httpCliente = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		
		// URLs utilizada nos testes locais:
		// "http://10.0.2.2:8080/svbe-resource/politico/setPolitico"
		// "http://192.168.1.100:8080/svbe-resource/politico/setPolitico"
		
		// URL utilizada no acesso ao Jelastic:
		// "http://faccamptcc2013.jelastic.websolute.net.br/svbe-resource/politico/setPolitico"
		HttpPost httpPost = new HttpPost("http://faccamptcc2013.jelastic.websolute.net.br/svbe-resource/politico/setPolitico");
		
		httpPost.setHeader("imei", parametro[0].getImei());
		httpPost.setHeader("nome", parametro[0].getNome());
		httpPost.setHeader("login", parametro[0].getLogin());
		httpPost.setHeader("senha", parametro[0].getSenha());
		String conteudo = null;
		
		try {
			HttpResponse response = httpCliente.execute(httpPost, localContext);
			HttpEntity entity = response.getEntity();
			conteudo = getASCIIContentFromEntity(entity);

		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		return conteudo;
	}
}
