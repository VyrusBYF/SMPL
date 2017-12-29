/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SMPL.values;

/**
 *
 * @author Owner
 */
public class PrimBoolean extends PrimitiveValue {
    
    Boolean bool;
    
    public PrimBoolean(boolean bool) {
        super(PrimitiveTypes.BOOLEAN);
        this.bool=bool;
    }
    
    @Override
    public Boolean getBool() {
        return bool;
    }
    
    @Override
    public boolean isBool() {
        return true;
    }
    
    @Override
    public Object getVal() {
        return bool;
    }
    
      @Override
    public PrimitiveValue and(PrimitiveValue val) throws Exception {
        if(val.isBool()){
            return new PrimBoolean(getBool() && val.getBool());
        }else{
            throw new Exception("Types no compatible with operation.");
        }
    }
    
    @Override
    public PrimitiveValue or(PrimitiveValue val) throws Exception {
        if(val.isBool()){
            return new PrimBoolean(getBool() || val.getBool());
        }else{
            throw new Exception("Types no compatible with operation.");
        }
    }
    
    @Override
    public PrimitiveValue not() throws Exception {
        if(isBool()){
            return new PrimBoolean(!(getBool()));
        }else{
            throw new Exception("Types no compatible with operation.");
        }
    }
    
    public String toString(){
        return ""+bool;
    }
    
}
