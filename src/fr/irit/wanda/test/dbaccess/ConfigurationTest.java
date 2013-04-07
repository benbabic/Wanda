package fr.irit.wanda.test.dbaccess;

import java.io.FileInputStream;
import java.util.Properties;

import org.junit.Test;

import fr.irit.wanda.configuration.DBConfiguration;
import fr.irit.wanda.configuration.HirarchyConfiguration;


public class ConfigurationTest {

	private static final String CONFIGURATION_FILE_PATH = "db.connect";

	@Test
	public void testConfiguration() {
		Properties prop = new Properties();
		DBConfiguration cfg = DBConfiguration.getInstance();
		
		try {
			FileInputStream in = new FileInputStream(CONFIGURATION_FILE_PATH);
			prop.load(in);
			org.junit.Assert.assertEquals(
					"failure - Driver not loaded from file !", cfg.getDriver(),
					prop.getProperty("Driver"));
			org.junit.Assert.assertEquals("failure - Url not loaded from file !",
					cfg.getUrl(), prop.getProperty("Url"));
			org.junit.Assert.assertEquals(
					"failure - Login not loaded from file !", cfg.getLogin(),
					prop.getProperty("Login"));
			org.junit.Assert.assertEquals(
					"failure - Password not loaded from file !",
					cfg.getPassword(), prop.getProperty("Password"));
			in.close();
		}  catch (Exception e) {
			org.junit.Assert.assertEquals(
					"failure - Driver not loaded from file!", cfg.getDriver(),
					"");
			org.junit.Assert.assertEquals("failure - Url not loaded from file",
					cfg.getUrl(), "");
			org.junit.Assert.assertEquals(
					"failure - Login not loaded from file", cfg.getLogin(),
					"");
			org.junit.Assert.assertEquals(
					"failure - Password not loaded from file",
					cfg.getPassword(), "");

		}
	}
	
	@Test
	public void testConnection(){
		HirarchyConfiguration HC = HirarchyConfiguration.getInstance();
		
	}
	

}
