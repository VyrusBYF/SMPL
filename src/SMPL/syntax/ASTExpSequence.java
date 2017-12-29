package SMPL.syntax;

import SMPL.semantics.Visitor;
import java.util.*;

public class ASTExpSequence extends ASTExp {

    ArrayList<ASTExp> seq;		// sequence of commands

    public ASTExpSequence() {
	seq = new ArrayList<>();
    }
    
    public ASTExpSequence(ArrayList<ASTExp> stmts) {
        seq = stmts;
    }

    public ASTExpSequence(ASTExp s) {
	this();
	seq.add(s);
    }

    public ArrayList<ASTExp> getSeq() {
	return seq;
    }

    public ASTExpSequence add(ASTExp s) {
	seq.add(s);
	return this;
    }
    
    public ASTExpSequence add(ArrayList<ASTExp> s) {
	seq.addAll(s);
	return this;
    }

    @Override
    public Object visit(Visitor v, Object arg) throws Exception
    {
	return v.visitASTExpSequence(this, arg);
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

