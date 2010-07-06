package sneps;

import java.util.LinkedList;

import snebr.Context;

/**
 * A special kind of paths that returns the start node if followed
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class EmptyPath extends Path
{

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node, sneps.PathTrace, snebr.Context)
	 */
	@Override
	public LinkedList<Object[]> follow(Node node,PathTrace trace,Context context)
	{
		LinkedList<Object[]> result = new LinkedList<Object[]>();
		Object[] o = new Object[2];
		o[0] = node;
		o[1] = trace;
		result.add(o);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node, sneps.PathTrace, snebr.Context)
	 */
	@Override
	public LinkedList<Object[]> followConverse(Node node,PathTrace trace,Context context)
	{
		LinkedList<Object[]> result = new LinkedList<Object[]>();
		Object[] o = new Object[2];
		o[0] = node;
		o[1] = trace;
		result.add(o);
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#clone()
	 */
	@Override
	public EmptyPath clone()
	{
		return new EmptyPath();
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#isEqual(sneps.Path)
	 */
	@Override
	public boolean isEqualTo(Path path) 
	{
		return path.getClass().getSimpleName().equals("EmptyPath");
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "empty-path";
	}

}
