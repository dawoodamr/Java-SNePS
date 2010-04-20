/**
 * @(#)AndOr.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/26
 */

package snip.fns;

import snip.ds.ChannelsSet;
import snip.ds.ReportSet;
import snip.ds.Process;

public class AndOr
{
	Process p;
	int min;
	int max;
	int reportCounter;
	
	/**
	 * Creating the AndOr process and give it the process p having needed data 
	 * and integer max and min are the pounds of the number of the true arguments
	 * (more then min and less than max should be true)
	 * @param p
	 * @param min
	 * @param max
	 */
	public AndOr(Process p,int min,int max)
	{
		this.p=p;
		this.min=min;
		this.max=max;
		reportCounter=0;
	}
	
	/**
	 * Run the andor test 
	 */
	public void run()
	{
		reportCounter++;
		if(reportCounter==p.getInComing().cardinality())
		{
			//Check if they all have the same vars (is-all-pat-same-vars)
			ReportSet rs=p.getReportSet();
			ChannelsSet cs=p.getOutGoing();
			int posrep=0;
			for(int i=0;i<reportCounter;i++)
			{
				if(rs.getReport(i).isPositive())
				{
					posrep++;
				}
			}
			if(posrep>=min&&posrep<=max)
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