/**
 * @(#)NonRule.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/5/6
 */

package sneps;

import java.util.LinkedList;

import match.ds.Substitutions;

import snebr.Proposition;
import sneps.MolecularNode;
import sneps.Network;
import sneps.Node;
import sneps.NodeSet;
import sneps.UpCable;
import snip.ds.Channel;
import snip.ds.ChannelsSet;
import snip.ds.Process;
import snip.ds.Report;
import snip.ds.Request;

@SuppressWarnings("serial")
public class NonRule extends Proposition
{
	private Process p;
	private int reportCounter;
	private int requestCounter;
	
	/**
	 * Create new non-rule
	 * @param n
	 */
	public NonRule(Node n)
	{
		super(n);
		reportCounter=0;
		requestCounter=0;
		p=new Process(n,'n');
	}
	
	/**
	 * Receive a report
	 * @param r Report
	 */
	public void receiveReport(Report r)
	{
		p.receiveReport(r);
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
		for(;reportCounter<getProcess().getReportSet().cardinality();reportCounter++)
		{
			System.out.println("Now processing report number:"+reportCounter);
			Report r=getProcess().getReportSet().getReport(reportCounter);
			System.out.println("the substitutions are:\n"+r.getSubstitutions());
			Report reply=new Report(r.getSubstitutions(),r.getSupport(),r.getSign()
					,getProcess().getNode(),null,r.getContext());
			if(!getProcess().getSentReports().isMember(reply))
			{
				//ChannelsSet ctemp=getProcess().getOutGoing()
					//.getConChannelsSet(r.getContext());
				ChannelsSet ctemp=getProcess().getOutGoing();
				getProcess().sendReport(reply,ctemp);
			}
		}
	}
	
	/**
	 * process requests
	 */
	public void processRequests()
	{
		for(;requestCounter<getProcess().getRequestSet().cardinality()
		;requestCounter++)
		{
			System.out.println("Now processing request number:"+requestCounter);
			Request r=getProcess().getRequestSet().getRequest(requestCounter);
			Channel c=r.getChannel();
			if(requestCounter==0)
			{
				if(getProcess().getNode().getClass().getSimpleName()
						.equals("ClosedNode"))
				{
					System.out.println("ClosedNode");
					//Check if this node is asserted in this context
					Report reply=new Report(new Substitutions(),null,true
							,getProcess().getNode(),null,c.getContext());
					ChannelsSet ctemp=new ChannelsSet();
					ctemp.putIn(c);
					getProcess().sendReport(reply,ctemp);
				}
				else
				{
					System.out.println("Not a ClosedNode");
					getProcess().getOutGoing().putIn(c);
					Network net=Network.getInstance();
					LinkedList<Object[]> nodes=net.match((MolecularNode)getProcess()
							.getNode());
					System.out.println("\n"+nodes.size()+" matches");
					getProcess().sendRequests(nodes,c.getContext());
				
					UpCable up1=getProcess().getNode().getUpCableSet()
						.getUpCable("cq");
					UpCable up2=getProcess().getNode().getUpCableSet()
						.getUpCable("arg");
					if(up1!=null)
					{
						NodeSet n=up1.getNodeSet();
						getProcess().sendRequests(n,c.getContext());
					}
					if(up2!=null)
					{
						NodeSet n=up2.getNodeSet();
						NodeSet nn=new NodeSet();
						ChannelsSet cstemp=getProcess().getOutGoing();
						for(int i=0;i<n.size();i++)
						{
							Node ntemp=n.getNode(i);
							boolean here=false;
							int j=0;
							if(getProcess().getFirst())
								j++;
							for(;j<cstemp.cardinality();j++)
							{
								Channel ctemp=cstemp.getChannel(j);
								if(ntemp==ctemp.getDestination().getNode())
								{
									here=true;	
									break;
								}
							}
							if(!here)
							{
								nn.addNode(ntemp);
							}
						}
						getProcess().sendRequests(nn,c.getContext());
					}
				}
			}
			else
				getProcess().addOutGoing(c);
		}
	}
	
	/**
	 * Run the non-rule
	 */
	public void run()
	{
		processReports();
		processRequests();
	}
}