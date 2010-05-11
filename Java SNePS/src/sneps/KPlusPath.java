package sneps;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

import snebr.Context;
import snebr.Support;

/**
 * a kplus path is a path that is composed of its self one or more times.
 * 
 * @author Amr Khaled Dawood
 */
public class KPlusPath extends Path
{
	
	/**
	 * the path that the kplus path is composed of it.
	 */
	private Path path;

	/**
	 * @param path the path that the kplus path is composed of
	 */
	public KPlusPath(Path path)
	{
		super();
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
	 * @see sneps.Path#follow(sneps.Node)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> follow(Node node,LinkedList<Support> supports,Context context)
	{
		Hashtable<Node,LinkedList<Support>> temp = path.follow(node,supports,context);
		
		return follow(temp,context);
	}
	
	/**
	 * @param ns the node set we want to follow the path starting at its nodes
	 * @param context the context that propositions in this path are asserted in
	 * @return a node set with the resulted nodes from following the path
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
	 * @see sneps.Path#followConverse(sneps.Node)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> followConverse(Node node,LinkedList<Support> supports,Context context)
	{
		Hashtable<Node,LinkedList<Support>> temp = path.followConverse(node,supports,context);
		
		return followConverse(temp,context);
	}
	
	/**
	 * @param ns the node set we want to follow the converse of the path starting 
	 * at its nodes
	 * @param context the context that propositions in this path are asserted in
	 * @return a node set with the resulted nodes from following the converse of the path
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
	 * @param old the old hash table
	 * @param neu the new hash table
	 * @return true if the neu node set contains elements that does not exist in node set old
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
