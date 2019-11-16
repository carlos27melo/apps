package domain;

import javax.persistence.Entity;

@Entity
public class Usuario extends GenericDomain{

	private String nome;
	private String login;
	private String senha;
	private int nivel;
	private int posicao;
	private int pontos;
	private int previlegio;
	
	public int getPrevilegio() {
		return previlegio;
	}
	public void setPrevilegio(int previlegio) {
		this.previlegio = previlegio;
	}
	public int getPontos() {
		return pontos;
	}
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public int getPosicao() {
		return posicao;
	}
	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
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
