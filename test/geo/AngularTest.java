/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package geo;

import static org.junit.Assert.*;

import org.junit.Test;

public class AngularTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * 
     * See the Testing reading for examples of what a testing strategy
     * comment looks like. Make sure you have partitions.
     */

	// Instructor created for testing.
    private static final Angle EQUATOR = new Angle(0, 0, 0, CardinalDirection.NORTH);
    private static final Angle NORTH_POLE = new Angle(90, 0, 0, CardinalDirection.NORTH);
    
    // Student created for testing toDegrees
    private static final Angle MAX = new Angle(Integer.MAX_VALUE, 0, 0, CardinalDirection.NORTH);
    private static final Angle MAX_MINUTES = new Angle(0, 60 * Integer.MAX_VALUE, 0, CardinalDirection.NORTH);
    private static final Angle MAX_SECONDS = new Angle(0, 0, 3600 * Integer.MAX_VALUE, CardinalDirection.NORTH);
    
    // Student Created for testing displacement
    private static final Angle ANTI_MERIDIAN_NORTH = new Angle(180, 0, 0, CardinalDirection.NORTH);
    private static final Angle ANTI_MERIDIAN_SOUTH = new Angle(180, 0, 0, CardinalDirection.SOUTH);
    private static final Angle ANTI_MERIDIAN_EAST = new Angle(180, 0, 0, CardinalDirection.EAST);
    private static final Angle ANTI_MERIDIAN_WEST = new Angle(180, 0, 0, CardinalDirection.WEST);
    private static final Angle NORTH = new Angle(60, 10, 5, CardinalDirection.NORTH);
    private static final Angle NORTH2 = new Angle(50, 50, 55, CardinalDirection.NORTH);
    private static final Angle EAST = new Angle(30, 30, 55, CardinalDirection.EAST);
    private static final Angle SOUTH = new Angle(30, 50, 55, CardinalDirection.SOUTH);
    private static final Angle SOUTH_POLE = new Angle(90, 0, 0, CardinalDirection.SOUTH);
    private static final Angle EQUATOR_EAST = new Angle(0, 0, 0, CardinalDirection.EAST);
    private static final Angle EQUATOR_SOUTH = new Angle(0, 0, 0, CardinalDirection.SOUTH);

    

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    // TODO: when you write your own test, state the partitions that the test covers in
    // a comment before the test, like this one
    
    // Partitions: d = MAX_INT, m = 60 * MAX_INT, s = 60 * 60 * MAX_INT, small positive, d, m, s = 0
    // and combinations. 
    
    @Test
    public void testToDegreesEquator() {
        assertEquals("expected equator at 0 degrees", 0.0, Angular.toDegrees(EQUATOR), 0.0001);
    }
    
    // Test covers partition when Angle has max degrees allowed
    @Test
    public void testToDegreesMax() {
    	double x = Integer.MAX_VALUE / 1.;
    	assertEquals("expected max at " + x + " degrees", x, Angular.toDegrees(MAX), 0.0001);
    }
    
    // Test covers partition when Angle has max minutes allowed
    @Test
    public void testToDegreesMaxMinutes() {
    	double x = Integer.MAX_VALUE / 60.;
    	assertEquals("expected max at " + x + " decrees", x, Angular.toDegrees(MAX_MINUTES), 0.0001);
    }
    
    // Test covers partition when Angle has max seconds allowed
    @Test
    public void testToDegreesMaxSeconds() {
    	double x = Integer.MAX_VALUE / 60. / 60;
    	assertEquals("expected max at " + x + " decrees", x, Angular.toDegrees(MAX_SECONDS), 0.0001);
    }
    
    // Creates new Angle object from a Double rounded to the nearest arc-second
    private static Angle createAngle(double d) {
    	int degrees = ((Double) d).intValue();
    	int minutes = ((Double) ((d - degrees) * 60)).intValue();
    	int seconds = ((Double) ((d - degrees) * 3600)).intValue();
    	return new Angle(degrees, minutes, seconds, CardinalDirection.NORTH);
    	
    }
    
    // Test covers when degrees, arc-minutes and arc seconds all small positives
    @Test
    public void testToDegreesSmallPositive() {
    	double x = 50.;
    	assertEquals("expected " + x + " degrees", x, Angular.toDegrees(createAngle(x)), 0.001);
    }

    // TODO: when you write your own test, state the partitions that the test covers in
    // a comment before the test, like this one
    @Test
    public void testDisplacementEquatorNorthPole() {
        Angle displacement = Angular.displacement(EQUATOR, NORTH_POLE);
        assertEquals("expected 90 degrees", 90, displacement.degrees());
        assertEquals("expected 0 minutes", 0, displacement.minutes());
        assertEquals("expected 0 seconds", 0, displacement.seconds());
        assertEquals("expected north", CardinalDirection.NORTH, displacement.direction());
    }
    
    // Displacement between North and South Poles
    @Test
    public void testDisplacementNorthSouthPoles() {
    	Angle displacement = Angular.displacement(NORTH_POLE, SOUTH_POLE);
    	assertEquals("expected 180 degrees", 180, displacement.degrees());
        assertEquals("expected 0 minutes", 0, displacement.minutes());
        assertEquals("expected 0 seconds", 0, displacement.seconds());
        assertEquals("expected south", CardinalDirection.SOUTH, displacement.direction());
    }
    
    // Between Max position E and max position W
    @Test
    public void testDisplacementEWAntimerdidian() {
    	Angle displacement = Angular.displacement(ANTI_MERIDIAN_EAST, ANTI_MERIDIAN_WEST);
    	assertEquals("expected 0 degrees", 0, displacement.degrees());
        assertEquals("expected 0 minutes", 0, displacement.minutes());
        assertEquals("expected 0 seconds", 0, displacement.seconds());
        assertEquals("expected south", CardinalDirection.EAST, displacement.direction());
    }
    
    // Intersection of Anti-meridian and Equator N, S
    @Test
    public void testDisplacementNSAntimeridian() {
    	Angle displacement = Angular.displacement(ANTI_MERIDIAN_NORTH, ANTI_MERIDIAN_SOUTH);
    	assertEquals("expected 0 degrees", 0, displacement.degrees());
        assertEquals("expected 0 minutes", 0, displacement.minutes());
        assertEquals("expected 0 seconds", 0, displacement.seconds());
        assertEquals("expected south", CardinalDirection.NORTH, displacement.direction());
    }
    
    // Intersection of Prime Meridian and Equator with Latitude and Longitude
    @Test (expected = RuntimeException.class)
    public void testDisplacementNEPrimeMeridianEquator() {
    	Angle displacement = Angular.displacement(EQUATOR, EQUATOR_EAST);
 
    }
    
    @Test (expected = RuntimeException.class)
    // Small Positives in Latitude and Longitude
    public void testDisplacementNESmallPositives() {
    	Angle displacement = Angular.displacement(NORTH, EAST);

    }
    
    // MAX INT and 0
    @Test
    public void testDisplacementMAXINTEquator() {
    	Angle displacement = Angular.displacement(MAX, EQUATOR);
    	assertEquals("expected MAX_INT degrees", Integer.MAX_VALUE, displacement.degrees());
        assertEquals("expected 0 minutes", 0, displacement.minutes());
        assertEquals("expected 0 seconds", 0, displacement.seconds());
        assertEquals("expected south", CardinalDirection.SOUTH, displacement.direction());
    }
    
    // small positives with same Cardinal Direction
    @Test
    public void testDisplacementSameDirection() {
    	Angle displacement = Angular.displacement(NORTH, NORTH2);
    	assertEquals("expected 10 degrees", 10, displacement.degrees());
        assertEquals("expected 20 minutes", 20, displacement.minutes());
        assertEquals("expected 10 seconds", 10, displacement.seconds());
        assertEquals("expected south", CardinalDirection.SOUTH, displacement.direction());
    }
    
    // Small positives in opposite directions
    @Test
    public void testDisplacementPostiveOppostieDirection() {
    	Angle displacement = Angular.displacement(NORTH, SOUTH);
    	assertEquals("expected 90 degrees", 90, displacement.degrees());
        assertEquals("expected 1 minutes", 1, displacement.minutes());
        assertEquals("expected 0 seconds", 0, displacement.seconds());
        assertEquals("expected south", CardinalDirection.SOUTH, displacement.direction());
    }
    
    @Test
    public void testDisplacementNSEquatorPrimeMeridian() {
    	Angle displacement = Angular.displacement(EQUATOR, EQUATOR_SOUTH);
    	assertEquals("expected 0 degrees", 0, displacement.degrees());
        assertEquals("expected 0 minutes", 0, displacement.minutes());
        assertEquals("expected 0 seconds", 0, displacement.seconds());
        assertEquals("expected south", CardinalDirection.SOUTH, displacement.direction());
    }

    // This test covers the case in which sweeping from begin to end is > 180 degrees
    @Test
    public void testDisplacementGreaterThanHalf() {
    	Angle displacement = Angular.displacement(NORTH2, NORTH);
    	assertEquals("expected 51 degrees", 51, displacement.degrees());
        assertEquals("expected 1 minutes", 1, displacement.minutes());
        assertEquals("expected 0 seconds", 0, displacement.seconds());
        assertEquals("expected south", CardinalDirection.SOUTH, displacement.direction());
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * Angular class that follows the spec. It will be run against several staff
     * implementations of Angular, which will be done by overwriting
     * (temporarily) your version of Angular with the staff's version.
     * 
     * DO NOT strengthen the spec of Angular or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Angular, because that means you're testing a
     * stronger spec than Angular says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */
}
