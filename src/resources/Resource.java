package resources;

import java.util.ArrayList;
import java.util.Calendar;

import users.Responsible;

public class Resource {
	private String identification;
	private Calendar initialDate;
	private Calendar finishDate;
	private ArrayList<Responsible> responsibles;
	
	public Resource(String identification, Calendar initialDate, Calendar finishDate, ArrayList<Responsible> responsibles) {
		this.identification = identification;
		this.initialDate = initialDate;
		this.finishDate = finishDate;
		this.responsibles = responsibles;
	}
	
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public Calendar getInitialDate() {
		return initialDate;
	}
	public void setInitialDate(Calendar initialDate) {
		this.initialDate = initialDate;
	}
	public Calendar getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Calendar finishDate) {
		this.finishDate = finishDate;
	}
	public ArrayList<Responsible> getResponsibles() {
		return responsibles;
	}
	public void setResponsibles(ArrayList<Responsible> responsibles) {
		this.responsibles = responsibles;
	}
	
	
	
}
