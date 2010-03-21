package SNePS.NetworkManagementSystem;

/**
 * An UpCable is a cable that contains the information needed for a node about its parents 
 * and the relations coming into it from them.It contains a Relation and a NodeSet for parents.
 * 
 * @author Amr Khaled Dawood
 */
public class UpCable
{
	
	/**
	 * the Relation that labels the arcs represented by this UpCable.
	 */
	private Relation relation;
	
	/**
	 * the NodeSet that contains that parent nodes that have the specified Relation labeling 
	 * the arcs coming from them into the Node containing this UpCable.
	 */
	private NodeSet nodeSet;

	/**
	 * @param relation the Relation that labels the arcs of this UpCable.
	 */
	public UpCable(Relation relation)
	{
		this.relation = relation;
		nodeSet = new NodeSet();
	}

	/**
	 * @param relation the Relation that labels the arcs of this UpCable.
	 * @param nodeSet the NodeSet that contains the parent nodes of the node containing 
	 * this UpCable that have arcs to this node labeled by this Relation.
	 */
	public UpCable(Relation relation,NodeSet nodeSet)
	{
		this.relation = relation;
		this.nodeSet = nodeSet;
	}

	/**
	 * @return the relation labeling the arcs of this UpCable.
	 */
	public Relation getRelation()
	{
		return relation;
	}

	/**
	 * @return the NodeSet containing the nodes of this UpCable.
	 */
	public NodeSet getNodeSet()
	{
		return nodeSet;
	}
	
	/**
	 * @param node a Node to be added to the list of nodes in the UpCable.
	 */
	public void addNode(Node node)
	{
		this.nodeSet.getNodes().add(node);
	}
	
	/**
	 * @param node a Node to be removed from the node set in this UpCable.
	 */
	public void removeNode(Node node)
	{
		this.nodeSet.getNodes().remove(node);
	}

}
