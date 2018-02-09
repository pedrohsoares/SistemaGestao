package sistemaGestao;

import java.util.ArrayList;

public class Usuario {
	private String nomeUsuario;
	private String senha;
	private String nome;
	private String email;
	private TipoUsuario tipoUsuario;
	private ArrayList<Recurso> listRecursosAlocados;
	private ArrayList<Atividade> listAtividades;
	
	public Usuario(String nomeUsuario, String senha, String nome,
				String email, TipoUsuario tipoUsuario) {
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		this.tipoUsuario = tipoUsuario;
		
		listRecursosAlocados = new ArrayList<Recurso>();
		listAtividades = new ArrayList<Atividade>();
	}
	
	public Usuario(String nomeUsuario, String senha, String nome,
			String email, TipoUsuario tipoUsuario,ArrayList<Recurso> listRecurso, ArrayList<Atividade> listAtividade) {
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		this.tipoUsuario = tipoUsuario;
		this.listRecursosAlocados = listRecurso;
		this.listAtividades = listAtividade;
	}

	public Usuario(String nomeUsuario, String senha, String nome, String email) {
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		this.listRecursosAlocados = new ArrayList<Recurso>();
		this.listAtividades = new ArrayList<Atividade>();
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

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<Recurso> getListRecursosAlocados() {
		return listRecursosAlocados;
	}
	public void setListRecursosAlocados(ArrayList<Recurso> listRecursosAlocados) {
		this.listRecursosAlocados = listRecursosAlocados;
	}
	public ArrayList<Atividade> getListAtividades() {
		return listAtividades;
	}
	
	public void setListAtividades(ArrayList<Atividade> listAtividades) {
		this.listAtividades = listAtividades;
	}
	
}
