package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java_cup.runtime.Symbol;
import jdk.nashorn.internal.runtime.GlobalFunctions;
import rs.ac.bg.etf.pp1.CounterVisitor.*;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class Main {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
		
	public static void main(String[] args) throws Exception {
		Logger log = Logger.getLogger(Main.class);
		if (args.length != 1 ) {
			log.error("Not enough arguments supplied! Usage: MJParser <specs-file>");
			return;
		}
		
		File specsFile = new File(args[0]);
		if (!specsFile.exists()) {
			log.error("Specs file [" + specsFile.getAbsolutePath() + "] not found!");
			return;
		}
			
		log.info("Specs file: " + specsFile.getAbsolutePath());
		
		try (BufferedReader br = new BufferedReader(new FileReader(specsFile))) {
			Yylex lexer = new Yylex(br);
			
			Symbol symbol = lexer.next_token();
			while(symbol.sym != sym.EOF){
				System.out.println(symbol.value.toString());
				symbol = lexer.next_token();
			}
			
			
			/*
			MJParser p = new MJParser(lexer);
	        Symbol s = p.parse();  //pocetak parsiranja
	        SyntaxNode prog = (SyntaxNode)(s.value);
     
	        log.debug("***Abstract tree***");
	        log.debug("\n"+s.value.toString());

	        
			Tab.init(); // Universe scope
			Tab.insert(Obj.Type, "bool", Tab.intType);
			Analyzer semanticCheck = new Analyzer();
			prog.traverseBottomUp(semanticCheck);

				        	  	        
	        // TODO
	        if (true){//semanticCheck.passed()) {
	        	
	        	// Counters
				log.info("=========================   Counters   =========================");
				
				// classes
				
				
				// Global array variables.
				//GlobalArrayVariablesCounter globalArrayVariablesCounter = new GlobalArrayVariablesCounter();
				//prog.traverseBottomUp(globalArrayVariablesCounter);
				//log.info(globalArrayVariablesCounter.count + " global array variables found.");
		        
		        

		        tsdump();
	        		        	
	        	File objFile = new File(args[1]);
	        	log.info("Generating bytecode file: " + objFile.getAbsolutePath());
	        	if (objFile.exists())
	        		objFile.delete();
	        	
	        	// Code generation...
	        	Generator codeGenerator = new Generator();
	        	//codeGenerator.staticVarCount = codeGenerator.totalStaticDataSize = globalVarCounter.count+1;
	        	prog.traverseBottomUp(codeGenerator);
	        	//Code.mainPc = codeGenerator.getMainPc();
	        	Code.write(new FileOutputStream(objFile));
	        	
	        	log.info("Finished code generation");
	        	tsdump();
	        	log.info("SUCCESS!");
	        	
	        }
	        else {
	        	if(true){//!semanticCheck.mainDetected){
	        		log.error("No main method detected.");
	        	}
	        	log.error("Compilation was UNSUCCESSFUL!");
	        }
	        */
		}catch(Exception e){
			e.printStackTrace();
			//Tab.dump();
	        //log.info(GlobalStuff.VirtualFunctions());
		}
	}
	
	public static void tsdump(){
		Tab.dump();
	}
}
