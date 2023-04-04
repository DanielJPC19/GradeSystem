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
	private String type;
	/**
	*The learning of the phase
	*/
	private String learning;
	/**
	*Approve of the capsule
	*/
	private boolean approve;
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
	
	
	public KnowledgeCapsule(String collaboratorName/*, String id*/, String description, String type, String learning, boolean approve/*, String url*/, String[] hashtag){
		this.collaboratorName = collaboratorName;
		//this.id = id;
		this.description = description;
		this.type = type;
		this.learning = type;
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
	*getUrl: Return the url of the capsule.
	*@return url Url of the capsule (html).
	*/
	public String getUrl(){
		return this.url;
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
}