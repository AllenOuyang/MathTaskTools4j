package org.cmc3.ouyangleiluo;

import lombok.experimental.UtilityClass;

/**
 * CommonUtils is a utility class that provide some useful method
 * to assist my work
 *
 * @author      allen ouyang (ouyang leiluo)
 * @version     0.0.1
 */
@UtilityClass
public class CommonUtils {
    /**
     * getExceptionStack is a method that allow us to get exception stack
     * info as string
     *
     * @param e     exception to be processed
     * @return      a string representing exception stack info
     */
    public static String getExceptionStack(Throwable e) {
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        StringBuilder result = new StringBuilder(e.toString() + "\n");
        for (int index = stackTraceElements.length - 1; index >= 0; --index) {
            result.append("at [").append(stackTraceElements[index].getClassName())
                    .append(",");
            result.append(stackTraceElements[index].getFileName()).append(",");
            result.append(stackTraceElements[index].getMethodName()).append(",");
            result.append(stackTraceElements[index].getLineNumber()).append("]\n");
        }
        return result.toString();
    }
}
