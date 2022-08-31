package org.cmc3.ouyangleiluo.exception;

public class ShowerBuildException extends Exception {
    public ShowerBuildException() {
        super("build shower failed");
    }

    public ShowerBuildException(String msg) {
        super(msg);
    }
}
