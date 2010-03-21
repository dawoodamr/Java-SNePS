package SNePS.NetworkManagementSystem;

/**
 * A VariableNode is a Node that has no outgoing arcs - but has incoming arcs probably - 
 * and it is considered to be variable in the sense that it can be unified with other 
 * non-variable Nodes.
 * 
 * @author Amr Khaled Dawood
 */
public class VariableNode extends Node
{
	
	/**
	 * this constructor creates a new VariableNode with a new empty UpCableSet and
	 * a new instance of the semantic class
	 * 
	 * @param identifier the name of the VariableNode
	 */
	public VariableNode(String identifier)
	{
		super(identifier,new UpCableSet(),new Entity());
	}
	
}
