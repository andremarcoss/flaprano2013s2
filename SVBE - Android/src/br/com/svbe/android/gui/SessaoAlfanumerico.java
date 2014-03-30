package br.com.svbe.android.gui;

import java.sql.Date;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.svbe.R;
import br.com.svbe.android.model.Sessao;
import br.com.svbe.android.task.SetaVoto;

/**
 * Classe da interface de sessão do tipo Escolha
 * 
 * @author FLAPRANO
 * 
 */
public class SessaoAlfanumerico extends Activity {
	
	private TextView nomeSessao, descricaoSessao, dtInicial, dtFinal;
	private Button btnOpcao1, btnOpcao2;
	private final Context CONTEXTO = this;

	/**
	 * Método executado no momento em que a tela (Activity) é chamada no
	 * smartphone
	 */
	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Seta o layout definido no xml de layout "activity_sessao_escolha"
		setContentView(R.layout.activity_sessao_escolha);

		//Recupera as informações que estavam na sessão selecionada da lista de sessões e preenche um objeto do tipo Sessao
		Sessao sessao = (Sessao) getIntent().getSerializableExtra("sessao");
		if (sessao != null) {
			// Referência os objetos com os elementos do layout
			nomeSessao = (TextView) findViewById(R.id.nome_SessaoEscolha);
			descricaoSessao = (TextView) findViewById(R.id.descricao_SessaoEscolha);
			dtInicial = (TextView) findViewById(R.id.dtInicial_SessaoEscolha);
			dtFinal = (TextView) findViewById(R.id.dtFinal_SessaoEscolha);

			btnOpcao1 = (Button) findViewById(R.id.btnOpcao1_SessaoEscolha);
			btnOpcao2 = (Button) findViewById(R.id.btnOpcao2_SessaoEscolha);

			nomeSessao.setText(sessao.getNome());
			descricaoSessao.setText(sessao.getDescricao());

			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

			Date dataInicial = Date.valueOf(sessao.getDataInicial());
			dtInicial.setText(formatoData.format(dataInicial));

			if (!sessao.getDataFinal().equals("")) {
				Date dataFinal = Date.valueOf(sessao.getDataFinal());
				dtFinal.setText(formatoData.format(dataFinal));
			} else
				dtFinal.setText("");

			btnOpcao1.setText(sessao.getOpcao1());
			btnOpcao2.setText(sessao.getOpcao2());

			final String idSessao = sessao.getIdSessao();

			// Ação do botão da primeira opção de voto (seta 1 como resposta no banco)
			btnOpcao1.setOnClickListener(new ImageView.OnClickListener() {
				public void onClick(View view) {
					// Recupera o IMEI do aparelho (IMEI do Emulador é igual a 000000000000000)
					TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
					String imei = telephonyManager.getDeviceId();

					// Executa a ação de setar o voto
					SetaVoto setaVoto = new SetaVoto(CONTEXTO);
					setaVoto.execute(idSessao, imei, "1");

					// Volta a tela anterior (Lista de Sessões)
					Intent i = new Intent(CONTEXTO, ListaSessoes.class);
					i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			});

			// Ação do botão da segunda opção de voto (seta 0 como resposta no banco)
			btnOpcao2.setOnClickListener(new ImageView.OnClickListener() {
				public void onClick(View v) {
					// Recupera o IMEI do aparelho (IMEI do Emulador é igual a 000000000000000)
					TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
					String imei = telephonyManager.getDeviceId();

					// Executa a ação de setar o voto
					SetaVoto setaVoto = new SetaVoto(CONTEXTO);
					setaVoto.execute(idSessao, imei, "0");

					// Volta a tela anterior (Lista de Sessões)
					Intent i = new Intent(CONTEXTO, ListaSessoes.class);
					i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			});
		}
	}

}
