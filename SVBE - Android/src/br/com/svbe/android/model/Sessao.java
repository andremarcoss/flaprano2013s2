package br.com.svbe.android.model;

import java.io.Serializable;

/**
 * 
 * Classe do modelo do objeto do tipo Sessao com seus atributos e métodos contrutores, getters e setters
 * Implementa a interface Serializable (usada para serializar as informações do objetos)
 * 
 * @author FLAPRANO
 *
 */
@SuppressWarnings("serial")
public class Sessao implements Serializable {

	private String idSessao;
	private String nome;
	private String descricao;
	private String tipo;
	private String opcao1;
	private String opcao2;
	private String participacao;
	private String dataInicial;
	private String dataFinal;
	private String controle;
	private int qtdMax;
	private int qtdVotosPos;
	private int qtdVotosNeg;
	private String estado;

	public Sessao() {

	}

	public Sessao(String idSessao, String nome, String descricao, String tipo,
			String opcao1, String opcao2, String participacao,
			String dataInicial, String dataFinal, String controle, int qtdMax,
			int qtdVotosPos, int qtdVotosNeg, String estado) {
		this.idSessao = idSessao;
		this.nome = nome;
		this.descricao = descricao;
		this.tipo = tipo;
		this.opcao1 = opcao1;
		this.opcao2 = opcao2;
		this.participacao = participacao;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.controle = controle;
		this.qtdMax = qtdMax;
		this.qtdVotosPos = qtdVotosPos;
		this.qtdVotosNeg = qtdVotosNeg;
		this.estado = estado;
	}

	public String getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(String id) {
		this.idSessao = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getParticipacao() {
		return participacao;
	}

	public void setParticipacao(String participacao) {
		this.participacao = participacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getOpcao1() {
		return opcao1;
	}

	public void setOpcao1(String opcao1) {
		this.opcao1 = opcao1;
	}

	public String getOpcao2() {
		return opcao2;
	}

	public void setOpcao2(String opcao2) {
		this.opcao2 = opcao2;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getControle() {
		return controle;
	}

	public void setControle(String controle) {
		this.controle = controle;
	}

	public int getQtdMax() {
		return qtdMax;
	}

	public void setQtdMax(int qtdMax) {
		this.qtdMax = qtdMax;
	}

	public int getQtdVotosPos() {
		return qtdVotosPos;
	}

	public void setQtdVotosPos(int qtdVotosPos) {
		this.qtdVotosPos = qtdVotosPos;
	}

	public int getQtdVotosNeg() {
		return qtdVotosNeg;
	}

	public void setQtdVotosNeg(int qtdVotosNeg) {
		this.qtdVotosNeg = qtdVotosNeg;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
