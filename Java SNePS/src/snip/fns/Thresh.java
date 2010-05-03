/**
 * @(#)Thresh.java
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

public class Thresh 
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
	 * Creating the Thresh process
	 * 
	 * @param p
	 *            process
	 */
	public Thresh(Process p) 
	{
		this.p = p;
		reportCounter = 0;
		NodeSet minNode = p.getNodeSet("thresh");
		min = Integer.parseInt(minNode.getNodes().get(0).getIdentifier());
		NodeSet maxNode = p.getNodeSet("threshmax");
		max = Integer.parseInt(maxNode.getNodes().get(0).getIdentifier());
		NodeSet patternNodes = p.getNodeSet("arg");
		total = patternNodes.getNodes().size();
		shareVars = p.allShareVars(patternNodes);
		if (shareVars) 
		{
			si = new Sindexing();
		} 
		else 
		{
			ruis = new RuleUseInfoSet();
		}
	}

	/**
	 * Run the thresh test
	 */
	public void run() 
	{
		for (; reportCounter < p.getReportSet().cardinality(); reportCounter++) 
		{
			Report r = p.getReportSet().getReport(reportCounter);
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
			if (shareVars) 
			{
				LinkedList<VariableNode> varsll = rui.getFlagNodeSet()
						.getFlagNode(0).getNode().getFreeVariables();
				int[] vars = new int[varsll.size()];
				for (int i = 0; i < vars.length; i++) 
				{
					vars[i] = varsll.get(i).getId();
				}
				res = si.insert(rui, vars);
			} 
			else 
			{
				res = ruis.insert(rui);
				if (res == null)
					res = new RuleUseInfoSet();
			}
			for (int i = 0; i < res.cardinality(); i++) 
			{
				if (res.getRuleUseInfo(i).getPosCount() > min 
						&& res.getRuleUseInfo(i).getNegCount()==total-max-1) 
				{
					// What?
				} 
				else if (res.getRuleUseInfo(i).getPosCount() == min-1 
						&& res.getRuleUseInfo(i).getNegCount()>=total-max) 
				{
					// What?
				}
			}
		}
	}

}