package sistemaGestao;

import java.util.ArrayList;

import activities.*;
import allocations.*;
import resources.Resource;
import users.*;
import users.User;

public class AcademicUnit {
	private String name;
	private ArrayList<User> listUsers;
	private ArrayList<Allocation> listAllocations;
	private ArrayList<Resource> listResources;
	private ArrayList<Activity> listActivities;
	
	
	public AcademicUnit(String name) {
		this.name = name;
		this.listUsers = new ArrayList<User>();
		this.listAllocations = new ArrayList<Allocation>();
		this.listResources = new ArrayList<Resource>();
		this.listActivities = new ArrayList<Activity>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(ArrayList<User> listUsers) {
		this.listUsers = listUsers;
	}

	public ArrayList<Allocation> getListAllocations() {
		return listAllocations;
	}

	public void setListAllocations(ArrayList<Allocation> listAllocations) {
		this.listAllocations = listAllocations;
	}

	public ArrayList<Resource> getListResources() {
		return listResources;
	}

	public void setListResources(ArrayList<Resource> listResources) {
		this.listResources = listResources;
	}

	public ArrayList<Activity> getListActivities() {
		return listActivities;
	}

	public void setListActivities(ArrayList<Activity> listActivities) {
		this.listActivities = listActivities;
	}
	
	public void addUser(User newUser) {
		for(User user : this.listUsers) {
			if(user.getUsername().equals(newUser.getUsername()) || user.getEmail().equals(newUser.getEmail())) {
				System.out.println("Ja possui um usuario com esse nome de usuario ou email!");
				return;
			}
		}
		
		this.listUsers.add(newUser);
		System.out.println("Usuario cadastrado com sucesso!");
	}
	
	public void addActivity(Activity newActivity,User currentUser) {
		if(currentUser.getClass().getSuperclass() == Responsible.class) {
			for(Activity activity : this.listActivities) {
				if(activity.getName().equals(newActivity.getName())) {
					System.out.println("Ja possui uma atividade com este nome!");
					return;
				}
			}
			
			Responsible responsible = (Responsible) currentUser;
			Allocation allocation = new Allocation(newActivity);
			
			responsible.getActivities().add(newActivity);
			responsible.getAllocations().add(allocation);
			this.listActivities.add(newActivity);
			this.listAllocations.add(allocation);
			
			System.out.println("Atividade cadastrada com sucesso!");
		}else {
			System.out.println("Nao e possivel cadastrar uma atividade sendo aluno");
		}
	}
	
	public void addResource(Resource resource, Responsible currentUser) {
		for(Resource currentResource : this.listResources) {
			if(currentResource.getIdentification().equals(resource.getIdentification())) {
				System.out.println("Nao e possivel criar um recurso com essa identificacao");
				return;
			}
		}
		
		this.listResources.add(resource);
		currentUser.getAllocatedResources().add(resource);
		System.out.println("Recurso cadastrado com sucesso!");
	}

	public User getUserById(String id) {
		for(User user : this.listUsers)
			if(user.getUsername().equals(id) || user.getEmail().equals(id))
				return user;
		return null;
	}
	
	public User getUserByLogin(String username, String password) {
		for(User user : this.listUsers){
			if(user.getUsername().equals(username)) {
				if(user.getPassword().equals(password)) {
					return user;
				}
				break;
			}
		}
		
		System.out.println("Nome de usuario ou senha incorreta!");
		return null;
	}
	
	public Resource getResourceById(String id) {
		for(Resource resource : this.listResources) 
			if(resource.getIdentification().equals(id))
				return resource;
		return null;
	}
	
	public void printQuantityResources() {
		Integer allocationProcess = 0;
		Integer allocated = 0;
		Integer progress = 0;
		Integer accomplished = 0;
		
		for(Allocation allocation : this.listAllocations) {
			if(allocation.getStatus() == AllocationType.ALLOCATION_PROCESS)
				allocationProcess += allocation.getListResources().size();
			else if(allocation.getStatus() == AllocationType.ALLOCATED)
				allocated += allocation.getListResources().size();
			else if(allocation.getStatus() == AllocationType.PROGRESS)
				progress += allocation.getListResources().size();
			else
				accomplished += allocation.getListResources().size();
		}
		
		System.out.println("Numero de recursos em processo de alocacao: " + allocationProcess);
		System.out.println("Numero de recursos alocados: " + allocated);
		System.out.println("Numero de recursos em andamento: " + progress);
		System.out.println("Numero de recursos concluidos: " + accomplished);
	}
	
	public void printQuantityActivities() {
		Integer tradicionalClass = 0;
		Integer presentation = 0;
		Integer laboratory = 0;
		
		for(Activity activity : this.listActivities) {
			if(activity.getClass() == TradicionalClass.class)
				tradicionalClass++;
			else if(activity.getClass() == Presentation.class)
				presentation++;
			else
				laboratory++;
		}
		
		System.out.println("Numero de apresentacoes: " + presentation);
		System.out.println("Numero de aulas tradicionais: " + tradicionalClass);
		System.out.println("Numero de laboratorios: " + laboratory);
	}
	
	public int getQuantityUsers() {
		return this.listUsers.size();
	}
	
	public int getQuantityAllocations() {
		return this.listAllocations.size();
	}

	
}
