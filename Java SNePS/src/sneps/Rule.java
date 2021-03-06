/**
 * @(#)Rule.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/5/5
 */

package sneps;

import snebr.Proposition;
import sneps.Node;
import snip.ds.Process;
import snip.ds.Report;

@SuppressWarnings("serial")
public abstract class Rule extends Proposition
{
	private Process p;
	
	/**
	 * Create a new rule
	 * @param node Node
	 * @param name inference rule name
	 */
	public Rule(Node node)
	{
		super(node);
		p=new Process(node,'r');
	}
	
	/**
	 * Receive a report
	 * @param r Report
	 */
	public void receiveReport(Report r)
	{
		getProcess().receiveReport(r);
	}
	
	/**
	 * Return the process
	 * @return Process
	 */
	public Process getProcess() 
	{
		return p;
	}
	
	/**
	 * process reports
	 */
	public void processReports()
	{
		
	}
	
	/**
	 * process requests
	 */
	public void processRequests()
	{
		
	}
	
	/**
	 * Run the rule
	 */
	public void run()
	{
		processReports();
		processRequests();
	}
}