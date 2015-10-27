package com.ca.field.test;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import com.ca.field.json.JSONException;
import com.ca.field.json.JSONObject;

/**
 * Root resource (exposed at "rest" path)
 */
@Path("rest")
public class Resources {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "application/json" media type.
     *
     * @return String that will be returned as a application/json response.
     * @throws JSONException 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getIt(@QueryParam("agent") String agent,@QueryParam("metric") String metric,@QueryParam("interval") String interval,@QueryParam("end") String end){

    	if (end==null) end = "now";
    	
		String metrics = null;
			try
			{ 			
				metrics = getMetrics.get(Main.endPoint,Main.userId,Main.password, agent, metric, end, Integer.parseInt(interval));
			} catch (JSONException e)
			{
				return "{ \"ERROR\": \"not all required parameters available\", \"required\": \"agent,metric,interval\", \"optional\": \"end\"} ";
			} catch (NumberFormatException e)
			{
				e.printStackTrace();	
			} 
        return metrics.toString();
    }
    /**
     * Method handling HTTP POST requests. The returned object will be sent
     * to the client as "application/json" media type.
     *
     * @return String that will be returned as a application/json response.
     * @throws JSONException 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String postIt(String request){
    	
    	if (Main.debugFlag) System.out.println("Input request=\n"+request);
    	
    	JSONObject jRequest = null;
		try {
			jRequest = new JSONObject(request);
		} catch (JSONException e) {
			System.out.println("Input is an invalid json\n You can check your input json here:\nhttp://jsonlint.com/");
			e.printStackTrace();
		}
    	
    	String metrics = null;
			try
			{
				String agent = jRequest.get("agent").toString();
				String metric = jRequest.get("metric").toString();
				String interval = jRequest.get("interval").toString();
				String end=null;
				try
				{
					end = jRequest.get("end").toString();
				} catch (JSONException e)
				{
					end = "now";
				}
				
				
				metrics = getMetrics.get(Main.endPoint,Main.userId,Main.password, agent, metric, end, Integer.parseInt(interval));
			} catch (JSONException e)
			{
				return "{ \"ERROR\": \"not all required parameters available\", \"required\": \"agent,metric,interval\", \"optional\": \"end\"} ";
			} catch (NumberFormatException e)
			{
				e.printStackTrace();	
			} 
        return metrics.toString();
    }
}
