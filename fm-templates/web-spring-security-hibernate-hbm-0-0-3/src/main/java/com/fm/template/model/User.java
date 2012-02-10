package com.fm.template.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class User implements UserDetails {
	private Integer id;
	@NotEmpty
	@Length(max=45)
	private String username;
	@NotEmpty
	@Length(max=45)
	private String password;
	private String newPassword;
	private String confirmNewPassword;
	@NotEmpty
	@Length(max=45)
	@Email
	private String email;
	@NotNull
	private boolean enabled;
	private boolean accountExpired;
	private boolean accountLocked;
	private boolean credentialsExpired;
	@NotEmpty
	private Set<Role> roles = new HashSet<Role>();
	
	private String firstName;
	private String lastName;
	private Integer loginOk;
	private Date lastLoginOk;
	
	
	public User() {
		
	}
	
	public User(String password) {
		this.password = password;
		this.enabled = true;
		this.accountExpired = false;
		this.accountLocked = false;
		this.credentialsExpired = false;
		this.loginOk = 0;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", newPassword=" + newPassword + ", confirmNewPassword=" + confirmNewPassword + ", email=" + email + ", enabled=" + enabled + ", accountExpired=" + accountExpired + ", accountLocked=" + accountLocked + ", credentialsExpired=" + credentialsExpired + ", roles=" + roles + ", firstName=" + firstName + ", lastName=" + lastName + ", loginOk=" + loginOk + ", lastLoginOk=" + lastLoginOk + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (accountExpired ? 1231 : 1237);
		result = prime * result + (accountLocked ? 1231 : 1237);
		result = prime * result + ((confirmNewPassword == null) ? 0 : confirmNewPassword.hashCode());
		result = prime * result + (credentialsExpired ? 1231 : 1237);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastLoginOk == null) ? 0 : lastLoginOk.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((loginOk == null) ? 0 : loginOk.hashCode());
		result = prime * result + ((newPassword == null) ? 0 : newPassword.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (accountExpired != other.accountExpired)
			return false;
		if (accountLocked != other.accountLocked)
			return false;
		if (confirmNewPassword == null) {
			if (other.confirmNewPassword != null)
				return false;
		} else if (!confirmNewPassword.equals(other.confirmNewPassword))
			return false;
		if (credentialsExpired != other.credentialsExpired)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled != other.enabled)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastLoginOk == null) {
			if (other.lastLoginOk != null)
				return false;
		} else if (!lastLoginOk.equals(other.lastLoginOk))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (loginOk == null) {
			if (other.loginOk != null)
				return false;
		} else if (!loginOk.equals(other.loginOk))
			return false;
		if (newPassword == null) {
			if (other.newPassword != null)
				return false;
		} else if (!newPassword.equals(other.newPassword))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
