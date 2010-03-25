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
	public Switch()
	{
		s=new Substitutions();
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
}