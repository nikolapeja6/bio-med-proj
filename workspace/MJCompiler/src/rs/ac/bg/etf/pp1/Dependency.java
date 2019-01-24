package rs.ac.bg.etf.pp1;

import java.util.LinkedHashSet;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class Dependency extends VisitorAdaptor{
		
	private static LinkedHashSet<String> currentDependencies = new LinkedHashSet<String>();

	@Override
	public void visit(Rule1 rule){
		checkDependencies(rule.getI1());
	}
	
	@Override
	public void visit(OutputRule rule){
		checkDependencies(rule.getI1());
	}
	
	@Override
	public void visit(IdentFactor factor){
		currentDependencies.add(factor.getI1());
	}
	
	
	private void checkDependencies(String name){
		if(Table.dependencies.contains(name)){
			Table.dependencies.addAll(currentDependencies);
		}
		currentDependencies.clear();
	}
}
