package users;

import java.util.ArrayList;

import activities.Activity;
import allocations.Allocation;
import allocations.AllocationType;
import resources.Resource;

public abstract class Responsible extends User{
	private ArrayList<Resource> allocatedResources;
	private ArrayList<Allocation> allocations;
	private ArrayList<Activity> activities;
	
	public Responsible(String username, String password, String name, String email) {
		super(username,password,name,email);
		this.allocatedResources = new ArrayList<Resource>();
		this.allocations = new ArrayList<Allocation>();
		this.activities = new ArrayList<Activity>();
	}
	
	public ArrayList<Resource> getAllocatedResources() {
		return allocatedResources;
	}
	
	public void setAllocatedResources(ArrayList<Resource> allocatedResources) {
		this.allocatedResources = allocatedResources;
	}
	
	public ArrayList<Allocation> getAllocations() {
		return allocations;
	}
	
	public void setAllocations(ArrayList<Allocation> allocations) {
		this.allocations = allocations;
	}
	
	public ArrayList<Activity> getActivities() {
		return activities;
	}

	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}
	
	public boolean checkResourceInProgress() {
		for(Allocation allocation : this.allocations) {
			if(allocation.getStatus() == AllocationType.PROGRESS) {
				return true;
			}
		}
		
		return false;
	}
	
}
