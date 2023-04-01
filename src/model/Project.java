package model;

import java.util.Calendar;
/**
*This class represents a project.
*/
public class Project{
	
	private Calendar calendar;
	/**
	*Name of the project.
	*/
	private String name;
	/**
	*Name of the client
	*/
	private String clientName;
	/**
	*Start date of the project (planned).
	*/
	private Calendar startDatePlanned;
	/**
	*Finish date of the project (planned).
	*/
	private Calendar finishDatePlanned;
	/**
	*Start date of the project (real).
	*/
	private Calendar startDateReal;
	/**
	*Finish date of the project (real).
	*/
	private Calendar finishDateReal;
	/**
	*Budget of the project.
	*/
	private double budget;
	
	/**
	*Size of the array of phases.
	*/
	public static final int SIZE_PHASE = 6;
	/**
	*Array of phases.
	*/
	private Phase[] phases;
	
	/**
	*Size of the array of managers.
	*/
	public static final int SIZE_MANAGER = 2;
	/**
	*Array of managers.
	*/
	private Manager[] managers;
	
	public Project(String name, String clientName, Calendar startDatePlanned, Calendar finishDatePlanned, double budget){
		this.name = name;
		this.clientName = clientName;
		this.startDatePlanned = startDatePlanned;
		this.finishDatePlanned = finishDatePlanned;
		this.startDateReal = calendar;
		this.budget = budget;
		this.phases = new Phase[SIZE_PHASE];
		this.calendar = Calendar.getInstance();
		/*this.manager = new Manager[SIZE_MANAGER];*/
	}
	
	private int getFirstValidPosition(){
		boolean exit = false;
		int position = -1;
		
		for (int i = 0; i<SIZE_PHASE && exit==false; i++){
			if (phases[i]==null){
				position = i;
				exit = true;
			}
		}
		return position;
	}
	
	/**
	*addPhase: Create the phases' array.
	*@param phase Object Phase
	*@return message Status creation
	*/
	public String addPhase(Phase phase){
		int position = getFirstValidPosition();
		String message = "";
		if (position==-1){
			message = "There's no free space for phase";
			return message;
		}else{
			phases[position]=phase;
			message = "The phase has been created";
			return message;
		}
	}
	
	/**
	*culminatePhase
	*/
	public String culminatePhase(boolean approve, boolean active){
		int position = getPositionActivePhase();
		String message;
		
		if (position == -1){
			message = "All the phases are approved!!";
		}else{
			phases[position].setApprove(approve);
			phases[position].setActive(active);
			if (position==(SIZE_PHASE-1)){
				message = "The phase has been deactived and approved";
				//terminar proyecto!!!
			}else{
				message = "The phase has been deactived and approved";
			}
		}
		return message;
	}
	
	private int getPositionActivePhase(){
		int position = -1;
		boolean exit = false;
		for (int i = 0; i<SIZE_PHASE && exit == false; i++){
			if (phases[i].getActive()==true){
				position = i;
				exit = true;
			}
		}
		return position;
	}
	
	/**
	*getProjectName: Return project's name.
	*@return name Name of project.
	*/
	public String getProjectName(){
		return name;
	}
	
	/**
	*setFinishDateReal: Save the finish real date.
	*/
	public void setFinishDateReal(){
		this.finishDateReal = calendar;
	}
	
	/*public String listPhases(){
		String message = "";
		for (int i = 0; i<SIZE_PHASE; i++){
			message += phases[i].getName() + " ";
		}
		return message;
	}*/
}