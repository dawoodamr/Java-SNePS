package sneps;

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
	 * @return the node that contains this entity
	 */
	public Node getNode()
	{
		return node;
	}
	
	/**
	 * this method is used after creating the entity in order to set the node that contains 
	 * this entity
	 * 
	 * @param node the node that contains the entity
	 */
	public void setNode(Node node)
	{
		this.node = node;
	}
	
}
