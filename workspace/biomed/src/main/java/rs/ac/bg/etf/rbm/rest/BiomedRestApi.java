package rs.ac.bg.etf.rbm.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
 
@Path("api")
public class BiomedRestApi {
 
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return "\n This is Crunchify REST API via HTTPServer";
    }
    /*
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String get(String name){
    	return "\n Hello "+name;
    }
    */
    @GET 
    @Path("/aaa")
    public String create(@QueryParam("name") String name) {
        return "param1 = " + name;
    }
 
}