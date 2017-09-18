/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package geo;

import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SearchTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * 
     * See the Testing reading for examples of what a testing strategy
     * comment looks like. Make sure you have partitions.
     */

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    // TODO: when you write your own test, state the partitions that the test covers in
    // a comment before the test, like this one
    @Test
    public void testCollectDuplicatesEmpty() {
        Map<PointOfInterest, List<PointOfInterest>> deduplicated = Search.collectDuplicates(Collections.emptyList());
        assertTrue("expected empty map", deduplicated.isEmpty());
    }

    // TODO: when you write your own test, state the partitions that the test covers in
    // a comment before the test, like this one
    @Test
    public void testSearchEmpty() {
        Set<PointOfInterest> points = Search.search(Collections.emptyMap(), Collections.emptySet());
        assertTrue("expected empty set", points.isEmpty());
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * Search class that follows the spec. It will be run against several staff
     * implementations of Search, which will be done by overwriting
     * (temporarily) your version of Search with the staff's version.
     * 
     * DO NOT strengthen the spec of Search or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Search, because that means you're testing a
     * stronger spec than Search says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */
}
