/**
 * @(#)FlagNodeSet.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/4/12
 */
package snip.ds;

import java.util.Vector;

import sneps.Node;

public class FlagNodeSet
{
	private Vector<FlagNode> fns;
	
	/**
	 * Create a new flag node set
	 */
	public FlagNodeSet()
	{
		fns=new Vector<FlagNode>();
	}
	
	/**
	 * Return the first flag node in the flag node set
	 * @return FlagNode
	 */
	public FlagNode choose()
	{
		return fns.get(0);
	}
	
	/**
	 * Check if the flag node set is new (empty)
	 * @return true or false
	 */
	public boolean isNew()
	{
		return fns.isEmpty();
	}
	
	/**
	 * Return a flag node set with all flag nodes in this except the first one
	 * @return FlagNodeSet
	 */
	public FlagNodeSet other()
	{
		FlagNodeSet s=new FlagNodeSet();
		for(int i=1;i<fns.size();i++)
		{
			s.fns.add(this.fns.get(i));
		}
		return s;
	}
	
	/**
	 * Check if fn is in this
	 * @param fn FlagNode
	 * @return true or false
	 */
	public boolean isMember(FlagNode fn)
	{
		for(int i=0;i<fns.size();i++)
		{
			if(fns.get(i).isEqual(fn))
				return true;
		}
		return false;
	}
	
	/**
	 * Insert fn in the flag node set if it is not in
	 * @param fn FlagNode
	 */
	public void insert(FlagNode fn)
	{
		if(!this.isMember(fn))
			fns.add(fn);
	}
	
	/**
	 * Insert fn in the flag node
	 * @param fn FlagNode
	 */
	public void putIn(FlagNode fn)
	{
			fns.add(fn);
	}
	
	/**
	 * Create a new flag node set and update the node equal to n with the flag and sup
	 * @param n node
	 * @param sup support
	 * @param flag int
	 */
	public void update(Node n,Object sup,int flag)
	{
		FlagNodeSet res=new FlagNodeSet();
		for(int i=0;i<fns.size();i++)
		{
			res.putIn(fns.get(i));
			if(res.fns.get(i).getNode()==n)
			{
				res.fns.get(i).setFlag(flag);
				//set the support of node to sup
			}
		}
	}
	
	/**
	 * Return the flag of n in the flag node set (5 should not be returned because 
	 * the existence of n in the flag node set should be tested before calling this 
	 * method)
	 * @param n node
	 * @return int
	 */
	public int getFlagNode(Node n)
	{
		for(int i=0;i<fns.size();i++)
		{
			if(this.fns.get(i).getNode()==n)
				return this.fns.get(i).getFlag();
		}
		return 5;
	}
	
	/**
	 * Return the support of n in the flag node set (null should not be returned 
	 * because the existence of n in the flag node set should be tested before calling
	 * this method)
	 * @param n node
	 * @return support
	 */
	public Object getSupNode(Node n)
	{
		for(int i=0;i<fns.size();i++)
		{
			if(this.fns.get(i).getNode()==n)
				return this.fns.get(i).getSupport();
		}
		return null;
	}
	
	/**
	 * Return the flagged node number i in the flag node set
	 * @param i int
	 * @return FlagNode
	 */
	public FlagNode getFlagNode(int i)
	{
		return fns.get(i);
	}
	
	/**
	 * Return the number of flagged nodes in this set
	 * @return int
	 */
	public int cardinality()
	{
		return fns.size();
	}
	
	/**
	 * Create a new FlagNodeSet and merge this and f in it
	 * @param f FlagNodeSet
	 * @return FlagNodeSet
	 */
	public FlagNodeSet union(FlagNodeSet f)
	{
		FlagNodeSet res=new FlagNodeSet();
		for(int i=0;i<this.fns.size();i++)
		{
			res.insert(this.fns.get(i));
		}
		for(int i=0;i<f.fns.size();i++)
		{
			res.insert(f.fns.get(i));
		}
		return res;
	}
}