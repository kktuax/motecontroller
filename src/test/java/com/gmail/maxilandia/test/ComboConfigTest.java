package com.gmail.maxilandia.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

import junit.framework.Assert;

import org.junit.Test;

import com.gmail.maxilandia.controller.mote.ConfigurableComboListener;

public class ComboConfigTest {

	@Test
	public void applicationConfigTest() throws Exception{
		ConfigurableComboListener ccl = new ConfigurableComboListener();
		
		ccl.setComboElements("A, B, A");
		ccl.setCommand("sudo shutdown -h now");
		File out = new File(System.getProperty("java.io.tmpdir"), "test-motecontroller-ccl.xml");
		ccl.writeXML(new FileOutputStream(out));
		System.out.println("Test config wrote to: " + out);
		Assert.assertEquals(ccl, ConfigurableComboListener.readXML(new FileReader(out)));
	}
	
}
