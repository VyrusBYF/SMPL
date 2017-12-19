package SMPL.sys;

import SMPL.semantics.Environment;
import SMPL.semantics.SMPLEvaluator;
import SMPL.syntax.ASTProgram;
import SMPL.syntax.SMPLLexer;
import SMPL.syntax.SMPLParser;
import java_cup.runtime.*;
import java.io.*;

public class Repl {

    public static final String PROMPT = "Eval>";

    public static void main(String args[]) {
	repl(new Environment());
    }

    public static void repl(Environment env){
	InputStreamReader reader = new InputStreamReader(System.in);
	while (true) {
	    parseEvalShow(reader, env);
	}
    }

    public static void parseEvalShow(Reader reader,
				     Environment env) {
	SMPLParser parser;
	ASTProgram program = null;
	SMPLEvaluator interp = new SMPLEvaluator();
	System.out.print(PROMPT);
	try {
	    parser = new SMPLParser(new SMPLLexer(reader));
	    program = (ASTProgram) parser.parse().value;
	} catch (Exception e) {
	    System.out.println("Parse Error: " + e.getMessage());
	}

	if (program != null)
	    try {
		Object result;
		result = program.visit(interp, env);
		System.out.println("\nResult: " + result);
	    } catch (Exception e) {
		System.out.println(e.getMessage());
	    }
    }

}
