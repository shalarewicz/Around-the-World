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
    private static final Angle N1 = new Angle(10, 10, 10, CardinalDirection.NORTH);
    private static final Angle N2 = new Angle(10, 10, 20, CardinalDirection.NORTH);
    private static final Angle N3 = new Angle(10, 20, 10, CardinalDirection.NORTH);
    private static final Angle N4 = new Angle(90, 0, 0, CardinalDirection.NORTH);
    private static final Angle S1 = new Angle(30, 30, 30, CardinalDirection.SOUTH);
    private static final Angle S2 = new Angle(90, 0, 0, CardinalDirection.SOUTH);
    private static final Angle E1 = new Angle(10, 10, 10, CardinalDirection.EAST);
    private static final Angle E2 = new Angle(10, 10, 20, CardinalDirection.EAST);
    private static final Angle E3 = new Angle(10, 20, 10, CardinalDirection.EAST);
    private static final Angle E4 = new Angle(170, 0, 0, CardinalDirection.EAST);
    private static final Angle W1 = new Angle(30, 30, 30, CardinalDirection.WEST);
    private static final Angle W2 = new Angle(170, 0, 0, CardinalDirection.WEST);
    

    // Instructor created points
    private static final PointOfInterest GREAT_DOME = new PointOfInterest(LATITUDE_N, LONGITUDE_W, "Great Dome", "");

    // Student created points
    private static final PointOfInterest POINT1 = new PointOfInterest(N1, E1, "Point1", "");
    private static final PointOfInterest POINT2 = new PointOfInterest(N2, E2, "Point2", "");
    private static final PointOfInterest POINT3 = new PointOfInterest(N3, E3, "Point3", "");
    private static final PointOfInterest POINT4 = new PointOfInterest(S1, W1, "Point4", "");
    private static final PointOfInterest POINT5 = new PointOfInterest(N1, E4, "Point5", "");
    private static final PointOfInterest POINT6 = new PointOfInterest(N1, W2, "Point6", "");
    private static final PointOfInterest NORTH_POLE = new PointOfInterest(N4, W2, "North Pole", "");
    private static final PointOfInterest SOUTH_POLE = new PointOfInterest(S2, W1, "South Pole", "");
    private static final PointOfInterest NORTH_POLE2 = new PointOfInterest(N4, E1, "North Pole 2", "");
    private static final PointOfInterest SOUTH_POLE2 = new PointOfInterest(S2, E3, "South Pole 2", "");
      
    // Test Sets
    	
    private static Set<PointOfInterest> createSet(PointOfInterest a, PointOfInterest b, PointOfInterest c) {
    	Set<PointOfInterest> result = new HashSet<PointOfInterest>();
    	if (a != null) {result.add(a);}
    	if (b != null) {result.add(b);}
    	if (c != null) {result.add(c);}
    	return result;
    }
    
    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    // Test Covers when set has only one element
    @Test
    public void testLatitudeRangeSingleton() {
        List<Angle> expected = Arrays.asList(LATITUDE_N, LATITUDE_N);
        List<Angle> range = Bounds.latitudeRange(Collections.singleton(GREAT_DOME));
        assertEquals("expected correct length", expected.size(), range.size());
        assertEquals("expected correct southern bound", expected.get(0), range.get(0));
        assertEquals("expected correct northern bound", expected.get(1), range.get(1));
    }

    // Test Covers when set has only one element
    @Test
    public void testLongitudeRangeSingleton() {
        List<Angle> expected = Arrays.asList(LONGITUDE_W, LONGITUDE_W);
        List<Angle> range = Bounds.longitudeRange(Collections.singleton(GREAT_DOME));
        assertEquals("expected correct length", expected.size(), range.size());
        assertEquals("expected correct western bound", expected.get(0), range.get(0));
        assertEquals("expected correct eastern bound", expected.get(1), range.get(1));
    }
    
//    // Test Empty List Latitude Range
//    @Test
//    public void testLatituteRangeEmpty() {
//        List<Angle> List<Angle> expected = Arrays.asList(); // Check how to catch an exception
//        List<Angle> List<Angle> range = Bounds.latitudeRange(Collections.EMPTY_SET);
//    }
//    
//    // Test Empty List Longitude Range
//    @Test
//    public void testLongitudeRangeEmpty() {
//        List<Angle> expected = Collections.EMPTY_LIST; // Check how to catch an exception
//        List<Angle> List<Angle> range = Bounds.longitudeRange(Collections.EMPTY_SET);
//        
//    }
    
    // Longitude test when range in seconds 
    @Test
    public void testLongitudeRangeSeconds() {
    	List<Angle> expected = Arrays.asList(E1, E2);
    	List<Angle> range = Bounds.longitudeRange(createSet(POINT1, POINT2, null));
        
        assertEquals("expected correct length", expected.size(), range.size());
        assertEquals("expected correct western bound", expected.get(0), range.get(0));
        assertEquals("expected correct eastern bound", expected.get(1), range.get(1));
    }
    
    // Latitude test when range in seconds 
    @Test
    public void testLatitudeRangeSeconds() {
    	List<Angle> expected = Arrays.asList(N1, N2);
        List<Angle> range = Bounds.latitudeRange(createSet(POINT1, POINT2, null));
        assertEquals("expected correct length", expected.size(), range.size());
        assertEquals("expected correct western bound", expected.get(0), range.get(0));
        assertEquals("expected correct eastern bound", expected.get(1), range.get(1));
    }
    
    // Longitude test when range in minutes
    @Test
    public void testLongitudeRangeMinutes() {
    	List<Angle> expected = Arrays.asList(E2, E3);
        List<Angle> range = Bounds.longitudeRange(createSet(POINT2, POINT3, null));
        assertEquals("expected correct length", expected.size(), range.size());
        assertEquals("expected correct western bound", expected.get(0), range.get(0));
        assertEquals("expected correct eastern bound", expected.get(1), range.get(1));
    }
    
    // Latitude test when range in minutes 
    @Test
    public void testLatitudeRangeMinutes() {
    	List<Angle> expected = Arrays.asList(N2, N3);
        List<Angle> range = Bounds.latitudeRange(createSet(POINT2, POINT3, null));
        assertEquals("expected correct length", expected.size(), range.size());
        assertEquals("expected correct western bound", expected.get(0), range.get(0));
        assertEquals("expected correct eastern bound", expected.get(1), range.get(1));
    }
    
    // Longitude test when range has positive degree
    @Test
    public void testLongitudeRangePositives() {
    	List<Angle> expected = Arrays.asList(W1, E3);
        List<Angle> range = Bounds.longitudeRange(createSet(POINT2, POINT3, POINT4));
        assertEquals("expected correct length", 2, range.size());
        assertEquals("expected correct western bound", expected.get(0), range.get(0));
        assertEquals("expected correct eastern bound", expected.get(1), range.get(1));
    }
    
    // Latitude test when range has positive degree 
    @Test
    public void testLatitudeRangePositives() {
    	List<Angle> expected = Arrays.asList(S1, N3);
        List<Angle> range = Bounds.latitudeRange(createSet(POINT2, POINT3, POINT4));
        assertEquals("expected correct length", 2, range.size());
        assertEquals("expected correct western bound", expected.get(0), range.get(0));
        assertEquals("expected correct eastern bound", expected.get(1), range.get(1));
    }
    
    // Longitude test when range difference between min and max is greater than difference
    // between max and min
    @Test
    public void testLongitudeRangeAreaFlip() {
    	List<Angle> expected = Arrays.asList(E4, W2);
        List<Angle> range = Bounds.longitudeRange(createSet(POINT5, POINT6, null)); 
        assertEquals("expected correct length", expected.size(), range.size());
        assertEquals("expected correct western bound", expected.get(0), range.get(0));
        assertEquals("expected correct eastern bound", expected.get(1), range.get(1));
    }

    // Longitude test when range difference between min and max is greater than difference
    // between max and min but there is a point between min and max so no flip occurs
    @Test
    public void testLongitudeRangeAreaNoFlip() {
        List<Angle> expected = Arrays.asList(W2, E4);
        List<Angle> range = Bounds.longitudeRange(createSet(POINT1, POINT5, POINT6));
        assertEquals("expected correct length", 2, range.size());
        assertEquals("expected correct western bound", expected.get(0), range.get(0));
        assertEquals("expected correct eastern bound", expected.get(1), range.get(1));
    }
    
    // Longitude test when min longitude is a pole
    @Test
    public void testLongitudeRangeMinIsPole() {
        List<Angle> expected = Arrays.asList(W1, W1);
        List<Angle> range = Bounds.longitudeRange(createSet(NORTH_POLE, POINT4, null));
        assertEquals("expected correct length", 2, range.size());
        assertEquals("expected correct western bound", expected.get(0), range.get(0));
        assertEquals("expected correct eastern bound", expected.get(1), range.get(1));
    }
    
    // Longitude test when max longitude is a pole
    @Test
    public void testLongitudeRangeMaxIsPole() {
        List<Angle> expected = Arrays.asList(W1, W1);
        List<Angle> range = Bounds.longitudeRange(createSet(NORTH_POLE2, POINT4, null));
        assertEquals("expected correct length", 2, range.size());
        assertEquals("expected correct western bound", expected.get(0), range.get(0));
        assertEquals("expected correct eastern bound", expected.get(1), range.get(1));
    }
    
    // Longitude test when all points are on a pole. Should return the minimum longitude.
    @Test
    public void testLongitudeRangeAllPoles() {
        List<Angle> expected = Arrays.asList(W1, W1);
        List<Angle> range = Bounds.longitudeRange(createSet(NORTH_POLE2, SOUTH_POLE, SOUTH_POLE2));
        assertEquals("expected correct length", 2, range.size());
        assertEquals("expected correct western bound", expected.get(0), range.get(0));
        assertEquals("expected correct eastern bound", expected.get(1), range.get(1));
    }
    
    
    // Longitude test when area from min to max > area from max to min but all other points are on a pole
    // so longitudeRange returns [max, min]
    @Test
    public void testLongitudeRangeFlipWithPoles() {
    	 List<Angle> expected = Arrays.asList(E4, W2);
         List<Angle> range = Bounds.longitudeRange(createSet(NORTH_POLE, POINT5, POINT6));
         assertEquals("expected correct length", 2, range.size());
         assertEquals("expected correct western bound", expected.get(0), range.get(0));
         assertEquals("expected correct eastern bound", expected.get(1), range.get(1));
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
