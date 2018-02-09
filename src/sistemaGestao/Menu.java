package sistemaGestao;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	private static Usuario activeUser;
	private static Scanner input;
	
	public static Usuario login(UnidadeAcademica unidadeAcademica) {
		boolean flag = false;
		
		while(!flag) {
			System.out.println("Informe seu nome de usu�rio: ");
			String usuario = input.nextLine();
			System.out.println("Informe sua senha: ");
			String senha = input.nextLine();
			
			for(Usuario user : unidadeAcademica.getListUsuarios()) {
				System.out.println("Login: " + user.getNomeUsuario() + "Senha: " + user.getSenha() + "Usuario: " + usuario);
				if(usuario.equals(user.getNomeUsuario())) {
					if(senha.equals(user.getSenha())) {
						activeUser = user;
						flag = true;
					}
					break;
				}
			}
			
			if(!flag)
				System.out.println("Nome do usu�rio ou senha incorreta! Informe novamente.");
		}
		
		return activeUser;
	}
	
	public static UnidadeAcademica criarUnidadeAcademica() {
		UnidadeAcademica unidadeAcademica = new UnidadeAcademica("UFAL");
		ArrayList<Usuario> arrResponsavel = new ArrayList<Usuario>();
		Usuario defaultAdmin = new Usuario("admin","admin","Administrador","admin@admin.com",TipoUsuario.ADMINISTRADOR);
		
		arrResponsavel.add(defaultAdmin);
		unidadeAcademica.setListUsuarios(arrResponsavel);
		
		return unidadeAcademica;
	}
	
	public static void principalTextMenu() {
		System.out.println("USU�RIO: " + activeUser.getNome() + "  TIPO_CONTA: " + activeUser.getTipoUsuario() + "\n\n");
		System.out.println("MENU - SISTEMA DE GEST�O DE RECURSOS\n\n");
	}
	
	public static int mainMenu() {
		int opcao;
		
		principalTextMenu();
		System.out.println("\t1.CONSULTAR");
		if(activeUser.getTipoUsuario() != TipoUsuario.ALUNO_DOUTORADO &&
			activeUser.getTipoUsuario() != TipoUsuario.ALUNO_MESTRADO &&
			activeUser.getTipoUsuario() != TipoUsuario.ALUNO_GRADUACAO) {
			System.out.println("\t2.CADASTRAR");
			System.out.println("\t3.ALTERAR STATUS DA ALOCA��O");
			System.out.println("\t4.ADICIONAR RECURSO NA ATIVIDADE");
		}
		if(activeUser.getTipoUsuario() == TipoUsuario.ADMINISTRADOR)
			System.out.println("\t5.RELAT�RIO DE ATIVIDADES");
		
		System.out.println("\t0.SAIR");
		
		System.out.println("Informe o n�mero da op��o desejada:");
		opcao = input.nextInt();
		
		return opcao;
		
	}
	
	public static int menuConsultar() {
		int opcao;
		
		principalTextMenu();
		System.out.println("CONSULTAR\n\t1.USU�RIO\n\t2.RECURSO");
		System.out.println("Informe o n�mero da op��o desejada:");
		opcao = input.nextInt();
		
		while(opcao > 2) {
			System.out.println("O op��o deve ser 1 ou 2\nInforme novamente o n�mero da op��o desejada:");
			opcao = input.nextInt();
		}
		
		return opcao;
	}
	
	public static int menuCadastrar() {
		int opcao;
		
		principalTextMenu();
		System.out.println("CADASTRAR\n\t1.USU�RIO\n\t2.ATIVIDADE\n\t3.RECURSO");
		opcao = input.nextInt();
		return opcao;
	}
	
	public static void main(String[] args) {
		UnidadeAcademica unidadeAcademica = criarUnidadeAcademica();
		input = new Scanner(System.in);
		int opcao;
		String identificacao;
		
		login(unidadeAcademica);
		while(activeUser != null) {
			opcao = mainMenu();
			switch(opcao) {
				case 0:
					System.out.println("Voc� deseja continuar utilizando o sistema?");
					System.out.println("\t1.SIM\n\t2.N�O");
					opcao = input.nextInt();
					if(opcao == 1) {
						activeUser = null;
						input = new Scanner(System.in);
						login(unidadeAcademica);
					}else {
						activeUser = null;
					}
					break;
				case 1:
					//MENU CONSULTAR
					int opcaoConsultar = menuConsultar();
					if(opcaoConsultar == 1){
						//CONSULTAR USUARIO
						input = new Scanner(System.in);
						System.out.println("Informe o nome de usu�rio ou email do usu�rio desejado:");
						identificacao = input.nextLine();
						Usuario consultado = unidadeAcademica.consultarUsuario(identificacao);
						if(consultado == null) {
							System.out.println("N�o foi encontrado nenhum usu�rio!");
						}else {
							System.out.println("Nome:" + consultado.getNome());
							System.out.println("E-mail:" + consultado.getEmail());
							System.out.println("Tipo de Usu�rio:" + consultado.getTipoUsuario());
							if(consultado.getListAtividades().size() > 0) {
								System.out.println("\nHist�rico de Atividades Realizadas");
								for(Atividade atividade : consultado.getListAtividades()) {
									System.out.println("Nome:" + atividade.getNome() + "\tTipo de Atividade:" + atividade.getTipoAtividade());
									System.out.println("Descri��o: " + atividade.getDescricao() + "\n");
								}
							}else
								System.out.println("Este usu�rio n�o possui nenhuma atividade realizada\n");
							
							if(consultado.getListRecursosAlocados().size() > 0) {
								System.out.println("\nHist�rico de Recursos Alocados");
								for(Recurso recurso : consultado.getListRecursosAlocados()) {
									System.out.println("Nome:" + recurso.getIdentificacao() + "\n"
											+ "\tData de Inicio:" + recurso.getDataInicio() + "\tData de T�rmino: " + recurso.getDataTermino());
								}
							}else
								System.out.println("Este usu�rio n�o possui nenhum recurso alocado\n");	
						}
					}else if(opcaoConsultar == 2){
						//CONSULTAR RECURSO
						input = new Scanner(System.in);
						System.out.println("Informe a identifica��o do recurso:");
						identificacao = input.nextLine();
						Recurso recurso = unidadeAcademica.consultarRecurso(identificacao);
						if(recurso != null) {
							System.out.println("Identifica��o: " + recurso.getIdentificacao());
							System.out.println("Data de Inicio: " + recurso.getDataInicio()
												+ "\tData de T�rmino: " + recurso.getDataTermino());
							
							System.out.println("Respons�veis pelo Recurso");
							for(Usuario usuario : recurso.getListResponsaveis())
								System.out.println("Nome: " + usuario.getNome() + "\tE-mail: " + usuario.getEmail());
							
							System.out.println("Atividades relacionadas ao recurso:");
							for(Alocacao alocacao: unidadeAcademica.getListAlocacoes()) {
								for(Recurso current : alocacao.getListRecursos()) {
									if(current.equals(recurso)) {
										System.out.println("Nome: " + alocacao.getAtividade().getNome() + "\tTipo de Atividade: " + alocacao.getAtividade().getTipoAtividade());
										System.out.println("Descri��o: " + alocacao.getAtividade().getDescricao());
									}
								}
							}
							
						}else {
							System.out.println("N�o foi possivel identificar nenhum recurso com essa identifica��o.");
						}
					}else {
						System.out.println("Informe uma op��o v�lida!");
					}
					break;
				case 2:
					//MENU CADASTRAR
					int opcaoCadastrar = menuCadastrar();
					switch(opcaoCadastrar) {
						//CADASTRAR USUARIO
						case 1:
							if(activeUser.getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
								input = new Scanner(System.in);
								System.out.println("Informe o nome de usu�rio desejado:");
								String nomeUsuario = input.nextLine();
								System.out.println("Informe a senha desejada:");
								String senha = input.nextLine();
								System.out.println("Informe o nome completo do usu�rio: ");
								String nome = input.nextLine();
								System.out.println("Informe o email do usu�rio");
								String email = input.nextLine();
								Usuario novoUsuario = new Usuario(nomeUsuario,senha,nome,email);
								
								System.out.println("Qual o tipo de usu�rio: \n\t1.ALUNO DE GRADUA��O\n\t2.ALUNO DE MESTRADO"
										+ "\n\t3.ALUNO DE DOUTORADO\n\t4.PROFESSOR\n\t5.PESQUISADOR\n\t6.ADMINISTRADOR");
								int tipoUsuario = input.nextInt();
								
								while(tipoUsuario > 6 || tipoUsuario < 0) {
									System.out.println("Informe o n�mero da op��o desejada (1-6):");
									tipoUsuario = input.nextInt();
								}
								
								switch(tipoUsuario) {
									case 1:
										novoUsuario.setTipoUsuario(TipoUsuario.ALUNO_GRADUACAO);
										break;
									case 2:
										novoUsuario.setTipoUsuario(TipoUsuario.ALUNO_MESTRADO);
										break;
									case 3:
										novoUsuario.setTipoUsuario(TipoUsuario.ALUNO_DOUTORADO);
										break;
									case 4:
										novoUsuario.setTipoUsuario(TipoUsuario.PROFESSOR);
										break;
									case 5:
										novoUsuario.setTipoUsuario(TipoUsuario.PESQUISADOR);
										break;
									case 6:
										novoUsuario.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
										break;
								}
								
								unidadeAcademica.getListUsuarios().add(novoUsuario);
								System.out.println("Usu�rio cadastrado com sucesso!");
								
								}else {
									System.out.println("Sua conta precisa ser do tipo administrador para cadastrar um novo usu�rio.");
							}
							break;
							//CADASTRAR ATIVIDADE
						case 2:
							boolean flag = false;
							int opcaoAtividade;
							
							input = new Scanner(System.in);
							System.out.println("Informe o nome da atividade:");
							String nome = input.nextLine();
							System.out.println("Escreva uma breve descri��o da atividade");
							String descricao = input.nextLine();
							System.out.println("Informe o material de apoio:");
							String materialApoio = input.nextLine();
							
							Atividade novaAtividade = new Atividade(nome,descricao,materialApoio);
							
							while(!flag) {
								input = new Scanner(System.in);
								System.out.println("Informe o nome de usu�rio ou email do participante:");
								identificacao = input.nextLine();
								
								Usuario participante = unidadeAcademica.consultarUsuario(identificacao);
								
								if(participante != null) 
									novaAtividade.getListParticipantes().add(participante);
								else
									System.out.println("N�o foi possivel encontrar nenhum usu�rio com a informa��o fornecida!");
								
								System.out.println("Deseja continuar adicionando novos participantes?\n\t1.SIM\n\t2.N�O");
								opcaoAtividade = input.nextInt();
								if(opcaoAtividade == 2)
									flag = true;
							}
							
							opcaoAtividade = 0;
							
							while(opcaoAtividade > 3 || opcaoAtividade < 1) {
								System.out.println("Informe o tipo da atividade:\n\t1.AULA TRADICIONAL\n\t2.APRESENTA��O\n\t3.LABORAT�RIO");
								opcaoAtividade = input.nextInt();
							}
							
							if(opcaoAtividade == 1)
								novaAtividade.setTipoAtividade(TipoAtividade.AULA_TRADICIONAL);
							else if(opcaoAtividade == 2)
								novaAtividade.setTipoAtividade(TipoAtividade.APRESENTACAO);
							else
								novaAtividade.setTipoAtividade(TipoAtividade.LABORATORIO);
							
							Alocacao novaAlocacao = new Alocacao(novaAtividade);
							activeUser.getListAtividades().add(novaAtividade);
							unidadeAcademica.getListAlocacoes().add(novaAlocacao);						
							break;
						
						//CADASTRAR RECURSO
						case 3:
							input = new Scanner(System.in);
							flag = false;
							
							if(activeUser.getTipoUsuario() == TipoUsuario.ADMINISTRADOR || 
									activeUser.getTipoUsuario() == TipoUsuario.PESQUISADOR ||
									activeUser.getTipoUsuario() == TipoUsuario.PROFESSOR) {
								
								if(activeUser.getListRecursosAlocados().size() > 0) {
									Recurso recurso = activeUser.getListRecursosAlocados().get(activeUser.getListRecursosAlocados().size()-1);
									for(Alocacao alocacao : unidadeAcademica.getListAlocacoes()) {
										if(alocacao.getListRecursos().contains(recurso)) {
											if(alocacao.getStatus() != TipoAlocacao.ANDAMENTO) {
												flag = true;
											}
											break;
											
										}
									}
								}else {
									flag = true;
								}
								
								if(!flag) {
									System.out.println("N�o � possivel criar um novo recurso");
								}else {
									System.out.println("Informe a identifica��o do recurso:");
									identificacao = input.nextLine();

									
									System.out.println("Informe o dia de inicio do recurso:");
									int dia = input.nextInt();
									System.out.println("Informe o mes de inicio do recurso:");
									int mes = input.nextInt();
									System.out.println("Informe o ano de inicio do recurso:");
									int ano = input.nextInt();
									System.out.println("Informe a hora de inicio do recurso:");
									int hora = input.nextInt();
									System.out.println("Informe o minuto de inicio do recurso:");
									int minuto = input.nextInt();
									Date dataInicio = new Date(dia,mes,ano,hora,minuto);
									
									System.out.println("Informe o dia de termino do recurso:");
									dia = input.nextInt();
									System.out.println("Informe o mes de termino do recurso:");
									mes = input.nextInt();
									System.out.println("Informe o ano de termino do recurso:");
									ano = input.nextInt();
									System.out.println("Informe a hora de termino do recurso:");
									hora = input.nextInt();
									System.out.println("Informe o minuto de termino do recurso:");
									minuto = input.nextInt();
									Date dataTermino = new Date(dia,mes,ano,hora,minuto);
									
									Recurso novoRecurso = new Recurso(identificacao,dataInicio,dataTermino);
									int opcaoTipoRecurso = 0;
									
									while(opcaoTipoRecurso > 4 || opcaoTipoRecurso < 1) {
										System.out.println("Informe o tipo do recurso:\n\t1.LABORAT�RIO\n\t2.AUDIT�RIO\n\t3.SALA DE AULA\n\t4.PROJETOR");
										opcaoTipoRecurso = input.nextInt();
									}
									
									if(opcaoTipoRecurso == 1)
										novoRecurso.setTipoRecurso(TipoRecurso.LABORATORIO);
									else if(opcaoTipoRecurso == 2)
										novoRecurso.setTipoRecurso(TipoRecurso.AUDITORIO);
									else if(opcaoTipoRecurso == 3)
										novoRecurso.setTipoRecurso(TipoRecurso.SALA_DE_AULA);
									else		
										novoRecurso.setTipoRecurso(TipoRecurso.PROJETOR);
									
									String nomeResponsavel = "";
									input = new Scanner(System.in);
									
									while(nomeResponsavel.length() <= 0) {
										System.out.println("Informe o nome de usu�rio ou email do responsavel:");
										nomeResponsavel = input.nextLine();
										Usuario usuario = unidadeAcademica.consultarUsuario(nomeResponsavel);
										
										if(usuario != null) {
											novoRecurso.getListResponsaveis().add(usuario);
											System.out.println("Deseja continuar adicionando respons�veis?\n\t1.SIM\n\t2.N�O");
											opcao = input.nextInt();
											if(opcao == 1)
												nomeResponsavel = "";
										}else {
											System.out.println("N�o foi possivel encontrar um usu�rio com as informa��es fornecidas");
										}
									}
									
									activeUser.getListRecursosAlocados().add(novoRecurso);
									unidadeAcademica.getListRecursos().add(novoRecurso);
								}
							}else {
								System.out.println("Voc� n�o possui um tipo de conta necess�rio para cadastrar um novo recurso!");
							}
							
							break;
						}
					break;
				case 3:
					//ALTERAR STATUS DA ALOCA��O
					input = new Scanner(System.in);
					boolean flag = false;
					
					System.out.println("Informe o nome da atividade que voc� deseja alterar o status");
					identificacao = input.nextLine();
					
					for(Alocacao alocacao : unidadeAcademica.getListAlocacoes()) {
						if(alocacao.getAtividade().getNome().equals(identificacao)) {
							if(alocacao.getStatus() == TipoAlocacao.PROCESSO_DE_ALOCACAO) {
								if(activeUser.getTipoUsuario() == TipoUsuario.ADMINISTRADOR && alocacao.getAtividade().getNome() != null && alocacao.getAtividade().getListParticipantes().size() > 0) {
									flag = true;
									alocacao.setStatus(TipoAlocacao.ALOCADO);
								}
							}else if(alocacao.getStatus() == TipoAlocacao.ALOCADO) {
								for(Recurso recurso : alocacao.getListRecursos()) {
									for(Usuario usuario : recurso.getListResponsaveis())
										if(usuario.getNomeUsuario().equals(activeUser.getNomeUsuario())) {
											flag = true;
											alocacao.setStatus(TipoAlocacao.ANDAMENTO);
											break;
										}
								}
							}else if(alocacao.getStatus() == TipoAlocacao.ANDAMENTO) {
								if(activeUser.getTipoUsuario() == TipoUsuario.ADMINISTRADOR && alocacao.getAtividade().getDescricao().length() > 0) {
									flag = true;
									alocacao.setStatus(TipoAlocacao.CONCLUIDO);
								}
							}else {
								System.out.println("Essa atividade ja foi concluida");
							}
							
							
							break;
						}
					}
					
					break;
				case 4:
					//ADICIONAR RECURSO NA ATIVIDADE
					input = new Scanner(System.in);
					flag = false;
					
					System.out.println("Informe a identifica��o do recurso");
					identificacao = input.nextLine();
					for(Recurso recurso : activeUser.getListRecursosAlocados()) {
						if(recurso.getIdentificacao().equals(identificacao)) {
							System.out.println("Informe o nome da atividade");
							identificacao = input.nextLine();
							for(Atividade atividade :activeUser.getListAtividades()) {
								if(atividade.getNome().equals(identificacao)) {
									flag = true;
									for(Alocacao alocacao : unidadeAcademica.getListAlocacoes()) {
										if(alocacao.getAtividade().equals(atividade)) {
											alocacao.getListRecursos().add(recurso);
											break;
										}
									}
									break;
								}
							}
						}
					}
					
					if(!flag)
						System.out.println("N�o foi possivel adicionar o recurso na atividade!");
					else
						System.out.println("Adicionado com sucesso!");
						
					break;
				case 5:
					//RELATORIO DE ATIVIDADES
					principalTextMenu();
					
					System.out.println("RELAT�RIO");
					System.out.println("N�mero de usu�rios: " + unidadeAcademica.quantidadeUsuarios());
					unidadeAcademica.quantidadeRecursosPorStatus();
					System.out.println("N�mero total de aloca��es: " + unidadeAcademica.quantidadeAlocacoes());
					unidadeAcademica.quantidadeAtividadesPorTipo();
					
					break;
			}
			
		}
	}
}
