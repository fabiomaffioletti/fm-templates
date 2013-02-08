package com.fm.template.multitenancy.dao;

import java.util.List;
import java.util.Map;

import com.fm.template.multitenancy.model.Tenant;

public interface TenantDAO {

	List<Tenant> all();
	
	Tenant get(Integer id);
	
	Tenant get(String name);
	
	Map<Object, Object> getTargetDataSources();
}
