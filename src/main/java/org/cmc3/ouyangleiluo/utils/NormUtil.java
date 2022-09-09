package org.cmc3.ouyangleiluo.utils;

import lombok.experimental.UtilityClass;
import org.cmc3.ouyangleiluo.exception.UnsupportedDimensionException;

/**
 * NormUtil is a utility class offer some methods to calculate norm of vectors or
 * matrices.
 *
 * @author      allen ouyang (ouyang leiluo)
 * @version     0.0.1
 */
@UtilityClass
public class NormUtil {

    private static int dimensionOf(Object arr) {
        int dimensionCount = 0;
        Class<?> c = arr.getClass();
        while (c.isArray()) {
            c = c.getComponentType();
            dimensionCount++;
        }
        return dimensionCount;
    }
    /**
     * norm is a method to calculate norm of vector or matrix
     *
     * @param nd        the matrix or vector to be calculated
     * @param type      the norm type
     *                  0 - infinite norm
     *                  1 - norm one
     *                  2 - norm two
     * @return          norm of given matrix/vector in given type
     */
    public static double norm(Object nd, int type) throws UnsupportedDimensionException {
        int rank = dimensionOf(nd);
        double norm = 0;
        if (rank > 2) {
            throw new UnsupportedDimensionException(rank + "d array's norm can't be calculated");
        }
        if (type == 0) {
            if (rank == 1) {
                double[] vec = (double[]) nd;
                norm = normInfVec(vec, rank);
            } else {
                double[][] mat = (double[][]) nd;
                norm = normInfMat(mat, rank);
            }
        } else if (type == 1) {
            if (rank == 1) {
                double[] vec = (double[]) nd;
                norm = normOneVec(vec, rank);
            } else {
                double[][] mat = (double[][]) nd;
                norm = normOneMat(mat, rank);
            }
        } else {
            if (rank == 1) {
                double[] vec = (double[]) nd;
                norm = normEVec(vec, rank);
            } else {
                double[][] mat = (double[][]) nd;
                norm = normEMat(mat, rank);
            }
        }
        return norm;
    }

    private static double normInfVec(double[] vec, int rank) {
        double norm = 0;
        for (int i = 0; i < vec.length; ++i) {
            norm = Math.max(Math.abs(vec[i]), norm);
        }
        return norm;
    }

    private static double normInfMat(double[][] nd, int rank) {
        double norm = 0;
        for (int i = 0; i < nd.length; ++i) {
            for (int j = 0; j < nd[0].length; ++j) {
                norm = Math.max(Math.abs(nd[i][j]), norm);
            }
        }
        return norm;
    }

    private static double normOneVec(double[] nd, int rank) {
        double norm = 0;
        for (int i = 0; i < nd.length; ++i) {
            norm += Math.abs(nd[i]);
        }
        return norm;
    }

    private static double normOneMat(double[][] nd, int rank) {
        double norm = 0;
        for (int i = 0; i < nd.length; ++i) {
            for (int j = 0; j < nd[0].length; ++j) {
                norm += Math.abs(nd[i][j]);
            }
        }
        return norm;
    }

    private static double normEVec(double[] nd, int rank) {
        double norm = 0;
        for (int i = 0; i < nd.length; ++i) {
            norm += Math.pow(nd[i], 2);
        }
        return Math.sqrt(norm);
    }

    private static double normEMat(double[][] nd, int rank) {
        double norm = 0;
        for (int i = 0; i < nd.length; ++i) {
            for (int j = 0; j < nd[0].length; ++j) {
                norm += Math.pow(nd[i][j], 2);
            }
        }
        return Math.sqrt(norm);
    }

}
