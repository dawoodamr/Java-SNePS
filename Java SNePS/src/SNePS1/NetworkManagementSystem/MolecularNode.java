package SNePS1.NetworkManagementSystem;

/**
 * A MolecularNode is a Node that has a CableSet, therefore it has outgoing arcs and child 
 * Nodes. It's an abstract class which means before using it we have to extend it by other
 * classes to inherit all its attributes and methods.
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
		super(identifier,new UpCableSet(),new Entity());
		this.cableSet = cableSet;
	}

	/**
	 * @return the CableSet of the MolecularNode
	 */
	public CableSet getCableSet()
	{
		return this.cableSet;
	}
	
}