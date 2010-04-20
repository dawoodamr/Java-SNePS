/**
 * @(#)NumericalEntailment.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/26
 */

package snip.fns;

import snip.ds.ChannelsSet;
import snip.ds.Process;
import snip.ds.ReportSet;

public class NumericalEntailment
{
	Process p;
	int reportCounter;
	int thresh;
	
	/**
	 * Creating the numericalentailment process and give it the process p having 
	 * needed data and integer i is the number of true arguments have to be true
	 * to prove consequents
	 * @param p process
	 */
	public NumericalEntailment(Process p,int i)
	{
		this.p=p;
		thresh=i;
		reportCounter=0;
	}
	
	/**
	 * Run the numericalentailment test 
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
			if(posrep==thresh)
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