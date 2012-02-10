package com.fm.template.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.fm.template.domain.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext-security.xml", "/applicationContext-persistence.xml", "/applicationContext-resources.xml", "/applicationContext-service.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class RoleMapperTest {
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Test
	public void testGetRoles() {
		List<Role> roles = roleMapper.getRoles();
		assertNotNull(roles);
		assertTrue(roles.size() == 2);
	}
	
}