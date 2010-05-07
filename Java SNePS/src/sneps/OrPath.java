package sneps;

import java.util.LinkedList;

import snebr.Context;

/**
 * an OrPath is a path that contains list of paths. an OrPath will lead us to a destination 
 * node if one of the paths it contains will lead us to this node
 * 
 * @author Amr Khaled Dawood
 */
public class OrPath extends Path
{
	
	/**
	 * the list of paths for the OrPath
	 */
	private LinkedList<Path> paths;

	/**
	 * @param paths the list of paths for the OrPath
	 */
	public OrPath(LinkedList<Path> paths)
	{
		this.paths = paths;
	}

	/**
	 * @return the list of Path that are used to create this OrPath
	 */
	public LinkedList<Path> getPaths()
	{
		return paths;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node)
	 */
	@Override
	public NodeSet follow(Node node,Context context)
	{
		LinkedList<Path> rest = new LinkedList<Path>();
		rest.addAll(paths);
		Path p = rest.removeFirst();
		OrPath orPath = new OrPath(rest);
		return Union(p.follow(node,context),orPath.follow(node,context));
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node)
	 */
	@Override
	public NodeSet followConverse(Node node,Context context)
	{
		LinkedList<Path> rest = new LinkedList<Path>();
		rest.addAll(paths);
		Path p = rest.removeLast();
		OrPath orPath = new OrPath(rest);
		return Union(p.followConverse(node,context),orPath.followConverse(node,context));
	}
	
	/**
	 * this method gets the union of two node sets
	 * 
	 * @param ns1 the first node set
	 * @param ns2 the second node set
	 * @return the union of the two node sets
	 */
	private NodeSet Union(NodeSet ns1,NodeSet ns2)
	{
		NodeSet result = new NodeSet();
		result.getNodes().addAll(ns1.getNodes());
		addWithNoRepeation(ns2,result);
		return result;
	}
	
	/**
	 * @param source the node set containing nodes we want to add to the other node set
	 * @param destination the node set containing nodes we want to add the nodes to
	 */
	private void addWithNoRepeation(NodeSet source,NodeSet destination)
	{
		for(int i=0;i<source.getNodes().size();i++)
		{
			Node n = source.getNodes().get(i);
			if(! destination.getNodes().contains(n))
				destination.getNodes().add(n);
		}
	}

}
