package rs.ac.bg.etf.rbm.rest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

import javax.print.attribute.standard.MediaSize.Engineering;
import javax.ws.rs.core.UriBuilder;
 
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;

import rs.ac.bg.etf.pp1.Engine;
 
/**
 * @author Crunchify.com
 * 
 */
 
@SuppressWarnings("restriction")
public class JerseyEmbeddedHTTPServerCrunchify {
	
	private static final int port = 8080;
	private static final String packageName = "rs.ac.bg.etf.rbm.rest";
	
	private static final String[] specs = new String[]{
			"..\\MJCompiler\\test\\rules_autism.spec",
			"..\\MJCompiler\\test\\rules_broken_ribs.spec",
			"..\\MJCompiler\\test\\rules_heart_attack.spec",
			"..\\MJCompiler\\test\\rules_kidney_failure.spec",
			"..\\MJCompiler\\test\\rules_kidney_stone.spec",
			"..\\MJCompiler\\test\\rules_flue.spec",
			"..\\MJCompiler\\test\\rules_giht.spec",
			"..\\MJCompiler\\test\\rules_alzheimer.spec",
			"..\\MJCompiler\\test\\rules1.spec",
			"..\\MJCompiler\\test\\heaviside.spec",
			};
	
 
    public static void main(String[] args) throws IOException {
    	Engine.readSpecsAndEvaluateStates(specs);
    	
        System.out.println("Starting Crunchify's Embedded Jersey HTTPServer...\n");
        HttpServer crunchifyHTTPServer = createHttpServer();
        crunchifyHTTPServer.start();
        System.out.println(String.format("\nJersey Application Server started with WADL available at " + "%sapplication.wadl\n", getCrunchifyURI()));
        System.out.println("Started Crunchify's Embedded Jersey HTTPServer Successfully !!!");
    }
 
        private static HttpServer createHttpServer() throws IOException {
        ResourceConfig crunchifyResourceConfig = new PackagesResourceConfig(packageName);
        // This tutorial required and then enable below line: http://crunchify.me/1VIwInK
        //crunchifyResourceConfig.getContainerResponseFilters().add(CrunchifyCORSFilter.class);
        return HttpServerFactory.create(getCrunchifyURI(), crunchifyResourceConfig);
    }
 
    private static URI getCrunchifyURI() {
        return UriBuilder.fromUri("http://" + crunchifyGetHostName() + "/").port(port).build();
    }
 
    private static String crunchifyGetHostName() {
        String hostName = "localhost";
        try {
            hostName = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostName;
    }
}