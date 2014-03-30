package br.com.svbe.resource.model;

/**
 * 
 * Classe do modelo do objeto do tipo Politico com seus atributos e métodos contrutores, getters e setters
 * 
 * @author FLAPRANO
 *
 */
public class Politico {

	private String imei;
	private String nome;
	private String login;
	private String senha;
	
	public Politico() {
		
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
