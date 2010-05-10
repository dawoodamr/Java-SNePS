package sneps;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

import snebr.Context;
import snebr.Support;

/**
 * a domain restrict path is a path that is restricted by another path that should lead to 
 * a specified node in order to be valid
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
	 * the node that should be reached from the q path in order to make the p path valid
	 */
	private Node node;
	
	/**
	 * the path that we will follow to reach nodes if it is valid 
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
	 * @return the restriction Path
	 */
	public Path getQ()
	{
		return q;
	}

	/**
	 * @return the node that restricts the destination for the Path q
	 */
	public Node getNode()
	{
		return node;
	}

	/**
	 * @return the path that should be followed
	 */
	public Path getP()
	{
		return p;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node)
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
	 * @see sneps.Path#followConverse(sneps.Node)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> followConverse(Node node,LinkedList<Support> supports,Context context)
	{
		return new RangeRestrictPath(this.p,this.q,this.node).follow(node,supports,context);
	}

}
