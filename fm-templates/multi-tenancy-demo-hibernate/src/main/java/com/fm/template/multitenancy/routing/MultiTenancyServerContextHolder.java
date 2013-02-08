package com.fm.template.multitenancy.routing;

import org.springframework.util.Assert;

import com.fm.template.multitenancy.model.Tenant;

public class MultiTenancyServerContextHolder {

	private static final ThreadLocal<Tenant> contextHolder = new ThreadLocal<Tenant>();

	public static void setMultiTenancyServer(Tenant multiTenancyServer) {
		Assert.notNull(multiTenancyServer, "multiTenancyServer cannot be null");
		contextHolder.set(multiTenancyServer);
	}

	public static Tenant getMultiTenancyServer() {
		return (Tenant) contextHolder.get();
	}

	public static void clearMultiTenancyServerContext() {
		contextHolder.remove();
	}

}
