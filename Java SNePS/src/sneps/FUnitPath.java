package sneps;

/**
 * the FUnitPath (forward unit path) class is a path which is only a relation.
 * 
 * @author Amr Khaled Dawood
 */
public class FUnitPath extends Path
{

	/**
	 * the name of the relation that identifies this unit path
	 */
	private String relationName;
	
	/**
	 * @param relationName the name of the relation that identifies this unit path
	 */
	public FUnitPath(String relationName)
	{
		this.relationName = relationName;
	}

	/**
	 * @return the name of the relation that this FUnitPath is defined for
	 */
	public String getRelationName()
	{
		return relationName;
	}

	/* (non-Javadoc)
	 * @see ds.Path#follow(ds.Node)
	 */
	@Override
	public NodeSet follow(Node node)
	{
		NodeSet result = new NodeSet();
		if(node.getClass().getSuperclass().getSimpleName().equals("MolecularNode"))
		{
			MolecularNode mNode = (MolecularNode) node;
			CableSet cableSet = mNode.getCableSet();
			Cable cable = cableSet.getCable(relationName);
			if(cable != null)
				result.getNodes().addAll(cable.getNodeSet().getNodes());
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node)
	 */
	@Override
	public NodeSet followConverse(Node node)
	{
		NodeSet result = new NodeSet();
		UpCableSet upCableSet = node.getUpCableSet();
		UpCable upCable = upCableSet.getUpCable(relationName);
		if(upCable != null)
			result.getNodes().addAll(upCable.getNodeSet().getNodes());
		
		return result;
	}

}