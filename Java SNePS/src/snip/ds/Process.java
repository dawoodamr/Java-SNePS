/**
 * @(#)Process.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/21
 */
package snip.ds;

public class Process
{
	private String name;
	private Object type;//-------rule-------
	private ReportSet reps;
	private RequestSet reqs;
	private ChannelsSet outGoing;
	private ChannelsSet inComing;
	private ChannelsSet ruleUse;
	private ReportSet pendingForwardInferences;
	private boolean priority;//true for high false for low
	
	/**
	 * Return the in coming channels list
	 * @return ChannelsSet
	 */
	public ChannelsSet getInComing()
	{
		return inComing;
	}
	
}