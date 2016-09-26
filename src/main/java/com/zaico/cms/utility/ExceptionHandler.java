package com.zaico.cms.utility;

import javax.persistence.NoResultException;

/**
 * Created by nzaitsev on 23.08.2016.
 */
public class ExceptionHandler {

    private final static String TECHNICAL_ERROR = "Technical error!";

    /**
     * Handle any with ExceptionCMS exception
     * @param e
     * @return error message
     */
    public static String handleException(Exception e) {
        if (e instanceof ExceptionCMS) {
            switch (((ExceptionCMS) e).getErrorCode()) {
                case 1: case 2: case 3: case 4:
                case 5: case 6: case 7: case 8:
                case 9: case 10: case 11: case 12:
                case 13: case 14: case 15: case 16:
                case 17: case 18: case 19: case 20:
                case 21: case 22: case 23: case 24:
                case 25: case 26: case 27: case 28:
                case 29: case 30: case 31: case 32:
                    return e.getMessage();
                default:
                    return TECHNICAL_ERROR;
            }
        } else {
            return "Out of CMS "+TECHNICAL_ERROR;
        }
    }
}
