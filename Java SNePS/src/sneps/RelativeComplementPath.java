package sneps;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

import snebr.Context;
import snebr.Support;

/**
 * A relative complement path is a path that consists of two paths where a destination node 
 * can be reached by following the first path, and cannot be reached by following the 
 * second path 
 * 
 * @author Amr Khaled Dawood
 */
public class RelativeComplementPath extends Path
{
	
	/**
	 * the first path that will lead to the nodes reachable by the relative complement path
	 */
	private Path p;
	
	/**
	 * the second path that will decide which nodes to be removed from the obtained
	 * node set after following the first path
	 */
	private Path q;

	/**
	 * @param p the first path
	 * @param q the second path
	 */
	public RelativeComplementPath(Path p,Path q)
	{
		this.p = p;
		this.q = q;
	}

	/**
	 * @return p which is the first Path of this relative complement path
	 */
	public Path getP()
	{
		return p;
	}

	/**
	 * @return q which is the second Path of this relative complement path
	 */
	public Path getQ()
	{
		return q;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node, java.util.LinkedList, snebr.Context)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> follow(Node node,LinkedList<Support> supports,Context context)
	{
		Hashtable<Node,LinkedList<Support>> h1 = p.follow(node,supports,context);
		Hashtable<Node,LinkedList<Support>> h2 = q.follow(node,supports,context);
		Hashtable<Node,LinkedList<Support>> temp = new Hashtable<Node,LinkedList<Support>>();
		temp.putAll(h1);
		Enumeration<Node> nodes = h1.keys();
		for(;nodes.hasMoreElements();)
		{
			Node n = nodes.nextElement();
			if(h2.containsKey(n))
				temp.remove(n);
		}
		return temp;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node, java.util.LinkedList, snebr.Context)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> followConverse(Node node,LinkedList<Support> supports,Context context)
	{
		Hashtable<Node,LinkedList<Support>> h1 = p.followConverse(node,supports,context);
		Hashtable<Node,LinkedList<Support>> h2 = q.followConverse(node,supports,context);
		Hashtable<Node,LinkedList<Support>> temp = new Hashtable<Node, LinkedList<Support>>();
		temp.putAll(h1);
		Enumeration<Node> nodes = h1.keys();
		for(;nodes.hasMoreElements();)
		{
			Node n = nodes.nextElement();
			if(h2.containsKey(n))
				temp.remove(n);
		}
		return temp;
	}

}
