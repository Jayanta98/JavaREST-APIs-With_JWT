package com.sp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "secure_table")
public class MyUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int uId;
	private String myUserName;
	private String myUserPassword;
	private String myUserEmail;
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getMyUserName() {
		return myUserName;
	}
	public void setMyUserName(String myUserName) {
		this.myUserName = myUserName;
	}
	public String getMyUserPassword() {
		return myUserPassword;
	}
	public void setMyUserPassword(String myUserPassword) {
		this.myUserPassword = myUserPassword;
	}
	public String getMyUserEmail() {
		return myUserEmail;
	}
	public void setMyUserEmail(String myUserEmail) {
		this.myUserEmail = myUserEmail;
	}
	
	
	

}
