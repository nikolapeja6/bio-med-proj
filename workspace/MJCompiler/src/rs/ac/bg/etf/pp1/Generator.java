package rs.ac.bg.etf.pp1;

import java.util.Collection;
import java.util.Stack;

import javax.management.StandardEmitterMBean;

import org.apache.log4j.Logger;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodType;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import sun.util.logging.resources.logging;

public class Generator extends VisitorAdaptor {

	@Override
	public void visit(ConstNum num){
		num.myobj = new MyObj();
		num.myobj.value = num.getN1();
	}
	
	@Override
	public void visit(ConstBool b){
		b.myobj = new MyObj();
		if(b.getB1().equals("true"))
			b.myobj.value = 1;
		else
			b.myobj.value = 0;
	}

	@Override
	public void visit(MulopMultiply m){
		m.myobj = new MyObj();
		m.myobj.name = "*";
	}
	
	@Override
	public void visit(MulopDiv m){
		m.myobj = new MyObj();

		m.myobj.name = "/";
	}
	
	@Override
	public void visit(AddopMinus m){
		m.myobj = new MyObj();

		m.myobj.name = "-";
	}
	
	@Override
	public void visit(AddopPlus m){
		m.myobj = new MyObj();

		m.myobj.name = "+";
	}
	
	
	
	@Override
	public void visit(RelopEq m){
		m.myobj = new MyObj();

		m.myobj.name = "==";
	}
	
	@Override
	public void visit(RelopNEq m){
		m.myobj = new MyObj();

		m.myobj.name = "!=";
	}
	
	@Override
	public void visit(RelopGr m){
		m.myobj = new MyObj();

		m.myobj.name = ">";
	}
	
	@Override
	public void visit(RelopLs m){
		m.myobj = new MyObj();

		m.myobj.name = "<";
	}
	
	@Override
	public void visit(RelopGEq m){
		m.myobj = new MyObj();

		m.myobj.name = ">=";
	}
	
	@Override
	public void visit(RelopLEq m){
		m.myobj = new MyObj();

		m.myobj.name = "<=";
	}
	
	
	@Override
	public void visit(ConstFactor c){
		c.myobj = new MyObj();

		c.myobj.value = c.getConst().myobj.value;
	}
	
	@Override
	public void visit(IdentFactor f){
		f.myobj = new MyObj();

		f.myobj.name = f.getI1();
		Table.insertRVal(f.getI1());
	}
	
	
	@Override
	public void visit(Rule1 rule){
		rule.myobj = new MyObj();

		rule.myobj.name = rule.getI1();
		Table.insertLVal(rule.getI1());
	}

}
