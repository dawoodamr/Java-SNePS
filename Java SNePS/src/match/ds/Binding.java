/**
 * @(#)Binding.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/14
 */
package match.ds;

public class Binding 
{
	private Object mn;
	private Object mv;
	/**
	 *Creates new binding from mvar and mnode
	 *@param mnode
	 *@param mvar
	 */
    public Binding(Object mvar,Object mnode) 
    {
    	mn=mnode;
    	mv=mvar;
    }
    
    /**
	 *returns the mvar of the mbind
	 *@return mv
	 */
    public Object getMv() 
    {
    	return mv;
    }
    /**
	 *returns the mnode of the mbind
	 *@return mn
	 */
    public Object getMn() 
    {
    	return mn;
    }
    
    /**
	 *Check if mb is equal to this binding
	 *@param mb binding
	 *@return true if equal false otherwise
	 */
    public boolean isEqual(Binding mb) 
    {
    	if(this.getMn() == mb.getMn() && this.getMv() == mb.getMv())//------node-----
    		return true;
    			return false;
    }
    
    /**
	 *Set the value of the mvar of the binding with m
	 *@param m the new mvar
	 */
    public void setMv(Object m) 
    {
    	mv=m;
    }
    /**
	 *Set the value of the mnode of the binding with m
	 *@param m the new mnode
	 */
    public void setMn(Object m) 
    {
    	mn=m;
    }
}