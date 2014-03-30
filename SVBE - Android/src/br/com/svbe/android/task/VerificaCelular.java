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

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Classe respons�vel pela tarefa de verificar se o celular j� est� cadastrado no banco de dados (atrav�s do uso do webservice)
 * 
 * String = tipo que passado para o m�todo doInBackground(String� parametros)
 * Void = tipo que ser� passado para o m�todo onProgressUpdate(Void� parametros)
 * Boolean tipo do retorno do m�todo doInBackground() e tipo que ser� passado para o m�todo onPostExecute(Boolean resultado)
 * 
 * @author Felipe Laprano
 *
 */
public class VerificaCelular extends AsyncTask<String, Void, Boolean> {
    private Context CONTEXTO;
    private static final int HTTP_STATUS_OK = 200;

	//M�todo construtor
    public VerificaCelular(Context contexto) {
        this.CONTEXTO = contexto;
    }
    
    //M�todo para ler as informa��es de uma p�gina (resposta do webservice) e transformar essas informa��es em uma string
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

    // M�todo que executa a tarefa de acessar o webservice
	@Override
	protected Boolean doInBackground(String... parametros) {
		HttpClient httpCliente = new DefaultHttpClient();
		HttpContext HttpContext = new BasicHttpContext();
		
		// URLs utilizada nos testes locais:
		// "http://10.0.2.2:8080/svbe-resource/politico/getPoliticoById?imei=" + parametros[0]
		// "http://192.168.1.101:8080/svbe-resource/politico/getPoliticoById?imei=" + parametros[0]
		
		// URL utilizada no acesso ao Jelastic:
		// "http://faccamptcc2013.jelastic.websolute.net.br/svbe-resource/politico/getPoliticoById?imei=" + parametros[0]
		HttpGet httpGet = new HttpGet("http://faccamptcc2013.jelastic.websolute.net.br/svbe-resource/politico/getPoliticoById?imei=" + parametros[0]);
		
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

	// M�todo executado ap�s a execu��o da tarefa (verifica se o usu�rio foi cadastrado ou n�o)
	@Override
	protected void onPostExecute(Boolean resultado) {
		if (resultado.equals(Boolean.FALSE)) {
			Toast aviso = Toast.makeText(CONTEXTO, "Usu�rio n�o cadastrado para esse celular.\n� necess�rio cadastrar um usu�rio.", Toast.LENGTH_LONG);
			aviso.show();
		}
	}
}
