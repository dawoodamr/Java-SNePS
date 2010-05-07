package sneps;

import snebr.Context;

/**
 * a relative complement path is a path that consists of two paths where a destination node 
 * can be reached from following the first path and cannot be reached from following the 
 * second path 
 * 
 * @author Amr Khaled Dawood
 */
public class RelativeComplementPath extends Path
{
	
	/**
	 * the first path that will lead us to the nodes desired
	 */
	private Path p;
	
	/**
	 * the second path that will deside about which nodes to be removed from the obtained
	 * node set from following the first path
	 */
	private Path q;

	/**
	 * @param p the first path
	 * @param q the second path
	 */
	public RelativeComplementPath(Path p,Path q)
	{
		this.p = p;
		this.q = q;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node)
	 */
	@Override
	public NodeSet follow(Node node,Context context)
	{
		NodeSet ns1 = p.follow(node,context);
		NodeSet ns2 = q.follow(node,context);
		ns1.getNodes().removeAll(ns2.getNodes());
		return ns1;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node)
	 */
	@Override
	public NodeSet followConverse(Node node,Context context)
	{
		NodeSet ns1 = p.followConverse(node,context);
		NodeSet ns2 = q.followConverse(node,context);
		ns1.getNodes().removeAll(ns2.getNodes());
		return ns1;
	}

	/**
	 * @return p which is the first Path
	 */
	public Path getP()
	{
		return p;
	}

	/**
	 * @return q which is the second Path
	 */
	public Path getQ()
	{
		return q;
	}

}
