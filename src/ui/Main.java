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
			System.out.println("Exit (0)");
			System.out.println("Create a project (1)");
			System.out.println("Finish stage of a project(2)");
			System.out.println("Register a capsule (3)");
			System.out.println("Approve a capsule(4)");
			System.out.println("Publish a capsule(5)");
			
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
				
			}
		}while(exit == false);
	}//Method menu
	
	private Calendar dateToCalendar(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	/**
	*registerCapsule: Create a capsule
	*/
	public void registerCapsule(){
		String projectName;
		String collaboratorName;
		int phasePosition;
		String description;
		String type = "";
		int forType;
		String learning;
		String[] hashtag = new String[3];
		boolean exit = false;
		String message = "";
		
		System.out.print("Type project's name: ");
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
		System.out.println("Type the knowledge (description):");
		description = reader.nextLine();
		reader.nextLine();
		exit = false;
		do{
			System.out.println("Type of project:");
			System.out.printf("Technical (1)\nManagement (2)\nDomain (3)\nExperiences (4)\n");
			forType = reader.nextInt();
			if (forType>0 && forType<5){
				switch (forType){
					case 1:
						type = "Technical";
						break;
					case 2:
						type = "Management";
						break;
					case 3:
						type = "Domain";
						break;
					case 4:
						type = "Experiences ";
						break;
				}
				exit = true;
			}else{
				System.out.println("Type a correct type of project");
			}
		}while(exit == false);
		System.out.println("Learning of the project:");
		learning = reader.nextLine();
		reader.nextLine();
		System.out.println("Type 3 keywords");
		for (int i = 0; i<3; i++){
			System.out.println("Keyword number " + (i+1));
			hashtag[i]=reader.next();
		}
		message = controller.createCapsule(projectName, collaboratorName, phasePosition, description, type, learning, false, hashtag);
		System.out.println(message);
		
		
		/*createCapsule(String projectName, String collaboratorName, String phaseName, String description, String type, String learning, boolean approve, String[] hashtag)*/
	}
	/**
	*culminatePhase: Deactive and approve a project's phase.
	*/
	public void culminatePhase(){
		String name;
		String message = "";
		
		System.out.print("Type the name of th project: ");
		name = reader.next();
		if (controller.getExistentOfProject(name)==true){
			System.out.println("The Phase will be deactived and approved...");
			message = controller.culminatePhase(name,true,false);
		}else{
			message = "The project does not exist";
		}
		System.out.println(message);
		System.out.println("------------------------------------------------------------");
	}
	
	/**
	*createProject: Ask the user for the project's data.
	*
	*/
	public void createProject() throws ParseException{
		String name;
		String clientName;
		String startDate;
		String finishDate;
		Calendar newStartDate;
		Calendar newFinishDate;
		Date date;
		double budget;
		String message;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
		System.out.print("Type the project's name: ");
		name = reader.next();
		System.out.print("Type the name of the client: ");
		clientName = reader.next();
		System.out.println("Type the project's start date in format <dd/MM/yyyy>:");
		startDate = reader.next();
		date = sdf.parse(startDate);
		newStartDate = dateToCalendar(date);
		System.out.println("Type the project's finish date in format <dd/MM/yyyy>:");
		finishDate = reader.next();
		date = sdf.parse(finishDate);
		newFinishDate = dateToCalendar(date);
		System.out.print("Type the project's budget: ");
		budget = reader.nextDouble();
		message = controller.createProject(name,clientName,newStartDate,newFinishDate,budget);
		System.out.println(message);
		System.out.println("------------------------------------------------------------");
		initPhases(name);
		/*initManagers();*/
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
	*/
	public void initPhases(String name) throws ParseException{
		boolean existProject = controller.getExistentOfProject(name);
		if (existProject==true){
			String startDate;
			String finishDate;
			Calendar newStartDate;
			Calendar newFinishDate;
			Date date;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String message;
			
			System.out.println("<Start Phase>");
			System.out.println("Start date in format <dd/MM/yyyy>:");
			startDate = reader.next();
			date = sdf.parse(startDate);
			newStartDate = dateToCalendar(date);
			System.out.println("Finish date in format <dd/MM/yyyy>:");
			finishDate = reader.next();
			date = sdf.parse(finishDate);
			newFinishDate = dateToCalendar(date);
			message = controller.initPhases(name,"Start",newStartDate,newFinishDate,false,true);
			System.out.println(message);
			System.out.println(" ");
			
			System.out.println("<Analisys Phase>");
			System.out.println("Start date in format <dd/MM/yyyy>:");
			startDate = reader.next();
			date = sdf.parse(startDate);
			newStartDate = dateToCalendar(date);
			System.out.println("Finish date in format <dd/MM/yyyy>:");
			finishDate = reader.next();
			date = sdf.parse(finishDate);
			newFinishDate = dateToCalendar(date);
			message = controller.initPhases(name,"Start",newStartDate,newFinishDate,false,true);
			System.out.println(message);
			System.out.println(" ");
			
			System.out.println("<Design Phase>");
			System.out.println("Start date in format <dd/MM/yyyy>:");
			startDate = reader.next();
			date = sdf.parse(startDate);
			newStartDate = dateToCalendar(date);
			System.out.println("Finish date in format <dd/MM/yyyy>:");
			finishDate = reader.next();
			date = sdf.parse(finishDate);
			newFinishDate = dateToCalendar(date);
			message = controller.initPhases(name,"Start",newStartDate,newFinishDate,false,true);
			System.out.println(message);
			System.out.println(" ");
			
			System.out.println("<Execution Phase>");
			System.out.println("Start date in format <dd/MM/yyyy>:");
			startDate = reader.next();
			date = sdf.parse(startDate);
			newStartDate = dateToCalendar(date);
			System.out.println("Finish date in format <dd/MM/yyyy>:");
			finishDate = reader.next();
			date = sdf.parse(finishDate);
			newFinishDate = dateToCalendar(date);
			message = controller.initPhases(name,"Start",newStartDate,newFinishDate,false,true);
			System.out.println(message);
			System.out.println(" ");
			
			System.out.println("<Close and Following Phase>");
			System.out.println("Start date in format <dd/MM/yyyy>:");
			startDate = reader.next();
			date = sdf.parse(startDate);
			newStartDate = dateToCalendar(date);
			System.out.println("Finish date in format <dd/MM/yyyy>:");
			finishDate = reader.next();
			date = sdf.parse(finishDate);
			newFinishDate = dateToCalendar(date);
			message = controller.initPhases(name,"Start",newStartDate,newFinishDate,false,true);
			System.out.println(message);
			System.out.println(" ");
			
			System.out.println("<Project Control Phase>");
			System.out.println("Start date in format <dd/MM/yyyy>:");
			startDate = reader.next();
			date = sdf.parse(startDate);
			newStartDate = dateToCalendar(date);
			System.out.println("Finish date in format <dd/MM/yyyy>:");
			finishDate = reader.next();
			date = sdf.parse(finishDate);
			newFinishDate = dateToCalendar(date);
			message = controller.initPhases(name,"Start",newStartDate,newFinishDate,false,true);
			System.out.println(message);
			System.out.println(" ");
		}else{
			System.out.println("The system cannot create correctly the phases");
			System.out.println("------------------------------------------------------------");
		}
	}
}