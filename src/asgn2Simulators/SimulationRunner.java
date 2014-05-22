/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2Simulators 
 * 23/04/2014
 * 
 */
package asgn2Simulators;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

import asgn2CarParks.CarPark;
import asgn2Exceptions.SimulationException;
import asgn2Exceptions.VehicleException;

/**
 * Class to operate the simulation, taking parameters and utility methods from the Simulator
 * to control the CarPark, and using Log to provide a record of operation. 
 * @author hogan
 *
 */
public class SimulationRunner {
	private CarPark carPark;
	private Simulator sim;
	private Log log;
	
	/**
	 * Constructor just does initialisation 
	 * @param carPark CarPark currently used 
	 * @param sim Simulator containing simulation parameters
	 * @param log Log to provide logging services 
	 */
	public SimulationRunner(CarPark carPark, Simulator sim,Log log) {
		this.carPark = carPark;
		this.sim = sim;
		this.log = log;
	}
	
	
	/**
	 * Method to run the simulation from start to finish. Exceptions are propagated upwards from Vehicle,
	 * Simulation and Log objects as necessary 
	 * @throws VehicleException if Vehicle creation or operation constraints violated 
	 * @throws SimulationException if Simulation constraints are violated 
	 * @throws IOException on logging failures
	 */
	public void runSimulation() throws VehicleException, SimulationException, IOException {
		this.log.initialEntry(this.carPark,this.sim);
		for (int time=0; time<=Constants.CLOSING_TIME; time++) {
			//queue elements exceed max waiting time
			if (!this.carPark.queueEmpty()) {
				this.carPark.archiveQueueFailures(time);
			}
			//vehicles whose time has expired
			if (!this.carPark.carParkEmpty()) {
				//force exit at closing time, otherwise normal
				boolean force = (time == Constants.CLOSING_TIME);
				this.carPark.archiveDepartingVehicles(time, force);
			}
			//attempt to clear the queue 
			if (!this.carPark.carParkFull()) {
				this.carPark.processQueue(time,this.sim);
			}
			// new vehicles from minute 1 until the last hour
			if (newVehiclesAllowed(time)) { 
				this.carPark.tryProcessNewVehicles(time,this.sim);
			}
			//Log progress 
			this.log.logEntry(time,this.carPark);
		}
		this.log.finalise(this.carPark);
	}

	/**
	 * Main program for the simulation 
	 * @param args Arguments to the simulation 
	 * @throws SimulationException 
	 * @throws VehicleException 
	 */
	public static void main(String[] args) throws VehicleException, SimulationException {
		CarPark cp = new CarPark();
		Simulator s = null;
		Log l = null; 
		try {
			s = new Simulator();
			l = new Log();
		} catch (IOException | SimulationException e1) {
			e1.printStackTrace();
			System.exit(-1);
		}
		
		//TODO: Implement Argument Processing 
		cp.userInput = true;
		
		System.out.println("Enter Max Car Spaces : ");
		String maxCar;		
	    Scanner scanIn = new Scanner(System.in);
	    maxCar = scanIn.nextLine();	          
	    System.out.println(maxCar);
	    Constants.maxCarSpaces = Integer.parseInt(maxCar);
	    
	    System.out.println("Enter Max Small Car Spaces : ");
		String maxSmallCar;		
	    Scanner scanIn1 = new Scanner(System.in);
	    maxSmallCar = scanIn1.nextLine();	           
	    System.out.println(maxSmallCar);
	    Constants.maxSmallCarSpaces = Integer.parseInt(maxSmallCar);
	    
	    System.out.println("Enter Max MotorCycle Spaces : ");
		String maxMCS;		
	    Scanner scanIn2 = new Scanner(System.in);
	    maxMCS = scanIn2.nextLine();	         
	    System.out.println(maxMCS);
	    Constants.maxMotorCycleSpaces = Integer.parseInt(maxMCS);
	    
	    System.out.println("Enter Max Max Queue Size : ");
		String maxQueue;		
	    Scanner scanIn3= new Scanner(System.in);
	    maxQueue = scanIn3.nextLine();	            
	    System.out.println(maxQueue);
	    Constants.maxQueueSize = Integer.parseInt(maxQueue);
	    
	    scanIn.close();
	    scanIn1.close();
	    scanIn2.close();
	    scanIn3.close();
	    
	    if(Constants.maxCarSpaces == 0 || Constants.maxSmallCarSpaces == 0 || Constants.maxMotorCycleSpaces == 0 || Constants.maxQueueSize == 0){
	    	cp.userInput = false;
	    }
	    
	    
		
		
	    
		//cp.tryProcessNewVehicles(1, s);
		//System.out.println(cp.getStatus(1));
		//Run the simulation 
		SimulationRunner sr = new SimulationRunner(cp,s,l);
		try {
			sr.runSimulation();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		} 
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

}
