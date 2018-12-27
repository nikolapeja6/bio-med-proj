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
