package sneps;

import java.util.LinkedList;

/**
 * A NodeSet is just a set of Nodes.It is used in other classes such as Cable and UpCable.
 * 
 * @author Amr Khaled Dawood
 */
public class NodeSet
{
	
	/**
	 * The list of Nodes that the NodeSet contains.
	 */
	private LinkedList<Node> nodes;

	/**
	 * The constructor creates a new NodeSet with empty list of Nodes.
	 */
	public NodeSet()
	{
		nodes = new LinkedList<Node>();
	}

	/**
	 * @param nodes the list of nodes that should be contained in the NodeSet.
	 */
	public NodeSet(LinkedList<Node> nodes)
	{
		this.nodes = nodes;
	}

	/**
	 * @return the list of nodes contained in the NodeSet.
	 */
	public LinkedList<Node> getNodes()
	{
		return nodes;
	}
	
	/**
	 * @param node the node that we want to add to the node set
	 */
	public void addNode(Node node)
	{
		if(! this.nodes.contains(node))
			this.nodes.add(node);
	}
	
	/**
	 * @param node the node that we want to remove
	 */
	public void removeNode(Node node)
	{
		this.nodes.remove(node);
	}
	
	/**
	 * @param nodeName the name of the node we want to remove
	 */
	public void removeNode(String nodeName)
	{
		for(int i=0;i<nodes.size();i++)
		{
			if(nodes.get(i).getIdentifier().equals(nodeName))
				nodes.remove(i);
		}
	}
	
	/**
	 * @return true if the node set has no nodes inside
	 */
	public boolean isEmpty()
	{
		return nodes.size() == 0;
	}
	
	/**
	 * @param ns the NodeSet we want to get the union with
	 * @return the NodeSet resulted from union of this node set and ns
	 */
	public NodeSet Union(NodeSet ns)
	{
		NodeSet nodeSet = new NodeSet();
		nodeSet.getNodes().addAll(this.nodes);
		for(int i=0;i<ns.getNodes().size();i++)
		{
			nodeSet.addNode(ns.getNodes().get(i));
		}
		return nodeSet;
	}
	
	/**
	 * @param ns the NodeSet we need to get its intersection with this one
	 * @return the NodeSet resulted from the intersection
	 */
	public NodeSet Intersection(NodeSet ns)
	{
		NodeSet nodeSet = new NodeSet();
		for(int i=0;i<ns.getNodes().size();i++)
		{
			if(this.nodes.contains(ns.getNodes().get(i)))
				nodeSet.addNode(ns.getNodes().get(i));
		}
		return nodeSet;
	}

}
