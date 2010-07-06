package sneps;

/**
 * A MolecularNode is a Node that has a CableSet, therefore it has outgoing arcs and child 
 * Nodes. It's an abstract class which means before using it, it should be extended using 
 * another sub class.
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public abstract class MolecularNode extends Node
{
	
	/**
	 * A CableSet that contains Cables going out of this MolecularNode.
	 */
	private CableSet cableSet;

	/**
	 * @param identifier a String representing the name of the Node
	 * @param cableSet a CableSet that is instantiated with the MolecularNode
	 */
	public MolecularNode(String identifier,CableSet cableSet)
	{
		super(identifier);
		this.cableSet = cableSet;
		this.updateUpCables();
	}

	/**
	 * @return the CableSet of this MolecularNode
	 */
	public CableSet getCableSet()
	{
		return this.cableSet;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return this.getIdentifier()+":("+this.getCableSet().toString()+")";
	}
	
	/**
	 * updates the up cables of child nodes of the given molecular node
	 */
	private void updateUpCables()
	{
		CableSet cableSet = this.getCableSet();
		for(int i=0;i<cableSet.size();i++)
		{
			Relation r = cableSet.getCable(i).getRelation();
			for(int j=0;j<cableSet.getCable(i).getNodeSet().size();j++)
			{
				Node n = cableSet.getCable(i).getNodeSet().getNode(j);
				if(! n.getUpCableSet().contains(r))
					n.getUpCableSet().addUpCable(new UpCable(r));
				n.getUpCableSet().getUpCable(r.getName()).addNode(this);
			}
		}
	}
	
}