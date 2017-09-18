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

    /**
     * Convert a degree-minutes-seconds angle to signed floating-point degrees.
     * 
     * @param dmsAngle angle in degrees-minutes-seconds
     * @return degrees in dmsAngle, accurate to within less than 0.0001 degrees, where
     *         angles north & east are positive and south & west are negative
     */
    public static double toDegrees(Angle dmsAngle) {
        throw new RuntimeException("not implemented");
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
    public static Angle displacement(Angle begin, Angle end) {
        throw new RuntimeException("not implemented");
    }

}
