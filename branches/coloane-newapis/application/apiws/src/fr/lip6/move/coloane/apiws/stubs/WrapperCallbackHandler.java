
/**
 * WrapperCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.3  Built on : Aug 10, 2007 (04:45:47 LKT)
 */

    package fr.lip6.move.coloane.apiws.stubs;

    /**
     *  WrapperCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class WrapperCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public WrapperCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public WrapperCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for answerDb method
            * override this method for handling normal response from answerDb operation
            */
           public void receiveResultanswerDb(
                    fr.lip6.move.coloane.apiws.stubs.WrapperStub.AnswerDbResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from answerDb operation
           */
            public void receiveErroranswerDb(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for closeSession method
            * override this method for handling normal response from closeSession operation
            */
           public void receiveResultcloseSession(
                    fr.lip6.move.coloane.apiws.stubs.WrapperStub.CloseSessionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from closeSession operation
           */
            public void receiveErrorcloseSession(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for createSession method
            * override this method for handling normal response from createSession operation
            */
           public void receiveResultcreateSession(
                    fr.lip6.move.coloane.apiws.stubs.WrapperStub.CreateSessionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from createSession operation
           */
            public void receiveErrorcreateSession(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for executeServiceWithOneObject method
            * override this method for handling normal response from executeServiceWithOneObject operation
            */
           public void receiveResultexecuteServiceWithOneObject(
                    fr.lip6.move.coloane.apiws.stubs.WrapperStub.ExecuteServiceWithOneObjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from executeServiceWithOneObject operation
           */
            public void receiveErrorexecuteServiceWithOneObject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for executeServiceWithOneText method
            * override this method for handling normal response from executeServiceWithOneText operation
            */
           public void receiveResultexecuteServiceWithOneText(
                    fr.lip6.move.coloane.apiws.stubs.WrapperStub.ExecuteServiceWithOneTextResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from executeServiceWithOneText operation
           */
            public void receiveErrorexecuteServiceWithOneText(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for changeSession method
            * override this method for handling normal response from changeSession operation
            */
           public void receiveResultchangeSession(
                    fr.lip6.move.coloane.apiws.stubs.WrapperStub.ChangeSessionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from changeSession operation
           */
            public void receiveErrorchangeSession(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for disconnectAllUser method
            * override this method for handling normal response from disconnectAllUser operation
            */
           public void receiveResultdisconnectAllUser(
                    fr.lip6.move.coloane.apiws.stubs.WrapperStub.DisconnectAllUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from disconnectAllUser operation
           */
            public void receiveErrordisconnectAllUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for ping method
            * override this method for handling normal response from ping operation
            */
           public void receiveResultping(
                    fr.lip6.move.coloane.apiws.stubs.WrapperStub.PingResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from ping operation
           */
            public void receiveErrorping(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for executeServiceWithTexts method
            * override this method for handling normal response from executeServiceWithTexts operation
            */
           public void receiveResultexecuteServiceWithTexts(
                    fr.lip6.move.coloane.apiws.stubs.WrapperStub.ExecuteServiceWithTextsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from executeServiceWithTexts operation
           */
            public void receiveErrorexecuteServiceWithTexts(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for executeService method
            * override this method for handling normal response from executeService operation
            */
           public void receiveResultexecuteService(
                    fr.lip6.move.coloane.apiws.stubs.WrapperStub.ExecuteServiceResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from executeService operation
           */
            public void receiveErrorexecuteService(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for disconnect method
            * override this method for handling normal response from disconnect operation
            */
           public void receiveResultdisconnect(
                    fr.lip6.move.coloane.apiws.stubs.WrapperStub.DisconnectResponse result
                        ) {
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
                    fr.lip6.move.coloane.apiws.stubs.WrapperStub.ConnectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from connect operation
           */
            public void receiveErrorconnect(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for executeServiceWithObjects method
            * override this method for handling normal response from executeServiceWithObjects operation
            */
           public void receiveResultexecuteServiceWithObjects(
                    fr.lip6.move.coloane.apiws.stubs.WrapperStub.ExecuteServiceWithObjectsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from executeServiceWithObjects operation
           */
            public void receiveErrorexecuteServiceWithObjects(java.lang.Exception e) {
            }
                


    }
    