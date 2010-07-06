package sneps;

import java.util.LinkedList;

import snebr.Context;

/**
 * A range restrict path is a path that is restricted by considering only nodes that are
 * resulted from following it, if they can be used as start nodes to follow a path that will
 * lead to a specific node
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class RangeRestrictPath extends Path
{
	
	/**
	 * the path that leads to nodes reachable by this range restrict path
	 */
	private Path p;
	
	/**
	 * the restriction path
	 */
	private Path q;
	
	/**
	 * the restriction node 
	 */
	private Node node;

	/**
	 * @param p the path that will lead to nodes reachable by the range restrict path
	 * @param q the restriction path
	 * @param node the restriction destination node
	 */
	public RangeRestrictPath(Path p,Path q,Node node)
	{
		this.p = p;
		this.q = q;
		this.node = node;
	}

	/**
	 * @return the Path p of this range restrict path
	 */
	public Path getP()
	{
		return p;
	}

	/**
	 * @return q a Path which is the restriction Path of this path
	 */
	public Path getQ()
	{
		return q;
	}

	/**
	 * @return a Node that restricts the destination of path q 
	 */
	public Node getNode()
	{
		return node;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node, sneps.PathTrace, snebr.Context)
	 */
	@Override
	public LinkedList<Object[]> follow(Node node,PathTrace trace,Context context)
	{
		LinkedList<Object[]> result = new LinkedList<Object[]>();
		LinkedList<Object[]> res = this.p.follow(node,trace,context);
		
		for(int i=0;i<res.size();i++)
		{
			Object[] o = res.get(i);
			Node n = (Node) o[0];
			PathTrace pt = (PathTrace) o[1];
			LinkedList<Object[]> temp = this.q.follow(n,pt,context);
			for(int j=0;j<temp.size();j++)
			{
				Object[] ob = temp.get(j);
				Node nt = (Node) ob[0];
				PathTrace ptt = (PathTrace) ob[1];
				if(nt.equals(this.node))
				{
					Object[] r = new Object[2];
					r[0] = n;
					PathTrace ptrace = pt.clone();
					ptrace.addAllSupports(ptt.getSupports());
					r[1] = ptrace;
					result.add(r);
				}

				
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
		return new DomainRestrictPath(this.q,this.node,new ConversePath(this.p)).follow(node,trace,context);
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#clone()
	 */
	@Override
	public RangeRestrictPath clone()
	{
		return new RangeRestrictPath(this.p.clone(),this.q.clone(),this.node);
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#isEqual(sneps.Path)
	 */
	@Override
	public boolean isEqualTo(Path path)
	{
		if(! path.getClass().getSimpleName().equals("RangeRestrictPath"))
			return false;
		RangeRestrictPath d = (RangeRestrictPath) path;
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
		return "range-restrict("+this.p.toString()+" "+this.q.toString()+" "+this.node.toString()+")";
	}

}
