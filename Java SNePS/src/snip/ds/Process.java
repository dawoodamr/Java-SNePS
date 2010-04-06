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
	
	/**
	 * Return the list of all sent reports
	 * @return ReportSet
	 */
	public ReportSet getSentReports()
	{
		return knownInstsnces;
	}
	
	/**
	 * Add a report to the sent reports list
	 * @param r report
	 */
	public void addToSent(Report r)
	{
		knownInstsnces.putIn(r);
	}
	
	/**
	 * Returns the list of out going channels
	 * @return ChannelsSet
	 */
	public ChannelsSet getOutGoing()
	{
		return outGoing;
	}
	
	/**
	 * Return the node this process is associated to
	 * @return Node
	 */
	public Node getNode()
	{
		return node;
	}
	
	/**
	 * Sends the report r over the channel c
	 * @param r report
	 * @param c channel
	 */
	public void send(Report r,Channel c)
	{
		Report rSent=new Report(r.getSubstitutions(),r.getSupport(),
				r.getSign(),this.getNode(),null,r.getContext());
			c.send(rSent);
			addToSent(r);
		
	}
}