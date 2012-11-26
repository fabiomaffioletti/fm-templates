package com.fm.template.builder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fm.template.model.Role;
import com.fm.template.model.User;
import com.fm.template.util.BaseBuilder;
import com.fm.template.util.FluentBuilder;

public class UserBuilder {
	public interface UserFluent extends BaseBuilder<User> {
		UserFluent id(Integer v);

		UserFluent username(String v);

		UserFluent password(String v);

		UserFluent newPassword(String v);

		UserFluent confirmNewPassword(String v);

		UserFluent email(String v);

		UserFluent enabled(boolean v);

		UserFluent accountExpired(boolean v);

		UserFluent accountLocked(boolean v);

		UserFluent credentialsExpired(boolean v);

		UserFluent roles(Set v);

		UserFluent firstName(String v);

		UserFluent lastName(String v);

		UserFluent loginOk(Integer v);

		UserFluent lastLoginOk(Date v);
	}

	public static UserFluent userBuilder(Integer role) {
		User instance = new User();
		instance.getUserLoginStatistic().setLoginOk(0);
		
		Set<Role> roles = new HashSet<Role>();
		roles.add(new Role(role));
		instance.setRoles(roles);
		
		return FluentBuilder.builderFor(UserFluent.class, instance);
	}
}
