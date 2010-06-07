package sneps;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

import snebr.Context;
import snebr.Support;

/**
 * A domain restrict path is a path that is restricted by another path that should lead to 
 * a specified node
 * 
 * @author Amr Khaled Dawood
 */
public class DomainRestrictPath extends Path
{
	
	/**
	 * the restriction path
	 */
	private Path q;
	
	/**
	 * the node that should be reached by following path q in order to make the p path valid
	 */
	private Node node;
	
	/**
	 * the path that will be followed to reach nodes reachable by this path if it is valid 
	 */
	private Path p;

	/**
	 * @param q the restriction path
	 * @param node the restriction node
	 * @param p the path that should be followed
	 */
	public DomainRestrictPath(Path q,Node node,Path p)
	{
		this.q = q;
		this.node = node;
		this.p = p;
	}

	/**
	 * @return a Path that is the restriction of this path
	 */
	public Path getQ()
	{
		return q;
	}

	/**
	 * @return a Node that restricts the destination for the Path q of this path
	 */
	public Node getNode()
	{
		return node;
	}

	/**
	 * @return a Path that should be followed for this path
	 */
	public Path getP()
	{
		return p;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node, java.util.LinkedList, snebr.Context)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> follow(Node node,LinkedList<Support> supports,Context context)
	{
		Hashtable<Node,LinkedList<Support>> result = new Hashtable<Node,LinkedList<Support>>();
		Hashtable<Node,LinkedList<Support>> h = q.follow(node,supports,context);
		LinkedList<Support> sup = new LinkedList<Support>();
		if(h.containsKey(this.node))
		{
			result = p.follow(node,supports,context);
			sup = h.get(this.node);
		}
		Enumeration<Node> nodes = result.keys();
		for(;nodes.hasMoreElements();)
		{
			permute(sup,result.get(nodes.nextElement()));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node, java.util.LinkedList, snebr.Context)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> followConverse(Node node,LinkedList<Support> supports,Context context)
	{
		return new RangeRestrictPath(this.p,this.q,this.node).follow(node,supports,context);
	}

}
