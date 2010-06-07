package sneps;

import java.util.Hashtable;
import java.util.LinkedList;

import snebr.Context;
import snebr.Support;

/**
 * The converse path is a path that is reversed. It can be followed by following the converse
 * of the path inside. 
 * 
 * @author Amr Khaled Dawood
 */
public class ConversePath extends Path
{
	
	/**
	 * the path that the conversion is applied to
	 */
	private Path path;
	
	/**
	 * @param path a Path that its converse will be obtained by this converse path
	 */
	public ConversePath(Path path)
	{
		this.path = path;
	}

	/**
	 * @return a Path that its converse is obtained by this path
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
		return path.followConverse(node,supports,context);
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node, java.util.LinkedList, snebr.Context)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> followConverse(Node node,LinkedList<Support> supports,Context context)
	{
		return path.follow(node,supports,context);
	}

}
