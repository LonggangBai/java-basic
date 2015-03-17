/**
 * Project Name:java-basic
 * File Name:ExeChainExeception.java
 * Package Name:com.easyway.java.basic.execeptions
 * Date:2015-3-17下午1:56:42
 * Copyright (c) 2015, longgangbai@sina.com All Rights Reserved.
 *
 */

package com.easyway.java.basic.execeptions;

import java.io.PrintStream;
import java.io.PrintWriter;


/**
 * ClassName:ExeChainExeception <br/>
 * Function: JDK1.4版本以上中Throwable类支持异常链机制，所谓的异常链就
 * 是把原始异常包装为新的异常类，也就是说在新的异常类中封装了原始异常类，这有助于
 * 查找产生异常的根本原因。 <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-3-17 下午1:56:42 <br/>
 * 
 * @author longgangbai
 * @version
 * @since JDK 1.6
 * @see
 */
public class ExeChainExeception extends Exception {

    /**
     * The cause of the activation exception.
     * 
     * <p>
     * This field predates the general-purpose exception chaining facility. The
     * {@link Throwable#getCause()} method is now the preferred means of
     * obtaining this information.
     * 
     * @serial
     */
    public Throwable detail;

    /** indicate compatibility with the Java 2 SDK v1.2 version of class */
    private static final long serialVersionUID = -4320118837291406071L;


    /**
     * Constructs an <code>ActivationException</code>.
     */
    public ExeChainExeception() {
        initCause(null); // Disallow subsequent initCause
    }


    /**
     * Constructs an <code>ActivationException</code> with the specified detail
     * message.
     * 
     * @param s
     *            the detail message
     */
    public ExeChainExeception(String s) {
        super(s);
        initCause(null); // Disallow subsequent initCause
    }


    /**
     * Constructs an <code>ActivationException</code> with the specified detail
     * message and cause. This constructor sets the {@link #detail} field to the
     * specified <code>Throwable</code>.
     * 
     * @param s
     *            the detail message
     * @param cause
     *            the cause
     */
    public ExeChainExeception(String s, Throwable cause) {
        super(s);
        initCause(null); // Disallow subsequent initCause
        detail = cause;
    }


    public Throwable initCause(Throwable cause) {
        this.detail = cause;
        return this;
    }


    public void printStackTrace() {
        printStackTrace(System.err);
    }


    public void printStackTrace(PrintStream outStream) {
        printStackTrace(new PrintWriter(outStream));
    }


    public void printStackTrace(PrintWriter writer) {
        super.printStackTrace(writer);
        if (this.getCause() != null) {
            getCause().printStackTrace(writer);
        }
        writer.flush();

    }


    /**
     * Returns the detail message, including the message from the cause, if any,
     * of this exception.
     * 
     * @return the detail message
     */
    public String getMessage() {
        if (detail == null)
            return super.getMessage();
        else
            return super.getMessage() + "; nested exception is: \n\t" + detail.toString();
    }


    /**
     * Returns the cause of this exception. This method returns the value of the
     * {@link #detail} field.
     * 
     * @return the cause, which may be <tt>null</tt>.
     * @since 1.4
     */
    public Throwable getCause() {
        return detail;
    }
}
