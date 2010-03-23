package sneps;

/**
 * A ClosedNode is a MolecularNode that does not dominate any free variables, that is 
 * if there are VariableNodes in the subtree that this ClosedNode is considered to be 
 * its root then these VariableNodes must be quantified using quantifier in this subtree.
 * A quantifier is a relation whose name is a quantifier(forall,..etc).
 * 
 * @author Amr Khaled Dawood
 */
public class ClosedNode extends MolecularNode
{

	/**
	 * @param identifier the name of the ClosedNode
	 * @param cableSet the CableSet that contains all outgoing arcs and their destination Nodes
	 */
	public ClosedNode(String identifier,CableSet cableSet)
	{
		super(identifier,cableSet);
	}
	
}
