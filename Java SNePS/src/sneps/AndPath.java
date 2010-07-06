package sneps;

import java.util.LinkedList;

import snebr.Context;

/**
 * An AndPath is a path that contains a set of paths. An AndPath may lead to a node 
 * if and only if all the paths it contains can lead to that node
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class AndPath extends Path
{
	
	/**
	 * the list of paths for this AndPath
	 */
	private LinkedList<Path> paths;

	/**
	 * @param paths a LinkedList of paths for the AndPath
	 */
	public AndPath(LinkedList<Path> paths)
	{
		LinkedList<Path> list = new LinkedList<Path>();
		list.addAll(paths);
		for(int i=0;i<list.size()-1;i++)
		{
			Path p = list.get(i);
			for(int j=i+1;j<list.size();j++)
			{
				if(list.get(j).isEqualTo(p))
				{
					list.remove(j);
					j--;
				}
			}
		}
		this.paths = list;
	}

	/**
	 * @return a LinkedList of Paths that are used to create this AndPath
	 */
	public LinkedList<Path> getPaths()
	{
		return paths;
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node, sneps.PathTrace, snebr.Context)
	 */
	@Override
	public LinkedList<Object[]> follow(Node node,PathTrace trace,Context context)
	{
		if(paths.isEmpty())
			return new LinkedList<Object[]>();
		LinkedList<Path> rest = new LinkedList<Path>();
		rest.addAll(this.paths);
		Path p = rest.removeFirst();
		AndPath andPath = new AndPath(rest);
		if(rest.size()>0)
			return intersectionAnd(p.follow(node,trace,context),andPath.follow(node,trace,context));
		else
			return p.follow(node,trace,context);
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node, sneps.PathTrace, snebr.Context)
	 */
	@Override
	public LinkedList<Object[]> followConverse(Node node,PathTrace trace,Context context)
	{
		if(paths.isEmpty())
			return new LinkedList<Object[]>();
		LinkedList<Path> rest = new LinkedList<Path>();
		rest.addAll(this.paths);
		Path p = rest.removeFirst();
		AndPath andPath = new AndPath(rest);
		if(rest.size()>0)
			return intersectionAnd(p.followConverse(node,trace,context),andPath.followConverse(node,trace,context));
		else
			return p.followConverse(node,trace,context);
	}
	
	/**
	 * returns the intersection between the two Lists of pairs
	 * 
	 * @param list1 a LinkedList of Node-PathTrace pairs
	 * @param list2 a LinkedList of Node-PathTrace pairs
	 * @return a LinkedList of Node-PathTrace pairs resulted from getting the intersection
	 * between the two given lists
	 */
	private LinkedList<Object[]> intersectionAnd(LinkedList<Object[]> list1,LinkedList<Object[]> list2)
	{
		LinkedList<Object[]> result = new LinkedList<Object[]>();
		
		for(int i=0;i<list1.size();i++)
		{
			Object[] ob1 = list1.get(i);
			Node n1 = (Node) ob1[0];
			PathTrace pt1 = (PathTrace) ob1[1];
			for(int j=0;j<list2.size();j++)
			{
				Object[] ob2 = list2.get(j);
				Node n2 = (Node) ob2[0];
				PathTrace pt2 = (PathTrace) ob2[1];
				if(n1.equals(n2))
				{
					PathTrace pt = pt1.clone();
					pt.and(pt2.getPath());
					pt.addAllSupports(pt2.getSupports());
					Object[] o = new Object[2];
					o[0] = n1;
					o[1] = pt;
					result.add(o);
				}
			}
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#clone()
	 */
	@Override
	public Path clone()
	{
		LinkedList<Path> list = new LinkedList<Path>();
		for(int i=0;i<this.paths.size();i++)
		{
			list.add(this.paths.get(i).clone());
		}
		
		return new AndPath(list);
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#isEqual(sneps.Path)
	 */
	@Override
	public boolean isEqualTo(Path path)
	{
		if(! path.getClass().getSimpleName().equals("AndPath"))
			return false;
		AndPath and = (AndPath) path;
		if(this.paths.size() != and.getPaths().size())
			return false;
		for(int i=0;i<this.paths.size();i++)
		{
			Path p = this.paths.get(i);
			boolean flag = false;
			for(int j=0;j<and.getPaths().size();j++)
			{
				Path q = and.getPaths().get(j);
				if(p.isEqualTo(q))
					flag = true;
			}
			if(! flag)
				return false;
			else
				flag = false;
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		String s = "";
		s += "and(";
		for(int i=0;i<this.paths.size();i++)
		{
			s += this.paths.get(i).toString();
			if(i<this.paths.size()-1)
				s += " ";
		}
		s += ")";
		return s;
	}

}
