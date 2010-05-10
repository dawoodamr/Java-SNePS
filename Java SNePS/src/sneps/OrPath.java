package sneps;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

import snebr.Context;
import snebr.Support;

/**
 * an OrPath is a path that contains list of paths. an OrPath will lead us to a destination 
 * node if one of the paths it contains will lead us to this node
 * 
 * @author Amr Khaled Dawood
 */
public class OrPath extends Path
{
	
	/**
	 * the list of paths for the OrPath
	 */
	private LinkedList<Path> paths;

	/**
	 * @param paths the list of paths for the OrPath
	 */
	public OrPath(LinkedList<Path> paths)
	{
		this.paths = paths;
	}

	/**
	 * @return the list of Path that are used to create this OrPath
	 */
	public LinkedList<Path> getPaths()
	{
		return paths;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node)
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
	 * @see sneps.Path#followConverse(sneps.Node)
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
	 * this method gets the union of two node sets
	 * 
	 * @param ns1 the first node set
	 * @param ns2 the second node set
	 * @return the union of the two node sets
	 */
	private Hashtable<Node,LinkedList<Support>> UnionOr(Hashtable<Node,LinkedList<Support>> h1,Hashtable<Node,LinkedList<Support>> h2)
	{
		Hashtable<Node,LinkedList<Support>> h = new Hashtable<Node, LinkedList<Support>>();
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
				for(int i=0;i<l.size();i++)
					h.get(node).add(l.get(i));
		}
		return h;
	}

}
