/**
 * @(#)AndOr.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/26
 */

package snip.fns;

import java.util.LinkedList;

import sneps.NodeSet;
import sneps.PatternNode;
import sneps.VariableNode;
import snip.ds.FlagNode;
import snip.ds.FlagNodeSet;
import snip.ds.Process;
import snip.ds.Report;
import snip.ds.RuleUseInfo;
import snip.ds.RuleUseInfoSet;
import snip.ds.Sindexing;

public class AndOr
{
	Process p;
	int min;
	int max;
	int total;
	boolean shareVars;
	Sindexing si;
	RuleUseInfoSet ruis;
	int reportCounter;
	
	/**
	 * Creating the AndOr process
	 * @param p
	 */
	public AndOr(Process p)
	{
		this.p=p;
		reportCounter=0;
		NodeSet minNode =p.getNodeSet("min");
		min=Integer.parseInt(minNode.getNodes().get(0).getIdentifier());
		NodeSet maxNode =p.getNodeSet("max");
		max=Integer.parseInt(maxNode.getNodes().get(0).getIdentifier());
		NodeSet patternNodes =p.getNodeSet("arg");
		total=patternNodes.getNodes().size();
		shareVars=p.allShareVars(patternNodes);
		if(shareVars)
		{
			si=new Sindexing();
		}
		else
		{
			ruis=new RuleUseInfoSet();
		}
	}
	
	/**
	 * Run the andor test 
	 */
	public void run()
	{
		for(;reportCounter<p.getReportSet().cardinality();reportCounter++)
		{
			Report r=p.getReportSet().getReport(reportCounter);
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
			if(shareVars)
			{
				LinkedList<VariableNode> varsll=rui.getFlagNodeSet().getFlagNode(0)
				.getNode().getFreeVariables();
				int[] vars=new int [varsll.size()];
				for(int i=0;i<vars.length;i++)
				{
					vars[i]=varsll.get(i).getId();
				}
				res=si.insert(rui, vars);
			}
			else
			{
				res=ruis.insert(rui);
				if(res==null)
					res=new RuleUseInfoSet();
			}
			for(int i=0;i<res.cardinality();i++)
			{
				if(res.getRuleUseInfo(i).getPosCount()==max)
				{
					//What?
				}
				else if(res.getRuleUseInfo(i).getNegCount()==total-min)
				{
					//What?
				}
			}
		}
	}
	
}