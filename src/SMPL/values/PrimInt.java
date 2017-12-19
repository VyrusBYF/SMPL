/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMPL.values;

/**
 *
 * @author newts
 */
public class PrimInt extends PrimitiveValue {
    int value;
    
    public PrimInt(int val) {
        super(PrimitiveTypes.INTEGER);
        value = val;
    }
    
    @Override
    public boolean isInt() {
        return true;
    }
    
    @Override
    public int intValue() {
        return value;
    }
    
    @Override
    public double floatValue() {
        return value;
    }
    
    @Override
    public PrimitiveValue add(PrimitiveValue val) throws Exception {
        if (!val.isNumber()) {
            throw new Exception("Types no compatible with operation.");
        } else if (val.isInt()) {
            return new PrimInt(intValue() + val.intValue());
        } else {
            return new PrimFloat(floatValue() + val.floatValue());
        }
    }
    
    @Override
    public PrimitiveValue sub(PrimitiveValue val) throws Exception {
        if (!val.isNumber()) {
            throw new Exception("Types no compatible with operation.");
        } else if (val.isInt()) {
            return new PrimInt(intValue() - val.intValue());
        } else {
            return new PrimFloat(floatValue() - val.floatValue());
        }
    }
    @Override
    public PrimitiveValue mul(PrimitiveValue val) throws Exception {
        if (!val.isNumber()) {
            throw new Exception("Types no compatible with operation.");
        } else if (val.isInt()) {
            return new PrimInt(intValue() * val.intValue());
        } else {
            return new PrimFloat(floatValue() * val.floatValue());
        }
    }
    @Override
    public PrimitiveValue div(PrimitiveValue val) throws Exception {
        if (!val.isNumber()) {
            throw new Exception("Types no compatible with operation.");
        } else if (val.isInt()) {
            return new PrimInt(intValue() / val.intValue());
        } else {
            return new PrimFloat(floatValue() / val.floatValue());
        }
    }
    
    @Override
    public PrimitiveValue mod(PrimitiveValue val) throws Exception {
        if (!val.isNumber()) {
            throw new Exception("Types no compatible with operation.");
        } else if (val.isInt()) {
            return new PrimInt(intValue() % val.intValue());
        } else {
            return new PrimFloat(floatValue() % val.floatValue());
        }
    }
    
    @Override
    public PrimitiveValue less(PrimitiveValue val) throws Exception {
        if (!val.isNumber()) {
            throw new Exception("Types no compatible with operation.");
        }else {
            return new PrimBoolean(floatValue() < val.floatValue());
        }
    }
    @Override
    public PrimitiveValue more(PrimitiveValue val) throws Exception {
        if (! val.isNumber()) {
            throw new Exception("Types no compatible with operation.");
        } else {
            return new PrimBoolean(floatValue() > val.floatValue());
        }
    }
    
    @Override
    public PrimitiveValue equal(PrimitiveValue val) throws Exception {
        if (!val.isNumber()) {
            throw new Exception("Types no compatible with operation.");
        }else {
            return new PrimBoolean(floatValue() == val.floatValue());
        }
    }
    
    public String toString(){
        return ""+value;
    }
}
