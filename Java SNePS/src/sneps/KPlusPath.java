package sneps;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

import snebr.Context;
import snebr.Support;

/**
 * A kplus path is a path that is composed with itself one or more times.
 * 
 * @author Amr Khaled Dawood
 */
public class KPlusPath extends Path
{
	
	/**
	 * the path that is composed with itself to obtain the kplus path
	 */
	private Path path;

	/**
	 * @param path the Path that the kplus path is composed of
	 */
	public KPlusPath(Path path)
	{
		this.path = path;
	}

	/**
	 * @return the path that is composed with itself to get this KPlusPath
	 */
	public Path getPath()
	{
		return path;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node, java.util.LinkedList, snebr.Context)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> follow(Node node,LinkedList<Support> supports,Context context)
	{
		Hashtable<Node,LinkedList<Support>> temp = path.follow(node,supports,context);
		
		return follow(temp,context);
	}
	
	/**
	 * follows this path starting at nodes in the hash table temp in the given context
	 * 
	 * @param temp a Hashtable of nodes that following this path will start at along with their 
	 * supports
	 * @param context the context that propositions in this path are asserted in
	 * @return a Hashtable of nodes and their supports resulted from following this path
	 */
	@SuppressWarnings("unchecked")
	private Hashtable<Node,LinkedList<Support>> follow(Hashtable<Node,LinkedList<Support>> temp,Context context)
	{
		Enumeration<Node> nodes = temp.keys();
		Enumeration<LinkedList<Support>> lists = temp.elements();
		Hashtable<Node,LinkedList<Support>> h = (Hashtable<Node,LinkedList<Support>>) temp.clone();
		for(;nodes.hasMoreElements();)
		{
			LinkedList<Support> list = lists.nextElement();
			// following the path for a node and adding the resulted nodes 
			Hashtable<Node,LinkedList<Support>> r = path.follow(nodes.nextElement(),list,context);
			Enumeration<LinkedList<Support>> tl = r.elements();
			for(Enumeration<Node> tn = r.keys();tn.hasMoreElements();)
			{
				LinkedList<Support> l = tl.nextElement();
				Node node = tn.nextElement();
				if(! temp.containsKey(node))
					temp.put(node,l);
				else
					permute(l,temp.get(node));
			}
		}
		if(wasChanged(h,temp))
			return follow(temp,context);
		
		
		return temp;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node, java.util.LinkedList, snebr.Context)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> followConverse(Node node,LinkedList<Support> supports,Context context)
	{
		Hashtable<Node,LinkedList<Support>> temp = path.followConverse(node,supports,context);
		
		return followConverse(temp,context);
	}
	
	/**
	 * follows the converse of this path starting at nodes in the hash table temp in the given context
	 * 
	 * @param temp a Hashtable of nodes that following the converse of this path will start 
	 * at along with their supports
	 * @param context the context that propositions in this path are asserted in
	 * @return a Hashtable of nodes and their supports resulted from following the converse of 
	 * this path
	 */
	@SuppressWarnings("unchecked")
	private Hashtable<Node,LinkedList<Support>> followConverse(Hashtable<Node,LinkedList<Support>> temp,Context context)
	{
		Enumeration<Node> nodes = temp.keys();
		Enumeration<LinkedList<Support>> lists = temp.elements();
		Hashtable<Node,LinkedList<Support>> h = (Hashtable<Node,LinkedList<Support>>) temp.clone();
		for(;nodes.hasMoreElements();)
		{
			LinkedList<Support> list = lists.nextElement();
			// following the path for a node and adding the resulted nodes 
			Hashtable<Node,LinkedList<Support>> r = path.followConverse(nodes.nextElement(),list,context);
			Enumeration<LinkedList<Support>> tl = r.elements();
			for(Enumeration<Node> tn = r.keys();tn.hasMoreElements();)
			{
				LinkedList<Support> l = tl.nextElement();
				Node node = tn.nextElement();
				if(! temp.containsKey(node))
					temp.put(node,l);
				else
					permute(l,temp.get(node));
			}
		}
		if(wasChanged(h,temp))
			return followConverse(temp,context);
		
		
		return temp;
	}
	
	/**
	 * checks whether two hash tables contain the same nodes or not
	 * 
	 * @param old a Hashtable
	 * @param neu another Hashtable
	 * @return false if both hash tables contain the same nodes, and true otherwise
	 */
	private boolean wasChanged(Hashtable<Node,LinkedList<Support>> old,Hashtable<Node,LinkedList<Support>> neu)
	{
		Enumeration<Node> enneu = neu.keys();
		for(;enneu.hasMoreElements();)
		{
			if(! old.containsKey(enneu.nextElement()))
				return true;
		}
		return false;
	}

}
