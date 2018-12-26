package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java_cup.runtime.Symbol;
import jdk.nashorn.internal.runtime.GlobalFunctions;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class Main {

	public static Logger log;

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
		log = Logger.getLogger(Main.class);
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
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

			/*
			 * Symbol symbol = lexer.next_token(); while(symbol.sym != sym.EOF){
			 * System.out.println(symbol.value.toString()); symbol =
			 * lexer.next_token(); }
			 */

			MJParser p = new MJParser(lexer);
			Symbol s = p.parse(); // pocetak parsiranja

			SyntaxNode prog = (SyntaxNode) (s.value);

			log.debug("***Abstract tree***");
			log.debug("\n" + s.value.toString());

			Generator codeGenerator = new Generator();
			prog.traverseBottomUp(codeGenerator);

			Table.reset();
			log.info(Table.getString());

			if (!Table.ok) {
				log.error("Aborted.");
				return;
			}

			int numberOfUnsetVars = Table.numberOfUnsetVariables();
			Evaluator evaluator = new Evaluator();
			prog.traverseBottomUp(evaluator);
			int newNumberOfUnsetVars = Table.numberOfUnsetVariables();
				
			while (newNumberOfUnsetVars != numberOfUnsetVars) {
				numberOfUnsetVars = newNumberOfUnsetVars;
				prog.traverseBottomUp(evaluator);
				newNumberOfUnsetVars = Table.numberOfUnsetVariables();
			}
			
			Table.set();
			log.info(Table.getString());
			
			
			int x = 5;
			for (String string : Table.getInputVarList()) {
				log.info("==> "+string);
				Table.setValue(string, x);
				x*=5;
			}
			
			numberOfUnsetVars = Table.numberOfUnsetVariables();
			prog.traverseBottomUp(evaluator);
			newNumberOfUnsetVars = Table.numberOfUnsetVariables();
				
			while (newNumberOfUnsetVars != numberOfUnsetVars) {
				numberOfUnsetVars = newNumberOfUnsetVars;
				prog.traverseBottomUp(evaluator);
				newNumberOfUnsetVars = Table.numberOfUnsetVariables();
			}
			
			log.info(Table.getString());
			
			log.info("SUCCESS!");

		} catch (Exception e) {
			e.printStackTrace();
			// Tab.dump();
			// log.info(GlobalStuff.VirtualFunctions());
		}
	}

}
