package org.cmc3.ouyangleiluo.utils;

import lombok.experimental.UtilityClass;
import org.cmc3.ouyangleiluo.CommonUtils;
import org.cmc3.ouyangleiluo.Global;

@UtilityClass
public class RootUtil {
    public static double root(final Function f, final Function g, final Function f1,
                              final Function g1, double a, double b, final double eps) {
        Function target = (x) -> {return f.getValue(x) - g.getValue(x);};
        Function target1 = (x) -> {return f1.getValue(x) - g1.getValue(x);};
        do {
            try {
                if (target.getValue(a) < 0) {//Условие начальной точки для метода хорд
                    a = a + (b - a) / (target.getValue(a) - target.getValue(b)) * target.getValue(a); //формулы расчета по методу хорд
                    b = b - target.getValue(b) / target1.getValue(b);
                } else if (target.getValue(a) > 0) {//Условие начальной точки для метода касательных
                    a = a - target.getValue(a) / target1.getValue(a); //формулы расчета по методу касательных
                    b = b + (b - a) / (target.getValue(b) - target.getValue(a)) * target.getValue(b);
                } else {
                    return a;
                }
            } catch (Throwable e) {
                Global.LOGGER.error("calculation failed " + CommonUtils.getExceptionStack(e));
                break;
            }
        } while (Math.abs(b - a) > eps);//Построение хорд и касательных продолжается до достижения необходимой точности решения е
        return (a + b) / 2.0;
    }
}
