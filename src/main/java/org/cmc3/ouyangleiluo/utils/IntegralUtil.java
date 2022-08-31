package org.cmc3.ouyangleiluo.utils;

import lombok.experimental.UtilityClass;

/**
 * IntegralUtil is a utility class that provide some method to
 * calculate integral.
 * <ul>
 * <li> Rectangle Method
 * <ul/>
 *
 * @author      allen ouyang (ouyang leiluo)
 * @version     0.0.1
 */
@UtilityClass
public class IntegralUtil {

    /**
     * integral is a function to calculate integral
     *
     * @param f     function to be integrated
     * @param a     start point of integral segment
     * @param b     end point of integral segment
     * @param eps   required precision
     * @return      integral result
     */
    public double integral(final Function f, final double a, final double b, final double eps) {
        double width = b - a;
        double sumNew = width * (f.getValue(a) + f.getValue(b)) / 2;
        double sumOld = 0;
        for (int n = 1; Math.abs(sumNew - sumOld) >= eps; n *= 2) {
            sumOld = sumNew;
            sumNew = 0;
            for (int i = 0; i  < n; ++i) {
                sumNew += f.getValue(a + width * (i + 0.5) / n);
            }
            sumNew *= width / n;
        }
        return sumNew;
    }
}
