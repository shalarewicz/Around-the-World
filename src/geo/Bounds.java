/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package geo;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;


/**
 * Methods for computing the latitudes and longitudes spanned by a set of points.
 * 
 * <p>
 * PS1 instructions: do NOT change the method signatures or specifications of
 * these methods, but you should implement their method bodies, and you may add
 * new public or private methods or classes if you like.
 */
public class Bounds {
	
	private static final CardinalDirection NORTH = CardinalDirection.NORTH;
	private static final CardinalDirection SOUTH = CardinalDirection.SOUTH;
	private static final CardinalDirection EAST = CardinalDirection.EAST;
	private static final CardinalDirection WEST = CardinalDirection.WEST;
	
	private static Angle max(Angle x, Angle y) {
		if (x == null) {return y;}
		if (y == null) {return x;}
		if (x.direction() == y.direction()) {
			if (x.degrees() == y.degrees()) {
				if (x.minutes() == y.minutes()) {
					if (x.seconds() >= y.seconds()) {
						return x;
					}
					else {return y;	}
				}
				else if (x.minutes() > y.minutes()) { return x;}
				else {return y;}					
			}
			else if (x.degrees() > y.degrees()) {return x;}
			else {return y;}
		}
		else {
			if (x.direction() == NORTH) { return x; }
			else if (x.direction() == EAST) { return x; }
			else { return y; }
		}
	}
	
	private static Angle min(Angle x, Angle y) {
		if (x == null) {return y;}
		if (y == null) {return x;}
		if (x.direction() == y.direction()) {
			if (x.degrees() == y.degrees()) {
				if (x.minutes() == y.minutes()) {
					if (x.seconds() <= y.seconds()) {
						return x;
					}
					else {return y;	}
				}
				else if (x.minutes() < y.minutes()) { return x;}
				else {return y;}					
			}
			else if (x.degrees() < y.degrees()) {return x;}
			else {return y;}
		}
		else {
			if (x.direction() == SOUTH) { return x; }
			else if (x.direction() == WEST) { return x; }
			else { return y; }
		}
	}
    /**
     * Find minimum-area latitude range that encloses all points of interest.
     *
     * @param pointsOfInterest  set of POIs, not modified by this method
     * @return a new two-element list representing a minimum-area band of latitudes such
     * that sweeping a latitude line from latitude list[0] NORTHWARD to latitude list[1]
     * touches every point in pointsOfInterest.
     */

    public static List<Angle> latitudeRange(Set<PointOfInterest> pointsOfInterest) {
        Angle min = null;
        Angle max = null;
        List<Angle> result = new ArrayList<Angle>();
        
        for (PointOfInterest point : pointsOfInterest) {
        	Angle latitude = point.latitude();
        	min = min(min, latitude);
        	max = max(max, latitude);
        }
        
        result.add(min);
        result.add(max);
        return result;
        
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
        Angle min = null;
        Angle max = null;
        List<Angle> result = new ArrayList<Angle>();
        
        for (PointOfInterest point : pointsOfInterest) {
        	Angle longitude = point.longitude();
        	min = min(min, longitude);
        	max = max(max, longitude);
        }
        
        if (Angular.displacement(min, max).direction() == EAST) {        	
        	result.add(min);
        	result.add(max);
        }
        else {
        	result.add(max);
        	result.add(min);
        }
        return result;
    }

}
