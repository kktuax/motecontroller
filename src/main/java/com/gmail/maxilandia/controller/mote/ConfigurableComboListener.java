package com.gmail.maxilandia.controller.mote;

import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmail.maxilandia.controller.SystemExecutor;
import com.thoughtworks.xstream.XStream;

public class ConfigurableComboListener {

	private String name, comboElements, command;
	
	private SystemExecutor executor;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getComboElements() {
		return comboElements;
	}
	public void setComboElements(String comboElements) {
		this.comboElements = comboElements;
	}
	public SystemExecutor getExecutor() {
		return executor;
	}
	public void setExecutor(SystemExecutor executor) {
		this.executor = executor;
	}

	public void writeXML(OutputStream out){
		getXStream().toXML(this, out);
	}
	
	public static ConfigurableComboListener readXML(Reader in){
		return (ConfigurableComboListener) getXStream().fromXML(in);
	}
	
	private static XStream getXStream(){
		XStream xs = new XStream();
		xs.omitField(ConfigurableComboListener.class, "log");
		xs.omitField(ConfigurableComboListener.class, "executor");
		xs.alias("comboController", ConfigurableComboListener.class);
		return xs;
	}
	
	public MoteComboButtonListener getMoteComboButtonListener(){
		List<MoteButton> comboButtons = new ArrayList<MoteButton>();
		StringTokenizer st = new StringTokenizer(getComboElements(), ",");
		while(st.hasMoreElements()){
			try{
				comboButtons.add(MoteButton.valueOf(st.nextToken().trim()));
			}catch (IllegalArgumentException e) {
				log.warn("Illegal button defined on combo controller: " + e.getMessage());
			}
		}
		if(comboButtons.isEmpty()) throw new IllegalStateException("No valid button secuency defined in: " + getComboElements());
		else{
			return new ConfiguredCommandComboButtonListener(comboButtons, getExecutor(), getCommand());
		}
	}
	
	private static class ConfiguredCommandComboButtonListener extends MoteComboButtonListener{
		private SystemExecutor executor; 
		private String command;
		public ConfiguredCommandComboButtonListener(List<MoteButton> comboButtons, SystemExecutor executor, String command) {
			super(comboButtons);
			this.executor = executor;
			this.command = command;
		}
		@Override
		public void performComboAction() {
			executor.execute(command);
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comboElements == null) ? 0 : comboElements.hashCode());
		result = prime * result + ((command == null) ? 0 : command.hashCode());
		result = prime * result + ((executor == null) ? 0 : executor.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ConfigurableComboListener other = (ConfigurableComboListener) obj;
		if (comboElements == null) {
			if (other.comboElements != null) return false;
		} else if (!comboElements.equals(other.comboElements)) return false;
		if (command == null) {
			if (other.command != null) return false;
		} else if (!command.equals(other.command)) return false;
		if (executor == null) {
			if (other.executor != null) return false;
		} else if (!executor.equals(other.executor)) return false;
		if (name == null) {
			if (other.name != null) return false;
		} else if (!name.equals(other.name)) return false;
		return true;
	}

	private static final Logger log = LoggerFactory.getLogger(ConfigurableComboListener.class);

}
