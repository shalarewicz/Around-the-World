/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package geo;

import java.util.List;
import java.util.Set;

/**
 * Methods for computing the latitudes and longitudes spanned by a set of points.
 * 
 * <p>
 * PS1 instructions: do NOT change the method signatures or specifications of
 * these methods, but you should implement their method bodies, and you may add
 * new public or private methods or classes if you like.
 */
public class Bounds {

    /**
     * Find minimum-area latitude range that encloses all points of interest.
     *
     * @param pointsOfInterest  set of POIs, not modified by this method
     * @return a new two-element list representing a minimum-area band of latitudes such
     * that sweeping a latitude line from latitude list[0] NORTHWARD to latitude list[1]
     * touches every point in pointsOfInterest.
     */
    public static List<Angle> latitudeRange(Set<PointOfInterest> pointsOfInterest) {
        throw new RuntimeException("not implemented");
    }

    /**
     * Find minimum-area longitude range that encloses all points of interest.
     *
     * @param pointsOfInterest  set of POIs, not modified by this method
     * @return a new two-element list representing a minimum-area band of longitudes
     * (also called a <a href="http://mathworld.wolfram.com/SphericalLune.html">spherical lune</a>)
     * such that sweeping a longitude line from longitude list[0] EASTWARD to longitude
     * list[1] touches every point in pointsOfInterest.
     */
    public static List<Angle> longitudeRange(Set<PointOfInterest> pointsOfInterest) {
        throw new RuntimeException("not implemented");
    }

}
