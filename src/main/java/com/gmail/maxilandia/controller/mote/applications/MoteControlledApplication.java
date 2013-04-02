package com.gmail.maxilandia.controller.mote.applications;

import com.gmail.maxilandia.controller.mote.MoteButton;

public interface MoteControlledApplication {

	void initApplication();	
	void actionOnButtonPushed(MoteButton button);

}
