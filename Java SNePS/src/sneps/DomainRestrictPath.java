package sneps;

import java.util.LinkedList;

import snebr.Context;

/**
 * A domain restrict path is a path that is restricted by another path that should lead to 
 * a specified node
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class DomainRestrictPath extends Path
{
	
	/**
	 * the restriction path
	 */
	private Path q;
	
	/**
	 * the node that should be reached by following path q in order to make the p path valid
	 */
	private Node node;
	
	/**
	 * the path that will be followed to reach nodes reachable by this path if it is valid 
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
	 * @return a Path that is the restriction of this path
	 */
	public Path getQ()
	{
		return q;
	}

	/**
	 * @return a Node that restricts the destination for the Path q of this path
	 */
	public Node getNode()
	{
		return node;
	}

	/**
	 * @return a Path that should be followed for this path
	 */
	public Path getP()
	{
		return p;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node, sneps.PathTrace, snebr.Context)
	 */
	@Override
	public LinkedList<Object[]> follow(Node node,PathTrace trace,Context context)
	{
		LinkedList<Object[]> result = new LinkedList<Object[]>();
		
		LinkedList<Object[]> temp = q.follow(node,trace,context);
		for(int i=0;i<temp.size();i++)
		{
			Object[] o = temp.get(i);
			Node n = (Node) o[0];
			PathTrace pt = (PathTrace) o[1];
			if(n.equals(this.node))
			{
				trace.addAllSupports(pt.getSupports());
				result = p.follow(node,trace,context);
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
		return new RangeRestrictPath(new ConversePath(this.p),this.q,this.node).follow(node,trace,context);
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#clone()
	 */
	@Override
	public RangeRestrictPath clone()
	{
		return new RangeRestrictPath(p.clone(),q.clone(),node);
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#isEqual(sneps.Path)
	 */
	@Override
	public boolean isEqualTo(Path path)
	{
		if(! path.getClass().getSimpleName().equals("DomainRestrictPath"))
			return false;
		DomainRestrictPath d = (DomainRestrictPath) path;
		if(! this.node.equals(d.getNode()))
			return false;
		if(! this.p.isEqualTo(d.getP()))
			return false;
		if(! this.q.isEqualTo(d.getQ()))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "domain-restrict("+this.q.toString()+" "+this.node.toString()+" "+this.p.toString()+")";
	}

}
