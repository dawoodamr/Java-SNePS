package sneps;

import java.util.LinkedList;

import snebr.Context;

/**
 * A compose path is a path resulted from the composition of multiple paths.
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class ComposePath extends Path
{
	
	/**
	 * the list of paths that represent the composed path
	 */
	private LinkedList<Path> paths;

	/**
	 * @param paths a list of paths that represent the composed path
	 */
	public ComposePath(LinkedList<Path> paths)
	{
		this.paths = paths;
	}

	/**
	 * @return a LinkedList of Paths of this ComposePath
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
		if(! this.paths.isEmpty())
		{
			LinkedList<Path> sublist = new LinkedList<Path>();
			sublist.addAll(this.paths);
			Path p = sublist.removeFirst();
			ComposePath cPath = new ComposePath(sublist);
			LinkedList<Object[]> temp = p.follow(node,trace,context);
			return follow(temp,context,cPath);
		}
		return new LinkedList<Object[]>();
	}
	
	/**
	 * follows the given path starting at all nodes in the given list of pairs 
	 * 
	 * @param temp a LinkedList of Node-PathTrace pairs
	 * @param context the context that the propositions along this path are asserted in
	 * @param path the path that will be followed starting at nodes in the list of pairs
	 * @return a LinkedList of Node-PathTrace pairs resulted from following the given 
	 * path starting at all nodes in the given list of pairs
	 */
	private LinkedList<Object[]> follow(LinkedList<Object[]> temp,Context context,ComposePath path)
	{
		if(path.getPaths().isEmpty())
			return temp;
		
		LinkedList<Object[]> result = new LinkedList<Object[]>();
		
		for(int i=0;i<temp.size();i++)
		{
			Object[] o = temp.get(i);
			Node node = (Node) o[0];
			PathTrace pt = (PathTrace) o[1];
			LinkedList<Object[]> f = path.follow(node,pt,context);
			result.addAll(f);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node, sneps.PathTrace, snebr.Context)
	 */
	@Override
	public LinkedList<Object[]> followConverse(Node node,PathTrace trace,Context context)
	{
		if(! this.paths.isEmpty())
		{
			LinkedList<Path> sublist = new LinkedList<Path>();
			sublist.addAll(this.paths);
			Path p = sublist.removeLast();
			ComposePath cPath = new ComposePath(sublist);
			LinkedList<Object[]> temp = p.followConverse(node,trace,context);
			return followConverse(temp,context,cPath);
		}
		return new LinkedList<Object[]>();
	}
	
	/**
	 * follows the converse of the given path starting at all nodes in the given list of pairs 
	 * 
	 * @param temp a LinkedList of Node-PathTrace pairs
	 * @param context the context that the propositions along this path are asserted in
	 * @param path the path that its converse will be followed starting at nodes in the 
	 * list of pairs
	 * @return a LinkedList of Node-PathTrace pairs resulted from following the converse of 
	 * the given path starting at all nodes in the given list of pairs
	 */
	private LinkedList<Object[]> followConverse(LinkedList<Object[]> temp,Context context,ComposePath path)
	{
		if(path.getPaths().isEmpty())
			return temp;
		
		LinkedList<Object[]> result = new LinkedList<Object[]>();
		
		for(int i=0;i<temp.size();i++)
		{
			Object[] o = temp.get(i);
			Node node = (Node) o[0];
			PathTrace pt = (PathTrace) o[1];
			LinkedList<Object[]> f = path.followConverse(node,pt,context);
			result.addAll(f);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#clone()
	 */
	@Override
	public ComposePath clone()
	{
		LinkedList<Path> paths = new LinkedList<Path>();
		for(int i=0;i<this.paths.size();i++)
		{
			paths.add(this.paths.get(i).clone());
		}
		return new ComposePath(paths);
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#isEqual(sneps.Path)
	 */
	@Override
	public boolean isEqualTo(Path path)
	{
		if(! path.getClass().getSimpleName().equals("ComposePath"))
			return false;
		ComposePath c = (ComposePath) path;
		if(c.getPaths().size() != this.paths.size())
			return false;
		for(int i=0;i<this.paths.size();i++)
		{
			if(! this.paths.get(i).isEqualTo(c.getPaths().get(i)))
				return false;
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		String s = "compose(";
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
