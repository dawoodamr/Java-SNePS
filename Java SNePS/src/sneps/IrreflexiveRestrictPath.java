package sneps;

import java.util.LinkedList;

import snebr.Context;

/**
 * An irreflexive restrict path is a path that does not lead to the same start node
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class IrreflexiveRestrictPath extends Path
{
	
	/**
	 * the path defined for the irreflexive restrict path
	 */
	private Path path;

	/**
	 * @param path the specified Path for the irreflexive restrict path
	 */
	public IrreflexiveRestrictPath(Path path)
	{
		this.path = path;
	}

	/**
	 * @return the path of this IrreflexiveRestrictPath
	 */
	public Path getPath()
	{
		return path;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node, sneps.PathTrace, snebr.Context)
	 */
	@Override
	public LinkedList<Object[]> follow(Node node,PathTrace trace,Context context)
	{
		LinkedList<Object[]> result = this.path.follow(node,trace,context);
		for(int i=0;i<result.size();i++)
		{
			if(result.get(i)[0].equals(node))
			{
				result.remove(i);
				i--;
			}
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node, sneps.PathTrace, snebr.Context)
	 */
	@Override
	public LinkedList<Object[]> followConverse(Node node,PathTrace trace,Context context)
	{
		LinkedList<Object[]> result = this.path.followConverse(node,trace,context);
		for(int i=0;i<result.size();i++)
		{
			if(result.get(i)[0].equals(node))
			{
				result.remove(i);
				i--;
			}
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#clone()
	 */
	@Override
	public IrreflexiveRestrictPath clone()
	{
		return new IrreflexiveRestrictPath(path.clone());
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#isEqual(sneps.Path)
	 */
	@Override
	public boolean isEqualTo(Path path)
	{
		if(! path.getClass().getSimpleName().equals("IrreflexiveRestrictPath"))
			return false;
		if(! this.path.isEqualTo(((IrreflexiveRestrictPath)path).getPath()))
				return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "irreflexive-restrict(" + this.path.toString() + ")";
	}

}
