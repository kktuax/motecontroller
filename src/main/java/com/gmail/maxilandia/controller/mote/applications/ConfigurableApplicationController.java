package com.gmail.maxilandia.controller.mote.applications;

import java.io.OutputStream;
import java.io.Reader;

import com.gmail.maxilandia.controller.SystemExecutor;
import com.gmail.maxilandia.controller.mote.MoteButton;
import com.thoughtworks.xstream.XStream;

public class ConfigurableApplicationController implements MoteControlledApplication{

	public ConfigurableApplicationController(){
		this.applicationCommands = new ApplicationCommands();
	}
	
	private String applicationName;
	
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	
	public String getInit() {
		return applicationCommands.init;
	}

	public void setInit(String init) {
		applicationCommands.init = init;
	}

	public String getHome() {
		return applicationCommands.home;
	}

	public void setHome(String home) {
		this.applicationCommands.home = home;
	}

	public String getPlus() {
		return applicationCommands.plus;
	}

	public void setPlus(String plus) {
		this.applicationCommands.plus = plus;
	}

	public String getMinus() {
		return applicationCommands.minus;
	}

	public void setMinus(String minus) {
		this.applicationCommands.minus = minus;
	}

	public String getUp() {
		return applicationCommands.up;
	}

	public void setUp(String up) {
		this.applicationCommands.up = up;
	}

	public String getDown() {
		return applicationCommands.down;
	}

	public void setDown(String down) {
		this.applicationCommands.down = down;
	}

	public String getRight() {
		return applicationCommands.right;
	}

	public void setRight(String right) {
		this.applicationCommands.right = right;
	}

	public String getLeft() {
		return applicationCommands.left;
	}

	public void setLeft(String left) {
		this.applicationCommands.left = left;
	}

	public String getOne() {
		return applicationCommands.one;
	}

	public void setOne(String one) {
		this.applicationCommands.one = one;
	}

	public String getTwo() {
		return applicationCommands.two;
	}

	public void setTwo(String two) {
		this.applicationCommands.two = two;
	}

	public String getA() {
		return applicationCommands.a;
	}

	public void setA(String a) {
		this.applicationCommands.a = a;
	}

	public String getB() {
		return applicationCommands.b;
	}

	public void setB(String b) {
		this.applicationCommands.b = b;
	}
	
	private final ApplicationCommands applicationCommands;
	
	private SystemExecutor executor;
	
	public void setExecutor(SystemExecutor executor) {
		this.executor = executor;
	}

	public void initApplication() {
		if(getInit() != null){
			executor.execute(getInit());
		}
	}

	public void actionOnButtonPushed(MoteButton button) {
		switch (button) {
			case HOME: 
				if(getHome() != null) executor.execute(getHome()); 
				break;
			case MINUS: 
				if(getMinus() != null) executor.execute(getMinus()); 
				break;
			case PLUS: if(getPlus() != null) 
				executor.execute(getPlus()); 
				break;
			case UP: 
				if(getUp() != null) executor.execute(getUp()); 
				break;
			case DOWN: 
				if(getDown() != null) executor.execute(getDown()); 
				break;
			case LEFT: 
				if(getLeft() != null) executor.execute(getLeft()); 
				break;
			case RIGHT: 
				if(getRight() != null) executor.execute(getRight()); 
				break;
			case ONE:
				if(getOne() != null) executor.execute(getOne()); 
				break;
			case TWO: 
				if(getTwo() != null) executor.execute(getTwo()); 
				break;
			case A: 
				if(getA() != null) executor.execute(getA()); 
				break;
			case B: 
				if(getB() != null) executor.execute(getB()); 
				break;
		}
		
	}

	private static class ApplicationCommands{
		
		private String init, home, plus, minus, up, down, right, left, one, two, a, b;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((a == null) ? 0 : a.hashCode());
			result = prime * result + ((b == null) ? 0 : b.hashCode());
			result = prime * result + ((down == null) ? 0 : down.hashCode());
			result = prime * result + ((home == null) ? 0 : home.hashCode());
			result = prime * result + ((init == null) ? 0 : init.hashCode());
			result = prime * result + ((left == null) ? 0 : left.hashCode());
			result = prime * result + ((minus == null) ? 0 : minus.hashCode());
			result = prime * result + ((one == null) ? 0 : one.hashCode());
			result = prime * result + ((plus == null) ? 0 : plus.hashCode());
			result = prime * result + ((right == null) ? 0 : right.hashCode());
			result = prime * result + ((two == null) ? 0 : two.hashCode());
			result = prime * result + ((up == null) ? 0 : up.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			ApplicationCommands other = (ApplicationCommands) obj;
			if (a == null) {
				if (other.a != null) return false;
			} else if (!a.equals(other.a)) return false;
			if (b == null) {
				if (other.b != null) return false;
			} else if (!b.equals(other.b)) return false;
			if (down == null) {
				if (other.down != null) return false;
			} else if (!down.equals(other.down)) return false;
			if (home == null) {
				if (other.home != null) return false;
			} else if (!home.equals(other.home)) return false;
			if (init == null) {
				if (other.init != null) return false;
			} else if (!init.equals(other.init)) return false;
			if (left == null) {
				if (other.left != null) return false;
			} else if (!left.equals(other.left)) return false;
			if (minus == null) {
				if (other.minus != null) return false;
			} else if (!minus.equals(other.minus)) return false;
			if (one == null) {
				if (other.one != null) return false;
			} else if (!one.equals(other.one)) return false;
			if (plus == null) {
				if (other.plus != null) return false;
			} else if (!plus.equals(other.plus)) return false;
			if (right == null) {
				if (other.right != null) return false;
			} else if (!right.equals(other.right)) return false;
			if (two == null) {
				if (other.two != null) return false;
			} else if (!two.equals(other.two)) return false;
			if (up == null) {
				if (other.up != null) return false;
			} else if (!up.equals(other.up)) return false;
			return true;
		}
	}
	
	public void writeXML(OutputStream out){
		getXStream().toXML(this, out);
	}
	
	public static ConfigurableApplicationController readXML(Reader in){
		return (ConfigurableApplicationController) getXStream().fromXML(in);
	}
	
	private static XStream getXStream(){
		XStream xs = new XStream();
		xs.alias("applicationController", ConfigurableApplicationController.class);
		return xs;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicationCommands == null) ? 0 : applicationCommands.hashCode());
		result = prime * result + ((applicationName == null) ? 0 : applicationName.hashCode());
		result = prime * result + ((executor == null) ? 0 : executor.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ConfigurableApplicationController other = (ConfigurableApplicationController) obj;
		if (applicationCommands == null) {
			if (other.applicationCommands != null) return false;
		} else if (!applicationCommands.equals(other.applicationCommands)) return false;
		if (applicationName == null) {
			if (other.applicationName != null) return false;
		} else if (!applicationName.equals(other.applicationName)) return false;
		if (executor == null) {
			if (other.executor != null) return false;
		} else if (!executor.equals(other.executor)) return false;
		return true;
	}
	
}
