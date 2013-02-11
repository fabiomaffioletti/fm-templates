package com.fm.template.multitenancy.routing;

import org.springframework.util.Assert;

public class MultiTenancyServerContextHolder {

	private static final ThreadLocal<Tenant> contextHolder = new ThreadLocal<Tenant>();

	public static void setTenant(Tenant tenant) {
		Assert.notNull(tenant, "tenant cannot be null");
		contextHolder.set(tenant);
	}

	public static Tenant getTenant() {
		return (Tenant) contextHolder.get();
	}

	public static void clearTenant() {
		contextHolder.remove();
	}

}
