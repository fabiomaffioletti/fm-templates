package com.fm.template.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user")
@DynamicUpdate(true)
@SuppressWarnings("serial")
public class User extends BaseUser implements UserDetails {
	@Column(name = "password")
	@Length(max = 45, message = "{user.password.length}")
	private String password;
	@Transient
	private String newPassword;
	@Transient
	private String confirmNewPassword;
	@Column(name = "enabled")
	private boolean enabled;
	@Column(name = "account_expired")
	private boolean accountExpired;
	@Column(name = "account_locked")
	private boolean accountLocked;
	@Column(name = "credentials_expired")
	private boolean credentialsExpired;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	@NotEmpty(message = "{user.roles.notEmpty}")
	private Set<Role> roles;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Embedded
	private UserLoginStatistic userLoginStatistic;
	@Embedded
	private Address address;

	public User() {
		this.enabled = true;
		this.accountExpired = false;
		this.accountLocked = false;
		this.credentialsExpired = false;
		roles = new HashSet<Role>();
		this.address = new Address();
		this.userLoginStatistic = new UserLoginStatistic();
	}

	public String getLastLoginKoIPAddress() {
		return this.userLoginStatistic.getLastLoginKoIPAddress();
	}

	public String getLastLoginOkIPAddress() {
		return this.userLoginStatistic.getLastLoginOkIPAddress();
	}

	public Date getLastLoginKo() {
		return this.userLoginStatistic.getLastLoginKo();
	}

	public Date getLastLoginOk() {
		return this.userLoginStatistic.getLastLoginOk();
	}

	public Integer getLoginOk() {
		return this.userLoginStatistic.getLoginOk();
	}

	public Integer getLoginKo() {
		return this.userLoginStatistic.getLoginKo();
	}

	public void incrementLoginOk() {
		this.userLoginStatistic.incrementLoginOk();
	}

	public void incrementLoginKO() {
		this.userLoginStatistic.incrementLoginKo();
	}

	public UserLoginStatistic getUserLoginStatistic() {
		return userLoginStatistic;
	}

	public void setUserLoginStatistic(UserLoginStatistic userLoginStatistic) {
		this.userLoginStatistic = userLoginStatistic;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		this.roles.add(role);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : roles) {
//			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			authorities.add(new GrantedAuthorityImpl(role.getRoleName()));
		}
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !isAccountExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return !isAccountLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !isCredentialsExpired();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
