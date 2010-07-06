package sneps;

/**
 * A VariableNode is a Node that has no outgoing arcs - but probably has incoming arcs - 
 * and it is considered to be variable in the sense that it can be unified with other 
 * non-variable Nodes.
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
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
	 * @param identifier a String representing the name of the VariableNode
	 */
	public VariableNode(String identifier)
	{
		super(identifier);
		rLoop = false;
		sLoop = false;
	}

	/**
	 * @return the boolean flag: loop that is used in the unification for r-pointer
	 */
	public boolean isRLoop()
	{
		return rLoop;
	}

	/**
	 * sets the rLoop to the value specified
	 * 
	 * @param loop a boolean which is the value that the flag is set to
	 */
	public void setRLoop(boolean rloop)
	{
		this.rLoop = rloop;
	}
	
	/** 
	 * @return the boolean flag: loop that is used in the unification for s-pointer
	 */
	public boolean isSLoop()
	{
		return sLoop;
	}

	/**
	 * sets the sLoop to the value specified
	 * 
	 * @param loop a boolean which is the value that the flag is set to
	 */
	public void setSLoop(boolean sloop)
	{
		this.sLoop = sloop;
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
