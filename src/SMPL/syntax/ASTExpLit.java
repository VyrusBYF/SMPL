package SMPL.syntax;

import SMPL.semantics.Visitor;
import SMPL.values.PrimitiveValue;

/**
  Class ASTExpLit.
  Intermediate representation class autogenerated by CS34Q semantic generator.
  Created on Sat Oct 12 03:13:16 2013
*/
public class ASTExpLit extends ASTExp {
  PrimitiveValue value;

  public ASTExpLit (PrimitiveValue value) {
    this.value = value;
  }

  public PrimitiveValue getValue() {
    return value;
  }

  @Override
  public Object visit(Visitor v, Object arg) throws Exception {
    return v.visitASTExpLit(this, arg);
  }

}
