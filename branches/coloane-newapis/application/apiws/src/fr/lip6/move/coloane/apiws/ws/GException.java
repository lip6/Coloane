/**
 * GException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.3  Built on : Aug 10, 2007 (04:45:47 LKT)
 */
package fr.lip6.move.coloane.apiws.ws;

import fr.lip6.move.coloane.apiws.ws.WrapperStub.GException1;

public class GException extends java.lang.Exception {
    private GException1 faultMessage;

    public GException() {
        super("GException");
    }

    public GException(java.lang.String s) {
        super(s);
    }

    public GException(java.lang.String s, java.lang.Throwable ex) {
        super(s, ex);
    }

    public void setFaultMessage(
        GException1 msg) {
        faultMessage = msg;
    }

    public GException1 getFaultMessage() {
        return faultMessage;
    }
}