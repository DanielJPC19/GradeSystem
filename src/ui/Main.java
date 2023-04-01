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
				
			}
		}while(exit == false);
	}//Method menu
	
	private Calendar dateToCalendar(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
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