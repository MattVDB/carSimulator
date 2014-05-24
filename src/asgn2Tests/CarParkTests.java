/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2Tests 
 * 29/04/2014
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import java.lang.reflect.Array;

import org.junit.Before;
import org.junit.Test;

import asgn2CarParks.CarPark;
import asgn2Exceptions.SimulationException;
import asgn2Exceptions.VehicleException;
import asgn2Simulators.Constants;
import asgn2Vehicles.Car;
import asgn2Vehicles.MotorCycle;
import asgn2Vehicles.Vehicle;

/**
 * @author hogan
 *
 */
public class CarParkTests {
	
	private CarPark cp;
	private Vehicle c;
	private Vehicle sc;
	private Vehicle m;
	private final String VEHID = "C1";
	private final int ARRIVALTIME = 1;
	private final int START = 0;
	private final int NEGETIVE = -1;
	private final int LARGE_NUMBER = 100000;
	private final int INTENDEDSTAY = Constants.MINIMUM_STAY;
	private final int MAX_STAY = Constants.MAXIMUM_QUEUE_TIME;
	private final int CLOSING = Constants.CLOSING_TIME;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cp = new CarPark();
		c = new Car(VEHID, ARRIVALTIME, false);
		sc = new Car(VEHID, ARRIVALTIME, true);
		m = new MotorCycle(VEHID, ARRIVALTIME);
	}


	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testCarArchiveDepartingVehiclesTime() throws VehicleException, SimulationException {
		cp.parkVehicle(c, ARRIVALTIME, INTENDEDSTAY);
		cp.archiveDepartingVehicles(ARRIVALTIME + INTENDEDSTAY, false);
		assertFalse(c.isParked());
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testCarArchiveDepartingVehiclesForced() throws VehicleException, SimulationException {
		cp.parkVehicle(c, ARRIVALTIME, INTENDEDSTAY);
		cp.archiveDepartingVehicles(ARRIVALTIME, true);
		assertFalse(c.isParked());
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testCarArchiveDepartingVehiclesNotParkedTime() throws VehicleException, SimulationException {
		cp.parkVehicle(c, ARRIVALTIME, INTENDEDSTAY);
		c.exitParkedState(ARRIVALTIME);
		cp.archiveDepartingVehicles(ARRIVALTIME, false);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testCarArchiveDepartingVehiclesNotParkedForced() throws VehicleException, SimulationException {
		cp.parkVehicle(c, ARRIVALTIME, INTENDEDSTAY);
		c.exitParkedState(ARRIVALTIME);
		cp.archiveDepartingVehicles(INTENDEDSTAY, true);
	}
	
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testSmallCarArchiveDepartingVehiclesTime() throws VehicleException, SimulationException {
		cp.parkVehicle(sc, ARRIVALTIME, INTENDEDSTAY);
		cp.archiveDepartingVehicles(ARRIVALTIME + INTENDEDSTAY, false);
		assertFalse(sc.isParked());
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testSmallCarArchiveDepartingVehiclesForced() throws VehicleException, SimulationException {
		cp.parkVehicle(sc, ARRIVALTIME, INTENDEDSTAY);
		cp.archiveDepartingVehicles(ARRIVALTIME, true);
		assertFalse(sc.isParked());
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testSmallCarArchiveDepartingVehiclesNotParkedTime() throws VehicleException, SimulationException {
		cp.parkVehicle(sc, ARRIVALTIME, INTENDEDSTAY);
		sc.exitParkedState(ARRIVALTIME);
		cp.archiveDepartingVehicles(ARRIVALTIME, false);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testSmallCarArchiveDepartingVehiclesNotParkedForced() throws VehicleException, SimulationException {
		cp.parkVehicle(sc, ARRIVALTIME, INTENDEDSTAY);
		sc.exitParkedState(ARRIVALTIME);
		cp.archiveDepartingVehicles(INTENDEDSTAY, true);
	}
	

	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testMotorCycleArchiveDepartingVehiclesTime() throws VehicleException, SimulationException {
		cp.parkVehicle(m, ARRIVALTIME, INTENDEDSTAY);
		cp.archiveDepartingVehicles(ARRIVALTIME + INTENDEDSTAY, false);
		assertFalse(m.isParked());
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testMotorCycleArchiveDepartingVehiclesForced() throws VehicleException, SimulationException {
		cp.parkVehicle(m, ARRIVALTIME, INTENDEDSTAY);
		cp.archiveDepartingVehicles(ARRIVALTIME, true);
		assertFalse(m.isParked());
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testMotorCycleArchiveDepartingVehiclesNotParkedTime() throws VehicleException, SimulationException {
		cp.parkVehicle(m, ARRIVALTIME, INTENDEDSTAY);
		m.exitParkedState(ARRIVALTIME);
		cp.archiveDepartingVehicles(ARRIVALTIME, false);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void tesMotorCycletArchiveDepartingVehiclesNotParkedForced() throws VehicleException, SimulationException {
		cp.parkVehicle(m, ARRIVALTIME, INTENDEDSTAY);
		m.exitParkedState(ARRIVALTIME);
		cp.archiveDepartingVehicles(INTENDEDSTAY, true);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testAllArchiveDepartingVehiclesTime() throws VehicleException, SimulationException {
		cp.parkVehicle(c, ARRIVALTIME, INTENDEDSTAY);
		cp.parkVehicle(sc, ARRIVALTIME, INTENDEDSTAY);
		cp.parkVehicle(m, ARRIVALTIME, INTENDEDSTAY);
		cp.archiveDepartingVehicles(ARRIVALTIME + INTENDEDSTAY, false);
		assertFalse(c.isParked());
		assertFalse(sc.isParked());
		assertFalse(m.isParked());
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testAllArchiveDepartingVehiclesForced() throws VehicleException, SimulationException {
		cp.parkVehicle(c, ARRIVALTIME, INTENDEDSTAY);
		cp.parkVehicle(sc, ARRIVALTIME, INTENDEDSTAY);
		cp.parkVehicle(m, ARRIVALTIME, INTENDEDSTAY);
		cp.archiveDepartingVehicles(ARRIVALTIME, true);
		assertFalse(c.isParked());
		assertFalse(sc.isParked());
		assertFalse(m.isParked());
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testAllArchiveDepartingVehiclesNotParkedTime() throws VehicleException, SimulationException {
		cp.parkVehicle(c, ARRIVALTIME, INTENDEDSTAY);
		c.exitParkedState(ARRIVALTIME);
		cp.parkVehicle(sc, ARRIVALTIME, INTENDEDSTAY);
		sc.exitParkedState(ARRIVALTIME);
		cp.parkVehicle(m, ARRIVALTIME, INTENDEDSTAY);
		m.exitParkedState(ARRIVALTIME);
		cp.archiveDepartingVehicles(ARRIVALTIME, false);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void tesAlltArchiveDepartingVehiclesNotParkedForced() throws VehicleException, SimulationException {
		cp.parkVehicle(c, ARRIVALTIME, INTENDEDSTAY);
		c.exitParkedState(ARRIVALTIME);
		cp.parkVehicle(sc, ARRIVALTIME, INTENDEDSTAY);
		sc.exitParkedState(ARRIVALTIME);
		cp.parkVehicle(m, ARRIVALTIME, INTENDEDSTAY);
		m.exitParkedState(ARRIVALTIME);
		cp.archiveDepartingVehicles(INTENDEDSTAY, true);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testOneArchiveDepartingVehiclesForced() throws VehicleException, SimulationException {
		cp.parkVehicle(c, ARRIVALTIME, INTENDEDSTAY);
		cp.parkVehicle(sc, ARRIVALTIME+1, INTENDEDSTAY);
		cp.parkVehicle(m, ARRIVALTIME+2, INTENDEDSTAY);
		cp.archiveDepartingVehicles(ARRIVALTIME + INTENDEDSTAY, false);
		assertFalse(c.isParked());
		assertTrue(sc.isParked());
		assertTrue(m.isParked());
	}

	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveNewVehicle(asgn2Vehicles.Vehicle)}.
	 * @throws SimulationException 
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testCarArchiveNewVehicleIsQueued() throws SimulationException, VehicleException {
		c.enterQueuedState();
		cp.archiveNewVehicle(c);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveNewVehicle(asgn2Vehicles.Vehicle)}.
	 * @throws SimulationException 
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testSmallCarArchiveNewVehicleIsQueued() throws SimulationException, VehicleException {
		sc.enterQueuedState();
		cp.archiveNewVehicle(sc);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveNewVehicle(asgn2Vehicles.Vehicle)}.
	 * @throws SimulationException 
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testMotorCycleArchiveNewVehicleIsQueued() throws SimulationException, VehicleException {
		m.enterQueuedState();
		cp.archiveNewVehicle(m);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveNewVehicle(asgn2Vehicles.Vehicle)}.
	 * @throws SimulationException 
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testCarArchiveNewVehicleIsParked() throws SimulationException, VehicleException {
		c.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		cp.archiveNewVehicle(c);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveNewVehicle(asgn2Vehicles.Vehicle)}.
	 * @throws SimulationException 
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testSmallCarArchiveNewVehicleIsParked() throws SimulationException, VehicleException {
		sc.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		cp.archiveNewVehicle(sc);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveNewVehicle(asgn2Vehicles.Vehicle)}.
	 * @throws SimulationException 
	 * @throws VehicleException 
	 */
	@Test(expected=Exception.class)
	public void testMotorCycleArchiveNewVehicleIsParked() throws SimulationException, VehicleException {
		m.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		cp.archiveNewVehicle(m);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveNewVehicle(asgn2Vehicles.Vehicle)}.
	 * @throws SimulationException 
	 */
	@Test
	public void testCarArchiveNewVehicle() throws SimulationException {
		cp.archiveNewVehicle(c);
		assertFalse(c.isParked());
		assertFalse(c.isQueued());
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveNewVehicle(asgn2Vehicles.Vehicle)}.
	 * @throws SimulationException 
	 */
	@Test
	public void testSmallCarArchiveNewVehicle() throws SimulationException {
		cp.archiveNewVehicle(sc);
		assertFalse(sc.isParked());
		assertFalse(sc.isQueued());
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveNewVehicle(asgn2Vehicles.Vehicle)}.
	 * @throws SimulationException 
	 */
	@Test
	public void testMotorCycleArchiveNewVehicle() throws SimulationException {
		cp.archiveNewVehicle(m);
		assertFalse(m.isParked());
		assertFalse(m.isQueued());
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testCarArchiveQueueFailuresPastClosing() throws SimulationException, VehicleException {
		cp.enterQueue(c);
		cp.archiveQueueFailures(LARGE_NUMBER);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testSmallCarArchiveQueueFailuresPastClosing() throws SimulationException, VehicleException {
		cp.enterQueue(sc);
		cp.archiveQueueFailures(LARGE_NUMBER);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testMotorCycleArchiveQueueFailuresPastClosing() throws SimulationException, VehicleException {
		cp.enterQueue(m);
		cp.archiveQueueFailures(LARGE_NUMBER);
	}
	
	
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testCarArchiveQueueFailuresClosing() throws SimulationException, VehicleException {
		cp.enterQueue(c);
		cp.archiveQueueFailures(CLOSING);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testSmallCarArchiveQueueFailuresClosing() throws SimulationException, VehicleException {
		cp.enterQueue(sc);
		cp.archiveQueueFailures(CLOSING);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testMotorCycleArchiveQueueFailuresClosing() throws SimulationException, VehicleException {
		cp.enterQueue(m);
		cp.archiveQueueFailures(CLOSING);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testCarArchiveQueueFailuresBeforeStart() throws SimulationException, VehicleException {
		cp.enterQueue(c);
		cp.archiveQueueFailures(NEGETIVE);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testSmallCarArchiveQueueFailuresBeforeStart() throws SimulationException, VehicleException {
		cp.enterQueue(sc);
		cp.archiveQueueFailures(NEGETIVE);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testMotorCycleArchiveQueueFailuresBeforeStart() throws SimulationException, VehicleException {
		cp.enterQueue(m);
		cp.archiveQueueFailures(NEGETIVE);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testCarArchiveQueueFailuresStart() throws SimulationException, VehicleException {
		cp.enterQueue(c);
		cp.archiveQueueFailures(START);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testSmallCarArchiveQueueFailuresStart() throws SimulationException, VehicleException {
		cp.enterQueue(sc);
		cp.archiveQueueFailures(START);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testMotorCycleArchiveQueueFailuresStart() throws SimulationException, VehicleException {
		cp.enterQueue(m);
		cp.archiveQueueFailures(START);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testCarArchiveQueueFailuresNotQueued() throws SimulationException, VehicleException {
		cp.enterQueue(c);
		c.exitQueuedState(ARRIVALTIME+1);
		cp.archiveQueueFailures(ARRIVALTIME + MAX_STAY + 1);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testSmallCarArchiveQueueFailuresNotQueued() throws SimulationException, VehicleException {
		cp.enterQueue(sc);
		sc.exitQueuedState(ARRIVALTIME+1);
		cp.archiveQueueFailures(ARRIVALTIME + MAX_STAY + 1);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testMotorCycleArchiveQueueFailuresNotQueued() throws SimulationException, VehicleException {
		cp.enterQueue(m);
		m.exitQueuedState(ARRIVALTIME+1);
		cp.archiveQueueFailures(ARRIVALTIME + MAX_STAY + 1);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testCarArchiveQueueFailures() throws SimulationException, VehicleException {
		cp.enterQueue(c);
		cp.archiveQueueFailures(ARRIVALTIME + MAX_STAY + 1);
		assertFalse(c.isQueued());
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testSmallCarArchiveQueueFailures() throws SimulationException, VehicleException {
		cp.enterQueue(sc);
		cp.archiveQueueFailures(ARRIVALTIME + MAX_STAY + 1);
		assertFalse(sc.isQueued());
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testMotorCycleArchiveQueueFailures() throws SimulationException, VehicleException {
		cp.enterQueue(m);
		cp.archiveQueueFailures(ARRIVALTIME + MAX_STAY + 1);
		assertFalse(m.isQueued());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#carParkEmpty()}.
	 */
	@Test
	public void testCarParkEmptyTrue() {
		assertTrue(cp.carParkEmpty());
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#carParkEmpty()}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testCarParkEmptyFalseWithCars() throws SimulationException, VehicleException {
		cp.parkVehicle(c, ARRIVALTIME, INTENDEDSTAY);
		assertFalse(cp.carParkEmpty());
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#carParkEmpty()}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testCarParkEmptyFalseWithSmallCars() throws SimulationException, VehicleException {
		cp.parkVehicle(sc, ARRIVALTIME, INTENDEDSTAY);
		assertFalse(cp.carParkEmpty());
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#carParkEmpty()}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testCarParkEmptyFalseWithMotorCycles() throws SimulationException, VehicleException {
		cp.parkVehicle(m, ARRIVALTIME, INTENDEDSTAY);
		assertFalse(cp.carParkEmpty());
	}
	
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#carParkFull()}.
	 */
	@Test
	public void testCarParkFullFalse() {
		assertFalse(cp.carParkFull());
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#carParkFull()}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testCarParkFullTrue() throws SimulationException, VehicleException {
		int maxCars = Constants.DEFAULT_MAX_CAR_SPACES - Constants.DEFAULT_MAX_SMALL_CAR_SPACES;
		int maxSmallCars = Constants.DEFAULT_MAX_SMALL_CAR_SPACES;
		int maxMotorCycle = Constants.DEFAULT_MAX_MOTORCYCLE_SPACES;
		
		// Fill car park
		for(int i =0; i < maxCars; i++){
			Car j = new Car("c1", ARRIVALTIME, false);
			cp.parkVehicle(j, ARRIVALTIME, INTENDEDSTAY);
		}
		for(int i =0; i < maxSmallCars; i++){
			Car j = new Car("c1", ARRIVALTIME, true);
			cp.parkVehicle(j, ARRIVALTIME, INTENDEDSTAY);
		}
		for(int i =0; i < maxMotorCycle; i++){
			MotorCycle l = new MotorCycle("mc1", ARRIVALTIME);
			cp.parkVehicle(l, ARRIVALTIME, INTENDEDSTAY);
		}
			
		assertTrue(cp.carParkFull());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#enterQueue(asgn2Vehicles.Vehicle)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testEnterQueueIsParked() throws VehicleException, SimulationException {
		c.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		cp.enterQueue(c);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#enterQueue(asgn2Vehicles.Vehicle)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testEnterQueueIsQueued() throws VehicleException, SimulationException {
		c.enterQueuedState();
		cp.enterQueue(c);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#enterQueue(asgn2Vehicles.Vehicle)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testEnterQueueWasParked() throws VehicleException, SimulationException {
		c.enterParkedState(ARRIVALTIME, INTENDEDSTAY);
		c.exitParkedState(INTENDEDSTAY);
		cp.enterQueue(c);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#enterQueue(asgn2Vehicles.Vehicle)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testEnterQueueWasQueued() throws VehicleException, SimulationException {
		c.enterQueuedState();
		c.exitQueuedState(INTENDEDSTAY);
		cp.enterQueue(c);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#enterQueue(asgn2Vehicles.Vehicle)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testEnterQueueFullQueue() throws VehicleException, SimulationException {
		// Fill queue
		for (int i =0 ; i < Constants.DEFAULT_MAX_QUEUE_SIZE; i++){
			Car j = new Car("C1", ARRIVALTIME, false);
			cp.enterQueue(j);
		}
		cp.enterQueue(c);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#exitQueue(asgn2Vehicles.Vehicle, int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testExitQueue() throws SimulationException, VehicleException {
		cp.enterQueue(c);
		cp.exitQueue(c, INTENDEDSTAY);
		assertFalse(c.isQueued());
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#exitQueue(asgn2Vehicles.Vehicle, int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testExitQueueNotQueued() throws SimulationException, VehicleException {
		cp.exitQueue(c, INTENDEDSTAY);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#exitQueue(asgn2Vehicles.Vehicle, int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testExitQueuePastClosing() throws SimulationException, VehicleException {
		cp.enterQueue(c);
		cp.exitQueue(c, CLOSING+1);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#exitQueue(asgn2Vehicles.Vehicle, int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testExitQueueAtClosing() throws SimulationException, VehicleException {
		cp.enterQueue(c);
		cp.exitQueue(c, CLOSING);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#exitQueue(asgn2Vehicles.Vehicle, int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testExitQueueBeforeStart() throws SimulationException, VehicleException {
		cp.enterQueue(c);
		cp.exitQueue(c, NEGETIVE);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#exitQueue(asgn2Vehicles.Vehicle, int)}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test(expected=Exception.class)
	public void testExitQueueAtStart() throws SimulationException, VehicleException {
		cp.enterQueue(c);
		cp.exitQueue(c, START);
	}


	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumCars()}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testGetNumCars() throws SimulationException, VehicleException {
		cp.parkVehicle(c, ARRIVALTIME, INTENDEDSTAY);
		assertEquals(cp.getNumCars(), 1);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumCars()}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testGetNumCarsBoth() throws SimulationException, VehicleException {
		cp.parkVehicle(c, ARRIVALTIME, INTENDEDSTAY);
		cp.parkVehicle(sc, ARRIVALTIME, INTENDEDSTAY);
		assertEquals(cp.getNumCars(), 2);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumCars()}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testGetNumCarsRemove() throws SimulationException, VehicleException {
		cp.parkVehicle(c, ARRIVALTIME, INTENDEDSTAY);
		cp.parkVehicle(sc, ARRIVALTIME, INTENDEDSTAY);
		cp.unparkVehicle(c, INTENDEDSTAY);
		assertEquals(cp.getNumCars(), 1);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumMotorCycles()}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testGetNumMotorCycles() throws SimulationException, VehicleException {
		cp.parkVehicle(m, ARRIVALTIME, INTENDEDSTAY);
		assertEquals(cp.getNumMotorCycles(), 1);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumMotorCycles()}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testGetNumMotorCyclesRemove() throws SimulationException, VehicleException {
		cp.parkVehicle(m, ARRIVALTIME, INTENDEDSTAY);
		cp.unparkVehicle(m, INTENDEDSTAY);
		assertEquals(cp.getNumMotorCycles(), 0);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumSmallCars()}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testGetNumSmallCars() throws SimulationException, VehicleException {
		cp.parkVehicle(sc, ARRIVALTIME, INTENDEDSTAY);
		assertEquals(cp.getNumSmallCars(), 1);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumSmallCars()}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testGetNumSmallCarsExtraCar() throws SimulationException, VehicleException {
		cp.parkVehicle(sc, ARRIVALTIME, INTENDEDSTAY);
		cp.parkVehicle(c, ARRIVALTIME, INTENDEDSTAY);
		assertEquals(cp.getNumSmallCars(), 1);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumSmallCars()}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testGetNumSmallCarsRemove() throws SimulationException, VehicleException {
		cp.parkVehicle(sc, ARRIVALTIME, INTENDEDSTAY);
		cp.unparkVehicle(sc, ARRIVALTIME);
		assertEquals(cp.getNumSmallCars(), 0);
	}


	/**
	 * Test method for {@link asgn2CarParks.CarPark#numVehiclesInQueue()}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testNumVehiclesInQueue() throws SimulationException, VehicleException {
		cp.enterQueue(c);
		assertEquals(cp.numVehiclesInQueue(), 1);
	}
	
	/**
	 * Test method for {@link asgn2CarParks.CarPark#numVehiclesInQueue()}.
	 * @throws VehicleException 
	 * @throws SimulationException 
	 */
	@Test
	public void testNumVehiclesInQueueZero() throws SimulationException, VehicleException {
		assertEquals(cp.numVehiclesInQueue(), 0);
	}

//	/**
//	 * Test method for {@link asgn2CarParks.CarPark#parkVehicle(asgn2Vehicles.Vehicle, int, int)}.
//	 */
//	@Test
//	public void testParkVehicle() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link asgn2CarParks.CarPark#processQueue(int, asgn2Simulators.Simulator)}.
//	 */
//	@Test
//	public void testProcessQueue() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link asgn2CarParks.CarPark#queueEmpty()}.
//	 */
//	@Test
//	public void testQueueEmpty() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link asgn2CarParks.CarPark#queueFull()}.
//	 */
//	@Test
//	public void testQueueFull() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}.
//	 */
//	@Test
//	public void testSpacesAvailable() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link asgn2CarParks.CarPark#toString()}.
//	 */
//	@Test
//	public void testToString() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link asgn2CarParks.CarPark#tryProcessNewVehicles(int, asgn2Simulators.Simulator)}.
//	 */
//	@Test
//	public void testTryProcessNewVehicles() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link asgn2CarParks.CarPark#unparkVehicle(asgn2Vehicles.Vehicle, int)}.
//	 */
//	@Test
//	public void testUnparkVehicle() {
//		fail("Not yet implemented"); // TODO
//	}

	
	/*
	 * Confirm that the API spec has not been violated through the
	 * addition of public fields, constructors or methods that were
	 * not requested
	 */
	@Test
	public void NoExtraPublicMethods() {
		//Extends Object, extras less toString() 
		final int ExtraMethods = 21; 
		final int NumObjectClassMethods = Array.getLength(Object.class.getMethods());
		final int NumCarParkClassMethods = Array.getLength(CarPark.class.getMethods());
		assertTrue("obj:"+NumObjectClassMethods+":cp:"+NumCarParkClassMethods,(NumObjectClassMethods+ExtraMethods)==NumCarParkClassMethods);
	}
	
	@Test 
	public void NoExtraPublicFields() {
		//Same as Vehicle 
		final int NumObjectClassFields = Array.getLength(Object.class.getFields());
		final int NumCarParkClassFields = Array.getLength(CarPark.class.getFields());
		assertTrue("obj:"+NumObjectClassFields+":cp:"+NumCarParkClassFields,(NumObjectClassFields)==NumCarParkClassFields);
	}
	
	@Test 
	public void NoExtraPublicConstructors() {
		//One extra cons used. 
		final int NumObjectClassConstructors = Array.getLength(Object.class.getConstructors());
		final int NumCarParkClassConstructors = Array.getLength(CarPark.class.getConstructors());
		assertTrue(":obj:"+NumObjectClassConstructors+":cp:"+NumCarParkClassConstructors,(NumObjectClassConstructors+1)==NumCarParkClassConstructors);
	}
}
