package SMPL.syntax;

import SMPL.semantics.Visitor;
import java.util.ArrayList;

/**
  Class ASTDefine.
  Intermediate representation class autogenerated by CS34Q semantic generator.
  Created on Sat Oct 12 03:13:16 2013
*/
public class ASTConditional extends ASTStatement {
  ASTExp condition;
  ArrayList<ASTExp> body;

  public ASTConditional(ASTExp condition, ASTStmtSequence s) {
    this.condition = condition;
    this.body = s.getSeq();
  }

  public ASTExp getCondition() {
    return condition;
  }
  
  public ArrayList<ASTExp> getBody() {
      return body;
  }

  @Override
  public Object visit(Visitor v, Object env) throws Exception {
    return v.visitASTConditional(this, env);
  }

}
