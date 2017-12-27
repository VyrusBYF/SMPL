package SMPL.syntax;

import SMPL.semantics.Visitor;
import java.util.*;

public class ASTPrintLn extends ASTExp {

   ASTExp seq;	

    public ASTPrintLn(ASTExp s) {
	seq = s;
    }

    public ASTExp getSeq() {
	return seq;
    }

    @Override
    public Object visit(Visitor v, Object arg) throws Exception
    {
	return v.visitASTPrintLn(this, arg);
    }

}

