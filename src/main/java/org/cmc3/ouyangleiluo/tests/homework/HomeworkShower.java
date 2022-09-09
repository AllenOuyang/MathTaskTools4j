package org.cmc3.ouyangleiluo.tests.homework;

import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import org.cmc3.ouyangleiluo.exception.MatrixException;

import java.io.IOException;

public interface HomeworkShower {
    void run() throws PythonExecutionException, IOException, MatrixException;
}
