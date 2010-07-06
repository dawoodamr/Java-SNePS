package sneps;

import java.util.LinkedList;

import snebr.Context;

/**
 * A kplus path is a path that is composed with itself one or more times.
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class KPlusPath extends Path
{
	
	/**
	 * the path that is composed with itself to obtain the kplus path
	 */
	private Path path;

	/**
	 * @param path the Path that the kplus path is composed of
	 */
	public KPlusPath(Path path)
	{
		this.path = path;
	}

	/**
	 * @return the path that is composed with itself to get this KPlusPath
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
		LinkedList<Object[]> temp = this.path.follow(node,trace,context);
		
		return follow(temp,context);
	}
	
	/**
	 * follows this path starting at nodes in the list of pairs: temp in the given context
	 * 
	 * @param temp a LinkedList of Node-PathTrace pairs
	 * @param context the context that propositions in this path are asserted in
	 * @return a LinkedList of Node-PathTrace pairs resulted from following the path starting
	 * at all nodes in temp until no more nodes are reached
	 */
	private LinkedList<Object[]> follow(LinkedList<Object[]> temp,Context context)
	{
		LinkedList<Object[]> result = new LinkedList<Object[]>();
		for(int i=0;i<temp.size();i++)
		{
			Object[] o = temp.get(i);
			Node node = (Node) o[0];
			PathTrace trace = (PathTrace) o[1];
			LinkedList<Object[]> f = this.path.follow(node,trace,context);
			result.addAll(f);
		}
		while(! result.isEmpty())
		{
			for(int i=0;i<temp.size();i++)
			{
				Object[] ob1 = temp.get(i);
				Node n1 = (Node) ob1[0];
				PathTrace pt1 = (PathTrace) ob1[1];
				for(int j=0;j<result.size();j++)
				{
					Object[] ob2 = result.get(j);
					Node n2 = (Node) ob2[0];
					PathTrace pt2 = (PathTrace) ob2[1];
					if(n1.equals(n2))
					{
						if(! pt1.getSupports().isEqualTo(pt2.getSupports()))
							temp.add(ob2);
						result.remove(j);
						j--;
					}
				}
			}
			temp.addAll(result);
			follow(result,context);
		}
		
		
		return temp;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node, sneps.PathTrace, snebr.Context)
	 */
	@Override
	public LinkedList<Object[]> followConverse(Node node,PathTrace trace,Context context)
	{
		LinkedList<Object[]> temp = this.path.followConverse(node,trace,context);
		
		return followConverse(temp,context);
	}
	
	/**
	 * follows the converse of this path starting at nodes in the list of pairs: temp in 
	 * the given context
	 * 
	 * @param temp a LinkedList of Node-PathTrace pairs
	 * @param context the context that propositions in this path are asserted in
	 * @return a LinkedList of Node-PathTrace pairs resulted from following the converse of 
	 * the path starting at all nodes in temp until no more nodes are reached
	 */
	private LinkedList<Object[]> followConverse(LinkedList<Object[]> temp,Context context)
	{
		LinkedList<Object[]> result = new LinkedList<Object[]>();
		for(int i=0;i<temp.size();i++)
		{
			Object[] o = temp.get(i);
			Node node = (Node) o[0];
			PathTrace trace = (PathTrace) o[1];
			LinkedList<Object[]> f = this.path.followConverse(node,trace,context);
			result.addAll(f);
		}
		while(! result.isEmpty())
		{
			for(int i=0;i<temp.size();i++)
			{
				Object[] ob1 = temp.get(i);
				Node n1 = (Node) ob1[0];
				PathTrace pt1 = (PathTrace) ob1[1];
				for(int j=0;j<result.size();j++)
				{
					Object[] ob2 = result.get(j);
					Node n2 = (Node) ob2[0];
					PathTrace pt2 = (PathTrace) ob2[1];
					if(n1.equals(n2))
					{
						if(! pt1.getSupports().isEqualTo(pt2.getSupports()))
							temp.add(ob2);
						result.remove(j);
						j--;
					}
				}
			}
			temp.addAll(result);
			followConverse(result,context);
		}
		
		
		return temp;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#clone()
	 */
	@Override
	public KPlusPath clone()
	{
		return new KPlusPath(this.path);
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#isEqual(sneps.Path)
	 */
	@Override
	public boolean isEqualTo(Path path)
	{
		if(! path.getClass().getSimpleName().equals("KPlusPath"))
			return false;
		KPlusPath con = (KPlusPath) path;
		if(! con.getPath().isEqualTo(this.path))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "kplus("+this.path.toString()+")";
	}
	
}
