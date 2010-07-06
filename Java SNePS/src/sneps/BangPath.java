package sneps;

import java.util.LinkedList;

import snebr.Context;

/**
 * This class is used to create a special instance of Path that requires the proposition to be asserted
 * in the given context, i.e. if a segment of a path requires all propositions in it to be asserted
 * then this segment is added as a BangPath
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class BangPath extends Path
{
	
	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node, sneps.PathTrace, snebr.Context)
	 */
	@Override
	public LinkedList<Object[]> follow(Node node,PathTrace trace,Context context)
	{
		LinkedList<Object[]> result = new LinkedList<Object[]>();
		// if asserted
		if((node.getEntity().getSuperClasses().contains("Proposition") ||
				node.getEntity().getClass().getSimpleName().equals("Proposition")) &&
				context.getHypSet().getPropositionSet().contains(node.getEntity()))
		{
			PathTrace pt = trace.clone();
			pt.addSupport(node);
			// add the pair to the result
			Object[] o = new Object[2];
			o[0] = node;
			o[1] = pt;
			result.add(o);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node, sneps.PathTrace, snebr.Context)
	 */
	@Override
	public LinkedList<Object[]> followConverse(Node node,PathTrace trace,Context context)
	{
		LinkedList<Object[]> result = new LinkedList<Object[]>();
		// if asserted
		if((node.getEntity().getSuperClasses().contains("Proposition") ||
				node.getEntity().getClass().getSimpleName().equals("Proposition")) &&
				context.getHypSet().getPropositionSet().contains(node.getEntity()))
		{
			PathTrace pt = trace.clone();
			pt.addSupport(node);
			// add the pair to the result
			Object[] o = new Object[2];
			o[0] = node;
			o[1] = pt;
			result.add(o);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#clone()
	 */
	@Override
	public Path clone()
	{
		return new BangPath();
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#isEqual(sneps.Path)
	 */
	@Override
	public boolean isEqualTo(Path path)
	{
		return path.getClass().getSimpleName().equals("BangPath");
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "!";
	}
}
