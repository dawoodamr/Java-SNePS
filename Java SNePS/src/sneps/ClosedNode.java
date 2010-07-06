package sneps;

/**
 * A ClosedNode is a MolecularNode that does not dominate any free variables. A variable dominated
 * by a molecular node is quantified (not free) if each down path from this molecular node to that
 * variable contains a node  that quantifies that variable
 * 
 * @author Amr Khaled Dawood 
 */
@SuppressWarnings("serial")
public class ClosedNode extends MolecularNode
{

	/**
	 * @param identifier a String representing the name of the ClosedNode
	 * @param cableSet a CableSet that contains all outgoing arcs and their destination Nodes
	 */
	public ClosedNode(String identifier,CableSet cableSet)
	{
		super(identifier,cableSet);
	}
	
}
