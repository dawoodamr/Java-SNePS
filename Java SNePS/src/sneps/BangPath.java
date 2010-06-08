package sneps;

import java.util.Hashtable;
import java.util.LinkedList;

import snebr.Context;
import snebr.Proposition;
import snebr.Support;

/**
 * This class is used to create a special instance of Path that requires the proposition to be asserted
 * in the given context, i.e. if a segment of a path requires all propositions in it to be asserted
 * then this segment is added as a BangPath
 * 
 * @author Amr Khaled Dawood
 */
public class BangPath extends Path
{
	
	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node, java.util.LinkedList, snebr.Context)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> follow(Node node,LinkedList<Support> supports,Context context)
	{
		Hashtable<Node,LinkedList<Support>> result = new Hashtable<Node,LinkedList<Support>>();
		Entity e = node.getEntity();
		if((e.getClass().getSimpleName().equals("Proposition") || 
				e.getClass().getSuperclass().getSimpleName().equals("Proposition")) &&
				context.getHypSet().getPropositionSet().contains(e))
		{
			for(int i=0;i<supports.size();i++)
				supports.get(i).addToOriginSet((Proposition) e);
			result.put(node,supports);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node, java.util.LinkedList, snebr.Context)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> followConverse(Node node,LinkedList<Support> supports,Context context)
	{
		Hashtable<Node,LinkedList<Support>> result = new Hashtable<Node,LinkedList<Support>>();
		Entity e = node.getEntity();
		if((getSuperClasses(e.getClass()).contains("Proposition") || 
				(e.getClass().getSimpleName().equals("Proposition"))) &&
				context.getHypSet().getPropositionSet().contains(e))
		{
			for(int i=0;i<supports.size();i++)
				supports.get(i).addToOriginSet((Proposition) e);
			result.put(node,supports);
		}
		
		return result;
	}
	
	/**
	 * gets a list of super classes' simple names for a given class
	 * 
	 * @param c a Class to get the super classes for
	 * @return a LinkedList of Strings representing the simple names of the super classes 
	 * of the given class
	 */
	@SuppressWarnings("unchecked")
	private LinkedList<String> getSuperClasses(Class c)
	{
		LinkedList<String> list = new LinkedList<String>();
		updateSuperClasses(c,list);
		return list;
	}
	
	/**
	 * updates a list of strings by adding the simple names of super classes of a given class
	 * 
	 * @param c a Class to get super classes for
	 * @param list a LinkedList of Strings representing the names of super classes
	 */
	@SuppressWarnings("unchecked")
	private void updateSuperClasses(Class c,LinkedList<String> list)
	{
		Class superClass = c.getSuperclass();
		if(superClass == null)
			return;
		else
		{
			list.add(superClass.getSimpleName());
			updateSuperClasses(superClass,list);
		}
	}
}
