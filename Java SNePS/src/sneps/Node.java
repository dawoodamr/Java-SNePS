package sneps;

/**
 * The Node is the main unit used in building the network of the knowledge base.
 * Each Node has an id, an identifier, an upCableSet and  an entity.
 * The class Node is the super class of all syntactic classes of nodes.
 * The Node class is an abstract class. 
 * 
 * @author Amr Khaled Dawood
 */
public abstract class Node
{
	
	/**
	 * The counter used for counting the nodes created
	 */
	private static int count = 1;
	
	/**
	 * The id is like an index of the node
	 */
	private int id;
	
	/**
	 * The identifier - name - of the node
	 */
	private String identifier;

	/**
	 * The UpCableSet that contains information about the parents of this Node.
	 */
	private UpCableSet upCableSet;
	
	/**
	 * The instance of the semantic class of this Node.
	 */
	private Entity entity;
	
	/**
	 * @param identifier the name of the Node
	 */
	public Node(String identifier)
	{
		this.identifier = identifier;
		this.upCableSet = new UpCableSet();
		this.entity = new Entity(this);
		this.id = count;
		count++;		
	}

	/**
	 * @return the id of this node
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @return the identifier of this node
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
	 * @return the instance of the semantic class that this node belongs to
	 */
	public Entity getEntity()
	{
		return entity;
	}

	/**
	 * sets the entity of this node to the specified entity
	 * 
	 * @param entity the new instance of the semantic class to replace the older one
	 */
	public void setEntity(Entity entity)
	{
		this.entity = entity;
	}

}
