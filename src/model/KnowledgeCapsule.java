package model;

/**
*This class represents the knowledge capsules.
*/
public class KnowledgeCapsule{
	
	/**
	*Name of the collaborator (who register the capsule).
	*/
	private String collaboratorName;
	/**
	*Name of the manager who approves the capsule.
	*/
	private String managerName;
	/**
	*Capsule id
	*/
	private String id;
	/**
	*Description, the knowledge or the pharagrap
	*/
	private String description;
	/**
	*Capsule type
	*/
	private TypeCapsule type;
	/**
	*The learning of the phase
	*/
	private String learning;
	/**
	*Approve of the capsule
	*/
	private boolean approve;
	/**
	*Publish of the capsule.
	*/
	private boolean publish;
	/**
	*url of the capsule
	*/
	private String url;
	/**
	*Size of the array Hashtag
	*/
	public final static int SIZE_HASHTAG = 3;
	/**
	*Important words, hashtag, filters.
	*/
	private String[] hashtag;
	
	
	public KnowledgeCapsule(String collaboratorName/*, String id*/, String description, TypeCapsule type, String learning, boolean approve/*, String url*/, String[] hashtag){
		this.collaboratorName = collaboratorName;
		//this.id = id;
		this.description = description;
		this.type = type;
		this.approve = approve;
		this.publish = false;
		this.learning = learning;
		this.hashtag = hashtag;
	}
	/**
	*getType: Return type of capsule.
	*@return type Type of capsule.
	*/
	public TypeCapsule getType(){
		return this.type;
	}
	/**
	*setUrl: Save the capsule's url.
	*@param url Url to save.
	*/
	public void setUrl(String url){
		this.url = url;
	}
	/**
	*setId: save the capsule's id;
	*@param id Id to save.
	*/
	public void setId(String id){
		this.id = id;
	}
	/**
	*getId: Return the id of the capsule.
	*@return id Id of the capsule.
	*/
	public String getId(){
		return this.id;
	}
	/**
	*getApprove: Return the approval of the capsule.
	*@return approve Approval of the Capsule.
	*/
	public boolean getApprove(){
		return this.approve;
	}
	/**
	*getPublish: Return the status of publication of the capsule.
	*@return publish Publication of the capsule.
	*/
	public boolean getPublish(){
		return this.publish;
	}
	/**
	*getUrl: Return the url of the capsule.
	*@return url Url of the capsule (html).
	*/
	public String getUrl(){
		return this.url;
	}
	/**
	*getCollaboratorName: Return the collaborator name of the capsule.
	*@return collaboratorName Name of the collaborator.
	*/
	public String getCollaboratorName(){
		return this.collaboratorName;
	}
	/**
	*approveCapsule: Changes the approval of a capsule.
	*@param managerName Name of the manager who approves the capsule.
	*@return message Status of approval.
	*/
	public String approveCapsule(String managerName){
		this.managerName = managerName;
		this.approve = true;
		String message = "The capsule has been approved";
		return message;
	}
	/**
	*setPublish: Change the status of the publish.
	*/
	public void setPublish(){
		this.publish = true;
	}
	
	/**
	*toString: Show the capsule information
	*/
	public String toString(){
		return "\nCapsule Info:\nId: " + this.id + "\nCollaborator: " + this.collaboratorName + "\nLearning: " + this.learning + "\nDescription: " + this.description + "\nUrl: " + this.url;
	}
	
	/**
	*getLearning: Return the learning of the capsule.
	*@return learning Learning of the capsule.
	*/
	public String getLearning(){
		return this.learning;
	}
	/**
	*getDescription: Return the description of the capsule.
	*@return description Description of the capsule.
	*/
	public String getDescription(){
		return this.description;
	}
	/**
	*getHashtag: Return the hashtag or keywords of the capsule.
	*@return hashtag Hashtag of the capsule.
	*/
	public String[] getHashtag(){
		return this.hashtag;
	}
}