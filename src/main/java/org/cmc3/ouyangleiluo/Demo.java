package org.cmc3.ouyangleiluo;

import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import org.cmc3.ouyangleiluo.exception.MatrixException;
import org.cmc3.ouyangleiluo.exception.ShowerBuildException;
import org.cmc3.ouyangleiluo.tests.homework.HomeworkShower;
import org.cmc3.ouyangleiluo.tests.homework.ShowerFactory;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Demo is a simple class for demonstrating ouyangleiluo's homework,
 * it contains some method under annotation <code>@Test</code> which
 * can show the result of corresponding homework result when invoking
 *
 * @author      allen ouyang (ouyang leiluo)
 * @version     0.0.1
 */
public class Demo {
    private final ShowerFactory showerFactory = new ShowerFactory();
    /**
     * Homework1 is a method for showing the first homework
     */
    @Test
    private void HomeWork1() {
        try {
            HomeworkShower homeworkShower = showerFactory.buildShower(1);
            homeworkShower.run();
        } catch (ShowerBuildException | PythonExecutionException | IOException | MatrixException e) {
            e.printStackTrace();
            Global.LOGGER.error(CommonUtils.getExceptionStack(e));
        }
    }
    /**
     * Homework2 is a method for showing the first homework
     */
    @Test
    private void HomeWork2() {
        try {
            HomeworkShower homeworkShower = showerFactory.buildShower(2);
            homeworkShower.run();
        } catch (ShowerBuildException | PythonExecutionException | IOException | MatrixException e) {
            e.printStackTrace();
            Global.LOGGER.error(CommonUtils.getExceptionStack(e));
        }
    }

}
