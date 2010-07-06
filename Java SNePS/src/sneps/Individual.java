package sneps;

/**
 * The Individual class represents a special type of Entity that is assigned for individuals in 
 * the knowledge base
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class Individual extends Entity
{

	/**
	 * @param node a Node that contains this individual
	 */
	public Individual(Node node)
	{
		super(node);
	}

}
