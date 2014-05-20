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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.VehicleException;
import asgn2Vehicles.Car;

/**
 * @author hogan
 *
 */
public class CarTests {
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Car car = new Car("iamcar", 10, true);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
	}

	/**
	 * Test method for {@link asgn2Vehicles.Car#toString()}.
	 */
	@Test
	public void testToString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Car#Car(java.lang.String, int, boolean)}.
	 * @throws VehicleException 
	 */
	@Test
	public void testCar() throws VehicleException{
		Car car = new Car("ircar", 10, true);
		assertEquals(car.getVehID(), "ircar");
	}

	/**
	 * Test method for {@link asgn2Vehicles.Car#isSmall()}.
	 * @throws VehicleException 
	 */
	@Test
	public void testIsSmall() throws VehicleException {
		Car car = new Car("IisCar",10, true);
		assertEquals(car.isSmall(), true);
	}

}
