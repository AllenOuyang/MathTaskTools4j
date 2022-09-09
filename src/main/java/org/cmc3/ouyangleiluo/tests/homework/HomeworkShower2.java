package org.cmc3.ouyangleiluo.tests.homework;

import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import org.cmc3.ouyangleiluo.Global;
import org.cmc3.ouyangleiluo.dataframe.Matrix;
import org.cmc3.ouyangleiluo.dataframe.MatrixManager;
import org.cmc3.ouyangleiluo.exception.MatrixException;
import org.cmc3.ouyangleiluo.utils.*;

import java.io.IOException;

/**
 * HomeworkShower1 is a class which implement all requirements to finish
 * homework2
 *
 * @author      allen ouyang (ouyang leiluo)
 * @version     0.0.1
 */
public class HomeworkShower2 implements HomeworkShower{

    public Matrix makeLehmer(int size) throws MatrixException {
        double[][] lehmer = new double[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                lehmer[i][j] = Math.min(i*1.0 + 1, j + 1) / Math.max(i + 1, j + 1);
            }
        }
        return MatrixManager.create(lehmer);
    }

    /**
     * run is a launch method implementing the one of interface HomeworkShower,
     * by invoking this method, we can see the result of this homework
     */
    @Override
    public void run() throws MatrixException {
        // task 4 На основе экспериментов со случайными матрицами при различных 𝑛 убедитесь в выполнении неравенств
        double[][] mat1 = new double[][]{
                {2, 0, 0},
                {1, 0, 5},
                {0, 1, 2}
        };
        double[][] mat2 = new double[][]{
                {2, 0, 0},
                {0, 1, 1},
                {2, 4, 3}
        };
        double[][] mat3 = new double[][]{
                {1, 8, 8, 4},
                {8, 7, 8, 9},
                {8, 8, 2, 0},
                {3, 1, 9, 6}
        };
        Matrix matrix1 = MatrixManager.create(mat1);
        Matrix matrix2 = MatrixManager.create(mat2);
        Matrix matrix3 = MatrixManager.create(mat3);
        System.out.println(Global.ANSI_RED + "-------------------Task 4 --------------------" + Global.ANSI_RESET);
        if (satisfyInequation(matrix1)) {
            System.out.println(Global.ANSI_YELLOW + matrix1 + Global.ANSI_RESET);
            System.out.println(Global.ANSI_BLUE + "Satisfy the inequation" + Global.ANSI_BLUE);
        } else {
            System.out.println(Global.ANSI_YELLOW + matrix1 + Global.ANSI_RESET);
            System.out.println(Global.ANSI_BLUE + "Unsatisfy the inequation" + Global.ANSI_BLUE);
        }
        if (satisfyInequation(matrix2)) {
            System.out.println(Global.ANSI_YELLOW + matrix2 + Global.ANSI_RESET);
            System.out.println(Global.ANSI_BLUE + "Satisfy the inequation" + Global.ANSI_BLUE);
        } else {
            System.out.println(Global.ANSI_YELLOW + matrix2 + Global.ANSI_RESET);
            System.out.println(Global.ANSI_BLUE + "Unsatisfy the inequation" + Global.ANSI_BLUE);
        }
        if (satisfyInequation(matrix3)) {
            System.out.println(Global.ANSI_YELLOW + matrix3 + Global.ANSI_RESET);
            System.out.println(Global.ANSI_BLUE + "Satisfy the inequation" + Global.ANSI_BLUE);
        } else {
            System.out.println(Global.ANSI_YELLOW + matrix3 + Global.ANSI_RESET);
            System.out.println(Global.ANSI_BLUE + "Unsatisfy the inequation" + Global.ANSI_BLUE);
        }
        System.out.println(Global.ANSI_RED + "-------------------Task 7 --------------------" + Global.ANSI_RESET);
        for (int i = 3; i < 6; ++i) {
            Matrix lehmer = makeLehmer(i);
            System.out.println(Global.ANSI_BLUE + "The orignal lehmer matrix: " + Global.ANSI_RESET);
            System.out.println(Global.ANSI_YELLOW + lehmer + Global.ANSI_RESET);
            System.out.println(Global.ANSI_BLUE + "Its inverse matrix: " + Global.ANSI_RESET);
            System.out.println(Global.ANSI_YELLOW + MatrixManager.inverse(lehmer) + Global.ANSI_RESET);
        }
    }

    public boolean satisfyInequation(Matrix matrix) {
        int n = matrix.shape()[0];
        return 1 / Math.sqrt(n) * MatrixManager.norm(matrix, "1") <= MatrixManager.norm(matrix, "2") &&
                MatrixManager.norm(matrix, "2") <= Math.sqrt(n) * MatrixManager.norm(matrix, "1") &&
                1 / Math.sqrt(n) * MatrixManager.norm(matrix, "inf") <= MatrixManager.norm(matrix, "2") &&
                MatrixManager.norm(matrix, "2") <= Math.sqrt(n) * MatrixManager.norm(matrix, "inf");
    }

}
