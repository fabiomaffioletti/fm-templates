package com.fm.template.util;

import java.lang.reflect.Proxy;

/**
 * Utility class for creating Fluent Interface.
 * It Generates a proxy-class for instance parameter.
 */
public class FluentBuilder<T extends BaseBuilder<?>> {

	@SuppressWarnings("unchecked")
	public static <T> T builderFor(Class<T> builderInterface,Object intance) {
		return (T) Proxy.newProxyInstance(Handler.class.getClassLoader(), new Class[] { builderInterface }, new Handler(intance));
	}
}
