package sneps;

/**
 * A BaseNode is a Node that has no outgoing arcs and is not a VariableNode.
 * It inherits all attributes and methods from the super class Node.
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class BaseNode extends Node
{
	
	/**
	 * @param identifier a String representing the name of the BaseNode
	 */
	public BaseNode(String identifier)
	{
		super(identifier);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return this.getIdentifier();
	}
	
}
