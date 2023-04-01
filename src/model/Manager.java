package model;

/**
*This class represents a manager
*/
public class Manager{
	
	private String name;
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