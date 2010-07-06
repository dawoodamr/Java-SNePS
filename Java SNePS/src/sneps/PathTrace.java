package sneps;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * This class represents the trace of a path as a path that could be a composed path, an and path, 
 * a unit path, or an empty path and a set of supports
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class PathTrace implements Serializable
{
	/**
	 * the trace path
	 */
	private Path path;
	
	/**
	 * a node set of supports
	 */
	private NodeSet supports;
	
	/**
	 * initializes the path to an empty path and the supports to an empty node set
	 */
	public PathTrace()
	{
		this.path = new EmptyPath();
		this.supports = new NodeSet();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public PathTrace clone()
	{
		PathTrace p = new PathTrace();
		p.path = this.path.clone();
		for(int i=0;i<supports.size();i++)
		{
			p.addSupport(this.supports.getNode(i));
		}
		return p;
	}
	
	/**
	 * @return the trace path
	 */
	public Path getPath()
	{
		return this.path;
	}
	
	/**
	 * @return the set of supports
	 */
	public NodeSet getSupports()
	{
		return this.supports;
	}
	
	/**
	 * adds a node to the set of supports
	 * 
	 * @param node a Node to be added to the list of supports
	 */
	public void addSupport(Node node)
	{
		this.supports.addNode(node);
	}
	
	/**
	 * adds all the supports of the given set to the node set of supports of this path trace
	 * 
	 * @param supports a node set of supports
	 */
	public void addAllSupports(NodeSet supports)
	{
		this.supports.addAll(supports);
	}
	
	/**
	 * appends the given path to the path
	 * 
	 * @param path a path to be appended to the path
	 */
	public void compose(Path path)
	{
		if(path.getClass().getSimpleName().equals("EmptyPath"))
			return;
		if(this.path.getClass().getSimpleName().equals("EmptyPath"))
		{
			this.path = path;
			return;
		}
		LinkedList<Path> list = new LinkedList<Path>();
		
		if(this.path.getClass().getSimpleName().equals("ComposePath"))
			list.addAll(((ComposePath)this.path).getPaths());
		else
			list.add(this.path);
		
		if(path.getClass().getSimpleName().equals("ComposePath"))
			list.addAll(((ComposePath)path).getPaths());
		else
			list.add(path);
		this.path = new ComposePath(list);
	}
	
	/**
	 * sets the path to be an and path of the path and the given path
	 * 
	 * @param path a path to be combined with the path in an and path
	 */
	public void and(Path path)
	{
		if(path.getClass().getSimpleName().equals("EmptyPath"))
			return;
		if(this.path.getClass().getSimpleName().equals("EmptyPath"))
		{
			this.path = path;
			return;
		}
		LinkedList<Path> list = new LinkedList<Path>();
		
		if(this.path.getClass().getSimpleName().equals("AndPath"))
			list.addAll(((AndPath) this.path).getPaths());
		else
			list.add(this.path);
		
		if(path.getClass().getSimpleName().equals("AndPath"))
			list.addAll(((AndPath)path).getPaths());
		else
			list.add(path);
		
		this.path = new AndPath(list);
	}
	
	/**
	 * @param pt a path trace to check whether it is equal to this one or not
	 * @return true if the given path trace is equal to this one, and false otherwise
	 */
	public boolean isEqualTo(PathTrace pt)
	{
		if(! this.supports.isEqualTo(pt.getSupports()))
			return false;
		if(! this.path.isEqualTo(pt.getPath()))
			return false;
		return true;
	}
	
	/**
	 * @return a LinkedList of relations which are the first relations on the path trace
	 */
	public LinkedList<Relation> getFirst()
	{
		LinkedList<Path> l = getFirst(this.path);
		LinkedList<Relation> list = new LinkedList<Relation>();
		for(int i=0;i<l.size();i++)
		{
			if(l.get(i).getClass().getSimpleName().equals("FUnitPath"))
				list.add(((FUnitPath)l.get(i)).getRelation());
		}
		for(int i=0;i<list.size()-1;i++)
		{
			Relation r = list.get(i);
			for(int j=i+1;j<list.size();j++)
			{
				if(r.equals(list.get(j)))
				{
					list.remove(j);
					j--;
				}
			}
		}
		return list;
	}
	
	/**
	 * @param path a path to get its first relation
	 * @return a LinkedList of paths
	 */
	private LinkedList<Path> getFirst(Path path)
	{
		if(path.getClass().getSimpleName().equals("FUnitPath"))
		{
			LinkedList<Path> l = new LinkedList<Path>();
			l.add(path);
			return l;
		}
		if(path.getClass().getSimpleName().equals("BUnitPath"))
		{
			LinkedList<Path> l = new LinkedList<Path>();
			l.add(path);
			return l;
		}
		if(path.getClass().getSimpleName().equals("EmptyPath"))
		{
			return new LinkedList<Path>();
		}
		if(path.getClass().getSimpleName().equals("ComposePath"))
		{
			for(int i=0;i<((ComposePath) path).getPaths().size();i++)
			{
				LinkedList<Path> l = getFirst(((ComposePath) path).getPaths().get(i));
				if(! l.isEmpty())
					return l;
			}
		}
		if(path.getClass().getSimpleName().equals("AndPath"))
		{
			LinkedList<Path> list = new LinkedList<Path>();
			for(int i=0;i<((AndPath) path).getPaths().size();i++)
			{
				list.addAll(getFirst(((AndPath) path).getPaths().get(i)));
			}
			return list;
		}
		
		return null;
	}
}
