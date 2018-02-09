package sistemaGestao;

import java.util.ArrayList;

public class Aluno {
	private String matricula;
	private String nome;
	private String nomeUsuario;
	private String senha;
	private String email;
	private ArrayList<Atividade> listAtividades;
	
	public Aluno(String matricula, String nome, String email) {
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		listAtividades = new ArrayList<Atividade>();
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<Atividade> getListAtividades() {
		return listAtividades;
	}
	public void setListAtividades(ArrayList<Atividade> listAtividades) {
		this.listAtividades = listAtividades;
	}
	
}
