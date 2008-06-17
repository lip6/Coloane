/**
 * WrapperStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.3  Built on : Aug 10, 2007 (04:45:47 LKT)
 */
package fr.lip6.move.wrapper.ws;


/*
 *  WrapperStub java implementation
 */
public class WrapperStub extends org.apache.axis2.client.Stub {
    protected org.apache.axis2.description.AxisOperation[] _operations;

    //hashmaps to keep the fault mapping
    private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
    private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
    private java.util.HashMap faultMessageMap = new java.util.HashMap();
    private javax.xml.namespace.QName[] opNameArray = null;

    /**
     *Constructor that takes in a configContext
     */
    public WrapperStub(
        org.apache.axis2.context.ConfigurationContext configurationContext,
        java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(configurationContext, targetEndpoint, false);
    }

    /**
     * Constructor that takes in a configContext  and useseperate listner
     */
    public WrapperStub(
        org.apache.axis2.context.ConfigurationContext configurationContext,
        java.lang.String targetEndpoint, boolean useSeparateListener)
        throws org.apache.axis2.AxisFault {
        //To populate AxisService
        populateAxisService();
        populateFaults();

        _serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext,
                _service);

        configurationContext = _serviceClient.getServiceContext()
                                             .getConfigurationContext();

        _serviceClient.getOptions()
                      .setTo(new org.apache.axis2.addressing.EndpointReference(
                targetEndpoint));
        _serviceClient.getOptions().setUseSeparateListener(useSeparateListener);

        //Set the soap version
        _serviceClient.getOptions()
                      .setSoapVersionURI(org.apache.axiom.soap.SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);
    }

    /**
     * Default Constructor
     */
    public WrapperStub(
        org.apache.axis2.context.ConfigurationContext configurationContext)
        throws org.apache.axis2.AxisFault {
        this(configurationContext,
            "http://localhost:8080/axis2/services/Wrapper");
    }

    /**
     * Default Constructor
     */
    public WrapperStub() throws org.apache.axis2.AxisFault {
        this("http://localhost:8080/axis2/services/Wrapper");
    }

    /**
     * Constructor taking the target endpoint
     */
    public WrapperStub(java.lang.String targetEndpoint)
        throws org.apache.axis2.AxisFault {
        this(null, targetEndpoint);
    }

    private void populateAxisService() throws org.apache.axis2.AxisFault {
        //creating the Service with a unique name
        _service = new org.apache.axis2.description.AxisService("Wrapper" +
                this.hashCode());

        //creating the operations
        org.apache.axis2.description.AxisOperation __operation;

        _operations = new org.apache.axis2.description.AxisOperation[3];

        __operation = new org.apache.axis2.description.OutOnlyAxisOperation();

        __operation.setName(new javax.xml.namespace.QName(
                "http://ws.wrapper.move.lip6.fr", "main"));
        _service.addOperation(__operation);

        _operations[0] = __operation;

        __operation = new org.apache.axis2.description.OutInAxisOperation();

        __operation.setName(new javax.xml.namespace.QName(
                "http://ws.wrapper.move.lip6.fr", "disconnect"));
        _service.addOperation(__operation);

        _operations[1] = __operation;

        __operation = new org.apache.axis2.description.OutInAxisOperation();

        __operation.setName(new javax.xml.namespace.QName(
                "http://ws.wrapper.move.lip6.fr", "connect"));
        _service.addOperation(__operation);

        _operations[2] = __operation;
    }

    //populates the faults
    private void populateFaults() {
        faultExceptionNameMap.put(new javax.xml.namespace.QName(
                "http://ws.wrapper.move.lip6.fr", "GException"),
            "fr.lip6.move.wrapper.ws.GExceptionException0");
        faultExceptionClassNameMap.put(new javax.xml.namespace.QName(
                "http://ws.wrapper.move.lip6.fr", "GException"),
            "fr.lip6.move.wrapper.ws.GExceptionException0");
        faultMessageMap.put(new javax.xml.namespace.QName(
                "http://ws.wrapper.move.lip6.fr", "GException"),
            "fr.lip6.move.wrapper.ws.WrapperStub$GException0");

        faultExceptionNameMap.put(new javax.xml.namespace.QName(
                "http://ws.wrapper.move.lip6.fr", "GException"),
            "fr.lip6.move.wrapper.ws.GExceptionException0");
        faultExceptionClassNameMap.put(new javax.xml.namespace.QName(
                "http://ws.wrapper.move.lip6.fr", "GException"),
            "fr.lip6.move.wrapper.ws.GExceptionException0");
        faultMessageMap.put(new javax.xml.namespace.QName(
                "http://ws.wrapper.move.lip6.fr", "GException"),
            "fr.lip6.move.wrapper.ws.WrapperStub$GException0");
    }

    public void main(fr.lip6.move.wrapper.ws.WrapperStub.Main main0)
        throws java.rmi.RemoteException {
        org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
        _operationClient.getOptions().setAction("urn:main");
        _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

        addPropertyToOperationClient(_operationClient,
            org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
            "&");

        org.apache.axiom.soap.SOAPEnvelope env = null;
        org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

        //Style is Doc.
        env = toEnvelope(getFactory(_operationClient.getOptions()
                                                    .getSoapVersionURI()),
                main0,
                optimizeContent(
                    new javax.xml.namespace.QName(
                        "http://ws.wrapper.move.lip6.fr", "main")));

        //adding SOAP soap_headers
        _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        _operationClient.execute(true);

        return;
    }

    /**
     * Auto generated method signature
     * @see fr.lip6.move.wrapper.ws.Wrapper#disconnect
     * @param disconnect1
     */
    public fr.lip6.move.wrapper.ws.WrapperStub.DisconnectResponse disconnect(
        fr.lip6.move.wrapper.ws.WrapperStub.Disconnect disconnect1)
        throws java.rmi.RemoteException,
            fr.lip6.move.wrapper.ws.GExceptionException0 {
        try {
            org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[1].getName());
            _operationClient.getOptions().setAction("urn:disconnect");
            _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

            addPropertyToOperationClient(_operationClient,
                org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
                "&");

            // create a message context
            org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(_operationClient.getOptions()
                                                        .getSoapVersionURI()),
                    disconnect1,
                    optimizeContent(
                        new javax.xml.namespace.QName(
                            "http://ws.wrapper.move.lip6.fr", "disconnect")));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            _messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            _operationClient.addMessageContext(_messageContext);

            //execute the operation client
            _operationClient.execute(true);

            org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

            java.lang.Object object = fromOM(_returnEnv.getBody()
                                                       .getFirstElement(),
                    fr.lip6.move.wrapper.ws.WrapperStub.DisconnectResponse.class,
                    getEnvelopeNamespaces(_returnEnv));
            _messageContext.getTransportOut().getSender()
                           .cleanup(_messageContext);

            return (fr.lip6.move.wrapper.ws.WrapperStub.DisconnectResponse) object;
        } catch (org.apache.axis2.AxisFault f) {
            org.apache.axiom.om.OMElement faultElt = f.getDetail();

            if (faultElt != null) {
                if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
                    //make the fault by reflection
                    try {
                        java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(faultElt.getQName());
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.Exception ex = (java.lang.Exception) exceptionClass.newInstance();

                        //message class
                        java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,
                                messageClass, null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                new java.lang.Class[] { messageClass });
                        m.invoke(ex, new java.lang.Object[] { messageObject });

                        if (ex instanceof fr.lip6.move.wrapper.ws.GExceptionException0) {
                            throw (fr.lip6.move.wrapper.ws.GExceptionException0) ex;
                        }

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    } catch (java.lang.ClassCastException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                } else {
                    throw f;
                }
            } else {
                throw f;
            }
        }
    }

    /**
     * Auto generated method signature for Asynchronous Invocations
     * @see fr.lip6.move.wrapper.ws.Wrapper#startdisconnect
     * @param disconnect1
     */
    public void startdisconnect(
        fr.lip6.move.wrapper.ws.WrapperStub.Disconnect disconnect1,
        final fr.lip6.move.wrapper.ws.WrapperCallbackHandler callback)
        throws java.rmi.RemoteException {
        org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[1].getName());
        _operationClient.getOptions().setAction("urn:disconnect");
        _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

        addPropertyToOperationClient(_operationClient,
            org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
            "&");

        // create SOAP envelope with that payload
        org.apache.axiom.soap.SOAPEnvelope env = null;
        org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

        //Style is Doc.
        env = toEnvelope(getFactory(_operationClient.getOptions()
                                                    .getSoapVersionURI()),
                disconnect1,
                optimizeContent(
                    new javax.xml.namespace.QName(
                        "http://ws.wrapper.move.lip6.fr", "disconnect")));

        // adding SOAP soap_headers
        _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);

        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
                public void onMessage(
                    org.apache.axis2.context.MessageContext resultContext) {
                    try {
                        org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

                        java.lang.Object object = fromOM(resultEnv.getBody()
                                                                  .getFirstElement(),
                                fr.lip6.move.wrapper.ws.WrapperStub.DisconnectResponse.class,
                                getEnvelopeNamespaces(resultEnv));
                        callback.receiveResultdisconnect((fr.lip6.move.wrapper.ws.WrapperStub.DisconnectResponse) object);
                    } catch (org.apache.axis2.AxisFault e) {
                        callback.receiveErrordisconnect(e);
                    }
                }

                public void onError(java.lang.Exception error) {
                    if (error instanceof org.apache.axis2.AxisFault) {
                        org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
                        org.apache.axiom.om.OMElement faultElt = f.getDetail();

                        if (faultElt != null) {
                            if (faultExceptionNameMap.containsKey(
                                        faultElt.getQName())) {
                                //make the fault by reflection
                                try {
                                    java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(faultElt.getQName());
                                    java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                                    java.lang.Exception ex = (java.lang.Exception) exceptionClass.newInstance();

                                    //message class
                                    java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
                                    java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                                    java.lang.Object messageObject = fromOM(faultElt,
                                            messageClass, null);
                                    java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                            new java.lang.Class[] { messageClass });
                                    m.invoke(ex,
                                        new java.lang.Object[] { messageObject });

                                    if (ex instanceof fr.lip6.move.wrapper.ws.GExceptionException0) {
                                        callback.receiveErrordisconnect((fr.lip6.move.wrapper.ws.GExceptionException0) ex);

                                        return;
                                    }

                                    callback.receiveErrordisconnect(new java.rmi.RemoteException(
                                            ex.getMessage(), ex));
                                } catch (java.lang.ClassCastException e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrordisconnect(f);
                                } catch (java.lang.ClassNotFoundException e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrordisconnect(f);
                                } catch (java.lang.NoSuchMethodException e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrordisconnect(f);
                                } catch (java.lang.reflect.InvocationTargetException e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrordisconnect(f);
                                } catch (java.lang.IllegalAccessException e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrordisconnect(f);
                                } catch (java.lang.InstantiationException e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrordisconnect(f);
                                } catch (org.apache.axis2.AxisFault e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrordisconnect(f);
                                }
                            } else {
                                callback.receiveErrordisconnect(f);
                            }
                        } else {
                            callback.receiveErrordisconnect(f);
                        }
                    } else {
                        callback.receiveErrordisconnect(error);
                    }
                }

                public void onFault(
                    org.apache.axis2.context.MessageContext faultContext) {
                    org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
                    onError(fault);
                }

                public void onComplete() {
                    // Do nothing by default
                }
            });

        org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;

        if ((_operations[1].getMessageReceiver() == null) &&
                _operationClient.getOptions().isUseSeparateListener()) {
            _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
            _operations[1].setMessageReceiver(_callbackReceiver);
        }

        //execute the operation client
        _operationClient.execute(false);
    }

    /**
     * Auto generated method signature
     * @see fr.lip6.move.wrapper.ws.Wrapper#connect
     * @param connect3
     */
    public fr.lip6.move.wrapper.ws.WrapperStub.ConnectResponse connect(
        fr.lip6.move.wrapper.ws.WrapperStub.Connect connect3)
        throws java.rmi.RemoteException,
            fr.lip6.move.wrapper.ws.GExceptionException0 {
        try {
            org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[2].getName());
            _operationClient.getOptions().setAction("urn:connect");
            _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

            addPropertyToOperationClient(_operationClient,
                org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
                "&");

            // create a message context
            org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(_operationClient.getOptions()
                                                        .getSoapVersionURI()),
                    connect3,
                    optimizeContent(
                        new javax.xml.namespace.QName(
                            "http://ws.wrapper.move.lip6.fr", "connect")));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            _messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            _operationClient.addMessageContext(_messageContext);

            //execute the operation client
            _operationClient.execute(true);

            org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

            java.lang.Object object = fromOM(_returnEnv.getBody()
                                                       .getFirstElement(),
                    fr.lip6.move.wrapper.ws.WrapperStub.ConnectResponse.class,
                    getEnvelopeNamespaces(_returnEnv));
            _messageContext.getTransportOut().getSender()
                           .cleanup(_messageContext);

            return (fr.lip6.move.wrapper.ws.WrapperStub.ConnectResponse) object;
        } catch (org.apache.axis2.AxisFault f) {
            org.apache.axiom.om.OMElement faultElt = f.getDetail();

            if (faultElt != null) {
                if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
                    //make the fault by reflection
                    try {
                        java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(faultElt.getQName());
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.Exception ex = (java.lang.Exception) exceptionClass.newInstance();

                        //message class
                        java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,
                                messageClass, null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                new java.lang.Class[] { messageClass });
                        m.invoke(ex, new java.lang.Object[] { messageObject });

                        if (ex instanceof fr.lip6.move.wrapper.ws.GExceptionException0) {
                            throw (fr.lip6.move.wrapper.ws.GExceptionException0) ex;
                        }

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    } catch (java.lang.ClassCastException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                } else {
                    throw f;
                }
            } else {
                throw f;
            }
        }
    }

    /**
     * Auto generated method signature for Asynchronous Invocations
     * @see fr.lip6.move.wrapper.ws.Wrapper#startconnect
     * @param connect3
     */
    public void startconnect(
        fr.lip6.move.wrapper.ws.WrapperStub.Connect connect3,
        final fr.lip6.move.wrapper.ws.WrapperCallbackHandler callback)
        throws java.rmi.RemoteException {
        org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[2].getName());
        _operationClient.getOptions().setAction("urn:connect");
        _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

        addPropertyToOperationClient(_operationClient,
            org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
            "&");

        // create SOAP envelope with that payload
        org.apache.axiom.soap.SOAPEnvelope env = null;
        org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

        //Style is Doc.
        env = toEnvelope(getFactory(_operationClient.getOptions()
                                                    .getSoapVersionURI()),
                connect3,
                optimizeContent(
                    new javax.xml.namespace.QName(
                        "http://ws.wrapper.move.lip6.fr", "connect")));

        // adding SOAP soap_headers
        _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);

        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
                public void onMessage(
                    org.apache.axis2.context.MessageContext resultContext) {
                    try {
                        org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

                        java.lang.Object object = fromOM(resultEnv.getBody()
                                                                  .getFirstElement(),
                                fr.lip6.move.wrapper.ws.WrapperStub.ConnectResponse.class,
                                getEnvelopeNamespaces(resultEnv));
                        callback.receiveResultconnect((fr.lip6.move.wrapper.ws.WrapperStub.ConnectResponse) object);
                    } catch (org.apache.axis2.AxisFault e) {
                        callback.receiveErrorconnect(e);
                    }
                }

                public void onError(java.lang.Exception error) {
                    if (error instanceof org.apache.axis2.AxisFault) {
                        org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
                        org.apache.axiom.om.OMElement faultElt = f.getDetail();

                        if (faultElt != null) {
                            if (faultExceptionNameMap.containsKey(
                                        faultElt.getQName())) {
                                //make the fault by reflection
                                try {
                                    java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(faultElt.getQName());
                                    java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                                    java.lang.Exception ex = (java.lang.Exception) exceptionClass.newInstance();

                                    //message class
                                    java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
                                    java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                                    java.lang.Object messageObject = fromOM(faultElt,
                                            messageClass, null);
                                    java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                            new java.lang.Class[] { messageClass });
                                    m.invoke(ex,
                                        new java.lang.Object[] { messageObject });

                                    if (ex instanceof fr.lip6.move.wrapper.ws.GExceptionException0) {
                                        callback.receiveErrorconnect((fr.lip6.move.wrapper.ws.GExceptionException0) ex);

                                        return;
                                    }

                                    callback.receiveErrorconnect(new java.rmi.RemoteException(
                                            ex.getMessage(), ex));
                                } catch (java.lang.ClassCastException e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrorconnect(f);
                                } catch (java.lang.ClassNotFoundException e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrorconnect(f);
                                } catch (java.lang.NoSuchMethodException e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrorconnect(f);
                                } catch (java.lang.reflect.InvocationTargetException e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrorconnect(f);
                                } catch (java.lang.IllegalAccessException e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrorconnect(f);
                                } catch (java.lang.InstantiationException e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrorconnect(f);
                                } catch (org.apache.axis2.AxisFault e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrorconnect(f);
                                }
                            } else {
                                callback.receiveErrorconnect(f);
                            }
                        } else {
                            callback.receiveErrorconnect(f);
                        }
                    } else {
                        callback.receiveErrorconnect(error);
                    }
                }

                public void onFault(
                    org.apache.axis2.context.MessageContext faultContext) {
                    org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
                    onError(fault);
                }

                public void onComplete() {
                    // Do nothing by default
                }
            });

        org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;

        if ((_operations[2].getMessageReceiver() == null) &&
                _operationClient.getOptions().isUseSeparateListener()) {
            _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
            _operations[2].setMessageReceiver(_callbackReceiver);
        }

        //execute the operation client
        _operationClient.execute(false);
    }

    /**
     *  A utility method that copies the namepaces from the SOAPEnvelope
     */
    private java.util.Map getEnvelopeNamespaces(
        org.apache.axiom.soap.SOAPEnvelope env) {
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();

        while (namespaceIterator.hasNext()) {
            org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
            returnMap.put(ns.getPrefix(), ns.getNamespaceURI());
        }

        return returnMap;
    }

    private boolean optimizeContent(javax.xml.namespace.QName opName) {
        if (opNameArray == null) {
            return false;
        }

        for (int i = 0; i < opNameArray.length; i++) {
            if (opName.equals(opNameArray[i])) {
                return true;
            }
        }

        return false;
    }

    private org.apache.axiom.om.OMElement toOM(
        fr.lip6.move.wrapper.ws.WrapperStub.Connect param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(fr.lip6.move.wrapper.ws.WrapperStub.Connect.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        fr.lip6.move.wrapper.ws.WrapperStub.ConnectResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(fr.lip6.move.wrapper.ws.WrapperStub.ConnectResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        fr.lip6.move.wrapper.ws.WrapperStub.GException0 param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(fr.lip6.move.wrapper.ws.WrapperStub.GException0.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        fr.lip6.move.wrapper.ws.WrapperStub.Main param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(fr.lip6.move.wrapper.ws.WrapperStub.Main.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        fr.lip6.move.wrapper.ws.WrapperStub.Disconnect param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(fr.lip6.move.wrapper.ws.WrapperStub.Disconnect.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        fr.lip6.move.wrapper.ws.WrapperStub.DisconnectResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(fr.lip6.move.wrapper.ws.WrapperStub.DisconnectResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        fr.lip6.move.wrapper.ws.WrapperStub.Connect param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    fr.lip6.move.wrapper.ws.WrapperStub.Connect.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    /* methods to provide back word compatibility */
    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        fr.lip6.move.wrapper.ws.WrapperStub.Main param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    fr.lip6.move.wrapper.ws.WrapperStub.Main.MY_QNAME, factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    /* methods to provide back word compatibility */
    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        fr.lip6.move.wrapper.ws.WrapperStub.Disconnect param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    fr.lip6.move.wrapper.ws.WrapperStub.Disconnect.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    /* methods to provide back word compatibility */

    /**
     *  get the default envelope
     */
    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory) {
        return factory.getDefaultEnvelope();
    }

    private java.lang.Object fromOM(org.apache.axiom.om.OMElement param,
        java.lang.Class type, java.util.Map extraNamespaces)
        throws org.apache.axis2.AxisFault {
        try {
            if (fr.lip6.move.wrapper.ws.WrapperStub.Connect.class.equals(type)) {
                return fr.lip6.move.wrapper.ws.WrapperStub.Connect.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (fr.lip6.move.wrapper.ws.WrapperStub.ConnectResponse.class.equals(
                        type)) {
                return fr.lip6.move.wrapper.ws.WrapperStub.ConnectResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (fr.lip6.move.wrapper.ws.WrapperStub.GException0.class.equals(
                        type)) {
                return fr.lip6.move.wrapper.ws.WrapperStub.GException0.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (fr.lip6.move.wrapper.ws.WrapperStub.Main.class.equals(type)) {
                return fr.lip6.move.wrapper.ws.WrapperStub.Main.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (fr.lip6.move.wrapper.ws.WrapperStub.Disconnect.class.equals(
                        type)) {
                return fr.lip6.move.wrapper.ws.WrapperStub.Disconnect.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (fr.lip6.move.wrapper.ws.WrapperStub.DisconnectResponse.class.equals(
                        type)) {
                return fr.lip6.move.wrapper.ws.WrapperStub.DisconnectResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (fr.lip6.move.wrapper.ws.WrapperStub.GException0.class.equals(
                        type)) {
                return fr.lip6.move.wrapper.ws.WrapperStub.GException0.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

        return null;
    }

    //http://localhost:8080/axis2/services/Wrapper
    public static class GException extends Exception implements org.apache.axis2.databinding.ADBBean {
        /**
         * field for Error
         */
        protected int localError;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localErrorTracker = false;

        /**
         * field for Level
         */
        protected int localLevel;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localLevelTracker = false;

        /**
         * field for Mess
         */
        protected java.lang.String localMess;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localMessTracker = false;

        /**
         * field for Type
         */
        protected java.lang.String localType;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localTypeTracker = false;

        /* This type was generated from the piece of schema that had
           name = GException
           Namespace URI = http://exceptions.wrapper.move.lip6.fr/xsd
           Namespace Prefix = ns2
         */
        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals("http://exceptions.wrapper.move.lip6.fr/xsd")) {
                return "ns2";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Auto generated getter method
         * @return int
         */
        public int getError() {
            return localError;
        }

        /**
         * Auto generated setter method
         * @param param Error
         */
        public void setError(int param) {
            // setting primitive attribute tracker to true
            if (param == java.lang.Integer.MIN_VALUE) {
                localErrorTracker = false;
            } else {
                localErrorTracker = true;
            }

            this.localError = param;
        }

        /**
         * Auto generated getter method
         * @return int
         */
        public int getLevel() {
            return localLevel;
        }

        /**
         * Auto generated setter method
         * @param param Level
         */
        public void setLevel(int param) {
            // setting primitive attribute tracker to true
            if (param == java.lang.Integer.MIN_VALUE) {
                localLevelTracker = false;
            } else {
                localLevelTracker = true;
            }

            this.localLevel = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getMess() {
            return localMess;
        }

        /**
         * Auto generated setter method
         * @param param Mess
         */
        public void setMess(java.lang.String param) {
            if (param != null) {
                //update the setting tracker
                localMessTracker = true;
            } else {
                localMessTracker = true;
            }

            this.localMess = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getType() {
            return localType;
        }

        /**
         * Auto generated setter method
         * @param param Type
         */
        public void setType(java.lang.String param) {
            if (param != null) {
                //update the setting tracker
                localTypeTracker = true;
            } else {
                localTypeTracker = true;
            }

            this.localType = param;
        }

        /**
         * isReaderMTOMAware
         * @return true if the reader supports MTOM
         */
        public static boolean isReaderMTOMAware(
            javax.xml.stream.XMLStreamReader reader) {
            boolean isReaderMTOMAware = false;

            try {
                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                            org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
            } catch (java.lang.IllegalArgumentException e) {
                isReaderMTOMAware = false;
            }

            return isReaderMTOMAware;
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                    parentQName) {
                    public void serialize(
                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException {
                        GException.this.serialize(parentQName, factory,
                            xmlWriter);
                    }
                };

            return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName,
                factory, dataSource);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();

            if (namespace != null) {
                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                if (writerPrefix != null) {
                    xmlWriter.writeStartElement(namespace,
                        parentQName.getLocalPart());
                } else {
                    if (prefix == null) {
                        prefix = generatePrefix(namespace);
                    }

                    xmlWriter.writeStartElement(prefix,
                        parentQName.getLocalPart(), namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }
            } else {
                xmlWriter.writeStartElement(parentQName.getLocalPart());
            }

            java.lang.String namespacePrefix = registerPrefix(xmlWriter,
                    "http://exceptions.wrapper.move.lip6.fr/xsd");

            if ((namespacePrefix != null) &&
                    (namespacePrefix.trim().length() > 0)) {
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "type",
                    namespacePrefix + ":GException", xmlWriter);
            } else {
                writeAttribute("xsi",
                    "http://www.w3.org/2001/XMLSchema-instance", "type",
                    "GException", xmlWriter);
            }

            if (localExceptionTracker) {
                if (localException != null) {
                    // write null attribute
                    java.lang.String namespace2 = "http://ws.wrapper.move.lip6.fr";

                    if (!namespace2.equals("")) {
                        java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                        if (prefix2 == null) {
                            prefix2 = generatePrefix(namespace2);

                            xmlWriter.writeStartElement(prefix2, "Exception",
                                namespace2);
                            xmlWriter.writeNamespace(prefix2, namespace2);
                            xmlWriter.setPrefix(prefix2, namespace2);
                        } else {
                            xmlWriter.writeStartElement(namespace2, "Exception");
                        }
                    } else {
                        xmlWriter.writeStartElement("Exception");
                    }

                    localException.serialize(xmlWriter);
                    xmlWriter.writeEndElement();
                } else {
                    // write null attribute
                    java.lang.String namespace2 = "http://ws.wrapper.move.lip6.fr";

                    if (!namespace2.equals("")) {
                        java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                        if (prefix2 == null) {
                            prefix2 = generatePrefix(namespace2);

                            xmlWriter.writeStartElement(prefix2, "Exception",
                                namespace2);
                            xmlWriter.writeNamespace(prefix2, namespace2);
                            xmlWriter.setPrefix(prefix2, namespace2);
                        } else {
                            xmlWriter.writeStartElement(namespace2, "Exception");
                        }
                    } else {
                        xmlWriter.writeStartElement("Exception");
                    }

                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                    xmlWriter.writeEndElement();
                }
            }

            if (localErrorTracker) {
                namespace = "http://exceptions.wrapper.move.lip6.fr/xsd";

                if (!namespace.equals("")) {
                    prefix = xmlWriter.getPrefix(namespace);

                    if (prefix == null) {
                        prefix = generatePrefix(namespace);

                        xmlWriter.writeStartElement(prefix, "error", namespace);
                        xmlWriter.writeNamespace(prefix, namespace);
                        xmlWriter.setPrefix(prefix, namespace);
                    } else {
                        xmlWriter.writeStartElement(namespace, "error");
                    }
                } else {
                    xmlWriter.writeStartElement("error");
                }

                if (localError == java.lang.Integer.MIN_VALUE) {
                    throw new org.apache.axis2.databinding.ADBException(
                        "error cannot be null!!");
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localError));
                }

                xmlWriter.writeEndElement();
            }

            if (localLevelTracker) {
                namespace = "http://exceptions.wrapper.move.lip6.fr/xsd";

                if (!namespace.equals("")) {
                    prefix = xmlWriter.getPrefix(namespace);

                    if (prefix == null) {
                        prefix = generatePrefix(namespace);

                        xmlWriter.writeStartElement(prefix, "level", namespace);
                        xmlWriter.writeNamespace(prefix, namespace);
                        xmlWriter.setPrefix(prefix, namespace);
                    } else {
                        xmlWriter.writeStartElement(namespace, "level");
                    }
                } else {
                    xmlWriter.writeStartElement("level");
                }

                if (localLevel == java.lang.Integer.MIN_VALUE) {
                    throw new org.apache.axis2.databinding.ADBException(
                        "level cannot be null!!");
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localLevel));
                }

                xmlWriter.writeEndElement();
            }

            if (localMessTracker) {
                namespace = "http://exceptions.wrapper.move.lip6.fr/xsd";

                if (!namespace.equals("")) {
                    prefix = xmlWriter.getPrefix(namespace);

                    if (prefix == null) {
                        prefix = generatePrefix(namespace);

                        xmlWriter.writeStartElement(prefix, "mess", namespace);
                        xmlWriter.writeNamespace(prefix, namespace);
                        xmlWriter.setPrefix(prefix, namespace);
                    } else {
                        xmlWriter.writeStartElement(namespace, "mess");
                    }
                } else {
                    xmlWriter.writeStartElement("mess");
                }

                if (localMess == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localMess);
                }

                xmlWriter.writeEndElement();
            }

            if (localTypeTracker) {
                namespace = "http://exceptions.wrapper.move.lip6.fr/xsd";

                if (!namespace.equals("")) {
                    prefix = xmlWriter.getPrefix(namespace);

                    if (prefix == null) {
                        prefix = generatePrefix(namespace);

                        xmlWriter.writeStartElement(prefix, "type", namespace);
                        xmlWriter.writeNamespace(prefix, namespace);
                        xmlWriter.setPrefix(prefix, namespace);
                    } else {
                        xmlWriter.writeStartElement(namespace, "type");
                    }
                } else {
                    xmlWriter.writeStartElement("type");
                }

                if (localType == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localType);
                }

                xmlWriter.writeEndElement();
            }

            xmlWriter.writeEndElement();
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(
            javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException {
            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            attribList.add(new javax.xml.namespace.QName(
                    "http://www.w3.org/2001/XMLSchema-instance", "type"));
            attribList.add(new javax.xml.namespace.QName(
                    "http://exceptions.wrapper.move.lip6.fr/xsd", "GException"));

            if (localExceptionTracker) {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ws.wrapper.move.lip6.fr", "Exception"));

                elementList.add((localException == null) ? null : localException);
            }

            if (localErrorTracker) {
                elementList.add(new javax.xml.namespace.QName(
                        "http://exceptions.wrapper.move.lip6.fr/xsd", "error"));

                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localError));
            }

            if (localLevelTracker) {
                elementList.add(new javax.xml.namespace.QName(
                        "http://exceptions.wrapper.move.lip6.fr/xsd", "level"));

                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localLevel));
            }

            if (localMessTracker) {
                elementList.add(new javax.xml.namespace.QName(
                        "http://exceptions.wrapper.move.lip6.fr/xsd", "mess"));

                elementList.add((localMess == null) ? null
                                                    : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localMess));
            }

            if (localTypeTracker) {
                elementList.add(new javax.xml.namespace.QName(
                        "http://exceptions.wrapper.move.lip6.fr/xsd", "type"));

                elementList.add((localType == null) ? null
                                                    : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localType));
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                elementList.toArray(), attribList.toArray());
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static GException parse(
                javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                GException object = new GException();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"GException".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (GException) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://ws.wrapper.move.lip6.fr", "Exception").equals(
                                reader.getName())) {
                        boolean loopDone1 = false;
                        javax.xml.namespace.QName startQname1 = new javax.xml.namespace.QName("http://ws.wrapper.move.lip6.fr",
                                "Exception");

                        while (!loopDone1) {
                            if (reader.isStartElement() &&
                                    startQname1.equals(reader.getName())) {
                                loopDone1 = true;
                            } else {
                                reader.next();
                            }
                        }

                        // We need to wrap the reader so that it produces a fake START_DOCUEMENT event
                        // this is needed by the builder classes
                        org.apache.axis2.databinding.utils.NamedStaxOMBuilder builder1 =
                            new org.apache.axis2.databinding.utils.NamedStaxOMBuilder(new org.apache.axis2.util.StreamWrapper(
                                    reader), startQname1);
                        object.setException(builder1.getOMElement()
                                                    .getFirstElement());

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://exceptions.wrapper.move.lip6.fr/xsd",
                                "error").equals(reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setError(org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        object.setError(java.lang.Integer.MIN_VALUE);
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://exceptions.wrapper.move.lip6.fr/xsd",
                                "level").equals(reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setLevel(org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        object.setLevel(java.lang.Integer.MIN_VALUE);
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://exceptions.wrapper.move.lip6.fr/xsd",
                                "mess").equals(reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setMess(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://exceptions.wrapper.move.lip6.fr/xsd",
                                "type").equals(reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setType(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class
    }

    public static class GException0 implements org.apache.axis2.databinding.ADBBean {
        public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://ws.wrapper.move.lip6.fr",
                "GException", "ns1");

        /**
         * field for GException
         */
        protected GException localGException;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localGExceptionTracker = false;

        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals("http://ws.wrapper.move.lip6.fr")) {
                return "ns1";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Auto generated getter method
         * @return GException
         */
        public GException getGException() {
            return localGException;
        }

        /**
         * Auto generated setter method
         * @param param GException
         */
        public void setGException(GException param) {
            if (param != null) {
                //update the setting tracker
                localGExceptionTracker = true;
            } else {
                localGExceptionTracker = true;
            }

            this.localGException = param;
        }

        /**
         * isReaderMTOMAware
         * @return true if the reader supports MTOM
         */
        public static boolean isReaderMTOMAware(
            javax.xml.stream.XMLStreamReader reader) {
            boolean isReaderMTOMAware = false;

            try {
                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                            org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
            } catch (java.lang.IllegalArgumentException e) {
                isReaderMTOMAware = false;
            }

            return isReaderMTOMAware;
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                    MY_QNAME) {
                    public void serialize(
                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException {
                        GException0.this.serialize(MY_QNAME, factory, xmlWriter);
                    }
                };

            return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME,
                factory, dataSource);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();

            if (namespace != null) {
                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                if (writerPrefix != null) {
                    xmlWriter.writeStartElement(namespace,
                        parentQName.getLocalPart());
                } else {
                    if (prefix == null) {
                        prefix = generatePrefix(namespace);
                    }

                    xmlWriter.writeStartElement(prefix,
                        parentQName.getLocalPart(), namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }
            } else {
                xmlWriter.writeStartElement(parentQName.getLocalPart());
            }

            if (localGExceptionTracker) {
                if (localGException == null) {
                    java.lang.String namespace2 = "http://ws.wrapper.move.lip6.fr";

                    if (!namespace2.equals("")) {
                        java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                        if (prefix2 == null) {
                            prefix2 = generatePrefix(namespace2);

                            xmlWriter.writeStartElement(prefix2, "GException",
                                namespace2);
                            xmlWriter.writeNamespace(prefix2, namespace2);
                            xmlWriter.setPrefix(prefix2, namespace2);
                        } else {
                            xmlWriter.writeStartElement(namespace2, "GException");
                        }
                    } else {
                        xmlWriter.writeStartElement("GException");
                    }

                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                    xmlWriter.writeEndElement();
                } else {
                    localGException.serialize(new javax.xml.namespace.QName(
                            "http://ws.wrapper.move.lip6.fr", "GException"),
                        factory, xmlWriter);
                }
            }

            xmlWriter.writeEndElement();
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(
            javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException {
            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            if (localGExceptionTracker) {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ws.wrapper.move.lip6.fr", "GException"));

                elementList.add((localGException == null) ? null : localGException);
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                elementList.toArray(), attribList.toArray());
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static GException0 parse(
                javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                GException0 object = new GException0();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "nil");

                    if ("true".equals(nillableValue) ||
                            "1".equals(nillableValue)) {
                        // Skip the element and report the null value.  It cannot have subelements.
                        while (!reader.isEndElement())
                            reader.next();

                        return null;
                    }

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"GException".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (GException0) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://ws.wrapper.move.lip6.fr", "GException").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if ("true".equals(nillableValue) ||
                                "1".equals(nillableValue)) {
                            object.setGException(null);
                            reader.next();

                            reader.next();
                        } else {
                            object.setGException(GException.Factory.parse(
                                    reader));

                            reader.next();
                        }
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class
    }

    public static class Unauthentification implements org.apache.axis2.databinding.ADBBean {
        /**
         * field for Id
         * This was an Array!
         */
        protected int[] localId;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localIdTracker = false;

        /* This type was generated from the piece of schema that had
           name = Unauthentification
           Namespace URI = http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd
           Namespace Prefix = ns3
         */
        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals(
                        "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd")) {
                return "ns3";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Auto generated getter method
         * @return int[]
         */
        public int[] getId() {
            return localId;
        }

        /**
         * validate the array for Id
         */
        protected void validateId(int[] param) {
        }

        /**
         * Auto generated setter method
         * @param param Id
         */
        public void setId(int[] param) {
            validateId(param);

            if (param != null) {
                //update the setting tracker
                localIdTracker = true;
            } else {
                localIdTracker = true;
            }

            this.localId = param;
        }

        /**
         * isReaderMTOMAware
         * @return true if the reader supports MTOM
         */
        public static boolean isReaderMTOMAware(
            javax.xml.stream.XMLStreamReader reader) {
            boolean isReaderMTOMAware = false;

            try {
                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                            org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
            } catch (java.lang.IllegalArgumentException e) {
                isReaderMTOMAware = false;
            }

            return isReaderMTOMAware;
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                    parentQName) {
                    public void serialize(
                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException {
                        Unauthentification.this.serialize(parentQName, factory,
                            xmlWriter);
                    }
                };

            return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName,
                factory, dataSource);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();

            if (namespace != null) {
                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                if (writerPrefix != null) {
                    xmlWriter.writeStartElement(namespace,
                        parentQName.getLocalPart());
                } else {
                    if (prefix == null) {
                        prefix = generatePrefix(namespace);
                    }

                    xmlWriter.writeStartElement(prefix,
                        parentQName.getLocalPart(), namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }
            } else {
                xmlWriter.writeStartElement(parentQName.getLocalPart());
            }

            if (localIdTracker) {
                if (localId != null) {
                    namespace = "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd";

                    boolean emptyNamespace = (namespace == null) ||
                        (namespace.length() == 0);
                    prefix = emptyNamespace ? null
                                            : xmlWriter.getPrefix(namespace);

                    for (int i = 0; i < localId.length; i++) {
                        if (localId[i] != java.lang.Integer.MIN_VALUE) {
                            if (!emptyNamespace) {
                                if (prefix == null) {
                                    java.lang.String prefix2 = generatePrefix(namespace);

                                    xmlWriter.writeStartElement(prefix2, "id",
                                        namespace);
                                    xmlWriter.writeNamespace(prefix2, namespace);
                                    xmlWriter.setPrefix(prefix2, namespace);
                                } else {
                                    xmlWriter.writeStartElement(namespace, "id");
                                }
                            } else {
                                xmlWriter.writeStartElement("id");
                            }

                            xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    localId[i]));
                            xmlWriter.writeEndElement();
                        } else {
                            // write null attribute
                            namespace = "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd";

                            if (!namespace.equals("")) {
                                prefix = xmlWriter.getPrefix(namespace);

                                if (prefix == null) {
                                    prefix = generatePrefix(namespace);

                                    xmlWriter.writeStartElement(prefix, "id",
                                        namespace);
                                    xmlWriter.writeNamespace(prefix, namespace);
                                    xmlWriter.setPrefix(prefix, namespace);
                                } else {
                                    xmlWriter.writeStartElement(namespace, "id");
                                }
                            } else {
                                xmlWriter.writeStartElement("id");
                            }

                            writeAttribute("xsi",
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "nil", "1", xmlWriter);
                            xmlWriter.writeEndElement();
                        }
                    }
                } else {
                    // write the null attribute
                    // write null attribute
                    java.lang.String namespace2 = "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd";

                    if (!namespace2.equals("")) {
                        java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                        if (prefix2 == null) {
                            prefix2 = generatePrefix(namespace2);

                            xmlWriter.writeStartElement(prefix2, "id",
                                namespace2);
                            xmlWriter.writeNamespace(prefix2, namespace2);
                            xmlWriter.setPrefix(prefix2, namespace2);
                        } else {
                            xmlWriter.writeStartElement(namespace2, "id");
                        }
                    } else {
                        xmlWriter.writeStartElement("id");
                    }

                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                    xmlWriter.writeEndElement();
                }
            }

            xmlWriter.writeEndElement();
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(
            javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException {
            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            if (localIdTracker) {
                if (localId != null) {
                    for (int i = 0; i < localId.length; i++) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                                "id"));
                        elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                localId[i]));
                    }
                } else {
                    elementList.add(new javax.xml.namespace.QName(
                            "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                            "id"));
                    elementList.add(null);
                }
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                elementList.toArray(), attribList.toArray());
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static Unauthentification parse(
                javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                Unauthentification object = new Unauthentification();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"Unauthentification".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (Unauthentification) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    java.util.ArrayList list1 = new java.util.ArrayList();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                                "id").equals(reader.getName())) {
                        // Process the array and step past its final element's end.
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if ("true".equals(nillableValue) ||
                                "1".equals(nillableValue)) {
                            list1.add(String.valueOf(
                                    java.lang.Integer.MIN_VALUE));

                            reader.next();
                        } else {
                            list1.add(reader.getElementText());
                        }

                        //loop until we find a start element that is not part of this array
                        boolean loopDone1 = false;

                        while (!loopDone1) {
                            // Ensure we are at the EndElement
                            while (!reader.isEndElement()) {
                                reader.next();
                            }

                            // Step out of this element
                            reader.next();

                            // Step to next element event.
                            while (!reader.isStartElement() &&
                                    !reader.isEndElement())
                                reader.next();

                            if (reader.isEndElement()) {
                                //two continuous end elements means we are exiting the xml structure
                                loopDone1 = true;
                            } else {
                                if (new javax.xml.namespace.QName(
                                            "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                                            "id").equals(reader.getName())) {
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                            "nil");

                                    if ("true".equals(nillableValue) ||
                                            "1".equals(nillableValue)) {
                                        list1.add(String.valueOf(
                                                java.lang.Integer.MIN_VALUE));

                                        reader.next();
                                    } else {
                                        list1.add(reader.getElementText());
                                    }
                                } else {
                                    loopDone1 = true;
                                }
                            }
                        }

                        // call the converter utility  to convert and set the array
                        object.setId((int[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                int.class, list1));
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class
    }

    public static class Disconnect implements org.apache.axis2.databinding.ADBBean {
        public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://ws.wrapper.move.lip6.fr",
                "disconnect", "ns1");

        /**
         * field for UserId
         */
        protected int localUserId;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localUserIdTracker = false;

        /**
         * field for AuthId
         */
        protected int localAuthId;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localAuthIdTracker = false;

        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals("http://ws.wrapper.move.lip6.fr")) {
                return "ns1";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Auto generated getter method
         * @return int
         */
        public int getUserId() {
            return localUserId;
        }

        /**
         * Auto generated setter method
         * @param param UserId
         */
        public void setUserId(int param) {
            // setting primitive attribute tracker to true
            if (param == java.lang.Integer.MIN_VALUE) {
                localUserIdTracker = false;
            } else {
                localUserIdTracker = true;
            }

            this.localUserId = param;
        }

        /**
         * Auto generated getter method
         * @return int
         */
        public int getAuthId() {
            return localAuthId;
        }

        /**
         * Auto generated setter method
         * @param param AuthId
         */
        public void setAuthId(int param) {
            // setting primitive attribute tracker to true
            if (param == java.lang.Integer.MIN_VALUE) {
                localAuthIdTracker = false;
            } else {
                localAuthIdTracker = true;
            }

            this.localAuthId = param;
        }

        /**
         * isReaderMTOMAware
         * @return true if the reader supports MTOM
         */
        public static boolean isReaderMTOMAware(
            javax.xml.stream.XMLStreamReader reader) {
            boolean isReaderMTOMAware = false;

            try {
                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                            org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
            } catch (java.lang.IllegalArgumentException e) {
                isReaderMTOMAware = false;
            }

            return isReaderMTOMAware;
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                    MY_QNAME) {
                    public void serialize(
                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException {
                        Disconnect.this.serialize(MY_QNAME, factory, xmlWriter);
                    }
                };

            return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME,
                factory, dataSource);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();

            if (namespace != null) {
                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                if (writerPrefix != null) {
                    xmlWriter.writeStartElement(namespace,
                        parentQName.getLocalPart());
                } else {
                    if (prefix == null) {
                        prefix = generatePrefix(namespace);
                    }

                    xmlWriter.writeStartElement(prefix,
                        parentQName.getLocalPart(), namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }
            } else {
                xmlWriter.writeStartElement(parentQName.getLocalPart());
            }

            if (localUserIdTracker) {
                namespace = "http://ws.wrapper.move.lip6.fr";

                if (!namespace.equals("")) {
                    prefix = xmlWriter.getPrefix(namespace);

                    if (prefix == null) {
                        prefix = generatePrefix(namespace);

                        xmlWriter.writeStartElement(prefix, "userId", namespace);
                        xmlWriter.writeNamespace(prefix, namespace);
                        xmlWriter.setPrefix(prefix, namespace);
                    } else {
                        xmlWriter.writeStartElement(namespace, "userId");
                    }
                } else {
                    xmlWriter.writeStartElement("userId");
                }

                if (localUserId == java.lang.Integer.MIN_VALUE) {
                    throw new org.apache.axis2.databinding.ADBException(
                        "userId cannot be null!!");
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localUserId));
                }

                xmlWriter.writeEndElement();
            }

            if (localAuthIdTracker) {
                namespace = "http://ws.wrapper.move.lip6.fr";

                if (!namespace.equals("")) {
                    prefix = xmlWriter.getPrefix(namespace);

                    if (prefix == null) {
                        prefix = generatePrefix(namespace);

                        xmlWriter.writeStartElement(prefix, "authId", namespace);
                        xmlWriter.writeNamespace(prefix, namespace);
                        xmlWriter.setPrefix(prefix, namespace);
                    } else {
                        xmlWriter.writeStartElement(namespace, "authId");
                    }
                } else {
                    xmlWriter.writeStartElement("authId");
                }

                if (localAuthId == java.lang.Integer.MIN_VALUE) {
                    throw new org.apache.axis2.databinding.ADBException(
                        "authId cannot be null!!");
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localAuthId));
                }

                xmlWriter.writeEndElement();
            }

            xmlWriter.writeEndElement();
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(
            javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException {
            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            if (localUserIdTracker) {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ws.wrapper.move.lip6.fr", "userId"));

                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localUserId));
            }

            if (localAuthIdTracker) {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ws.wrapper.move.lip6.fr", "authId"));

                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localAuthId));
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                elementList.toArray(), attribList.toArray());
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static Disconnect parse(
                javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                Disconnect object = new Disconnect();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"disconnect".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (Disconnect) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://ws.wrapper.move.lip6.fr", "userId").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setUserId(org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        object.setUserId(java.lang.Integer.MIN_VALUE);
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://ws.wrapper.move.lip6.fr", "authId").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setAuthId(org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        object.setAuthId(java.lang.Integer.MIN_VALUE);
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class
    }

    public static class Authentification implements org.apache.axis2.databinding.ADBBean {
        /**
         * field for AckOC
         */
        protected AckOC localAckOC;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localAckOCTracker = false;

        /**
         * field for AckSC
         */
        protected AckSC localAckSC;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localAckSCTracker = false;

        /**
         * field for Id
         * This was an Array!
         */
        protected int[] localId;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localIdTracker = false;

        /**
         * field for OC
         */
        protected AckOC localOC;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localOCTracker = false;

        /**
         * field for SC
         */
        protected AckSC localSC;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localSCTracker = false;

        /* This type was generated from the piece of schema that had
           name = Authentification
           Namespace URI = http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd
           Namespace Prefix = ns3
         */
        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals(
                        "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd")) {
                return "ns3";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Auto generated getter method
         * @return AckOC
         */
        public AckOC getAckOC() {
            return localAckOC;
        }

        /**
         * Auto generated setter method
         * @param param AckOC
         */
        public void setAckOC(AckOC param) {
            if (param != null) {
                //update the setting tracker
                localAckOCTracker = true;
            } else {
                localAckOCTracker = true;
            }

            this.localAckOC = param;
        }

        /**
         * Auto generated getter method
         * @return AckSC
         */
        public AckSC getAckSC() {
            return localAckSC;
        }

        /**
         * Auto generated setter method
         * @param param AckSC
         */
        public void setAckSC(AckSC param) {
            if (param != null) {
                //update the setting tracker
                localAckSCTracker = true;
            } else {
                localAckSCTracker = true;
            }

            this.localAckSC = param;
        }

        /**
         * Auto generated getter method
         * @return int[]
         */
        public int[] getId() {
            return localId;
        }

        /**
         * validate the array for Id
         */
        protected void validateId(int[] param) {
        }

        /**
         * Auto generated setter method
         * @param param Id
         */
        public void setId(int[] param) {
            validateId(param);

            if (param != null) {
                //update the setting tracker
                localIdTracker = true;
            } else {
                localIdTracker = true;
            }

            this.localId = param;
        }

        /**
         * Auto generated getter method
         * @return AckOC
         */
        public AckOC getOC() {
            return localOC;
        }

        /**
         * Auto generated setter method
         * @param param OC
         */
        public void setOC(AckOC param) {
            if (param != null) {
                //update the setting tracker
                localOCTracker = true;
            } else {
                localOCTracker = true;
            }

            this.localOC = param;
        }

        /**
         * Auto generated getter method
         * @return AckSC
         */
        public AckSC getSC() {
            return localSC;
        }

        /**
         * Auto generated setter method
         * @param param SC
         */
        public void setSC(AckSC param) {
            if (param != null) {
                //update the setting tracker
                localSCTracker = true;
            } else {
                localSCTracker = true;
            }

            this.localSC = param;
        }

        /**
         * isReaderMTOMAware
         * @return true if the reader supports MTOM
         */
        public static boolean isReaderMTOMAware(
            javax.xml.stream.XMLStreamReader reader) {
            boolean isReaderMTOMAware = false;

            try {
                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                            org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
            } catch (java.lang.IllegalArgumentException e) {
                isReaderMTOMAware = false;
            }

            return isReaderMTOMAware;
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                    parentQName) {
                    public void serialize(
                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException {
                        Authentification.this.serialize(parentQName, factory,
                            xmlWriter);
                    }
                };

            return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName,
                factory, dataSource);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();

            if (namespace != null) {
                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                if (writerPrefix != null) {
                    xmlWriter.writeStartElement(namespace,
                        parentQName.getLocalPart());
                } else {
                    if (prefix == null) {
                        prefix = generatePrefix(namespace);
                    }

                    xmlWriter.writeStartElement(prefix,
                        parentQName.getLocalPart(), namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }
            } else {
                xmlWriter.writeStartElement(parentQName.getLocalPart());
            }

            if (localAckOCTracker) {
                if (localAckOC == null) {
                    java.lang.String namespace2 = "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd";

                    if (!namespace2.equals("")) {
                        java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                        if (prefix2 == null) {
                            prefix2 = generatePrefix(namespace2);

                            xmlWriter.writeStartElement(prefix2, "ackOC",
                                namespace2);
                            xmlWriter.writeNamespace(prefix2, namespace2);
                            xmlWriter.setPrefix(prefix2, namespace2);
                        } else {
                            xmlWriter.writeStartElement(namespace2, "ackOC");
                        }
                    } else {
                        xmlWriter.writeStartElement("ackOC");
                    }

                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                    xmlWriter.writeEndElement();
                } else {
                    localAckOC.serialize(new javax.xml.namespace.QName(
                            "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                            "ackOC"), factory, xmlWriter);
                }
            }

            if (localAckSCTracker) {
                if (localAckSC == null) {
                    java.lang.String namespace2 = "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd";

                    if (!namespace2.equals("")) {
                        java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                        if (prefix2 == null) {
                            prefix2 = generatePrefix(namespace2);

                            xmlWriter.writeStartElement(prefix2, "ackSC",
                                namespace2);
                            xmlWriter.writeNamespace(prefix2, namespace2);
                            xmlWriter.setPrefix(prefix2, namespace2);
                        } else {
                            xmlWriter.writeStartElement(namespace2, "ackSC");
                        }
                    } else {
                        xmlWriter.writeStartElement("ackSC");
                    }

                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                    xmlWriter.writeEndElement();
                } else {
                    localAckSC.serialize(new javax.xml.namespace.QName(
                            "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                            "ackSC"), factory, xmlWriter);
                }
            }

            if (localIdTracker) {
                if (localId != null) {
                    namespace = "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd";

                    boolean emptyNamespace = (namespace == null) ||
                        (namespace.length() == 0);
                    prefix = emptyNamespace ? null
                                            : xmlWriter.getPrefix(namespace);

                    for (int i = 0; i < localId.length; i++) {
                        if (localId[i] != java.lang.Integer.MIN_VALUE) {
                            if (!emptyNamespace) {
                                if (prefix == null) {
                                    java.lang.String prefix2 = generatePrefix(namespace);

                                    xmlWriter.writeStartElement(prefix2, "id",
                                        namespace);
                                    xmlWriter.writeNamespace(prefix2, namespace);
                                    xmlWriter.setPrefix(prefix2, namespace);
                                } else {
                                    xmlWriter.writeStartElement(namespace, "id");
                                }
                            } else {
                                xmlWriter.writeStartElement("id");
                            }

                            xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    localId[i]));
                            xmlWriter.writeEndElement();
                        } else {
                            // write null attribute
                            namespace = "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd";

                            if (!namespace.equals("")) {
                                prefix = xmlWriter.getPrefix(namespace);

                                if (prefix == null) {
                                    prefix = generatePrefix(namespace);

                                    xmlWriter.writeStartElement(prefix, "id",
                                        namespace);
                                    xmlWriter.writeNamespace(prefix, namespace);
                                    xmlWriter.setPrefix(prefix, namespace);
                                } else {
                                    xmlWriter.writeStartElement(namespace, "id");
                                }
                            } else {
                                xmlWriter.writeStartElement("id");
                            }

                            writeAttribute("xsi",
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "nil", "1", xmlWriter);
                            xmlWriter.writeEndElement();
                        }
                    }
                } else {
                    // write the null attribute
                    // write null attribute
                    java.lang.String namespace2 = "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd";

                    if (!namespace2.equals("")) {
                        java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                        if (prefix2 == null) {
                            prefix2 = generatePrefix(namespace2);

                            xmlWriter.writeStartElement(prefix2, "id",
                                namespace2);
                            xmlWriter.writeNamespace(prefix2, namespace2);
                            xmlWriter.setPrefix(prefix2, namespace2);
                        } else {
                            xmlWriter.writeStartElement(namespace2, "id");
                        }
                    } else {
                        xmlWriter.writeStartElement("id");
                    }

                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                    xmlWriter.writeEndElement();
                }
            }

            if (localOCTracker) {
                if (localOC == null) {
                    java.lang.String namespace2 = "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd";

                    if (!namespace2.equals("")) {
                        java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                        if (prefix2 == null) {
                            prefix2 = generatePrefix(namespace2);

                            xmlWriter.writeStartElement(prefix2, "oC",
                                namespace2);
                            xmlWriter.writeNamespace(prefix2, namespace2);
                            xmlWriter.setPrefix(prefix2, namespace2);
                        } else {
                            xmlWriter.writeStartElement(namespace2, "oC");
                        }
                    } else {
                        xmlWriter.writeStartElement("oC");
                    }

                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                    xmlWriter.writeEndElement();
                } else {
                    localOC.serialize(new javax.xml.namespace.QName(
                            "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                            "oC"), factory, xmlWriter);
                }
            }

            if (localSCTracker) {
                if (localSC == null) {
                    java.lang.String namespace2 = "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd";

                    if (!namespace2.equals("")) {
                        java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                        if (prefix2 == null) {
                            prefix2 = generatePrefix(namespace2);

                            xmlWriter.writeStartElement(prefix2, "sC",
                                namespace2);
                            xmlWriter.writeNamespace(prefix2, namespace2);
                            xmlWriter.setPrefix(prefix2, namespace2);
                        } else {
                            xmlWriter.writeStartElement(namespace2, "sC");
                        }
                    } else {
                        xmlWriter.writeStartElement("sC");
                    }

                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                    xmlWriter.writeEndElement();
                } else {
                    localSC.serialize(new javax.xml.namespace.QName(
                            "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                            "sC"), factory, xmlWriter);
                }
            }

            xmlWriter.writeEndElement();
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(
            javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException {
            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            if (localAckOCTracker) {
                elementList.add(new javax.xml.namespace.QName(
                        "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                        "ackOC"));

                elementList.add((localAckOC == null) ? null : localAckOC);
            }

            if (localAckSCTracker) {
                elementList.add(new javax.xml.namespace.QName(
                        "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                        "ackSC"));

                elementList.add((localAckSC == null) ? null : localAckSC);
            }

            if (localIdTracker) {
                if (localId != null) {
                    for (int i = 0; i < localId.length; i++) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                                "id"));
                        elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                localId[i]));
                    }
                } else {
                    elementList.add(new javax.xml.namespace.QName(
                            "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                            "id"));
                    elementList.add(null);
                }
            }

            if (localOCTracker) {
                elementList.add(new javax.xml.namespace.QName(
                        "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                        "oC"));

                elementList.add((localOC == null) ? null : localOC);
            }

            if (localSCTracker) {
                elementList.add(new javax.xml.namespace.QName(
                        "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                        "sC"));

                elementList.add((localSC == null) ? null : localSC);
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                elementList.toArray(), attribList.toArray());
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static Authentification parse(
                javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                Authentification object = new Authentification();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"Authentification".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (Authentification) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    java.util.ArrayList list3 = new java.util.ArrayList();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                                "ackOC").equals(reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if ("true".equals(nillableValue) ||
                                "1".equals(nillableValue)) {
                            object.setAckOC(null);
                            reader.next();

                            reader.next();
                        } else {
                            object.setAckOC(AckOC.Factory.parse(reader));

                            reader.next();
                        }
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                                "ackSC").equals(reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if ("true".equals(nillableValue) ||
                                "1".equals(nillableValue)) {
                            object.setAckSC(null);
                            reader.next();

                            reader.next();
                        } else {
                            object.setAckSC(AckSC.Factory.parse(reader));

                            reader.next();
                        }
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                                "id").equals(reader.getName())) {
                        // Process the array and step past its final element's end.
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if ("true".equals(nillableValue) ||
                                "1".equals(nillableValue)) {
                            list3.add(String.valueOf(
                                    java.lang.Integer.MIN_VALUE));

                            reader.next();
                        } else {
                            list3.add(reader.getElementText());
                        }

                        //loop until we find a start element that is not part of this array
                        boolean loopDone3 = false;

                        while (!loopDone3) {
                            // Ensure we are at the EndElement
                            while (!reader.isEndElement()) {
                                reader.next();
                            }

                            // Step out of this element
                            reader.next();

                            // Step to next element event.
                            while (!reader.isStartElement() &&
                                    !reader.isEndElement())
                                reader.next();

                            if (reader.isEndElement()) {
                                //two continuous end elements means we are exiting the xml structure
                                loopDone3 = true;
                            } else {
                                if (new javax.xml.namespace.QName(
                                            "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                                            "id").equals(reader.getName())) {
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                            "nil");

                                    if ("true".equals(nillableValue) ||
                                            "1".equals(nillableValue)) {
                                        list3.add(String.valueOf(
                                                java.lang.Integer.MIN_VALUE));

                                        reader.next();
                                    } else {
                                        list3.add(reader.getElementText());
                                    }
                                } else {
                                    loopDone3 = true;
                                }
                            }
                        }

                        // call the converter utility  to convert and set the array
                        object.setId((int[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                int.class, list3));
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                                "oC").equals(reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if ("true".equals(nillableValue) ||
                                "1".equals(nillableValue)) {
                            object.setOC(null);
                            reader.next();

                            reader.next();
                        } else {
                            object.setOC(AckOC.Factory.parse(reader));

                            reader.next();
                        }
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                                "sC").equals(reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if ("true".equals(nillableValue) ||
                                "1".equals(nillableValue)) {
                            object.setSC(null);
                            reader.next();

                            reader.next();
                        } else {
                            object.setSC(AckSC.Factory.parse(reader));

                            reader.next();
                        }
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class
    }

    public static class ExtensionMapper {
        public static java.lang.Object getTypeObject(
            java.lang.String namespaceURI, java.lang.String typeName,
            javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
            if ("http://exceptions.wrapper.move.lip6.fr/xsd".equals(
                        namespaceURI) && "GException".equals(typeName)) {
                return GException.Factory.parse(reader);
            }

            if ("http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd".equals(
                        namespaceURI) && "AckOC".equals(typeName)) {
                return AckOC.Factory.parse(reader);
            }

            if ("http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd".equals(
                        namespaceURI) && "Unauthentification".equals(typeName)) {
                return Unauthentification.Factory.parse(reader);
            }

            if ("http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd".equals(
                        namespaceURI) && "AckSC".equals(typeName)) {
                return AckSC.Factory.parse(reader);
            }

            if ("http://ws.wrapper.move.lip6.fr".equals(namespaceURI) &&
                    "Exception".equals(typeName)) {
                return Exception.Factory.parse(reader);
            }

            if ("http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd".equals(
                        namespaceURI) && "Authentification".equals(typeName)) {
                return Authentification.Factory.parse(reader);
            }

            throw new org.apache.axis2.databinding.ADBException(
                "Unsupported type " + namespaceURI + " " + typeName);
        }
    }

    public static class Connect implements org.apache.axis2.databinding.ADBBean {
        public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://ws.wrapper.move.lip6.fr",
                "connect", "ns1");

        /**
         * field for Login
         */
        protected java.lang.String localLogin;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localLoginTracker = false;

        /**
         * field for Mdp
         */
        protected java.lang.String localMdp;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localMdpTracker = false;

        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals("http://ws.wrapper.move.lip6.fr")) {
                return "ns1";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getLogin() {
            return localLogin;
        }

        /**
         * Auto generated setter method
         * @param param Login
         */
        public void setLogin(java.lang.String param) {
            if (param != null) {
                //update the setting tracker
                localLoginTracker = true;
            } else {
                localLoginTracker = true;
            }

            this.localLogin = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getMdp() {
            return localMdp;
        }

        /**
         * Auto generated setter method
         * @param param Mdp
         */
        public void setMdp(java.lang.String param) {
            if (param != null) {
                //update the setting tracker
                localMdpTracker = true;
            } else {
                localMdpTracker = true;
            }

            this.localMdp = param;
        }

        /**
         * isReaderMTOMAware
         * @return true if the reader supports MTOM
         */
        public static boolean isReaderMTOMAware(
            javax.xml.stream.XMLStreamReader reader) {
            boolean isReaderMTOMAware = false;

            try {
                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                            org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
            } catch (java.lang.IllegalArgumentException e) {
                isReaderMTOMAware = false;
            }

            return isReaderMTOMAware;
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                    MY_QNAME) {
                    public void serialize(
                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException {
                        Connect.this.serialize(MY_QNAME, factory, xmlWriter);
                    }
                };

            return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME,
                factory, dataSource);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();

            if (namespace != null) {
                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                if (writerPrefix != null) {
                    xmlWriter.writeStartElement(namespace,
                        parentQName.getLocalPart());
                } else {
                    if (prefix == null) {
                        prefix = generatePrefix(namespace);
                    }

                    xmlWriter.writeStartElement(prefix,
                        parentQName.getLocalPart(), namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }
            } else {
                xmlWriter.writeStartElement(parentQName.getLocalPart());
            }

            if (localLoginTracker) {
                namespace = "http://ws.wrapper.move.lip6.fr";

                if (!namespace.equals("")) {
                    prefix = xmlWriter.getPrefix(namespace);

                    if (prefix == null) {
                        prefix = generatePrefix(namespace);

                        xmlWriter.writeStartElement(prefix, "login", namespace);
                        xmlWriter.writeNamespace(prefix, namespace);
                        xmlWriter.setPrefix(prefix, namespace);
                    } else {
                        xmlWriter.writeStartElement(namespace, "login");
                    }
                } else {
                    xmlWriter.writeStartElement("login");
                }

                if (localLogin == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localLogin);
                }

                xmlWriter.writeEndElement();
            }

            if (localMdpTracker) {
                namespace = "http://ws.wrapper.move.lip6.fr";

                if (!namespace.equals("")) {
                    prefix = xmlWriter.getPrefix(namespace);

                    if (prefix == null) {
                        prefix = generatePrefix(namespace);

                        xmlWriter.writeStartElement(prefix, "mdp", namespace);
                        xmlWriter.writeNamespace(prefix, namespace);
                        xmlWriter.setPrefix(prefix, namespace);
                    } else {
                        xmlWriter.writeStartElement(namespace, "mdp");
                    }
                } else {
                    xmlWriter.writeStartElement("mdp");
                }

                if (localMdp == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localMdp);
                }

                xmlWriter.writeEndElement();
            }

            xmlWriter.writeEndElement();
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(
            javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException {
            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            if (localLoginTracker) {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ws.wrapper.move.lip6.fr", "login"));

                elementList.add((localLogin == null) ? null
                                                     : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localLogin));
            }

            if (localMdpTracker) {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ws.wrapper.move.lip6.fr", "mdp"));

                elementList.add((localMdp == null) ? null
                                                   : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localMdp));
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                elementList.toArray(), attribList.toArray());
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static Connect parse(javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                Connect object = new Connect();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"connect".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (Connect) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://ws.wrapper.move.lip6.fr", "login").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setLogin(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://ws.wrapper.move.lip6.fr", "mdp").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setMdp(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class
    }

    public static class AckOC implements org.apache.axis2.databinding.ADBBean {
        /**
         * field for Major
         */
        protected int localMajor;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localMajorTracker = false;

        /**
         * field for Minor
         */
        protected int localMinor;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localMinorTracker = false;

        /* This type was generated from the piece of schema that had
           name = AckOC
           Namespace URI = http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd
           Namespace Prefix = ns3
         */
        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals(
                        "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd")) {
                return "ns3";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Auto generated getter method
         * @return int
         */
        public int getMajor() {
            return localMajor;
        }

        /**
         * Auto generated setter method
         * @param param Major
         */
        public void setMajor(int param) {
            // setting primitive attribute tracker to true
            if (param == java.lang.Integer.MIN_VALUE) {
                localMajorTracker = false;
            } else {
                localMajorTracker = true;
            }

            this.localMajor = param;
        }

        /**
         * Auto generated getter method
         * @return int
         */
        public int getMinor() {
            return localMinor;
        }

        /**
         * Auto generated setter method
         * @param param Minor
         */
        public void setMinor(int param) {
            // setting primitive attribute tracker to true
            if (param == java.lang.Integer.MIN_VALUE) {
                localMinorTracker = false;
            } else {
                localMinorTracker = true;
            }

            this.localMinor = param;
        }

        /**
         * isReaderMTOMAware
         * @return true if the reader supports MTOM
         */
        public static boolean isReaderMTOMAware(
            javax.xml.stream.XMLStreamReader reader) {
            boolean isReaderMTOMAware = false;

            try {
                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                            org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
            } catch (java.lang.IllegalArgumentException e) {
                isReaderMTOMAware = false;
            }

            return isReaderMTOMAware;
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                    parentQName) {
                    public void serialize(
                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException {
                        AckOC.this.serialize(parentQName, factory, xmlWriter);
                    }
                };

            return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName,
                factory, dataSource);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();

            if (namespace != null) {
                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                if (writerPrefix != null) {
                    xmlWriter.writeStartElement(namespace,
                        parentQName.getLocalPart());
                } else {
                    if (prefix == null) {
                        prefix = generatePrefix(namespace);
                    }

                    xmlWriter.writeStartElement(prefix,
                        parentQName.getLocalPart(), namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }
            } else {
                xmlWriter.writeStartElement(parentQName.getLocalPart());
            }

            if (localMajorTracker) {
                namespace = "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd";

                if (!namespace.equals("")) {
                    prefix = xmlWriter.getPrefix(namespace);

                    if (prefix == null) {
                        prefix = generatePrefix(namespace);

                        xmlWriter.writeStartElement(prefix, "major", namespace);
                        xmlWriter.writeNamespace(prefix, namespace);
                        xmlWriter.setPrefix(prefix, namespace);
                    } else {
                        xmlWriter.writeStartElement(namespace, "major");
                    }
                } else {
                    xmlWriter.writeStartElement("major");
                }

                if (localMajor == java.lang.Integer.MIN_VALUE) {
                    throw new org.apache.axis2.databinding.ADBException(
                        "major cannot be null!!");
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localMajor));
                }

                xmlWriter.writeEndElement();
            }

            if (localMinorTracker) {
                namespace = "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd";

                if (!namespace.equals("")) {
                    prefix = xmlWriter.getPrefix(namespace);

                    if (prefix == null) {
                        prefix = generatePrefix(namespace);

                        xmlWriter.writeStartElement(prefix, "minor", namespace);
                        xmlWriter.writeNamespace(prefix, namespace);
                        xmlWriter.setPrefix(prefix, namespace);
                    } else {
                        xmlWriter.writeStartElement(namespace, "minor");
                    }
                } else {
                    xmlWriter.writeStartElement("minor");
                }

                if (localMinor == java.lang.Integer.MIN_VALUE) {
                    throw new org.apache.axis2.databinding.ADBException(
                        "minor cannot be null!!");
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localMinor));
                }

                xmlWriter.writeEndElement();
            }

            xmlWriter.writeEndElement();
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(
            javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException {
            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            if (localMajorTracker) {
                elementList.add(new javax.xml.namespace.QName(
                        "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                        "major"));

                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localMajor));
            }

            if (localMinorTracker) {
                elementList.add(new javax.xml.namespace.QName(
                        "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                        "minor"));

                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localMinor));
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                elementList.toArray(), attribList.toArray());
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static AckOC parse(javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                AckOC object = new AckOC();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"AckOC".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (AckOC) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                                "major").equals(reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setMajor(org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        object.setMajor(java.lang.Integer.MIN_VALUE);
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                                "minor").equals(reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setMinor(org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        object.setMinor(java.lang.Integer.MIN_VALUE);
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class
    }

    public static class Main implements org.apache.axis2.databinding.ADBBean {
        public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://ws.wrapper.move.lip6.fr",
                "main", "ns1");

        /**
         * field for Args
         * This was an Array!
         */
        protected java.lang.String[] localArgs;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localArgsTracker = false;

        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals("http://ws.wrapper.move.lip6.fr")) {
                return "ns1";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Auto generated getter method
         * @return java.lang.String[]
         */
        public java.lang.String[] getArgs() {
            return localArgs;
        }

        /**
         * validate the array for Args
         */
        protected void validateArgs(java.lang.String[] param) {
        }

        /**
         * Auto generated setter method
         * @param param Args
         */
        public void setArgs(java.lang.String[] param) {
            validateArgs(param);

            if (param != null) {
                //update the setting tracker
                localArgsTracker = true;
            } else {
                localArgsTracker = true;
            }

            this.localArgs = param;
        }

        /**
         * Auto generated add method for the array for convenience
         * @param param java.lang.String
         */
        public void addArgs(java.lang.String param) {
            if (localArgs == null) {
                localArgs = new java.lang.String[] {  };
            }

            //update the setting tracker
            localArgsTracker = true;

            java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localArgs);
            list.add(param);
            this.localArgs = (java.lang.String[]) list.toArray(new java.lang.String[list.size()]);
        }

        /**
         * isReaderMTOMAware
         * @return true if the reader supports MTOM
         */
        public static boolean isReaderMTOMAware(
            javax.xml.stream.XMLStreamReader reader) {
            boolean isReaderMTOMAware = false;

            try {
                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                            org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
            } catch (java.lang.IllegalArgumentException e) {
                isReaderMTOMAware = false;
            }

            return isReaderMTOMAware;
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                    MY_QNAME) {
                    public void serialize(
                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException {
                        Main.this.serialize(MY_QNAME, factory, xmlWriter);
                    }
                };

            return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME,
                factory, dataSource);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();

            if (namespace != null) {
                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                if (writerPrefix != null) {
                    xmlWriter.writeStartElement(namespace,
                        parentQName.getLocalPart());
                } else {
                    if (prefix == null) {
                        prefix = generatePrefix(namespace);
                    }

                    xmlWriter.writeStartElement(prefix,
                        parentQName.getLocalPart(), namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }
            } else {
                xmlWriter.writeStartElement(parentQName.getLocalPart());
            }

            if (localArgsTracker) {
                if (localArgs != null) {
                    namespace = "http://ws.wrapper.move.lip6.fr";

                    boolean emptyNamespace = (namespace == null) ||
                        (namespace.length() == 0);
                    prefix = emptyNamespace ? null
                                            : xmlWriter.getPrefix(namespace);

                    for (int i = 0; i < localArgs.length; i++) {
                        if (localArgs[i] != null) {
                            if (!emptyNamespace) {
                                if (prefix == null) {
                                    java.lang.String prefix2 = generatePrefix(namespace);

                                    xmlWriter.writeStartElement(prefix2,
                                        "args", namespace);
                                    xmlWriter.writeNamespace(prefix2, namespace);
                                    xmlWriter.setPrefix(prefix2, namespace);
                                } else {
                                    xmlWriter.writeStartElement(namespace,
                                        "args");
                                }
                            } else {
                                xmlWriter.writeStartElement("args");
                            }

                            xmlWriter.writeCharacters(localArgs[i]);

                            xmlWriter.writeEndElement();
                        } else {
                            // write null attribute
                            namespace = "http://ws.wrapper.move.lip6.fr";

                            if (!namespace.equals("")) {
                                prefix = xmlWriter.getPrefix(namespace);

                                if (prefix == null) {
                                    prefix = generatePrefix(namespace);

                                    xmlWriter.writeStartElement(prefix, "args",
                                        namespace);
                                    xmlWriter.writeNamespace(prefix, namespace);
                                    xmlWriter.setPrefix(prefix, namespace);
                                } else {
                                    xmlWriter.writeStartElement(namespace,
                                        "args");
                                }
                            } else {
                                xmlWriter.writeStartElement("args");
                            }

                            writeAttribute("xsi",
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "nil", "1", xmlWriter);
                            xmlWriter.writeEndElement();
                        }
                    }
                } else {
                    // write the null attribute
                    // write null attribute
                    java.lang.String namespace2 = "http://ws.wrapper.move.lip6.fr";

                    if (!namespace2.equals("")) {
                        java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                        if (prefix2 == null) {
                            prefix2 = generatePrefix(namespace2);

                            xmlWriter.writeStartElement(prefix2, "args",
                                namespace2);
                            xmlWriter.writeNamespace(prefix2, namespace2);
                            xmlWriter.setPrefix(prefix2, namespace2);
                        } else {
                            xmlWriter.writeStartElement(namespace2, "args");
                        }
                    } else {
                        xmlWriter.writeStartElement("args");
                    }

                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                    xmlWriter.writeEndElement();
                }
            }

            xmlWriter.writeEndElement();
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(
            javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException {
            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            if (localArgsTracker) {
                if (localArgs != null) {
                    for (int i = 0; i < localArgs.length; i++) {
                        if (localArgs[i] != null) {
                            elementList.add(new javax.xml.namespace.QName(
                                    "http://ws.wrapper.move.lip6.fr", "args"));
                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    localArgs[i]));
                        } else {
                            elementList.add(new javax.xml.namespace.QName(
                                    "http://ws.wrapper.move.lip6.fr", "args"));
                            elementList.add(null);
                        }
                    }
                } else {
                    elementList.add(new javax.xml.namespace.QName(
                            "http://ws.wrapper.move.lip6.fr", "args"));
                    elementList.add(null);
                }
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                elementList.toArray(), attribList.toArray());
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static Main parse(javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                Main object = new Main();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"main".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (Main) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    java.util.ArrayList list1 = new java.util.ArrayList();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://ws.wrapper.move.lip6.fr", "args").equals(
                                reader.getName())) {
                        // Process the array and step past its final element's end.
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if ("true".equals(nillableValue) ||
                                "1".equals(nillableValue)) {
                            list1.add(null);

                            reader.next();
                        } else {
                            list1.add(reader.getElementText());
                        }

                        //loop until we find a start element that is not part of this array
                        boolean loopDone1 = false;

                        while (!loopDone1) {
                            // Ensure we are at the EndElement
                            while (!reader.isEndElement()) {
                                reader.next();
                            }

                            // Step out of this element
                            reader.next();

                            // Step to next element event.
                            while (!reader.isStartElement() &&
                                    !reader.isEndElement())
                                reader.next();

                            if (reader.isEndElement()) {
                                //two continuous end elements means we are exiting the xml structure
                                loopDone1 = true;
                            } else {
                                if (new javax.xml.namespace.QName(
                                            "http://ws.wrapper.move.lip6.fr",
                                            "args").equals(reader.getName())) {
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                            "nil");

                                    if ("true".equals(nillableValue) ||
                                            "1".equals(nillableValue)) {
                                        list1.add(null);

                                        reader.next();
                                    } else {
                                        list1.add(reader.getElementText());
                                    }
                                } else {
                                    loopDone1 = true;
                                }
                            }
                        }

                        // call the converter utility  to convert and set the array
                        object.setArgs((java.lang.String[]) list1.toArray(
                                new java.lang.String[list1.size()]));
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class
    }

    public static class AckSC implements org.apache.axis2.databinding.ADBBean {
        /**
         * field for HostInformation
         */
        protected java.lang.String localHostInformation;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localHostInformationTracker = false;

        /* This type was generated from the piece of schema that had
           name = AckSC
           Namespace URI = http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd
           Namespace Prefix = ns3
         */
        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals(
                        "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd")) {
                return "ns3";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getHostInformation() {
            return localHostInformation;
        }

        /**
         * Auto generated setter method
         * @param param HostInformation
         */
        public void setHostInformation(java.lang.String param) {
            if (param != null) {
                //update the setting tracker
                localHostInformationTracker = true;
            } else {
                localHostInformationTracker = true;
            }

            this.localHostInformation = param;
        }

        /**
         * isReaderMTOMAware
         * @return true if the reader supports MTOM
         */
        public static boolean isReaderMTOMAware(
            javax.xml.stream.XMLStreamReader reader) {
            boolean isReaderMTOMAware = false;

            try {
                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                            org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
            } catch (java.lang.IllegalArgumentException e) {
                isReaderMTOMAware = false;
            }

            return isReaderMTOMAware;
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                    parentQName) {
                    public void serialize(
                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException {
                        AckSC.this.serialize(parentQName, factory, xmlWriter);
                    }
                };

            return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName,
                factory, dataSource);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();

            if (namespace != null) {
                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                if (writerPrefix != null) {
                    xmlWriter.writeStartElement(namespace,
                        parentQName.getLocalPart());
                } else {
                    if (prefix == null) {
                        prefix = generatePrefix(namespace);
                    }

                    xmlWriter.writeStartElement(prefix,
                        parentQName.getLocalPart(), namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }
            } else {
                xmlWriter.writeStartElement(parentQName.getLocalPart());
            }

            if (localHostInformationTracker) {
                namespace = "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd";

                if (!namespace.equals("")) {
                    prefix = xmlWriter.getPrefix(namespace);

                    if (prefix == null) {
                        prefix = generatePrefix(namespace);

                        xmlWriter.writeStartElement(prefix, "hostInformation",
                            namespace);
                        xmlWriter.writeNamespace(prefix, namespace);
                        xmlWriter.setPrefix(prefix, namespace);
                    } else {
                        xmlWriter.writeStartElement(namespace, "hostInformation");
                    }
                } else {
                    xmlWriter.writeStartElement("hostInformation");
                }

                if (localHostInformation == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localHostInformation);
                }

                xmlWriter.writeEndElement();
            }

            xmlWriter.writeEndElement();
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(
            javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException {
            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            if (localHostInformationTracker) {
                elementList.add(new javax.xml.namespace.QName(
                        "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                        "hostInformation"));

                elementList.add((localHostInformation == null) ? null
                                                               : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localHostInformation));
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                elementList.toArray(), attribList.toArray());
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static AckSC parse(javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                AckSC object = new AckSC();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"AckSC".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (AckSC) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://connexionHandler.output.camixml.wrapper.move.lip6.fr/xsd",
                                "hostInformation").equals(reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setHostInformation(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class
    }

    public static class ConnectResponse implements org.apache.axis2.databinding.ADBBean {
        public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://ws.wrapper.move.lip6.fr",
                "connectResponse", "ns1");

        /**
         * field for _return
         */
        protected Authentification local_return;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean local_returnTracker = false;

        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals("http://ws.wrapper.move.lip6.fr")) {
                return "ns1";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Auto generated getter method
         * @return Authentification
         */
        public Authentification get_return() {
            return local_return;
        }

        /**
         * Auto generated setter method
         * @param param _return
         */
        public void set_return(Authentification param) {
            if (param != null) {
                //update the setting tracker
                local_returnTracker = true;
            } else {
                local_returnTracker = true;
            }

            this.local_return = param;
        }

        /**
         * isReaderMTOMAware
         * @return true if the reader supports MTOM
         */
        public static boolean isReaderMTOMAware(
            javax.xml.stream.XMLStreamReader reader) {
            boolean isReaderMTOMAware = false;

            try {
                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                            org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
            } catch (java.lang.IllegalArgumentException e) {
                isReaderMTOMAware = false;
            }

            return isReaderMTOMAware;
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                    MY_QNAME) {
                    public void serialize(
                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException {
                        ConnectResponse.this.serialize(MY_QNAME, factory,
                            xmlWriter);
                    }
                };

            return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME,
                factory, dataSource);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();

            if (namespace != null) {
                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                if (writerPrefix != null) {
                    xmlWriter.writeStartElement(namespace,
                        parentQName.getLocalPart());
                } else {
                    if (prefix == null) {
                        prefix = generatePrefix(namespace);
                    }

                    xmlWriter.writeStartElement(prefix,
                        parentQName.getLocalPart(), namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }
            } else {
                xmlWriter.writeStartElement(parentQName.getLocalPart());
            }

            if (local_returnTracker) {
                if (local_return == null) {
                    java.lang.String namespace2 = "http://ws.wrapper.move.lip6.fr";

                    if (!namespace2.equals("")) {
                        java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                        if (prefix2 == null) {
                            prefix2 = generatePrefix(namespace2);

                            xmlWriter.writeStartElement(prefix2, "return",
                                namespace2);
                            xmlWriter.writeNamespace(prefix2, namespace2);
                            xmlWriter.setPrefix(prefix2, namespace2);
                        } else {
                            xmlWriter.writeStartElement(namespace2, "return");
                        }
                    } else {
                        xmlWriter.writeStartElement("return");
                    }

                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                    xmlWriter.writeEndElement();
                } else {
                    local_return.serialize(new javax.xml.namespace.QName(
                            "http://ws.wrapper.move.lip6.fr", "return"),
                        factory, xmlWriter);
                }
            }

            xmlWriter.writeEndElement();
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(
            javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException {
            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            if (local_returnTracker) {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ws.wrapper.move.lip6.fr", "return"));

                elementList.add((local_return == null) ? null : local_return);
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                elementList.toArray(), attribList.toArray());
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static ConnectResponse parse(
                javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                ConnectResponse object = new ConnectResponse();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"connectResponse".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (ConnectResponse) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://ws.wrapper.move.lip6.fr", "return").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if ("true".equals(nillableValue) ||
                                "1".equals(nillableValue)) {
                            object.set_return(null);
                            reader.next();

                            reader.next();
                        } else {
                            object.set_return(Authentification.Factory.parse(
                                    reader));

                            reader.next();
                        }
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class
    }

    public static class Exception implements org.apache.axis2.databinding.ADBBean {
        /**
         * field for Exception
         */
        protected org.apache.axiom.om.OMElement localException;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localExceptionTracker = false;

        /* This type was generated from the piece of schema that had
           name = Exception
           Namespace URI = http://ws.wrapper.move.lip6.fr
           Namespace Prefix = ns1
         */
        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals("http://ws.wrapper.move.lip6.fr")) {
                return "ns1";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Auto generated getter method
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getException() {
            return localException;
        }

        /**
         * Auto generated setter method
         * @param param Exception
         */
        public void setException(org.apache.axiom.om.OMElement param) {
            if (param != null) {
                //update the setting tracker
                localExceptionTracker = true;
            } else {
                localExceptionTracker = true;
            }

            this.localException = param;
        }

        /**
         * isReaderMTOMAware
         * @return true if the reader supports MTOM
         */
        public static boolean isReaderMTOMAware(
            javax.xml.stream.XMLStreamReader reader) {
            boolean isReaderMTOMAware = false;

            try {
                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                            org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
            } catch (java.lang.IllegalArgumentException e) {
                isReaderMTOMAware = false;
            }

            return isReaderMTOMAware;
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                    parentQName) {
                    public void serialize(
                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException {
                        Exception.this.serialize(parentQName, factory, xmlWriter);
                    }
                };

            return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName,
                factory, dataSource);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();

            if (namespace != null) {
                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                if (writerPrefix != null) {
                    xmlWriter.writeStartElement(namespace,
                        parentQName.getLocalPart());
                } else {
                    if (prefix == null) {
                        prefix = generatePrefix(namespace);
                    }

                    xmlWriter.writeStartElement(prefix,
                        parentQName.getLocalPart(), namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }
            } else {
                xmlWriter.writeStartElement(parentQName.getLocalPart());
            }

            if (localExceptionTracker) {
                if (localException != null) {
                    // write null attribute
                    java.lang.String namespace2 = "http://ws.wrapper.move.lip6.fr";

                    if (!namespace2.equals("")) {
                        java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                        if (prefix2 == null) {
                            prefix2 = generatePrefix(namespace2);

                            xmlWriter.writeStartElement(prefix2, "Exception",
                                namespace2);
                            xmlWriter.writeNamespace(prefix2, namespace2);
                            xmlWriter.setPrefix(prefix2, namespace2);
                        } else {
                            xmlWriter.writeStartElement(namespace2, "Exception");
                        }
                    } else {
                        xmlWriter.writeStartElement("Exception");
                    }

                    localException.serialize(xmlWriter);
                    xmlWriter.writeEndElement();
                } else {
                    // write null attribute
                    java.lang.String namespace2 = "http://ws.wrapper.move.lip6.fr";

                    if (!namespace2.equals("")) {
                        java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                        if (prefix2 == null) {
                            prefix2 = generatePrefix(namespace2);

                            xmlWriter.writeStartElement(prefix2, "Exception",
                                namespace2);
                            xmlWriter.writeNamespace(prefix2, namespace2);
                            xmlWriter.setPrefix(prefix2, namespace2);
                        } else {
                            xmlWriter.writeStartElement(namespace2, "Exception");
                        }
                    } else {
                        xmlWriter.writeStartElement("Exception");
                    }

                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                    xmlWriter.writeEndElement();
                }
            }

            xmlWriter.writeEndElement();
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(
            javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException {
            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            if (localExceptionTracker) {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ws.wrapper.move.lip6.fr", "Exception"));

                elementList.add((localException == null) ? null : localException);
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                elementList.toArray(), attribList.toArray());
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static Exception parse(
                javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                Exception object = new Exception();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"Exception".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (Exception) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://ws.wrapper.move.lip6.fr", "Exception").equals(
                                reader.getName())) {
                        boolean loopDone1 = false;
                        javax.xml.namespace.QName startQname1 = new javax.xml.namespace.QName("http://ws.wrapper.move.lip6.fr",
                                "Exception");

                        while (!loopDone1) {
                            if (reader.isStartElement() &&
                                    startQname1.equals(reader.getName())) {
                                loopDone1 = true;
                            } else {
                                reader.next();
                            }
                        }

                        // We need to wrap the reader so that it produces a fake START_DOCUEMENT event
                        // this is needed by the builder classes
                        org.apache.axis2.databinding.utils.NamedStaxOMBuilder builder1 =
                            new org.apache.axis2.databinding.utils.NamedStaxOMBuilder(new org.apache.axis2.util.StreamWrapper(
                                    reader), startQname1);
                        object.setException(builder1.getOMElement()
                                                    .getFirstElement());

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class
    }

    public static class DisconnectResponse implements org.apache.axis2.databinding.ADBBean {
        public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://ws.wrapper.move.lip6.fr",
                "disconnectResponse", "ns1");

        /**
         * field for _return
         */
        protected Unauthentification local_return;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean local_returnTracker = false;

        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals("http://ws.wrapper.move.lip6.fr")) {
                return "ns1";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Auto generated getter method
         * @return Unauthentification
         */
        public Unauthentification get_return() {
            return local_return;
        }

        /**
         * Auto generated setter method
         * @param param _return
         */
        public void set_return(Unauthentification param) {
            if (param != null) {
                //update the setting tracker
                local_returnTracker = true;
            } else {
                local_returnTracker = true;
            }

            this.local_return = param;
        }

        /**
         * isReaderMTOMAware
         * @return true if the reader supports MTOM
         */
        public static boolean isReaderMTOMAware(
            javax.xml.stream.XMLStreamReader reader) {
            boolean isReaderMTOMAware = false;

            try {
                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                            org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
            } catch (java.lang.IllegalArgumentException e) {
                isReaderMTOMAware = false;
            }

            return isReaderMTOMAware;
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                    MY_QNAME) {
                    public void serialize(
                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException {
                        DisconnectResponse.this.serialize(MY_QNAME, factory,
                            xmlWriter);
                    }
                };

            return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME,
                factory, dataSource);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();

            if (namespace != null) {
                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                if (writerPrefix != null) {
                    xmlWriter.writeStartElement(namespace,
                        parentQName.getLocalPart());
                } else {
                    if (prefix == null) {
                        prefix = generatePrefix(namespace);
                    }

                    xmlWriter.writeStartElement(prefix,
                        parentQName.getLocalPart(), namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }
            } else {
                xmlWriter.writeStartElement(parentQName.getLocalPart());
            }

            if (local_returnTracker) {
                if (local_return == null) {
                    java.lang.String namespace2 = "http://ws.wrapper.move.lip6.fr";

                    if (!namespace2.equals("")) {
                        java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                        if (prefix2 == null) {
                            prefix2 = generatePrefix(namespace2);

                            xmlWriter.writeStartElement(prefix2, "return",
                                namespace2);
                            xmlWriter.writeNamespace(prefix2, namespace2);
                            xmlWriter.setPrefix(prefix2, namespace2);
                        } else {
                            xmlWriter.writeStartElement(namespace2, "return");
                        }
                    } else {
                        xmlWriter.writeStartElement("return");
                    }

                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                    xmlWriter.writeEndElement();
                } else {
                    local_return.serialize(new javax.xml.namespace.QName(
                            "http://ws.wrapper.move.lip6.fr", "return"),
                        factory, xmlWriter);
                }
            }

            xmlWriter.writeEndElement();
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(
            javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException {
            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            if (local_returnTracker) {
                elementList.add(new javax.xml.namespace.QName(
                        "http://ws.wrapper.move.lip6.fr", "return"));

                elementList.add((local_return == null) ? null : local_return);
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                elementList.toArray(), attribList.toArray());
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static DisconnectResponse parse(
                javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                DisconnectResponse object = new DisconnectResponse();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"disconnectResponse".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (DisconnectResponse) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://ws.wrapper.move.lip6.fr", "return").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if ("true".equals(nillableValue) ||
                                "1".equals(nillableValue)) {
                            object.set_return(null);
                            reader.next();

                            reader.next();
                        } else {
                            object.set_return(Unauthentification.Factory.parse(
                                    reader));

                            reader.next();
                        }
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class
    }
}
