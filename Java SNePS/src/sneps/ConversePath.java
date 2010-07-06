package sneps;

import java.util.LinkedList;

import snebr.Context;

/**
 * The converse path is a path that is reversed. It can be followed by following the converse
 * of the path inside. 
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class ConversePath extends Path
{
	
	/**
	 * the path that the conversion is applied to
	 */
	private Path path;
	
	/**
	 * @param path a Path that its converse will be obtained by this converse path
	 */
	public ConversePath(Path path)
	{
		this.path = path;
	}

	/**
	 * @return a Path that its converse is obtained by this path
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
		return path.followConverse(node,trace,context);
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node, sneps.PathTrace, snebr.Context)
	 */
	@Override
	public LinkedList<Object[]> followConverse(Node node,PathTrace trace,Context context)
	{
		return path.follow(node,trace,context);
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#clone()
	 */
	@Override
	public ConversePath clone()
	{
		return new ConversePath(path.clone());
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#isEqual(sneps.Path)
	 */
	@Override
	public boolean isEqualTo(Path path)
	{
		if(! path.getClass().getSimpleName().equals("ConversePath"))
			return false;
		ConversePath con = (ConversePath) path;
		if(! con.path.isEqualTo(this.path))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "converse("+this.path.toString()+")";
	}

}
