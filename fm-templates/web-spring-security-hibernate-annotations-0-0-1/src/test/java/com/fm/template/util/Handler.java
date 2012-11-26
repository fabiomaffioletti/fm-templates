package com.fm.template.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.springframework.test.util.ReflectionTestUtils;

public class Handler implements InvocationHandler {
	private Object obj;

	public Handler(Object obj) {
		this.obj = obj;
	}

	@Override
	public Object invoke(Object target, Method m, Object[] args) throws Throwable {
		try {
			if (m.getName().equals("build")) {
				return obj;
			} else {
				ReflectionTestUtils.setField(obj, m.getName(), args[0]);
				return target;
			}
		} catch (Throwable e) {
			 throw new IllegalStateException("Unrecognized builder method: " + m.getName());
		}		
	}
}