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
				}
			}
			if(allTrue)
			{
				for(int i=0;i<rs.cardinality();i++)
				{
					for(int j=0;j<cs.cardinality();j++)
					{
						p.send(rs.getReport(i),cs.getChannel(j));
					}
				}
			}
		}
	}
	
	
}