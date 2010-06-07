package sneps;

import snip.ds.Process;

/**
 * The super class of all semantic classes of Nodes.
 * 
 * @author Amr Khaled Dawood
 */
public class Entity
{

	/**
	 * the node that contains this entity
	 */
	private Node node;
	
	/**
	 * sets the node to null
	 */
	public Entity()
	{
		this.node = null;
	}
	
	/**
	 * @param node a Node that contains this entity
	 */
	public Entity(Node node)
	{
		this.node = node;
	}

	/**
	 * @return a Node that contains this entity
	 */
	public Node getNode()
	{
		return node;
	}
	
	/**
	 * sets the node in this entity
	 * 
	 * @param node a Node that contains this entity
	 */
	public void setNode(Node node)
	{
		this.node = node;
	}
	
	/**
	 * @return a Process
	 */
	public Process getProcess()
	{
		return null;
	}
	
}
