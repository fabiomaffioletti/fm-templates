package com.fm.template.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fm.template.model.UserAction;
import com.fm.template.util.FilterObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@Transactional
public class UserActionManagerTest {

	@Autowired
	private UserActionManager userActionManager;

	@Before
	public void setUp() {
		Assert.assertNotNull(userActionManager);
	}

	@Test
	public void testGetAll() {
		List<UserAction> items = userActionManager.all();
		Assert.assertTrue(items.size() > 0);
	}
	
	@Test
	public void testGet() {
		UserAction item = userActionManager.get(1);
		Assert.assertNotNull(item);
		Assert.assertNotNull(item.getPerformedBy());
		Assert.assertEquals("user1", item.getPerformedByUsername());
		Assert.assertNotNull(item.getPerformedOn());
	}
	
	@Test
	public void testFilter() {
		List<FilterObject> filters = new ArrayList<FilterObject>();
		filters.add(new FilterObject("performedByUsername", "admin", true));
		List<UserAction> items = userActionManager.filter(0, 10, filters, "performedAt", "desc");
		Assert.assertTrue(items.size() == 1);
		
		filters = new ArrayList<FilterObject>();
		filters.add(new FilterObject("performedByUsername", "user", false));
		items = userActionManager.filter(0, 10, filters, "performedAt", "desc");
		Assert.assertTrue(items.size() > 1);
		
		filters = new ArrayList<FilterObject>();
		filters.add(new FilterObject("performedBy", 1, false));
		items = userActionManager.filter(0, 10, filters, "performedAt", "desc");
		Assert.assertTrue(items.size() > 1);
	}

}