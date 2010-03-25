/**
 * @(#)Filter.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/14
 */
package snip.ds;
import match.ds.*;

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
}