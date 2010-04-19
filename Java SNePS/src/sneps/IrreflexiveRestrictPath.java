package sneps;

/**
 * an irreflexive restrict path is a path that does not lead to the same start node
 * 
 * @author Amr Khaled Dawood
 */
public class IrreflexiveRestrictPath extends Path
{
	
	/**
	 * the path that leads to nodes other than the starting node
	 */
	private Path path;

	/**
	 * @param path the specified path that is irreflexive restrict
	 */
	public IrreflexiveRestrictPath(Path path)
	{
		this.path = path;
	}

	/**
	 * @return the path that leads to nodes other than the one that it starts at
	 */
	public Path getPath()
	{
		return path;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node)
	 */
	@Override
	public NodeSet follow(Node node)
	{
		NodeSet ns = path.follow(node);
		ns.getNodes().remove(node);
		return ns;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node)
	 */
	@Override
	public NodeSet followConverse(Node node)
	{
		NodeSet ns = path.followConverse(node);
		ns.getNodes().remove(node);
		return ns;
	}

}
