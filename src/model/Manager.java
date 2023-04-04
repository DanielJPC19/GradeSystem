package model;

/**
*This class represents a manager
*/
public class Manager{
	
	/**
	*Name of the manager.
	*/
	private String name;
	/**
	*Phone of the manager.
	*/
	private String phone;
	
	public Manager(String name, String phone){
		this.name = name;
		this.phone = phone;
	}
	
	/**
	*getName: Return the name of manager.
	*@return name Name of manager
	*/
	public String getName(){
		return this.name;
	}
	
	/**
	*getPhone: Return the name of manager.
	*@return phone Phone of manager
	*/
	public String getPhone(){
		return this.phone;
	}
}