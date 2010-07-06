package sneps;

import java.io.Serializable;

/**
 * An UpCable is a cable that contains the information needed about a node's parents 
 * and the arcs coming into that node from them. It contains a Relation and a NodeSet of parent nodes.
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class UpCable implements Serializable
{ 
	
	/**
	 * the Relation that labels the arcs represented by this UpCable.
	 */
	private Relation relation;
	
	/**
	 * the NodeSet that contains parent nodes that have the specified Relation labeling 
	 * the arcs coming from them into the Node containing this UpCable.
	 */
	private NodeSet nodeSet;

	/**
	 * @param relation a Relation that labels the arcs of the UpCable.
	 */
	public UpCable(Relation relation)
	{
		this.relation = relation;
		nodeSet = new NodeSet();
	}

	/**
	 * @return the Relation of this UpCable.
	 */
	public Relation getRelation()
	{
		return relation;
	}

	/**
	 * @return the NodeSet of this UpCable.
	 */
	public NodeSet getNodeSet()
	{
		return nodeSet;
	}
	
	/**
	 * adds the specified node to the node set of this up cable
	 * 
	 * @param node a Node to be added to the set of nodes in this UpCable.
	 */
	public void addNode(Node node)
	{
		this.nodeSet.addNode(node);
	}
	
	/**
	 * removes the specified node from the node set of this up cable
	 * 
	 * @param node a Node to be removed from the node set in this UpCable.
	 */
	public void removeNode(Node node)
	{
		this.nodeSet.removeNode(node);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return this.relation.toString()+" "+this.nodeSet.toString();
	}

}
