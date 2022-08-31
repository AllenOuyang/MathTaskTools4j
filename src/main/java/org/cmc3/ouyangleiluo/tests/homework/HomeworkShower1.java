package org.cmc3.ouyangleiluo.tests.homework;

import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import org.cmc3.ouyangleiluo.Global;
import org.cmc3.ouyangleiluo.utils.*;

import java.io.IOException;

/**
 * HomeworkShower1 is a class which implement all requirements to finish
 * homework1
 *
 * @author      allen ouyang (ouyang leiluo)
 * @version     0.0.1
 */
public class HomeworkShower1 implements HomeworkShower{

    /**
     * run is a launch method implementing the one of interface HomeworkShower,
     * by invoking this method, we can see the result of this homework
     */
    @Override
    public void run() throws PythonExecutionException, IOException {
        Function f = (x) -> 1 + 4/ (Math.pow(x,2) + 1); // f(x) = 1 + 4 / (x^2 + 1)
        Function g = (x) -> Math.pow(x, 3); // g(x) = x^3
        Function h = (x) -> Math.pow(2, -x); // h(x) = 2^(-x)
        Function f1 = (x) -> -8 * x / Math.pow(Math.pow(x, 2) + 1, 2); // f`(x) = -8x / (x^2 + 1)^2
        Function g1 = (x) -> 3 * Math.pow(x, 2); // g`(x) = 3x^2
        Function h1 = (x) -> -Math.log(2) * Math.pow(2, -x); // h`(x) = -2^(-x)ln2

        double intersection_fg = RootUtil.root(f, g, f1, g1, 1, 2, 0.0001);
        double intersection_fh = RootUtil.root(f, h, f1, h1, -2, -1, 0.0001);
        double intersection_gh = RootUtil.root(g, h, g1, h1, -1, 1, 0.0001);
        double integral_f = IntegralUtil.integral(f, Math.min(intersection_fg, intersection_fh),
                Math.max(intersection_fg, intersection_fh), 0.0001);
        double integral_g = IntegralUtil.integral(g, Math.min(intersection_fg, intersection_gh),
                Math.max(intersection_fg, intersection_gh), 0.0001);
        double integral_h = IntegralUtil.integral(h, Math.min(intersection_fh, intersection_gh),
                Math.max(intersection_fh, intersection_gh), 0.0001);
        double area = integral_f - integral_g - integral_h;

        System.out.print(Global.ANSI_BLUE + "The intersection of " + Global.ANSI_RESET +
                Global.ANSI_YELLOW + "f(x)" + Global.ANSI_RESET +
                Global.ANSI_BLUE + " and " + Global.ANSI_RESET +
                Global.ANSI_YELLOW + "g(x)" + Global.ANSI_RESET +
                Global.ANSI_BLUE + " is " + Global.ANSI_RESET);
        System.out.println(Global.ANSI_YELLOW + intersection_fg + Global.ANSI_RESET);

        System.out.print(Global.ANSI_BLUE + "The intersection of " + Global.ANSI_RESET +
                Global.ANSI_YELLOW + "f(x)" + Global.ANSI_RESET +
                Global.ANSI_BLUE + " and " + Global.ANSI_RESET +
                Global.ANSI_YELLOW + "h(x)" + Global.ANSI_RESET +
                Global.ANSI_BLUE + " is " + Global.ANSI_RESET);
        System.out.println(Global.ANSI_YELLOW + intersection_fh + Global.ANSI_RESET);

        System.out.print(Global.ANSI_BLUE + "The intersection of " + Global.ANSI_RESET +
                Global.ANSI_YELLOW + "g(x)" + Global.ANSI_RESET +
                Global.ANSI_BLUE + " and " + Global.ANSI_RESET +
                Global.ANSI_YELLOW + "h(x)" + Global.ANSI_RESET +
                Global.ANSI_BLUE + " is " + Global.ANSI_RESET);
        System.out.println(Global.ANSI_YELLOW + intersection_gh + Global.ANSI_RESET);

        System.out.print(Global.ANSI_BLUE + "The integral of " + Global.ANSI_RESET +
                Global.ANSI_YELLOW + "f(x)" + Global.ANSI_RESET +
                Global.ANSI_BLUE + " is " + Global.ANSI_RESET);
        System.out.println(Global.ANSI_YELLOW + integral_f + Global.ANSI_RESET);

        System.out.print(Global.ANSI_BLUE + "The integral of " + Global.ANSI_RESET +
                Global.ANSI_YELLOW + "g(x)" + Global.ANSI_RESET +
                Global.ANSI_BLUE + " is " + Global.ANSI_RESET);
        System.out.println(Global.ANSI_YELLOW + integral_g + Global.ANSI_RESET);

        System.out.print(Global.ANSI_BLUE + "The integral of " + Global.ANSI_RESET +
                Global.ANSI_YELLOW + "h(x)" + Global.ANSI_RESET +
                Global.ANSI_BLUE + " is " + Global.ANSI_RESET);
        System.out.println(Global.ANSI_YELLOW + integral_h + Global.ANSI_RESET);

        System.out.print(Global.ANSI_BLUE + "The area surrounded by functions " + Global.ANSI_RESET +
                Global.ANSI_YELLOW + "f(x), g(x), h(x)" + Global.ANSI_RESET +
                Global.ANSI_BLUE + " is " + Global.ANSI_RESET);
        System.out.println(Global.ANSI_YELLOW + area + Global.ANSI_RESET);
        PlotUtil.drawFunction(Math.min(Math.min(intersection_fg, intersection_fh), intersection_gh) - 0.5,
                Math.max(Math.max(intersection_fg, intersection_fh), intersection_gh) + 0.5, f, g, h);
    }

}
