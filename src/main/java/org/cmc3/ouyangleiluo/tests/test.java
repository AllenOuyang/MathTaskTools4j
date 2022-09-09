package org.cmc3.ouyangleiluo.tests;

import org.cmc3.ouyangleiluo.dataframe.Matrix;
import org.cmc3.ouyangleiluo.dataframe.MatrixManager;
import org.testng.annotations.Test;

import java.util.Arrays;

public class test {
    @Test
    public void testNdArray() {
        try {
            double[][] matrix = new double[][]{
                    {1, 1/2.0, 1/3.0},
                    {1/2.0, 1, 2/3.0},
                    {1/3.0, 2/3.0, 1}
            };
            Matrix mat = MatrixManager.create(matrix);
            System.out.println(MatrixManager.inverse(mat));
            System.out.println(MatrixManager.norm(mat, "2"));
        } catch (Exception e) {
        }
    }
}
