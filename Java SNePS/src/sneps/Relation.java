package sneps;

/**
 * A relation is a five-tuple: name, type, adjust, limit, and path.A relation represents the 
 * arc connecting two nodes in the knowledge base.
 * 
 * @author Amr Khaled Dawood.
 */
public class Relation
{
	
	/**
	 * The name of this relation.
	 * No two relations in a knowledge base may have the same name.
	 */
	private String name;
	
	/**
	 * The semantic type of the node pointed to by the relation.
	 */
	private String type;
	
	/**
	 * The adjustability of this relation which can hold one of the following values:
	 * "reduce" if the relation is reducible,
	 * "expand" if the relation is expandable, and 
	 * "none" if it is neither.
	 */
	private String adjust;
	
	/**
	 * The limit that this Relation can be reduced to or expanded from. 
	 */
	private int limit;
	
	/**
	 * The path defined for this relation.
	 */
	private Path path;
	
	/**
	 * A boolean that is true if the relation is a quantifier one, and false otherwise.
	 */
	private boolean quantifier;
	
	/**
	 * @param name the name of the relation
	 * @param type the semantic type of the node pointed to by the relation
	 * @param adjust the adjustability of the relation: "reduce", "expand", or "none"
	 * @param limit the minimum number that the Relation can be reduced to or expanded from
	 */
	public Relation(String name,String type,String adjust,int limit)
	{
		this.name = name;
		this.type = type;
		this.adjust = adjust;
		this.limit = limit;
		this.path = null;
		setQuantifier();
	}

	/**
	 * @return the name of the relation
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the semantic type of the node pointed to by the relation
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @return the adjustability of the relation: "reduce", "expand", or "none"
	 */
	public String getAdjust()
	{
		return adjust;
	}

	/**
	 * @return the minimum number that the relation can be reduced to or expanded from
	 */
	public int getLimit()
	{
		return limit;
	}
	
	/**
	 * @return the path that is defined for this relation
	 */
	public Path getPath()
	{
		return path;
	}

	/**
	 * @param path the path that is to be defined for this relation
	 */
	public void setPath(Path path)
	{
		this.path = path;
	}

	/**
	 * determines whether this relation is a quantifier or not
	 * 
	 * @return true if the relation is a quantifier and false if not
	 */
	public boolean isQuantifier()
	{
		return quantifier;
	}

	/**
	 * updates the quantifier boolean according to the name
	 */
	public void setQuantifier()
	{
		if(name.equals("forall") || name.equals("min") || name.equals("max") || name.equals("thresh")
				 || name.equals("threshmax") || name.equals("emin") || name.equals("emax")
				 || name.equals("etot"))
		{
			this.quantifier = true;
		}
	}
	
}