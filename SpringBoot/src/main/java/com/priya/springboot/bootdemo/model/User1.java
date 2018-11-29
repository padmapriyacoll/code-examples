package com.priya.springboot.bootdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user1")
public class User1 {
	@Id
	@GeneratedValue
	@Column(name="id")
	Integer id;
	@Column(name="name")
	String name;
	@Column(name="salary")
	Integer salary;
	@Column(name="team_name")
	String teamname;
	
	
	public User1() {
		
	}
	@Override
	public String toString() {
		return "User1 [id=" + id + ", name=" + name + ", salary=" + salary + ", teamname=" + teamname + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public String getTeamname() {
		return teamname;
	}
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
	

}
