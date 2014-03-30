package br.com.svbe.android.gui;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.svbe.R;
import br.com.svbe.android.model.Politico;
import br.com.svbe.android.task.CadastraCelular;

/**
 * Classe da interface de cadastro de usuário
 * 
 * @author FLAPRANO
 * 
 */
public class CadastroUsuario extends Activity {
	private EditText edtUsuario, edtLogin, edtSenha;
	private Button btnCadastrar;
	private Politico politico;
	private String imei;
	private final Context CONTEXTO = this;

	/**
	 * Método executado no momento em que a tela (Activity) é chamada no
	 * smartphone
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Seta o layout definido no xml de layout "activity_cadastro_usuario"
		setContentView(R.layout.activity_cadastro_usuario);

		// Referência os objetos com os elementos do layout
		edtUsuario = (EditText) findViewById(R.id.edtUsuarioCadastro);
		edtLogin = (EditText) findViewById(R.id.edtLoginCadastro);
		edtSenha = (EditText) findViewById(R.id.edtSenhaCadastro);
		btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

		// Recupera o IMEI do aparelho (IMEI do Emulador é igual a 000000000000000)
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		imei = telephonyManager.getDeviceId();

		// Cria objeto do tipo Politico
		politico = new Politico();

		// Ação do botão cadastrar usuário
		btnCadastrar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {

				// Preenche o objeto com as informações da interface
				politico.setImei(imei);
				politico.setNome(edtUsuario.getText().toString());
				politico.setLogin(edtLogin.getText().toString());
				politico.setSenha(edtSenha.getText().toString());

				// Verifica se todas as informações estão preenchidas
				// Se não estiverem mostra um aviso na tela
				if (!politico.getNome().equals("")
						&& !politico.getLogin().equals("")
						&& !politico.getSenha().equals("")) {
					//Objeto da ação de cadastro (ação executada em Thread diferente da interface)
					CadastraCelular cadastraCelular = new CadastraCelular();
					try {
						//Executa o cadastro
						String resultado = cadastraCelular.execute(politico)
								.get();
						//Se cadastrou abre a interface principal (tela de Login)
						if (resultado.equals("1")) {
							Intent intent = new Intent(CONTEXTO, Main.class);
							startActivity(intent);
							finish();
						}
						//Senão mostra um aviso na tela (usuário já existe no banco)
						else {
							Toast aviso = Toast.makeText(CONTEXTO,
									"Usuário já existe", Toast.LENGTH_LONG);
							aviso.show();
						}
					} catch (InterruptedException e) {
					} catch (ExecutionException e) {
					}
				} else {
					Toast aviso = Toast.makeText(CONTEXTO,
							"Necessário informar todos os campos",
							Toast.LENGTH_LONG);
					aviso.show();
				}
			}
		});
	}

}
