package com.gmail.maxilandia.controller.mote.applications;

import com.gmail.maxilandia.controller.mote.MoteButton;
import com.gmail.maxilandia.controller.mote.MoteSlowButtonListener;

public class MoteApplicationListener extends MoteSlowButtonListener {

	public MoteApplicationListener(MoteControlledApplication app){
		this.app = app;
	}
	
	private final MoteControlledApplication app;
	
	public MoteControlledApplication getApp() {
		return app;
	}

	@Override
	public void performButtonAction(MoteButton button) {
		app.actionOnButtonPushed(button);
	}

}
