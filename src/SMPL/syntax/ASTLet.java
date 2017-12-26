package SMPL.syntax;

import SMPL.semantics.Binding;
import SMPL.semantics.Visitor;
import java.util.ArrayList;

public class ASTLet extends ASTStatement {

    ArrayList<Binding> bindings;
    ASTStmtSequence body;

    public ASTLet(ArrayList<Binding> bs, ASTStmtSequence bod) {
	bindings = bs;
	body = bod;
    }

    public ArrayList<Binding> getBindings(){
	return bindings;
    }

    public ASTStmtSequence getBody() {
	return body;
    }

    @Override
    public Object visit(Visitor v, Object arg) throws Exception {
        return v.visitASTLet(this,arg); //To change body of generated methods, choose Tools | Templates.
    }
}
