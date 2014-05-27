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
import asgn2Simulators.Constants;
import asgn2Vehicles.MotorCycle;
import asgn2Vehicles.Vehicle;

/**
 * @author Matthew Van Der Boor
 *
 */
public class MotorCycleTests {

	private MotorCycle mc;
	private final String VEHID = "MC1";
	private final int NEGETIVE = -1;
	private final int ZERO = 0;
	private final int ARRIVALTIME = 1;
	private final int DEPARTURETIME = 2;
	private final int LARGE_NUMBER = 100000;
	private final int INTENDEDSTAY = Constants.MINIMUM_STAY;
	private final int LOW_INTENDEDSTAY = Constants.MINIMUM_STAY - 1;
	private final int HIGH_INTENDEDSTAY = Constants.MINIMUM_STAY + 1;
	
	/**
	 * Sets up a new Motorcycle.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mc = new MotorCycle(VEHID, ARRIVALTIME);
	}

	/**
	 * Tests the constructor with a negative ArrivalTime.
	 * @throws VehicleException
	 */
	@Test(expected=Exception.class)
	public void ConstructorNegetiveArrivalTime() throws VehicleException {
		new MotorCycle(VEHID, NEGETIVE);
	}
	
	/**
	 * Tests the constructor with a zero ArrivalTime.
	 * @throws VehicleException
	 */
	@Test(expected=Exception.class)
	public void ConstructorZeroArrivalTime() throws VehicleException {
		new MotorCycle(VEHID, ZERO);
	}
	
	/**
	 * Tests the constructor with an ArrivalTime of one.
	 * @throws VehicleException
	 */
	@Test
	public void ConstructorOneArrivalTime() throws VehicleException {
		new MotorCycle(VEHID, ARRIVALTIME);
	}
	
	/**
	 * Tests the constructor with a large number greater than what it can accept.
	 * @throws VehicleException
	 */
	@Test
	public void ConstructorLargeArrivalTime() throws VehicleException {
		new MotorCycle(VEHID, LARGE_NUMBER);
	}
	
	/**
	 * Tests the enterParkedState() method with a Vehicle that has already been parked.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testEnterParkedStateParked() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
	}
	
	/**
	 * Tests the enterParkedState with a motorcycle that is in the queue.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testEnterParkedStateQueued() throws VehicleException {
		mc.enterQueuedState();
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
	}
	
	/**
	 * Tests the enteredParkState with a negative time.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testEnterParkedStateParkingTimeNegetive() throws VehicleException {
		mc.enterParkedState(NEGETIVE, INTENDEDSTAY);
	}
	
	/**
	 * Tests enteredParkState with a IntendedStay lower than acceptable.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testEnterParkedStateIntendedStayLow() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, LOW_INTENDEDSTAY);
	}
	
	/**
	 * Tests the enteredParkedState with a larger parkingTime than acceptable.
	 * @throws VehicleException 
	 */
	@Test
	public void testEnterParkedStateLargeParkingTime() throws VehicleException {
		mc.enterParkedState(LARGE_NUMBER, INTENDEDSTAY);
	}
	
	/**
	 * Tests enteredParkedState with a zero parking time.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testEnterParkedStateZeroParkingTime() throws VehicleException {
		mc.enterParkedState(ZERO, INTENDEDSTAY);
	}
	
	/**
	 * Tests the enteredParkedState method with a high intendedStay does not hit a size limit.
	 * @throws VehicleException 
	 */
	@Test
	public void testEnterParkedStateIntendedStayHigh() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, HIGH_INTENDEDSTAY);
	}
	
	/**
	 * Tests the enteredParkedState accepts appropriate args by calling the getParkingTime method
	 * and comapring that to to arrival time used.
	 * @throws VehicleException 
	 */
	@Test
	public void testEnterParkedStateParkingTime() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		assertEquals(mc.getParkingTime(), ARRIVALTIME);
	}
	
	/**
	 * Tests the enteredParkedState accepts appropriate args and that getDepaturetime() returns
	 * the ArrivalTime + IntendedStay.
	 * @throws VehicleException 
	 */
	@Test
	public void testEnterParkedStateDepartureTime() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		assertEquals(mc.getDepartureTime(), ARRIVALTIME+INTENDEDSTAY);
	}
	
	/**
	 * Tests the enteredParkedState accepts appropriate args, the motorcycle is parked and 
	 * the isParked method returns true.
	 * @throws VehicleException 
	 */
	@Test
	public void testEnterParkedState() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		assertTrue(mc.isParked());
	}
	
	/**
	 * Test the enteredQueuedState throws an exception when a vehicle is parkeded an
	 * no longer in the queue.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testEnterQueuedStateParked() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		mc.enterQueuedState();
	}
	
	/**
	 * Tests enteredQueuedState throws an exception when enteredQueuedState is called when 
	 * the vehicle is already queued.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testEnterQueuedStateQueued() throws VehicleException {
		mc.enterQueuedState();
		mc.enterQueuedState();
	}
	
	/**
	 * Tests EnteredQueuedState enters the motorcycle into the queue.
	 * @throws VehicleException 
	 */
	@Test
	public void testEnterQueuedState() throws VehicleException {
		mc.enterQueuedState();
		assertTrue(mc.isQueued());
	}
	
	/**
	 * Tests exitParkedState throws and exception when the vehicle is not in the correct
	 * state.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testExitParkedStateNotParked() throws VehicleException {
		mc.exitParkedState(ARRIVALTIME);
	}
	
	/**
	 * Tests exitParkedState throws an exception when the motorcycle is not in the correct state.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testExitParkedStateIsQueued() throws VehicleException {
		mc.enterQueuedState();
		mc.exitParkedState(ARRIVALTIME);
	}
	
	/**
	 * Tests exitParkedState can accept a motorcycle that has just been created.
	 * @throws VehicleException 
	 */
	@Test
	public void testExitParkedStateSameExitTime() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		mc.exitParkedState(ARRIVALTIME);
	}
	
	/**
	 * Tests exitParkedState throws an exception when an earlier exitTime is used.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testExitParkedStateEarlierExitTime() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		mc.exitParkedState(ZERO);
	}
	
	/**
	 * Tests exitParkedState accepts and uses the DepartureTime arg.
	 * @throws VehicleException 
	 */
	@Test
	public void testExitParkedStateInt() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		mc.exitParkedState(DEPARTURETIME);
		assertEquals(mc.getDepartureTime(), DEPARTURETIME);
	}

	/**
	 * Tests exitParkedState takes the parked motorcycle out of the carpark and archives it.
	 * @throws VehicleException 
	 */
	@Test
	public void testExitParkedStateWasParked() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		mc.exitParkedState(DEPARTURETIME);
		assertTrue(mc.wasParked());
	}
	
	/**
	 * Tests exitParkedState takes the parked motorcycle out of the carparck and archives it.
	 * @throws VehicleException 
	 */
	@Test
	public void testExitParkedStateIsParked() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		mc.exitParkedState(DEPARTURETIME);
		assertFalse(mc.isParked());
	}
	
	/**
	 * Tests exitQueuedSate throws and exception when it is used on a parked motorcycle.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testExitQueuedStateIsParked() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		mc.exitQueuedState(ARRIVALTIME);
	}
	
	/**
	 * Tests exitQueuedState throws an exception when the motorcycle is not in the queue.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testExitQueuedStateNotQueued() throws VehicleException {
		mc.exitQueuedState(ARRIVALTIME);
	}
	
	/**
	 * Tests exitQueuedSate throws an exception when the same ArrivalTime is used.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testExitQueuedStateSameExitTime() throws VehicleException {
		mc.enterQueuedState();
		mc.exitQueuedState(ARRIVALTIME);
	}
	
	/**
	 * Test exitQueuedState throws an exception when an earlier exit time than arrivalTime 
	 * is used.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testExitQueuedStateEarlierExitTime() throws VehicleException {
		mc.enterQueuedState();
		mc.exitQueuedState(ZERO);
	}
	
	/**
	 * Tests exitQueuedState takes the motorcycle from the queue.
	 * @throws VehicleException 
	 */
	@Test
	public void testExitQueuedStateIsQueued() throws VehicleException {
		mc.enterQueuedState();
		mc.exitQueuedState(DEPARTURETIME);
		assertFalse(mc.isQueued());
	}
	
	/**
	 * Tests exitQueuedState removes the motorcycle from the queue  
	 * @throws VehicleException 
	 */
	@Test
	public void testExitQueuedStateWasQueued() throws VehicleException {
		mc.enterQueuedState();
		mc.exitQueuedState(DEPARTURETIME);
		assertTrue(mc.wasQueued());
	}

	/**
	 * Tests the motorcycles ArrivalTime is equal to the arrival time 
	 * that was used to create it.
	 */
	@Test
	public void testGetArrivalTime() {
		assertEquals(mc.getArrivalTime(), ARRIVALTIME);
	}
	
	/**
	 * Tests the motorcycles VehID is the same to the one used
	 * to create it.
	 */
	@Test
	public void testGetVehID() {
		assertEquals(mc.getVehID(), VEHID);
	}


	/**
	 * Test that when a motorcycle enters the parked state, the customer
	 * is satisfied.
	 * @throws VehicleException 
	 */
	@Test
	public void testIsSatisfiedIsParked() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		assertTrue(mc.isSatisfied());
	}
	
	/**
	 * Tests that when a motorcycle is parked and then exits, the customer
	 * is satisfied.
	 * @throws VehicleException 
	 */
	@Test
	public void testIsSatisfiedWasParked() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		mc.exitParkedState(DEPARTURETIME);
		assertTrue(mc.isSatisfied());
	}

	/**
	 * Tests that when the motorcycle is in the queue, then leaves to be parked, 
	 * the customer is satisfied.
	 * @throws VehicleException 
	 */
	@Test
	public void testIsSatisfiedTrueWasQueued() throws VehicleException {
		mc.enterQueuedState();
		mc.exitQueuedState(DEPARTURETIME);
		mc.enterParkedState(LARGE_NUMBER, INTENDEDSTAY);
		assertTrue(mc.isSatisfied());
	}
		
	/**
	 * Tests that when the motorcycle is in the queue, gets parked, then leaves, the
	 * customer is satisfied. 
	 * @throws VehicleException 
	 */
	@Test
	public void testIsSatisfiedTrueExitQueuePark() throws VehicleException {
		mc.enterQueuedState();
		mc.exitQueuedState(DEPARTURETIME);
		mc.enterParkedState(LARGE_NUMBER, INTENDEDSTAY);
		mc.exitParkedState(LARGE_NUMBER + 1);
		assertTrue(mc.isSatisfied());
	}
	
	/**
	 * Tests the customer is not satisfied when the motorcycle has just been
	 * created. 
	 * @throws VehicleException 
	 */
	@Test
	public void testIsSatisfiedFalse() throws VehicleException {
		assertFalse(mc.isSatisfied());
	}
	
	/**
	 * Tests the customers is not satisfied when it is in the queue.
	 * @throws VehicleException 
	 */
	@Test
	public void testIsSatisfiedFalseIsQueued() throws VehicleException {
		mc.enterQueuedState();
		assertFalse(mc.isSatisfied());
	}
	
	/**
	 * Tests the customer is not satisfied when the motorcycle has been in the queue
	 * and exited the queue and never parked. 
	 * @throws VehicleException 
	 */
	@Test
	public void testIsSatisfiedFalseWasQueued() throws VehicleException {
		mc.enterQueuedState();
		mc.exitQueuedState(DEPARTURETIME);
		assertFalse(mc.isSatisfied());
	}


	/**
	 * Tests the motorcycle toString() method returns the expected
	 * output when a motorcycle is created.
	 */
	@Test
	public void testToString() {
		assertEquals(mc.toString(), "Vehicle vehID: MC1"+
									"\nArrival Time: 1" +
									"\nVehicle was not queued" + 
									"\nVehicle was not parked" + 
									"\nCustomer was not satisfied");
	}
	
	/* MotorCycleTEsts */
	/*
	 * Confirm that the API spec has not been violated through the
	 * addition of public fields, constructors or methods that were
	 * not requested
	 */
	@Test
	public void NoExtraPublicMethods() {
		//MotorCycle Class implements Vehicle
		final int NumVehicleClassMethods = Array.getLength(Vehicle.class.getMethods());
		final int NumMotorCycleClassMethods = Array.getLength(MotorCycle.class.getMethods());
		assertTrue("veh:"+NumVehicleClassMethods+":MotorCycle:"+NumMotorCycleClassMethods,(NumVehicleClassMethods)==NumMotorCycleClassMethods);
	}
	
	@Test 
	public void NoExtraPublicFields() {
		//Same as Vehicle 
		final int NumVehicleClassFields = Array.getLength(Vehicle.class.getFields());
		final int NumMotorCycleClassFields = Array.getLength(MotorCycle.class.getFields());
		assertTrue("veh:"+NumVehicleClassFields+":MotorCycle:"+NumMotorCycleClassFields,(NumVehicleClassFields)==NumMotorCycleClassFields);
	}
	
	@Test 
	public void NoExtraPublicConstructors() {
		//Same as Vehicle
		final int NumVehicleClassConstructors = Array.getLength(Vehicle.class.getConstructors());
		final int NumMotorCycleClassConstructors = Array.getLength(MotorCycle.class.getConstructors());
		assertTrue(":veh:"+NumVehicleClassConstructors+":mc:"+NumMotorCycleClassConstructors,(NumVehicleClassConstructors)==NumMotorCycleClassConstructors);
	}

}
