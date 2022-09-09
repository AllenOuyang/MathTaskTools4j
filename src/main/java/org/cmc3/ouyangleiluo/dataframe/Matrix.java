package org.cmc3.ouyangleiluo.dataframe;

import org.cmc3.ouyangleiluo.exception.MatrixException;

import java.util.Arrays;


public class Matrix {
    private double[][] data;

    public Matrix(double[][] mat) {
        this.data = mat;
    }

    public Matrix(Matrix A) {
        int row = A.shape()[0];
        int col = A.shape()[1];
        this.data = new double[row][col];
        for (int i = 0; i < row; ++i) {
            System.arraycopy(A.data[i], 0, this.data[i], 0, col);
        }
    }

    public boolean isSquare() {
        return this.shape()[0] == this.shape()[1];
    }

    public boolean isRegular() throws MatrixException {
        if (!isSquare()) {
            throw new MatrixException("can not judge if a non-square matrix is a regular matrix");
        }
        int rank = this.shape()[0];
        boolean isreg = true;
        for (int i = rank; i > 0; --i) {
            Matrix sub = subMatrix(rank);
            double det = sub.det();
            if (det < 0) {
                isreg = false;
            }
        }
        return isreg;
    }

    public int[] shape() {
        return new int[]{this.data.length, this.data[0].length};
    }

    public double det() throws MatrixException {
        if (!isSquare()) {
            throw new MatrixException("can not calculate det of non-square matrix");
        }
        int rank = this.shape()[0];
        if (rank == 1) {
            return this.data[0][0];
        }
        return this.det(this.shape()[0], this.data);
    }

    private double det(int rank, double[][] a) {
        if (rank > 2)
        {
            double result = 0;
            for (int k = 0; k < rank; k++)
            {
                double[][] newArr = new double[rank-1][rank-1];
                int index = 0;
                for (int i = 0; i < rank; ++i) {
                    if (k != i) {
                        System.arraycopy(a[i], 1, newArr[index], 0, rank - 1);
                        index++;
                    }
                }
                result += Math.pow(-1, (k+1+1)) * a[k][0] * det(rank-1, newArr);
            }
            return result;
        }
        else {
            return a[0][0] * a[1][1] - a[0][1] * a[1][0];
        }
    }

    public double get(int i, int j) {
        return this.data[i][j];
    }

    public void set(int i, int j, double val) {
        this.data[i][j] = val;
    }

    public Matrix subMatrix(int rank) throws MatrixException {
        if (!isSquare()) {
            throw new MatrixException("can not get sub matrix of non-square matrix");
        }
        double[][] mat = new double[rank][rank];
        for (int i = 0; i < rank; ++i) {
            System.arraycopy(this.data[i], 0, mat[i], 0, rank);
        }
        return new Matrix(mat);
    }

    public Matrix getRow(int index) {
        int col = this.shape()[1];
        double[][] vec = new double[1][col];
        vec[0] = this.data[index];
        return new Matrix(vec);
    }

    public void setRow(int index, Matrix vec) {
        double[] vvec = vec.data[0];
        this.data[index] = vvec;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(this.data).replaceAll("],", "],\n");
    }

//    public void copyfrom(Matrix from) throws MatrixException {
//        if (this.shape()[0] == from.shape()[0] && this.shape()[1] == from.shape()[1]) {
//            System.arraycopy(0, from.data, 0, data, );
//        } else {
//            throw new MatrixException("can not copy between two matrix with different shape");
//        }
//    }
}
