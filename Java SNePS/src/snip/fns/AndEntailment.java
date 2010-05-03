/**
 * @(#)AndEntailment.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/24
 */
package snip.fns;

import java.util.LinkedList;

import sneps.NodeSet;
import sneps.PatternNode;
import sneps.VariableNode;
import snip.ds.FlagNode;
import snip.ds.FlagNodeSet;
import snip.ds.Process;
import snip.ds.Ptree;
import snip.ds.Report;
import snip.ds.RuleUseInfo;
import snip.ds.RuleUseInfoSet;
import snip.ds.Sindexing;

public class AndEntailment
{
	Process p;
	boolean shareVars;
	Sindexing si;
	Ptree pt;
	int reportCounter;
	int patternsNumber;
	/**
	 * Creating the andentailment process and give it the process p
	 * @param p process
	 */
	public AndEntailment(Process p)
	{
		this.p=p;
		reportCounter=0;
		NodeSet patternNodes =p.getNodeSet("ant");
		shareVars=p.allShareVars(patternNodes);
		patternsNumber=patternNodes.getNodes().size();
		if(shareVars)
		{
			si=new Sindexing();
		}
		else
		{
			pt=new Ptree();
			int [] patsIds=new int [patternsNumber];
			for(int i=0;i<patternsNumber;i++)
			{
				patsIds[i]=patternNodes.getNodes().get(i).getId();
			}
			pt.buildTree(patsIds);
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
			RuleUseInfo rui=null;
			RuleUseInfoSet res;
			if(r.getSign())
			{
				FlagNode fn=new FlagNode((PatternNode)r.getSignature()
						,r.getSupport(),1);
				FlagNodeSet fns=new FlagNodeSet();
				fns.putIn(fn);
				rui=new RuleUseInfo(r.getSubstitutions(),1,0,fns);
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
					res=pt.insert(rui);
					if(res==null)
						res=new RuleUseInfoSet();
				}
				for(int i=0;i<res.cardinality();i++)
				{
					if(res.getRuleUseInfo(i).getPosCount()==patternsNumber)
					{
						//What?
					}
				}
			}
		}
	}
	
	
}