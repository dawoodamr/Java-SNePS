package SNePS.NetworkManagementSystem;

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
	private String type;
	
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
	 * @param name the name of the Relation which is unique
	 * @param type the semantic type of the node that the Relation can point to
	 * @param adjust the adjustability of the Relation "reduce", "expand", or "none"
	 * @param limit the minimum number that the Relation can be reduced to or expanded from
	 */
	public Relation(String name,String type,String adjust,int limit)
	{
		this.name = name;
		this.type = type;
		this.adjust = adjust;
		this.limit = limit;
	}

	/**
	 * @return the name of the Relation
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the semantic type of the node that the Relation can point to
	 */
	public String getType()
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
	 * This method is used to determine whether the specified relation is a quantifier or not
	 * 
	 * @return true if the relation is a quantifier and false if not
	 */
	public boolean isQuantifier()
	{
		return false;
	}
	
}