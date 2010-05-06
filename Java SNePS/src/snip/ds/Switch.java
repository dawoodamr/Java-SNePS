/**
 * @(#)Switch.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/14
 */
package snip.ds;
import match.ds.*;

public class Switch
{
	private Substitutions s;
	/**
	 *Creates new switch
	 */
	public Switch(Substitutions s)
	{
		this.s=s;
	}
	
	/**
	 * Check if the switch s is equal to this switch
	 * @param s switch
	 * @return true if equal false otherwise
	 */
	public boolean isEqual(Switch s)
	{
		return this.s.isEqual(s.s);
	}
	
	/**
	 * Switch the substitutions list of the report r
	 * @param r Report
	 */
	public void switchReport(Report r)
	{
		for(int i=0;i<this.s.cardinality();i++)
		{
			r.getSubstitutions().getBinding(i).setvariable(this.s.getBinding(i)
					.getVariable());
		}
	}
}