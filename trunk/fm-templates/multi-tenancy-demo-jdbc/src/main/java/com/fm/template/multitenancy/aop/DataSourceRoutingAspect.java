package com.fm.template.multitenancy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.fm.template.multitenancy.routing.MultiTenancyServer;
import com.fm.template.multitenancy.routing.MultiTenancyServerContextHolder;

@Component(value = "dataSourceRoutingAspect")
@Aspect
public class DataSourceRoutingAspect {
	
	@Pointcut(value = "execution(* com.fm.template.multitenancy.manager.impl.*.*(..)) && args(multiTenancyServer,..)")
	public void multiTenancyPointcut(MultiTenancyServer multiTenancyServer) {

	}
	
	@Around("multiTenancyPointcut(multiTenancyServer)")
	public Object apiKeyChecker(ProceedingJoinPoint jp, MultiTenancyServer multiTenancyServer) throws Throwable {
		MultiTenancyServerContextHolder.setMultiTenancyServer(multiTenancyServer);
		return jp.proceed();
	}

}