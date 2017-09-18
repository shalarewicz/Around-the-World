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

    private static final Angle EQUATOR = new Angle(0, 0, 0, CardinalDirection.NORTH);
    private static final Angle NORTH_POLE = new Angle(90, 0, 0, CardinalDirection.NORTH);

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    // TODO: when you write your own test, state the partitions that the test covers in
    // a comment before the test, like this one
    @Test
    public void testToDegreesEquator() {
        assertEquals("expected equator at 0 degrees", 0.0, Angular.toDegrees(EQUATOR), 0.0001);
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
