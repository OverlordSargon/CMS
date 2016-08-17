package com.zaico.cms.utility;

/**
 * Created by nzaitsev on 17.08.2016.
 * @author ZAITNIK
 */
public class MistakeException extends Exception {
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

    /**
     * Empty constructor
     */
    public MistakeException() {};

    /**
     * The Constructor.
     *
     * @param message   The message of the exception.
     */
    public MistakeException(String message) {
        super(message);
        this.message = addOops(message);
    }

    /**
     * The Constructor.
     *
     * @param message   The message of the exception.
     * @param e         The parent exception.
     */
    public MistakeException(String message, Throwable e) {
        super(message, e);
        this.message = addOops(message);
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

    private String addOops(String message) {
        StringBuilder newMessage = new StringBuilder(message);
        if (!message.endsWith(DOT)) {
            newMessage.append(DOT);
        }
        newMessage.append(MISTAKE);
        return newMessage.toString();
    }
}
