package sneps;

/**
 * A BaseNode is a Node that has no outgoing arcs and is not a VariableNode.
 * It inherits all attributes and methods from the super class Node.
 * 
 * @author Amr Khaled Dawood
 */
public class BaseNode extends Node
{
	
	/**
	 * the constructor sets the chosen name for the BaseNode and creates new empty UpCableSet
	 * as well as a new instance of the semantic class Entity.
	 * 
	 * @param identifier the name of the BaseNode
	 */
	public BaseNode(String identifier)
	{
		super(identifier,new UpCableSet(),new Entity());
	}
	
}
