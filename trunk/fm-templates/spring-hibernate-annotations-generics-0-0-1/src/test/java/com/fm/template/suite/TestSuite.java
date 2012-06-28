package com.fm.template.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.fm.template.dao.CityDAOTest;
import com.fm.template.dao.CountryDAOTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CountryDAOTest.class,
	CityDAOTest.class
	})
public class TestSuite {

}