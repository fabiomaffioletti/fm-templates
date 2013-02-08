package com.fm.template.multitenancy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.fm.template.multitenancy.model.Tenant;
import com.fm.template.multitenancy.routing.MultiTenancyServerContextHolder;

@Component(value = "dataSourceRoutingAspect")
@Aspect
public class DataSourceRoutingAspect {
	
	@Pointcut(value = "execution(* com.fm.template.multitenancy.manager.impl.*.*(..)) && args(multiTenancyServer,..)")
	public void multiTenancyPointcut(Tenant multiTenancyServer) {

	}
	
	@Around("multiTenancyPointcut(multiTenancyServer)")
	public Object apiKeyChecker(ProceedingJoinPoint jp, Tenant multiTenancyServer) throws Throwable {
		MultiTenancyServerContextHolder.setMultiTenancyServer(multiTenancyServer);
		return jp.proceed();
	}

}