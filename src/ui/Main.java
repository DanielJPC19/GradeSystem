package ui;

import model.GradeSystem;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
/**
*Is where the user interacts with the software, user interface (UI).
*/
public class Main{
	
	private Scanner reader;
	/**
	*Represents the connection between Main and GradeSystem.
	*/
	private GradeSystem controller;
	/**
	*Main: Initialize the variables.
	*
	*/
	public Main(){
		reader = new Scanner(System.in);
		controller = new GradeSystem();
	}
	
	public static void main(String[] args){
		Main view = new Main();
		view.menu();
		
		view.reader.close();
	}
	
	/**
	*menu: Show all the possible options to select and redirects to it.
	*
	*/
	public void menu(){
		int option;
		boolean exit = false;
		
		do{
			System.out.printf("\n<-<-<-<-<- Welcome ->->->->->\n");
			System.out.println("Exit (0)");
			System.out.println("Create a project (1)");
			System.out.println("Finish stage of a project (2)");
			System.out.println("Register a capsule (3)");
			System.out.println("Approve a capsule (4)");
			System.out.println("Publish a capsule (5)");
			System.out.println("List capsules by type (6)");
			System.out.println("List the learnings in a phase (7)");
			System.out.println("Show the project that has more capsules (8)");
			System.out.println("Count the capsules written by a collaborator (9)");
			System.out.println("Search capsules that are approved and published (10)");
			
			option = reader.nextInt();
			switch (option){
				case 0:
					System.out.println("Exit.");
					exit = true;
					break;
				case 1:
					tryCatchCreateProject();
					exit = false;
					break;
				case 2:
					culminatePhase();
					exit = false;
					break;
				case 3:
					registerCapsule();
					exit = false;
					break;
				case 4:
					approveCapsule();
					exit = false;
					break;
				case 5:
					publishCapsule();
					exit = false;
					break;
				case 6:
					listCapsulesByType();
					exit = false;
					break;
				case 7:
					listLearningsByPhase();
					exit = false;
					break;
				case 8:
					showProjectWithMoreCapsules();
					exit = false;
					break;
				case 9:
					countCollaboratorCapsules();
					exit = false;
					break;
				case 10:
					approvedAndPublishedCapsules();
					exit = false;
					break;
				default:
					System.out.println("The option does not exist, try again.");
			}
		}while(exit == false);
	}//Method menu
	
	private Calendar dateToCalendar(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	/**
	*approvedAndPublishedCapsules: Search with keywords for those capsules that are approved and published.
	*/
	public void approvedAndPublishedCapsules(){
		String message = "";
		String projectName;
		String[] keywords = new String[3];
		
		System.out.print("\nType the project's name: ");
		projectName = reader.next();
		for (int i = 0; i<3; i++){
			System.out.print("Keyword No " + (i+1) + ": ");
			keywords[i] = reader.next();
		}
		
		message = controller.approvedAndPublishedCapsules(projectName,keywords);
		System.out.println(message);
	}
	/**
	*countCollaboratorCapsules: Show the number of capsules that the collaborator has written.
	*/
	public void countCollaboratorCapsules(){
		String message = "";
		String projectName;
		String collaboratorName;
		
		System.out.print("\nType the project's name: ");
		projectName = reader.next();
		System.out.print("Type the collaborator's name: ");
		collaboratorName = reader.next();
		
		message = controller.collaboratorCapsules(projectName, collaboratorName);
		System.out.println(message);
	}
	
	/**
	*showProjectWithMoreCapsules: Show the project that have more capsules
	*/
	public void showProjectWithMoreCapsules(){
		String message = "";
		String name;
		
		message = controller.showProjectWithMoreCapsules();
		System.out.println("\n" + message);
	}
	/**
	*listLearningsByPhase: Show all the capsules' learnings of a phase.
	*/
	public void listLearningsByPhase(){
		String message = "";
		int phase;
		String projectName;
		boolean exit = false;
		
		System.out.print("\nType the project's name: ");
		projectName = reader.next();
		do{
			System.out.println("\nSelect the phase:");
			System.out.printf("Start (0)\nAnalisys (1)\nDesign (2)\nExecution (3)\nClose and Following (4)\nProject Control (5)\n");
			System.out.print("Option: ");
			phase = reader.nextInt();
			if (phase<0 || phase>5){
				System.out.println("The option typed does not exist, type a correct option.");
				exit = false;
			}else{
				message = controller.listLearningsByPhase(projectName,phase);
				exit = true;
			}
		}while(exit==false);
		System.out.println(message);
	}
	/**
	*listCapsulesByType: Show all the capsules that have a specific type.
	*/
	public void listCapsulesByType(){
		String message = "";
		boolean exit = false;
		int type = 0;
		int option;
		String projectName;
		
		System.out.print("\nType the project's name: ");
		projectName = reader.next();
		do{
			System.out.println("\nSelect the type of capsule:");
			System.out.printf("Technical (1)\nManagement (2)\nDomain (3)\nExperiences (4)\n");
			System.out.print("Option: ");
			option = reader.nextInt();
			if (option<1 || option >4){
				System.out.println("The option typed does not exist, type a correct option.");
				exit = false;
			}else{
				switch(option){
					case 1:
						type = 1;
						exit = true;
						break;
					case 2:
						type = 2;
						exit = true;
						break;
					case 3:
						type = 3;
						exit = true;
						break;
					case 4:
						type = 4;
						exit = true;
						break;
				}
			}
		}while(exit==false);
		message = controller.listCapsulesByType(projectName,type);
		System.out.println(message);
	}
	
	/**
	*publishCapsule: Post a capsule with a url.
	*/
	public void publishCapsule(){
		String message = "";
		String projectName;
		System.out.print("\nType the project's name: ");
		projectName = reader.next();
		message = controller.publishCapsule(projectName);
		System.out.println(message);
	}
	
	/**
	*approveCapsule: Approves a class.
	*/
	public void approveCapsule(){
		String nameProject;
		String nameManager;
		String idCapsule;
		String message = "";
		
		System.out.print("\nType the project's name: ");
		nameProject = reader.next();
		System.out.print("Type the manager's name: ");
		nameManager = reader.next();
		System.out.print("Type the capsule's id: ");
		idCapsule = reader.next();
		message = controller.approveCapsule(nameProject,nameManager,idCapsule);
		System.out.println(message);
	}
	
	/**
	*registerCapsule: Create a capsule
	*/
	public void registerCapsule(){
		String projectName;
		String collaboratorName;
		int phasePosition;
		String description;
		int type = 0;
		int forType;
		String learning;
		String[] hashtag = new String[3];
		boolean exit = false;
		String message = "";
		
		System.out.print("\nType project's name: ");
		projectName = reader.next();
		System.out.print("Type collaborator's name: ");
		collaboratorName = reader.next();
		do{
			System.out.println("At what phase are you going to register the capsule?");
			System.out.printf("Start Phase (1)\nAnalisys Phase (2)\nDesign Phase (3)\nExecution Phase (4)\nClose and Following Phase (5)\nProject Control Phase (6)\n");
			phasePosition = reader.nextInt();
			if(phasePosition>0){
				exit = true;
			}else{
				System.out.println("Type a correct Phase");
			}
		}while(exit == false);
		reader.nextLine();
		System.out.println("Type the knowledge (description):");
		description = reader.nextLine();
		exit = false;
		
		do{
			System.out.println("Type of project:");
			System.out.printf("Technical (1)\nManagement (2)\nDomain (3)\nExperiences (4)\n");
			forType = reader.nextInt();
			if (forType>0 && forType<5){
				switch (forType){
					case 1:
						type = 1;
						break;
					case 2:
						type = 2;
						break;
					case 3:
						type = 3;
						break;
					case 4:
						type = 4;
						break;
				}
				exit = true;
			}else{
				System.out.println("Type a correct type of project");
			}
		}while(exit == false);
		reader.nextLine();
		System.out.println("Learning of the project:");
		learning = reader.nextLine();
		System.out.println("Type 3 keywords");
		for (int i = 0; i<3; i++){
			System.out.println("Keyword number " + (i+1));
			hashtag[i]=reader.next();
		}
		message = controller.createCapsule(projectName, collaboratorName, phasePosition, description, type, learning, false, hashtag);
		System.out.println("> " + message);
	}
	/**
	*culminatePhase: Deactive and approve a project's phase.
	*/
	public void culminatePhase(){
		String name;
		int month;
		String message = "";
		
		System.out.print("\nType the name of the project: ");
		name = reader.next();
		System.out.print("How long did the phase last?: ");
		month = reader.nextInt();
		if (controller.getExistentOfProject(name)==true){
			System.out.println("The Phase will be deactived and approved...");
			message = controller.culminatePhase(name,true,false,month);
		}else{
			message = "The project does not exist";
		}
		System.out.println(message);
		System.out.println("------------------------------------------------------------");
	}
	
	/**
	*createProject: Ask the user for the project's data.
	*@throws ParseException Exception to convert String to date.
	*/
	public void createProject() throws ParseException{
		String name;
		String clientName;
		String startDate;
		Calendar newStartDate;
		Date date;
		double budget;
		String message;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String managerName;
		String managerPhone;
				
		System.out.print("\nType the project's name: ");
		name = reader.next();
		System.out.print("Type the name of the client: ");
		clientName = reader.next();
		System.out.println("Type the project's start date in format <dd/MM/yyyy>:");
		startDate = reader.next();
		date = sdf.parse(startDate);
		newStartDate = dateToCalendar(date);
		System.out.print("Type the project's budget: ");
		budget = reader.nextDouble();
		message = controller.createProject(name,clientName,newStartDate,budget);
		System.out.println(message + "\n");
		System.out.print("Type the name of client's manager: ");
		managerName = reader.next();
		System.out.print("Type the phone: ");
		managerPhone = reader.next();
		message = controller.registerManager(name,0,managerName,managerPhone);
		System.out.println("> " + message);
		System.out.print("Type the name of green's manager: ");
		managerName = reader.next();
		System.out.print("Type the phone: ");
		managerPhone = reader.next();
		message = controller.registerManager(name,1,managerName,managerPhone);
		System.out.println("> " + message);
		
		System.out.println("------------------------------------------------------------");
		initPhases(name, newStartDate);
		controller.setFinishDatePlanned(name);
	}
	
	/**
	*tryCatchCreateProject: Checks for an exception and displays error message.
	*
	*/
	public void tryCatchCreateProject(){
		try{
			createProject();
		}catch (ParseException exe){
			System.out.println("The date does not have the format");
			System.out.println("The project has not been created");
			System.out.println("------------------------------------------------------------");
		}
	}
	
	/**
	*initPhases: Initialize the six phases
	*@param name Name of project
	*@param startDate Start date of the project and the start phase.
	*@throws ParseException Exception to convert String to date.
	*/
	public void initPhases(String name, Calendar startDate) throws ParseException{
		boolean existProject = controller.getExistentOfProject(name);
		if (existProject==true){
			Calendar newStartDate;
			Calendar newFinishDate;
			Date date;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String message;
			int month;
			
			System.out.println("<Start Phase>");
			System.out.print("Start date in format <dd/MM/yyyy>: ");
			newStartDate = startDate;
			System.out.println(sdf.format(newStartDate.getTime()));
			System.out.println("Duration in months: ");
			month = reader.nextInt();
			newFinishDate = newStartDate;
			newFinishDate.add(Calendar.MONTH, month);
			message = controller.initPhases(name,"Start",newStartDate,newFinishDate,false,true,0,month);
			System.out.println(message);
			System.out.println(" ");
			
			System.out.println("<Analisys Phase>");
			System.out.print("Start date in format <dd/MM/yyyy>: ");
			newStartDate = newFinishDate;
			System.out.println(sdf.format(newStartDate.getTime()));
			System.out.println("Duration in months: ");
			month = reader.nextInt();
			newFinishDate = newStartDate;
			newFinishDate.add(Calendar.MONTH, month);
			message = controller.initPhases(name,"Analisys",newStartDate,newFinishDate,false,true,1,month);
			System.out.println(message);
			System.out.println(" ");
			
			System.out.println("<Design Phase>");
			System.out.print("Start date in format <dd/MM/yyyy>: ");
			newStartDate = newFinishDate;
			System.out.println(sdf.format(newStartDate.getTime()));
			System.out.println("Duration in months: ");
			month = reader.nextInt();
			newFinishDate = newStartDate;
			newFinishDate.add(Calendar.MONTH, month);
			message = controller.initPhases(name,"Design",newStartDate,newFinishDate,false,true,2,month);
			System.out.println(message);
			System.out.println(" ");
			
			System.out.println("<Execution Phase>");
			System.out.print("Start date in format <dd/MM/yyyy>: ");
			newStartDate = newFinishDate;
			System.out.println(sdf.format(newStartDate.getTime()));
			System.out.println("Duration in months: ");
			month = reader.nextInt();
			newFinishDate = newStartDate;
			newFinishDate.add(Calendar.MONTH, month);
			message = controller.initPhases(name,"Execution",newStartDate,newFinishDate,false,true,3,month);
			System.out.println(message);
			System.out.println(" ");
			
			System.out.println("<Close and Following Phase>");
			System.out.print("Start date in format <dd/MM/yyyy>: ");
			newStartDate = newFinishDate;
			System.out.println(sdf.format(newStartDate.getTime()));
			System.out.println("Duration in months: ");
			month = reader.nextInt();
			newFinishDate = newStartDate;
			newFinishDate.add(Calendar.MONTH, month);
			message = controller.initPhases(name,"Close and Following",newStartDate,newFinishDate,false,true,4,month);
			System.out.println(message);
			System.out.println(" ");
			
			System.out.println("<Project Control Phase>");
			System.out.print("Start date in format <dd/MM/yyyy>: ");
			newStartDate = newFinishDate;
			System.out.println(sdf.format(newStartDate.getTime()));
			System.out.println("Duration in months: ");
			month = reader.nextInt();
			newFinishDate = newStartDate;
			newFinishDate.add(Calendar.MONTH, month);
			message = controller.initPhases(name,"Project Control",newStartDate,newFinishDate,false,true,5,month);
			System.out.println(message);
			System.out.println(" ");
		}else{
			System.out.println("The system cannot create correctly the phases");
			System.out.println("------------------------------------------------------------");
		}
	}
}