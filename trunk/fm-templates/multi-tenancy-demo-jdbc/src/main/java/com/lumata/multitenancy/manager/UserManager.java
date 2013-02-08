package com.lumata.multitenancy.manager;

import java.util.List;

import com.lumata.multitenancy.model.User;
import com.lumata.multitenancy.routing.MultiTenancyServer;

public interface UserManager {
	
	List<User> all(MultiTenancyServer multiTenancyServer);
	
	void save(MultiTenancyServer multiTenancyServer, User user);
	
	void delete(MultiTenancyServer multiTenancyServer, User user);
	
	User getByName(MultiTenancyServer multiTenancyServer, String name);

}
