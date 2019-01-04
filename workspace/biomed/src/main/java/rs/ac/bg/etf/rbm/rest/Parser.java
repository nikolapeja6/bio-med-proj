package rs.ac.bg.etf.rbm.rest;

import com.google.gson.Gson;
import rs.ac.bg.etf.rbm.rest.containers.BaseContainer;

public class Parser {
	private static Gson gson = new Gson();
	public static final BaseContainer base = new BaseContainer();
	
	private static BaseContainer deserialize(String string, Class cls) {
		try {
			return gson.fromJson(string, cls);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String serialize(BaseContainer base){
		return gson.toJson(base);
	}
	
	public static void main(String[] args){
		System.out.println(Parser.serialize(base));
	}

}
