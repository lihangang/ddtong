package com.ddtong.entity;

import java.io.Serializable;

import com.ddtong.enums.YshDemoSexEnum;

public class YshDemoEntity implements Serializable {

	private static final long serialVersionUID = 8512552938645768921L;
	private Long id;
	private String userName;
	private String passWord;
	private YshDemoSexEnum userSex;
	private String nickName;

	public YshDemoEntity() {
		super();
	}

	public YshDemoEntity(String userName, String passWord, YshDemoSexEnum userSex) {
		super();
		this.passWord = passWord;
		this.userName = userName;
		this.userSex = userSex;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public YshDemoSexEnum getUserSex() {
		return userSex;
	}

	public void setUserSex(YshDemoSexEnum userSex) {
		this.userSex = userSex;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "userName " + this.userName + ", pasword " + this.passWord + "sex " + userSex.name();
	}

}