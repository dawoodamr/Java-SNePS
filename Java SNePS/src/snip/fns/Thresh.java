/**
 * @(#)Thresh.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/26
 */

package snip.fns;

import snip.ds.ChannelsSet;
import snip.ds.Process;
import snip.ds.ReportSet;

public class Thresh
{
	Process p;
	int min;
	int max;
	int reportCounter;
	
	/**
	 * Creating the Thresh process and give it the process p having needed data 
	 * and integer threshmax and threshmin are the pounds of the number of the true 
	 * arguments(less then min or more than max should be true)
	 * @param p process
	 * @param threshmax max thresh
	 * @param threshmin min thresh
	 */
	public Thresh(Process p,int threshmax,int threshmin)
	{
		this.p=p;
		max=threshmax;
		min=threshmin;
		reportCounter=0;
	}
	
	/**
	 * Run the thresh test 
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
			if(posrep<min||posrep>max)
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