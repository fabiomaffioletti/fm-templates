package com.fm.template.multitenancy.routing;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.fm.template.multitenancy.dao.TenantDAO;

public class MultiTenancyServerRoutingDataSource extends AbstractRoutingDataSource {
	private TenantDAO tenantDAO;

	public void setTenantDAO(TenantDAO tenantDAO) {
		this.tenantDAO = tenantDAO;
	}

	@Override
	public void afterPropertiesSet() {
		setTargetDataSources(tenantDAO.getTargetDataSources());
		super.afterPropertiesSet();
	}

	@Override
	protected Object determineCurrentLookupKey() {
		if (MultiTenancyServerContextHolder.getMultiTenancyServer() != null) {
			return MultiTenancyServerContextHolder.getMultiTenancyServer().getKeyName();
		} else {
			return null;
		}
	}

}
