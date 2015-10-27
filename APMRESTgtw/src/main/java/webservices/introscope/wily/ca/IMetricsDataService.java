/**
 * IMetricsDataService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservices.introscope.wily.ca;

public interface IMetricsDataService extends java.rmi.Remote {
    public webservices.introscope.wily.ca.TimesliceGroupedMetricData[] getMetricData(java.lang.String agentRegex, java.lang.String metricRegex, java.util.Calendar startTime, java.util.Calendar endTime, int dataFrequency) throws java.rmi.RemoteException, com.wily.introscope.server.webservicesapi.IntroscopeWebServicesException;
    public webservices.introscope.wily.ca.TimeslicedMetricDataPerAgentMetric[] getBulkMetricData(webservices.introscope.wily.ca.AgentMetricName[] allMetrics, java.util.Calendar startTime, java.util.Calendar endTime, int dataFrequency) throws java.rmi.RemoteException, com.wily.introscope.server.webservicesapi.IntroscopeWebServicesException;
    public webservices.introscope.wily.ca.TimeslicedResultSetMetricData[] getLiveMetricData(java.lang.String agentRegex, java.lang.String metricPrefix) throws java.rmi.RemoteException, com.wily.introscope.server.webservicesapi.IntroscopeWebServicesException;
    public webservices.introscope.wily.ca.TimesliceGroupedMetricData[] getTopNMetricData(java.lang.String agentRegex, java.lang.String metricRegex, java.util.Calendar startTime, java.util.Calendar endTime, int dataFrequency, int topNCount, boolean decreasingOrder) throws java.rmi.RemoteException, com.wily.introscope.server.webservicesapi.IntroscopeWebServicesException;
}
