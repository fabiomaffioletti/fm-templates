package com.fm.template.manager.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.fm.template.dao.GenericDAO;
import com.fm.template.manager.GenericManager;
import com.fm.template.model.BaseObject;
import com.fm.template.util.AdminConstants;
import com.fm.template.util.FilterObject;
import com.fm.template.util.SecurityUtils;

public class GenericManagerImpl<T extends BaseObject, PK extends Serializable> implements GenericManager<T, PK> {
	protected final Log log = LogFactory.getLog(getClass());
	protected GenericDAO<T, PK> dao;

	public GenericManagerImpl() {

	}

	public GenericManagerImpl(GenericDAO<T, PK> genericDao) {
		this.dao = genericDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<T> all() {
		return dao.all();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public T get(PK id) {
		return dao.get(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public boolean exists(PK id) {
		return dao.exists(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public T merge(T object, boolean systemUpdate) {
		if (object.getCreatedAt() == null) { // It is a new object
			if (systemUpdate) {
				object.setCreatedBy(AdminConstants.SYSTEM_USERNAME);
			} else {
				object.setCreatedBy(SecurityUtils.getUsernameFromSecurityContext());
			}
			object.setCreatedAt(new Date());
		}
		if (systemUpdate) {
			object.setModifiedBy(AdminConstants.SYSTEM_USERNAME);
		} else {
			object.setModifiedBy(SecurityUtils.getUsernameFromSecurityContext());
		}
		object.setModifiedAt(new Date());
		return dao.merge(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public PK save(T object, boolean systemUpdate) {
		object.setCreatedAt(new Date());
		if (systemUpdate) {
			object.setCreatedBy(AdminConstants.SYSTEM_USERNAME);
			object.setModifiedBy(AdminConstants.SYSTEM_USERNAME);
		} else {
			object.setCreatedBy(SecurityUtils.getUsernameFromSecurityContext());
			object.setModifiedBy(SecurityUtils.getUsernameFromSecurityContext());
		}
		object.setModifiedAt(object.getCreatedAt());
		return dao.save(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void update(T object, boolean systemUpdate) {
		if (systemUpdate) {
			object.setModifiedBy(AdminConstants.SYSTEM_USERNAME);
		} else {
			object.setModifiedBy(SecurityUtils.getUsernameFromSecurityContext());
		}
		object.setModifiedAt(new Date());
		dao.update(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void saveOrUpdate(T object, boolean systemUpdate) {
		if (object.getCreatedAt() == null) { // It is a new object
			if (systemUpdate) {
				object.setCreatedBy(AdminConstants.SYSTEM_USERNAME);
			} else {
				object.setCreatedBy(SecurityUtils.getUsernameFromSecurityContext());
			}
			object.setCreatedAt(new Date());
		}
		if (systemUpdate) {
			object.setModifiedBy(AdminConstants.SYSTEM_USERNAME);
		} else {
			object.setModifiedBy(SecurityUtils.getUsernameFromSecurityContext());
		}
		object.setModifiedAt(new Date());
		dao.saveOrUpdate(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void remove(PK id) {
		dao.remove(id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void remove(T object) {
		dao.remove(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<T> distinct() {
		return dao.distinct();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
		return dao.findByNamedQuery(queryName, queryParams);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public T filter(String property, String value) {
		return dao.filter(property, value);
	}

	@Override
	@Transactional
	public List<T> filter(Integer first, Integer size, List<FilterObject> filters, String sortColumn, String sortOrder) {
		return dao.filter(first, size, filters, sortColumn, sortOrder);
	}

	@Override
	@Transactional
	public Integer count() {
		return dao.count().intValue();
	}

	@Override
	@Transactional
	public Integer count(List<FilterObject> filters) {
		return dao.count(filters).intValue();
	}

}
