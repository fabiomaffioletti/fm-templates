package com.fm.template.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.fm.template.dao.GenericDAO;
import com.fm.template.util.AdminConstants;
import com.fm.template.util.FilterObject;

public class GenericDAOImpl<T, PK extends Serializable> implements GenericDAO<T, PK> {
	protected final Log log = LogFactory.getLog(getClass());
	private Class<T> persistentClass;
	private SessionFactory sessionFactory;

	public GenericDAOImpl(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public GenericDAOImpl(final Class<T> persistentClass, SessionFactory sessionFactory) {
		this.persistentClass = persistentClass;
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	@Autowired
	@Required
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> all() {
		return sessionFactory.getCurrentSession().createQuery("from " + this.persistentClass.getName()).list();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<T> distinct() {
		Collection<T> result = new LinkedHashSet<T>(all());
		return new ArrayList<T>(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T get(PK id) {
		return (T) sessionFactory.getCurrentSession().get(this.persistentClass, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public boolean exists(PK id) {
		T entity = (T) sessionFactory.getCurrentSession().get(this.persistentClass, id);
		return entity != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public PK save(T object) {
		return (PK) sessionFactory.getCurrentSession().save(object);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(T object) {
		sessionFactory.getCurrentSession().update(object);
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveOrUpdate(T object) {
		sessionFactory.getCurrentSession().saveOrUpdate(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T merge(T object) {
		return (T) sessionFactory.getCurrentSession().merge(object);
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(PK id) {
		sessionFactory.getCurrentSession().delete(this.get(id));
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void remove(T object) {
		sessionFactory.getCurrentSession().delete(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
		String[] params = new String[queryParams.size()];
		Object[] values = new Object[queryParams.size()];

		Query query = sessionFactory.getCurrentSession().getNamedQuery(queryName);
		int index = 0;
		for (String s : queryParams.keySet()) {
			params[index] = s;
			values[index++] = queryParams.get(s);
			query.setParameter(s, queryParams.get(s));
		}

		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T filter(String property, String value) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(persistentClass);
		criteria.add(Restrictions.eq(property, value));
		List<T> result = criteria.list();
		if (result.size() > 0)
			return result.get(0);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> filter(Integer first, Integer size, List<FilterObject> filters, String sortColumn, String sortOrder) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(persistentClass);
		
		for (FilterObject filterObject : filters) {
			if(filterObject.getValue() instanceof String) {
				if(filterObject.isStrict()) {
					criteria.add(Restrictions.like(filterObject.getProperty(), String.valueOf(filterObject.getValue()), MatchMode.EXACT));
				} else {
					criteria.add(Restrictions.like(filterObject.getProperty(), String.valueOf(filterObject.getValue()), MatchMode.ANYWHERE));
				}
			} else if(filterObject.getValue() instanceof Number) {
				criteria.add(Restrictions.eq(filterObject.getProperty(), filterObject.getValue()));
			}
		}
		
//		for (int i=0; i<properties.length; i++) {
//			if(!StringUtils.isBlank(properties[i]) && values[i] != null) {
//				if(properties[i].contains(".")) {
//					String[] splittedProperty = properties[i].split("\\.");
//					if(strict) {
//						criteria.createCriteria(splittedProperty[0]).add(Restrictions.like(splittedProperty[1], values[i], MatchMode.EXACT));
//					} else {
//						criteria.createCriteria(splittedProperty[0]).add(Restrictions.like(splittedProperty[1], values[i], MatchMode.ANYWHERE));
//					}
//					
//				} else {
//					if(strict) {
//						criteria.add(Restrictions.like(properties[i], values[i], MatchMode.EXACT));
//					} else {
//						criteria.add(Restrictions.like(properties[i], values[i], MatchMode.ANYWHERE));
//					}
//					
//				}
//			}
//		}
		if (!StringUtils.isBlank(sortColumn) && !StringUtils.isBlank(sortOrder)) {
			if(sortOrder.equalsIgnoreCase(AdminConstants.ORDER_DESC)) {
				criteria.addOrder(Order.desc(sortColumn));
			} else {
				criteria.addOrder(Order.asc(sortColumn));
			}
		}
		criteria.setFirstResult(first);
		criteria.setMaxResults(size);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	public Long count() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(persistentClass);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	@Override
	public Long count(List<FilterObject> filters) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(persistentClass);
		criteria.setProjection(Projections.rowCount());
		
		for (FilterObject filterObject : filters) {
			if(filterObject.getValue() instanceof String) {
				if(filterObject.isStrict()) {
					criteria.add(Restrictions.like(filterObject.getProperty(), String.valueOf(filterObject.getValue()), MatchMode.EXACT));
				} else {
					criteria.add(Restrictions.like(filterObject.getProperty(), String.valueOf(filterObject.getValue()), MatchMode.ANYWHERE));
				}
			} else if(filterObject.getValue() instanceof Number) {
				criteria.add(Restrictions.eq(filterObject.getProperty(), filterObject.getValue()));
			}
		}
		
//		for (int i=0; i<properties.length; i++) {
//			if(!StringUtils.isBlank(properties[i]) && !StringUtils.isBlank(values[i])) {
//				if(properties[i].contains(".")) {
//					String[] splittedProperty = properties[i].split("\\.");
//					if(strict) {
//						criteria.createCriteria(splittedProperty[0]).add(Restrictions.like(splittedProperty[1], values[i], MatchMode.EXACT));
//					} else {
//						criteria.createCriteria(splittedProperty[0]).add(Restrictions.like(splittedProperty[1], values[i], MatchMode.ANYWHERE));
//					}
//				} else {
//					if(strict) {
//						criteria.add(Restrictions.like(properties[i], values[i], MatchMode.EXACT));
//					} else {
//						criteria.add(Restrictions.like(properties[i], values[i], MatchMode.ANYWHERE));
//					}
//				}
//			}
//		}
		return (Long) criteria.uniqueResult();
	}
}
