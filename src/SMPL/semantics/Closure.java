package SMPL.semantics;

import SMPL.syntax.ASTExp;
import java.util.ArrayList;

public class Closure {

    ArrayList<String> parameters;
    ASTExp body;
    String rest;
    Environment env;
    String list;

    public Closure(ArrayList<String> params, ASTExp bod, Environment e) {
	parameters = params;
	body = bod;
	env = e;
    }
    
    public Closure(String r, ASTExp bod, Environment e) {
	list = r;
	body = bod;
	env = e;
    }


    public Closure(ArrayList<String> params, ASTExp bod, Environment e,String r) {
	parameters = params;
	body = bod;
	env = e;
        rest = r;
    }

}
