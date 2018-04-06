package sistemaGestao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import activities.Activity;
import activities.Laboratory;
import activities.Presentation;
import activities.TradicionalClass;
import allocations.Allocation;
import allocations.AllocationType;
import resources.Auditorium;
import resources.Classroom;
import resources.LaboratoryRoom;
import resources.Projector;
import resources.Resource;
import users.*;
import util.SupportMaterial;

public class Menu {
	
	public User login(AcademicUnit academicUnit) {
		Scanner input = new Scanner(System.in);
		User user = null;
		
		while(user == null) {
			System.out.println("Informe seu nome de usuario: ");
			String username = input.nextLine();
			System.out.println("Informe sua senha: ");
			String password = input.nextLine();
			
			user = academicUnit.getUserByLogin(username,password);
		}
		
		return user;
	}
	
	public AcademicUnit createDefaultAcademicUnit(String name) {
		AcademicUnit academicUnit = new AcademicUnit(name);
		ArrayList<User> listUsers = new ArrayList<User>();
		
		Manager defaultManager = new Manager("admin","admin","Administrador","admin@admin.com");
		
		listUsers.add(defaultManager);
		academicUnit.setListUsers(listUsers);
		
		return academicUnit;
	}
	
	public Integer mainMenu(User user){
		Scanner input = new Scanner(System.in);

		System.out.println("USUARIO: " + user.getName() + "  TIPO_CONTA: " + user.getClass().getName().substring(6) + "\n\n");
		System.out.println("MENU - SISTEMA DE GESTAO DE RECURSOS\n\n");
		
		System.out.println("\t1.CONSULTAR");
		if(user.getClass() != GraduateStudent.class  &&
			user.getClass() != MasterStudent.class &&
			user.getClass() != PhDStudent.class) {
			System.out.println("\t2.CADASTRAR");
			System.out.println("\t3.ALTERAR STATUS DA ALOCACAO");
			System.out.println("\t4.ADICIONAR RECURSO NA ATIVIDADE");
		}
		if(user.getClass() == Manager.class)
			System.out.println("\t5.RELATORIO DE ATIVIDADES");
		
		System.out.println("\t0.SAIR");
		
		System.out.println("Informe o numero da opcao desejada:");
		Integer option = Integer.parseInt(input.nextLine());
		
		
		while(option < 0 || option > 5) {
			System.out.println("Opcao invalide, digite novamente:");
			option = Integer.parseInt(input.nextLine());
		}
		
		return option;
	}
	
	public Integer logout() {
		Scanner input = new Scanner(System.in);
		Integer option;
		
		System.out.println("Voce deseja continuar utilizando o sistema?");
		System.out.println("\t1.SIM\n\t2.NAO");
		option = Integer.parseInt(input.nextLine());
		
		while(option < 1 || option > 2) {
			System.out.println("Opcao invalida, digite novamente:");
			option = Integer.parseInt(input.nextLine());
		}
	
		
		return option;
	}
	
	public Integer consult() {
		Scanner input = new Scanner(System.in);
		Integer option;
	
		System.out.println("CONSULTAR\n\t1.USUARIO\n\t2.RECURSO");
		System.out.println("Informe o numero da opcao desejada:");
		option = Integer.parseInt(input.nextLine());
		
		while(option < 1 || option > 2) {
			System.out.println("Opcao invalida, informe novamente:");
			option = Integer.parseInt(input.nextLine());
		}
		
		return option;
	}
	
	public void consultUser(AcademicUnit academicUnit) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Informe o nome de usuario ou email do usuario desejado:");
		String id = input.nextLine();
		User user = academicUnit.getUserById(id);
		
		if(user == null) {
			System.out.println("Nao foi encontrado nenhum usuario!");
		}else {
			System.out.println("Nome: " + user.getName());
			System.out.println("E-mail: " + user.getEmail());
			System.out.println("Tipo de Usuario: " + user.getClass().getName().substring(6));
			if(user.getClass().getSuperclass() == Responsible.class) {
				Responsible responsible = (Responsible) user;
				
				if(responsible.getActivities().size() > 0){
					System.out.println("\nHistorico de Atividades Realizadas");
					for(Activity activity : responsible.getActivities()) {
						System.out.println("Nome: " + activity.getName() + "\tTipo de Atividade:" + activity.getClass().getName().substring(11));
						System.out.println("Descricao: " + activity.getDescription() + "\n");
					}
				}else {
					System.out.println("Este usuario nao possui nenhuma atividade realizada!\n");
				}
				
				if(responsible.getAllocatedResources().size() > 0) {
					System.out.println("\nHistorico de Recursos Alocados");
					for(Resource resource : responsible.getAllocatedResources()) {
						System.out.println("Nome:" + resource.getIdentification() + "\n"
								+ "\tData de Inicio:" + resource.getInitialDate() + "\tData de Termino: " + resource.getFinishDate());
					}
				}else
					System.out.println("Este usuario nao possui nenhum recurso alocado!\n");
			}
		}
	}
	
	public void consultResource(AcademicUnit academicUnit) {
		Scanner input = new Scanner(System.in);
		System.out.println("Informe a identificacao do recurso:");
		String identification = input.nextLine();
		Resource resource = academicUnit.getResourceById(identification);
		if(resource != null) {
			System.out.println("Identificacao: " + resource.getIdentification());
			System.out.println("Data de Inicio: " + resource.getInitialDate()
								+ "\tData de Termino: " + resource.getFinishDate());
			
			System.out.println("Responsaveis pelo Recurso");
			for(User user : resource.getResponsibles())
				System.out.println("Nome: " + user.getName() + "\tE-mail: " + user.getEmail());
			
			System.out.println("Atividades relacionadas ao recurso:");
			for(Allocation allocation : academicUnit.getListAllocations()) {
				for(Resource currentResource : allocation.getListResources()) {
					if(currentResource.equals(resource)) {
						System.out.println("Nome: " + allocation.getActivity().getName() + "\tTipo de Atividade: " + allocation.getActivity().getClass().getName().substring(11));
						System.out.println("Descricao: " + allocation.getActivity().getDescription());
					}
				}
			}
			
		}else {
			System.out.println("Nao foi possivel identificar nenhum recurso com essa identificacao.");
		}
		
	}
	
	public Integer register() {
		Scanner input = new Scanner(System.in);

		System.out.println("CADASTRAR\n\t1.USUARIO\n\t2.ATIVIDADE\n\t3.RECURSO");
		Integer option = Integer.parseInt(input.nextLine());
		
		while(option < 1 || option > 3) {
			System.out.println("Opcao invalida, digite novamente:");
			option = Integer.parseInt(input.nextLine());
		}
		
		return option;
	}
	
	public void registerUser(AcademicUnit academicUnit, User currentUser) {
		Scanner input = new Scanner(System.in);
		Integer option;
		
		if(currentUser.getClass() == Manager.class) {
			System.out.println("\tCADASTRAR USUARIO\n\n");
			System.out.println("Informe o nome de usuario desejado:");
			String username = input.nextLine();
			System.out.println("Informe a senha desejada:");
			String password = input.nextLine();
			System.out.println("Informe o nome completo do usuario: ");
			String name = input.nextLine();
			System.out.println("Informe o email do usuario");
			String email = input.nextLine();
			
			System.out.println("Informe o tipo de usuario: \n\t1.ALUNO DE GRADUACAO\n\t2.ALUNO DE MESTRADO"
					+ "\n\t3.ALUNO DE DOUTORADO\n\t4.PROFESSOR\n\t5.PESQUISADOR\n\t6.ADMINISTRADOR");
			
			option = Integer.parseInt(input.nextLine());
			
			while(option < 1 || option > 6) {
				System.out.println("Opcao invalida, digite novamente:");
				option = Integer.parseInt(input.nextLine());
			}
			
			switch(option) {
				case 1:
					GraduateStudent graduateStudent = new GraduateStudent(username, password, name, email);
					academicUnit.addUser(graduateStudent);
					break;
				case 2:
					MasterStudent masterStudent = new MasterStudent(username, password, name, email);
					academicUnit.addUser(masterStudent);
					break;
				case 3:
					PhDStudent phDStudent = new PhDStudent(username, password, name, email);
					academicUnit.addUser(phDStudent);
					break;
				case 4:
					Teacher teacher = new Teacher(username, password, name, email);
					academicUnit.addUser(teacher);
					break;
				case 5:
					Researcher researcher = new Researcher(username, password, name, email);
					academicUnit.addUser(researcher);
					break;
				case 6:
					Manager manager = new Manager(username, password, name, email);
					academicUnit.addUser(manager);
					break;
			}
		}else {
			System.out.println("Voce precisa ser administrador para cadastrar novos usuarios.");
		}
		
	}

	public void registerActivity(AcademicUnit academicUnit, User currentUser) {
		Scanner input = new Scanner(System.in);
		ArrayList<User> listParticipants = new ArrayList<User>();
		boolean flag = false;
		Integer option;
		SupportMaterial material;
		
		System.out.println("Informe o nome da atividade:");
		String name = input.nextLine();
		System.out.println("Escreva uma breve descricao da atividade");
		String description = input.nextLine();
		
		System.out.println("Informe a opcao do material de apoio: (1 - Codigo Fonte, 2 - Arquivo de Apresentacao)");
		option = Integer.parseInt(input.nextLine());
		
		while(option < 1 || option > 2) {
			System.out.println("Opcao invalida, digite novamente:");
			option = Integer.parseInt(input.nextLine());
		}
		
		if(option == 1) {
			material = SupportMaterial.SOURCE_CODE;
		}else {
			material = SupportMaterial.PRESENTATION_FILE;
		}
		
		String id;
		while(!flag) {
			input = new Scanner(System.in);
			System.out.println("Informe o nome de usuario ou email do participante:");
			id = input.nextLine();
			
			User member = academicUnit.getUserById(id);
			
			if(member != null) {
				if(member.getUsername().equals(currentUser.getUsername())) {
					System.out.println("Voce nao pode se adicionar como participante");
				}else {
					listParticipants.add(member);
				}		
			}else {
				System.out.println("Nao foi possivel encontrar nenhum usuario com a informacao fornecida!");
			}
			
			System.out.println("Deseja continuar adicionando novos participantes?\n\t1.SIM\n\t2.NAO");
			option = Integer.parseInt(input.nextLine());
			
			while(option < 1 || option > 2) {
				System.out.println("Opcao invalida, digite novamente:");
				option = Integer.parseInt(input.nextLine());
			}
			
			if(option == 2) {
				flag = true;
			}
		}
		
		System.out.println("Informe o tipo da atividade:\n\t1.AULA TRADICIONAL\n\t2.APRESENTACAO\n\t3.LABORATORIO");
		option = Integer.parseInt(input.nextLine());
		
		while(option < 1 || option > 3) {
			System.out.println("Opcao invalida, digite novamente:");
			option = Integer.parseInt(input.nextLine());
		}
		
		if(option == 1) {
			TradicionalClass tradicionalClass = new TradicionalClass(name, description, currentUser, listParticipants, material);
			academicUnit.addActivity(tradicionalClass, currentUser);
		}else if(option == 2){
			Presentation presentation = new Presentation(name, description, currentUser, listParticipants, material);
			academicUnit.addActivity(presentation, currentUser);
		}else {
			Laboratory laboratory = new Laboratory(name, description, currentUser, listParticipants, material);
			academicUnit.addActivity(laboratory, currentUser);
		}
		
	}
	
	public void registerResource(AcademicUnit academicUnit, User currentUser) {
		Scanner input = new Scanner(System.in);
		
		if(currentUser.getClass().getSuperclass() == Responsible.class) {
			Responsible responsible = (Responsible) currentUser;
			if(!responsible.checkResourceInProgress()) {
				System.out.println("Informe a identificacao do recurso:");
				String identification = input.nextLine();
				
				System.out.println("Informe o data de inicio do recurso no formato (dd/mm/aaaa):");
				String initialDateStr = input.nextLine();
				
				while(isValidDate(initialDateStr) == null) {
					System.out.println("Formato invalido, informe a data no formato (dd/mm/aaaa):");
					initialDateStr = input.nextLine();
				}
				
				Calendar initialDate = isValidDate(initialDateStr);
				
				System.out.println("Informe a hora de inicio do recurso no formato (HH:MM):");
				String initialHourStr = input.nextLine();
				
				while(!isValidHour(initialHourStr,initialDate)) {
					System.out.println("Formato invalido, informe a hora no formato (HH:MM):");
					initialHourStr = input.nextLine();
				}
				
				System.out.println("Informe o data de termino do recurso no formato (dd/mm/aaaa):");
				String finishDateStr = input.nextLine();
				
				while(isValidDate(finishDateStr) == null) {
					System.out.println("Formato invalido, informe a data no formato (dd/mm/aaaa):");
					finishDateStr = input.nextLine();
				}
				
				Calendar finishDate = isValidDate(finishDateStr);
				
				System.out.println("Informe a hora de termino do recurso no formato (HH:MM):");
				String finishHourStr = input.nextLine();
				
				while(!isValidHour(finishHourStr,finishDate)) {
					System.out.println("Formato invalido, informe a hora no formato (HH:MM):");
					finishHourStr = input.nextLine();
				}
				
				Integer option;
				System.out.println("Informe o tipo do recurso:\n\t1.LABORATORIO\n\t2.AUDITORIO\n\t3.SALA DE AULA\n\t4.PROJETOR");
				option = Integer.parseInt(input.nextLine());
				
				while(option < 1 || option > 4) {
					System.out.println("Opcao invalida, digite novamente:");
					option = Integer.parseInt(input.nextLine());
				}
				
				ArrayList<Responsible> listResponsible = new ArrayList<Responsible>();
				listResponsible.add(responsible);
				String responsibleId = "";
				
				while(responsibleId.length() <= 0) {
					System.out.println("Informe o nome de usuario ou email do responsavel:");
					responsibleId = input.nextLine();
					
					User user = academicUnit.getUserById(responsibleId);
					
					if(user != null) {
						if(user.getClass().getSuperclass() == Responsible.class) {
							boolean flag = true;
							for(Responsible iterator : listResponsible) {
								if(iterator.getUsername().equals(user.getUsername())) {
									System.out.println("Este usuario ja esta como responsavel");
									flag = false;
									break;
								}
							}
							if(flag) {
								listResponsible.add((Responsible) user);
								System.out.println("Usuario adicionado a lista de responsaveis!");
							}
						}else {
							System.out.println("Este usuario nao pode ser responsavel");
						}
						
						System.out.println("Deseja continuar adicionando responsaveis?\n\t1.SIM\n\t2.NAO");
						option = Integer.parseInt(input.nextLine());
						if(option == 1)
							responsibleId = "";
					}else {
						System.out.println("Nao foi possivel encontrar um usuario com as informacoes fornecidas");
					}
				}
				
				switch(option) {
					case 1:
						LaboratoryRoom laboratory = new LaboratoryRoom(identification, initialDate, finishDate,listResponsible);
						academicUnit.addResource(laboratory,responsible);
						break;
					case 2:
						Auditorium auditorium = new Auditorium(identification, initialDate, finishDate, listResponsible);
						academicUnit.addResource(auditorium,responsible);
						break;
					case 3:
						Classroom classroom = new Classroom(identification, initialDate, finishDate, listResponsible);
						academicUnit.addResource(classroom,responsible);
						break;
					case 4:
						Projector projector = new Projector(identification, initialDate, finishDate, listResponsible);
						academicUnit.addResource(projector,responsible);
						break;
				}
				
			}else {
				System.out.println("Ja possui recursos em estado de andamento");
			}
		}else {
			System.out.println("Nao e possivel criar um novo recurso");
		}
		
	}
	
	public void changeAllocationStatus(AcademicUnit academicUnit,User currentUser) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Informe o nome da atividade que voce deseja alterar o status");
		String id = input.nextLine();
		
		for(Allocation allocation : academicUnit.getListAllocations()) {
			if(allocation.getActivity().getName().equals(id)) {
				switch(allocation.getStatus()) {
					case ALLOCATION_PROCESS:
						if(currentUser.getClass() == Manager.class && allocation.getActivity().getName() != null && allocation.getActivity().getListParticipants().size() > 0) {
							allocation.setStatus(AllocationType.ALLOCATED);
							System.out.println("Status da alocacao alterado com sucesso");
						}else {
							System.out.println("Nao foi possivel alterar o status!");
						}
						
						break;
					case ALLOCATED:
						for(Resource resource : allocation.getListResources()) {
							for(User user : resource.getResponsibles()) {
								if(user.getUsername().equals(currentUser.getUsername())) {
									allocation.setStatus(AllocationType.PROGRESS);
									System.out.println("Status da alocacao alterado com sucesso");
									break;
								}
							}
						}
						break;
					case PROGRESS:
						if(currentUser.getClass() == Manager.class && allocation.getActivity().getDescription().length() > 0) {
							allocation.setStatus(AllocationType.ACCOMPLISHED);
							System.out.println("Status da alocacao alterado com sucesso");
						}else {
							System.out.println("Nao foi possivel alterar o status dessa alocacao");
						}
						
						break;
					case ACCOMPLISHED:
						System.out.println("Alocacao ja foi concluida");
						break;
				}
			}
		}

	}
	
	public void addResourceInActivity(AcademicUnit academicUnit, User currentUser) {
		Scanner input = new Scanner(System.in);
		boolean flag = false;
		
		if(currentUser.getClass().getSuperclass() == Responsible.class) {
			Responsible responsible = (Responsible) currentUser;
			
			System.out.println("Informe a identificacao do recurso");
			String identification = input.nextLine();
			for(Resource resource : responsible.getAllocatedResources()) {
				if(resource.getIdentification().equals(identification)) {
					System.out.println("Informe o nome da atividade");
					identification = input.nextLine();
					
					for(Allocation allocation : responsible.getAllocations()) {
						if(allocation.getActivity().getName().equals(identification)) {
							allocation.getListResources().add(resource);
							flag = true;
							break;
						}
					}
				}
				
			}
				
			if(flag) {
				System.out.println("Recurso adicionado na atividade com sucesso!");
			}else {
				System.out.println("Nao foi possivel concluir essa opcao");
			}
		}else {
			System.out.println("Acesso negado!");
		}
		
	}
	
	public void report(AcademicUnit academicUnit) {
		System.out.println("\tRELATORIO\n");
		
		System.out.println("Numero de usuarios: " + academicUnit.getQuantityUsers());
		academicUnit.printQuantityResources();
		System.out.println("Numero total de alocacoes: " + academicUnit.getQuantityAllocations());
		academicUnit.printQuantityActivities();
	}

	
	private Calendar isValidDate(String date) {
		//Data : 24/10/1996
		
		if(date.length() == 10) {
			if(date.charAt(2) == '/' && date.charAt(5) == '/') {
				Integer day = Integer.parseInt(date.substring(0, 2));
				Integer month = Integer.parseInt(date.substring(3, 5));
				Integer year = Integer.parseInt(date.substring(6));
				if(day <= 31 && month <= 12) {
					Calendar newDate = Calendar.getInstance();
					newDate.set(year, month, day);
					
					return newDate;
				}
			}
		}
			
		return null;
	}
	
	private boolean isValidHour(String hourOfDay, Calendar date) {
		//Hora : 12:00
		
		if(hourOfDay.length() == 5) {
			if(hourOfDay.charAt(2) == ':') {
				Integer hour = Integer.parseInt(hourOfDay.substring(0, 2));
				Integer minute = Integer.parseInt(hourOfDay.substring(3));
				
				if(hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59) {
					date.set(Calendar.HOUR, hour);
					date.set(Calendar.MINUTE, minute);
					return true;
				}
			}
		}
		
		return false;
	}

}
