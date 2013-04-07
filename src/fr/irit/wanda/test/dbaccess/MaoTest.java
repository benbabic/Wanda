package fr.irit.wanda.test.dbaccess;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.irit.wanda.configuration.HirarchyConfiguration;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

public class MaoTest {

	@BeforeClass
	public static void setUpBeforeClass() {

	}

	@Test
	public void testHirarchyConfiguration() throws NotFoundInDatabaseException {
		HirarchyConfiguration HC = HirarchyConfiguration.getInstance();
		System.out.println(HC.getHierarchy());
	}
}
