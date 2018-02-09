package sistemaGestao;

import java.util.ArrayList;

public class Alocacao {
	
	private Atividade atividade;
	private ArrayList<Recurso> listRecursos;
	private TipoAlocacao status;
	
	public Alocacao(Atividade atividade) {
		this.atividade = atividade;
		this.status = TipoAlocacao.PROCESSO_DE_ALOCACAO;
		listRecursos = new ArrayList<Recurso>();
	}
	
	public Atividade getAtividade() {
		return atividade;
	}
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	public ArrayList<Recurso> getListRecursos() {
		return listRecursos;
	}
	public void setListRecursos(ArrayList<Recurso> listRecursos) {
		this.listRecursos = listRecursos;
	}

	public TipoAlocacao getStatus() {
		return status;
	}

	public void setStatus(TipoAlocacao status) {
		this.status = status;
	}

	
	
}
