package br.com.svbe.android.model;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.svbe.R;

/**
 * 
 * Classe do de adaptador de objetos do tipo Sessao para ser usada para adaptar os objetos desse tipo
 * em objetos do tipo ListView (listas em aplica��es Android)
 * 
 * @author FLAPRANO
 *
 */
public class SessaoAdapter extends BaseAdapter {

	private Context contexto;
	private List<Sessao> lista;

	//M�todo construtor do adaptador
	public SessaoAdapter(Context contexto, List<Sessao> lista) {
		this.contexto = contexto;
		this.lista = lista;
	}

	//M�todo para retornar a quantidade de elementos do adaptador
	@Override
	public int getCount() {
		return lista.size();
	}

	//M�todo para retornar o objeto Sessao de uma determinada posi��o da lista
	@Override
	public Object getItem(int posicao) {
		Sessao sessao = lista.get(posicao);
		return sessao;
	}

	//M�todo para retornar a posi��o de um determinado elemento da lista
	@Override
	public long getItemId(int posicao) {
		return posicao;
	}

	//M�todo com a estrutura de como a lista ser� exibida (informa��es a serem exibidas)
	@Override
	public View getView(int posicao, View convertView, ViewGroup parent) {
		Sessao sessao = lista.get(posicao);

		LayoutInflater inflater = (LayoutInflater) contexto
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.list_item, null);

		TextView textID = (TextView) view.findViewById(R.id.idSessao);
		textID.setText(sessao.getIdSessao());

		TextView textNome = (TextView) view.findViewById(R.id.tituloSessao);
		textNome.setText(sessao.getNome());
		return view;
	}

}
