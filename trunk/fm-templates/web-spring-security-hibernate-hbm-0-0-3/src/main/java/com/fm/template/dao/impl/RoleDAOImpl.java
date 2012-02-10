package com.fm.template.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.fm.template.dao.RoleDAO;
import com.fm.template.model.Role;

public class RoleDAOImpl extends HibernateDaoSupport implements RoleDAO {

	@Override
	public List<Role> getRoles() {
		return getHibernateTemplate().loadAll(Role.class);
	}

	@Override
	public Role getRoleById(Integer id) {
		return getHibernateTemplate().get(Role.class, id);
	}

	

}