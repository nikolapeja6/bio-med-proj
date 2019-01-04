package rs.ac.bg.etf.rbm.rest.containers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseContainer {
	
	@SerializedName("Description")
	@Expose
	public String description = "Hello! This is the state evaluation system. Irasshaimase!";

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
