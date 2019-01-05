package rs.ac.bg.etf.rbm.rest.containers;

import java.util.LinkedList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dependencies extends BaseContainer {
	
	@SerializedName("Target")
	@Expose
	public String target = "unknown";
	
	@SerializedName("Dependencies")
	@Expose
	public LinkedList<String> dependencies;

	public Dependencies(){
		description = String.format("List of all the dependencies for the %s state.", target);
	}
	
	public Dependencies(String target, Iterable<String> dependencies){
		this.dependencies = new LinkedList<String>();
		this.target = target;
		description = String.format("List of all the dependencies for the '%s' state.", target);

		
		for (String dependency : dependencies) {
			this.dependencies.add(dependency.replace('_', ' '));
		}
		
		if(this.dependencies.size() == 1 && this.dependencies.contains(target)){
			this.dependencies.clear();
			description = String.format("The state '%s' has no dependencies. Either it is an input state, or the name is invalid.", target);
		}
	}

}
