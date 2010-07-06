/**
 * @(#)NumericalEntailment.java
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

@SuppressWarnings("serial")
public class NumericalEntailment extends Rule
{
	private int thresh;
	private boolean shareVars;
	private int reportCounter;
	private int[] vars;
	private NodeSet patternNodes;
	private int requestCounter;
	
	/**
	 * Creating the numericalentailment process
	 * @param node Node
	 */
	public NumericalEntailment(Node node)
	{
		super(node,"NumericalEntailment");
		reportCounter=0;
		requestCounter=0;
		NodeSet minNode =getProcess().getNodeSet("thresh");
		thresh=Integer.parseInt(minNode.getNode(0).getIdentifier());
		patternNodes =getProcess().getNodeSet("arg");
		shareVars=getProcess().allShareVars(patternNodes);
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
			return getProcess().addContextRUIS(c,'r');
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
			RuleUseInfo rui;
			RuleUseInfoSet res;
			if(r.getSign())
			{
				FlagNode fn=new FlagNode((PatternNode)r.getSignature()
						,r.getSupport(),1);
				FlagNodeSet fns=new FlagNodeSet();
				fns.putIn(fn);
				rui=new RuleUseInfo(r.getSubstitutions(),1,0,fns);
				int pos=getProcess().getCRS().getIndex(c);
				ContextRUIS crtemp;
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
					if(ruitemp.getPosCount()>=thresh)
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