package rs.ac.bg.etf.rbm.rest;

import java.util.List;
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
import rs.ac.bg.etf.rbm.rest.containers.EvaluationResults;
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
	public String getDependencies(@QueryParam("target") String targetName) {
		Dependencies dep = new Dependencies(targetName, Table.getDependenciesForState(targetName));
		return Parser.serialize(dep);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/evaluate")
	public String evaluateState(String dataString) {
		double result = Table.MAGIC_NUMBER;
		
		Table.reset();	

		EvaluationData data = (EvaluationData) Parser.deserialize(dataString, EvaluationData.class);

		System.out.println(dataString);
		System.out.println(data.target);

		data.transform();

		Engine.evaluateState(data.data);
		EvaluationResults ret = new EvaluationResults(data.target);

		if (data.target.equals("all")) {
			for (String target : Table.outVars) {
				processResult(ret, target);
			}
		}
		else {
			processResult(ret, data.target);
		}

		Table.reset();

		return Parser.serialize(ret);
	}
	
	private void processResult(EvaluationResults ret, String targetName){
		double result = Table.getValue(targetName);
		if(result != Table.MAGIC_NUMBER){
			ret.insertResult(targetName,  result);
		}else{
			ret.insertUnresolvedDependency(new Dependencies(targetName, Table.getDependenciesForState(targetName)));
		}
	}

}