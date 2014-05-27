/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2Vehicles 
 * 19/04/2014
 * 
 */
package asgn2Vehicles;

import asgn2Exceptions.VehicleException;
import asgn2Simulators.Constants;



/**
 * Vehicle is an abstract class specifying the basic state of a vehicle and the methods used to 
 * set and access that state. A vehicle is created upon arrival, at which point it must either 
 * enter the car park to take a vacant space or become part of the queue. If the queue is full, then 
 * the vehicle must leave and never enters the car park. The vehicle cannot be both parked and queued 
 * at once and both the constructor and the parking and queuing state transition methods must 
 * respect this constraint. 
 * 
 * Vehicles are created in a neutral state. If the vehicle is unable to park or queue, then no changes 
 * are needed if the vehicle leaves the carpark immediately.
 * Vehicles that remain and can't park enter a queued state via {@link #enterQueuedState() enterQueuedState} 
 * and leave the queued state via {@link #exitQueuedState(int) exitQueuedState}. 
 * Note that an exception is thrown if an attempt is made to join a queue when the vehicle is already 
 * in the queued state, or to leave a queue when it is not. 
 * 
 * Vehicles are parked using the {@link #enterParkedState(int, int) enterParkedState} method and depart using 
 * {@link #exitParkedState(int) exitParkedState}
 * 
 * Note again that exceptions are thrown if the state is inappropriate: vehicles cannot be parked or exit 
 * the car park from a queued state. 
 * 
 * The method javadoc below indicates the constraints on the time and other parameters. Other time parameters may 
 * vary from simulation to simulation and so are not constrained here.  
 * 
 * @author hogan
 *
 */
public abstract class Vehicle {
	
	private String vehID;
	private int arrivalTime;
	private int departureTime;
	private int parkingTime;
	private boolean isParked;
	private boolean isQueued;
	private boolean wasParked;
	private boolean wasQueued;
	private int exitTime;
	
	/**
	 * Vehicle Constructor 
	 * @param vehID String identification number or plate of the vehicle
	 * @param arrivalTime int time (minutes) at which the vehicle arrives and is 
	 *        either queued, given entry to the car park or forced to leave
	 * @throws VehicleException if arrivalTime is <= 0 
	 * @author Matthew Van Der Boor
	 */
	public Vehicle(String vehID,int arrivalTime) throws VehicleException  {
		if (arrivalTime <= 0){
			throw new VehicleException ("Arrival time is <= 0");
		}
		this.vehID = vehID;
		this.arrivalTime = arrivalTime;
	}
	
  
	/**
	 * Transition vehicle to parked state (mutator)
	 * Parking starts on arrival or on exit from the queue, but time is set here
	 * @param parkingTime int time (minutes) at which the vehicle was able to park
	 * @param intendedDuration int time (minutes) for which the vehicle is intended to remain in the car park.
	 *  	  Note that the parkingTime + intendedDuration yields the departureTime
	 * @throws VehicleException if the vehicle is already in a parked or queued state, if parkingTime < 0, 
	 *         or if intendedDuration is less than the minimum prescribed in asgnSimulators.Constants
	 * @author Matthew Van Der Boor
	 */
	public void enterParkedState(int parkingTime, int intendedDuration) throws VehicleException {
		if (isParked || isQueued){
			throw new VehicleException ("Vehicle is already in a parked or queued state");
		}
		if (parkingTime <= 0){
			throw new VehicleException ("Parking Time <= 0");
		}
		if (intendedDuration < Constants.MINIMUM_STAY){
			throw new VehicleException ("IntendedDuration is less than the minimum");
		}
		isParked = true;
		this.parkingTime = parkingTime;
		this.departureTime = parkingTime + intendedDuration;
	}
	
	/**
	 * Transition vehicle to queued state (mutator) 
	 * Queuing formally starts on arrival and ceases with a call to {@link #exitQueuedState(int) exitQueuedState}
	 * @throws VehicleException if the vehicle is already in a queued or parked state
	 * @author Matthew Van Der Boor
	 * @author Ashley Maletz
	 */
	public void enterQueuedState() throws VehicleException {
		if(isQueued || isParked)
			throw new VehicleException ("Vehicle is already in a queued or parked state");
		isQueued = true;
	}
	
	/**
	 * Transition vehicle from parked state (mutator) 
	 * @param departureTime int holding the actual departure time 
	 * @throws VehicleException if the vehicle is not in a parked state, is in a queued 
	 * 		  state or if the revised departureTime < parkingTime
	 * @author Matthew Van Der Boor
	 */
	public void exitParkedState(int departureTime) throws VehicleException {
		if(!isParked)
			throw new VehicleException ("Vehicle is not in a parked state");
		if(isQueued)
			throw new VehicleException ("Vehicle is in a queued state");
		if(departureTime < parkingTime)
			throw new VehicleException ("Revised departureTime < parkingTime");
		
		isParked = false;
		wasParked = true;
		this.departureTime = departureTime;
	}

	/**
	 * Transition vehicle from queued state (mutator) 
	 * Queuing formally starts on arrival with a call to {@link #enterQueuedState() enterQueuedState}
	 * Here we exit and set the time at which the vehicle left the queue
	 * @param exitTime int holding the time at which the vehicle left the queue 
	 * @throws VehicleException if the vehicle is in a parked state or not in a queued state, or if 
	 *  exitTime is not later than arrivalTime for this vehicle
	 * @author Matthew Van Der Boor
	 */
	public void exitQueuedState(int exitTime) throws VehicleException {
		if(isParked)
			throw new VehicleException ("Vehicle is in a parked state");
		if(!isQueued)
			throw new VehicleException ("Vehicle is not in a queued state");
		if(exitTime <= arrivalTime)
			throw new VehicleException ("Exit time is not later than arrival time");
		
		isQueued = false;
		wasQueued = true;
		this.exitTime = exitTime;
	}
	
	/**
	 * Simple getter for the arrival time 
	 * @return the arrivalTime
	 * @author Matthew Van Der Boor
	 */
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	/**
	 * Simple getter for the departure time from the car park
	 * Note: result may be 0 before parking, show intended departure 
	 * time while parked; and actual when archived
	 * @return the departureTime
	 * @author Matthew Van Der Boor
	 */
	public int getDepartureTime() {
		return departureTime;
	}
	
	/**
	 * Simple getter for the parking time
	 * Note: result may be 0 before parking
	 * @return the parkingTime
	 * @author Matthew Van Der Boor
	 */
	public int getParkingTime() {
		return parkingTime;
	}

	/**
	 * Simple getter for the vehicle ID
	 * @return the vehID
	 * @author Matthew Van Der Boor
	 */
	public String getVehID() {
		return vehID;
	}

	/**
	 * Boolean status indicating whether vehicle is currently parked 
	 * @return true if the vehicle is in a parked state; false otherwise
	 * @author Matthew Van Der Boor
	 */
	public boolean isParked() {
		return isParked;
	}

	/**
	 * Boolean status indicating whether vehicle is currently queued
	 * @return true if vehicle is in a queued state, false otherwise 
	 * @author Matthew Van Der Boor
	 */
	public boolean isQueued() {
		return isQueued;
	}
	
	/**
	 * Boolean status indicating whether customer is satisfied or not
	 * Satisfied if they park; dissatisfied if turned away, or queuing for too long 
	 * Note that calls to this method may not reflect final status 
	 * @return true if satisfied, false if never in parked state or if queuing time exceeds max allowable 
	 * @author Matthew Van Der Boor
	 */
	public boolean isSatisfied() {
		if(wasParked || isParked){
			return true;
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @author Matthew Van Der Boor
	 */
	@Override
	public String toString() {
		String queing;
		String parking;
		String satisfied;
		String exceeded;
		if(!wasQueued){
			queing = "\nVehicle was not queued";
		}else{
			queing = "\nExit from Queue: " + exitTime +
					 "\nQueuing Time: " + (exitTime-arrivalTime);
		}
		
		if(!wasParked){
			parking =  "\nVehicle was not parked";
		}else{
			parking = "\nEntry to Car Park: " + parkingTime +
					  "\nExit from Car Park: " + departureTime +
					  "\nParking Time: " + (departureTime-parkingTime);
		}
		
		if(isSatisfied()){
			satisfied = "\nCustomer was satisfied";
		}else{
			satisfied = "\nCustomer was not satisfied";
		}
		
		if(exitTime-arrivalTime > Constants.MAXIMUM_QUEUE_TIME){
			exceeded = "\nExceeded maximum acceptable queuing time by: " + ((exitTime-arrivalTime) - Constants.MAXIMUM_QUEUE_TIME);
		}else{
			exceeded = "";
		}
		
		return "Vehicle vehID: " + vehID +
				"\nArrival Time: " + arrivalTime +
				queing + exceeded + parking + satisfied;
		}

	/**
	 * Boolean status indicating whether vehicle was ever parked
	 * Will return false for vehicles in queue or turned away 
	 * @return true if vehicle was or is in a parked state, false otherwise 
	 * @author Matthew Van Der Boor
	 */
	public boolean wasParked() {
		return wasParked;
	}

	/**
	 * Boolean status indicating whether vehicle was ever queued
	 * @return true if vehicle was or is in a queued state, false otherwise 
	 * @author Matthew Van Der Boor
	 */
	public boolean wasQueued() {
		return wasQueued;
	}
}
