/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMPL.semantics;

import SMPL.syntax.ASTBitAnd;
import SMPL.syntax.ASTBitNot;
import SMPL.syntax.ASTBitOr;
import SMPL.syntax.ASTCase;
import SMPL.syntax.ASTComment;
import SMPL.syntax.ASTConditional;
import SMPL.syntax.ASTDefine;
import SMPL.syntax.ASTExp;
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
import SMPL.syntax.ASTExpSequence;
import SMPL.syntax.ASTExpStr;
import SMPL.syntax.ASTExpSub;
import SMPL.syntax.ASTExpUni;
import SMPL.syntax.ASTExpVar;
import SMPL.syntax.ASTLazyExp;
import SMPL.syntax.ASTLet;
import SMPL.syntax.ASTPrint;
import SMPL.syntax.ASTPrintLn;
import SMPL.syntax.ASTProcCall;
import SMPL.syntax.ASTProcDef;
import SMPL.syntax.ASTStatement;
import SMPL.syntax.ASTProgram;
import SMPL.syntax.ASTRead;
import SMPL.syntax.ASTReadInt;
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
    public Object visitASTDefine(ASTDefine exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public Object visitASTBitNot(ASTBitNot exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTBitAnd(ASTBitAnd exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTBitOr(ASTBitOr exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTExpNE(ASTExpNE exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTExpLRE(ASTExpLRE exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTExpMRE(ASTExpMRE exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTLazyExp(ASTLazyExp exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTLet(ASTLet exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTExpSequence(ASTExpSequence exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTExpList(ASTExpList exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTCase(ASTCase exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTRead(ASTRead exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTReadInt(ASTReadInt exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTPrint(ASTPrint exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTPrintLn(ASTPrintLn exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTComment(ASTComment exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTExpStr(ASTExpStr exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTExpUni(ASTExpUni exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTExpChar(ASTExpChar exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
