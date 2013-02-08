package com.fm.template.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.fm.template.routing.MultiTenancyAOPTest;
import com.fm.template.routing.MultiTenancyDAOTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	MultiTenancyDAOTest.class,
	MultiTenancyAOPTest.class
})
public class TestSuite {

}