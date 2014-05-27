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
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mc = new MotorCycle(VEHID, ARRIVALTIME);
	}

	
	@Test(expected=Exception.class)
	public void ConstructorNegetiveArrivalTime() throws VehicleException {
		new MotorCycle(VEHID, NEGETIVE);
	}
	
	@Test(expected=Exception.class)
	public void ConstructorZeroArrivalTime() throws VehicleException {
		new MotorCycle(VEHID, ZERO);
	}
	
	@Test
	public void ConstructorOneArrivalTime() throws VehicleException {
		new MotorCycle(VEHID, ARRIVALTIME);
	}
	
	@Test
	public void ConstructorLargeArrivalTime() throws VehicleException {
		new MotorCycle(VEHID, LARGE_NUMBER);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testEnterParkedStateParked() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testEnterParkedStateQueued() throws VehicleException {
		mc.enterQueuedState();
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testEnterParkedStateParkingTimeNegetive() throws VehicleException {
		mc.enterParkedState(NEGETIVE, INTENDEDSTAY);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testEnterParkedStateIntendedStayLow() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, LOW_INTENDEDSTAY);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * @throws VehicleException 
	 */
	@Test
	public void testEnterParkedStateLargeParkingTime() throws VehicleException {
		mc.enterParkedState(LARGE_NUMBER, INTENDEDSTAY);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testEnterParkedStateZeroParkingTime() throws VehicleException {
		mc.enterParkedState(ZERO, INTENDEDSTAY);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * @throws VehicleException 
	 */
	@Test
	public void testEnterParkedStateIntendedStayHigh() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, HIGH_INTENDEDSTAY);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * @throws VehicleException 
	 */
	@Test
	public void testEnterParkedStateParkingTime() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		assertEquals(mc.getParkingTime(), ARRIVALTIME);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * @throws VehicleException 
	 */
	@Test
	public void testEnterParkedStateDepartureTime() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		assertEquals(mc.getDepartureTime(), ARRIVALTIME+INTENDEDSTAY);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * @throws VehicleException 
	 */
	@Test
	public void testEnterParkedState() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		assertTrue(mc.isParked());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterQueuedState()}.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testEnterQueuedStateParked() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		mc.enterQueuedState();
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterQueuedState()}.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testEnterQueuedStateQueued() throws VehicleException {
		mc.enterQueuedState();
		mc.enterQueuedState();
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterQueuedState()}.
	 * @throws VehicleException 
	 */
	@Test
	public void testEnterQueuedState() throws VehicleException {
		mc.enterQueuedState();
		assertTrue(mc.isQueued());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState(int)}.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testExitParkedStateNotParked() throws VehicleException {
		mc.exitParkedState(ARRIVALTIME);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState(int)}.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testExitParkedStateIsQueued() throws VehicleException {
		mc.enterQueuedState();
		mc.exitParkedState(ARRIVALTIME);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState(int)}.
	 * @throws VehicleException 
	 */
	@Test
	public void testExitParkedStateSameExitTime() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		mc.exitParkedState(ARRIVALTIME);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState(int)}.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testExitParkedStateEarlierExitTime() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		mc.exitParkedState(ZERO);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState(int)}.
	 * @throws VehicleException 
	 */
	@Test
	public void testExitParkedStateInt() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		mc.exitParkedState(DEPARTURETIME);
		assertEquals(mc.getDepartureTime(), DEPARTURETIME);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState()}.
	 * @throws VehicleException 
	 */
	@Test
	public void testExitParkedStateWasParked() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		mc.exitParkedState(DEPARTURETIME);
		assertTrue(mc.wasParked());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState()}.
	 * @throws VehicleException 
	 */
	@Test
	public void testExitParkedStateIsParked() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		mc.exitParkedState(DEPARTURETIME);
		assertFalse(mc.isParked());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState(int)}.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testExitQueuedStateIsParked() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		mc.exitQueuedState(ARRIVALTIME);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState(int)}.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testExitQueuedStateNotQueued() throws VehicleException {
		mc.exitQueuedState(ARRIVALTIME);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState(int)}.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testExitQueuedStateSameExitTime() throws VehicleException {
		mc.enterQueuedState();
		mc.exitQueuedState(ARRIVALTIME);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState(int)}.
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testExitQueuedStateEarlierExitTime() throws VehicleException {
		mc.enterQueuedState();
		mc.exitQueuedState(ZERO);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState(int)}.
	 * @throws VehicleException 
	 */
	@Test
	public void testExitQueuedStateIsQueued() throws VehicleException {
		mc.enterQueuedState();
		mc.exitQueuedState(DEPARTURETIME);
		assertFalse(mc.isQueued());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState(int)}.
	 * @throws VehicleException 
	 */
	@Test
	public void testExitQueuedStateWasQueued() throws VehicleException {
		mc.enterQueuedState();
		mc.exitQueuedState(DEPARTURETIME);
		assertTrue(mc.wasQueued());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getArrivalTime()}.
	 */
	@Test
	public void testGetArrivalTime() {
		assertEquals(mc.getArrivalTime(), ARRIVALTIME);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getVehID()}.
	 */
	@Test
	public void testGetVehID() {
		assertEquals(mc.getVehID(), VEHID);
	}


	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isSatisfied()}.
	 * @throws VehicleException 
	 */
	@Test
	public void testIsSatisfiedIsParked() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		assertTrue(mc.isSatisfied());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isSatisfied()}.
	 * @throws VehicleException 
	 */
	@Test
	public void testIsSatisfiedWasParked() throws VehicleException {
		mc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		mc.exitParkedState(DEPARTURETIME);
		assertTrue(mc.isSatisfied());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isSatisfied()}.
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
	 * Test method for {@link asgn2Vehicles.Vehicle#isSatisfied()}.
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
	 * Test method for {@link asgn2Vehicles.Vehicle#isSatisfied()}.
	 * @throws VehicleException 
	 */
	@Test
	public void testIsSatisfiedFalse() throws VehicleException {
		assertFalse(mc.isSatisfied());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isSatisfied()}.
	 * @throws VehicleException 
	 */
	@Test
	public void testIsSatisfiedFalseIsQueued() throws VehicleException {
		mc.enterQueuedState();
		assertFalse(mc.isSatisfied());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isSatisfied()}.
	 * @throws VehicleException 
	 */
	@Test
	public void testIsSatisfiedFalseWasQueued() throws VehicleException {
		mc.enterQueuedState();
		mc.exitQueuedState(DEPARTURETIME);
		assertFalse(mc.isSatisfied());
	}


	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#toString()}.
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
