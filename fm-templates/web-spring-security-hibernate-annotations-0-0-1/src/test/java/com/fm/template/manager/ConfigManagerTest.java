package com.fm.template.manager;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.fm.template.dao.ConfigDAO;
import com.fm.template.model.Config;
import com.fm.template.util.AdminConstants;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ConfigManagerTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private ConfigManager configManager;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ConfigDAO configDAO;

	@Before
	public void setUp() {
		Assert.assertNotNull(configManager);
	}

	@Test
	public void testGetAll() {
		List<Config> items = configManager.all();
		Assert.assertTrue(items.size() > 0);
	}

	@Test
	public void testGet() {
		Config item = configManager.get(1);
		Assert.assertNotNull(item);
		Assert.assertNotNull(item.getConfigName());
		Assert.assertNotNull(item.getConfigValue());
	}

	@Test
	public void testFilter() {
		Config item = configManager.filter("configName", AdminConstants.DEFAULT_USERS_PAGE_SIZE_CONFIG_NAME);
		Assert.assertNotNull(item);
		Assert.assertNotNull(item.getConfigName());
		Assert.assertNotNull(item.getConfigValue());
	}
	
	@Test
	public void save() {
		Config config = new Config();
		config.setConfigName("test");
		config.setConfigValue("test");
		configManager.save(config, true);
		sessionFactory.getCurrentSession().flush();
	}
	
	@Test
	public void update() {
		Config config = configManager.get(1);
		config.setConfigValue("test");
		configDAO.merge(config);
		sessionFactory.getCurrentSession().flush();
	}

}