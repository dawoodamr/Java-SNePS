package sneps;

import java.util.LinkedList;

/**
 * a kstar path is a path that is composed with itself zero or more times.
 * 
 * @author Amr Khaled Dawood
 */
public class KStarPath extends Path
{
	
	/**
	 * the path that is composed with itself to obtain the kstar path.
	 */
	private Path path;

	/**
	 * @param path the path that the kstar path is composed of
	 */
	public KStarPath(Path path)
	{
		this.path = path;
	}

	/**
	 * @return the path that is composed with itself to get this KStarPath
	 */
	public Path getPath()
	{
		return path;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node)
	 */
	@Override
	public NodeSet follow(Node node)
	{
		LinkedList<Node> nodes = new LinkedList<Node>();
		nodes.add(node);
		NodeSet nodeSet = new NodeSet(nodes);
		
		return follow(nodeSet);
	}
	
	/**
	 * @param ns the node set we want to follow the path starting at its nodes
	 * @return a node set with the resulted nodes from following the path
	 */
	private NodeSet follow(NodeSet ns)
	{
		LinkedList<Node> res = new LinkedList<Node>();
		res.addAll(ns.getNodes());
		NodeSet result = new NodeSet(res);
		for(int i=0;i<result.getNodes().size();i++)
		{
			// following the path for a node and adding the resulted nodes 
			addWithNoRepeation(path.follow(result.getNodes().get(i)),result);
		}
		if(wasChanged(ns,result))
			return follow(result);
		
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
	
	/**
	 * @param old the old node set
	 * @param neu the new node set 
	 * @return true if the neu node set contains elements that does not exist in node set old
	 */
	private boolean wasChanged(NodeSet old,NodeSet neu)
	{
		LinkedList<Node> o = old.getNodes();
		LinkedList<Node> n = neu.getNodes();
		for(int i=0;i<n.size();i++)
		{
			if(! o.contains(n.get(i)))
				return true;
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node)
	 */
	@Override
	public NodeSet followConverse(Node node)
	{
		LinkedList<Node> nodes = new LinkedList<Node>();
		nodes.add(node);
		NodeSet nodeSet = new NodeSet(nodes);
		
		return followConverse(nodeSet);
	}
	
	/**
	 * @param ns the node set we want to follow the converse of the path starting 
	 * at its nodes
	 * @return a node set with the resulted nodes from following the converse of the path
	 */
	private NodeSet followConverse(NodeSet ns)
	{
		LinkedList<Node> res = new LinkedList<Node>();
		res.addAll(ns.getNodes());
		NodeSet result = new NodeSet(res);
		for(int i=0;i<result.getNodes().size();i++)
		{
			// following the path for a node and adding the resulted nodes 
			addWithNoRepeation(path.followConverse(result.getNodes().get(i)),result);
		}
		if(wasChanged(ns,result))
			return followConverse(result);
		
		return result;
	}

}
