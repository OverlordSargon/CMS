package com.zaico.cms.utility;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by nzaitsev on 23.08.2016.
 */
public class ExceptionHandler {

	/**
	 * The Logger.
	 */
	private static final Logger LOG = LogManager.getLogger(ExceptionHandler.class);

	/**
	 * The TECHNICAL_ERROR string constant.
	 */
    private final static String TECHNICAL_ERROR = "Technical error!";

    /**
     * Handle any with ExceptionCMS exception
     * @param e
     * @return error message
     */
    public static String handleException(Exception e) {
	    LOG.error(e);
        if (e instanceof ExceptionCMS) {
	        if (((ExceptionCMS) e).getErrorCode() > 0) {
		        return e.getMessage();
	        } else {
		        return TECHNICAL_ERROR;
	        }
        } else {
	        LOG.error("Unexpected error.");
            return TECHNICAL_ERROR;
        }
    }
}
