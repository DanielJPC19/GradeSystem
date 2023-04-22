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
	*@param clientName Name of the client.
	*@param startDate Start date of project.
	*@param budget Budget of the project.
	*@return message Status of creation.
	*/
	public String createProject(String name, String clientName, Calendar startDate, double budget){
		Project project = new Project(name, clientName, startDate, budget);
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
	*setFinishDatePlanned: Set the finish date of the project, based in the array of months.
	*@param name Name of the project.
	*/
	public void setFinishDatePlanned(String name){
		int position = getProjectPosition(name);
		projects[position].setFinishDatePlanned();
	}
	/**
	*registerManager: Create a manager.
	*@param projectName Name of the project.
	*@param i Position of the manager's array.
	*@param name Name of the manager.
	*@param phone Phone of the manager.
	*@return message Status of the creation.
	*/
	public String registerManager(String projectName,int i, String name, String phone){
		int position = getProjectPosition(projectName);
		String message;
		Manager manager = new Manager(name,phone);
		message = projects[position].registerManager(i,manager);
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
			if (projects[i]!=null){
				if ((projects[i].getProjectName()).equalsIgnoreCase(name)){
					exist = true;
				}
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
			if (projects[i]!=null){
				if ((projects[i].getProjectName()).equalsIgnoreCase(name)){
					position = i;
					exit = true;
				}
			}
		}
		return position;
	}
	/**
	*approvedAndPublishedCapsules: Search and returns those capsules that are approved and published. 
	*@param projectName Name of the project.
	*@param keywords Array with keywords to search.
	*@return message Status of the searching.
	*/
	public String approvedAndPublishedCapsules(String projectName, String[] keywords){
		String message = "";
		
		int position = getProjectPosition(projectName);
		if (position == -1){
			message = "The project does not exist.";
		}else{
			message = projects[position].approvedAndPublishedCapsules(keywords);
		}
		
		if (message == ""){
			message = "There's no any capsule that are approved, published or have that keywords";
		}
		return message;
	}
	/**
	*listLearningsByPhase: Show all the learnings of a capsule in a phase.
	*@param projectName Name of the project.
	*@param phase Position of the phase in the array phases.
	*@return message Status of the showing.
	*/
	public String listLearningsByPhase(String projectName, int phase){
		String message = "";
		int position = getProjectPosition(projectName);
		
		if (position == -1){
			message = "The project does not exist";
		}else{
			message = projects[position].listLearnings(phase);
		}
		if (message == ""){
			message = "There's no any capsule.";
		}
		return message;
	}
	
	/**
	*collaboratorCapsules: Return the number of capsules that the collaborator has written.
	*@param projectName Name of the project.
	*@param collaboratorName Name of the collaborator.
	*@return message Status of the searching.
	*/
	public String collaboratorCapsules(String projectName, String collaboratorName){
		int position = getProjectPosition(projectName);
		String message = "";
		
		if (position==-1){
			message = "The project does not exist";
		}else{
			message = projects[position].collaboratorCapsules(collaboratorName);
		}
		if (message == ""){
			message = "The collaborator has not written any capsule.";
		}
		return message;
	}
	/**
	*showProjectWithMoreCapsules: Show the project that has more capsules.
	*@return message Status of the searching.
	*/
	public String showProjectWithMoreCapsules(){
		int[] numberCapsules = new int[SIZE_PROJECT];
		int projectMoreCapsules = -1;
		String message = "";
		
		for (int i = 0; i<SIZE_PROJECT; i++){
			numberCapsules[i] = -1;
		}
		
		for (int i = 0; i<SIZE_PROJECT; i++){
			if (projects[i]!=null){
				numberCapsules[i] = projects[i].countCapsules();
			}
		}
		
		for (int i = 0; i<SIZE_PROJECT; i++){
			if (numberCapsules[0]!=-1){				
				if (i==0){
					projectMoreCapsules = i;
				}else if(numberCapsules[i]<numberCapsules[i]){
					projectMoreCapsules = i;
				}
			}else{
				projectMoreCapsules = -1;
			}
		}
		
		if (projectMoreCapsules==-1){
			message = "There's no any project registered";
		}else{
			message = "The project that has more capsules is: " + projects[projectMoreCapsules].getProjectName();
		}
		return message;
	}
	
	/**
	*initPhases: Create an object and send it to project class.
	*@param projectName Name of project
	*@param name Name of phase
	*@param startDate Start date of phase
	*@param finishDate Finish date of phase
	*@param approve Status of approvation of phase
	*@param active Phase activation status
	*@param i Represents the position in the months' array.
	*@param month Month to save.
	*@return message Message status
	*/
	public String initPhases(String projectName,String name, Calendar startDate, /*int i, int month,*/Calendar finishDate, boolean approve, boolean active, int i, int month){
		String message = "The project does not exist";
		int position = getProjectPosition(projectName);
		
		Phase phase = new Phase(name, startDate, finishDate,approve, active);
		message = projects[position].addPhase(phase,i,month);
		return message;
	}
	
	/**
	*culminatePhase: End a phase.
	*@param name Name of the project.
	*@param approve Project approval.
	*@param active Project activation.
	*@param month Number of months in the duration of a phase.
	*@return message Status message.
	*/
	public String culminatePhase(String name, boolean approve, boolean active, int month){
		int position = getProjectPosition(name);
		String message = projects[position].culminatePhase(approve,active, month);
		return message;
	}
	
	/**
	*createCapsule: Creates a knowledge capsule.
	*@param projectName Name of the project.
	*@param collaboratorName Who creates the capsule.
	*@param phasePosition In which phase the capsule is to be registered.
	*@param description The knowledge of the collaborator.
	*@param type Type of capsule.
	*@param learning Learning in the phase.
	*@param approve Status of approval.
	*@param hashtag Array with all the keywords.
	*@return message Status of the creation.
	*/
	public String createCapsule(String projectName, String collaboratorName, int phasePosition/*, String id*/, String description, int type, String learning, boolean approve, String[] hashtag){
		String message = "";
		TypeCapsule typeCapsule = null;
		boolean existProject = getExistentOfProject(projectName);
		
		if (existProject==false){
			message = "The project does not exist";
		}else{
			int position = getProjectPosition(projectName);
			String id = "p"+position;
			switch (type){
				case 1:
					typeCapsule = TypeCapsule.TECHNICAL;
					break;
				case 2:
					typeCapsule = TypeCapsule.MANAGEMENT;
					break;
				case 3:
					typeCapsule = TypeCapsule.DOMAIN;
					break;
				case 4:
					typeCapsule = TypeCapsule.EXPERIENCES;
					break;
			}
			KnowledgeCapsule capsule = new KnowledgeCapsule(collaboratorName, description, typeCapsule, learning, approve, hashtag);
			message = projects[position].createCapsule(phasePosition, id, capsule);
		}
		return message;
	}
	
	/**
	*approveCapsule: A manager approves a project capsule.
	*@param nameProject Name of the project.
	*@param nameManager Name of the manager.
	*@param idCapsule Id of the capsule.
	*@return message Status of the approval.
	*/
	public String approveCapsule(String nameProject, String nameManager, String idCapsule){
		String message = "";
		boolean existProject = getExistentOfProject(nameProject);
		
		if (existProject==false){
			message = "The project does not exist";
		}else{
			int position = getProjectPosition(nameProject);
			message = projects[position].approveCapsule(nameManager,idCapsule);
		}
		return message;
	}
	
	/**
	*publishCapsule: All capsules (with their urls) of a project that are approved are listed.
	*@param projectName Name of the project.
	*@return message Publication status.
	*/
	public String publishCapsule(String projectName){
		String message = "";
		boolean existProject = getExistentOfProject(projectName);
		if (existProject==false){
			message = "The project does not exist";
		}else{
			int position = getProjectPosition(projectName);
			message = projects[position].publishCapsule();
		}
		if (message == ""){
			message = "No capsule has been approved.";
		}
		return message;
	}
	
	/**
	*listCapsulesByType: Search a capsule based in its type.
	*@param type The type of the capsule.
	*@param name Name of the project.
	*@return message Status of the searching.
	*/
	public String listCapsulesByType(String name,int type){
		String message = "";
		int position = getProjectPosition(name);
		TypeCapsule newType = null;
		
		switch (type){
			case 1:
				newType = newType.TECHNICAL;
				break;
			case 2:
				newType = newType.MANAGEMENT;
				break;
			case 3:
				newType = newType.DOMAIN;
				break;
			case 4:
				newType = newType.EXPERIENCES;
				break;
		}
		
		if (position!=-1){
			for (int i = 0; i<SIZE_PROJECT;i++){
				if (projects[i]!=null){
					message+=projects[i].listCapsulesByType(newType);
				}else if (projects[0]==null){
					message = "There's no any capsule with that type.";
				}
			}
		}else{
			message = "The project does not exist.";
		}
		
		return message;
	}
}