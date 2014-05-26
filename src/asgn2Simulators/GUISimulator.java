/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2Simulators 
 * 20/04/2014
 * 
 */
package asgn2Simulators;

import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Choice;
import java.awt.EventQueue;

import javax.swing.JToggleButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RefineryUtilities;

import asgn2CarParks.CarPark;
import asgn2Examples.DTSCTest;
import asgn2Exceptions.SimulationException;
import asgn2Exceptions.VehicleException;

import javax.swing.JTextPane;
import javax.swing.JTextArea;

/**
 * @author hogan
 *
 */
@SuppressWarnings("serial")
public class GUISimulator extends JFrame implements Runnable {
	private JTextField randomSeed;
	private JTextField avgStaySD;
	private JTextField avgStay;
	private JTextField carProb;
	private JTextField smallCarProb;
	private JTextField motorCycleProb;
	private JTextField maxSmallCars;
	private JTextField maxQueue;
	private JTextField maxCars;
	private JTextField maxMotorCycles;
	private static GUISimulator gui;
	private static CarPark cp;
	private static Simulator s;
	private JTextArea logText;

	/**
	 * @param arg0
	 * @throws HeadlessException
	 * @throws SimulationException 
	 * @throws VehicleException 
	 */
	public GUISimulator(String arg0) throws HeadlessException, VehicleException, SimulationException {
		super(arg0);
		setTitle("Car Park Simulator");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 670, 241);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblRandomSeed = new JLabel("Random Seed");
		lblRandomSeed.setBounds(105, 38, 79, 16);
		panel.add(lblRandomSeed);
		
		JLabel lblUserInputs = new JLabel("User Inputs");
		lblUserInputs.setBounds(312, 11, 56, 14);
		panel.add(lblUserInputs);
		
		randomSeed = new JTextField();
		randomSeed.setBounds(195, 36, 86, 20);
		panel.add(randomSeed);
		randomSeed.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Average Stay");
		lblNewLabel.setBounds(109, 102, 75, 16);
		panel.add(lblNewLabel);
		
		JLabel lblAverageStaySd = new JLabel("Average Stay SD");
		lblAverageStaySd.setBounds(90, 69, 94, 16);
		panel.add(lblAverageStaySd);
		
		JLabel lblNewLabel_1 = new JLabel("Car Prob");
		lblNewLabel_1.setBounds(399, 38, 50, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblSmallCarProb = new JLabel("Small Car Prob");
		lblSmallCarProb.setBounds(364, 69, 85, 16);
		panel.add(lblSmallCarProb);
		
		JLabel lblMotorCycleProb = new JLabel("Motor Cycle Prob");
		lblMotorCycleProb.setBounds(352, 102, 97, 16);
		panel.add(lblMotorCycleProb);
		
		avgStaySD = new JTextField();
		avgStaySD.setColumns(10);
		avgStaySD.setBounds(195, 67, 86, 20);
		panel.add(avgStaySD);
		
		avgStay = new JTextField();
		avgStay.setColumns(10);
		avgStay.setBounds(195, 100, 86, 20);
		panel.add(avgStay);
		
		carProb = new JTextField();
		carProb.setColumns(10);
		carProb.setBounds(461, 36, 86, 20);
		panel.add(carProb);
		
		smallCarProb = new JTextField();
		smallCarProb.setColumns(10);
		smallCarProb.setBounds(461, 67, 86, 20);
		panel.add(smallCarProb);
		
		motorCycleProb = new JTextField();
		motorCycleProb.setColumns(10);
		motorCycleProb.setBounds(461, 100, 86, 20);
		panel.add(motorCycleProb);
		
		maxSmallCars = new JTextField();
		maxSmallCars.setColumns(10);
		maxSmallCars.setBounds(195, 166, 86, 20);
		panel.add(maxSmallCars);
		
		maxQueue = new JTextField();
		maxQueue.setColumns(10);
		maxQueue.setBounds(461, 166, 86, 20);
		panel.add(maxQueue);
		
		maxCars = new JTextField();
		maxCars.setColumns(10);
		maxCars.setBounds(195, 134, 86, 20);
		panel.add(maxCars);
		
		maxMotorCycles = new JTextField();
		maxMotorCycles.setColumns(10);
		maxMotorCycles.setBounds(461, 134, 86, 20);
		panel.add(maxMotorCycles);
		
		JLabel lblMaxCars = new JLabel("Max Cars");
		lblMaxCars.setBounds(130, 136, 54, 16);
		panel.add(lblMaxCars);
		
		JLabel lblMaxSmallCars = new JLabel("Max Small Cars");
		lblMaxSmallCars.setBounds(95, 168, 89, 16);
		panel.add(lblMaxSmallCars);
		
		JLabel lblMaxMotorCycles = new JLabel("Max Motor Cycles");
		lblMaxMotorCycles.setBounds(348, 136, 101, 16);
		panel.add(lblMaxMotorCycles);
		
		JLabel lblMaxQueueSize = new JLabel("Max Queue Size");
		lblMaxQueueSize.setBounds(358, 168, 91, 16);
		panel.add(lblMaxQueueSize);
		
		JButton btnStartSimulation = new JButton("Start Simulation");
		btnStartSimulation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				run();
			}
		});
		btnStartSimulation.setBounds(280, 207, 107, 23);
		panel.add(btnStartSimulation);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(30, 278, 618, 285);
		getContentPane().add(tabbedPane);
		
		JScrollPane log = new JScrollPane();
		tabbedPane.addTab("Log Output", null, log, null);
		
		logText = new JTextArea();
		logText.setEditable(false);
		log.setViewportView(logText);
		
		JPanel plotSeries = new JPanel();
		tabbedPane.addTab("Plot Series", null, plotSeries, null);
		
//		final TimeSeriesCollection dataset = createTimeSeriesData(); 
//        JFreeChart chart = createChart(dataset);
		
		JPanel summary = new JPanel();
		tabbedPane.addTab("Summary", null, summary, null);

//		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		cp = new CarPark();
		try {
			s = new Simulator();
			logOutput();
		} catch (VehicleException | SimulationException e) {
			e.printStackTrace();
		}
		
	}

	private void logOutput() throws VehicleException, SimulationException {
		for (int time=0; time<=Constants.CLOSING_TIME; time++) {
			//queue elements exceed max waiting time
			if (!cp.queueEmpty()) {
				cp.archiveQueueFailures(time);
			}
			//vehicles whose time has expired
			if (!cp.carParkEmpty()) {
				//force exit at closing time, otherwise normal
				boolean force = (time == Constants.CLOSING_TIME);
				cp.archiveDepartingVehicles(time, force);
			}
			//attempt to clear the queue 
			if (!cp.carParkFull()) {
				cp.processQueue(time,s);
			}
			// new vehicles from minute 1 until the last hour
			if (newVehiclesAllowed(time)) { 
				cp.tryProcessNewVehicles(time,s);
			}
			//Log progress 
			logText.append(cp.getStatus(time)); 
			String str = cp.getStatus(time);
			String[] split = str.split("::");
			for(String s : split){
				String[] value = s.split(":");
				System.out.println(value[1]);
				System.out.println(value[3]);
				System.out.println(value[5]);
				System.out.println(value[7]);
				System.out.println(value[9]);
				System.out.println(value[11]);
				System.out.println(value[13]);
				System.out.println(value[15]);
			}
		}
	}
	
	
    /**
     * Private method creates the dataset. Lots of hack code in the 
     * middle, but you should use the labelled code below  
	 * @return collection of time series for the plot 
     * @throws SimulationException 
     * @throws VehicleException 
	 */
//	private TimeSeriesCollection createTimeSeriesData() throws VehicleException, SimulationException {
//		TimeSeriesCollection tsc = new TimeSeriesCollection(); 
//		TimeSeries vehTotal = new TimeSeries("Total Vehicles");
//		TimeSeries parkedTotal = new TimeSeries("Parked Vehicles"); 
//		TimeSeries carsTotal = new TimeSeries("Cars");
//		TimeSeries smallCarsTotal = new TimeSeries("Small Cars");
//		TimeSeries MotorCyclesTotal = new TimeSeries("Motor Cycles");
//		TimeSeries queueTotal = new TimeSeries("Queue");
//		TimeSeries archivedTotal = new TimeSeries("Archived");
//		TimeSeries dissatisfiedTotal = new TimeSeries("Dissatisfied");
//		
//		Calendar cal = GregorianCalendar.getInstance();
//		
//		
//		Date timePoint;
//		
//		
//		for (int time=0; time<=Constants.CLOSING_TIME; time++) {
//			cal.set(2014,0,1,6,time);
//			timePoint = cal.getTime();
//			
//			int count=0;
//			int spaces=0;
//			int cars=0;
//			int smallcars=0;
//			int motorcycles=0;
//			int dissatisfied=0;
//			int archived=0;
//			int queued=0;
//			
//			
//			//queue elements exceed max waiting time
//			if (!cp.queueEmpty()) {
//				cp.archiveQueueFailures(time);
//			}
//			//vehicles whose time has expired
//			if (!cp.carParkEmpty()) {
//				//force exit at closing time, otherwise normal
//				boolean force = (time == Constants.CLOSING_TIME);
//				cp.archiveDepartingVehicles(time, force);
//			}
//			//attempt to clear the queue 
//			if (!cp.carParkFull()) {
//				cp.processQueue(time,s);
//			}
//			// new vehicles from minute 1 until the last hour
//			if (newVehiclesAllowed(time)) { 
//				cp.tryProcessNewVehicles(time,s);
//			}
//			//Log progress 
//			String str = cp.getStatus(time);
//			String[] split = str.split("::");
//			for(String s : split){
//				String[] value = s.split(":");
//				count = Integer.parseInt(value[]);
//				spaces = Integer.parseInt(value[3]);
//				cars = Integer.parseInt(value[5]);
//				smallcars = Integer.parseInt(value[7]);
//				motorcycles = Integer.parseInt(value[9]);
//				dissatisfied = Integer.parseInt(value[11]);
//				archived = Integer.parseInt(value[13]);
//				queued = Integer.parseInt(value[15]);
//			}
//			
//			vehTotal.add(new Minute(timePoint),count);
//			parkedTotal.add(new Minute(timePoint),spaces);
//			carsTotal.add(new Minute(timePoint),cars);
//			smallCarsTotal.add(new Minute(timePoint),smallcars);
//			MotorCyclesTotal.add(new Minute(timePoint),motorcycles);
//			dissatisfiedTotal.add(new Minute(timePoint),dissatisfied);
//			archivedTotal.add(new Minute(timePoint),archived);
//			queueTotal.add(new Minute(timePoint),queued);
//		}
//		
//		tsc.addSeries(vehTotal);
//		tsc.addSeries(parkedTotal);
//		tsc.addSeries(carsTotal);
//		tsc.addSeries(smallCarsTotal);
//		tsc.addSeries(MotorCyclesTotal);
//		tsc.addSeries(dissatisfiedTotal);
//		tsc.addSeries(archivedTotal);
//		tsc.addSeries(queueTotal);
//		return tsc; 
//		
//		
//
//	}
//	
//    private JFreeChart createChart(final XYDataset dataset) {
//        final JFreeChart result = ChartFactory.createTimeSeriesChart(
//            "", "hh:mm:ss", "Vehicles", dataset, true, true, false);
//        final XYPlot plot = result.getXYPlot();
//        ValueAxis domain = plot.getDomainAxis();
//        domain.setAutoRange(true);
//        ValueAxis range = plot.getRangeAxis();
//        range.setAutoRange(true);
//        return result;
//    }
	
	/**
	 * Helper method to determine if new vehicles are permitted
	 * @param time int holding current simulation time
	 * @return true if new vehicles permitted, false if not allowed due to simulation constraints. 
	 */
	private boolean newVehiclesAllowed(int time) {
		boolean allowed = (time >=1);
		return allowed && (time <= (Constants.CLOSING_TIME - 60));
	}
	

	/**
	 * @param args
	 * @throws SimulationException 
	 * @throws VehicleException 
	 * @throws HeadlessException 
	 */
	public static void main(String[] args) throws SimulationException, HeadlessException, VehicleException {

			 
			 cp = new CarPark();
			 s = new Simulator();
			 gui = new GUISimulator("Carpark");
			 gui.setVisible(true);
		  
		
	}
}
