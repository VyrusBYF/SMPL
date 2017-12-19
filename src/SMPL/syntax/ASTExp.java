/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMPL.syntax;

import SMPL.semantics.Visitor;

/**
 *
 * @author newts
 */
public abstract class ASTExp {
    public abstract Object visit(Visitor v, Object arg) throws Exception;
}
