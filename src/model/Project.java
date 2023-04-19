package model;

import java.util.Calendar;
/**
*This class represents a project.
*/
public class Project{
	
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
		this.budget = budget;
		this.phases = new Phase[SIZE_PHASE];
		this.managers = new Manager[SIZE_MANAGER];
		/*this.manager = new Manager[SIZE_MANAGER];*/
	}
	
	/**
	*registerManager: Save a manager.
	*@param i Position in the array managers.
	*@param manager The manager, an object.
	*@return message Registration status.
	*/
	public String registerManager(int i, Manager manager){
		managers[i]=manager;
		String message = "Manager has been created";
		return message;
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
	*culminatePhase: End a phase
	*@param approve Approve status of phase
	*@param active Active status of phase
	*@return message Message status of culminating
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
	*createCapsule: Create a capsule.
	*@param phasePosition Position in the array phases,.
	*@param id Id of capsule.
	*@param capsule Object of capsule, the knowledge.
	*@return message Status of creation
	*/
	public String createCapsule(int phasePosition, String id, KnowledgeCapsule capsule){
		id += "p"+(phasePosition-1);
		String message = phases[phasePosition-1].createCapsule(id,capsule);
		return message;
	}
	
	private int managerPosition(String name){
		int position = -1;
		boolean exit = false;
		for (int i = 0; i<SIZE_MANAGER; i++){
			if (managers[i].getName().equalsIgnoreCase(name)){
				position = i;
				exit = true;
			}
		}
		return position;
	}
	
	private int[] searchCapsule(String id){
		boolean exit = false;
		int[] position = new int[2];
		position[0] = -1;
		position[1] = -1;
		for (int i = 0; i<SIZE_PHASE && exit==false; i++){
			position[1]=phases[i].searchCapsuleById(id);
			if (position[1]!=-1){
				position[0] = i;
				exit = true;
			}
		}
		return position;
	}
	/**
	*approveCapsule: Changes the approval status of a capsule.
	*@param nameManager Name of the manager.
	*@param idCapsule Id of the capsule.
	*@return message Status of the approval.
	*/
	public String approveCapsule(String nameManager, String idCapsule){
		String message = "";
		int position = managerPosition(nameManager);
		
		if (position==-1){
			message = "The manager does not exist";
		}else{
			int[] capsulePosition = searchCapsule(idCapsule);
			if (capsulePosition[1]==-1){
				message = "The capsule does not exist";
			}else{
				message = phases[capsulePosition[0]].approveCapsule(capsulePosition[1],managers[position].getName());
			}
		}
		return message;
	}
	
	/**
	*publishCapsule: Publish all approved capsules.
	*@return message The capsules that are approved.
	*/
	public String publishCapsule(){
		String message = "";
		for (int i = 0; i<SIZE_PHASE;i++){
			message += phases[i].publishCapsule();
		}
		return message;
	}
	
	/**
	*listCapsulesByType: Search a capsule based in its type.
	*@param type The type of the capsule.
	*@return message Status of the searching.
	*/
	public String listCapsulesByType(TypeCapsule type){
		String message = "";
		
		for (int i = 0; i<SIZE_PHASE, i++){
			if (phases[i]!=null){
				message += phases[i].listCapsulesByType(type);
			}else if(phases[0]==null){
				message = "There's no any capsule that are approved or published"; 
			}
		}
		
		return message;
	}
}