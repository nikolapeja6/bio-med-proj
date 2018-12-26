package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;

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

public class Engine {

	public static Logger log;
	private static SyntaxNode prog;
	private static Evaluator evaluator = new Evaluator();

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
		log = Logger.getLogger(Engine.class);
	}
	
	public static void main(String []args){
		File specsFile = new File(args[0]);
		if (!specsFile.exists()) {
			log.error("Specs file [" + specsFile.getAbsolutePath() + "] not found!");
			return;
		}

		log.info("Specs file: " + specsFile.getAbsolutePath());
		
		StringBuilder text = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new FileReader(specsFile))){
			String line = br.readLine();
			while(line != null ){
				text.append(line);
				text.append("\r\n");
				line = br.readLine();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		log.debug(text.toString());
		
		initRules(text.toString());
		
		evaluateState();
	}

	public static void evaluateState() {
		int numberOfUnsetVars;
		int newNumberOfUnsetVars;

		int x = 5;
		for (String string : Table.getInputVarList()) {
			log.debug("==> " + string);
			Table.setValue(string, x);
			x *= 5;
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
	}
	
	

	public static void initRules(String args) {
		if (args == null) {
			log.error("Invalid specs.");
			return;
		}

		try (Reader r = new StringReader(args)) {
			Yylex lexer = new Yylex(r);

			MJParser p = new MJParser(lexer);
			Symbol s = p.parse(); // pocetak parsiranja

			prog = (SyntaxNode) (s.value);

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
			prog.traverseBottomUp(evaluator);
			int newNumberOfUnsetVars = Table.numberOfUnsetVariables();

			while (newNumberOfUnsetVars != numberOfUnsetVars) {
				numberOfUnsetVars = newNumberOfUnsetVars;
				prog.traverseBottomUp(evaluator);
				newNumberOfUnsetVars = Table.numberOfUnsetVariables();
			}

			Table.set();
			log.info(Table.getString());

			log.info("SUCCESS!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
