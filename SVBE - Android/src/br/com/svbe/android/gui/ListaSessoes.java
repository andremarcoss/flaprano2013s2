package br.com.svbe.android.gui;

import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.com.svbe.R;
import br.com.svbe.android.model.Sessao;
import br.com.svbe.android.model.SessaoAdapter;
import br.com.svbe.android.task.BuscaSessoes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Classe da interface com a lista de sessões a serem votadas
 * 
 * @author FLAPRANO
 * 
 */
public class ListaSessoes extends Activity implements OnItemClickListener {
	private String imei;
	private ListView listView;
	private String resultadoBusca;
	private SessaoAdapter adapter;
	private final Context CONTEXTO = this;

	/**
	 * Método executado no momento em que a tela (Activity) é chamada no
	 * smartphone
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Seta o layout definido no xml de layout "activity_lista_sessoes"
		setContentView(R.layout.activity_lista_sessoes);

		// Recupera o IMEI do aparelho (IMEI do Emulador é igual a 000000000000000)
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		this.imei = telephonyManager.getDeviceId();

		//Executa a ação de buscar sessões
		BuscaSessoes buscaSessoes = new BuscaSessoes(CONTEXTO);
		try {
			this.resultadoBusca = buscaSessoes.execute(this.imei).get();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		}

		//Se obteve resultado monta a lista na interface
		if (this.resultadoBusca != null && !this.resultadoBusca.equals("null")) {
			try {
				
				//Preenche a lista de sessões com as informações retornadas no formato json
				List<Sessao> listaSessao = new Gson().fromJson(this.resultadoBusca, new TypeToken<List<Sessao>>() {}.getType());

				//Preenche um adaptador de informações personalizado para objetos do tipo Sessao
				adapter = new SessaoAdapter(this, listaSessao);

				//Tipo próprio do Android para se trabalhar com listas na interface
				listView = (ListView) findViewById(R.id.listView1);
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Atualizar");
		menu.getItem(0).setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent intent = new Intent(CONTEXTO, ListaSessoes.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				return false;
			}
		});
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int posicao,
			long id) {
		Sessao sessao = (Sessao) listView.getAdapter().getItem(posicao);

		if (sessao.getTipo().equals("A")) {
			Intent intent = new Intent(this, SessaoAprovacao.class);
			intent.putExtra("sessao", sessao);
			startActivity(intent);
		} else if (sessao.getTipo().equals("C")) {
			Intent intent = new Intent(this, SessaoConfirmacao.class);
			intent.putExtra("sessao", sessao);
			startActivity(intent);
		} else if (sessao.getTipo().equals("D")) {
			Intent intent = new Intent(this, SessaoDeliberacao.class);
			intent.putExtra("sessao", sessao);
			startActivity(intent);
		} else if (sessao.getTipo().equals("E")) {
			Intent intent = new Intent(this, SessaoAlfanumerico.class);
			intent.putExtra("sessao", sessao);
			startActivity(intent);
		}

	}
}
