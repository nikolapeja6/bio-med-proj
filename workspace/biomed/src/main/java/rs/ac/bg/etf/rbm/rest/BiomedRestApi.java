package rs.ac.bg.etf.rbm.rest;

import java.util.Stack;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import rs.ac.bg.etf.pp1.Table;
import rs.ac.bg.etf.rbm.rest.containers.States;
 
@Path("")
public class BiomedRestApi {
 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String greeting() {
        return Parser.serialize(Parser.base);
    }
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/state")
    public String listStates() {
    	States state = new States(Table.outVars);
        return Parser.serialize(state);
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