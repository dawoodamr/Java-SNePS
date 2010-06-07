package sneps;

import java.util.Hashtable;
import java.util.LinkedList;

import snebr.Context;
import snebr.Support;

/**
 * An irreflexive restrict path is a path that does not lead to the same start node
 * 
 * @author Amr Khaled Dawood
 */
public class IrreflexiveRestrictPath extends Path
{
	
	/**
	 * the path defined for the irreflexive restrict path
	 */
	private Path path;

	/**
	 * @param path the specified Path for the irreflexive restrict path
	 */
	public IrreflexiveRestrictPath(Path path)
	{
		this.path = path;
	}

	/**
	 * @return a Path that is defined for this irreflexive restrict path
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
		Hashtable<Node,LinkedList<Support>> res = path.follow(node,supports,context);
		if(res.containsKey(node))
			res.remove(node);
		return res;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node, java.util.LinkedList, snebr.Context)
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
