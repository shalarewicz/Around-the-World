/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package geo;

/**
 * Methods for computing with angles.
 * 
 * <p>
 * PS1 instructions: do NOT change the method signatures or specifications of
 * these methods, but you should implement their method bodies, and you may add
 * new public or private methods or classes if you like.
 */
public class Angular {
	/** Sexagesimal base. Minutes per degree, and seconds per minute. */
	private static final double BASE_60 = 60.;
	private static final CardinalDirection NORTH = CardinalDirection.NORTH;
	private static final CardinalDirection SOUTH = CardinalDirection.SOUTH;
	private static final CardinalDirection EAST = CardinalDirection.EAST;
	private static final CardinalDirection WEST = CardinalDirection.WEST;
	
    /**
     * Convert a degree-minutes-seconds angle to signed floating-point degrees.
     * 
     * @param dmsAngle angle in degrees-minutes-seconds
     * @return degrees in dmsAngle, accurate to within less than 0.0001 degrees, where
     *         angles north & east are positive and south & west are negative
     */
    public static double toDegrees(Angle dmsAngle) {
        double degrees = dmsAngle.degrees();
        double minutes = dmsAngle.minutes() / BASE_60;
        double seconds = dmsAngle.seconds() / BASE_60 / BASE_60;
        double answer = degrees + minutes + seconds;
        if (dmsAngle.direction() == CardinalDirection.SOUTH || dmsAngle.direction() == CardinalDirection.WEST) {
        	answer *= -1.;
        }
        return answer;
    }

    /**
     * Angular displacement from begin to end. Returns an angle with smallest
     * absolute value, for example like this:
     * <br><img src="doc-files/displacement.svg"><br>
     * Not this:
     * <br><img src="doc-files/displacement-invert.svg"><br>
     * 
     * @param begin starting angle, must be a valid latitude or longitude as
     *            defined in {@link Angle}
     * @param end ending angle, must be a valid angle measuring the same
     *            coordinate (latitude or longitude) as begin
     * @return angle with a smallest absolute value, measuring the same
     *         coordinate as the inputs, that sweeps from begin to end
     */
    private static CardinalDirection getDirectionComplement(CardinalDirection x) {
    	CardinalDirection complement = null;
    	switch (x) {
	    	case NORTH: complement = CardinalDirection.SOUTH; break;
	    	case SOUTH: complement = CardinalDirection.NORTH; break;
	    	case WEST: complement = CardinalDirection.EAST; break;
	    	case EAST: complement = CardinalDirection.WEST; break;
    	}
    	return complement;
    }
    
    public static Angle displacement(Angle begin, Angle end) {
    	// Check to see if both directions measure latitude or both measure longitude
        if (begin.direction() != end.direction() && end.direction() != getDirectionComplement(begin.direction()) ) {
        	throw new RuntimeException("Incompatible directions " + begin.direction() + " and " + end.direction());
        }
        
        boolean LATITUDE = false;
        boolean LONGITUDE = false;
        
        if (begin.direction() == NORTH || begin.direction() == SOUTH) LATITUDE = true;
        if (begin.direction() == EAST || begin.direction() == WEST) LONGITUDE = true;
        
        int degreeDiff = 0;
        int minutesDiff = 0;
        int secondsDiff = 0;
        CardinalDirection resultDirection = null;
        
        
       // If begin and end are in the same direction return that direction if begin < end
        if (begin.direction() == end.direction()) {
        	double difference = toDegrees(end) - toDegrees(begin);
        	double absoluteDiff = Math.abs(difference);
        	
        	System.out.println("Begin = " + begin.toString());
        	System.out.println("End = " + end.toString());
        	System.out.println("The differnce is " + difference);
        	
        	degreeDiff = (int) absoluteDiff;
        	System.out.println("Degreediff " + degreeDiff);
        	double minutesRemainder = (absoluteDiff - degreeDiff) * 60.;
        	System.out.println("Minutes remainder " + minutesRemainder);
        	minutesDiff = (int) (minutesRemainder);
        	System.out.println("Minutes Diff " + minutesDiff);
        	double secondsRemainder = (minutesRemainder - minutesDiff) * 60.;
        	System.out.println("Seconds Remainder " + secondsRemainder);
        	secondsDiff = (int) Math.round(secondsRemainder);
        	System.out.println("Seconds Diff " + secondsDiff);
        	
        	if (difference < 0) {
        		resultDirection = getDirectionComplement(begin.direction());
        	} else {
        		resultDirection = begin.direction();
        	}

        } else {
        	// Elif begin and end have different cardinal directions add to find the difference
        	degreeDiff = begin.degrees() + end.degrees();
        	minutesDiff = begin.minutes() + end.minutes();
        	secondsDiff = begin.seconds() + end.seconds();
        	
        	System.out.println("Begin = " + begin.toString());
        	System.out.println("End = " + end.toString());
        	System.out.println("Degrees = " + degreeDiff + "; Minutes = " + minutesDiff + "; Seconds = " + secondsDiff);
        	resultDirection = end.direction();
        	// Account for Base 60 addition
        	if (secondsDiff >= (int) BASE_60) {
        		secondsDiff = secondsDiff - (int) BASE_60;
        		minutesDiff++;
        	}
        	if (minutesDiff >= BASE_60) {
        		minutesDiff = minutesDiff - (int) BASE_60;
        		degreeDiff++;
        	}
        	
        	System.out.println("After afjusting for base 60");
        	System.out.println("Degrees = " + degreeDiff + "; Minutes = " + minutesDiff + "; Seconds = " + secondsDiff);
        	
        	// If measuring Longitude and result > 180 degrees reverse direction
        	if (degreeDiff > 180 || (degreeDiff == 180 && (minutesDiff > 0 || secondsDiff > 0)) && 
        			(resultDirection == EAST || resultDirection == WEST) && LONGITUDE) {
		        		resultDirection = getDirectionComplement(resultDirection);
		        		degreeDiff = 360 - degreeDiff;
		        		if (minutesDiff != 0) {
		        			minutesDiff = (int) BASE_60 - minutesDiff;
		        		}
		        		if (secondsDiff != 0) {
		        			secondsDiff = (int) BASE_60 - secondsDiff;
		        		}
		        		
        	}    
        	System.out.println("After flip if necessary");
        	System.out.println("Degrees = " + degreeDiff + "; Minutes = " + minutesDiff + "; Seconds = " + secondsDiff);
        }
        return new Angle(degreeDiff, minutesDiff, secondsDiff, resultDirection);
    }

}
