package rs.ac.bg.etf.rbm.rest;

import java.util.Stack;

import javax.ws.rs.*;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import rs.ac.bg.etf.pp1.Engine;
import rs.ac.bg.etf.pp1.Table;
import rs.ac.bg.etf.rbm.rest.containers.Dependencies;
import rs.ac.bg.etf.rbm.rest.containers.EvaluationData;
import rs.ac.bg.etf.rbm.rest.containers.EvaluationResult;
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
    @Path("/states")
    public String listStates() {
    	States state = new States(Table.outVars);
        return Parser.serialize(state);
    }    
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/dependencies")
    public String getDependencies(@QueryParam("target") String targetName ) {
    	Dependencies dep = new Dependencies(targetName, Table.getDependenciesForState(targetName));
        return Parser.serialize(dep);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/evaluate")
    public String evaluateState(String dataString){
    	double result = Table.MAGIC_NUMBER;

    	EvaluationData data = (EvaluationData)Parser.deserialize(dataString, EvaluationData.class);
    	System.out.println(dataString);
    	System.out.println(data.target);
    	
    	data.transform();
    	
    	
    	Engine.evaluateState(data.data);

    	result = Table.getValue(data.target);
    	
    	EvaluationResult ret = new EvaluationResult(data.target, result);
    	
    	return Parser.serialize(ret);
    }
    
 
}