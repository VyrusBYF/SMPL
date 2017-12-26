package SMPL.syntax;

import SMPL.semantics.Visitor;
import java.util.*;

public class ASTExpList extends ASTExp {

    ArrayList<ASTExp> seq;		// sequence of commands

    public ASTExpList() {
	seq = new ArrayList<>();
    }
    
    public ASTExpList(ArrayList<ASTExp> stmts) {
        seq = stmts;
    }

    public ASTExpList(ASTExp s) {
	this();
	seq.add(s);
    }

    public ArrayList<ASTExp> getSeq() {
	return seq;
    }

    public ASTExpList add(ASTExp s) {
	seq.add(s);
	return this;
    }
    
    public ASTExpList add(ArrayList<ASTExp> s) {
	seq.addAll(s);
	return this;
    }

    @Override
    public Object visit(Visitor v, Object arg) throws Exception
    {
	return v.visitASTExpList(this, arg);
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

