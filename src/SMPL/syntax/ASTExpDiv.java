package SMPL.syntax;

import SMPL.semantics.Visitor;

/**
  Class ASTExpDiv.
  Intermediate representation class autogenerated by CS34Q semantic generator.
  Created on Sat Oct 12 03:13:16 2013
*/
public class ASTExpDiv extends ASTExp {
  ASTExp first;
  ASTExp second;

  public ASTExpDiv (ASTExp first,ASTExp second) {
    this.first = first;
    this.second = second;
  }

  public ASTExp getFirst() {
    return first;
  }

  public ASTExp getSecond() {
    return second;
  }

  @Override
  public Object visit(Visitor v, Object arg) throws Exception {
    return v.visitASTExpDiv(this, arg);
  }

}
