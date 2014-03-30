package br.com.svbe.android.task;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Classe respons�vel pela tarefa de buscar as Sess�es no banco de dados (atrav�s do uso do webservice)
 * 
 * String = tipo que passado para o m�todo doInBackground(String� parametros)
 * Void = tipo que ser� passado para o m�todo onProgressUpdate(Void� parametros)
 * Boolean tipo do retorno do m�todo doInBackground() e tipo que ser� passado para o m�todo onPostExecute(Boolean resultado)
 * 
 * @author Felipe Laprano
 *
 */
public class BuscaSessoes extends AsyncTask<String, Void, String> {
	private ProgressDialog progresso;
	private Context contexto;

	//M�todo construtor
	public BuscaSessoes(Context contexto) {
		this.contexto = contexto;
	}

    //M�todo para ler as informa��es de uma p�gina (resposta do webservice) e transformar essas informa��es em uma string
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

	//A��o executada antes de se executar a tarefa em si (geralmente usada para informar ao usu�rio o que est� acontecendo)
	@Override
	protected void onPreExecute() {
		// Cria novo um ProgressDialogo e exibe
		progresso = new ProgressDialog(contexto);
		progresso.setTitle("Buscando sess�es");
		progresso.setMessage("Buscando as sess�es abertas para o usu�rio, aguarde...");
		progresso.setIndeterminate(true);
		progresso.setCancelable(false);
		progresso.show();
	}

	// M�todo que executa a tarefa de acessar o webservice
	@Override
	protected String doInBackground(String... parametros) {
		HttpClient httpCliente = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		
		// URLs utilizada nos testes locais:
		// "http://10.0.2.2:8080/svbe-resource/sessao/getSessoes?idPolitico=" + parametros[0]
		// "http://192.168.137.1:8080/svbe-resource/sessao/getSessoes?idPolitico=" + parametros[0]
		
		// URL utilizada no acesso ao Jelastic:
		// "http://faccamptcc2013.jelastic.websolute.net.br/svbe-resource/sessao/getSessoes?idPolitico=" + parametros[0]
		HttpGet httpGet = new HttpGet("http://faccamptcc2013.jelastic.websolute.net.br/svbe-resource/sessao/getSessoes?idPolitico=" + parametros[0]);
		String texto = null;

		try {
			HttpResponse response = httpCliente.execute(httpGet, localContext);

			HttpEntity entity = response.getEntity();

			texto = getASCIIContentFromEntity(entity);
		} catch (Exception e) {
			return e.getLocalizedMessage();
		}

		return texto;
	}

	// M�todo executado ap�s a execu��o da tarefa (verifica se existem sess�es sem voto definido cadastradas para o usu�rio ou n�o)
	@Override
	protected void onPostExecute(String resultado) {
		if (resultado.equals(null)) {
			progresso.dismiss();
			Toast aviso = Toast.makeText(contexto,
					"N�o existem sess�es cadastradas para voc�.",
					Toast.LENGTH_LONG);
			aviso.show();
		} else {
			progresso.dismiss();
		}
	}

}
