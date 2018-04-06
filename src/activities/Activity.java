package activities;

import java.util.ArrayList;
import users.User;
import util.SupportMaterial;

public abstract class Activity {
	private String name;
	private String description;
	private User userResponsible;
	private ArrayList<User> listParticipants;
	private SupportMaterial material;
	
	public Activity(String name, String description, User userResponsible, ArrayList<User> listParticipants, SupportMaterial material) {
		this.name = name;
		this.description = description;
		this.userResponsible = userResponsible;
		this.listParticipants = listParticipants;
		this.material = material;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUserResponsible() {
		return userResponsible;
	}
	public void setUserResponsible(User userResponsible) {
		this.userResponsible = userResponsible;
	}
	public ArrayList<User> getListParticipants() {
		return listParticipants;
	}
	public void setListParticipants(ArrayList<User> listParticipants) {
		this.listParticipants = listParticipants;
	}
	public SupportMaterial getMaterial() {
		return material;
	}
	public void setMaterial(SupportMaterial material) {
		this.material = material;
	}
	
	public void addParticipant(User participant) {
		if(!participant.equals(userResponsible))
			this.listParticipants.add(participant);
		else
			System.out.println("Nao e possivel adicionar este usuario como participante");
	}
}
