/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2Tests 
 * 22/04/2014
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import java.lang.reflect.Array;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.VehicleException;
import asgn2Vehicles.Car;
import asgn2Vehicles.Vehicle;

/**
 * @author Matthew Van Der Boor
 *
 */
public class CarTests {
	
	private Car car;
	private Car smallCar;
	private final int NEGETIVE = -1;
	private final int ZERO = 0;
	private final int ARRIVALTIME = 1;
	private final int LARGE_NUMBER = 100000;
	private final String VEHID = "C1";
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		car = new Car("C1", ARRIVALTIME, false);
		smallCar = new Car("C2", ARRIVALTIME, true);
	}
	
	/**
	 * Call the constructor with a negative arrival time.
	 * @throws VehicleException
	 */
	@Test(expected=Exception.class)
	public void ConstructorNegetiveArrivalTime() throws VehicleException {
		new Car(VEHID, NEGETIVE, false);
	}
	
	/**
	 * Calls the constructor with an arrival time of zero.
	 * @throws VehicleException
	 */
	@Test(expected=Exception.class)
	public void ConstructorZeroArrivalTime() throws VehicleException {
		new Car(VEHID, ZERO, false);
	}
	
	/**
	 * Calls constuctor with an arrival time of one.
	 * @throws VehicleException
	 */
	@Test
	public void ConstructorOneArrivalTime() throws VehicleException {
		new Car(VEHID, ARRIVALTIME, false);
	}
	
	/**
	 * Calls the constructor with an ArivalTime larger than what it can handle.
	 * @throws VehicleException
	 */
	@Test
	public void ConstructorLargeArrivalTime() throws VehicleException {
		new Car(VEHID, LARGE_NUMBER, false);
	}
	

	/**
	 * Tests that the toString method in car returns the expected 
	 * result after a new car is created.
	 */
	@Test
	public void testToString() {
		assertEquals(car.toString(), "Vehicle vehID: C1"+
									"\nArrival Time: 1" +
									"\nVehicle was not queued" + 
									"\nVehicle was not parked" + 
									"\nCustomer was not satisfied" +
									"\nCar cannot use small parking space");
	}
	
	/**
	 * Tests the toString method in smallCar returns the expected 
	 * result after a new small car is created.
	 */
	@Test
	public void testToStringSmall() {
		assertEquals(smallCar.toString(), "Vehicle vehID: C2"+
											"\nArrival Time: 1" +
											"\nVehicle was not queued" + 
											"\nVehicle was not parked" + 
											"\nCustomer was not satisfied" +
											"\nCar can use small car parking space");
	}


	/**
	 * Tests that the smallCar isSmall() method returns true.
	 * @throws VehicleException 
	 */
	@Test
	public void testIsSmallTrue() throws VehicleException {
		assertTrue(smallCar.isSmall());
	}
	
	
	/**
	 * Tests that the car isSmall() method returns false.
	 * @throws VehicleException 
	 */
	@Test
	public void testIsSmallFalse() throws VehicleException {
		assertFalse(car.isSmall());
	}
	
	/**
	 * Tests that the VechicleID used to create the car is returned from the car getVehID() method.
	 * @throws VehicleException
	 */
	@Test
	public void testgetVehId() throws VehicleException {
		assertEquals(car.getVehID(), VEHID);
	}
	
	/**
	 * Tests the ArrivalTime used to create the car is returned from the car getArrivalTime() method.
	 * @throws VehicleException
	 */
	@Test
	public void testgetArrivalTime() throws VehicleException {
		assertEquals(car.getArrivalTime(), ARRIVALTIME);
	}
	
	/*
	 * Confirm that the API spec has not been violated through the
	 * addition of public fields, constructors or methods that were
	 * not requested
	 */
	@Test
	public void NoExtraPublicMethods() {
		//Car Class implements Vehicle, adds isSmall() 
		final int NumVehicleClassMethods = Array.getLength(Vehicle.class.getMethods());
		final int NumCarClassMethods = Array.getLength(Car.class.getMethods());
		assertTrue("veh:"+NumVehicleClassMethods+":car:"+NumCarClassMethods,(NumVehicleClassMethods+1)==NumCarClassMethods);
	}
	
	@Test 
	public void NoExtraPublicFields() {
		//Same as Vehicle 
		final int NumVehicleClassFields = Array.getLength(Vehicle.class.getFields());
		final int NumCarClassFields = Array.getLength(Car.class.getFields());
		assertTrue("veh:"+NumVehicleClassFields+":car:"+NumCarClassFields,(NumVehicleClassFields)==NumCarClassFields);
	}
	
	@Test 
	public void NoExtraPublicConstructors() {
		//Same as Vehicle
		final int NumVehicleClassConstructors = Array.getLength(Vehicle.class.getConstructors());
		final int NumCarClassConstructors = Array.getLength(Car.class.getConstructors());
		assertTrue(":veh:"+NumVehicleClassConstructors+":car:"+NumCarClassConstructors,(NumVehicleClassConstructors)==NumCarClassConstructors);
	}
	


}
