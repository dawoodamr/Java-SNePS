/**
 * @(#)FlagNode.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/4/12
 */
package snip.ds;

import sneps.PatternNode;

public class FlagNode
{
	
	private PatternNode node;
	private Object support;
	private int flag;
	
	/**
	 * Create a new flag node
	 * @param n node
	 * @param s support
	 * @param f true or false
	 */
	public FlagNode(PatternNode n,Object s,int f)
	{
		node=n;
		support=s;
		flag=f;
	}
	
	/**
	 * Return the node of the flag node 
	 * @return Node
	 */
	public PatternNode getNode()
	{
		return node;
	}

	/**
	 * Return the support of the flag node 
	 * @return support
	 */
	public Object getSupport()
	{
		return support;
	}
	
	/**
	 * Return the flag of the flag node (1 is true, 2 is false, 3 is unknown and 4 is
	 * requested)
	 * @return Node
	 */
	public int getFlag()
	{
		return flag;
	}
	
	/**
	 * Check if this and fn are equal
	 * @param fn flag node
	 * @return true or false
	 */
	public boolean isEqual(FlagNode fn)
	{
		return fn.node==node&&fn.support==support&&fn.flag==flag;
	}
	
	/**
	 * Merge fn and this by merging there supports 
	 * @param fn flag node
	 * @return support
	 */
	public Object merge(FlagNode fn)
	{
		Object s=null;
		//merge the supports of this and fn in s
		return s;
	}
	
	/**
	 * Set the value of the flag to x
	 * @param x int
	 */
	public void setFlag(int x)
	{
		flag=x;
	}
}