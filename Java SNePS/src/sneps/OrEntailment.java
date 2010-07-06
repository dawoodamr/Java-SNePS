/**
 * @(#)OrEntailment.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/26
 */
package sneps;

import sneps.Node;
import sneps.NodeSet;
import snip.ds.Channel;
import snip.ds.ChannelsSet;
import snip.ds.Report;
import snip.ds.Request;
import snip.fns.*;

@SuppressWarnings("serial")
public class OrEntailment extends Rule
{
	private int reportCounter;
	private int requestCounter;
	private NodeSet patternNodes;
	
	/**
	 * Creating the orentailment process
	 * @param node Node
	 */
	public OrEntailment(Node node)
	{
		super(node,"OrEntailment");
		reportCounter=0;
		requestCounter=0;
		patternNodes =getProcess().getNodeSet("ant");
	}
	
	/**
	 * process the reports
	 */
	public void processReports()
	{
		for(;reportCounter<getProcess().getReportSet().cardinality();reportCounter++)
		{
			Report r=getProcess().getReportSet().getReport(reportCounter);
			if(r.getSign())
			{
				Report reply=new Report(r.getSubstitutions(),r.getSupport(),true
						,getProcess().getNode(),null,r.getContext());
				ChannelsSet ctemp=getProcess().getOutGoing()
					.getConChannelsSet(r.getContext());
				getProcess().sendReport(reply,ctemp);
			}
		}
	}
	
	/**
	 * process the requests
	 */
	public void processRequests()
	{
		for(;requestCounter<getProcess().getRequestSet().cardinality()
			;requestCounter++)
		{
			Request r=getProcess().getRequestSet().getRequest(requestCounter);
			Channel c=r.getChannel();
			if(requestCounter==0)
			{
				getProcess().sendRequests(patternNodes,c.getContext());
			}
			else
			getProcess().addOutGoing(c);
			
		}
	}
}