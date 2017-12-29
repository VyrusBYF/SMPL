package SMPL.syntax;

import SMPL.semantics.Visitor;
import java.util.*;

public class ASTPrint extends ASTExp {

   ASTExp seq;	

    public ASTPrint(ASTExp s) {
	seq = s;
    }

    public ASTExp getSeq() {
	return seq;
    }

    @Override
    public Object visit(Visitor v, Object arg) throws Exception
    {
	return v.visitASTPrint(this, arg);
    }

}

