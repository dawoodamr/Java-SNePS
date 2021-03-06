/**
 * @(#)Process.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/21
 */
package snip.ds;

import java.util.LinkedList;

import match.ds.Substitutions;
import snebr.Context;
import sneps.MolecularNode;
import sneps.Node;
import sneps.NodeSet;
import sneps.PatternNode;
import snip.fns.QueuesProcessor;

public class Process
{
	private char name;
	private Node node;
	private ReportSet knownInstances;
	private ReportSet reps;
	private RequestSet reqs;
	private ChannelsSet outGoing;
	private ChannelsSet inComing;
	private ChannelsSet consequentChannel;
	private ContextRUISSet crs;
	private QueuesProcessor qp;
	private boolean first;
	
	/**
	 * Create a new process
	 * @param node Node
	 * @param n 'r' for "RULE" or 'n' for "NON-RULE"
	 * null otherwise
	 */
	public Process(Node node, char n)
	{
		name =n;
		this.node=node;
		knownInstances=new ReportSet();
		reps=new ReportSet();
		reqs=new RequestSet();
		outGoing=new ChannelsSet();
		inComing=new ChannelsSet();
		consequentChannel=new ChannelsSet();
		crs=new ContextRUISSet();
		first=false;
	}
	
	/**
	 * Return the in coming channels list
	 * @return ChannelsSet
	 */
	public ChannelsSet getInComing()
	{
		return inComing;
	}
	
	/**
	 * Returns the reports 
	 * @return ReportSet
	 */
	public ReportSet getReportSet()
	{
		return reps;
	}
	
	/**
	 * Return the list of all sent reports
	 * @return ReportSet
	 */
	public ReportSet getSentReports()
	{
		return knownInstances;
	}
	
	/**
	 * Add a report to the sent reports list
	 * @param r report
	 */
	public void addToSent(Report r)
	{
		knownInstances.putIn(r);
	}
	
	/**
	 * Returns the list of out going channels
	 * @return ChannelsSet
	 */
	public ChannelsSet getOutGoing()
	{
		return outGoing;
	}
	
	/**
	 * Return the node this process is associated to
	 * @return Node
	 */
	public Node getNode()
	{
		return node;
	}
	
	/**
	 * Return the pattern node set of this node
	 * @param name the relation name
	 * @return NodeSet
	 */
	public NodeSet getNodeSet(String name)
	{
		MolecularNode n=(MolecularNode)node;
		return n.getCableSet().getCable(name).getNodeSet();
	}
	
	/**
	 * Check if all patterns share all variables or not
	 * @return true or false
	 */
	public boolean allShareVars(NodeSet nodes)
	{
		PatternNode n=(PatternNode)nodes.getNode(0);
		boolean res=true;
		for(int i=1;i<nodes.size();i++)
		{
			if(!n.haveSameFreeVariables((PatternNode)nodes.getNode(i)))
			{
				res=false;
				break;
			}
		}
		return res;
	}
	
	/**
	 * Return the consequent channel set
	 * @return ChannelsSet
	 */
	public ChannelsSet getCQChannels()
	{
		return consequentChannel;
	}
	
	/**
	 * Return the ContextRUISSet
	 * @return ContextRUISSet
	 */
	public ContextRUISSet getCRS()
	{
		return crs;
	}
	
	/**
	 * Add a ContextRUIS to the ContextRUISSet and return it.
	 * @param c Context
	 * @param t 's' for Sindexing, 'p' for Ptree and 'r' for RuleUseInfoSet
	 * @return ContextRUIS
	 */
	public ContextRUIS addContextRUIS(Context c, char t)
	{
		ChannelsSet ctemp=consequentChannel.getConChannelsSet(c);
		ContextRUIS cr;
		if(t=='s')
			cr=new ContextRUIS(c,ctemp,'s');
		else if(t=='p')
		{
			cr=new ContextRUIS(c,ctemp,'p');
		}
		else
		{
			cr=new ContextRUIS(c,ctemp,'r');
		}
		crs.putIn(cr);
		return cr;
	}
	
	/**
	 * Send r
	 * @param r Report
	 */
	public void sendReport(Report r, ChannelsSet c)
	{
		knownInstances.putIn(r);
		if(!first)
		{
			for(int i=0;i<c.cardinality();i++)
			{
				System.out.println("Sending report from "+ node.getIdentifier()+
						" to "+c.getChannel(i).getDestination().getNode()
							.getIdentifier());
				qp.addToHigh(c.getChannel(i).getDestination().getNode()
						.getEntity());
				c.getChannel(i).send(r);
			}
		}
	}
	
	/**
	 * When report is received put it in the report list
	 * @param r Report
	 */
	public void receiveReport(Report r)
	{
		reps.putIn(r);
	}
	
	/**
	 * When report is received put it in the report list
	 * @param r Report
	 */
	public void receiveRequest(Request r)
	{
		reqs.putIn(r);
	}
	
	/**
	 * Return the request set
	 * @return RequestSet
	 */
	public RequestSet getRequestSet()
	{
		return reqs;
	}
	
	/**
	 * Add a channel to the out going channels
	 * @param c Channel
	 */
	public void addOutGoing(Channel c)
	{
		if(!outGoing.isMember(c))
		{
			sendKnown(c);
			outGoing.putIn(c);
			if(name=='r')
			{
				consequentChannel.putIn(c);
				for(int i=0;i<crs.cardinality();i++)
				{
					ContextRUIS cr=crs.getContextRUIS(i);
					if(cr.getContext()==c.getContext())
					{
						cr.addChannel(c);
					}
				}
			}
		}
	}
	
	/**
	 * Send all known Instances in the channel c
	 * @param c Channel
	 */
	public void sendKnown(Channel c)
	{
		System.out.println("Sending all known instances to " + 
				c.getDestination().getNode().getIdentifier());
		boolean set=false;
		for(int i=0;i<knownInstances.cardinality();i++)
		{
			Report r=knownInstances.getReport(i);
			if(r.getContext()==c.getContext())
			{
				if(!set)
				{
					qp.addToHigh(c.getDestination().getNode().getEntity());
					set=true;
				}
				c.send(r);
			}
		}
	}
	
	/**
	 * Send requests to every node in the node set ns with the context c
	 * @param ns NodeSet
	 * @param c Context
	 */
	public void sendRequests(NodeSet ns,Context c)
	{
		for(int i=0;i<ns.size();i++)
		{
			Node to=ns.getNode(i);
			System.out.println("Sending request from "+ node.getIdentifier()+
					" to "+to.getIdentifier());
			Filter f=new Filter(new Substitutions());
			Switch s=new Switch(new Substitutions());
			Destination d=new Destination(node);
			Channel ch=new Channel(f,s,c,d,true);
			System.out.println(ch);
			inComing.putIn(ch);
			qp.addToLow(to.getEntity());
			to.getEntity().getProcess().setQueuesProcessor(qp);
			Request r=new Request(ch);
			to.getEntity().getProcess().receiveRequest(r);
		}
	}
	
	/**
	 * Set the queues processor to q
	 * @param q QueuesProcessor
	 */
	public void setQueuesProcessor(QueuesProcessor q)
	{
		qp=q;
	}
	
	/**
	 * Check if this node is the first one in the deduction or not
	 * @return true or false
	 */
	public boolean getFirst()
	{
		return first;
	}
	
	/**
	 * Set this node to be the first in the deduction or not 
	 * @param f true or false
	 */
	public void setFirst(boolean f)
	{
		first=f;
	}
	
	/**
	 * Send requests to the nodes in the nodes list.
	 * nodes list is the result from matching, and it is 
	 * {<Node> <Substitutions> <Substitutions>} where the node is the matched node,
	 * the first substitution list is the source binding, and the other substitution
	 * list is the target binding.
	 * @param nodes LinkedList<Object[]>
	 * @param c Context
	 */
	public void sendRequests(LinkedList<Object[]> nodes,Context c)
	{
		for(int i=0;i<nodes.size();i++)
		{
			Node to=(Node)nodes.get(i)[0];
			System.out.println("Sending request from "+ node.getIdentifier()+
					" to "+to.getIdentifier());
			Substitutions sub=(Substitutions)nodes.get(i)[1];
			Substitutions t=(Substitutions)nodes.get(i)[2];
			Substitutions[] sp=sub.split();
			Filter f=new Filter(sp[0]);
			Switch s=new Switch(sp[1]);
			Destination d=new Destination(node);
			Channel ch=new Channel(f,s,c,d,true);
			Substitutions[] sp1=t.split();
			ch.setTarget(sp1[0]);
			System.out.println(ch);
			inComing.putIn(ch);
			qp.addToLow(to.getEntity());
			to.getEntity().getProcess().setQueuesProcessor(qp);
			Request r=new Request(ch);
			to.getEntity().getProcess().receiveRequest(r);
		}
	}
	
}