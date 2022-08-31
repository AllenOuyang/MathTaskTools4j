package org.cmc3.ouyangleiluo.utils;

import com.github.sh0nk.matplotlib4j.NumpyUtils;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonConfig;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.testng.internal.collections.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * PlotUtil is a utility class offer some wrapped useful functions to help
 * you draw some functions graph
 *
 * @author      allen ouyang (ouyang leiluo)
 * @version     0.0.1
 */
@UtilityClass
public class PlotUtil {
    private static final String pythonBinPath = "/Library/Frameworks/Python.framework/Versions/3.6/bin/python3";

    /**
     * getValues can get a list of function values of arguments X by using f
     *
     * @param f     function used to get valued
     * @param X     Domain
     * @return      value range(List)
     */
    public static List<Double> getValues(Function f, List<Double> X) {
        List<Double> y = new ArrayList<>();
        for (Double x : X) {
            y.add(f.getValue(x));
        }
        return y;
    }

    /**
     * drawFunction is simple method that help you batch drawing functions graph in
     * interval(start, end).
     *
     * @param start     the start point's horizontal axis coordinates
     * @param end       the end point's horizontal axis coordinates
     * @param functions functions you want to batch
     */
    public static void drawFunction(double start, double end, Function @NotNull ... functions)
            throws PythonExecutionException, IOException {
        Plot plot = Plot.create(PythonConfig.pythonBinPathConfig(pythonBinPath));
        for (Function f : functions) {
            List<Double> x = NumpyUtils.linspace(start, end, 100);
            List<Double> y = getValues(f, x);
            plot.plot().add(x, y).label("").linestyle("solid");
        }
        plot.xlabel("x");
        plot.ylabel("y");
        plot.legend();
        plot.show();
    }
}
