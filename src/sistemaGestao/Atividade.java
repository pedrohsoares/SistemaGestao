package sistemaGestao;

import java.util.ArrayList;

public class Atividade {
	private TipoAtividade tipoAtividade;
	private String nome;
	private String descricao;
	private ArrayList<Usuario> listParticipantes;
	private String materialApoio;
	
	public Atividade(String nome, String descricao, String materialApoio) {
		this.nome = nome;
		this.descricao = descricao;
		this.materialApoio = materialApoio;
		listParticipantes = new ArrayList<Usuario>();
	}
	
	public TipoAtividade getTipoAtividade() {
		return tipoAtividade;
	}
	
	public void setTipoAtividade(TipoAtividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
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
	public ArrayList<Usuario> getListParticipantes() {
		return listParticipantes;
	}
	public void setListParticipantes(ArrayList<Usuario> listParticipantes) {
		this.listParticipantes = listParticipantes;
	}
	public String getMaterialApoio() {
		return materialApoio;
	}
	public void setMaterialApoio(String materialApoio) {
		this.materialApoio = materialApoio;
	}
	
	public void adicionarParticipante(Usuario usuario) {
		listParticipantes.add(usuario);
	}
	
}
