package org.cmc3.ouyangleiluo.exception;

public class MatrixException extends Exception {
    public MatrixException() {
        super("matrix process failed");
    }

    public MatrixException(String msg) {
        super(msg);
    }
}
