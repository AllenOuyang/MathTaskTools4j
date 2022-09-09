package org.cmc3.ouyangleiluo.exception;

public class UnsupportedDimensionException extends Exception {
    public UnsupportedDimensionException() {
        super("array of given dimension not supported");
    }

    public UnsupportedDimensionException(String msg) {
        super(msg);
    }
}
