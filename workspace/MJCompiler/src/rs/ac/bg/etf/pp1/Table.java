package rs.ac.bg.etf.pp1;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Table {

	public static final double MAGIC_NUMBER = -123456789;

	public static boolean ok = true;

	public static LinkedHashMap<String, Double> tmp_table = new LinkedHashMap<>();

	public static LinkedHashMap<String, Double> table = new LinkedHashMap<>();
	public static LinkedHashSet<String> lValues = new LinkedHashSet<>();
	public static LinkedHashSet<String> outVars = new LinkedHashSet<>();

	public static void insertRVal(String name) {
		if (!table.containsKey(name))
			table.put(name, MAGIC_NUMBER);
	}

	public static void insertLVal(String name) {
		if (lValues.contains(name)) {
			ok = false;
			Engine.log.error("Multiple rules for state '" + name + "'");
		}
		insertRVal(name);
		lValues.add(name);
	}
	
	public static void insertOutVal(String name){
		outVars.add(name);
	}

	public static void reset() {
		tmp_table = new LinkedHashMap<>();
		Iterator it = table.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			tmp_table.put((String) pair.getKey(), (Double) (pair.getValue()));
		}
	}

	public static void set() {
		table = new LinkedHashMap<>();
		Iterator it = tmp_table.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			table.put((String) pair.getKey(), (Double) (pair.getValue()));
		}
	}

	public static int numberOfUnsetVariables() {
		int cnt = 0;

		Iterator it = tmp_table.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if ((Double) pair.getValue() == MAGIC_NUMBER)
				cnt++;
		}
		return cnt;
	}

	public static List<String> getInputVarList() {
		LinkedList<String> ret = new LinkedList<>();

		Iterator it = table.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if ((Double) pair.getValue() == MAGIC_NUMBER && !lValues.contains(pair.getKey()))
				ret.add((String) pair.getKey());
		}

		return ret;
	}

	public static void setValue(String name, double val) {
		tmp_table.put(name, val);
	}

	public static double getValue(String name) {
		return tmp_table.get(name);
	}

	public static String getString() {
		StringBuilder sb = new StringBuilder("Table:\n");

		Iterator it = tmp_table.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			sb.append(pair.getKey());
			sb.append(" = ");
			sb.append(pair.getValue());
			sb.append("\n");
		}

		return sb.toString();
	}

	public static void flush() {
		table = new LinkedHashMap<>();
	}
}
