package com.easyway.java.concurrent.practice;

/**
 * StaticUtilities
 *
 * @author Brian Goetz and Tim Peierls
 */
public class LaunderThrowable extends RuntimeException {

    public LaunderThrowable(Throwable cause) {
    	super(cause);
	}

	/**
     * Coerce an unchecked Throwable to a RuntimeException
     * <p/>
     * If the Throwable is an Error, throw it; if it is a
     * RuntimeException return it, otherwise throw IllegalStateException
     */
    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException)
            return (RuntimeException) t;
        else if (t instanceof Error)
            throw (Error) t;
        else
            throw new IllegalStateException("Not unchecked", t);
    }
}
