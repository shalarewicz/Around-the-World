/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package geo;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

import org.junit.Test;

public class SearchTest {

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    // PARTITIONS - collectDuplicates
    // Points list size 0, 1, n
    // List contains all unique elements
    // List contains all duplicates
    // List contains points with same names but different location
    // List contains points with same location but different names
    
    // Points needed:
    // single point tested
    // two points with same name and location tested
    // two points with sane name different location
    // two points with different name same location tested
    // two points with same name with different case and same location tested
    
    // Angles
    private static final Angle N1 = new Angle(10, 10, 10, CardinalDirection.NORTH);
    private static final Angle E1 = new Angle(10, 10, 10, CardinalDirection.EAST);
    private static final Angle S1 = new Angle(10, 10, 10, CardinalDirection.SOUTH);
    private static final Angle W1 = new Angle(10, 10, 10, CardinalDirection.WEST);
    
    // Points
    
    private static final PointOfInterest POINT1 = new PointOfInterest(N1, E1, "POINT 1", "this is a test point");
    private static final PointOfInterest POINT2 = new PointOfInterest(N1, E1, "POint 1", "test point 2");
    private static final PointOfInterest POINT3 = new PointOfInterest(N1, E1, "POINT 1", "identical to POINT1");
    private static final PointOfInterest POINT4 = new PointOfInterest(S1, W1, "POINT 1", "same name as POINT1 location somewhere else");
    private static final PointOfInterest POINT5 = new PointOfInterest(N1, E1, "Somewhere", "same location different name as POINT1");
    private static final PointOfInterest POINT6 = new PointOfInterest(N1, E1, "Out There", "Over the rainbow");

    
    // TODO: Create test case for an error tolerance in point location. 

    // Tests collectDuplicates when the list is empty
    @Test
    public void testCollectDuplicatesEmpty() {
        Map<PointOfInterest, List<PointOfInterest>> deduplicated = Search.collectDuplicates(Collections.emptyList());
        assertTrue("expected empty map", deduplicated.isEmpty());
    }
    
    // Tests collect duplicates when list size is one
    @Test
    public void testCollectDuplicatesSingle() {
    	List<PointOfInterest> testList = new ArrayList<PointOfInterest>();
    	testList.add(POINT1);
    	Map<PointOfInterest, List<PointOfInterest>> expected = new HashMap<PointOfInterest, List<PointOfInterest>>();
    	expected.put(POINT1, Collections.EMPTY_LIST);
    	Map<PointOfInterest, List<PointOfInterest>> deduplicated = Search.collectDuplicates(testList);
    	assertEquals("Expected map size", expected.size(), deduplicated.size());
    	assertTrue("expected one key", deduplicated.containsKey(POINT1));
    }
    // Tests collectDuplicates when list contains all duplicate elements
    @Test
    public void testCollectDuplicatesAll() {
    	List<PointOfInterest> testList = new ArrayList<PointOfInterest>();
    	testList.add(POINT1);
    	testList.add(POINT2);
    	testList.add(POINT3);
    	Map<PointOfInterest, List<PointOfInterest>> deduplicated = Search.collectDuplicates(testList);
    	assertEquals("Expected map size", 1, deduplicated.size());
    	boolean mapContainsKeyInList = deduplicated.containsKey(POINT1) || deduplicated.containsKey(POINT2) || deduplicated.containsKey(POINT3);
    	assertTrue("expected key to be an element of the list", mapContainsKeyInList);
    	Set<PointOfInterest> keys = deduplicated.keySet();
    	for (PointOfInterest key : keys) {
    		List<PointOfInterest> value = deduplicated.get(key);
    		assertEquals("expected size of values", testList.size() - 1, value.size());
    		testList.remove(key);
    		for (PointOfInterest point : value) {
    			assertTrue("expected value in testlist", testList.contains(point));
    		}
    	}
    	
    }
    
    // Tests collectDuplicates when list contains points with same name but different location and n > 1 unique elements
    // where the size of the list is n
    @Test
    public void testCollectDuplicatesAllUnique() {
    	List<PointOfInterest> testList = new ArrayList<PointOfInterest>();
    	testList.add(POINT1);
    	testList.add(POINT4);
    	testList.add(POINT5);
    	Map<PointOfInterest, List<PointOfInterest>> deduplicated = Search.collectDuplicates(testList);
    	assertEquals("Expected map size", testList.size(), deduplicated.size());
    	for (PointOfInterest point : testList) {
    		assertTrue("expected element in list", deduplicated.containsKey(point));
    		assertEquals("expected key to map to empty list", Collections.EMPTY_LIST, deduplicated.get(point));
    	}
    	
    }
    
    // TODO: PARTIONS search()
    // Map Size = 0, n
    // number of keywords = 0, 1, m
    // keyword found in name, description
    // keyword found in key, value
    // keyword not found
    // keyword does and doesn't match case

    // Test Map size 0 and number of keywords 0
    @Test
    public void testSearchEmpty() {
        Set<PointOfInterest> points = Search.search(Collections.emptyMap(), Collections.emptySet());
        assertTrue("expected empty set", points.isEmpty());
    }
    
    

    
    // Tests map size n, m key words, key found in name or description of key
    // Also tests case insensitivity of name or description in values
    // TODO; need 2 keys
    // 	key 1 match name in key
    //  key 2 match description in key
    @Test
    public void testSearchMatchKeys() {
    	// Build set of keywords
    	Set<String> keywords = new HashSet<String>();
    	keywords.add("point");
    	
    	// Build Test map
    	Map<PointOfInterest, List<PointOfInterest>> testMap = new HashMap<PointOfInterest, List<PointOfInterest>>();
    	testMap.put(POINT1, new ArrayList<PointOfInterest>()); // POINT1, [POINT6])
    	testMap.get(POINT1).add(POINT6);
    	testMap.put(POINT5, new ArrayList<PointOfInterest>()); // (POINT 5, [POINT6])
    	testMap.get(POINT5).add(POINT6);
    	
    	// Test result
    	Set<PointOfInterest> points = Search.search(testMap, keywords);
    	assertEquals("Expected size of set", 2, points.size());
    	for (String keyword : keywords) {
    		String keywordNoCase = keyword.toLowerCase();
    		for (PointOfInterest point : points) {
    			String nameNoCase = point.name().toLowerCase();
    			String description = point.description().toLowerCase();
    			assertTrue("Expected to contain keyword", nameNoCase.contains(keywordNoCase) || description.contains(keywordNoCase));
    			
    		}
    	}
    	
    }
    
    // Tests map size n, m key words, key found in name or description of value 
    // Also tests case insensitivity of name or description in values
    // Tests map size n with m keywords and no matches found. 
    @Test
    public void testSearchMatchValues() {
    	System.out.println("Starting...");
    	// Build set of keywords
    	Set<String> keywords = new HashSet<String>();
    	keywords.add("point");
    	keywords.add("SomeWhere");
    	
    	// Build Test map
    	Map<PointOfInterest, List<PointOfInterest>> testMap = new HashMap<PointOfInterest, List<PointOfInterest>>();
    	testMap.put(POINT3, new ArrayList<PointOfInterest>()); // (POINT3, [POINT4,  POINT5] )
    	testMap.get(POINT3).add(POINT4);
    	testMap.get(POINT3).add(POINT5);
    	testMap.put(POINT6, new ArrayList<PointOfInterest>()); // (POINT6, [POINT1] )
    	testMap.get(POINT6).add(POINT1);
    	for (PointOfInterest key : testMap.keySet()) {
    		System.out.print("Key = " + key.name() + ", (");
    		for (PointOfInterest value : testMap.get(key)) {
    			System.out.print(" " + value.name() + ", ");
    		}
    		System.out.println(")");
    		
    		
    		
    	}
    	System.out.println("Testing map " + testMap);
    	System.out.println("Looking for keywords " + keywords);
    	
    	
    	// Test result
    	Set<PointOfInterest> points = Search.search(testMap, keywords);
    	System.out.println("End");
    	assertEquals("Expected size of set", 1, points.size());
    	for (String keyword : keywords) {
    		String keywordNoCase = keyword.toLowerCase();
    		for (PointOfInterest point : points) {
    			List<PointOfInterest> values = testMap.get(point);
    			boolean foundMatch = false;
    			for (PointOfInterest value : values) {
    				String nameNoCase = value.name().toLowerCase();
        			String description = value.description().toLowerCase();
        			if (nameNoCase.contains(keywordNoCase) || description.contains(keywordNoCase)) {
        				foundMatch = true; 
        				break;
        			}
    			}
    			assertTrue("Expected to contain keyword", foundMatch);
    		}
    	}
    	assertTrue(!points.contains(POINT6));
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
