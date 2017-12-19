package SMPL.semantics;

import SMPL.syntax.ASTConditional;
import SMPL.syntax.ASTProgram;
import SMPL.syntax.ASTDefine;
import SMPL.syntax.ASTExp;
import SMPL.syntax.ASTProcCall;
import SMPL.syntax.ASTStatement;
import SMPL.syntax.ASTStmtSequence;
import SMPL.syntax.ASTExpAdd;
import SMPL.syntax.ASTExpAnd;
import SMPL.syntax.ASTExpDiv;
import SMPL.syntax.ASTExpEqual;
import SMPL.syntax.ASTExpLess;
import SMPL.syntax.ASTExpLit;
import SMPL.syntax.ASTExpMod;
import SMPL.syntax.ASTExpMore;
import SMPL.syntax.ASTExpMul;
import SMPL.syntax.ASTExpNot;
import SMPL.syntax.ASTExpOr;
import SMPL.syntax.ASTExpSub;
import SMPL.syntax.ASTExpVar;
import SMPL.syntax.ASTProcDef;

public interface Visitor {

    // program
    public Object visitASTProgram(ASTProgram p,
				    Object arg)
	throws Exception;

    // statements
    public Object visitASTExp(ASTExp exp, Object arg)
	throws Exception ;
    public Object visitASTStmtSequence(ASTStmtSequence exp,
				    Object arg)
	throws Exception ;

    public Object visitASTDefine(ASTDefine exp, Object arg) throws Exception;
    public Object visitASTProcCall(ASTProcCall exp, Object arg) throws Exception;

    // expressions
    public Object visitASTExpAdd(ASTExpAdd exp, Object arg)
	throws Exception ;
    public Object visitASTExpSub(ASTExpSub exp, Object arg)
	throws Exception;
    public Object visitASTExpMul(ASTExpMul exp, Object arg)
	throws Exception;
    public Object visitASTExpDiv(ASTExpDiv exp, Object arg)
	throws Exception;
    public Object visitASTExpMod(ASTExpMod exp, Object arg)
	throws Exception;
    public Object visitASTExpLit(ASTExpLit exp, Object arg)
	throws Exception;
    public Object visitASTExpVar(ASTExpVar exp, Object arg)
	throws Exception;

    public Object visitASTConditional(ASTConditional exp, Object env) throws Exception;

    public Object visitASTProcDef(ASTProcDef exp, Object state)throws Exception;

    public Object visitASTExpOr(ASTExpOr exp, Object state) throws Exception;

    public Object visitASTExpNot(ASTExpNot exp, Object state) throws Exception;

    public Object visitASTExpMore(ASTExpMore aThis, Object state) throws Exception;

    public Object visitASTExpLess(ASTExpLess aThis, Object state) throws Exception;

    public Object visitASTExpEqual(ASTExpEqual aThis, Object state) throws Exception;

    public Object visitASTExpAnd(ASTExpAnd aThis, Object state) throws Exception;
}
