package com.fm.template.controller;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.server.setup.MockMvcBuilders.xmlConfigSetup;

import java.util.regex.Matcher;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.server.MockMvc;

import com.fm.template.builder.UserBuilder;
import com.fm.template.model.User;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CountryFormControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	protected String contextLoc = "classpath:applicationContext.xml";
    protected String warDir = "src/main/webapp";
    protected MockMvc mockMvc = null;

    @Before
	public void setUp() throws Exception {
    	User user = UserBuilder.userBuilder(2).username("admin").password("admin").build();
		mockMvc = xmlConfigSetup(contextLoc).configureWebAppRootDir(warDir, false).build();
        Assert.assertNotNull(mockMvc);
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, "admin"));
	}

	@Test
	public void country() throws Exception {
		mockMvc.perform(get("/admin-edit-country.html").param("id", "1"))
			.andExpect(status().isOk());
		
		mockMvc.perform(post("/admin-edit-country.html")
				.param("id", "1")
				.param("cities", "1", "2", "3")
				.param("foundation", "test")
				.param("description", "test")
				.param("createdAt", "2012-01-01 00:00:00")
				.param("modifiedAt", "2012-01-01 00:00:00")
				.param("createdBy", "sys")
				.param("modifiedBy", "sys")
				.param("description", "test")
				.param("name", "test"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void newcountry() throws Exception {
		mockMvc.perform(get("/admin-edit-country.html"))
			.andExpect(status().isOk());
		
		mockMvc.perform(post("/admin-edit-country.html")
				.param("cities", "2", "3")
				.param("createdAt", "2012-01-01 00:00:00")
				.param("modifiedAt", "2012-01-01 00:00:00")
				.param("createdBy", "sys")
				.param("modifiedBy", "sys")
				.param("description", "test")
				.param("foundation", "test")
				.param("name", "test"))
				.andExpect(view().name("redirect:admin-edit-country.html?id=3"))
				.andExpect(status().isOk());
	}
}
