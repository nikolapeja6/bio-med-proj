package rs.ac.bg.etf.pp1;

import java.util.Stack;

import rs.ac.bg.etf.pp1.ast.*;

public class Evaluator extends VisitorAdaptor {
	
	private static Stack<String> expressionStack = new Stack<>();
	private static boolean definedExpression = true;
	
	@Override
	public void visit(ConstNum item){
		expressionStack.push(""+item.getN1().intValue());
	}
	
	@Override
	public void visit(ConstBool item){
		if(item.getB1().equals("true"))
			expressionStack.push("1");
		else
			expressionStack.push("0");
	}
	
	@Override
	public void visit(IdentFactor item){
		if(Table.getValue(item.getI1()) == Table.MAGIC_NUMBER){
			definedExpression = false;
			expressionStack.push("0");
		}else{
			expressionStack.push(""+Table.getValue(item.getI1()));
		}
	}
	
	@Override
	public void visit(TermElement1 item){
		Mulop mulop = item.getMulop();
		double x = Double.parseDouble(expressionStack.pop());
		double y = Double.parseDouble(expressionStack.pop());
		
		if(mulop instanceof MulopMultiply){
			expressionStack.push(""+(x*y));
		}
		else{
			expressionStack.push(""+(y/x));
		}	
	}
	
	@Override
	public void visit(ExprElement1 item){
		Addop addop = item.getAddop();
		double x = Double.parseDouble(expressionStack.pop());
		double y = Double.parseDouble(expressionStack.pop());
		
		if(addop instanceof AddopPlus){
			expressionStack.push(""+(x+y));
		}
		else{
			expressionStack.push(""+(y-x));
		}	
	}
	
	@Override
	public void visit(ExprWithMinus item){
		double x = Double.parseDouble(expressionStack.pop());
		expressionStack.push(""+(-x));
	}
	
	@Override
	public void visit(CondFactElement1 item) {
		Relop relop = item.getRelop();
		double x = Double.parseDouble(expressionStack.pop());
		double y = Double.parseDouble(expressionStack.pop());
		boolean res = false;

		if (relop instanceof RelopEq) {
			res = (y == x);
		}
		if (relop instanceof RelopNEq) {
			res = (y != x);
		}
		if (relop instanceof RelopGr) {
			res = (y > x);
		}
		if (relop instanceof RelopLs) {
			res = (y < x);
		}
		if (relop instanceof RelopGEq) {
			res = (y >= x);
		}
		if (relop instanceof RelopLEq) {
			res = (y <= x);
		}
	
		if(res)
			expressionStack.push("1");
		else
			expressionStack.push("0");
	}
	
	
	@Override
	public void visit(CondTermElement1 item) {
		boolean x = Double.parseDouble(expressionStack.pop()) != 0;
		boolean y = Double.parseDouble(expressionStack.pop()) != 0;
		boolean res = x && y;
		
		if(res)
			expressionStack.push("1");
		else
			expressionStack.push("0");
	}
	
	@Override
	public void visit(ConditionElement1 item) {
		boolean x = Double.parseDouble(expressionStack.pop()) != 0;
		boolean y = Double.parseDouble(expressionStack.pop()) != 0;
		boolean res = x || y;
		
		if(res)
			expressionStack.push("1");
		else
			expressionStack.push("0");
	}
	
	@Override
	public void visit(Rule1 item) {
		double value = Double.parseDouble(expressionStack.pop());
		if(expressionStack.size() != 0){
			Main.log.error("Inalid expression - expression stack size not 0 at end of eval");
		}
		
		if(definedExpression){
			Table.setValue(item.getI1(), value);
		}
		
		expressionStack.clear();
		Evaluator.definedExpression = true;
	}
	

}
