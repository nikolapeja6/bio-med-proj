package rs.ac.bg.etf.pp1;


import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CounterVisitor extends VisitorAdaptor {

	protected int count;

	public int getCount() {
		return count;
	}


}
