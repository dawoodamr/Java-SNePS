package sneps;

import snebr.*;

/**
 * the Path class is the super class of all paths.It is an abstract class with only one
 * abstract method called follow.
 * 
 * @author Amr Khaled Dawood
 */
public abstract class Path
{
	
	/**
	 * @param node the node that we want to follow the path from
	 * @param context the Context that we want the nodes to be asserted in
	 * @return a node set of nodes resulted from following the specified path starting 
	 * with the specified node
	 */
	public abstract NodeSet follow(Node node,Context context);
	
	/**
	 * @param node the node we want to follow the converse of this path starting at
	 * @param context the Context that we want the nodes to be asserted in
	 * @return a node set of nodes resulted from following the converse of this path starting
	 * at the specified node
	 */
	public abstract NodeSet followConverse(Node node,Context context);

}
