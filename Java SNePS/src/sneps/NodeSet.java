package sneps;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * A NodeSet is merely a set of Nodes.
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class NodeSet implements Serializable
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
	 * gets the node at the specific position
	 * 
	 * @param index an int representing the position of the node in this node set
	 * @return the node at the specified position
	 */
	public Node getNode(int index)
	{
		return this.nodes.get(index);
	}
	
	/**
	 * adds the specified node to this node set
	 * 
	 * @param node a Node that is to be added to this node set
	 */
	public void addNode(Node node)
	{
		if(! this.nodes.contains(node))
			this.nodes.add(node);
	}
	
	/**
	 * adds all nodes in the specified node set to this node set
	 * 
	 * @param nodeSet a NodeSet whose elements are to be added to this node set
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
	 * @param node a Node that is to be removeds
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
	 * @return true if the node set is empty, and false otherwise
	 */
	public boolean isEmpty()
	{
		return nodes.size() == 0;
	}
	
	/**
	 * checks whether the specified node is contained in this node set or not
	 * 
	 * @param node a Node to check whether it exists in this node set or not
	 * @return true if the specified node is in this node set, and false otherwise
	 */
	public boolean contains(Node node)
	{
		return this.nodes.contains(node);
	}
	
	/**
	 * @param nodeSet a node set to check whether it is equal to this one or not
	 * @return true if the given node set is equal to this one, and false otherwise
	 */
	public boolean isEqualTo(NodeSet nodeSet)
	{
		if(this.nodes.size() != nodeSet.size())
			return false;
		for(int i=0;i<this.nodes.size();i++)
		{
			if(! nodeSet.contains(this.nodes.get(i)))
				return false;
		}
		return true;
	}
	
	/**
	 * gets the union between this node set and the specified one
	 * 
	 * @param ns a NodeSet to get the union of it with this node set
	 * @return the NodeSet resulted from union of this node set and the given one
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
	 * @param ns a NodeSet to get the intersection between it and this node set
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
	
	/**
	 * gets the difference between this node set and the given one
	 * 
	 * @param ns a node set to get the difference between this one and it
	 * @return a node set containing nodes that exist in this node set and not in the given one
	 */
	public NodeSet difference(NodeSet ns)
	{
		NodeSet result = new NodeSet();
		for(int i=0;i<this.nodes.size();i++)
		{
			if(! ns.contains(this.nodes.get(i)))
				result.addNode(this.nodes.get(i));
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		String s = "{";
		for(int i=0;i<this.nodes.size();i++)
		{
			s += this.nodes.get(i).toString();
			if(i < this.nodes.size()-1)
				s += " ";
		}
		s += "}";
		return s;
	}

}
