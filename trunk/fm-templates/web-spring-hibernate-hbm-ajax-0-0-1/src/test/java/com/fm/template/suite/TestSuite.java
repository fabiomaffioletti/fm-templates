package com.fm.template.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.fm.template.dao.CountryDAOTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CountryDAOTest.class
	})
public class TestSuite {

}