package SMPL.sys;

import SMPL.semantics.Environment;
import SMPL.semantics.SMPLEvaluator;
import SMPL.syntax.ASTProgram;
import SMPL.syntax.SMPLLexer;
import SMPL.syntax.SMPLParser;
import java_cup.runtime.*;
import java.io.*;
import java.util.ArrayList;

public class Repl {

    public static final String PROMPT = "Eval>";
    static Environment env;

    public static void main(String args[]) {
        ArrayList<String> fileList = new ArrayList<>();
        if (args.length > 0) {        
            for (int i = 1; i < args.length; i++) {
                fileList.add(args[i]);
            }
        }
        Repl r = new Repl(new Environment());
        r.visitFiles(fileList);
        r.loop();
    }

    public Repl(Environment e){
	env=e;
    }
    
     /**
     * The driver loop in which the standard input is read until EOF is pressed
     * (Ctrl-D on Unix, Ctrl-Z on Windows); on each pass of the loop, that input
     * is parsed, and visited, and the result is displayed .
     */
    public void loop() {
        System.out.println("Type commands at the prompt.\n" + 
                "Press Ctrl-D (Ctrl-Z on Windows) to evaluate them.\n");
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            parseEvalShow(reader,env);
        }
    }
    
    public void visitFiles(ArrayList<String> fileNames) {
        // Treat all other command line arguments as files to be read and evaluated
        FileReader freader;
        for (String file : fileNames) {
            try {
                System.out.println("Reading from: " + file + "...");
                freader = new FileReader(new File(file));
                parseEvalShow(freader,env);
                System.out.println("Done! Press ENTER to continue");
                System.in.read();
            } catch (FileNotFoundException fnfe) {
                System.err.println(fnfe.getMessage());
                System.err.println("Skipping it");
            } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }
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
