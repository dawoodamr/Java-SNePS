package sneps;

/**
 * A Relation is a four-tuple name, type, adjust and limit.A Relation represents the 
 * arc connecting two nodes in the knowledge base.
 * 
 * @author Amr Khaled Dawood.
 */
public class Relation
{
	
	/**
	 * This is the name of the Relation.
	 * No two Relations in a knowledge base may have the same name.
	 */
	private String name;
	
	/**
	 * This String represents the semantic type of the node that the Relation 
	 * could be pointing at
	 */
	@SuppressWarnings("unchecked")
	private Class type;
	
	/**
	 * This String represents the adjustability of the Relation which can hold
	 * one of the following values:
	 * "reduce" if the Relation can be reduced
	 * "expand" if the Relation can be expanded
	 * "none" if it is none of these
	 */
	private String adjust;
	
	/**
	 * This int represents the limit that the Relation can be reduced to or
	 * expanded from. 
	 */
	private int limit;
	
	/**
	 * this is the path that is defined for this relation
	 */
	private Path path;
	
	/**
	 * this boolean is true if the relation is quantifier, and false otherwise
	 */
	private boolean quantifier;
	
	/**
	 * @param name the name of the Relation which is unique
	 * @param type the semantic Class of the node that the Relation can point to
	 * @param adjust the adjustability of the Relation "reduce", "expand", or "none"
	 * @param limit the minimum number that the Relation can be reduced to or expanded from
	 */
	@SuppressWarnings("unchecked")
	public Relation(String name,Class type,String adjust,int limit)
	{
		this.name = name;
		this.type = type;
		this.adjust = adjust;
		this.limit = limit;
		this.path = null;
		this.quantifier = false;
	}

	/**
	 * @return the name of the Relation
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the semantic Class of the node that the Relation can point to
	 */
	@SuppressWarnings("unchecked")
	public Class getType()
	{
		return type;
	}

	/**
	 * @return the adjustability of the Relation "reduce", "expand", or "none"
	 */
	public String getAdjust()
	{
		return adjust;
	}

	/**
	 * @return the minimum number that the Relation can be reduced to or expanded from
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
	 * @param path the path that we need to define for this relation
	 */
	public void setPath(Path path)
	{
		this.path = path;
	}

	/**
	 * This method is used to determine whether the specified relation is a quantifier or not
	 * 
	 * @return true if the relation is a quantifier and false if not
	 */
	public boolean isQuantifier()
	{
		return quantifier;
	}

	/**
	 * this method sets the relation to be quantifier
	 */
	public void setQuantifier()
	{
		this.quantifier = true;
	}
	
}