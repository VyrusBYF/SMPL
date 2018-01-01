package SMPL.semantics;

import SMPL.syntax.ASTBitAnd;
import SMPL.syntax.ASTBitNot;
import SMPL.syntax.ASTBitOr;
import SMPL.syntax.ASTCase;
import SMPL.syntax.ASTComment;
import SMPL.syntax.ASTConditional;
import SMPL.syntax.ASTProgram;
import SMPL.syntax.ASTDefine;
import SMPL.syntax.ASTExp;
import SMPL.syntax.ASTProcCall;
import SMPL.syntax.ASTStatement;
import SMPL.syntax.ASTExpAdd;
import SMPL.syntax.ASTExpAnd;
import SMPL.syntax.ASTExpChar;
import SMPL.syntax.ASTExpDiv;
import SMPL.syntax.ASTExpEqual;
import SMPL.syntax.ASTExpLRE;
import SMPL.syntax.ASTExpLess;
import SMPL.syntax.ASTExpList;
import SMPL.syntax.ASTExpLit;
import SMPL.syntax.ASTExpMRE;
import SMPL.syntax.ASTExpMod;
import SMPL.syntax.ASTExpMore;
import SMPL.syntax.ASTExpMul;
import SMPL.syntax.ASTExpNE;
import SMPL.syntax.ASTExpNot;
import SMPL.syntax.ASTExpOr;
import SMPL.syntax.ASTExpPow;
import SMPL.syntax.ASTExpSequence;
import SMPL.syntax.ASTExpStr;
import SMPL.syntax.ASTExpSub;
import SMPL.syntax.ASTExpVar;
import SMPL.syntax.ASTExpWrapper;
import SMPL.syntax.ASTLazyExp;
import SMPL.syntax.ASTLet;
import SMPL.syntax.ASTPrint;
import SMPL.syntax.ASTPrintLn;
import SMPL.syntax.ASTProcDef;
import SMPL.syntax.ASTRead;
import SMPL.syntax.ASTReadInt;

public interface Visitor {

    // program
    public Object visitASTProgram(ASTProgram p,
				    Object arg)
	throws Exception;

    // statements
    public Object visitASTExp(ASTExp exp, Object arg)
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

    public Object visitASTConditional(ASTConditional exp, Object arg) throws Exception;

    public Object visitASTProcDef(ASTProcDef exp, Object arg)throws Exception;

    public Object visitASTExpOr(ASTExpOr exp, Object arg) throws Exception;

    public Object visitASTExpNot(ASTExpNot exp, Object arg) throws Exception;

    public Object visitASTExpMore(ASTExpMore exp, Object arg) throws Exception;

    public Object visitASTExpLess(ASTExpLess exp, Object arg) throws Exception;

    public Object visitASTExpEqual(ASTExpEqual exp, Object arg) throws Exception;

    public Object visitASTExpAnd(ASTExpAnd exp, Object arg) throws Exception;

    public Object visitASTBitNot(ASTBitNot exp, Object arg) throws Exception;

    public Object visitASTBitAnd(ASTBitAnd exp, Object arg) throws Exception;

    public Object visitASTBitOr(ASTBitOr exp, Object arg) throws Exception;

    public Object visitASTExpNE(ASTExpNE exp, Object arg) throws Exception;

    public Object visitASTExpLRE(ASTExpLRE exp, Object arg) throws Exception;

    public Object visitASTExpMRE(ASTExpMRE exp, Object arg) throws Exception;

    public Object visitASTLazyExp(ASTLazyExp exp, Object arg) throws Exception;

    public Object visitASTLet(ASTLet exp, Object arg) throws Exception;

    public Object visitASTExpSequence(ASTExpSequence exp, Object arg) throws Exception;

    public Object visitASTExpList(ASTExpList exp, Object arg) throws Exception;

    public Object visitASTCase(ASTCase exp, Object arg) throws Exception;

    public Object visitASTRead(ASTRead exp, Object arg) throws Exception;

    public Object visitASTReadInt(ASTReadInt exp, Object arg) throws Exception;

    public Object visitASTPrint(ASTPrint exp, Object arg) throws Exception;

    public Object visitASTPrintLn(ASTPrintLn exp, Object arg) throws Exception;

    public Object visitASTComment(ASTComment exp, Object arg) throws Exception;

    public Object visitASTExpStr(ASTExpStr exp, Object arg) throws Exception;

    public Object visitASTExpChar(ASTExpChar exp, Object arg) throws Exception;

    public Object visitASTExpWrapper(ASTExpWrapper exp, Object arg) throws Exception;

    public Object visitASTExpPow(ASTExpPow exp, Object arg) throws Exception;
}
