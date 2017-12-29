package SMPL.syntax;

import SMPL.semantics.Visitor;
import java.util.*;

public class ASTReadInt extends ASTExp {

    public ASTReadInt() {
    }
   

    @Override
    public Object visit(Visitor v, Object arg) throws Exception
    {
	return v.visitASTReadInt(this, arg);
    }

}

