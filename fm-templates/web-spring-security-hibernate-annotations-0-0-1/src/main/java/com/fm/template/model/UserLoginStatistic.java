package com.fm.template.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class UserLoginStatistic {

	@Column(name = "login_ok")
	private Integer loginOk;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "last_login_ok")
	private Date lastLoginOk;
	@Column(name = "login_ko")
	private Integer loginKo;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "last_login_ko")
	private Date lastLoginKo;
	@Column(name = "last_login_ok_ip_address")
	private String lastLoginOkIPAddress;
	@Column(name = "last_login_ko_ip_address")
	private String lastLoginKoIPAddress;
	
	public UserLoginStatistic() {
		this.loginKo = 0;
		this.loginOk = 0;
	}
	
	public void incrementLoginOk() {
		this.loginOk++;
	}
	
	public void incrementLoginKo() {
		this.loginKo++;
	}

	public Integer getLoginOk() {
		return loginOk;
	}

	public void setLoginOk(Integer loginOk) {
		this.loginOk = loginOk;
	}

	public Date getLastLoginOk() {
		return lastLoginOk;
	}

	public void setLastLoginOk(Date lastLoginOk) {
		this.lastLoginOk = lastLoginOk;
	}

	public Integer getLoginKo() {
		return loginKo;
	}

	public void setLoginKo(Integer loginKo) {
		this.loginKo = loginKo;
	}

	public Date getLastLoginKo() {
		return lastLoginKo;
	}

	public void setLastLoginKo(Date lastLoginKo) {
		this.lastLoginKo = lastLoginKo;
	}

	public String getLastLoginOkIPAddress() {
		return lastLoginOkIPAddress;
	}

	public void setLastLoginOkIPAddress(String lastLoginOkIPAddress) {
		this.lastLoginOkIPAddress = lastLoginOkIPAddress;
	}

	public String getLastLoginKoIPAddress() {
		return lastLoginKoIPAddress;
	}

	public void setLastLoginKoIPAddress(String lastLoginKoIPAddress) {
		this.lastLoginKoIPAddress = lastLoginKoIPAddress;
	}

}
