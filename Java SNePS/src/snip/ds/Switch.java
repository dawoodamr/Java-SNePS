/**
 * @(#)Switch.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/14
 */
package snip.ds;
import sneps.VariableNode;
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
	 * Switch the substitutions list of the report r and union it with s
	 * @param r Report
	 * @param s Substitutions
	 */
	public void switchReport(Report r,Substitutions s)
	{
		for(int i=0;i<this.s.cardinality();i++)
		{
			r.getSubstitutions().getBindingByVariable(this.s.getBinding(i)
					.getVariable()).setvariable((VariableNode)this.s.getBinding(i)
							.getNode());
		}
		r.getSubstitutions().unionIn(s);
	}
	
	/**
	 * Print the switch
	 */
	public String toString()
	{
		return s.toString();
	}
}