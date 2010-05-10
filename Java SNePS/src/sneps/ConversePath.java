package sneps;

import java.util.Hashtable;
import java.util.LinkedList;

import snebr.Context;
import snebr.Support;

/**
 * the converse path is a path that is reversed.It can be followed by following the converse
 * of that path. 
 * 
 * @author Amr Khaled Dawood
 */
public class ConversePath extends Path
{
	
	/**
	 * the path that we need to follow its converse
	 */
	private Path path;
	
	/**
	 * @param path the path that we need to get its converse
	 */
	public ConversePath(Path path)
	{
		this.path = path;
	}

	/**
	 * @return the Path that we need to get its converse
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
		return path.followConverse(node,supports,context);
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> followConverse(Node node,LinkedList<Support> supports,Context context)
	{
		return path.follow(node,supports,context);
	}

}
