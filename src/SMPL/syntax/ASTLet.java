package SMPL.syntax;

import SMPL.semantics.Binding;
import SMPL.semantics.Visitor;
import java.util.ArrayList;

public class ASTLet extends ASTStatement {

    ArrayList<Binding> bindings;
    ASTExpSequence body;

    public ASTLet(ArrayList<Binding> bs, ASTExpSequence bod) {
	bindings = bs;
	body = bod;
    }

    public ArrayList<Binding> getBindings(){
	return bindings;
    }

    public ASTExpSequence getBody() {
	return body;
    }

    @Override
    public Object visit(Visitor v, Object arg) throws Exception {
        return v.visitASTLet(this,arg); //To change body of generated methods, choose Tools | Templates.
    }
}
