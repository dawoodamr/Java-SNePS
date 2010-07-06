package sneps;

import java.io.Serializable;

/**
 * A relation is a five-tuple: name, type, adjust, limit, and path. A relation represents the 
 * arc connecting two nodes in the knowledge base.
 * 
 * @author Amr Khaled Dawood.
 */
@SuppressWarnings("serial")
public class Relation implements Serializable
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
	 * The minimum size of the node set of nodes pointed to by this relation from a given node 
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
	 * @param name a String representing the name of the relation
	 * @param type a String representing the semantic type of the node pointed to by the relation
	 * @param adjust a String representing the adjustability of the relation: 
	 * "reduce", "expand", or "none"
	 * @param limit an int representing the minimum size of the node set of nodes pointed to by the relation from
	 * a given node
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
	 * @return the name of this relation
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the semantic type of the node pointed to by this relation
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @return the adjustability of this relation: "reduce", "expand", or "none"
	 */
	public String getAdjust()
	{
		return adjust;
	}

	/**
	 * @return the limit on the size of the node set of nodes pointed to by this relation
	 * from a given node
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
	 * @param path a Path that is to be defined for this relation
	 */
	public void setPath(Path path)
	{
		this.path = path;
	}

	/**
	 * determines whether this relation is a quantifier or not
	 * 
	 * @return true if this relation is a quantifier and false if not
	 */
	public boolean isQuantifier()
	{
		return quantifier;
	}

	/**
	 * updates the quantifier boolean according to the name of this relation
	 */
	public void setQuantifier()
	{
		if(name.equals("forall") || name.equals("min") || name.equals("max") || name.equals("thresh")
				 || name.equals("threshmax") || name.equals("emin") || name.equals("emax")
				 || name.equals("etot") || name.equals("pevb"))
		{
			this.quantifier = true;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return this.name;
	}
	
}