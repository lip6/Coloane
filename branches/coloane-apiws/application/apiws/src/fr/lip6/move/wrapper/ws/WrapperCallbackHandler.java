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

    /**
     * auto generated Axis2 call back method for wanswerDb method
     * override this method for handling normal response from wanswerDb operation
     */
    public void receiveResultwanswerDb(
        fr.lip6.move.wrapper.ws.WrapperStub.WanswerDbResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from wanswerDb operation
     */
    public void receiveErrorwanswerDb(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for wcloseSession method
     * override this method for handling normal response from wcloseSession operation
     */
    public void receiveResultwcloseSession(
        fr.lip6.move.wrapper.ws.WrapperStub.WcloseSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from wcloseSession operation
     */
    public void receiveErrorwcloseSession(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for answerDb method
     * override this method for handling normal response from answerDb operation
     */
    public void receiveResultanswerDb(
        fr.lip6.move.wrapper.ws.WrapperStub.AnswerDbResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from answerDb operation
     */
    public void receiveErroranswerDb(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for wchangeSession method
     * override this method for handling normal response from wchangeSession operation
     */
    public void receiveResultwchangeSession(
        fr.lip6.move.wrapper.ws.WrapperStub.WchangeSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from wchangeSession operation
     */
    public void receiveErrorwchangeSession(java.lang.Exception e) {
    }

    // No methods generated for meps other than in-out

    /**
     * auto generated Axis2 call back method for createSession method
     * override this method for handling normal response from createSession operation
     */
    public void receiveResultcreateSession(
        fr.lip6.move.wrapper.ws.WrapperStub.CreateSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from createSession operation
     */
    public void receiveErrorcreateSession(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for ping method
     * override this method for handling normal response from ping operation
     */
    public void receiveResultping(
        fr.lip6.move.wrapper.ws.WrapperStub.PingResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from ping operation
     */
    public void receiveErrorping(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for disconnectAllUser method
     * override this method for handling normal response from disconnectAllUser operation
     */
    public void receiveResultdisconnectAllUser(
        fr.lip6.move.wrapper.ws.WrapperStub.DisconnectAllUserResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from disconnectAllUser operation
     */
    public void receiveErrordisconnectAllUser(java.lang.Exception e) {
    }

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
     * auto generated Axis2 call back method for closeSession method
     * override this method for handling normal response from closeSession operation
     */
    public void receiveResultcloseSession(
        fr.lip6.move.wrapper.ws.WrapperStub.CloseSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from closeSession operation
     */
    public void receiveErrorcloseSession(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for wdisconnect method
     * override this method for handling normal response from wdisconnect operation
     */
    public void receiveResultwdisconnect(
        fr.lip6.move.wrapper.ws.WrapperStub.WdisconnectResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from wdisconnect operation
     */
    public void receiveErrorwdisconnect(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for changeSession method
     * override this method for handling normal response from changeSession operation
     */
    public void receiveResultchangeSession(
        fr.lip6.move.wrapper.ws.WrapperStub.ChangeSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from changeSession operation
     */
    public void receiveErrorchangeSession(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for wping method
     * override this method for handling normal response from wping operation
     */
    public void receiveResultwping(
        fr.lip6.move.wrapper.ws.WrapperStub.WpingResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from wping operation
     */
    public void receiveErrorwping(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for wcreateSession method
     * override this method for handling normal response from wcreateSession operation
     */
    public void receiveResultwcreateSession(
        fr.lip6.move.wrapper.ws.WrapperStub.WcreateSessionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from wcreateSession operation
     */
    public void receiveErrorwcreateSession(java.lang.Exception e) {
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