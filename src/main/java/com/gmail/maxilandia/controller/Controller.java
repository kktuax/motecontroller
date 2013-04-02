package com.gmail.maxilandia.controller;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import motej.Mote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmail.maxilandia.controller.mote.ConfigurableComboListener;
import com.gmail.maxilandia.controller.mote.MoteComboButtonListener;
import com.gmail.maxilandia.controller.mote.SimpleMoteFinder;
import com.gmail.maxilandia.controller.mote.applications.ConfigurableApplicationController;
import com.gmail.maxilandia.controller.mote.applications.MoteApplicationListener;
import com.gmail.maxilandia.controller.mote.applications.MoteControlledApplication;

public class Controller {

    public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Controller window = new Controller();
				window.frmWiimoteController.setVisible(true);
			}
		});
	}
	
	private Controller(){
		
		if(!configurationFolder.exists()) configurationFolder.mkdir();
		
		final Map<String, MoteControlledApplication> controlledApplications = getControlledApplications(getSystemExecutor());
		
		this.frmWiimoteController = new JFrame();
		frmWiimoteController.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				arg0.getWindow().dispose();
				System.exit(0);
			}
		});
		frmWiimoteController.setTitle("Wiimote Controller");
		this.frmWiimoteController.setBounds(100, 100, 300, 300);
		
		JSeparator separator = new JSeparator();
		frmWiimoteController.getContentPane().add(separator, BorderLayout.CENTER);
		
		syncButton = new JButton("Syncronize");
		syncButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				progressBar_1 = new JProgressBar();
				progressBar_1.setIndeterminate(true);
				frmWiimoteController.getContentPane().add(progressBar_1, BorderLayout.SOUTH);
				
				Syncronize task = new Syncronize();
				task.execute();
				task.addPropertyChangeListener(new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						log.info("PropertyChangeEvent:" + evt.getNewValue());
						syncButton.setText("Syncronizing...");
						syncButton.setEnabled(false);
						progressBar_1.setVisible(true);
						if ("DONE".equals(evt.getNewValue().toString())) {
							progressBar_1.setVisible(false);
							if(mote != null){
								syncButton.setText("Syncronized OK");
								applicationList.setEnabled(true);
							}else{
								syncButton.setText("Syncronize");
								syncButton.setEnabled(true);
							}
						}
					}
				});
			}
		});
		
		frmWiimoteController.getContentPane().add(syncButton, BorderLayout.NORTH);
		
		List<String> availableApplications = new ArrayList<String>();
		
		for(String application : controlledApplications.keySet()){
			availableApplications.add(application);
		}
		
		applicationList = new JList(availableApplications.toArray());
		applicationList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(!arg0.getValueIsAdjusting()){
					int[] idx = applicationList.getSelectedIndices();
					if(idx.length > 0){
						String selectedApp = applicationList.getModel().getElementAt(idx[0]).toString();
						if(controlledApplications.containsKey(selectedApp)){
							if(applicationListener != null){
								log.info("Unselecting old application listener");
								mote.removeCoreButtonListener(applicationListener);
							}
							MoteControlledApplication mca = controlledApplications.get(selectedApp);
							applicationListener = new MoteApplicationListener(mca);
							log.info("Selecting application to control: " + selectedApp);
							mote.addCoreButtonListener(applicationListener);
							mca.initApplication();
						}else{
							log.error("Selected unkown app: " + selectedApp);
						}	
					}
				}
			}
		});
		applicationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		applicationList.setEnabled(false);
		frmWiimoteController.getContentPane().add(applicationList, BorderLayout.CENTER);
		
	}
	
	private static File configurationFolder = new File(System.getProperty("user.home"), ".motecontroller");
	
	private Map<String, MoteControlledApplication> getControlledApplications(SystemExecutor executor){
		Map<String, MoteControlledApplication> apps = new LinkedHashMap<String, MoteControlledApplication>();
		File applicationsFolder = new File(configurationFolder, "applications");
		if(!applicationsFolder.exists()) applicationsFolder.mkdir();
		if(applicationsFolder.exists() && applicationsFolder.isDirectory()){
			String[] applicationXmls = applicationsFolder.list();
			for(String applicationXml : applicationXmls){
				File applicationConfigFile = new File(applicationsFolder, applicationXml);
				try{
					ConfigurableApplicationController cac = ConfigurableApplicationController.readXML(new FileReader(applicationConfigFile));
					cac.setExecutor(executor);
					apps.put(cac.getApplicationName(), cac);
				}catch (Exception e) {
					log.warn("Error reading " + applicationConfigFile + ": " + e.getMessage());
				}
			}
		}
		return apps;
	}
	
	private Collection<MoteComboButtonListener> getComboListeners(SystemExecutor executor){
		Collection<MoteComboButtonListener> comboListeners = new HashSet<MoteComboButtonListener>(); 
		File combosFolder = new File(configurationFolder, "combos");
		if(!combosFolder.exists()) combosFolder.mkdir();
		if(combosFolder.exists() && combosFolder.isDirectory()){
			String[] combosXmls = combosFolder.list();
			for(String combosXml : combosXmls){
				File comboConfigFile = new File(combosFolder, combosXml);
				try{
					ConfigurableComboListener ccl = ConfigurableComboListener.readXML(new FileReader(comboConfigFile));
					ccl.setExecutor(executor);
					comboListeners.add(ccl.getMoteComboButtonListener());
				}catch (Exception e) {
					log.warn("Error reading " + comboConfigFile + ": " + e.getMessage());
				}
			}
		}
		return comboListeners;
	}
	
	private SystemExecutor executor;
	
	private SystemExecutor getSystemExecutor(){
		if(executor == null){
			executor = new DebianSystemExecutor();
		}
		return executor;
	}
	
	private JFrame frmWiimoteController;
	private JButton syncButton;
	private JList applicationList;
	private MoteApplicationListener applicationListener;
	private JProgressBar progressBar_1;
	
	private Mote mote;
	
	private static final Logger log = LoggerFactory.getLogger(Controller.class);
	
	private class Syncronize extends SwingWorker<Void, Void> {
		@Override
		public Void doInBackground() {
			log.info("Starting syncronizing task...");
			ExecutorService executor = new ThreadPoolExecutor(1, 1, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5));
			executor.execute(new Runnable() {
				public void run() {
					log.info("Starting syncronizing thread...");
					SimpleMoteFinder smf = SimpleMoteFinder.getInstance();
					Mote foundMote = smf.findMote();
					if(foundMote != null){
						mote = foundMote;
					}
				}
			});
			executor.shutdown();
			try {
				executor.awaitTermination(15, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				log.info(e.getMessage());
			}
			if(mote != null){
				log.info("Configuring mote...");
				mote.setPlayerLeds(new boolean[]{true, false, false, false});
				for(MoteComboButtonListener comboListener : getComboListeners(getSystemExecutor())){
					mote.addCoreButtonListener(comboListener);
				}
			}
			log.info("Finished syncronizing task");
			return null;
		}
	}

}
