package sneps;

/**
 * the converse path is a path that is reversed.It can be followed by following the converse
 * of that path. 
 * 
 * @author Amr Khaled Dawood
 */
public class ConversePath extends Path
{
	
	/**
	 * the path that we need to follow its converse
	 */
	private Path path;
	
	/**
	 * @param path the path that we need to get its converse
	 */
	public ConversePath(Path path)
	{
		this.path = path;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node)
	 */
	@Override
	public NodeSet follow(Node node)
	{
		return path.followConverse(node);
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node)
	 */
	@Override
	public NodeSet followConverse(Node node)
	{
		return path.follow(node);
	}

}
