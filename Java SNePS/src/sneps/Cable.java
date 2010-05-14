package sneps;

import java.lang.reflect.Constructor;

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
		this.updateSemanticClasses();
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
	
	@SuppressWarnings("unchecked")
	public void updateSemanticClasses()
	{
		for(int i=0;i<nodeSet.getNodes().size();i++)
		{
			Node n = nodeSet.getNodes().get(i);
			if(n.getUpCableSet().isEmpty())
			{
				try {
					Class c = Class.forName("sneps."+this.relation.getType());
					Constructor con = c.getConstructor(new Class[] {Node.class});
					Entity e = (Entity) con.newInstance(n);
					n.setEntity(e);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
	}

}
