package sneps;

import java.util.Hashtable;
import java.util.LinkedList;

import snebr.Context;
import snebr.Support;

/**
 * an irreflexive restrict path is a path that does not lead to the same start node
 * 
 * @author Amr Khaled Dawood
 */
public class IrreflexiveRestrictPath extends Path
{
	
	/**
	 * the path that leads to nodes other than the starting node
	 */
	private Path path;

	/**
	 * @param path the specified path that is irreflexive restrict
	 */
	public IrreflexiveRestrictPath(Path path)
	{
		this.path = path;
	}

	/**
	 * @return the path that leads to nodes other than the one that it starts at
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
		Hashtable<Node,LinkedList<Support>> res = path.follow(node,supports,context);
		if(res.containsKey(node))
			res.remove(node);
		return res;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> followConverse(Node node,LinkedList<Support> supports,Context context)
	{
		Hashtable<Node,LinkedList<Support>> res = path.followConverse(node,supports,context);
		if(res.containsKey(node))
			res.remove(node);
		return res;
	}

}
