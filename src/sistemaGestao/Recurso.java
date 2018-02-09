package sistemaGestao;

import java.util.ArrayList;

public class Recurso {
	private String identificacao;
	private TipoRecurso tipoRecurso;
	private Date dataInicio;
	private Date dataTermino;
	private ArrayList<Usuario> listResponsaveis;
	
	public Recurso(String identificacao, TipoRecurso tipoRecurso, Date dataInicio, Date dataTermino, Usuario responsavel) {
		ArrayList<Usuario> listResponsaveis = new ArrayList<Usuario>();
		listResponsaveis.add(responsavel);
		
		this.identificacao = identificacao;
		this.tipoRecurso = tipoRecurso;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.listResponsaveis = listResponsaveis;
	}
	
	public Recurso(String identificacao, TipoRecurso tipoRecurso, Date dataInicio, Date dataTermino, ArrayList<Usuario> listResponsaveis) {
		this.identificacao = identificacao;
		this.tipoRecurso = tipoRecurso;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.listResponsaveis = listResponsaveis;
	}
	
	public Recurso(String identificacao, Date dataInicio, Date dataTermino) {
		this.identificacao = identificacao;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.listResponsaveis = new ArrayList<Usuario>();
	}
	
	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}
	
	public TipoRecurso getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(TipoRecurso tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataTermino() {
		return dataTermino;
	}
	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}
	public ArrayList<Usuario> getListResponsaveis() {
		return listResponsaveis;
	}
	
	public void setListResponsaveis(ArrayList<Usuario> listResponsaveis) {
		this.listResponsaveis = listResponsaveis;
	}
	
	
	
}
