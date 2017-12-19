package SMPL.syntax;

import SMPL.semantics.Visitor;
import java.util.*;

public class ASTStmtSequence extends ASTStatement {

    ArrayList<ASTStatement> seq;		// sequence of commands

    public ASTStmtSequence() {
	seq = new ArrayList<>();
    }
    
    public ASTStmtSequence(ArrayList<ASTStatement> stmts) {
        seq = stmts;
    }

    public ASTStmtSequence(ASTStatement s) {
	this();
	seq.add(s);
    }

    public ArrayList<ASTStatement> getSeq() {
	return seq;
    }

    public ASTStmtSequence add(ASTStatement s) {
	seq.add(s);
	return this;
    }
    
    public ASTStmtSequence add(ArrayList<ASTStatement> s) {
	seq.addAll(s);
	return this;
    }

    @Override
    public Object visit(Visitor v, Object arg) throws Exception
    {
	return v.visitASTStmtSequence(this, arg);
    }

    @Override
    public String toString() {

	String result = "";
        for (ASTStatement stmt : seq) {
            result = result + stmt.toString() + "\n";
        }
        
	return result;
    }

}

