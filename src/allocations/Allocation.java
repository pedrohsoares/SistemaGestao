package allocations;

import java.util.ArrayList;

import activities.Activity;
import resources.Resource;

public class Allocation {
	private Activity activity;
	private ArrayList<Resource> listResources;
	private AllocationType status;
	
	public Allocation(Activity activity) {
		this.activity = activity;
		this.listResources = new ArrayList<Resource>();
		this.status = AllocationType.ALLOCATION_PROCESS;
	}
	
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public ArrayList<Resource> getListResources() {
		return listResources;
	}
	public void setListResources(ArrayList<Resource> listResources) {
		this.listResources = listResources;
	}
	public AllocationType getStatus() {
		return status;
	}
	public void setStatus(AllocationType status) {
		this.status = status;
	}
	
	
	
}
