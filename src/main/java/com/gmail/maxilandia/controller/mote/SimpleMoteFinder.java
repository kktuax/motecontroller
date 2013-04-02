package com.gmail.maxilandia.controller.mote;

import motej.Mote;
import motej.MoteFinder;
import motej.MoteFinderListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleMoteFinder implements MoteFinderListener {

	private Logger log = LoggerFactory.getLogger(SimpleMoteFinder.class);
	private MoteFinder finder;
	private Object lock = new Object();
	private Mote mote;

	public static void main(String[] args) throws Exception{
		SimpleMoteFinder smf = new SimpleMoteFinder();
		Mote mote = smf.findMote();
		LoggerFactory.getLogger(SimpleMoteFinder.class).debug("Found mote!: " + mote);
	}
	
	public static SimpleMoteFinder getInstance(){
		if(instance == null) instance = new SimpleMoteFinder();
		return instance;
	}
	
	private static SimpleMoteFinder instance;
	
	private SimpleMoteFinder(){
		
	}
	
	public void moteFound(Mote mote) {
		log.info("SimpleMoteFinder received notification of a found mote.");
		this.mote = mote;
		synchronized(lock) {
			lock.notifyAll();
		}
	}
	
	public Mote findMote() {
		if (finder == null) {
			finder = MoteFinder.getMoteFinder();
			finder.addMoteFinderListener(this);
		}
		log.info("Starting discovery...");
		finder.startDiscovery();
		try {
			synchronized(lock) {
				lock.wait();
			}
		} catch (InterruptedException ex) {
			log.info("Stopping discovery after: " + ex.getMessage());
			finder.stopDiscovery();
		}
		return mote;
	}

}
