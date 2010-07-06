package sneps;

import java.io.Serializable;
import java.util.LinkedList;

import snebr.*;

/**
 * The Path class is the super class of all paths. It is an abstract class containing four
 * abstract method: follow, followConverse, isEqualTo, and clone
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public abstract class Path implements Serializable
{

	/**
	 * follows the path starting at node and updates the given PathTrace in the given context
	 * 
	 * @param node a node that this path will be followed starting at
	 * @param trace a PathTrace representing the trace of following this path
	 * @param context a Context that propositions in this path are asserted in
	 * @return a LinkedList of pairs (arrays of size 2): a node and a PathTrace used to reach 
	 * that node
	 */
	public abstract LinkedList<Object[]> follow(Node node,PathTrace trace,Context context);
	
	/**
	 * follows the converse of the path starting at node and updates the given PathTrace in the 
	 * given context
	 * 
	 * @param node a node that the converse of this path will be followed starting at
	 * @param trace a PathTrace representing the trace of following the converse of this path
	 * @param context a Context that propositions in this path are asserted in
	 * @return a LinkedList of pairs (arrays of size 2): a node and a PathTrace used to reach 
	 * that node
	 */
	public abstract LinkedList<Object[]> followConverse(Node node,PathTrace trace,Context context);

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public abstract Path clone();
	
	/**
	 * @param path a Path to check whether it is equal to this one or not
	 * @return true if the given path is equal to this one, and false otherwise
	 */
	public abstract boolean isEqualTo(Path path);
	
}
