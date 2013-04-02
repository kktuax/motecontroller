package com.gmail.maxilandia.controller.mote;

import motej.event.CoreButtonEvent;
import motej.event.CoreButtonListener;

public abstract class MoteSlowButtonListener implements CoreButtonListener{
	
	private final static int DEFAULT_INTERVAL_TIME = 500;
	
	private long lastPressed = 0;
	
	public void buttonPressed(CoreButtonEvent arg0) {
		if(arg0.getButton() == CoreButtonEvent.NO_BUTTON) return;
		if((System.currentTimeMillis() - lastPressed) > getIntervalTime()){
			lastPressed = System.currentTimeMillis();
			MoteButton mb = MoteButton.getMoteButton(arg0);
			if(mb != null){
				performButtonAction(mb);
			}
		}
	}
	
	public int getIntervalTime(){
		return DEFAULT_INTERVAL_TIME;
	}
	
	public abstract void performButtonAction(MoteButton button);

}
