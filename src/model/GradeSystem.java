package model;

import java.util.Calendar;
/**
*Controller class
*/
public class GradeSystem{
	
	/**
	*Size of the array of projects
	*
	*/
	public static final int SIZE_PROJECT = 10;
	/**
	*Array of projects
	*
	*/
	private Project[] projects;
	
	public GradeSystem(){
		projects = new Project[SIZE_PROJECT];
	}
	
	/**
	*createProject: Create a project and add it in an array of projects.
	*@param name Name of project.
	*@param startDate Start date of project.
	*@param finishDate Finish date of project.
	*@param budget Budget of the project.
	*@return message Status of creation.
	*/
	public String createProject(String name, String clientName, Calendar startDate, Calendar finishDate, double budget){
		Project project = new Project(name, clientName, startDate, finishDate,budget);
		boolean exit = false;
		String message = "The project has not been created";
		
		for (int i = 0; i<SIZE_PROJECT && exit == false; i++){
			if (projects[i]==null){
				projects[i]=project;
				exit = true;
				message = "The project has been created";
			}
		}
		return message;
	}
	
	/**
	*getExistentOfProject: Search in all the array if the project exist, then return its existence.
	*@param name Nmae of the project.
	*@return exist Existent of a project.
	*/
	public boolean getExistentOfProject(String name){
		boolean exist = false;
		
		for (int i = 0; i<SIZE_PROJECT && exist==false; i++){
			if ((projects[i].getProjectName()).equalsIgnoreCase(name)){
				exist = true;
			}
		}
		return exist;
	}
	
	/**
	*getProjectPosition: Return the position of the project
	*@param name Name of the project
	*@return position Position in projects' array
	*/
	public int getProjectPosition(String name){
		int position = -1;
		boolean exit = false;
		
		for (int i = 0; i<SIZE_PROJECT && exit==false; i++){
			if ((projects[i].getProjectName()).equalsIgnoreCase(name)){
				position = i;
				exit = true;
			}
		}
		return position;
	}
	
	/**
	*initPhases: Create an object and send it to project class.
	*@param projectName Name of project
	*@param name Name of phase
	*@param startDate Start date of phase
	*@param finishDate Finish date of phase
	*@param approve Status of approvation of phase
	*@param active Phase activation status
	*@return message Message status
	*/
	public String initPhases(String projectName,String name, Calendar startDate, Calendar finishDate, boolean approve, boolean active){
		String message = "The project does not exist";
		int position = getProjectPosition(projectName);
		
		Phase phase = new Phase(name, startDate, finishDate,approve, active);
		message = projects[position].addPhase(phase);
		return message;
	}
	
	/**
	*culminatePhase: End a phase
	*/
	public String culminatePhase(String name, boolean approve, boolean active){
		int position = getProjectPosition(name);
		String message = projects[position].culminatePhase(approve,active);
		return message;
	}
	
	/**
	*
	*/
	public String createCapsule(String projectName, String collaboratorName, int phasePosition/*, String id*/, String description, String type, String learning, boolean approve, String[] hashtag){
		String message = "";
		boolean existProject = getExistentOfProject(projectName);
		
		if (existProject==false){
			message = "The project does not exist";
		}else{
			int position = getProjectPosition(projectName);
			String id = "p"+position;
			KnowledgeCapsule capsule = new KnowledgeCapsule(collaboratorName, description, type, learning, approve, hashtag);
			message = projects[position].createCapsule(phasePosition, id, capsule);
		}
		return message;
	}
	
	/**
	*listPhasesByProject: Get 
	*@return message Message with all the phases
	*/
	/*public String listPhasesByProject(String name){
		int position = getProjectPosition(name);
		String message = projects[position].listPhases();
		return message;
	}*/
}