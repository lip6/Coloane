/**
 * GExceptionException0.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.3  Built on : Aug 10, 2007 (04:45:47 LKT)
 */
package fr.lip6.move.wrapper.ws;

public class GExceptionException0 extends java.lang.Exception {
    private fr.lip6.move.wrapper.ws.WrapperStub.GException0 faultMessage;

    public GExceptionException0() {
        super("GExceptionException0");
    }

    public GExceptionException0(java.lang.String s) {
        super(s);
    }

    public GExceptionException0(java.lang.String s, java.lang.Throwable ex) {
        super(s, ex);
    }

    public void setFaultMessage(
        fr.lip6.move.wrapper.ws.WrapperStub.GException0 msg) {
        faultMessage = msg;
    }

    public fr.lip6.move.wrapper.ws.WrapperStub.GException0 getFaultMessage() {
        return faultMessage;
    }
}
