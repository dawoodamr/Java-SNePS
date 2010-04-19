package sneps;

/**
 * a domain restrict path is a path that is restricted by another path that should lead to 
 * a specified node in order to be valid
 * 
 * @author Amr Khaled Dawood
 */
public class DomainRestrictPath extends Path
{
	
	/**
	 * the restriction path
	 */
	private Path q;
	
	/**
	 * the node that should be reached from the q path in order to make the p path valid
	 */
	private Node node;
	
	/**
	 * the path that we will follow to reach nodes if it is valid 
	 */
	private Path p;

	/**
	 * @param q the restriction path
	 * @param node the restriction node
	 * @param p the path that should be followed
	 */
	public DomainRestrictPath(Path q,Node node,Path p)
	{
		this.q = q;
		this.node = node;
		this.p = p;
	}

	/**
	 * @return the restriction Path
	 */
	public Path getQ()
	{
		return q;
	}

	/**
	 * @return the node that restricts the destination for the Path q
	 */
	public Node getNode()
	{
		return node;
	}

	/**
	 * @return the path that should be followed
	 */
	public Path getP()
	{
		return p;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node)
	 */
	@Override
	public NodeSet follow(Node node)
	{
		NodeSet result = new NodeSet();
		NodeSet ns = q.follow(node);
		if(ns.getNodes().contains(this.node))
			result = p.follow(node);
		return result;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node)
	 */
	@Override
	public NodeSet followConverse(Node node)
	{
		NodeSet result = new NodeSet();
		NodeSet ns = q.follow(node);
		if(ns.getNodes().contains(this.node))
			result = p.followConverse(node);
		return result;
	}

}
