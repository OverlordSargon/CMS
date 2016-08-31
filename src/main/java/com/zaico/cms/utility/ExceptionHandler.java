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
                case 1:
                    return e.getMessage();
                case 2:
                    return e.getMessage();
                default:
                    return TECHNICAL_ERROR;
            }
        } else {
            return "Out of CMS "+TECHNICAL_ERROR;
        }
    }
}
