/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMPL.semantics;

import SMPL.syntax.ASTConditional;
import SMPL.syntax.ASTDefine;
import SMPL.syntax.ASTExp;
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
import SMPL.syntax.ASTProcCall;
import SMPL.syntax.ASTProcDef;
import SMPL.syntax.ASTStmtSequence;
import SMPL.syntax.ASTStatement;
import SMPL.syntax.ASTProgram;
import SMPL.values.PrimitiveValue;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author newts
 */
public class SMPLEvaluator implements Visitor {
    
    protected Object result = null;

    @Override
    public Object visitASTProgram(ASTProgram exp, Object arg) throws Exception {
        ArrayList<ASTExp> s = exp.getStatements().getSeq();
        Iterator iter = s.iterator();
        while(iter.hasNext()){
            ASTExp e = (ASTExp) iter.next();
            result = e.visit(this, arg);
        }
        return result;
    }

    @Override
    public Object visitASTExp(ASTExp exp, Object arg) throws Exception {
        return exp.visit(this, arg);
    }

    @Override
    public Object visitASTProcCall(ASTProcCall exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTConditional(ASTConditional exp, Object arg) throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTProcDef(ASTProcDef exp, Object arg) throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTExpOr(ASTExpOr exp, Object arg) throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTExpNot(ASTExpNot exp, Object arg) throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTExpMore(ASTExpMore exp, Object arg) throws Exception{
        PrimitiveValue val1 = (PrimitiveValue) exp.getFirst().visit(this, arg);
        PrimitiveValue Val2 = (PrimitiveValue) exp.getSecond().visit(this, arg);
        return val1.more(Val2);
    }

    @Override
    public Object visitASTExpLess(ASTExpLess exp, Object arg) throws Exception {
        PrimitiveValue val1 = (PrimitiveValue) exp.getFirst().visit(this, arg);
        PrimitiveValue Val2 = (PrimitiveValue) exp.getSecond().visit(this, arg);
        return val1.less(Val2);
    }

    @Override
    public Object visitASTExpEqual(ASTExpEqual exp, Object arg) throws Exception{
        PrimitiveValue val1 = (PrimitiveValue) exp.getFirst().visit(this, arg);
        PrimitiveValue Val2 = (PrimitiveValue) exp.getSecond().visit(this, arg);
        return val1.equal(Val2);
    }

    @Override
    public Object visitASTExpAnd(ASTExpAnd exp, Object state) throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTStmtSequence(ASTStmtSequence exp, Object arg) throws Exception {
        ASTStatement s;
		ArrayList<ASTStatement> sseq = exp.getSeq();
		Iterator iter = sseq.iterator();
		while(iter.hasNext()) {
			s = (ASTStatement) iter.next();
			result = s.visit(this, arg);
		}
		// return last value evaluated
		return result;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTDefine(ASTDefine exp, Object arg) throws Exception {
        Environment env = arg.getEnvironment();
		result = exp.getValueExp().visit(this, arg);
		env.put(exp.getVar(), result);
		return result;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTExpAdd(ASTExpAdd exp, Object arg) throws Exception {
        PrimitiveValue val1 = (PrimitiveValue) exp.getFirst().visit(this, arg);
        PrimitiveValue Val2 = (PrimitiveValue) exp.getSecond().visit(this, arg);
        return val1.add(Val2);
    }

    @Override
    public Object visitASTExpSub(ASTExpSub exp, Object arg) throws Exception {
        PrimitiveValue val1 = (PrimitiveValue) exp.getFirst().visit(this, arg);
        PrimitiveValue Val2 = (PrimitiveValue) exp.getSecond().visit(this, arg);
        return val1.sub(Val2);
    }

    @Override
    public Object visitASTExpMul(ASTExpMul exp, Object arg) throws Exception {
        PrimitiveValue val1 = (PrimitiveValue) exp.getFirst().visit(this, arg);
        PrimitiveValue Val2 = (PrimitiveValue) exp.getSecond().visit(this, arg);
        return val1.mul(Val2);
    }

    @Override
    public Object visitASTExpDiv(ASTExpDiv exp, Object arg) throws Exception {
        PrimitiveValue val1 = (PrimitiveValue) exp.getFirst().visit(this, arg);
        PrimitiveValue Val2 = (PrimitiveValue) exp.getSecond().visit(this, arg);
        return val1.div(Val2);
    }

    @Override
    public Object visitASTExpMod(ASTExpMod exp, Object arg) throws Exception {
        PrimitiveValue val1 = (PrimitiveValue) exp.getFirst().visit(this, arg);
        PrimitiveValue Val2 = (PrimitiveValue) exp.getSecond().visit(this, arg);
        return val1.mod(Val2);
    }

    @Override
    public Object visitASTExpLit(ASTExpLit exp, Object arg) throws Exception {
        return exp.getValue();
    }

    @Override
    public Object visitASTExpVar(ASTExpVar exp, Object arg) throws Exception {
        return exp.getVar();
    }

}
