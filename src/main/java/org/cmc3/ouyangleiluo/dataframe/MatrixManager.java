package org.cmc3.ouyangleiluo.dataframe;


import org.cmc3.ouyangleiluo.exception.MatrixException;

public class MatrixManager {

    public static Matrix zeros(int row, int column) throws MatrixException {
        if (!(row > 0 && column > 0)) {
            throw new MatrixException("row or column can not be 0 or negative");
        }
        return new Matrix(new double[row][column]);
    }

    public static Matrix create(double[][] mat) throws MatrixException {
        if (mat == null) {
            throw new MatrixException("mat can not be null");
        }
        return new Matrix(mat);
    }

    public static Matrix ones(int row, int column) throws MatrixException {
        if (!(row > 0 && column > 0)) {
            throw new MatrixException("row or column can not be 0 or negative");
        }
        double[][] mat = new double[row][column];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < column; ++j) {
                mat[i][j] = 1;
            }
        }
        return new Matrix(mat);
    }

    public static Matrix eyes(int size) throws MatrixException {
        if (!(size > 0)) {
            throw new MatrixException("row or column can not be 0 or negative");
        }
        double[][] mat = new double[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (i == j) {
                    mat[i][j] = 1;
                }
            }
        }
        return new Matrix(mat);
    }

    public static double multiply(Matrix A, Matrix B) {
        return 0;
    }

    public static Matrix multiply(Matrix A, double b) throws MatrixException {
        int row = A.shape()[0];
        int col = A.shape()[1];
        double[][] res = new double[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                res[i][j] = A.get(i, j) * b;
            }
        }
        return create(res);
    }

    public static Matrix dot(Matrix A, Matrix B) throws MatrixException {
        int rowA = A.shape()[0];
        int rowB = B.shape()[0];
        int colA = A.shape()[1];
        int colB = B.shape()[1];
        if (colA != rowB) {
            throw new MatrixException("can not dot matrices, please check their shape");
        }
        double[][] res = new double[rowA][colB];
        for (int i = 0; i < rowA; i++)
        {
            for (int j = 0; j < colB; j++)
            {
                res[i][j] = 0;
                for (int k = 0; k < rowB; k++) {
                    res[i][j] += res[i][k] * res[k][j];
                }
                if (res[i][j] < Math.pow(10, -10)) {
                    res[i][j] = 0;
                }
            }
        }

        return create(res);
    }

    public static void LUP_Decomposition(Matrix A, Matrix L, Matrix U, int[] P) throws MatrixException {
        int n = A.shape()[0];
        int row = 0;
        for (int i = 0; i < n; i++) {
            P[i] = i;
        }
        for (int i = 0; i < n-1; i++){
            double p = 0d;
            for(int j = i; j < n; j++) {
                if(Math.abs(A.get(j, i)) > p) {
                    p = Math.abs(A.get(j, i));
                    row = j;
                }
            }
            if (p == 0) {
                throw new MatrixException("singular matrix");
            }
            //exchange P[i] with P[row]
            int tmp = P[i];
            P[i] = P[row];
            P[row] = tmp;

            double tmp2 = 0f;
            for (int j = 0; j < n; j++) {
                //exchange A[i][j] with A[row][j]
                tmp2 = A.get(i, j);
                A.set(i, j, A.get(row, j));
                A.set(row, j, tmp2);
            }
            //下面与LU分解一样了
            double u = A.get(i, i);
            double l = 0d;
            for (int j = i+1; j < n; j++) {
                l = A.get(j, i) / u;
                A.set(j, i, l);
                //update A'
                for(int k = i+1; k < n; k++) {
                    A.set(j, k, A.get(j, k) - A.get(i, k) * l);
                }
            }
        }
        //make L and U
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i; j++) {
                if (i != j) {
                    L.set(i, j, A.get(i, j));
                }
                else {
                    L.set(i, j, 1);
                }
            }
            for (int k = i; k < n; ++k) {
                U.set(i, k, A.get(i, k));
            }
        }
    }

    public static Matrix LUP_Solve(Matrix L, Matrix U, int[]P, Matrix b) throws MatrixException {
        int n = L.shape()[0];
        MatrixManager mm = new MatrixManager();
        Matrix x = mm.zeros(1, n);
        Matrix y = mm.zeros(1, n);

        //正向替换
        for (int i = 0; i < n; ++i) {
            y.set(0, i, b.get(0, P[i])); // y[i] = b[P[i]];
            for (int j = 0; j < i; ++j) {
                y.set(0, i, y.get(0, i) - L.get(i, j) * y.get(0, j)); //y[i] = y[i] - L[i][j]*y[j];
            }
        }
        //反向替换
        for (int i = n - 1; i >= 0; --i) {
            x.set(0, i, y.get(0, i)); //x[i] = y[i];
            for (int j = n - 1; j > i; --j) {
                x.set(0, i, x.get(0, i) - U.get(i, j) * x.get(0, j)); // x[i] = x[i] - U[i][j]*x[j];
            }
            x.set(0, i, x.get(0, i) / U.get(i, i)); // x[i] /= U[i][i];
        }

        return x;
    }

    public static Matrix transposition(Matrix A){
        int n = A.shape()[0];
        double tmp = 0d;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                tmp = A.get(i, j);
                A.set(i, j, A.get(j, i)); //A[i][j] = A[j][i];
                A.set(j, i, tmp); // A[j][i] = tmp;
            }
        }
        return A;
    }

    public static Matrix inverse(Matrix A) throws MatrixException {
        int n = A.shape()[0];
        MatrixManager mm = new MatrixManager();
        Matrix inv = mm.zeros(n, n);
        Matrix B = mm.zeros(n, n);
        //init identity matrix
        for (int i = 0; i < n; ++i){
            B.set(i, i, 1); // B[i][i] = 1;
        }
        //each column of the inverse matrix
        for (int i = 0; i < n; ++i) {
            Matrix L = mm.zeros(n, n);
            Matrix U = mm.zeros(n, n);
            int[] P = new int[n];
            Matrix copyOfA = new Matrix(A);
            LUP_Decomposition(copyOfA,L,U,P);
            inv.setRow(i, LUP_Solve(L, U, P, B.getRow(i)));
        }
        inv = transposition(inv);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (Math.abs(inv.get(i, j)) < Math.pow(10, -10)) {
                    inv.set(i, j, 0);
                }
            }
        }
        return inv;
    }

    public static double norm(Matrix A, String setting) {
        double norm = 0d;
        // if it is vector
        if (A.shape()[0] == 1) {
            switch (setting) {
                case "1": {
                    norm = normOneVec(A);
                    break;
                }
                case "2": {
                    norm = normEVec(A);
                    break;
                }
                case "inf": {
                    norm = normInfVec(A);
                    break;
                }
            }
        } else {
            switch (setting) {
                case "1": {
                    norm = normOneMat(A);
                    break;
                }
                case "2": {
                    norm = normEMat(A);
                    break;
                }
                case "inf": {
                    norm = normInfMat(A);
                    break;
                }
            }
        }
        return norm;
    }

    private static double normInfVec(Matrix vec) {
        double norm = 0d;
        int length = vec.shape()[1];
        for (int i = 0; i < length; ++i) {
            norm = Math.max(Math.abs(vec.get(0, i)), norm);
        }
        return norm;
    }

    private static double normInfMat(Matrix mat) {
        double norm = 0d;
        int row = mat.shape()[0];
        int col = mat.shape()[1];
        for (int i = 0; i < row; ++i) {
            double sumRow = 0d;
            for (int j = 0; j < col; ++j) {
                sumRow += Math.abs(mat.get(i, j));
            }
            norm = Math.max(norm, sumRow);
        }
        return norm;
    }

    private static double normOneVec(Matrix vec) {
        double norm = 0d;
        int length = vec.shape()[1];
        for (int i = 0; i < length; ++i) {
            norm += Math.abs(vec.get(0, i));
        }
        return norm;
    }

    private static double normOneMat(Matrix mat) {
        double norm = 0d;
        int row = mat.shape()[0];
        int col = mat.shape()[1];
        for (int i = 0; i < col; ++i) {
            double sumCol = 0d;
            for (int j = 0; j < row; ++j) {
                sumCol += Math.abs(mat.get(j, i));
            }
            norm = Math.max(norm, sumCol);
        }
        return norm;
    }

    private static double normEVec(Matrix vec) {
        double norm = 0d;
        int length = vec.shape()[1];
        for (int i = 0; i < length; ++i) {
            norm += Math.pow(vec.get(0, i), 2);
        }
        return Math.sqrt(norm);
    }

    private static double normEMat(Matrix mat) {
        double norm = 0d;
        int row = mat.shape()[0];
        int col = mat.shape()[1];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                norm += Math.pow(mat.get(i, j), 2);
            }
        }
        return Math.sqrt(norm);
    }
}
