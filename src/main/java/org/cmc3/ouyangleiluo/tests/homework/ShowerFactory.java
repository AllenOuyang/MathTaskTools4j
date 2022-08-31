package org.cmc3.ouyangleiluo.tests.homework;

import org.cmc3.ouyangleiluo.exception.ShowerBuildException;

/**
 * ShowerFactory is a factory class to produce HomeworkShower objects
 *
 * @author      allen ouyang (ouyang leiluo)
 * @version     0.0.1
 */
public class ShowerFactory {

    /**
     * buildShower is a method that produce a HomeworkShower object corresponding to
     * given id
     *
     * @param homeworkId    id of Homework
     * @return              a needed HomeworkShower object
     */
    public HomeworkShower buildShower(int homeworkId) throws ShowerBuildException {
        switch (homeworkId) {
            case 1 : return new HomeworkShower1();
            default:
                throw new ShowerBuildException("homeworkId not found");
        }
    }
}
