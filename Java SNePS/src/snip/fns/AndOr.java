/**
 * @(#)AndOr.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/26
 */

package snip.fns;

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
import snip.ds.Process;
import snip.ds.Report;
import snip.ds.RuleUseInfo;
import snip.ds.RuleUseInfoSet;

public class AndOr
{
	private Process p;
	private int min;
	private int max;
	private int total;
	private boolean shareVars;
	private int reportCounter;
	private int[] vars;
	private PatternNode []pns;
	
	/**
	 * Creating the AndOr process
	 * @param node Node
	 */
	public AndOr(Node node)
	{
		p=new Process(node,'r',"AndOr");
		reportCounter=0;
		NodeSet minNode =p.getNodeSet("min");
		min=Integer.parseInt(minNode.getNodes().get(0).getIdentifier());
		NodeSet maxNode =p.getNodeSet("max");
		max=Integer.parseInt(maxNode.getNodes().get(0).getIdentifier());
		NodeSet patternNodes =p.getNodeSet("arg");
		total=patternNodes.getNodes().size();
		LinkedList<Node> nodes=patternNodes.getNodes();
		pns=new PatternNode[total]; 
		for(int i=0;i<total;i++)
		{
			pns[i]=(PatternNode)nodes.get(i);
		}
		PatternNode n =(PatternNode)patternNodes.getNodes().get(0);
		shareVars=p.allShareVars(patternNodes);
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
			return p.addContextRUIS(c,'s');
		else
			return p.addContextRUIS(c,'r');
	}
	
	/**
	 * Run the andor test 
	 */
	public void run()
	{
		for(;reportCounter<p.getReportSet().cardinality();reportCounter++)
		{
			Report r=p.getReportSet().getReport(reportCounter);
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
					reply=new Report(ruitemp.getSub(),null,false,p.getNode()
							,null,c);
				}
				else if(ruitemp.getNegCount()==total-min)
				{
					reply=new Report(ruitemp.getSub(),null,true,p.getNode()
							,null,c);
				}
				if(reply!=null)
				{
					ChannelsSet ctemp=getSendIn(ruitemp.getFlagNodeSet(),crtemp);
					p.sendReport(reply,ctemp.getConChannelsSet(c));
				}
			}
		}
	}
}