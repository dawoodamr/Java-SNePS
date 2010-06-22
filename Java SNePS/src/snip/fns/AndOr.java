/**
 * @(#)AndOr.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/26
 */

package snip.fns;

import java.util.LinkedList;

import match.ds.Substitutions;

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

public class AndOr extends Rule
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
	 * Creating the AndOr process
	 * @param node Node
	 */
	public AndOr(Node node)
	{
		super(node);
		reportCounter=0;
		requestCounter=0;
		NodeSet minNode = getProcess().getNodeSet("min");
		min=Integer.parseInt(minNode.getNode(0).getIdentifier());
		NodeSet maxNode = getProcess().getNodeSet("max");
		max=Integer.parseInt(maxNode.getNode(0).getIdentifier());
		patternNodes = getProcess().getNodeSet("arg");
		total=patternNodes.size();
		pns=new PatternNode[total]; 
		for(int i=0;i<total;i++)
		{
			pns[i]=(PatternNode)patternNodes.getNode(i);
		}
		PatternNode n =(PatternNode)patternNodes.getNode(0);
		shareVars= getProcess().allShareVars(patternNodes);
		if(shareVars)
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
		for(;reportCounter< getProcess().getReportSet().cardinality();reportCounter++)
		{
			Report r= getProcess().getReportSet().getReport(reportCounter);
			Context c=r.getContext();
			RuleUseInfo rui;
			RuleUseInfoSet res;
			if(r.getSign())
			{
				FlagNode fn=new FlagNode((PatternNode)r.getSignature()
						,r.getSupport(),1);
				FlagNodeSet fns=new FlagNodeSet();
				fns.putIn(fn);
				rui=new RuleUseInfo(r.getSubstitutions(),1,0,fns);
			}
			else
			{
				FlagNode fn=new FlagNode((PatternNode)r.getSignature()
						,r.getSupport(),2);
				FlagNodeSet fns=new FlagNodeSet();
				fns.putIn(fn);
				rui=new RuleUseInfo(r.getSubstitutions(),0,1,fns);
			}
			int pos= getProcess().getCRS().getIndex(c);
			ContextRUIS crtemp;
			if(pos==-1)
				crtemp=addContextRUIS(c);
			else
				crtemp= getProcess().getCRS().getContextRUIS(pos);
			if(shareVars)
			{
				res=crtemp.getSindexing().insert(rui, vars);
			}
			else
			{
				res=crtemp.getRUIS().insert(rui);
				if(res==null)
					res=new RuleUseInfoSet();
			}
			for(int i=0;i<res.cardinality();i++)
			{
				RuleUseInfo ruitemp=res.getRuleUseInfo(i);
				Report reply=null;
				if(ruitemp.getPosCount()==max)
				{
					reply=new Report(ruitemp.getSub(),null,false,
							getProcess().getNode(),null,c);
				}
				else if(ruitemp.getNegCount()==total-min)
				{
					reply=new Report(ruitemp.getSub(),null,true,
							getProcess().getNode(),null,c);
				}
				if(reply!=null)
				{
					//ChannelsSet ctemp=getSendIn(ruitemp.getFlagNodeSet(),crtemp);
					//getProcess().sendReport(reply,ctemp.getConChannelsSet(c));
					ChannelsSet ctemp=getProcess().getOutGoing();
					getProcess().sendReport(reply,ctemp);
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
				getProcess().getOutGoing().putIn(c);
				if(min==max&&min==0)
				{
					Report reply=new Report(new Substitutions(),null,false,
							getProcess().getNode(),null,c.getContext());
					ChannelsSet cs=new ChannelsSet();
					cs.putIn(c);
					getProcess().sendReport(reply, cs);
				}
				else
				{
					NodeSet ns=new NodeSet();
					for(int i=0;i<patternNodes.size();i++)
					{
						Node n=patternNodes.getNode(i);
						if(n!=c.getDestination().getNode())
						{
							ns.addNode(n);
						}
					}
					getProcess().sendRequests(ns,c.getContext());
				}
			}
			else
			getProcess().addOutGoing(c);
			
		}
	}
	
}