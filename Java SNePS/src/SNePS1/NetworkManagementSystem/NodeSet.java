package SNePS1.NetworkManagementSystem;

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
		this.nodes.add(node);
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

}
