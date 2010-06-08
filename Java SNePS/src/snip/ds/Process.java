/**
 * @(#)Process.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/21
 */
package snip.ds;

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
	private String type;
	private Node node;
	private ReportSet knownInstances;
	private ReportSet reps;
	private RequestSet reqs;
	private ChannelsSet outGoing;
	private ChannelsSet inComing;
	private ChannelsSet consequentChannel;
	private boolean priority;//true for high false for low
	private boolean uasbility;
	private ContextRUISSet crs;
	private QueuesProcessor qp;
	private boolean first;
	
	/**
	 * Create a new process
	 * @param node Node
	 * @param n 'r' for "RULE" or 'n' for "NON-RULE"
	 * @param t if it is a rule node then type will be the name of the inference rule
	 * null otherwise
	 */
	public Process(Node node, char n, String t)
	{
		name =n;
		type=t;
		this.node=node;
		knownInstances=new ReportSet();
		reps=new ReportSet();
		reqs=new RequestSet();
		outGoing=new ChannelsSet();
		inComing=new ChannelsSet();
		consequentChannel=new ChannelsSet();
		priority=false;
		uasbility=false;
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
		for(int i=0;i<c.cardinality();i++)
		{
			qp.addToHigh(c.getChannel(i).getDestination().getNode().getEntity());
			c.getChannel(i).send(r);
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
		for(int i=0;i<knownInstances.cardinality();i++)
		{
			Report r=knownInstances.getReport(i);
			if(r.getContext()==c.getContext())
			c.send(r);
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
			Substitutions sub=match(node,to);
			Substitutions[] sp=sub.split();
			Filter f=new Filter(sp[0]);
			Switch s=new Switch(sp[1]);
			Destination d=new Destination(node);
			Channel ch=new Channel(f,s,c,d,true);
			System.out.println(ch);
			inComing.putIn(ch);
			qp.addToLow(to.getEntity());
			//to.getEntity().getProcess().setQueuesProcessor(qp);
			//Request r=new Request(ch);
			//to.getEntity().getProcess().addRequest(r);
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
	
	public Substitutions match(Node n1,Node n2)
	{
		return null;
	}
}