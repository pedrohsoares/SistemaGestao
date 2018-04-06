package activities;

import java.util.ArrayList;

import users.User;
import util.SupportMaterial;

public class Laboratory extends Activity{
	public Laboratory(String name, String description, User userResponsible, ArrayList<User> listParticipants,
			SupportMaterial material) {
		super(name, description, userResponsible, listParticipants, material);
	}

}
