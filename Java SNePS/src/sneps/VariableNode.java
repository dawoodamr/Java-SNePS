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
	 * the flag used in the unification of variables to show that there is a loop using r-pointer
	 */
	private boolean rLoop;
	
	/**
	 * the flag used in the unification of variables to show that there is a loop using s-pointer
	 */
	private boolean sLoop;

	/**
	 * this constructor creates a new VariableNode with a new empty UpCableSet and
	 * a new instance of the semantic class
	 * 
	 * @param identifier the name of the VariableNode
	 */
	public VariableNode(String identifier)
	{
		super(identifier);
		rLoop = false;
		sLoop = false;
	}

	/**
	 * @return the flag loop that is used in the unification for r-pointer
	 */
	public boolean isRLoop()
	{
		return rLoop;
	}

	/**
	 * @param loop the value that the flag is set to
	 */
	public void setRLoop(boolean rloop)
	{
		this.rLoop = rloop;
	}
	
	/**
	 * @return the flag loop that is used in the unification for s-pointer
	 */
	public boolean isSLoop()
	{
		return sLoop;
	}

	/**
	 * @param loop the value that the flag is set to
	 */
	public void setSLoop(boolean sloop)
	{
		this.sLoop = sloop;
	}
	
}
