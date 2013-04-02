package com.gmail.maxilandia.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

import junit.framework.Assert;

import org.junit.Test;

import com.gmail.maxilandia.controller.mote.applications.ConfigurableApplicationController;

public class ApplicationConfigTest {

	@Test
	public void applicationConfigTest() throws Exception{
		ConfigurableApplicationController cac = new ConfigurableApplicationController();
		cac.setInit("clementine");
		cac.setApplicationName("clementine");
		cac.setOne("clementine --play-pause");
		File out = new File(System.getProperty("java.io.tmpdir"), "test-motecontroller-cac.xml");
		cac.writeXML(new FileOutputStream(out));
		System.out.println("Test config wrote to: " + out);
		Assert.assertEquals(cac, ConfigurableApplicationController.readXML(new FileReader(out)));
	}
	
}
