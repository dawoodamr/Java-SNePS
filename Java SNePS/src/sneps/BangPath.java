package sneps;

import java.util.Hashtable;
import java.util.LinkedList;

import snebr.Context;
import snebr.Proposition;
import snebr.Support;

/**
 * This class is used to create a weird instance of Path that requires the proposition to be asserted
 * in the given context, i.e. if a segment of a path requires all propositions in it to be asserted
 * then this segment is added as a BangPath
 * 
 * @author Amr Khaled Dawood
 */
public class BangPath extends Path
{

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node, snebr.Context)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> follow(Node node,LinkedList<Support> supports,Context context)
	{
		Hashtable<Node,LinkedList<Support>> result = new Hashtable<Node,LinkedList<Support>>();
		Entity e = node.getEntity();
		if(e.getClass().getSimpleName().equals("Proposition") &&
				context.getHypSet().getPropositionSet().contains(e))
		{
			for(int i=0;i<supports.size();i++)
				supports.get(i).addToOriginSet((Proposition) e);
			result.put(node,supports);
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node, snebr.Context)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> followConverse(Node node,LinkedList<Support> supports,Context context)
	{
		Hashtable<Node,LinkedList<Support>> result = new Hashtable<Node,LinkedList<Support>>();
		Entity e = node.getEntity();
		if(e.getClass().getSimpleName().equals("Proposition") &&
				context.getHypSet().getPropositionSet().contains(e))
		{
			for(int i=0;i<supports.size();i++)
				supports.get(i).addToOriginSet((Proposition) e);
			result.put(node,supports);
		}
		
		return result;
	}

}
