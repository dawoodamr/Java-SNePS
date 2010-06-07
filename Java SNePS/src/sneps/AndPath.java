package sneps;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

import snebr.Context;
import snebr.Support;

/**
 * An AndPath is a path that contains a list of paths. An AndPath may lead to a node 
 * if and only if all the paths it contains will lead to that node
 * 
 * @author Amr Khaled Dawood
 */
public class AndPath extends Path
{
	
	/**
	 * the list of paths for this AndPath
	 */
	private LinkedList<Path> paths;

	/**
	 * @param paths a LinkedList of paths for the AndPath
	 */
	public AndPath(LinkedList<Path> paths)
	{
		this.paths = paths;
	}

	/**
	 * @return a LinkedList of Paths that are used to create this AndPath
	 */
	public LinkedList<Path> getPaths()
	{
		return paths;
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node, java.util.LinkedList, snebr.Context)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> follow(Node node,LinkedList<Support> supports,Context context)
	{
		if(paths.isEmpty())
			return new Hashtable<Node, LinkedList<Support>>();
		LinkedList<Path> rest = new LinkedList<Path>();
		rest.addAll(this.paths);
		Path p = rest.removeFirst();
		AndPath andPath = new AndPath(rest);
		if(rest.size()>0)
			return intersectionAnd(p.follow(node,supports,context),andPath.follow(node,supports,context));
		else
			return p.follow(node,supports,context);
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node, java.util.LinkedList, snebr.Context)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> followConverse(Node node,LinkedList<Support> supports,Context context)
	{
		if(paths.isEmpty())
			return new Hashtable<Node, LinkedList<Support>>();
		LinkedList<Path> rest = new LinkedList<Path>();
		rest.addAll(this.paths);
		Path p = rest.removeFirst();
		AndPath andPath = new AndPath(rest);
		if(rest.size()>0)
			return intersectionAnd(p.followConverse(node,supports,context),andPath.followConverse(node,supports,context));
		else
			return p.followConverse(node,supports,context);
	}
	
	/**
	 * returns the intersection between the two hash tables
	 * 
	 * @param ns1 a Hashtable of Nodes and Supports
	 * @param ns2 a Hashtable of Nodes and Supports
	 * @return a Hashtable of Nodes and their Supports resulting from the intersection of 
	 * the two given Hashtables
	 */
	private Hashtable<Node,LinkedList<Support>> intersectionAnd(Hashtable<Node,LinkedList<Support>> h1,Hashtable<Node,LinkedList<Support>> h2)
	{
		Hashtable<Node,LinkedList<Support>> h = new Hashtable<Node,LinkedList<Support>>();
		Enumeration<LinkedList<Support>> lists1 = h1.elements();
		Enumeration<Node> nodes1 = h1.keys();
		for(;nodes1.hasMoreElements();)
		{
			Node node = nodes1.nextElement();
			LinkedList<Support> list = lists1.nextElement();
			if(h2.containsKey(node))
			{
				LinkedList<Support> temp = h2.get(node);
				permute(list,temp);
				h.put(node,temp);
			}
		}
		
		return h;
	}

}
