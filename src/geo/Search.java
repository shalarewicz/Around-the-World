/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package geo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Methods for searching points of interest.
 * 
 * <p>
 * PS1 instructions: do NOT change the method signatures or specifications of
 * these methods, but you should implement their method bodies, and you may add
 * new public or private methods or classes if you like.
 */
public class Search {

    /**
     * Guess which points of interest (POIs) are duplicates that represent the
     * same physical place.
     * <p>
     * This method identifies groups of POIs in the input list that appear to be
     * duplicative, and picks a best entry from the group. That entry is a key
     * in the returned map, and its value is a list of the other duplicates in
     * the group.
     * <p>
     * POIs are identified as duplicates if and only if their location, name,
     * and/or description provide evidence that they represent the same physical
     * place. POIs whose latitude, longitude, and name are .equals() must be
     * identified as duplicates. Other evidence may be used at the implementor's
     * discretion.
     * 
     * @param pointsOfInterest a list of POIs, not modified by this method
     * @return a map in which every element of pointsOfInterest appears
     *         exactly once, as either a key or in a value list, so that
     *         pointsOfInterest.size() ==
     *         map.keySet().size() + sum of v.size() for all value lists v in map.values(),
     *         and where each key maps to the list of POIs that are identified
     *         as the key's duplicates, as described above.
     */
    public static Map<PointOfInterest, List<PointOfInterest>> collectDuplicates(List<PointOfInterest> pointsOfInterest) {
    	Map<PointOfInterest, List<PointOfInterest>> result = new HashMap<PointOfInterest, List<PointOfInterest>>();
    	
        for (int i = 0; i < pointsOfInterest.size(); i++) {
        	PointOfInterest point = pointsOfInterest.get(i);
        	if (i == 0) {
        		result.put(point, new ArrayList<PointOfInterest>());
        		continue;
        	}
        	Set<PointOfInterest> keySet = result.keySet();
        	ArrayList<PointOfInterest> keys = new ArrayList<PointOfInterest>(keySet);
        	
        	for (PointOfInterest key : keys) {
        		if (duplicates(key, point)) {
        			result.get(key).add(point);
        		}
        		else {
        			result.put(point, new ArrayList<PointOfInterest>());
        		}
        	}
        }
        return result;
    }
    
//    private static void insertPoint(Map<PointOfInterest, List<PointOfInterest>> map, PointOfInterest point) {
//    	for (PointOfInterest key : map.keySet()) {
//    		if (duplicates(key, point)) {
//    			map.get(key).add(point);
//    		}
//    	}
//    }
    private static boolean duplicates(PointOfInterest key, PointOfInterest point) {
    	String keyName = key.name().toLowerCase();
    	Angle keyLatitude = key.latitude();
    	Angle keyLongitude = key.longitude();
    	
    	String pointName = point.name().toLowerCase();
    	Angle pointLatitude = point.latitude();
    	Angle pointLongitude = point.longitude();
    	
    	return (keyName.equals(pointName) && 
    			Math.abs(Angular.toDegrees(Angular.displacement(keyLatitude, pointLatitude))) < 0.05 && 
    			Math.abs(Angular.toDegrees(Angular.displacement(keyLongitude, pointLongitude))) < 0.5 );
    	
    }


    /**
     * Search for points of interest that match keywords.
     *
     * @param pointOfInterestMap
     *           a map whose keys are the POIs that can be returned by this method,
     *           and whose value lists contain alternative POIs that can be used to match
     *           the keywords. For example, a key POI might be named "Great Dome", and
     *           its value list might include POIs with descriptions "Lobby 10" or
     *           "Barker Library".
     * @param keywords
     *           keywords to search for. Must be nonempty strings of letters (A-Z, a-z),
     *           digits (0-9), underscores ("_"), hyphens ("-"), or spaces (" ").
     *           Keywords are treated case-insensitively for matching, so "dome"
     *           is the same as "DOME", and the name "Great Dome" matches either one.
     * @return set of all keys in pointOfInterestMap such that every keyword is a substring
     *         (case-insensitively) of the name or description of the key POI or any of its value POIs.
     */
    public static Set<PointOfInterest> search(Map<PointOfInterest, List<PointOfInterest>> pointOfInterestMap,
                                              Set<String> keywords) {
    	Set<PointOfInterest> result = new HashSet<PointOfInterest>();
    	
    	for (PointOfInterest key : pointOfInterestMap.keySet()) {
    		boolean allFound = true;
    		for (String keyword : keywords) {
    			String keywordNoCase = keyword.toLowerCase();
	    		boolean found = false;
	    		// check if the key contains the keyword
	    		if (containsKeyword(key, keywordNoCase)) {
	    			found = true;
	    			// break;
	    		}
	    		else {
		    		// Otherwise we will need to check each point in the value list
		    		List<PointOfInterest> values = pointOfInterestMap.get(key);
		    		
		    		for (PointOfInterest point : values) {
		    			if (containsKeyword(point, keywordNoCase)) {
		    				found = true;
		    				break;
		    			}
		    		}
		    		
	    		}	    		
	    		if (!found) {
	    			allFound = false;
	    		}
	    	}
    		if (allFound) {
    			result.add(key);
    		}
    	}
    	return result;
    }
    
    private static boolean containsKeyword(PointOfInterest point, String keyword) {
    	return (point.name().toLowerCase().contains(keyword) || point.description().toLowerCase().contains(keyword));
    }
    
}
