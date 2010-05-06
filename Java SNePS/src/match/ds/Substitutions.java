/**
 * @(#)Substitutions.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/14
 */
package match.ds;
import java.util.Vector;

import sneps.Node;
import sneps.VariableNode;

public class Substitutions 
{
	private Vector<Binding> sub;
	/**
	 *Creates new empty substitutions list
	 */
    public Substitutions() 
    {
    	sub=new Vector<Binding>();
    }
    
    /**
	 *Check if the substitutions list new or not (empty) 
	 *@return true if new false otherwise 
	 */
    public boolean isNew(Binding mb) 
    {
    	return sub.isEmpty();
    }
    
    /**
	 *Insert a new binding in the list of substitutions
	 *@param mb Binding
	 */
    public void putIn(Binding mb) 
    {
    	sub.add(mb);
    }
    
    /**
     * Check if mb is compatible with this substitutions list 
     * @param mb Binding
     * @return true or false
     */
    public boolean isCompatible(Binding mb) 
    {
    	Substitutions test =new Substitutions();
    	test.sub.add(mb);
    	return this.isCompatible(test);
    }
    
    /**
	 *Update the value of a binding with the new node
	 *@param mb the binding
	 *@param mn the new node
	 */
    public void update(Binding mb , Node mn) 
    {
    	for(int i=0;i<sub.size();i++)
    	{
    		if(mb.isEqual(sub.get(i)))
    		{
    			sub.get(i).setNode(mn);
    		}
    	}
    }
    
    /**
	 *Check if the mvar is bound in this substitution list or not.
	 *@param mv the mvar
	 *@return true if the mv is bound false otherwise
	 */
    public boolean isBound(VariableNode mv) 
    {
    	for(int i=0;i<sub.size();i++)
    	{
    		if(sub.get(i).getVariable()==mv)//---------node-----------
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
	 *Check if the mnode is a value in this substitution list or not.
	 *@param mn the mnode
	 *@return true if the mn is a value false otherwise
	 */
    public boolean isValue(Node mn) 
    {
    	for(int i=0;i<sub.size();i++)
    	{
    		if(sub.get(i).getNode()==mn)//---------node-----------
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
	 *Returns mvar of the mnode in the substitutions list if mnode is not in the 
	 *substitutions list return null
	 *@param mn is the mnode
	 *@return mvar or null
	 */
    public Object srcNode(Node mn) 
    {
    	for(int i=0;i<sub.size();i++)
    	{
    		if(sub.get(i).getNode()==mn)
    		{
    			return sub.get(i).getVariable();
    		}
    	}
    	return null;
    }
    
    /**
	 *Returns the binding witch have mv as its mvar or null if mv is not in the 
	 *substitutions list
	 *@param mv mvar
	 *@return binding or null
	 */
    public Binding getBindingByVariable(VariableNode mv) 
    {
    	for(int i=0;i<sub.size();i++)
    	{
    		if(sub.get(i).getVariable()==mv)
    		{
    			return sub.get(i);
    		}
    	}
    	return null;
    }
    
    /**
	 *Returns the binding witch have mn as its node or null if mn is not in the 
	 *substitutions list
	 *@param mn mnode
	 *@return binding or null
	 */
    public Binding getBindingByNode(Node mn) 
    {
    	for(int i=0;i<sub.size();i++)
    	{
    		if(sub.get(i).getNode()==mn)
    		{
    			return sub.get(i);
    		}
    	}
    	return null;
    }
    
    /**
	 *Check if the binding mb is in the substitutions list or not
	 *@param mb the binding
	 *@return true if mb exists in substitutions list false otherwise
	 */
    public boolean isMemb(Binding mb) 
    {
    	for(int i=0;i<sub.size();i++)
    	{
    		if(mb.isEqual(sub.get(i)))
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     *Check if substitutions list s is a subset of this substitutions list
     *@param s substitutions list
     *@return true if s is a subset of this false otherwise
     */
    public boolean isSubSet(Substitutions s)
    {
    	if(this.sub.size()<s.sub.size())
    		return false;
    	for(int i=0;i<s.sub.size();i++)
    	{
    		boolean found =false;
    		for(int j=0;j<this.sub.size()&&!found;j++)
    		{
    			if(s.sub.get(i).isEqual(this.sub.get(j)))
    				found=true;
    		}
    		if(!found)
    			return false;
    	}
    	return true;
    }
    
    /**
     *Check if substitutions list s is a equal to this substitutions list
     *@param s substitutions list
     *@return true if s is a equal to this false otherwise
     */
    public boolean isEqual(Substitutions s)
    {
    	if(this.sub.size()==s.sub.size())
    	{
    		for(int i=0;i<s.sub.size();i++)
    		{
    			boolean found =false;
    			for(int j=0;j<this.sub.size()&&!found;j++)
    			{
    				if(s.sub.get(i).isEqual(this.sub.get(j)))
    					found=true;
    			}
    			if(!found)
    				return false;
    		}
    		return true;
    	}
    	return false;
    }
    
    /**
     * Union the substitution list s with this substitution list in a new
     * substitutions list
     * @param s substitutions list
     * @return substitutions
     */
    public Substitutions union (Substitutions s)
    {
    	Substitutions res=new Substitutions();
    	for(int i=0;i<this.sub.size();i++)
    	{
    		if(!res.isMemb(this.sub.get(i)))
    		{
    			res.putIn(this.sub.get(i));
    		}
    	}
    	for(int i=0;i<s.sub.size();i++)
    	{
    		if(!res.isMemb(s.sub.get(i)))
    		{
    			res.putIn(s.sub.get(i));
    		}
    	}
    	return res;
    }
    
    /**
     * returns a substitutions list consisting of only those bindings 
     * whose mvar are in ns
     * @param ns array of mvar nodes
     * @return substitutions list
     */
    public Substitutions restrict(VariableNode [] ns)
    {
    	Substitutions s=new Substitutions();
    	for(int i=0;i<ns.length;i++)
    	{
    		Binding x=getBindingByVariable(ns[i]);
    		if(x!=null)
    		s.putIn(x);
    	}
    	return s;
    }
    
    /**
     * If mv is an mvar which is bound, then returns the mnode to which "mv" is 
     * bound  otherwise it returns null
     * @param mv mvar
     * @return mnode or null
     */
    public Object term(VariableNode mv) 
    {
    	for(int i=0;i<sub.size();i++)
    	{
    		if(sub.get(i).getVariable()==mv)
    		{
    			return sub.get(i).getNode();
    		}
    	}
    	return null;
    }
    
    /**
     * Returns the number of bindings in the substitution list
     * @return number of bindings
     */
    public int cardinality()
    {
    	return sub.size();
    }
    
    /**
     * Returns the first mbind in the substitutions list
     * @return mbind
     */
    public Binding choose()
    {
    	return sub.get(0);
    }
    
    /**
	 * Return a substitutions list with all the bindings in the substitutions list 
	 * except the first binding 
	 * @return Substitutions
	 */
	public Substitutions others()
	{
		Substitutions s1=new Substitutions();
		for(int i=1;i<this.sub.size();i++)
		{
			s1.sub.add(this.sub.get(i));
		}
		return  s1;
	}
	
	/**
	 * If the node n is bound to another node return the one bounding it 
	 * otherwise return the node it self
	 * @param n node
	 * @return node
	 */
	public Object value(VariableNode n)
	{
		Binding b = getBindingByVariable(n);
		if(b==null)
			return n;
		return b.getNode();
	}
	
	/**
	 * Returns a new substitutions list with the binding of this added to them 
	 * the Mbind m
	 * @param m mbind
	 * @return Substitutions
	 */
	public Substitutions insert(Binding m)
	{
		Substitutions s1=new Substitutions();
		s1.putIn(m);
		for(int i=0;i<this.sub.size();i++)
		{
			s1.sub.add(this.sub.get(i));
		}
		return  s1;
	}
	
	/**
	 * Check if the substitutions list s is compatible to this or not
	 * two lists are compatible if ever mvar in both are bound to the same mnode
	 * and ever mnode in both are bound to the same mvar
	 * @param s substitutions list
	 * @return true or false
	 */
	public boolean isCompatible(Substitutions s)
	{
		for(int i=0;i<this.sub.size();i++)
		{
			Binding m1=s.getBindingByNode(this.sub.get(i).getNode());
			Binding m2=s.getBindingByVariable(this.sub.get(i).getVariable());
			if(!this.sub.get(i).isEqual(m1)||!this.sub.get(i).isEqual(m2))
				return false;
		}
		return true;
	}
	
	/**
	 * Return the Binding number x in the substitutions list
	 * @param x binding number
	 * @return Binding
	 */
	public Binding getBinding(int x)
	{
		return sub.get(x);
	}
	
	
	public Substitutions[] split()
	{
		Substitutions []res=new Substitutions[2];
		for(int i=0;i<sub.size();i++)
		{
			Binding x=sub.get(i);
			Node n=x.getNode();
			String name=n.getClass().getName();
			if(name.equals("BaseNode"))
				res[0].putIn(x);
			else
				res[1].putIn(x);
		}
		return res;
	}
}