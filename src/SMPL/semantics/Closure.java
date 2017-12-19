package SMPL.semantics;

import SMPL.syntax.ASTExp;
import java.util.ArrayList;

public class Closure {

    ArrayList parameters;
    ASTExp body;
    Environment env;

    public Closure(ArrayList params, ASTExp bod, Environment e) {
	parameters = params;
	body = bod;
	env = e;
    }

    

}
