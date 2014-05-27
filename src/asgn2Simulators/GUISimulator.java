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
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import asgn2CarParks.CarPark;
import asgn2Exceptions.SimulationException;
import asgn2Exceptions.VehicleException;

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
	private JPanel plotSeries;
	private JPanel summary;
	private JLabel errorMsg;


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
		panel.setBounds(98, 12, 670, 241);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblRandomSeed = new JLabel("Random Seed");
		lblRandomSeed.setBounds(105, 38, 79, 16);
		panel.add(lblRandomSeed);
		
		JLabel lblUserInputs = new JLabel("User Inputs");
		lblUserInputs.setBounds(312, 11, 65, 16);
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
		btnStartSimulation.setBounds(12, 203, 125, 26);
		panel.add(btnStartSimulation);
		
		errorMsg = new JLabel("");
		errorMsg.setBounds(155, 208, 294, 16);
		panel.add(errorMsg);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(30, 278, 818, 390);
		getContentPane().add(tabbedPane);
		
		JScrollPane log = new JScrollPane();
		tabbedPane.addTab("Log Output", null, log, null);
		
		logText = new JTextArea();
		logText.setEditable(false);
		log.setViewportView(logText);
		
		plotSeries = new JPanel();
		tabbedPane.addTab("Plot Series", null, plotSeries, null);
		plotSeries.setLayout(null);

		summary = new JPanel();
		tabbedPane.addTab("Summary", null, summary, null);
		summary.setLayout(null);

	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		try {
			if(valid()){
				plotGraph();
		        
		        logOutput();
		        
		        barGraph();
			}
	        
		} catch (VehicleException | SimulationException | IOException e) {
			e.printStackTrace();
		}
		
	}

	private void plotGraph() throws SimulationException, VehicleException {
		if(valid()){
			// create new time series graph
			TimeSeriesCollection dataset = createTimeSeriesData(); 
			JFreeChart chart = createChart(dataset);
			ChartPanel CP = new ChartPanel(chart);
			CP.setBounds(0, 0, 813, 362);
			plotSeries.removeAll();
			plotSeries.add(CP);
			plotSeries.repaint();
		}
	}

	private void barGraph() {
		// create new bar graph
		CategoryDataset datasetBar = createDataset();
		JFreeChart bar = createChart(datasetBar);
		ChartPanel chartPanel  = new ChartPanel(bar);
		chartPanel.setBounds(0, 0, 813, 362);
		summary.removeAll();
		summary.add(chartPanel);
		summary.repaint();
	}

	private void logOutput() throws VehicleException, SimulationException, IOException {
		// reset text area
		logText.setText("");
		Log log = new Log();
		// update log
        if(valid()){
        	log.initialEntry(cp, s);
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
				String status = cp.getStatus(time);
				log.writer.write(status);
				logText.append(status); 
		
			}
			log.finalise(cp);
        }
	}
	
	
    /**
     * Private method creates the dataset. Lots of hack code in the 
     * middle, but you should use the labelled code below  
	 * @return collection of time series for the plot 
     * @throws SimulationException 
     * @throws VehicleException 
	 */
	private TimeSeriesCollection createTimeSeriesData() throws VehicleException, SimulationException {
		TimeSeriesCollection tsc = new TimeSeriesCollection(); 
		TimeSeries vehTotal = new TimeSeries("Total Vehicles");
		TimeSeries parkedTotal = new TimeSeries("Parked Vehicles"); 
		TimeSeries carsTotal = new TimeSeries("Cars");
		TimeSeries smallCarsTotal = new TimeSeries("Small Cars");
		TimeSeries MotorCyclesTotal = new TimeSeries("Motor Cycles");
		TimeSeries queueTotal = new TimeSeries("Queue");
		TimeSeries archivedTotal = new TimeSeries("Archived");
		TimeSeries dissatisfiedTotal = new TimeSeries("Dissatisfied");
		
		Calendar cal = GregorianCalendar.getInstance();
		
		
		Date timePoint;
		
		
		for (int time=0; time<=Constants.CLOSING_TIME; time++) {
			cal.set(2014,0,1,6,time);
			timePoint = cal.getTime();
			
			int count=0;
			int spaces=0;
			int cars=0;
			int smallcars=0;
			int motorcycles=0;
			int dissatisfied=0;
			int archived=0;
			int queued=0;
			
			
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
			String str = cp.getStatus(time);
			String[] split = str.split("::");
			count = Integer.parseInt(split[1].replaceAll("[\\D]", ""));
			spaces = Integer.parseInt(split[2].replaceAll("[\\D]", ""));
			cars = Integer.parseInt(split[3].replaceAll("[\\D]", ""));
			smallcars = Integer.parseInt(split[4].replaceAll("[\\D]", ""));
			motorcycles = Integer.parseInt(split[5].replaceAll("[\\D]", ""));
			dissatisfied = Integer.parseInt(split[6].replaceAll("[\\D]", ""));
			archived = Integer.parseInt(split[7].replaceAll("[\\D]", ""));
			queued = Integer.parseInt(split[8].replaceAll("[\\D]", ""));
			
			vehTotal.add(new Minute(timePoint),count);
			parkedTotal.add(new Minute(timePoint),spaces);
			carsTotal.add(new Minute(timePoint),cars);
			smallCarsTotal.add(new Minute(timePoint),smallcars);
			MotorCyclesTotal.add(new Minute(timePoint),motorcycles);
			dissatisfiedTotal.add(new Minute(timePoint),dissatisfied);
			archivedTotal.add(new Minute(timePoint),archived);
			queueTotal.add(new Minute(timePoint),queued);
		}
		
		tsc.addSeries(vehTotal);
		tsc.addSeries(parkedTotal);
		tsc.addSeries(carsTotal);
		tsc.addSeries(smallCarsTotal);
		tsc.addSeries(MotorCyclesTotal);
		tsc.addSeries(dissatisfiedTotal);
		tsc.addSeries(archivedTotal);
		tsc.addSeries(queueTotal);
		return tsc; 

	}
	
	
	private CategoryDataset createDataset(){
		
		String str = cp.getStatus(Constants.CLOSING_TIME);
		String[] split = str.split("::");
		int count = Integer.parseInt(split[1].replaceAll("[\\D]", ""));
		int dissatisfied = Integer.parseInt(split[6].replaceAll("[\\D]", ""));
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(count, "Total Vehicles", "");
		dataset.addValue(dissatisfied, "Total Dissatisfied", "");
		return dataset;
		
	}
	
	
	private JFreeChart createChart(CategoryDataset dataset) {
		final JFreeChart result = ChartFactory.createBarChart(
	            "", "", "Value", dataset, PlotOrientation.VERTICAL,
	            true, true, false
	        );
		return result;
	}
	
    private JFreeChart createChart(XYDataset dataset) {
        JFreeChart result = ChartFactory.createTimeSeriesChart(
            "", "hh:mm:ss", "Vehicles", dataset, true, true, false);
        XYPlot plot = result.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
        ValueAxis range = plot.getRangeAxis();
        range.setAutoRange(true);
        plot.getRenderer().setSeriesPaint(0, Color.black);
        plot.getRenderer().setSeriesPaint(1, Color.blue);
        plot.getRenderer().setSeriesPaint(2, Color.cyan);
        plot.getRenderer().setSeriesPaint(3, Color.gray);
        plot.getRenderer().setSeriesPaint(4, Color.darkGray);
        plot.getRenderer().setSeriesPaint(5, Color.yellow);
        plot.getRenderer().setSeriesPaint(6, Color.green);
        plot.getRenderer().setSeriesPaint(7, Color.red);
        return result;
    }
	
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
		gui = new GUISimulator("Carpark");
		gui.setVisible(true);
		gui.setSize(900, 720);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  
		if(args.length != 0){
			if(args.length == 10){
				gui.maxCars.setText(args[0]);
				gui.maxSmallCars.setText(args[1]);
				gui.maxMotorCycles.setText(args[2]);
				gui.maxQueue.setText(args[3]);
				gui.randomSeed.setText(args[4]);
				gui.carProb.setText(args[5]);
				gui.smallCarProb.setText(args[6]);
				gui.motorCycleProb.setText(args[7]);
				gui.avgStay.setText(args[8]);
				gui.avgStaySD.setText(args[9]);
			}else if(args.length < 10){
				System.err.println("Not enough arguments");
			}
		}else{
			gui.maxCars.setText(Integer.toString(Constants.DEFAULT_MAX_CAR_SPACES));
			gui.maxSmallCars.setText(Integer.toString(Constants.DEFAULT_MAX_SMALL_CAR_SPACES));
			gui.maxMotorCycles.setText(Integer.toString(Constants.DEFAULT_MAX_MOTORCYCLE_SPACES));
			gui.maxQueue.setText(Integer.toString(Constants.DEFAULT_MAX_QUEUE_SIZE));
			gui.randomSeed.setText(Integer.toString(Constants.DEFAULT_SEED));
			gui.carProb.setText(Double.toString(Constants.DEFAULT_CAR_PROB));
			gui.smallCarProb.setText(Double.toString(Constants.DEFAULT_SMALL_CAR_PROB));
			gui.motorCycleProb.setText(Double.toString(Constants.DEFAULT_MOTORCYCLE_PROB));
			gui.avgStay.setText(Double.toString(Constants.DEFAULT_INTENDED_STAY_MEAN));
			gui.avgStaySD.setText(Double.toString(0.33*Constants.DEFAULT_INTENDED_STAY_MEAN));
		}
		
		
	}

	private boolean valid() throws SimulationException {
		int maxCarSpaces =Integer.parseInt(gui.maxCars.getText());
		int maxSmallCarSpaces =Integer.parseInt(gui.maxSmallCars.getText());
		int maxMotorCycleSpaces =Integer.parseInt(gui.maxMotorCycles.getText());
		int maxQueueSize = Integer.parseInt(gui.maxQueue.getText());
		int seed =Integer.parseInt(gui.randomSeed.getText());
		double carProb =Double.parseDouble(gui.carProb.getText());
		double smallCarProb =Double.parseDouble(gui.smallCarProb.getText());
		double motorCycleProb =Double.parseDouble(gui.motorCycleProb.getText());
		double avgStay =Double.parseDouble(gui.avgStay.getText());
		double avgStaySD =Double.parseDouble(gui.avgStaySD.getText());
		
		if(maxCarSpaces >= 0 && maxMotorCycleSpaces >= 0 &&
				maxQueueSize >= 0 && maxSmallCarSpaces >= 0){
			if(maxSmallCarSpaces <= maxCarSpaces){
				// set up carpark and simulator with users fields 
				cp = new CarPark(maxCarSpaces, maxSmallCarSpaces, maxMotorCycleSpaces, maxQueueSize);
				s = new Simulator(seed, avgStay, avgStaySD, carProb, smallCarProb, motorCycleProb);
				gui.errorMsg.setText("");
				return true;
				
			}else{
				//display error message
				gui.errorMsg.setText("Max Small Cars must not be larger than Max Cars.");
				return false;
			}
		}else{
			//display error message
			gui.errorMsg.setText("Vaules cannot be negetive.");
			return false;
		}
	}
}
