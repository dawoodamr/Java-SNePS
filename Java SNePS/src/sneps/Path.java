package sneps;

import java.util.Hashtable;
import java.util.LinkedList;

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
	public abstract Hashtable<Node,LinkedList<Support>> follow(Node node,LinkedList<Support> supports,Context context);
	
	/**
	 * @param node the node we want to follow the converse of this path starting at
	 * @param context the Context that we want the nodes to be asserted in
	 * @return a node set of nodes resulted from following the converse of this path starting
	 * at the specified node
	 */
	public abstract Hashtable<Node,LinkedList<Support>> followConverse(Node node,LinkedList<Support> supports,Context context);


	
	/**
	 * adds origin set of each support of the source to all origin sets of all supports of target
	 * so that target will contain all possible permutations of supports of both of the sets
	 * 
	 * @param source the source list whose supports will be added to all supports in the target
	 * @param target the target list that supports of the source will be added to all of its supports
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
