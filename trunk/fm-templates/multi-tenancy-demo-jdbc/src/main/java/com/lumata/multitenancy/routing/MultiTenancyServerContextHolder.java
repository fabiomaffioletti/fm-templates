package com.lumata.multitenancy.routing;

import org.springframework.util.Assert;

public class MultiTenancyServerContextHolder {

	private static final ThreadLocal<MultiTenancyServer> contextHolder = new ThreadLocal<MultiTenancyServer>();

	public static void setMultiTenancyServer(MultiTenancyServer multiTenancyServer) {
		Assert.notNull(multiTenancyServer, "multiTenancyServer cannot be null");
		contextHolder.set(multiTenancyServer);
	}

	public static MultiTenancyServer getMultiTenancyServer() {
		return (MultiTenancyServer) contextHolder.get();
	}

	public static void clearMultiTenancyServer() {
		contextHolder.remove();
	}

}
