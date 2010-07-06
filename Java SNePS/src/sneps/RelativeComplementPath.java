package sneps;

import java.util.LinkedList;

import snebr.Context;

/**
 * A relative complement path is a path that consists of two paths where a destination node 
 * can be reached by following the first path, and cannot be reached by following the 
 * second path 
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class RelativeComplementPath extends Path
{
	
	/**
	 * the first path that will lead to the nodes reachable by the relative complement path
	 */
	private Path p;
	
	/**
	 * the second path that will decide which nodes to be removed from the obtained
	 * node set after following the first path
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

	/**
	 * @return p which is the first Path of this relative complement path
	 */
	public Path getP()
	{
		return p;
	}

	/**
	 * @return q which is the second Path of this relative complement path
	 */
	public Path getQ()
	{
		return q;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node, sneps.PathTrace, snebr.Context)
	 */
	@Override
	public LinkedList<Object[]> follow(Node node,PathTrace trace,Context context)
	{
		LinkedList<Object[]> result = this.p.follow(node,trace,context);
		LinkedList<Object[]> temp = this.q.follow(node,trace,context);
		for(int i=0;i<temp.size();i++)
		{
			Object[] ob1 = temp.get(i);
			Node n1 = (Node) ob1[0];
			for(int j=0;j<result.size();j++)
			{
				Object[] ob2 = result.get(j);
				Node n2 = (Node) ob2[0];
				if(n1.equals(n2))
				{
					result.remove(j);
					j--;
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
		LinkedList<Object[]> result = this.p.followConverse(node,trace,context);
		LinkedList<Object[]> temp = this.q.followConverse(node,trace,context);
		for(int i=0;i<temp.size();i++)
		{
			Object[] ob1 = temp.get(i);
			Node n1 = (Node) ob1[0];
			for(int j=0;j<result.size();j++)
			{
				Object[] ob2 = result.get(j);
				Node n2 = (Node) ob2[0];
				if(n1.equals(n2))
				{
					result.remove(j);
					j--;
				}
			}
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#clone()
	 */
	@Override
	public RelativeComplementPath clone()
	{
		return new RelativeComplementPath(this.p,this.q);
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#isEqual(sneps.Path)
	 */
	@Override
	public boolean isEqualTo(Path path)
	{
		if(! path.getClass().getSimpleName().equals("RelativeComplementPath"))
			return false;
		RelativeComplementPath d = (RelativeComplementPath) path;
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
		return "relative-complement("+this.p.toString()+" "+this.q.toString()+")";
	}

}
