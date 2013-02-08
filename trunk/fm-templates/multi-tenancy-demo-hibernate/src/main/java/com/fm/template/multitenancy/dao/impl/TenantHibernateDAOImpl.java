package com.fm.template.multitenancy.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

import com.fm.template.multitenancy.dao.TenantDAO;
import com.fm.template.multitenancy.model.Tenant;

public class TenantHibernateDAOImpl implements TenantDAO, InitializingBean {
	private Map<Object, Object> targetDataSources;

	public void setTargetDataSources(Map<Object, Object> targetDataSources) {
		this.targetDataSources = targetDataSources;
	}

	private SessionFactory globalSessionFactory;

	public void setGlobalSessionFactory(SessionFactory globalSessionFactory) {
		this.globalSessionFactory = globalSessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional("globalTransactionManager")
	public List<Tenant> all() {
		return globalSessionFactory.getCurrentSession().createCriteria(Tenant.class).list();
	}

	@Override
	@Transactional("globalTransactionManager")
	public Tenant get(Integer id) {
		return (Tenant) globalSessionFactory.getCurrentSession().get(Tenant.class, id);
	}

	@Override
	@Transactional("globalTransactionManager")
	public Tenant get(String name) {
		Criteria criteria = globalSessionFactory.getCurrentSession().createCriteria(Tenant.class);
		criteria.add(Restrictions.eq("keyName", name));
		return (Tenant) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void afterPropertiesSet() throws Exception {
		if(targetDataSources == null) {
			targetDataSources = new HashMap<Object, Object>();
			Session session = globalSessionFactory.openSession();
			List<Tenant> multiTenancyServers = session.createCriteria(Tenant.class).list();
			for (Tenant multiTenancyServer : multiTenancyServers) {
				targetDataSources.put(multiTenancyServer.getKeyName(), multiTenancyServer.getJndiName());
			}
			session.close();
		}
	}

	public Map<Object, Object> getTargetDataSources() {
		return targetDataSources;
	}

}
