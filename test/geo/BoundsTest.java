/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package geo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.junit.Test;

public class BoundsTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * 
     * See the Testing reading for examples of what a testing strategy
     * comment looks like. Make sure you have partitions.
     */

	// Instructor created points
    private static final Angle LATITUDE_N = new Angle(42, 21, 35, CardinalDirection.NORTH);
    private static final Angle LONGITUDE_W = new Angle(71, 5, 31, CardinalDirection.WEST);
    
    // Student created lines
    private static final Angle LATITUDE_S = new Angle(30, 15, 25, CardinalDirection.SOUTH);
    private static final Angle LONGITUDE_E = new Angle(65, 6, 31, CardinalDirection.EAST);
    private static final Angle LONGITUDE_E2 = new Angle(120, 5, 31, CardinalDirection.EAST);
    private static final Angle LONGITUDE_E3 = new Angle(120, 5, 25, CardinalDirection.EAST);
    private static final Angle LATITUDE_N2 = new Angle(10, 21, 35, CardinalDirection.NORTH);
    private static final Angle LATITUDE_S2 = new Angle(90, 0, 0, CardinalDirection.SOUTH);
    private static final Angle LONGITUDE_W2 = new Angle(10, 5, 31, CardinalDirection.WEST);
    

    // Instructor created points
    private static final PointOfInterest GREAT_DOME = new PointOfInterest(LATITUDE_N, LONGITUDE_W, "Great Dome", "");

    // Student created points
    private static final PointOfInterest POINT1 = new PointOfInterest(LATITUDE_N, LONGITUDE_E, "Point1", "");
    private static final PointOfInterest POINT2 = new PointOfInterest(LATITUDE_S, LONGITUDE_E2, "Point2", "");
    private static final PointOfInterest POINT3 = new PointOfInterest(LATITUDE_S, LONGITUDE_W2, "Point3", "");
    private static final PointOfInterest POINT4 = new PointOfInterest(LATITUDE_N2, LONGITUDE_E, "Point4", "");
    private static final PointOfInterest POINT5 = new PointOfInterest(LATITUDE_S2, LONGITUDE_W2, "Point5", "");
    private static final PointOfInterest POINT6 = new PointOfInterest(LATITUDE_S2, LONGITUDE_W2, "Point6", "");
    
    

    
    // Student created PointOfInterest Lists
    private static final Set<PointOfInterest> EMPTY = Collections.emptySet();
    private static final Set<PointOfInterest> TWOSAME = createSet(POINT5, POINT6, null, null, null);
    private static final Set<PointOfInterest> x = createSet(POINT1, POINT2, null, null, null);
    private static final Set<PointOfInterest> y = createSet(POINT1, POINT2, null, null, null);
    private static final Set<PointOfInterest> z = createSet(POINT1, POINT2, null, null, null);
    

    
    
    private static Set<PointOfInterest> createSet(PointOfInterest a, PointOfInterest b, PointOfInterest c, PointOfInterest d, PointOfInterest e) {
    	Set<PointOfInterest> result = new HashSet<PointOfInterest>();
    	if (a != null) {result.add(a);}
    	if (a != null) {result.add(b);}
    	if (a != null) {result.add(c);}
    	if (a != null) {result.add(d);}
    	if (a != null) {result.add(e);}
    	return result;
    }
    
    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    // TODO: when you write your own test, state the partitions that the test covers in
    // a comment before the test, like this one
    @Test
    public void testLatitudeRangeSingleton() {
        List<Angle> expected = Arrays.asList(LATITUDE_N, LATITUDE_N);
        List<Angle> range = Bounds.latitudeRange(Collections.singleton(GREAT_DOME));
        assertEquals("expected correct length", expected.size(), range.size());
        assertEquals("expected correct southern bound", expected.get(0), range.get(0));
        assertEquals("expected correct northern bound", expected.get(1), range.get(1));
    }

    // TODO: when you write your own test, state the partitions that the test covers in
    // a comment before the test, like this one
    @Test
    public void testLongitudeRangeSingleton() {
        List<Angle> expected = Arrays.asList(LONGITUDE_W, LONGITUDE_W);
        List<Angle> range = Bounds.longitudeRange(Collections.singleton(GREAT_DOME));
        assertEquals("expected correct length", expected.size(), range.size());
        assertEquals("expected correct western bound", expected.get(0), range.get(0));
        assertEquals("expected correct eastern bound", expected.get(1), range.get(1));
    }
    
    // Test Empty List Latitude Range
    @Test
    public void testLatituteRangeEmpty() {
    	List<Angle> expected = Arrays.asList(LATITUDE_N, LONGITUDE_E);
    }
    // Test Empty List Longitude Range
    @Test
    public void testLongitudeRangeEmpty() {
    	
    }
    
    // Longitude test when range in seconds 
    @Test
    public void testLongitudeRangeSeconds() {
    	
    }
    
    // Latitude test when range in seconds 
    @Test
    public void testLatitudeRangeSeconds() {
    	
    }
    
    // Longitude test when range in minutes
    @Test
    public void testLongitudeRangeMinutes() {
    	
    }
    
    // Latitude test when range in minutes 
    @Test
    public void testLatitudeRangeMinutes() {
    	
    }
    
    // Longitude test when range has positive degree
    @Test
    public void testLongitudeRangePositives() {
    	
    }
    
    // Latitude test when range has positive degree 
    @Test
    public void testLatitudeRangePositives() {
    	
    }
    
    // Longitude test when range difference between min and max is greater than difference
    // between max and min
    @Test
    public void testLongitudeRangeAreaFlip() {
    	
    }
    
    
    /*
     * Warning: all the tests you write here must be runnable against any
     * Bounds class that follows the spec. It will be run against several staff
     * implementations of Bounds, which will be done by overwriting
     * (temporarily) your version of Bounds with the staff's version.
     * 
     * DO NOT strengthen the spec of Bounds or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Bounds, because that means you're testing a
     * stronger spec than Bounds says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */
}
