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
	
	public static final int SIZE_CAPSULE = 50;
	private KnowledgeCapsule[] capsules;
	
	public Phase(String name, Calendar startDatePlanned, Calendar finishDatePlanned, boolean approve, boolean active){
		this.calendar = Calendar.getInstance();
		this.name = name;
		this.startDatePlanned = startDatePlanned;
		this.finishDatePlanned = finishDatePlanned;
		this.startDateReal = calendar;
		this.approve = approve;
		this.active = active;
		this.capsules = new KnowledgeCapsule[SIZE_CAPSULE];
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
	
	public String createCapsule(String id, KnowledgeCapsule capsule){
		String message = "";
		int position = getFirstValidPositionCapsule();
		
		if (position==-1){
			message = "You cannot create more capsules, the software is full";
		}else{
			id += "c"+position;
			String url = "https://gradesystem.co/"+id;
			capsules[position] = capsule;
			capsules[position].setId(id);
			capsules[position].setUrl(url);
			message = "The capsule has been created";
		}
		return message;
	}
}