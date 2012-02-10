package com.fm.template.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.fm.template.dao.UserDAOTest;
import com.fm.template.manager.UserManagerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	UserDAOTest.class,
	UserManagerTest.class})
public class TestSuite {

}