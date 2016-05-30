package com.bluemedia.app.impl.dao;

import org.junit.Before;

public abstract class BaseDaoTest {

	@Before
	public void initializeTest(){
		getDaoToTest().postConstruct();
	}
	
	public abstract BaseDao<?> getDaoToTest();
}
