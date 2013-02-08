package com.fm.template.multitenancy.manager;

import java.util.List;

import com.fm.template.multitenancy.model.Tenant;
import com.fm.template.multitenancy.model.User;

public interface UserManager {
	
	List<User> all(Tenant multiTenancyServer);
	
	void save(Tenant multiTenancyServer, User user);
	
	void delete(Tenant multiTenancyServer, User user);

}
