package SMPL.semantics;

import SMPL.syntax.ASTExp;

public class Clause {
    ASTExp pred;
    ASTExp valueExp;

    public Clause(ASTExp v, ASTExp valExp) {
	pred = v;
	valueExp = valExp;
    }

    public ASTExp getVar() {
	return pred;
    }

    public ASTExp getValueExp() {
	return valueExp;
    }

    public String toString() {
	return pred + "=" + valueExp;
    }
}
