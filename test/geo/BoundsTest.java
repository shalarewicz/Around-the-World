/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package geo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class BoundsTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * 
     * See the Testing reading for examples of what a testing strategy
     * comment looks like. Make sure you have partitions.
     */

    private static final Angle LATITUDE_N = new Angle(42, 21, 35, CardinalDirection.NORTH);
    private static final Angle LONGITUDE_W = new Angle(71, 5, 31, CardinalDirection.WEST);

    private static final PointOfInterest GREAT_DOME = new PointOfInterest(LATITUDE_N, LONGITUDE_W, "Great Dome", "");

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
