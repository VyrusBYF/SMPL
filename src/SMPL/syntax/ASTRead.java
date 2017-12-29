package SMPL.syntax;

import SMPL.semantics.Visitor;
import java.util.*;

public class ASTRead extends ASTExp {

    public ASTRead() {
    }
   

    @Override
    public Object visit(Visitor v, Object arg) throws Exception
    {
	return v.visitASTRead(this, arg);
    }

}

