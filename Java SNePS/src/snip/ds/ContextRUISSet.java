/**
 * @(#)ContextRUISSet.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/5/4
 */

package snip.ds;

import java.util.Vector;

import snebr.Context;

public class ContextRUISSet
{
	Vector<ContextRUIS> crs;
	
	/**
	 * Create a new ContextRUISSet
	 */
	public ContextRUISSet()
	{
		crs=new Vector<ContextRUIS>();
	}
	
	/**
	 * Add a new ContextRUIS to the ContextRUISSet
	 * @param c ContextRUIS
	 */
	public void putIn(ContextRUIS c)
	{
		crs.add(c);
	}
	
	/**
	 * Return the number of ContextRUIS in the ContextRUISSet
	 * @return int
	 */
	public int cardinality()
	{
		return crs.size();
	}
	
	/**
	 * Return the index of the ContextRUIS in ContextRUISSet which have the context c
	 * if there are no ContextRUIS with the context c return -1
	 * @param c Context
	 * @return int
	 */
	public int getIndex(Context c)
	{
		for(int i=0;i<crs.size();i++)
		{
			if(crs.get(i).getContext()==c)
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Return the ContextRUIS number x in the ContextRUISSet
	 * @param x int
	 * @return ContextRUIS
	 */
	public ContextRUIS getContextRUIS(int x)
	{
		return crs.get(x);
	}
}