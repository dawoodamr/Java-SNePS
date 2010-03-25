/**
 * @(#)Process.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/21
 */
package snip.ds;

import sneps.Node;

public class Process
{
	private String name;
	private Object type;//-------rule-------
	private Node node;
	private ReportSet knownInstsnces;
	private ReportSet reps;
	private RequestSet reqs;
	private ChannelsSet outGoing;
	private ChannelsSet inComing;
	private ChannelsSet ruleUse;
	private ReportSet pendingForwardInferences;
	private boolean priority;//true for high false for low
	private boolean uasbility;
	
	/**
	 * Return the in coming channels list
	 * @return ChannelsSet
	 */
	public ChannelsSet getInComing()
	{
		return inComing;
	}
	
	/**
	 * Returns the reports 
	 * @return ReportSet
	 */
	public ReportSet getReportSet()
	{
		return reps;
	}
	
}