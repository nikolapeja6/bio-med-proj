package rs.ac.bg.etf.rbm.rest.containers;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EvaluationData  {
	
	@SerializedName("Target")
	@Expose
	public String target = "unknown";
	
	@SerializedName("Data")
	@Expose
	public LinkedHashMap<String, Double> data;

	public EvaluationData(){
	}
	
	public void transform(){
		target = target.replace(' ', '_');
		
		Iterator it = data.entrySet().iterator();
		
		LinkedHashMap<String, Double> new_data = new LinkedHashMap<String, Double>();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        
	        String key = (String) pair.getKey();
	        Double value = (Double) pair.getValue();
	        
	        String key2 = key.replace(' ', '_');
	        
	        new_data.put(key2, value);
	    }
	    data.clear();
	    data = new_data;
	}
	
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public LinkedHashMap<String, Double> getData() {
		return data;
	}

	public void setData(LinkedHashMap<String, Double> data) {
		this.data = data;
	}
}