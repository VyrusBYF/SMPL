package SMPL.values;


public class PrimitiveValue {
    public static final PrimitiveValue NO_VALUE = null;
    
//    private Object val;
    private PrimitiveTypes type;
    
    public static PrimitiveValue make(int val) {
        return new PrimInt(val);
    }
    
    public static PrimitiveValue make(double val) {
        return new PrimFloat(val);
    }
    
    public static PrimitiveValue make(boolean var) {
        return new PrimBoolean(var);
    }
    
    public PrimitiveValue(PrimitiveTypes type) {
        this.type = type;
    }
    
    protected void setType(PrimitiveTypes t) {
        type = t;
    }

    public PrimitiveTypes getType() {
        return type;
    }
    
    /**
     *
     * @return <code>true</code> if this value represents an integer
     */
    public boolean isInt() {
	return type == PrimitiveTypes.INTEGER;
    }
    
    /**
     *
     * @return The integer that this value represents
     * @throws FractalException if this value does not represent an integer 
     */
    public int intValue() throws Exception {
        throw new Exception("Type Error: "+PrimitiveTypes.INTEGER+ " expected.");
    }

    /**
     *
     * @return <code>true</code> if this value represents a floating point number
     */
    public boolean isFloat() {
	return type == PrimitiveTypes.FLOAT;
    }
    
    /**
     *
     * @return The floating point number that this value represents
     * @throws FractalException if this value does not represent a floating point number 
     */
    public double floatValue() throws Exception {
        throw new Exception("Type Error: "+PrimitiveTypes.FLOAT+ " expected.");
    }
    
    /**
     *
     * @return <code>true</code> if this value represents either an (exact) 
     * integer or a real (inexact floating point) number.
     */
    public boolean isNumber() {
        return isInt() || isFloat();
    }
    
    /**
     * Add two values
     * @param val The value to be added to this one.
     * @return The sum of the two values
     * @throws FractalException if the types of this value and the given one are
     * incompatible.
     */
    public PrimitiveValue add(PrimitiveValue val) throws Exception {
        throw new Exception("Types no compatible with operation.");
    }
    
    /**
     * Subtract two values
     * @param val The value to be subtracted from this one.
     * @return The difference of the two values
     * @throws FractalException if the types of this value and the given one are
     * incompatible.
     */
    public PrimitiveValue sub(PrimitiveValue val) throws Exception {
        throw new Exception("Types no compatible with operation.");
    }
    
    /**
     * Multiply two values
     * @param val The value to be multiplied by this one.
     * @return The product of the two values
     * @throws FractalException if the types of this value and the given one are
     * incompatible.
     */
    public PrimitiveValue mul(PrimitiveValue val) throws Exception {
        throw new Exception("Types no compatible with operation.");
    }
    
    /**
     * Divide two values to find the quotient
     * @param val The divisor.
     * @return The quotient of this value and the given one.
     * @throws FractalException if the types of this value and the given one are
     * incompatible.
     */
    public PrimitiveValue div(PrimitiveValue val) throws Exception {
        throw new Exception("Types no compatible with operation.");
    }
    
    /**
     * Divide two values to find the remainder
     * @param val The divisor
     * @return The modulo (remainder) of this value and the given one.
     * @throws FractalException if the types of this value and the given one are
     * incompatible.
     */
    public PrimitiveValue mod(PrimitiveValue val) throws Exception {
        throw new Exception("Types no compatible with operation.");
    }
    
    public PrimitiveValue less(PrimitiveValue val) throws Exception {
        throw new Exception("Types no compatible with operation.");
    }
    
    public PrimitiveValue more(PrimitiveValue val) throws Exception {
        throw new Exception("Types no compatible with operation.");
    }
    
    public PrimitiveValue equal(PrimitiveValue val) throws Exception {
        throw new Exception("Types no compatible with operation.");
    }
    
    public PrimitiveValue and(PrimitiveValue val) throws Exception {
        throw new Exception("Types no compatible with operation.");
    }
    
    public PrimitiveValue or(PrimitiveValue val) throws Exception {
        throw new Exception("Types no compatible with operation.");
    }
    
    public PrimitiveValue not() throws Exception {
        return null;
    }

    public boolean isBool() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean getBool() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
