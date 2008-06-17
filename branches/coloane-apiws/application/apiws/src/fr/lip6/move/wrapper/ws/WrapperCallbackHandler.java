/**
 * WrapperCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.3  Built on : Aug 10, 2007 (04:45:47 LKT)
 */
package fr.lip6.move.wrapper.ws;


/**
 *  WrapperCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class WrapperCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public WrapperCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public WrapperCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    // No methods generated for meps other than in-out

    /**
     * auto generated Axis2 call back method for disconnect method
     * override this method for handling normal response from disconnect operation
     */
    public void receiveResultdisconnect(
        fr.lip6.move.wrapper.ws.WrapperStub.DisconnectResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from disconnect operation
     */
    public void receiveErrordisconnect(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for connect method
     * override this method for handling normal response from connect operation
     */
    public void receiveResultconnect(
        fr.lip6.move.wrapper.ws.WrapperStub.ConnectResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from connect operation
     */
    public void receiveErrorconnect(java.lang.Exception e) {
    }
}
