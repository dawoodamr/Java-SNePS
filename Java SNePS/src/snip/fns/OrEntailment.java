/**
 * @(#)OrEntailment.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/26
 */
package snip.fns;

import snip.ds.ChannelsSet;
import snip.ds.Process;
import snip.ds.ReportSet;

public class OrEntailment
{
	Process p;
	int reportCounter;
	
	/**
	 * Creating the orentailment process and give it the process p having needed data
	 * @param p process
	 */
	public OrEntailment(Process p)
	{
		this.p=p;
		reportCounter=-1;
	}
	
	/**
	 * Run the orentailment test 
	 */
	public void run()
	{
		reportCounter++;
		ReportSet rs=p.getReportSet();
		ChannelsSet cs=p.getOutGoing();
		if(rs.getReport(reportCounter).isPositive())
		{
			for(int i=0;i<cs.cardinality();i++)
			{
				p.send(rs.getReport(reportCounter), cs.getChannel(i));
			}
		}
		else if(reportCounter==p.getInComing().cardinality())
		{
			//send a negative report
		}
	}
}