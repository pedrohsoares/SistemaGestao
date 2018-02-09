package sistemaGestao;

import java.util.ArrayList;

public class UnidadeAcademica {
	private String nome;
	private ArrayList<Usuario> listUsuarios;
	private ArrayList<Alocacao> listAlocacoes;
	private ArrayList<Recurso> listRecursos;
	
	
	public UnidadeAcademica(String nome) {
		this.nome = nome;
		this.listUsuarios = new ArrayList<Usuario>();
		this.listAlocacoes = new ArrayList<Alocacao>();
		this.listRecursos = new ArrayList<Recurso>();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ArrayList<Usuario> getListUsuarios() {
		return listUsuarios;
	}

	public void setListUsuarios(ArrayList<Usuario> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}

	public ArrayList<Alocacao> getListAlocacoes() {
		return listAlocacoes;
	}

	public void setListAlocacoes(ArrayList<Alocacao> listAlocacoes) {
		this.listAlocacoes = listAlocacoes;
	}

	public ArrayList<Recurso> getListRecursos() {
		return listRecursos;
	}

	public void setListRecursos(ArrayList<Recurso> listRecursos) {
		this.listRecursos = listRecursos;
	}
	
	public Usuario consultarUsuario(String identificacao) {
		for(Usuario iterator : listUsuarios) {
			if(iterator.getNomeUsuario().equals(identificacao) || iterator.getEmail().equals(identificacao))
				return iterator;
		}
		return null;
	}
	
	public Recurso consultarRecurso(String identificacao) {
		for(Recurso recurso : listRecursos) 
			if(recurso.getIdentificacao().equals(identificacao))
				return recurso;
			
		return null;
	}
	
	public void quantidadeRecursosPorStatus() {
		int processoAlocacao = 0;
		int alocado = 0;
		int andamento = 0;
		int concluido = 0;
		
		
		for(Alocacao alocacao : listAlocacoes) {
			if(alocacao.getStatus() == TipoAlocacao.PROCESSO_DE_ALOCACAO)
				processoAlocacao += alocacao.getListRecursos().size();
			else if(alocacao.getStatus() == TipoAlocacao.ALOCADO)
				alocado += alocacao.getListRecursos().size();
			else if(alocacao.getStatus() == TipoAlocacao.ANDAMENTO)
				andamento += alocacao.getListRecursos().size();
			else
				concluido += alocacao.getListRecursos().size();
		}
		
		System.out.println("Número de recursos em processo de alocação: " + processoAlocacao);
		System.out.println("Número de recursos alocados: " + alocado);
		System.out.println("Número de recursos em andamento: " + andamento);
		System.out.println("Número de recursos concluídos: " + concluido);
	}
	
	public void quantidadeAtividadesPorTipo() {
		int aulaTradicional = 0;
		int apresentacao = 0;
		int laboratorio = 0;
		
		for(Alocacao alocacao : listAlocacoes) {
			if(alocacao.getAtividade().getTipoAtividade() == TipoAtividade.APRESENTACAO)
				apresentacao++;
			if(alocacao.getAtividade().getTipoAtividade() == TipoAtividade.AULA_TRADICIONAL)
				aulaTradicional++;
			if(alocacao.getAtividade().getTipoAtividade() == TipoAtividade.LABORATORIO)
				laboratorio++;
		}
		
		System.out.println("Número de apresentações: " + apresentacao);
		System.out.println("Número de aulas tradicionais: " + aulaTradicional);
		System.out.println("Número de laboratórios: " + laboratorio);
	}
	
	public int quantidadeUsuarios() {
		return listUsuarios.size();
	}
	
	public int quantidadeAlocacoes() {
		return listAlocacoes.size();
	}
	
}
