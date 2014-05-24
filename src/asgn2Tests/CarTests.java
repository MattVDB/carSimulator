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
 * @author hogan
 *
 */
public class CarTests {
	
	private Car car;
	private Car smallCar;
	private final int NEGETIVE = -1;
	private final int ZERO = 0;
	private final int ONE = 1;
	private final int LARGE_NUMBER = 100000;
	private final String VEHID = "C1";
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		car = new Car("C1", ONE, false);
		smallCar = new Car("C2", ONE, true);
	}
	
	@Test(expected=Exception.class)
	public void ConstructorNegetiveArrivalTime() throws VehicleException {
		new Car(VEHID, NEGETIVE, false);
	}
	
	@Test(expected=Exception.class)
	public void ConstructorZeroArrivalTime() throws VehicleException {
		new Car(VEHID, ZERO, false);
	}
	
	@Test
	public void ConstructorOneArrivalTime() throws VehicleException {
		new Car(VEHID, ONE, false);
	}
	
	@Test
	public void ConstructorLargeArrivalTime() throws VehicleException {
		new Car(VEHID, LARGE_NUMBER, false);
	}
	

	/**
	 * Test method for {@link asgn2Vehicles.Car#toString()}.
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
	 * Test method for {@link asgn2Vehicles.Car#toString()}.
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
	 * Test method for {@link asgn2Vehicles.Car#isSmall()}.
	 * @throws VehicleException 
	 */
	@Test
	public void testIsSmallTrue() throws VehicleException {
		assertTrue(smallCar.isSmall());
	}
	
	
	/**
	 * Test method for {@link asgn2Vehicles.Car#isSmall()}.
	 * @throws VehicleException 
	 */
	@Test
	public void testIsSmallFalse() throws VehicleException {
		assertFalse(car.isSmall());
	}
	
	@Test
	public void testgetVehId() throws VehicleException {
		assertEquals(car.getVehID(), VEHID);
	}
	
	@Test
	public void testgetArrivalTime() throws VehicleException {
		assertEquals(car.getArrivalTime(), ONE);
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
