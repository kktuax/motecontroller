package com.gmail.maxilandia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebianSystemExecutor implements SystemExecutor{
	
	public Process execute(String command) {
		try{
			return Runtime.getRuntime().exec(new String[]{"/bin/bash", "-c", command});
		}catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	private Logger log = LoggerFactory.getLogger(DebianSystemExecutor.class);
	
}
