/**
 * @(#)Filter.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/14
 */
package snip.ds;

import match.ds.Binding;
import match.ds.Substitutions;

public class Filter
{
	private Substitutions s;
	/**
	 *Creates new filter
	 */
	public Filter()
	{
		s=new Substitutions();
	}
	
	/**
	 * Check if the filter f is equal to this filter
	 * @param f filter
	 * @return true if equal false otherwise
	 */
	public boolean isEqual(Filter f)
	{
		return this.s.isEqual(f.s);
	}
	
	/**
	 * Check if the report r can pass throw this filter
	 * @param r Report
	 * @return boolean
	 */
	public boolean canPass(Report r)
	{
		for(int i=0;i<this.s.cardinality();i++)
		{
			Binding b=r.getSubstitutions().getBindingByVariable(s.getBinding(i)
					.getVariable());
			if(s.getBinding(i).getNode()!=b.getNode())
				return false;
		}
		return true;
	}
}