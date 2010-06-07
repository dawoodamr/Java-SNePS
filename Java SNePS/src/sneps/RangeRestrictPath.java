package sneps;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

import snebr.Context;
import snebr.Support;

/**
 * A range restrict path is a path that is restricted by considering only nodes that are
 * resulted from following it, if they can be used as start nodes to follow a path that will
 * lead to a specific node
 * 
 * @author Amr Khaled Dawood
 */
public class RangeRestrictPath extends Path
{
	
	/**
	 * the path that leads to nodes reachable by this range restrict path
	 */
	private Path p;
	
	/**
	 * the restriction path
	 */
	private Path q;
	
	/**
	 * the restriction destination node 
	 */
	private Node node;

	/**
	 * @param p the path that will lead to nodes reachable by this range restrict path
	 * @param q the restriction path
	 * @param node the restriction destination node
	 */
	public RangeRestrictPath(Path p,Path q,Node node)
	{
		this.p = p;
		this.q = q;
		this.node = node;
	}

	/**
	 * @return the Path p of this range restrict path
	 */
	public Path getP()
	{
		return p;
	}

	/**
	 * @return q a Path which is the restriction Path
	 */
	public Path getQ()
	{
		return q;
	}

	/**
	 * @return a Node that restricts the destination of path q
	 */
	public Node getNode()
	{
		return node;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node, java.util.LinkedList, snebr.Context)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> follow(Node node,LinkedList<Support> supports,Context context)
	{
		Hashtable<Node,LinkedList<Support>> result = new Hashtable<Node,LinkedList<Support>>();
		Hashtable<Node,LinkedList<Support>> h = p.follow(node,supports,context);
		Enumeration<LinkedList<Support>> lists = h.elements();
		for(Enumeration<Node> e = h.keys();e.hasMoreElements();)
		{
			Node n = e.nextElement();
			LinkedList<Support> list = lists.nextElement();
			Hashtable<Node,LinkedList<Support>> temp = q.follow(n,list,context);
			if(temp.containsKey(this.node))
			{
				permute(temp.get(this.node),list);
				result.put(n,list);
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node, java.util.LinkedList, snebr.Context)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> followConverse(Node node,LinkedList<Support> supports,Context context)
	{
		return new DomainRestrictPath(q,node,p).follow(node,supports,context);
	}

}
