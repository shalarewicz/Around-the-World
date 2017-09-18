/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package geo;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
        throw new RuntimeException("not implemented");
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
        throw new RuntimeException("not implemented");

    }
}
