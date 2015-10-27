package com.ca.field.test;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static String BASE_URI_part1 = "http://localhost:";
    public static String BASE_URI_part2 = "/RESTgtw/";
    /*
	String endPoint = "http://localhost:8081/introscope-web-services/services/MetricsDataService";
	String userId = "admin";
	String password = "Mickey01";
	*/
	public static String endPoint = null;
	public static String userId = null;
	public static String password = null;
	public static Boolean debugFlag = false;
	public static int port = 8310;

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.ca.field.test package
        final ResourceConfig rc = new ResourceConfig().packages("com.ca.field.test");
        
         HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI_part1+port+BASE_URI_part2), rc);

        return server;
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {


        for (int i = 0; args != null && i < args.length; i++) {
            if (args[i].equals("-endpoint") || args[i].equals("-e")) {
                endPoint = args[++i];
            }
            if (args[i].equals("-userid") || args[i].equals("-u")) {
            	userId = args[++i];
            }
            if (args[i].equals("-password") || args[i].equals("-p")) {
            	password = args[++i];
            }
            if (args[i].equals("-debug") || args[i].equals("-d")) {
            	debugFlag = true;
            }
            if (args[i].equals("-port") || args[i].equals("-port")) {
            	port = Integer.parseInt(args[++i]); // override default port 8310
            }
            
        }
        
        if (endPoint==null||password==null||userId==null)
        {
        	System.out.println(String.format("Usage: \n RESTgtw\n  -endpoint (-e) \"endpoint\" \n  -userid (-u) \"userid\" \n  -password (-p) \"password\""));
        }
        else
        {
        	final HttpServer server = startServer();
        	System.out.println(String.format("Jersey app started with WADL available at "
        			+ "%sapplication.wadl\nHit enter to stop it...", BASE_URI_part1+port+BASE_URI_part2));
        	System.in.read();
        	server.shutdown();
        //server.stop();
        }
    }
}

