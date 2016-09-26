package com.zaico.cms.utility;

/**
 * Created by nzaitsev on 17.08.2016.
 * @author ZAITNIK
 */
public class ExceptionCMS extends Exception {
    /**
     *  Exception message
     */
    private String message;

    /**
     * The "." constant
     */
    private final String DOT = ".";

    /**
     * The "Mistake: " constant
     */
    private final String MISTAKE = "Mistake: ";

    /**
     * The parent exception.
     */
    Throwable parentException;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     *
     */
    private int errorCode;

    /**
     * Empty constructor
     */
    public ExceptionCMS() {};

    /**
     * The Constructor.
     *
     * @param message   The message of the exception.
     */
    public ExceptionCMS(String message, int errorCode) {
        this.message = addCMSException(message);
        this.errorCode = errorCode;
    }

    /**
     * The Constructor.
     *
     * @param message   The message of the exception.
     * @param e         The parent exception.
     */
    public ExceptionCMS(String message, Throwable e) {
        super(message, e);
        this.message = addCMSException(message);
        this.parentException = e;
    }

    /**
     * Get exception message
     * @return  The message.
     */
    @Override
    public String getMessage() {
        return message;
    }

    private String addCMSException(String message) {
        StringBuilder newMessage = new StringBuilder(message);
        if (!message.endsWith(DOT)) {
            newMessage.append(DOT);
        }
//        newMessage.append(MISTAKE);
        return newMessage.toString();
    }
}
