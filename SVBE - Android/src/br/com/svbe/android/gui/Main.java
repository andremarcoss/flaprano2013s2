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
import br.com.svbe.android.task.VerificaCelular;
import br.com.svbe.android.task.VerificaUsuario;

/**
 * Classe da interface de login (tela principal da aplicação)
 * 
 * @author FLAPRANO
 *
 */
public class Main extends Activity {
	private Button btnEntrar;
	private EditText edtUsuario, edtSenha;
	private String imei;
	private final Context CONTEXTO = this;

	/**
	 * Método executado no momento em que a tela (Activity) é chamada no
	 * smartphone
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Seta o layout definido no xml de layout "activity_main"
		setContentView(R.layout.activity_main);
		
		// Recupera o IMEI do aparelho (IMEI do Emulador é igual a 000000000000000)
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		this.imei = telephonyManager.getDeviceId();
		
		// Referência os objetos com os elementos do layout
		this.edtUsuario = (EditText) findViewById(R.id.edtUsuarioLogin);
		this.edtSenha = (EditText) findViewById(R.id.edtSenhaLogin);
		this.btnEntrar = (Button) findViewById(R.id.btnEntrar);
		
		// Verifica se o celular já está cadastrado
		VerificaCelular verificaCelular = new VerificaCelular(CONTEXTO);
		try {
			boolean resultado = verificaCelular.execute(this.imei).get();
			
			if (!resultado){
				Intent intent = new Intent(this, CadastroUsuario.class);
				startActivity(intent);
				this.finish();
			}
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
		}
		
		// Ação do botão "entrar"
		this.btnEntrar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View view) {
				String login = edtUsuario.getText().toString();
				String senha = edtSenha.getText().toString();
				
				if (!login.equals("") && !senha.equals("")) {
					VerificaUsuario verificaUsuario = new VerificaUsuario(CONTEXTO);
					verificaUsuario.execute(imei, login, senha);
				} else {
					Toast aviso = Toast.makeText(CONTEXTO,
							"Necessário informar todos os campos", Toast.LENGTH_LONG);
					aviso.show();
				}
			}
		});
	}

}
