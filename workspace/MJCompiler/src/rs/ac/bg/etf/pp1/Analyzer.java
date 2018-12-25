package rs.ac.bg.etf.pp1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.naming.ldap.Rdn;

import org.apache.log4j.Logger;
import org.omg.PortableServer.RequestProcessingPolicyOperations;

import com.sun.corba.se.spi.activation.Repository;
import com.sun.java_cup.internal.runtime.Symbol;
import com.sun.org.apache.bcel.internal.generic.CPInstruction;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java_cup.production;
import javafx.geometry.Pos;
import jdk.nashorn.internal.ir.WhileNode;
import jdk.nashorn.internal.objects.Global;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.factory.SymbolTableFactory;
import rs.etf.pp1.symboltable.structure.HashTableDataStructure;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;
import sun.dc.DuctusRenderingEngine;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

// Semantic Analyzer
public class Analyzer extends VisitorAdaptor {

	boolean errorDetected = false;
	boolean mainDetected = false;
	int printCallCount = 0;
	Obj currentMethod = null;
	boolean returnFound = false;
	int nFields = 0;
	int fldCnt = 0;
	int globalVarCnt = 1;
	int localVarCnt = 0;
	int fpCnt = 0;
	Stack<Integer> apCnt = new Stack<>();

}
