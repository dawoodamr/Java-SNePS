/**
 * @(#)Binding.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/14
 */
package match.ds;

import sneps.Node;

public class Binding 
{
	private Node mn;
	private Node mv;
	/**
	 *Creates new binding from mvar and mnode
	 *@param mnode
	 *@param mvar
	 */
    public Binding(Node mvar,Node mnode) 
    {
    	mn=mnode;
    	mv=mvar;
    }
    
    /**
	 *returns the mvar of the binding
	 *@return mv
	 */
    public Object getMv() 
    {
    	return mv;
    }
    /**
	 *returns the mnode of the binding
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
    public void setMv(Node m) 
    {
    	mv=m;
    }
    /**
	 *Set the value of the mnode of the binding with m
	 *@param m the new mnode
	 */
    public void setMn(Node m) 
    {
    	mn=m;
    }
}