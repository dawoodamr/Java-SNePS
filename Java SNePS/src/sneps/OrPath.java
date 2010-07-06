package sneps;

import java.util.LinkedList;

import snebr.Context;

/**
 * An OrPath is a path that contains set of paths. An OrPath will lead to a destination 
 * node if one of the paths it contains can lead to this node
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class OrPath extends Path
{
	
	/**
	 * a LinkedList of paths for the OrPath
	 */
	private LinkedList<Path> paths;

	/**
	 * @param paths a LinkedList of paths for the OrPath
	 */
	public OrPath(LinkedList<Path> paths)
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
	 * @return the LinkedList of Paths that were used to create this OrPath
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
		if(this.paths.isEmpty())
			return new LinkedList<Object[]>();
		LinkedList<Path> rest = new LinkedList<Path>();
		rest.addAll(this.paths);
		Path p = rest.removeFirst();
		OrPath orPath = new OrPath(rest);
		return UnionOr(p.follow(node,trace,context),orPath.follow(node,trace,context));
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node, sneps.PathTrace, snebr.Context)
	 */
	@Override
	public LinkedList<Object[]> followConverse(Node node,PathTrace trace,Context context)
	{
		if(this.paths.isEmpty())
			return new LinkedList<Object[]>();
		LinkedList<Path> rest = new LinkedList<Path>();
		rest.addAll(this.paths);
		Path p = rest.removeFirst();
		OrPath orPath = new OrPath(rest);
		return UnionOr(p.followConverse(node,trace,context),orPath.followConverse(node,trace,context));
	}
	
	/**
	 * gets the union of two lists of Node-PathTrace pairs
	 * 
	 * @param list1 a LinkedList of Node-PathTrace pairs
	 * @param list2 a LinkedList of Node-PathTrace pairs
	 * @return a LinkedList of Node-PathTrace pairs resulted from getting the union of the two 
	 * given lists
	 */
	private LinkedList<Object[]> UnionOr(LinkedList<Object[]> list1,LinkedList<Object[]> list2)
	{
		LinkedList<Object[]> result = new LinkedList<Object[]>();
		result.addAll(list1);
		result.addAll(list2);
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#clone()
	 */
	@Override
	public OrPath clone()
	{
		LinkedList<Path> list = new LinkedList<Path>();
		for(int i=0;i<this.paths.size();i++)
		{
			list.add(this.paths.get(i).clone());
		}
		return new OrPath(list);
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#isEqual(sneps.Path)
	 */
	@Override
	public boolean isEqualTo(Path path)
	{
		if(! path.getClass().getSimpleName().equals("OrPath"))
			return false;
		OrPath and = (OrPath) path;
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
		String s = "or(";
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
