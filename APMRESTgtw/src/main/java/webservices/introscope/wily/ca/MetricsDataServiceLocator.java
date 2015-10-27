/**
 * MetricsDataServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservices.introscope.wily.ca;

public class MetricsDataServiceLocator extends org.apache.axis.client.Service implements webservices.introscope.wily.ca.MetricsDataService {

    public MetricsDataServiceLocator() {
    }


    public MetricsDataServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MetricsDataServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MetricsDataService
    private java.lang.String MetricsDataService_address = "http://192.168.127.131:8081/introscope-web-services/services/MetricsDataService";

    public java.lang.String getMetricsDataServiceAddress() {
        return MetricsDataService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MetricsDataServiceWSDDServiceName = "MetricsDataService";

    public java.lang.String getMetricsDataServiceWSDDServiceName() {
        return MetricsDataServiceWSDDServiceName;
    }

    public void setMetricsDataServiceWSDDServiceName(java.lang.String name) {
        MetricsDataServiceWSDDServiceName = name;
    }

    public webservices.introscope.wily.ca.IMetricsDataService getMetricsDataService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MetricsDataService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMetricsDataService(endpoint);
    }

    public webservices.introscope.wily.ca.IMetricsDataService getMetricsDataService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            webservices.introscope.wily.ca.MetricsDataServiceSoapBindingStub _stub = new webservices.introscope.wily.ca.MetricsDataServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getMetricsDataServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMetricsDataServiceEndpointAddress(java.lang.String address) {
        MetricsDataService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (webservices.introscope.wily.ca.IMetricsDataService.class.isAssignableFrom(serviceEndpointInterface)) {
                webservices.introscope.wily.ca.MetricsDataServiceSoapBindingStub _stub = new webservices.introscope.wily.ca.MetricsDataServiceSoapBindingStub(new java.net.URL(MetricsDataService_address), this);
                _stub.setPortName(getMetricsDataServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("MetricsDataService".equals(inputPortName)) {
            return getMetricsDataService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:ca.wily.introscope.webservices", "MetricsDataService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:ca.wily.introscope.webservices", "MetricsDataService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MetricsDataService".equals(portName)) {
            setMetricsDataServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
