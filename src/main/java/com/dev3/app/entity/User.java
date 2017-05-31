package com.dev3.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author RL
 *
 */

@Entity
@Table(name = "user")
@PrimaryKeyJoinColumn(name = "id")
public class User implements Serializable {

	private static final long serialVersionUID = -1506155395944938535L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String userId;
	
	private String userName;
	
	private String password;
	
	private String roles;
	
	private int status;
	
	private int badPassCount;
	
	private Date lastBadPassTry;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getBadPassCount() {
		return badPassCount;
	}

	public void setBadPassCount(int badPassCount) {
		this.badPassCount = badPassCount;
	}

	public Date getLastBadPassTry() {
		return lastBadPassTry;
	}

	public void setLastBadPassTry(Date lastBadPassTry) {
		this.lastBadPassTry = lastBadPassTry;
	}
	
	
	
}
