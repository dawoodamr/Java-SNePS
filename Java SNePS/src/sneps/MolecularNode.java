package sneps;

/**
 * A MolecularNode is a Node that has a CableSet, therefore it has outgoing arcs and child 
 * Nodes. It's an abstract class which means before using it, it should be extended using 
 * another sub class.
 * 
 * @author Amr Khaled Dawood
 */
public abstract class MolecularNode extends Node
{
	
	/**
	 * A CableSet that contains Cables going out of this MolecularNode.
	 */
	private CableSet cableSet;

	/**
	 * @param identifier the name of the Node
	 * @param cableSet the CableSet that is instantiated with the MolecularNode
	 */
	public MolecularNode(String identifier,CableSet cableSet)
	{
		super(identifier);
		this.cableSet = cableSet;
	}

	/**
	 * @return the CableSet of this MolecularNode
	 */
	public CableSet getCableSet()
	{
		return this.cableSet;
	}
	
}