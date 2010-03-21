package SNePS.NetworkManagementSystem;

/**
 * A cable is a pair of two things: a Relation and a NodeSet.It describes the arcs 
 * going from a Node to some Nodes - that are in the NodeSet - with the same Relation.
 * Once a Cable is created it can never be changed.
 * @author Amr Khaled Dawood
 */
public class Cable
{
	
	/**
	 *  The Relation on the arcs connecting the Node that has this Cable to other Nodes.
	 */
	private Relation relation;
	
	/**
	 * The NodeSet that specifies the Nodes that are reachable from the parent node 
	 * using the arcs labeled with the Relation called: relation.
	 */
	private NodeSet nodeSet;
	
	/**
	 * @param relation the Relation that labels the arcs of this Cable
	 * @param nodeSet the NodeSet that specifies the Nodes reachable by the arcs in the Cable.
	 */
	public Cable(Relation relation,NodeSet nodeSet)
	{
		this.relation = relation;
		this.nodeSet = nodeSet;
	}

	/**
	 * @return the Relation of this Cable
	 */
	public Relation getRelation()
	{
		return relation;
	}

	/**
	 * @return the NodeSet of this Cable
	 */
	public NodeSet getNodeSet()
	{
		return nodeSet;
	}

}
