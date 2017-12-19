package SMPL.syntax;

import SMPL.semantics.Visitor;
import java.util.*;

public class ASTStmtSequence extends ASTStatement {

    ArrayList<ASTExp> seq;		// sequence of commands

    public ASTStmtSequence() {
	seq = new ArrayList<>();
    }
    
    public ASTStmtSequence(ArrayList<ASTExp> stmts) {
        seq = stmts;
    }

    public ASTStmtSequence(ASTExp s) {
	this();
	seq.add(s);
    }

    public ArrayList<ASTExp> getSeq() {
	return seq;
    }

    public ASTStmtSequence add(ASTExp s) {
	seq.add(s);
	return this;
    }
    
    public ASTStmtSequence add(ArrayList<ASTExp> s) {
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
        for (ASTExp stmt : seq) {
            result = result + stmt.toString() + "\n";
        }
        
	return result;
    }

}

