package sneps;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

import snebr.Context;
import snebr.Support;

/**
 * A compose path is a path resulted from the composition of multiple paths.
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
	 * @return a LinkedList of Paths of this ComposePath
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
	 * follows the given path starting at all nodes that are indices of the given Hashtable 
	 * 
	 * @param temp a Hashtable of Nodes and their supports
	 * @param context the context that the propositions along this path are asserted in
	 * @param path the path that will be followed starting at nodes in the Hashtable
	 * @return a Hashtable of Nodes and their supports that were resulted from following 
	 * path starting at all nodes in the given Hashtable
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
					permute(l,result.get(n));
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
	 * follows the converse of the given path starting at all nodes that are indices 
	 * of the given Hashtable 
	 * 
	 * @param temp a Hashtable of Nodes and their supports
	 * @param context the context that the propositions along this path are asserted in
	 * @param path the path that its converse will be followed starting at nodes in the Hashtable
	 * @return a Hashtable of Nodes and their supports that were resulted from following 
	 * the converse of path starting at all nodes in the given Hashtable
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
					permute(l,result.get(n));
			}
		}
		return result;
	}

}
