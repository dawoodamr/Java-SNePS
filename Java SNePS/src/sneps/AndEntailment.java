/**
 * @(#)AndEntailment.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/24
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

@SuppressWarnings("serial")
public class AndEntailment extends Rule
{
	private boolean shareVars;
	private int reportCounter;
	private int requestCounter;
	private int patternsNumber;
	private int[] vars;
	private NodeSet patternNodes;
	
	/**
	 * Creating the andentailment process
	 * @param node Node
	 */
	public AndEntailment(Node node)
	{
		super(node,"AndEntailment");
		reportCounter=0;
		requestCounter=0;
		patternNodes =getProcess().getNodeSet("&ant");
		shareVars=getProcess().allShareVars(patternNodes);
		patternsNumber=patternNodes.size();
		PatternNode n =(PatternNode)patternNodes.getNode(0);
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
	 * Add a ContextRUIS to ContextRUISSet
	 * @param c Context
	 * @return ContextRUIS
	 */
	public ContextRUIS addContextRUIS(Context c)
	{
		if(shareVars)
			return getProcess().addContextRUIS(c,'s');
		else
		{
			ContextRUIS cr=getProcess().addContextRUIS(c,'p');
			cr.getPtree().buildTree(patternNodes);
			return cr;
		}
	}
	
	/**
	 * process the reports
	 */
	public void processReports()
	{
		for(;reportCounter<getProcess().getReportSet().cardinality();reportCounter++)
		{
			Report r=getProcess().getReportSet().getReport(reportCounter);
			Context c=r.getContext();
			RuleUseInfo rui=null;
			RuleUseInfoSet res=null;
			if(r.getSign())
			{
				FlagNode fn=new FlagNode((PatternNode)r.getSignature()
						,r.getSupport(),1);
				FlagNodeSet fns=new FlagNodeSet();
				fns.putIn(fn);
				rui=new RuleUseInfo(r.getSubstitutions(),1,0,fns);
				int pos=getProcess().getCRS().getIndex(c);
				ContextRUIS crtemp=null;
				if(pos==-1)
					crtemp=addContextRUIS(c);
				else
					crtemp=getProcess().getCRS().getContextRUIS(pos);
				if(shareVars)
				{
					res=crtemp.getSindexing().insert(rui, vars);
				}
				else
				{
					res=crtemp.getPtree().insert(rui);
					if(res==null)
						res=new RuleUseInfoSet();
				}
				for(int i=0;i<res.cardinality();i++)
				{
					RuleUseInfo ruitemp=res.getRuleUseInfo(i);
					if(ruitemp.getPosCount()==patternsNumber)
					{
						Report reply=new Report(ruitemp.getSub(),null,true
								,getProcess().getNode(),null,c);
						ChannelsSet ctemp=crtemp.getChannels();
						getProcess().sendReport(reply,ctemp);
					}
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