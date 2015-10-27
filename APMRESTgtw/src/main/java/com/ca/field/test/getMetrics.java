package com.ca.field.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.xml.rpc.ServiceException;
import com.ca.field.json.JSONArray;
import com.ca.field.json.JSONException;
import com.ca.field.json.JSONObject;
import webservices.introscope.wily.ca.MetricData;
import webservices.introscope.wily.ca.MetricsDataServiceLocator;
import webservices.introscope.wily.ca.MetricsDataServiceSoapBindingStub;
import webservices.introscope.wily.ca.TimesliceGroupedMetricData;

public class getMetrics  {
//	String endpoint = "http://192.168.127.131:8081/introscope-web-services/services/";
public static String get(String endpoint,String userId,String password, String agent, String metric, String end, int interval) throws JSONException {

		JSONObject agentname = new JSONObject();
		JSONArray returnArray = new JSONArray();
		JSONObject returnJSON = new JSONObject();
		
		Map<String, String> metricvalues = new HashMap<String, String>();
		Map<String, Map<String,String>> metricnames = new HashMap<String, Map<String, String>>();
		Map<String, Map<String, Map<String,String>>> outputMap = new HashMap<String,Map<String, Map<String, String>>>();
		
		List<Map<String, String>> metricvaluesList = new ArrayList<Map<String, String>>();
		
		List<String[]> tempArray = new ArrayList<String[]>();
		
//		String returnJSON="\"APM return\": [";
		///List<Metrics> output = new ArrayList<>();

		//Map<String, Integer> test = new HashMap<String, Integer>();
		try
		{
			if(Main.debugFlag) System.out.println("Getting SOAP connection to "+endpoint);

				MetricsDataServiceLocator locator = new MetricsDataServiceLocator();
				locator.setMetricsDataServiceEndpointAddress(endpoint);

			
			MetricsDataServiceSoapBindingStub stub = new MetricsDataServiceSoapBindingStub(locator);
						
			try {
				stub = (MetricsDataServiceSoapBindingStub) locator.getMetricsDataService();
			} catch (ServiceException e) {
				System.out.println("ERROR - Tried creating a locator - failed");
				e.printStackTrace();
			}
			stub.setUsername(userId);
			stub.setPassword(password);
			
			long calendarinmil = 0;
			Calendar starttime = new GregorianCalendar();
	        Calendar endtime = new GregorianCalendar();
	        
			if (end.equals("now"))
			{
				calendarinmil = Calendar.getInstance().getTimeInMillis();
				starttime.setTimeInMillis(calendarinmil - interval*1000);
		        endtime.setTimeInMillis(calendarinmil);
			}
			else
			{
			//	calendarinmil = Calendar.getInstance().getTimeInMillis();
				SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy HH:mm:ss", Locale.getDefault());
			//	SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.getDefault());				
				endtime.setTime(sdf.parse(end));
				starttime.setTimeInMillis(endtime.getTimeInMillis() - (long)interval*1000);
			}
			
			if (Main.debugFlag) System.out.println("Start:"+starttime);


			
			TimesliceGroupedMetricData[] returnedData = stub.getMetricData(agent, metric, starttime, endtime, interval);
			
			MetricData[] mData = null;
			
			for(int i=0; i< returnedData.length ; i++)
			{
				mData = returnedData[i].getMetricData();
				String temp[];
				//ArrayList<String[]> tempArray = new ArrayList<String[]>();
				//String tempTable[][] = new String[mData.length][5];
				

				
				String startTime = returnedData[i].getTimesliceStartTime().getTime().toString();
				String endTime = returnedData[i].getTimesliceEndTime().getTime().toString();
				
				returnJSON.accumulate("start",startTime);
				returnJSON.accumulate("end",endTime);

				for (int z=0; z < mData.length; z++)
				{
					if (mData[z]!=null)
					{
						temp = mData[z].getMetricName().split(":");
						String tempTable[] = new String[5];
						tempTable[0] = mData[z].getAgentName() + mData[z].getMetricName();
						tempTable[1] = mData[z].getAgentName();
						tempTable[2] = temp[0];
						tempTable[3] = temp[1];
						tempTable[4] = mData[z].getMetricValue();
						if(Main.debugFlag) System.out.println("tempTable=" + tempTable[0]);
						if(Main.debugFlag) System.out.println("Number of entries in tempArray="+tempArray.size());
						
						tempArray.add(z,tempTable);
						
						if(Main.debugFlag) System.out.println("tempArray["+z+"]=" + tempArray.get(z)[0]);
					}
				}

				Collections.sort(tempArray,new ArrayComparator(0, true));
				
				// GroupBy Agentname + Metrictree
				String group = "";
				String tmpgroup[] = null;
				int groupnumber = 0;
				
				String intermediateTable[] = new String[5];				
				for(int h=0; h<tempArray.size(); h++)
				{

					intermediateTable = tempArray.get(h);
					tmpgroup = intermediateTable[0].split(":");
					if (group.equals(tmpgroup[0]))
					{
						// same group logic
			// pass as string i.o. double			metricvalues.put(tempTable[h][3],Double.parseDouble(tempTable[h][4]));
						metricvalues.put(intermediateTable[3],intermediateTable[4]);
						metricnames.put(intermediateTable[2], metricvalues);
						
						if(Main.debugFlag) System.out.println("Same group- "+ intermediateTable[1] +" "+ intermediateTable[2] +" "+ intermediateTable[3]+" "+ intermediateTable[4]);
					}
					else
					{
						// previous group logic
						group=tmpgroup[0];
						if (h!=0)
						{
							agentname.accumulate("data", outputMap);
							returnArray.put(outputMap);
							if(Main.debugFlag) System.out.println("New group- #"+outputMap.toString());
						}

						// new group logic
						metricnames.clear();
						metricvalues.clear();
						
						metricvalues.put(intermediateTable[3],intermediateTable[4]);					
						metricvaluesList.add(groupnumber, metricvalues);
						metricnames.put(intermediateTable[2], metricvaluesList.get(groupnumber));	
						outputMap.put(intermediateTable[1], metricnames);
					
						if(Main.debugFlag) System.out.println("New group- #"+ groupnumber+ " "+ intermediateTable[1] +" "+ intermediateTable[2] +" "+ intermediateTable[3]+" "+ intermediateTable[4]);
						groupnumber++;
						if(Main.debugFlag) System.out.println("New group- #"+outputMap.toString());
					}
				}
				agentname.accumulate("data", outputMap);
				returnArray.put(outputMap);
				
				if(Main.debugFlag) System.out.println("Return JSON=\n"+returnArray.toString(2));
			}
		} 
		catch (Exception e) {
			e.printStackTrace(); 
		}
		
		for (int h=0; h<returnArray.length();h++)
		{
			returnJSON.accumulate("data",(returnArray.getJSONObject(h)));
		}
		return  returnJSON.toString();
	}
}
