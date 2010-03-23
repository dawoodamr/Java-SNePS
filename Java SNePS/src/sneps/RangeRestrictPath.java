package sneps;

/**
 * a range restrict path is a path that is restricted by considering only nodes that are
 * resulted from following it if they can be used as start nodes to follow a path that will
 * lead to a specific node
 * 
 * @author Amr Khaled Dawood
 */
public class RangeRestrictPath extends Path
{
	
	/**
	 * the path
	 */
	private Path p;
	
	/**
	 * the restriction path
	 */
	private Path q;
	
	/**
	 * the restriction destination node 
	 */
	private Node node;

	/**
	 * @param p the path that we want to follow
	 * @param q the restriction path
	 * @param node the restriction destination node
	 */
	public RangeRestrictPath(Path p,Path q,Node node)
	{
		this.p = p;
		this.q = q;
		this.node = node;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node)
	 */
	@Override
	public NodeSet follow(Node node)
	{
		NodeSet result = new NodeSet();
		NodeSet ns = p.follow(node);
		for(int i=0;i<ns.getNodes().size();i++)
		{
			NodeSet temp = q.follow(ns.getNodes().get(i));
			if(temp.getNodes().contains(this.node))
				result.addNode(ns.getNodes().get(i));
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node)
	 */
	@Override
	public NodeSet followConverse(Node node)
	{
		NodeSet result = new NodeSet();
		NodeSet ns = p.followConverse(node);
		for(int i=0;i<ns.getNodes().size();i++)
		{
			NodeSet temp = q.followConverse(ns.getNodes().get(i));
			if(temp.getNodes().contains(this.node))
				result.addNode(ns.getNodes().get(i));
		}
		
		return result;
	}

}
