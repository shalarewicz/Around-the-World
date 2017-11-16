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
		double xDegrees = Angular.toDegrees(x);
		double yDegrees = Angular.toDegrees(y);
		if (xDegrees >= yDegrees) { return x;}
		else { return y;}
	}
	
	private static Angle min(Angle x, Angle y) {
		if (x == null) {return y;}
		if (y == null) {return x;}
		double xDegrees = Angular.toDegrees(x);
		double yDegrees = Angular.toDegrees(y);
		if (xDegrees <= yDegrees) { return x;}
		else { return y;}
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
    	if (pointsOfInterest.size() < 1) {
    		throw new RuntimeException("EmptySetException");
    	}
    	
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
    
    /* @param Angle - angle not modified by the method
     * @return ture if the angle represents the North South pole
     */
    
    private static boolean isPole(PointOfInterest a) {
    	return (a.latitude().degrees() == 90 && a.latitude().minutes() ==  0 && 
    			a.latitude().minutes() == 0 && 
    			(a.latitude().direction() == NORTH || a.latitude().direction() == SOUTH));
    }
    
    private static int compareLongitude(PointOfInterest x, PointOfInterest y) {
    	if (min(x.longitude(), y.longitude()) == max(x.longitude(), y.longitude())) {
    		return 0;
    	}
    	else if (min(x.longitude(), y.longitude()) == y.longitude()) {
    		return 1;
    	}
    	else if (min(x.longitude(), y.longitude()) == x.longitude()) {
    		return -1;
    	}
    	
    	else throw new RuntimeException("Compare Failure");
    	
    }
    
    private static List<PointOfInterest> longitudeSort(List<PointOfInterest> points){
    	List<PointOfInterest> less = new ArrayList<PointOfInterest>();
    	List<PointOfInterest> equal = new ArrayList<PointOfInterest>();
    	List<PointOfInterest> greater = new ArrayList<PointOfInterest>();
    	
    	if (points.size() <= 1) {
    		return points;
    	}
    	else {
    		PointOfInterest partition = points.get(0);
    		for (int i = 1; i < points.size(); i++) {
    			if (compareLongitude(points.get(i),  partition) < 0) {
    				less.add(points.get(i));
    			}
    			else if (compareLongitude(points.get(i),  partition) == 0) {
    				equal.add(points.get(i));
    			}
    			else if (compareLongitude(points.get(i),  partition) > 0) {
    				greater.add(points.get(i));
    			}
    		}
    		List<PointOfInterest> newLess = longitudeSort(less);
    		List<PointOfInterest> newEqual = longitudeSort(equal);
    		List<PointOfInterest> newGreater = longitudeSort(greater);
    		
    		List<PointOfInterest> result = new ArrayList<PointOfInterest>();
    		result.addAll(newLess);
    		result.add(partition);
    		result.addAll(newEqual);
    		result.addAll(newGreater);
    		
    		return result;
    	}    	
    	// make sure you move poles to end
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
    	if (pointsOfInterest.isEmpty()) {
    		throw new RuntimeException("EmptySetException");
    	}
    	
    	// find true min and true max (ie if exists two points with same long then find the one not on a pole if all on a pole return next min or max
    	// compare index of true min and max. if difference is one then return [min, max] or [max, min] if displacement(min, max) == WEST
    	// if difference is zero retun [min, min]
    	// if difference > 1 then return [min, max]
    	
    	List<Angle> result = new ArrayList<Angle>();	
    
		List<PointOfInterest> noPoles = new ArrayList<PointOfInterest>();
		List<PointOfInterest> poles = new ArrayList<PointOfInterest>();
		
		for (PointOfInterest point : pointsOfInterest) {
			if (!isPole(point)) {
				noPoles.add(point);
			} else {
				poles.add(point);
			}
		}
		
		List<PointOfInterest> sortedPoints = longitudeSort(noPoles);
		List<PointOfInterest> sortedPoles = longitudeSort(poles);
		
		if (sortedPoints.size() == 0) {
			result.add(sortedPoles.get(0).longitude());
			result.add(sortedPoles.get(0).longitude());
		} else {
			PointOfInterest min = sortedPoints.get(0);
			PointOfInterest max = sortedPoints.get(sortedPoints.size() - 1);
			
			if (sortedPoints.indexOf(min) == sortedPoints.indexOf(max)) {
				result.add(min.longitude());
				result.add(min.longitude());
			}
			
			else if (sortedPoints.indexOf(max) - sortedPoints.indexOf(min) == 1 && Angular.displacement(min.longitude(), max.longitude()).direction() == WEST) {
					result.add(max.longitude());
					result.add(min.longitude());
			}
			else {
				result.add(min.longitude());
				result.add(max.longitude());
			}
		}
    	return result;
    }
}
