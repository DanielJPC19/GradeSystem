package model;

public class KnowledgeCapsule{
	
	/**
	*Name of the collaborator (who register the capsule)
	*/
	private String collaboratorName;
	/**
	*
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
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public void setId(String id){
		this.id = id;
	}
}