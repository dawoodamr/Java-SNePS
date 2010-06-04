package sneps;

import java.util.LinkedList;

/**
 * A NodeSet is merely a set of Nodes.
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
	 * Creates a new NodeSet with empty list of Nodes.
	 */
	public NodeSet()
	{
		nodes = new LinkedList<Node>();
	}
	
	/**
	 * gets the node at a specific position
	 * 
	 * @param index the position of the node in the list
	 * @return the node at position index
	 */
	public Node getNode(int index)
	{
		return this.nodes.get(index);
	}
	
	/**
	 * adds the specified node to the node set
	 * 
	 * @param node the node that we want to add to the node set
	 */
	public void addNode(Node node)
	{
		if(! this.nodes.contains(node))
			this.nodes.add(node);
	}
	
	/**
	 * adds all nodes in the specified node set to this node set
	 * 
	 * @param nodeSet the node set that is to be added to this node set
	 */
	public void addAll(NodeSet nodeSet)
	{
		for(int i=0;i<nodeSet.size();i++)
		{
			this.addNode(nodeSet.getNode(i));
		}
	}
	
	/**
	 * @return the size of this node set
	 */
	public int size()
	{
		return this.nodes.size();
	}
	
	/**
	 * removes the specified node from this node set
	 * 
	 * @param node the node that we want to remove
	 */
	public void removeNode(Node node)
	{
		this.nodes.remove(node);
	}
	
	/**
	 * removes all node from this node sets
	 */
	public void clear()
	{
		this.nodes.clear();
	}
	
	/**
	 * @return true if the node set is empty
	 */
	public boolean isEmpty()
	{
		return nodes.size() == 0;
	}
	
	/**
	 * checks whether the specified node is contained in this node set or not
	 * 
	 * @param node a node to check whether it exists in the node set or not
	 * @return true if the specified node is in this node set, and false otherwise
	 */
	public boolean contains(Node node)
	{
		return this.nodes.contains(node);
	}
	
	/**
	 * gets the union between this node set and the specified one
	 * 
	 * @param ns the NodeSet to get its union with this node set
	 * @return the NodeSet resulted from union of this node set and ns
	 */
	public NodeSet Union(NodeSet ns)
	{
		NodeSet nodeSet = new NodeSet();
		nodeSet.addAll(this);
		for(int i=0;i<ns.size();i++)
		{
			nodeSet.addNode(ns.getNode(i));
		}
		return nodeSet;
	}
	
	/**
	 * gets the intersection between this node set and the specified one
	 * 
	 * @param ns the NodeSet to get its intersection with this node set
	 * @return the NodeSet resulted from the intersection
	 */
	public NodeSet Intersection(NodeSet ns)
	{
		NodeSet nodeSet = new NodeSet();
		for(int i=0;i<ns.size();i++)
		{
			if(this.nodes.contains(ns.getNode(i)))
				nodeSet.addNode(ns.getNode(i));
		}
		return nodeSet;
	}

}
