package sneps;

import java.util.LinkedList;

/**
 * a compose path is a path resulted from composing multiple paths.
 * 
 * @author Amr Khaled Dawood
 */
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
	 * @return the list of Paths that are composed together
	 */
	public LinkedList<Path> getPaths()
	{
		return paths;
	}

	/* (non-Javadoc)
	 * @see ds.Path#follow(ds.Node)
	 */
	@Override
	public NodeSet follow(Node node)
	{
		if(! paths.isEmpty())
		{
			LinkedList<Path> sublist = new LinkedList<Path>();
			sublist.addAll(paths);
			Path p = sublist.removeFirst();
			ComposePath cPath = new ComposePath(sublist);
			NodeSet temp = p.follow(node);
			return follow(temp,cPath);
		}
		NodeSet r = new NodeSet();
		r.addNode(node);
		return r;
	}
	
	/**
	 * @param nodeSet a node set of nodes we want to follow the path from
	 * @param path the path we want to follow starting at nodes in the node set
	 * @return the node set of the resulted nodes from following the path starting at
	 * the specified node set
	 */
	private NodeSet follow(NodeSet nodeSet,Path path)
	{
		NodeSet result = new NodeSet();
		for(int i=0;i<nodeSet.getNodes().size();i++)
		{
			Node node = nodeSet.getNodes().get(i);
			addWithNoRepeation(path.follow(node),result);
		}
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

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node)
	 */
	@Override
	public NodeSet followConverse(Node node)
	{
		if(! paths.isEmpty())
		{
			LinkedList<Path> sublist = new LinkedList<Path>();
			sublist.addAll(paths);
			Path p = sublist.removeLast();
			ComposePath cPath = new ComposePath(sublist);
			NodeSet temp = p.followConverse(node);
			return followConverse(temp,cPath);
		}
		NodeSet r = new NodeSet();
		r.addNode(node);
		return r;
	}
	
	/**
	 * @param nodeSet a node set of nodes we want to follow the converse of this path
	 * starting at
	 * @param path the path we want to follow it starting at nodes in the node set
	 * @return the node set of the resulted nodes from following the  converse of this 
	 * path starting at the specified node set
	 */
	private NodeSet followConverse(NodeSet nodeSet,Path path)
	{
		NodeSet result = new NodeSet();
		for(int i=0;i<nodeSet.getNodes().size();i++)
		{
			Node node = nodeSet.getNodes().get(i);
			addWithNoRepeation(path.followConverse(node),result);
		}
		return result;
	}

}
