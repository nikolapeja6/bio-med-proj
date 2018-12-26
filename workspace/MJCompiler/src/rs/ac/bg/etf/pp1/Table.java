package rs.ac.bg.etf.pp1;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;

public class Table {
	
	public static boolean ok = true;
	
	public static LinkedHashMap<String, Integer> tmp_table = new LinkedHashMap<>();
	
	public static LinkedHashMap<String, Integer> table = new LinkedHashMap<>();
	public static LinkedHashSet<String> lValues = new LinkedHashSet<>();
	
	public static void insertRVal(String name){
		if(!table.containsKey(name))
			table.put(name, -123456789);
	}
	
	public static void insertLVal(String name){
		if(lValues.contains(name)){
			ok = false;
			Main.log.error("Multiple rules for state '"+name+"'");
		}
		insertRVal(name);
		lValues.add(name);
	}
	
	public static void reset(){
		tmp_table = new LinkedHashMap<>();
		Iterator it = table.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        tmp_table.put((String)pair.getKey(), (Integer)(pair.getValue()));
	    }
	}
	
	public static void setValue(String name, int val){
			tmp_table.put(name, val);
	}
	
	public static int getValue(String name){
		return tmp_table.get(name);
	}
	
	public static String getString(){
		StringBuilder sb = new StringBuilder("Table:\n");
		
		Iterator it = tmp_table.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        sb.append(pair.getKey());
	        sb.append(" = ");
	        sb.append(pair.getValue());
	        sb.append("\n");
	    }
	    
	    return sb.toString();
	}
	
	public static void flush(){
		table = new LinkedHashMap<>();
	}
}
