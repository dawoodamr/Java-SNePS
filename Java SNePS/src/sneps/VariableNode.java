package sneps;

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
	 * the pointer used in the unification of variables
	 */
	private Object rPointer;
	
	/**
	 * the substitution pointer used in the unification of variables
	 */
	private Object sPointer;

	/**
	 * this constructor creates a new VariableNode with a new empty UpCableSet and
	 * a new instance of the semantic class
	 * 
	 * @param identifier the name of the VariableNode
	 */
	public VariableNode(String identifier)
	{
		super(identifier);
	}
	
	/**
	 * @return the rPointer used in the unification
	 */
	public Object getRPointer()
	{
		return rPointer;
	}

	/**
	 * @param rPointer the new Object that is pointed to by this pointer
	 */
	public void setRPointer(Object rPointer)
	{
		this.rPointer = rPointer;
	}

	/**
	 * @return the sPointer used in the unification
	 */
	public Object getSPointer()
	{
		return sPointer;
	}

	/**
	 * @param sPointer the new Object that is pointed to by this pointer
	 */
	public void setSPointer(Object sPointer)
	{
		this.sPointer = sPointer;
	}
	
}
