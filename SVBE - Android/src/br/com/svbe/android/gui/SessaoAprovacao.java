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
import android.widget.ImageView;
import android.widget.TextView;
import br.com.svbe.R;
import br.com.svbe.android.model.Sessao;
import br.com.svbe.android.task.SetaVoto;

/**
 * Classe da interface de sess�o do tipo Aprova��o
 * 
 * @author FLAPRANO
 * 
 */
public class SessaoAprovacao extends Activity {

	private TextView nomeSessao, descricaoSessao, dtInicial, dtFinal;
	private ImageView btnYes, btnNo;
	private final Context CONTEXTO = this;

	/**
	 * M�todo executado no momento em que a tela (Activity) � chamada no
	 * smartphone
	 */
	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Seta o layout definido no xml de layout "activity_sessao_aprovacao"
		setContentView(R.layout.activity_sessao_aprovacao);

		// Recupera as informa��es que estavam na sess�o selecionada da lista de sess�es e preenche um objeto do tipo Sessao
		Sessao sessao = (Sessao) getIntent().getSerializableExtra("sessao");
		if (sessao != null) {
			// Refer�ncia os objetos com os elementos do layout
			nomeSessao = (TextView) findViewById(R.id.nome_SessaoAprovacao);
			descricaoSessao = (TextView) findViewById(R.id.descricao_SessaoAprovacao);
			dtInicial = (TextView) findViewById(R.id.dtInicial_SessaoAprovacao);
			dtFinal = (TextView) findViewById(R.id.dtFinal_SessaoAprovacao);

			btnYes = (ImageView) findViewById(R.id.btnYes_SessaoAprovacao);
			btnNo = (ImageView) findViewById(R.id.btnNo_SessaoAprovacao);

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

			final String idSessao = sessao.getIdSessao();

			// A��o do bot�o da op��o de voto "Sim" (seta 1 como resposta no banco)
			btnYes.setOnClickListener(new ImageView.OnClickListener() {
				public void onClick(View view) {
					// Recupera o IMEI do aparelho (IMEI do Emulador � igual a 000000000000000)
					TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
					String imei = telephonyManager.getDeviceId();

					// Executa a a��o de setar o voto
					SetaVoto setaVoto = new SetaVoto(CONTEXTO);
					setaVoto.execute(idSessao, imei, "1");

					// Volta a tela anterior (Lista de Sess�es)
					Intent i = new Intent(CONTEXTO, ListaSessoes.class);
					i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			});

			// A��o do bot�o da op��o de voto "N�o" (seta 0 como resposta no banco)
			btnNo.setOnClickListener(new ImageView.OnClickListener() {
				public void onClick(View v) {
					// Recupera o IMEI do aparelho (IMEI do Emulador � igual a 000000000000000)
					TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
					String imei = telephonyManager.getDeviceId();

					// Executa a a��o de setar o voto
					SetaVoto setaVoto = new SetaVoto(CONTEXTO);
					setaVoto.execute(idSessao, imei, "0");

					// Volta a tela anterior (Lista de Sess�es)
					Intent i = new Intent(CONTEXTO, ListaSessoes.class);
					i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			});
		}
	}

}
