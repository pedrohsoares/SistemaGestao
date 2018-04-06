package sistemaGestao;

import java.util.Scanner;

import users.User;

public class Main {
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Menu menu = new Menu();
		AcademicUnit academicUnit = menu.createDefaultAcademicUnit("UFAL");
		User activeUser = null;
		Integer option;
		
		activeUser = menu.login(academicUnit);
		while(activeUser != null) {
			Integer result;
			
			option = menu.mainMenu(activeUser);
			switch(option) {
				case 0:
					result = menu.logout();
					if(result == 1)
						activeUser = menu.login(academicUnit);
					else
						activeUser = null;
					break;
				case 1:
					result = menu.consult();
					switch(result) {
						case 1:
							menu.consultUser(academicUnit);
							break;
						case 2:
							menu.consultResource(academicUnit);
							break;
					}
					break;
				case 2:
					result = menu.register();
					switch(result) {
						case 1:
							menu.registerUser(academicUnit,activeUser);
							break;
						case 2:
							menu.registerActivity(academicUnit,activeUser);
							break;
						case 3:
							menu.registerResource(academicUnit,activeUser);
							break;
					}
					break;
				case 3:
					menu.changeAllocationStatus(academicUnit, activeUser);
					break;
				case 4:
					menu.addResourceInActivity(academicUnit, activeUser);
					break;
				case 5:
					menu.report(academicUnit);
					break;	
			}
		}
		
	}

}
