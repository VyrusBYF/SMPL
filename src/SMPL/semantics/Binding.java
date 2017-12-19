package SMPL.semantics;

import SMPL.syntax.ASTExp;

public class Binding {
    String var;
    ASTExp valueExp;

    public Binding(String v, ASTExp valExp) {
	var = v;
	valueExp = valExp;
    }

    public String getVar() {
	return var;
    }

    public ASTExp getValueExp() {
	return valueExp;
    }

    public String toString() {
	return var + "=" + valueExp;
    }
}
