/**
 * @(#)AndEntailment.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/24
 */
package snip.fns;

import java.util.LinkedList;

import snebr.Context;
import sneps.Node;
import sneps.NodeSet;
import sneps.PatternNode;
import sneps.VariableNode;
import snip.ds.ChannelsSet;
import snip.ds.ContextRUIS;
import snip.ds.FlagNode;
import snip.ds.FlagNodeSet;
import snip.ds.Process;
import snip.ds.Report;
import snip.ds.RuleUseInfo;
import snip.ds.RuleUseInfoSet;

public class AndEntailment
{
	private Process p;
	private boolean shareVars;
	private int reportCounter;
	private int patternsNumber;
	private int[] vars;
	/**
	 * Creating the andentailment process
	 * @param node Node
	 */
	public AndEntailment(Node node)
	{
		p=new Process(node,'r',"AndEntailment");
		reportCounter=0;
		NodeSet patternNodes =p.getNodeSet("ant");
		shareVars=p.allShareVars(patternNodes);
		patternsNumber=patternNodes.getNodes().size();
		PatternNode n =(PatternNode)patternNodes.getNodes().get(0);
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
			return p.addContextRUIS(c,'s');
		else
		{
			ContextRUIS cr=p.addContextRUIS(c,'p');
			NodeSet patternNodes =p.getNodeSet("ant");
			int [] patsIds=new int [patternsNumber];
			for(int i=0;i<patternsNumber;i++)
			{
				patsIds[i]=patternNodes.getNodes().get(i).getId();
			}
			cr.getPtree().buildTree(patsIds);
			return cr;
		}
	}
	
	/**
	 * Run the andentailment test
	 */
	public void run()
	{
		for(;reportCounter<p.getReportSet().cardinality();reportCounter++)
		{
			Report r=p.getReportSet().getReport(reportCounter);
			Context c=r.getContext();
			RuleUseInfo rui=null;
			RuleUseInfoSet res;
			if(r.getSign())
			{
				FlagNode fn=new FlagNode((PatternNode)r.getSignature()
						,r.getSupport(),1);
				FlagNodeSet fns=new FlagNodeSet();
				fns.putIn(fn);
				rui=new RuleUseInfo(r.getSubstitutions(),1,0,fns);
				int pos=p.getCRS().getIndex(c);
				ContextRUIS crtemp;
				if(pos==-1)
					crtemp=addContextRUIS(c);
				else
					crtemp=p.getCRS().getContextRUIS(pos);
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
						Report reply=new Report(ruitemp.getSub(),null,true,p.getNode()
								,null,c);
						ChannelsSet ctemp=crtemp.getChannels();
						p.sendReport(reply,ctemp);
					}
				}
			}
		}
	}
	
	
}