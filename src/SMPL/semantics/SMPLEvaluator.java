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
import SMPL.syntax.ASTProcCall;
import SMPL.syntax.ASTProcDef;
import SMPL.syntax.ASTStatement;
import SMPL.syntax.ASTProgram;
import SMPL.syntax.ASTRead;
import SMPL.syntax.ASTReadInt;
import SMPL.values.PrimitiveValue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
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
        Environment env = (Environment) arg;
        String name = exp.getName();
        Closure fn = env.getFn(name);
        ArrayList<ASTExp> args = exp.getArgs();
        ArrayList<String> params = fn.parameters;
        if(params.size()>=1){
            Iterator iter = args.iterator();
            if(params.size()==args.size()){
                ArrayList<Object> vals = new ArrayList<>();
                while(iter.hasNext()){
                    ASTExp b = (ASTExp) iter.next();
                    Object n = b.visit(this, arg);
                    vals.add(n);
                }
                String[] ids = params.toArray(new String[params.size()]);
                Object[] values = vals.toArray();
                Environment nenv = new Environment(ids,values,env);
                return fn.body.visit(this, nenv);
            }else{
                if(params.size()<args.size()){
                    if(fn.rest!=null){
                    ArrayList<Object> vals = new ArrayList<>();
                    while(iter.hasNext()){
                        ASTExp b = (ASTExp) iter.next();
                        Object n = b.visit(this, arg);
                        vals.add(n);
                    }
                    ArrayList<Object> v = new ArrayList(vals.subList(0, params.size()-1));
                    String[] ids = params.toArray(new String[params.size()]);
                    Object[] values = v.toArray();
                    Environment nenv = new Environment(ids,values,env);
                    result = fn.body.visit(this, nenv);

                    ArrayList<Object> v2 = new ArrayList(vals.subList(params.size(), vals.size()-1)); 
                    Closure rest = (Closure) env.get(fn.rest);
                    ids = rest.parameters.toArray(new String[rest.parameters.size()]);
                    nenv = new Environment(ids,v2.toArray(),env);
                    result = rest.body.visit(this, nenv);
                    return result;
                    }
                }
            }
        }else{
            if(params.size()==0 && fn.list==null){
                if(args.size()==0){
                    return fn.body.visit(this, env);
                }
            }else{
                if(params.size()==0 && fn.list!=null){
                     String[] ids = {fn.list};
                     return fn.body.visit(this, new Environment(ids,args.toArray(),env));
                }
            }
        }
        return result;
    }

    @Override
    public Object visitASTConditional(ASTConditional exp, Object arg) throws Exception{
        PrimitiveValue con = (PrimitiveValue) exp.getCondition().visit(this, arg);
        System.out.println(con.getBool());
        if(con.getBool()){
            ArrayList<ASTExp> seq = exp.getBody();
            Iterator iter = seq.iterator();
            while(iter.hasNext()){
                ASTExp ex = (ASTExp) iter.next();
                result = ex.visit(this, arg);
            }
            return result;
        }else{
            if(exp.getAlt()!=null){
            ArrayList<ASTExp> seq = exp.getAlt();
            Iterator iter = seq.iterator();
            while(iter.hasNext()){
                ASTExp ex = (ASTExp) iter.next();
                result = ex.visit(this, arg);
            }
            return result;
            }
        }
        return result;
    }

    @Override
    public Object visitASTProcDef(ASTProcDef exp, Object arg) throws Exception{
        Environment env = (Environment) arg;
        ArrayList<String> param = exp.getIds();
        ASTExp body = exp.getBody();
        String rest = exp.getRest(); 
        if(rest!=null){
            result = new Closure(param,body,env,rest);
        }
        else{
            if(exp.getListVar()!=null){
                result = new Closure(exp.getListVar(),body,env);
            }else{
              result = new Closure(param,body,env);
            }
        }
        return result;
    }

    @Override
    public Object visitASTExpOr(ASTExpOr exp, Object arg) throws Exception{
        PrimitiveValue val1 = (PrimitiveValue) exp.getFirst().visit(this, arg);
        PrimitiveValue val2 = (PrimitiveValue) exp.getSecond().visit(this, arg);
        return val1.or(val2);
    }

    @Override
    public Object visitASTExpNot(ASTExpNot exp, Object arg) throws Exception{
        PrimitiveValue val = (PrimitiveValue) exp.getExp().visit(this, arg);
        return val.not();
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
        PrimitiveValue val1 = (PrimitiveValue) exp.getFirst().visit(this, state);
        PrimitiveValue val2 = (PrimitiveValue) exp.getSecond().visit(this, state);
        return val1.and(val2);
    }

    @Override
    public Object visitASTDefine(ASTDefine exp, Object arg) throws Exception {
        Environment env = (Environment) arg;
        ArrayList<String> var = exp.getVar();
        Object val = exp.getValueExp().visit(this, arg);
        if(var.size()>1){
            ArrayList<Object>  vals = (ArrayList<Object>) val;
            if(var.size()==vals.size()){
                Iterator varIter = var.iterator();
                Iterator valIter = vals.iterator();
                while(varIter.hasNext()){
                    String id = (String) varIter.next();
                    Object value = valIter.next();
                    env.put(id, value);
                }
            }
        }else{
            env.put(var.get(0), val);
        }
        return result;
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
        Environment env = (Environment) arg;
        if(exp.neg()){
            PrimitiveValue v = (PrimitiveValue) env.get(exp.getVar());
            return v.negate();
        }
        return env.get(exp.getVar());
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
        PrimitiveValue val1 = (PrimitiveValue) exp.getFirst().visit(this, arg);
        PrimitiveValue Val2 = (PrimitiveValue) exp.getSecond().visit(this, arg);
        return val1.ne(Val2);
    }

    @Override
    public Object visitASTExpLRE(ASTExpLRE exp, Object arg) throws Exception {
        PrimitiveValue val1 = (PrimitiveValue) exp.getFirst().visit(this, arg);
        PrimitiveValue Val2 = (PrimitiveValue) exp.getSecond().visit(this, arg);
        return val1.lre(Val2);    
    }

    @Override
    public Object visitASTExpMRE(ASTExpMRE exp, Object arg) throws Exception {
        PrimitiveValue val1 = (PrimitiveValue) exp.getFirst().visit(this, arg);
        PrimitiveValue Val2 = (PrimitiveValue) exp.getSecond().visit(this, arg);
        return val1.mre(Val2);
    }

    @Override
    public Object visitASTLazyExp(ASTLazyExp exp, Object arg) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitASTLet(ASTLet exp, Object arg) throws Exception {
        Environment env = (Environment) arg;
        ArrayList<Binding> bindings = exp.getBindings();
        ASTExp body = exp.getBody();
        Iterator iter = bindings.iterator();
        ArrayList<String> id = new ArrayList<>();
        ArrayList<Object> val = new ArrayList<>();
        while(iter.hasNext()){
            Binding b = (Binding) iter.next();
            id.add(b.getVar());
            val.add(b.valueExp.visit(this, arg));
        }
        String[] ids = id.toArray(new String[id.size()]);
        Environment nenv = new Environment(ids,val.toArray(),env);
        return  body.visit(this, nenv);
    }

    @Override
    public Object visitASTExpSequence(ASTExpSequence exp, Object arg) throws Exception {
        ArrayList<ASTExp> seq = exp.getSeq();
        Iterator iter = seq.iterator();
        while(iter.hasNext()){
            ASTExp v = (ASTExp) iter.next();
            result = v.visit(this, arg);
        }
        return result;
    }

    @Override
    public Object visitASTExpList(ASTExpList exp, Object arg) throws Exception {
        ArrayList<ASTExp> lst = exp.getSeq();
        Iterator iter = lst.iterator();
        ArrayList<Object> res = new ArrayList<Object>();
        while(iter.hasNext()){
            ASTExp val = (ASTExp) iter.next();
            Object v = val.visit(this, arg);
            res.add(v);
        }
        return res;
    }

    @Override
    public Object visitASTCase(ASTCase exp, Object arg) throws Exception {
        ArrayList<Clause> bod = exp.getBody();
        Iterator iter = bod.iterator();
        while(iter.hasNext()){
            Clause c = (Clause) iter.next();
            PrimitiveValue predicate = (PrimitiveValue) c.pred.visit(this, arg);
            if(predicate.getBool()){
                return c.valueExp.visit(this, arg);
            }
        }
        return result;
    }

    @Override
    public Object visitASTRead(ASTRead exp, Object arg) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        String s = scanner.nextLine();
        System.out.println(s);  
        return result;
    }

    @Override
    public Object visitASTReadInt(ASTReadInt exp, Object arg) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        Integer s = scanner.nextInt();
        System.out.println(s);  
        return result;    
    }

    @Override
    public Object visitASTPrint(ASTPrint exp, Object arg) throws Exception {
        System.out.print(exp.getSeq().visit(this, arg));
        return result;
    }

    @Override
    public Object visitASTPrintLn(ASTPrintLn exp, Object arg) throws Exception {
        System.out.println(exp.getSeq().visit(this, arg));
        return result;
    }

    @Override
    public Object visitASTComment(ASTComment exp, Object arg) throws Exception {
        String comment = exp.getVar();
        return comment;
    }

    @Override
    public Object visitASTExpStr(ASTExpStr exp, Object arg) throws Exception {
        String s = exp.getVar();
        return s;
    }

    @Override
    public Object visitASTExpChar(ASTExpChar exp, Object arg) throws Exception {
        Character c = exp.getVar();
        return c;
    }

    @Override
    public Object visitASTExpWrapper(ASTExpWrapper exp, Object arg) throws Exception {
        return exp.getValue();
    }

    @Override
    public Object visitASTExpPow(ASTExpPow exp, Object arg) throws Exception {
        PrimitiveValue val1 = (PrimitiveValue) exp.getFirst().visit(this, arg);
        PrimitiveValue Val2 = (PrimitiveValue) exp.getSecond().visit(this, arg);
        return val1.exp(Val2);
    }

}
