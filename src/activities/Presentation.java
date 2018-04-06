package activities;

import java.util.ArrayList;

import users.User;
import util.SupportMaterial;

public class Presentation extends Activity{

	public Presentation(String name, String description, User userResponsible, ArrayList<User> listParticipants,
			SupportMaterial material) {
		super(name, description, userResponsible, listParticipants, material);
	}
	
}
