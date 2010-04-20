/**
 * @(#)AndEntailment.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/24
 */
package snip.fns;

import snip.ds.ChannelsSet;
import snip.ds.Process;
import snip.ds.ReportSet;

public class AndEntailment
{
	Process p;
	int reportCounter;
	
	/**
	 * Creating the andentailment process and give it the process p having needed data
	 * @param p process
	 */
	public AndEntailment(Process p)
	{
		this.p=p;
		reportCounter=0;
	}
	
	/**
	 * Run the andentailment test
	 */
	public void run()
	{
		reportCounter++;
		if(reportCounter==p.getInComing().cardinality())
		{
			//Check if they all have the same vars (is-all-pat-same-vars)
			ReportSet rs=p.getReportSet();
			ChannelsSet cs=p.getOutGoing();
			boolean allTrue=true;
			for(int i=0;i<rs.cardinality();i++)
			{
				if(rs.getReport(i).isNegative())
				{
					allTrue=false;
					break;
				}
			}
			if(allTrue)
			{
				//combine reports from rs and send over cs page 15 16
			}
			else
			{
				//send a negative report
			}
		}
	}
	
	
}