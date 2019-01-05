package rs.ac.bg.etf.rbm.rest.containers;

import java.util.LinkedList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import rs.ac.bg.etf.pp1.Table;

public class EvaluationResult extends BaseContainer {
	
	@SerializedName("Target")
	@Expose
	public String target = "unknown";
	
	@SerializedName("Result")
	@Expose
	public double result = -1;
	
	public EvaluationResult(){
		description = String.format("Result of the evaluation of the %s state.", target);
	}
	
	public EvaluationResult(String target, double result){
		this.target = target;
		this.result = result;

		if(result == Table.MAGIC_NUMBER){
			result = -1;
			description = String.format("The state '%s' was not evaluated. Either it is not a valid state, or not enough data was provided.", target);
		}else{
			description = String.format("Result of the evaluation of the %s state.", target);

		}
	}

}