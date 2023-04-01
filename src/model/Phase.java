package model;

import java.util.Calendar;
public class Phase{
	
	private String name;
	private Calendar calendar;
	private Calendar startDatePlanned;
	private Calendar finishDatePlanned;
	private Calendar startDateReal;
	private Calendar finishDateReal;
	private boolean approve;
	private boolean active;
	
	public Phase(String name, Calendar startDatePlanned, Calendar finishDatePlanned, boolean approve, boolean active){
		this.calendar = Calendar.getInstance();
		this.name = name;
		this.startDatePlanned = startDatePlanned;
		this.finishDatePlanned = finishDatePlanned;
		this.startDateReal = calendar;
		this.approve = approve;
		this.active = active;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean getApprove(){
		return this.approve;
	}
	
	public boolean getActive(){
		return this.active;
	}
	
	public void setApprove(boolean approve){
		this.approve = approve;
	}
	
	public void setActive(boolean active){
		this.active = active;
		setFinishDateReal();
	}
	
	public void setFinishDateReal(){
		this.finishDateReal = calendar;
	}
}