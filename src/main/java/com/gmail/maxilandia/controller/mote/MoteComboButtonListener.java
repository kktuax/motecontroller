package com.gmail.maxilandia.controller.mote;

import java.util.ArrayList;
import java.util.List;

import motej.event.CoreButtonListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class MoteComboButtonListener extends MoteSlowButtonListener implements CoreButtonListener{
	
	private final List<MoteButton> comboButtons, receivedButtons;

	public MoteComboButtonListener(List<MoteButton> comboButtons){
		this.comboButtons = new ArrayList<MoteButton>(comboButtons);
		this.receivedButtons = new ArrayList<MoteButton>();
	}
	
	private long firstPressed;
	
	public void performButtonAction(MoteButton buttonPressed){
		if(receivedButtons.size() < comboButtons.size()){
			if(comboButtons.get(receivedButtons.size()).equals(buttonPressed)){
				log.info("Recibido boton de la secuencia: " + buttonPressed);
				receivedButtons.add(buttonPressed);
				if(this.receivedButtons.size() == 1){
					firstPressed = System.currentTimeMillis();
				}else{
					if((System.currentTimeMillis() - firstPressed) > getMaxTimeCombo()){
						log.info("Superado el tiempo maximo de la secuencia...");
						receivedButtons.clear();
					}
				}
				if(receivedButtons.size() == comboButtons.size()){
					receivedButtons.clear();
					performComboAction();
				}
			}
			else{
				receivedButtons.clear();
			}
		}
	}
	
	public int getMaxTimeCombo(){
		return (2 * this.comboButtons.size() * getIntervalTime()) + getIntervalTime();
	}
	
	public abstract void performComboAction();
	
	private static final Logger log = LoggerFactory.getLogger(MoteComboButtonListener.class);
	
}
