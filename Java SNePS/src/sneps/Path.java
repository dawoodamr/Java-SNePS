package sneps;

import java.util.Hashtable;
import java.util.LinkedList;

import snebr.*;

/**
 * The Path class is the super class of all paths. It is an abstract class with containing two
 * abstract method: follow and followConverse.
 * 
 * @author Amr Khaled Dawood
 */
public abstract class Path
{
	
	/**
	 * follows the path starting at node and updates the given list of supports in the given context
	 * 
	 * @param node the node that a path will be followed starting at
	 * @param supports a LinkedList of Supports of the given node
	 * @param context the Context that propositions in this path are asserted in
	 * @return a Hash table of nodes and their supports resulted from following the specified 
	 * path starting with the given node
	 */
	public abstract Hashtable<Node,LinkedList<Support>> follow(Node node,LinkedList<Support> supports,Context context);
	
	/**
	 * follows the converse of the path starting at node and updates the given list of supports in the 
	 * given context
	 * 
	 * @param node the node that a converse of a path will be followed starting at
	 * @param supports a LinkedList of Supports of the given node
	 * @param context the Context that propositions in the converse of this path are asserted in
	 * @return a Hash table of nodes and their supports resulted from following the converse of 
	 * the specified path starting with the given node
	 */
	public abstract Hashtable<Node,LinkedList<Support>> followConverse(Node node,LinkedList<Support> supports,Context context);

	/**
	 * adds origin set of each support of the source to all origin sets of all supports of target
	 * so that target will contain all possible combinations of supports of both of the sets
	 * 
	 * @param source a LinkedList of supports whose supports will be added to all supports in the target
	 * @param target a LinkedList of supports that supports of the source will be added to all of 
	 * its supports
	 */
	@SuppressWarnings({ "unchecked" })
	protected void permute(LinkedList<Support> source,LinkedList<Support> target)
	{
		LinkedList<Support> copy = (LinkedList<Support>) target.clone();
		for(int i=0;i<source.size();i++)
			target.addAll(copy);
		for(int i=0;i<source.size();i++)
		{
			int rep = 0;
			int count = copy.size();
			Support s = source.get(i);
			for(int j=rep*count;j<(rep*count)+count;j++)
			{
				Support t = copy.get(j);
				t.getOrginSet().getPropositionSet().addAll(s.getOrginSet().getPropositionSet());
			}
			rep++;
		}
	}
	
}
