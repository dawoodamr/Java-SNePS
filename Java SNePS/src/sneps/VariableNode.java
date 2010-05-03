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
	 * the flag used in the unification of variables
	 */
	private boolean loop;

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
	 * @return the flag loop that is used in the unification
	 */
	public boolean isLoop()
	{
		return loop;
	}

	/**
	 * @param loop the value that the flag is set to
	 */
	public void setLoop(boolean loop)
	{
		this.loop = loop;
	}
	
}
