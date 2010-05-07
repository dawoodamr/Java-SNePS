package sneps;

import snebr.Context;

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
	public NodeSet follow(Node node, Context context)
	{
		NodeSet result = new NodeSet();
		Entity e = node.getEntity();
		if(e.getClass().getSimpleName().equals("Proposition") &&
				context.getHypSet().getPropositionSet().contains(e))
			result.addNode(node);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node, snebr.Context)
	 */
	@Override
	public NodeSet followConverse(Node node, Context context)
	{
		NodeSet result = new NodeSet();
		Entity e = node.getEntity();
		if(e.getClass().getSimpleName().equals("Proposition") &&
				context.getHypSet().getPropositionSet().contains(e))
			result.addNode(node);
		
		return result;
	}

}
