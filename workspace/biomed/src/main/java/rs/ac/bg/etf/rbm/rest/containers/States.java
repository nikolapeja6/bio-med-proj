package rs.ac.bg.etf.rbm.rest.containers;

import java.util.LinkedList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class States extends BaseContainer {

	@SerializedName("States")
	@Expose
	public LinkedList<String> states = null;
	
	public States(){
		description = "List of all the states.";
	}
	
	public States(Iterable<String> states){
		this();

		this.states = new LinkedList<String>();
		
		for (String state : states) {
			this.states.add(state.replace('_', ' '));
		}
	}

	public LinkedList<String> getStates() {
		return states;
	}

	public void setStates(LinkedList<String> states) {
		this.states = states;
	}
	
	
}
