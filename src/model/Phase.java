package model;

import java.util.Calendar;
/**
*This class represents the phases.
*/
public class Phase{
	
	/**
	*Name of the Phase.
	*/
	private String name;
	/**
	*Planned initialisation date.
	*/
	private Calendar startDatePlanned;
	/**
	*Planned finalisation date.
	*/
	private Calendar finishDatePlanned;
	/**
	*Phase approval.
	*/
	private boolean approve;
	/**
	*Phase activation.
	*/
	private boolean active;
	/**
	*Array size capsules.
	*/
	public static final int SIZE_CAPSULE = 50;
	/**
	*Array of capsules.
	*/
	private KnowledgeCapsule[] capsules;
	
	public Phase(String name, Calendar startDatePlanned, Calendar finishDatePlanned, boolean approve, boolean active){
		this.name = name;
		this.startDatePlanned = startDatePlanned;
		this.finishDatePlanned = finishDatePlanned;
		this.approve = approve;
		this.active = active;
		this.capsules = new KnowledgeCapsule[SIZE_CAPSULE];
	}
	/**
	*getName: Obtaining the name of the phase.
	*@return name Name of the phase.
	*/
	public String getName(){
		return this.name;
	}
	/**
	*getApprove: Status of phase approval.
	*@return approve Phase approval.
	*/
	public boolean getApprove(){
		return this.approve;
	}
	/**
	*getActive: Phase activation status.
	*@return active Phase activation.
	*/
	public boolean getActive(){
		return this.active;
	}
	/**
	*setApprove: Changes the approval status of a phase.
	*@param approve New Phase approval.
	*/
	public void setApprove(boolean approve){
		this.approve = approve;
	}
	/**
	*setActive: Changes the activation status of a phase.
	*@param active New Phase activation.
	*/
	public void setActive(boolean active){
		this.active = active;
	}
	
	private int getFirstValidPositionCapsule(){
		int position = -1;
		boolean exit = false;
		
		for (int i = 0; i<SIZE_CAPSULE && exit==false; i++){
			if (capsules[i]==null){
				position = i;
				exit = true;
			}
		}
		return position;
	}
	
	/**
	*createCapsule: Save a capsule.
	*@param id Id of the capsule
	*@param capsule Capsule to be stored, object.
	@return message Status of the saving.
	*/
	public String createCapsule(String id, KnowledgeCapsule capsule){
		String message = "";
		int position = getFirstValidPositionCapsule();
		
		if (position==-1){
			message = "You cannot create more capsules, the software is full";
		}else{
			id += "c"+position;
			String url = "https://gradesystem.co/"+id+".html";
			capsules[position] = capsule;
			capsules[position].setId(id);
			capsules[position].setUrl(url);
			message = "The capsule has been created";
			message += ", the id of capsule is: " + capsules[position].getId();
		}
		return message;
	}
	/**
	*searchCapsuleById: Busca una cápsula según su id.
	*@param id Id of the capsule.
	*@return position Position of the capsule in the array.
	*/
	public int searchCapsuleById(String id){
		int position = -1;
		boolean exit = false;
		for (int i = 0; i<SIZE_CAPSULE && exit==false; i++){
				if (capsules[i]!=null){
					if (capsules[i].getId().equalsIgnoreCase(id)){
						position = i;
						exit = true;
					}
				}
		}
		return position;
	}
	/**
	*approveCapsule: Approve a capsule.
	*@param position Position of the capsule in array.
	*@param managerName The manager who approves the capsule.
	*@return message Status of the approval.
	*/
	public String approveCapsule(int position, String managerName){
		String message = "";
		message = capsules[position].approveCapsule(managerName);
		return message;
	}
	/**
	*publishCapsule: Lists the url of the capsules that are approved.
	*@return message The capsules that are approval.
	*/
	public String publishCapsule(){
		String message = "";
		boolean exit = true;
		for (int i = 0; i<SIZE_CAPSULE; i++){
			if (capsules[i]!=null){
				if (capsules[i].getApprove()==true){
					capsules[i].setPublish();
					message += "\nId  of Capsule: "+capsules[i].getId()+"\nUrl: "+capsules[i].getUrl() + "\n";
				}
			}
		}
		return message;
	}
	
	/**
	*countCapsules: Count all the capsules of the phase.
	*@return totalCapsules Number of capsules in the phase.
	*/
	public int countCapsules(){
		int totalCapsules = 0;
		
		for (int i = 0; i<SIZE_CAPSULE; i++){
			if(capsules[i]!=null){
				totalCapsules += 1;
			}
		}
		
		return totalCapsules;
	}
	
	/**
	*listCapsulesByType: Search a capsule based in its type.
	*@param type The type of the capsule.
	*@return message Status of the searching.
	*/
	public String listCapsulesByType(TypeCapsule type){
		String message = "";
		
		for (int i = 0; i<SIZE_CAPSULE; i++){
			if (capsules[i]!=null){
				if (capsules[i].getType()==type){
					message += capsules[i].toString();
				}
			}
		}	
		return message;
	}
	
	/**
	*listLearnings: Show all the learnings of a capsule in a phase.
	*@return message Status of the showing.
	*/
	public String listLearnings(){
		String message = "";
		
		for (int i = 0; i<SIZE_CAPSULE; i++){
			if (capsules[0]==null){
				message = "There's no any capsule.";
			}else if (capsules[i]!=null){
				message += "\nLearning " + (i+1) + ": " + capsules[i].getLearning();
			}
		}
		return message;
	}
	
	/**
	*collaboratorCapsules: Return the number of capsules in a phase of a collaborator if he exists.
	*@param collaboratorName Name of the collaborator.
	*@return numberCapsules Number of capsules written by the collaborator.
	*/
	public int collaboratorCapsules(String collaboratorName){
		int numberCapsules = 0;
		
		for (int i = 0; i<SIZE_CAPSULE; i++){
			if (capsules[i]!=null){
				if (capsules[i].getCollaboratorName().equalsIgnoreCase(collaboratorName)){
					numberCapsules += 1;
				}
			}
		}
		
		return numberCapsules;
	}
	
	/**
	*approvedAndPublishedCapsules: Search and returns those capsules that are approved and published.
	*@param keywords Keywords to search capsules.
	*@return message Status of the searching.
	*/
	public String approvedAndPublishedCapsules(String[] keywords){
		String message = "";
		String[] hashtag;
		boolean exit = false;
		
		for (int i = 0; i<SIZE_CAPSULE; i++){
			exit = false;
			if (capsules[i]!=null){
				hashtag = capsules[i].getHashtag();
				for (int j = 0; j<keywords.length && exit == false; j++){
					for (int k = 0; k<hashtag.length && exit == false; k++){
						if(hashtag[k].equalsIgnoreCase(keywords[j]) && capsules[i].getApprove()==true && capsules[i].getPublish()==true){
							message += "\nCapsule info:" + "\nLearning: " + capsules[i].getLearning() + "\nDescription: " + capsules[i].getDescription() + "\n";
							exit = true;
						}
					}
				}
			}
		}
		
		return message;
	}
}