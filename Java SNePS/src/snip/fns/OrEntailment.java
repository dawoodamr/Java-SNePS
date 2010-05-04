/**
 * @(#)OrEntailment.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/26
 */
package snip.fns;

import sneps.Node;
import snip.ds.ChannelsSet;
import snip.ds.Process;
import snip.ds.Report;

public class OrEntailment
{
	Process p;
	int reportCounter;
	
	/**
	 * Creating the orentailment process
	 * @param node Node
	 */
	public OrEntailment(Node node)
	{
		p=new Process(node,'r',"OrEntailment");
		reportCounter=0;
	}
	
	/**
	 * Run the orentailment test 
	 */
	public void run()
	{
		for(;reportCounter<p.getReportSet().cardinality();reportCounter++)
		{
			Report r=p.getReportSet().getReport(reportCounter);
			if(r.getSign())
			{
				Report reply=new Report(r.getSubstitutions(),r.getSupport(),true
						,p.getNode(),null,r.getContext());
				ChannelsSet ctemp=p.getCQChannels().getConChannelsSet(r.getContext());
				p.sendReport(reply,ctemp);
			}
		}
	}
}