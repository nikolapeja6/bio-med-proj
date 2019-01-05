package rs.ac.bg.etf.rbm.rest.containers;

import java.util.LinkedList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import rs.ac.bg.etf.pp1.Table;

public class EvaluationResults extends BaseContainer {
	
	@SerializedName("Requested")
	@Expose
	public String requestedTarget = "all";
	
	@SerializedName("Results")
	@Expose
	public LinkedList<EvaluationResult> results = new LinkedList<EvaluationResult>();
	
	@SerializedName("Missing data")
	@Expose
	public LinkedList<Dependencies> unresolved = new LinkedList<Dependencies>();
	
	
	public EvaluationResults(){
		updateDescription();
	}
	
	public EvaluationResults(String target){
		requestedTarget = target;
		updateDescription();
	}
	
	public void insertResult(String target, double result){
		results.add(new EvaluationResult(target, result));
	}
	
	public void insertUnresolvedDependency(Dependencies dep){
		dep.description = "The data points that were not provided in the request, but are needed in the evaluation of "+dep.target;
		unresolved.add(dep);
	}
	
	private void updateDescription(){
		if(!requestedTarget.equals("all")){
			this.description = String.format("The evaluation results for the '%s' state.", requestedTarget);
		}
		else{
			this.description = "Evaluation results for all possible states (based on the provided data).";
		}
		
	}
}


class EvaluationResult extends BaseContainer{
	@SerializedName("Target")
	@Expose
	public String target = "unknown";
	
	@SerializedName("Result")
	@Expose
	public double result = -1;
	
	public EvaluationResult(){
		description = String.format("Result of the evaluation of the '%s' state.", target);
	}
	
	public EvaluationResult(String target, double result){
		this.target = target;
		this.result = result;

		if(result == Table.MAGIC_NUMBER){
			result = -1;
			description = String.format("The state '%s' was not evaluated. Either it is not a valid state, or not enough data was provided.", target);
		}else{
			description = String.format("Result of the evaluation of the '%s' state.", target);

		}
	}
}


