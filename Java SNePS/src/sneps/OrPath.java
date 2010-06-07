package sneps;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

import snebr.Context;
import snebr.Support;

/**
 * An OrPath is a path that contains list of paths. An OrPath will lead to a destination 
 * node if one of the paths it contains will lead to this node
 * 
 * @author Amr Khaled Dawood
 */
public class OrPath extends Path
{
	
	/**
	 * a LinkedList of paths for the OrPath
	 */
	private LinkedList<Path> paths;

	/**
	 * @param paths a LinkedList of paths for the OrPath
	 */
	public OrPath(LinkedList<Path> paths)
	{
		this.paths = paths;
	}

	/**
	 * @return the LinkedList of Paths that are used to create this OrPath
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
		rest.addAll(paths);
		Path p = rest.removeFirst();
		OrPath orPath = new OrPath(rest);
		return UnionOr(p.follow(node,supports,context),orPath.follow(node,supports,context));
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
		rest.addAll(paths);
		Path p = rest.removeFirst();
		OrPath orPath = new OrPath(rest);
		return UnionOr(p.followConverse(node,supports,context),orPath.followConverse(node,supports,context));
	}
	
	/**
	 * gets the union of two Hashtables
	 * 
	 * @param h1 the first Hashtable
	 * @param h2 the second Hashtable
	 * @return a Hashtable that is the union of the two Hashtables
	 */
	private Hashtable<Node,LinkedList<Support>> UnionOr(Hashtable<Node,LinkedList<Support>> h1,Hashtable<Node,LinkedList<Support>> h2)
	{
		Hashtable<Node,LinkedList<Support>> h = new Hashtable<Node,LinkedList<Support>>();
		Enumeration<LinkedList<Support>> list1 = h1.elements();
		Enumeration<Node> nodes1 = h1.keys();
		for(;nodes1.hasMoreElements();)
			h.put(nodes1.nextElement(),list1.nextElement());
		Enumeration<LinkedList<Support>> list2 = h2.elements();
		Enumeration<Node> nodes2 = h2.keys();
		for(;nodes2.hasMoreElements();)
		{
			LinkedList<Support> l = list2.nextElement();
			Node node = nodes2.nextElement();
			if(! h.containsKey(node))
				h.put(node,l);
			else
				h.get(node).addAll(l);
		}
		return h;
	}

}
