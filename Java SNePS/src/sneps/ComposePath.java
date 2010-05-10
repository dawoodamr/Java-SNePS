package sneps;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

import snebr.Context;
import snebr.Support;

/**
 * a compose path is a path resulted from composing multiple paths.
 * 
 * @author Amr Khaled Dawood
 */
public class ComposePath extends Path
{
	
	/**
	 * the list of paths that represent the composed path
	 */
	private LinkedList<Path> paths;

	/**
	 * @param paths a list of paths that represent the composed path
	 */
	public ComposePath(LinkedList<Path> paths)
	{
		this.paths = paths;
	}

	/**
	 * @return the list of Paths that are composed together
	 */
	public LinkedList<Path> getPaths()
	{
		return paths;
	}

	/* (non-Javadoc)
	 * @see ds.Path#follow(ds.Node)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> follow(Node node,LinkedList<Support> supports,Context context)
	{
		if(! paths.isEmpty())
		{
			LinkedList<Path> sublist = new LinkedList<Path>();
			sublist.addAll(paths);
			Path p = sublist.removeFirst();
			ComposePath cPath = new ComposePath(sublist);
			Hashtable<Node,LinkedList<Support>> temp = p.follow(node,supports,context);
			return follow(temp,context,cPath);
		}
		return new Hashtable<Node,LinkedList<Support>>();
	}
	
	/**
	 * @param nodeSet a node set of nodes we want to follow the path from
	 * @param context the context that we need to make sure that all proposition in this path
	 * are asserted in
	 * @param path the path we want to follow starting at nodes in the node set
	 * @return the node set of the resulted nodes from following the path starting at
	 * the specified node set
	 */
	private Hashtable<Node,LinkedList<Support>> follow(Hashtable<Node,LinkedList<Support>> temp,Context context,ComposePath path)
	{
		if(path.getPaths().isEmpty())
			return temp;
		Hashtable<Node,LinkedList<Support>> result = new Hashtable<Node,LinkedList<Support>>();
		Enumeration<LinkedList<Support>> tl = temp.elements();
		for(Enumeration<Node> tn = temp.keys();tn.hasMoreElements();)
		{
			LinkedList<Support> list = tl.nextElement();
			Node node = tn.nextElement();
			Hashtable<Node,LinkedList<Support>> h = path.follow(node,list,context);
			Enumeration<LinkedList<Support>> el = h.elements();
			for(Enumeration<Node> en = h.keys();en.hasMoreElements();)
			{
				Node n = en.nextElement();
				LinkedList<Support> l = el.nextElement();
				if(! result.containsKey(n))
					{result.put(n,l);
					}
				else
					result.get(n).addAll(l);
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> followConverse(Node node,LinkedList<Support> supports,Context context)
	{
		if(! paths.isEmpty())
		{
			LinkedList<Path> sublist = new LinkedList<Path>();
			sublist.addAll(paths);
			Path p = sublist.removeLast();
			ComposePath cPath = new ComposePath(sublist);
			Hashtable<Node,LinkedList<Support>> temp = p.followConverse(node,supports,context);
			return followConverse(temp,context,cPath);
		}
		return new Hashtable<Node,LinkedList<Support>>();
	}
	
	/**
	 * @param nodeSet a node set of nodes we want to follow the converse of this path
	 * starting at
	 * @param context the context that we need to make sure that all proposition in this path
	 * are asserted in
	 * @param path the path we want to follow it starting at nodes in the node set
	 * @return the node set of the resulted nodes from following the  converse of this 
	 * path starting at the specified node set
	 */
	private Hashtable<Node,LinkedList<Support>> followConverse(Hashtable<Node,LinkedList<Support>> temp,Context context,Path path)
	{
		Hashtable<Node,LinkedList<Support>> result = new Hashtable<Node,LinkedList<Support>>();
		Enumeration<LinkedList<Support>> lists = temp.elements();
		for(Enumeration<Node> tn = temp.keys();tn.hasMoreElements();)
		{
			Node node = tn.nextElement();
			LinkedList<Support> list = lists.nextElement();
			Hashtable<Node,LinkedList<Support>> h = path.followConverse(node,list,context);
			Enumeration<LinkedList<Support>> el = h.elements();
			for(Enumeration<Node> en = h.keys();en.hasMoreElements();)
			{
				Node n = en.nextElement();
				LinkedList<Support> l = el.nextElement();
				if(! result.containsKey(n))
					result.put(n,l);
				else
					result.get(n).addAll(l);
			}
		}
		return result;
	}

}
