package com.gmail.maxilandia.controller.mote;

import motej.event.CoreButtonEvent;

public enum MoteButton {

	A(CoreButtonEvent.BUTTON_A), B(CoreButtonEvent.BUTTON_B), HOME(
			CoreButtonEvent.BUTTON_HOME), MINUS(CoreButtonEvent.BUTTON_MINUS), PLUS(
			CoreButtonEvent.BUTTON_PLUS), ONE(CoreButtonEvent.BUTTON_ONE), TWO(
			CoreButtonEvent.BUTTON_TWO), UP(CoreButtonEvent.D_PAD_UP), DOWN(
			CoreButtonEvent.D_PAD_DOWN), LEFT(CoreButtonEvent.D_PAD_LEFT), RIGHT(
			CoreButtonEvent.D_PAD_RIGHT);

	private MoteButton(int buttonEvent) {
		this.buttonEvent = buttonEvent;
	}
	
	private final int buttonEvent;
	
	/**
	 * @param arg0
	 * @return Button pressed or null if unkown
	 */
	public static MoteButton getMoteButton(CoreButtonEvent arg0){
		for(MoteButton mb : MoteButton.values()){
			if(arg0.getButton() == mb.buttonEvent) return mb;
		}
		return null;
	}
	
}
