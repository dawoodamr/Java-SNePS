/**
 * @(#)Thresh.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/26
 */

package sneps;

import java.util.LinkedList;

import snebr.Context;
import sneps.Node;
import sneps.NodeSet;
import sneps.PatternNode;
import sneps.VariableNode;
import snip.ds.Channel;
import snip.ds.ChannelsSet;
import snip.ds.ContextRUIS;
import snip.ds.FlagNode;
import snip.ds.FlagNodeSet;
import snip.ds.Report;
import snip.ds.Request;
import snip.ds.RuleUseInfo;
import snip.ds.RuleUseInfoSet;
import snip.fns.*;

public class Thresh extends Rule
{
	private int min;
	private int max;
	private int total;
	private boolean shareVars;
	private int reportCounter;
	private int[] vars;
	private PatternNode []pns;
	private NodeSet patternNodes;
	private int requestCounter;

	/**
	 * Creating the Thresh process
	 * @param node Node
	 */
	public Thresh(Node node) 
	{
		super(node,"Thresh");
		reportCounter = 0;
		requestCounter=0;
		NodeSet minNode = getProcess().getNodeSet("thresh");
		min = Integer.parseInt(minNode.getNodes().get(0).getIdentifier());
		NodeSet maxNode = getProcess().getNodeSet("threshmax");
		max = Integer.parseInt(maxNode.getNodes().get(0).getIdentifier());
		patternNodes = getProcess().getNodeSet("arg");
		total = patternNodes.getNodes().size();
		LinkedList<Node> nodes=patternNodes.getNodes();
		pns=new PatternNode[total]; 
		for(int i=0;i<total;i++)
		{
			pns[i]=(PatternNode)nodes.get(i);
		}
		PatternNode n =(PatternNode)patternNodes.getNodes().get(0);
		shareVars = getProcess().allShareVars(patternNodes);
		if (shareVars) 
		{
			LinkedList<VariableNode> varsll=n.getFreeVariables();
			vars=new int [varsll.size()];
			for(int i=0;i<vars.length;i++)
			{
				vars[i]=varsll.get(i).getId();
			}
		} 
	}
	
	/**
	 * Return channels set having the channels to the nodes did not reply
	 * @param fns FlagNodeSet
	 * @return ChannelsSet
	 */
	public ChannelsSet getSendIn(FlagNodeSet fns,ContextRUIS crtemp)
	{
		PatternNode n[]=new PatternNode[fns.cardinality()];
		for(int i=0;i<fns.cardinality();i++)
		{
			n[i]=fns.getFlagNode(i).getNode();
		}
		PatternNode [] rest =new PatternNode[pns.length-n.length];
		int restIndex=0;
		for(int i=0;i<pns.length;i++)
		{
			boolean here=false;
			for(int j=0;j<n.length;i++)
			{
				if(pns[i]==n[j])
				{
					here=true;
					break;
				}
			}
			if(!here)
			{
				rest[restIndex]=pns[i];
				restIndex++;
			}
		}
		ChannelsSet cq=crtemp.getChannels();
		ChannelsSet res=new ChannelsSet();
		for(int i=0;i<cq.cardinality();i++)
		{
			Channel ctemp=cq.getChannel(i);
			boolean here=false;
			for(int j=0;j<rest.length;j++)
			{
				if(ctemp.getDestination().getNode()==rest[j])
				{
					here=true;
					break;
				}
			}
			if(!here)
				res.putIn(ctemp);
		}
		return res;
	}
	
	/**
	 * Add a ContextRUIS to ContextRUISSet
	 * @param c Context
	 * @return ContextRUIS
	 */
	public ContextRUIS addContextRUIS(Context c)
	{
		if(shareVars)
			return getProcess().addContextRUIS(c,'s');
		else
			return getProcess().addContextRUIS(c,'r');
	}

	/**
	 * process the reports
	 */
	public void processReports()
	{
		for (; reportCounter < getProcess().getReportSet().cardinality()
			; reportCounter++) 
		{
			Report r = getProcess().getReportSet().getReport(reportCounter);
			Context c=r.getContext();
			RuleUseInfo rui;
			RuleUseInfoSet res;
			if (r.getSign()) 
			{
				FlagNode fn = new FlagNode((PatternNode) r.getSignature(), r
						.getSupport(), 1);
				FlagNodeSet fns = new FlagNodeSet();
				fns.putIn(fn);
				rui = new RuleUseInfo(r.getSubstitutions(), 1, 0, fns);
			} 
			else 
			{
				FlagNode fn = new FlagNode((PatternNode) r.getSignature(), r
						.getSupport(), 2);
				FlagNodeSet fns = new FlagNodeSet();
				fns.putIn(fn);
				rui = new RuleUseInfo(r.getSubstitutions(), 0, 1, fns);
			}
			int pos=getProcess().getCRS().getIndex(c);
			ContextRUIS crtemp;
			if(pos==-1)
				crtemp=addContextRUIS(c);
			else
				crtemp=getProcess().getCRS().getContextRUIS(pos);
			if (shareVars) 
			{
				res=crtemp.getSindexing().insert(rui, vars);
			} 
			else 
			{
				res=crtemp.getRUIS().insert(rui);
				if (res == null)
					res = new RuleUseInfoSet();
			}
			for (int i = 0; i < res.cardinality(); i++) 
			{
				RuleUseInfo ruitemp=res.getRuleUseInfo(i);
				Report reply=null;
				if(ruitemp.getPosCount() > min && ruitemp.getNegCount()==total-max-1) 
				{
					reply=new Report(ruitemp.getSub(),null,true,getProcess().getNode()
							,null,c);
				} 
				else if (ruitemp.getPosCount() == min-1 
						&& ruitemp.getNegCount()>=total-max) 
				{
					reply=new Report(ruitemp.getSub(),null,false
							,getProcess().getNode(),null,c);
				}
				if(reply!=null)
				{
					ChannelsSet ctemp=getSendIn(ruitemp.getFlagNodeSet(),crtemp);
					getProcess().sendReport(reply,ctemp.getConChannelsSet(c));
				}
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