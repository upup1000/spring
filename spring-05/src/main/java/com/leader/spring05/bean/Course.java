package com.leader.spring05.bean;

public class Course {

	private int id;
	private String name;
	private int mark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	
	public String toString()
	{
		return "id:"+id+", name:"+name+", mark:"+mark;
    }
}
