package sneps;

/**
 * The Node is the main unit used in building the graph of the knowledge base.
 * Each Node has an identifier, an UpCableSet and  an Entity.
 * The class Node is the super class of all syntactic classes of Nodes.
 * The Node class is abstract so that we have to create subclasses to inherit from it
 * depending on the hierarchy of Node classes. 
 * 
 * @author Amr Khaled Dawood
 */
public abstract class Node
{
	
	/**
	 * the counter used for counting the nodes created
	 */
	private static int count = 1;
	
	/**
	 * the id is like an index of the node
	 */
	private int id;
	
	/**
	 * the identifier - name - of the node.No two nodes in the network have the same name.
	 */
	private String identifier;

	/**
	 * the UpCableSet that contains information about the parents of this Node.
	 */
	private UpCableSet upCableSet;
	
	/**
	 * the instance of the semantic class of this Node.
	 */
	private Entity entity;
	
	/**
	 * @param identifier the name of the Node
	 * @param upCableSet the UpCableSet that contains information about the parents of this Node
	 * @param entity the instance of the semantic class that this Node belongs to
	 */
	public Node(String identifier)
	{
		this.identifier = identifier;
		this.upCableSet = new UpCableSet();
		this.entity = new Entity();
		this.entity.setNode(this);
		this.id = count;
		count++;		
	}

	/**
	 * @return the id of the node that uniquely identifies it
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @return the identifier - name - of this Node
	 */
	public String getIdentifier()
	{
		return identifier;
	}

	/**
	 * @return the UpCableSet of this node
	 */
	public UpCableSet getUpCableSet()
	{
		return upCableSet;
	}

	/**
	 * @return the instance of the semantic class that this Node belongs to
	 */
	public Entity getEntity()
	{
		return entity;
	}

	/**
	 * @param entity the new instance of the semantic class to replace the older one
	 */
	public void setEntity(Entity entity)
	{
		this.entity = entity;
	}

}
