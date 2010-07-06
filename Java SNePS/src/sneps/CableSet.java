package sneps;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * This class represents the cables going out of a Node along with the case frame of this
 * cable set. 
 * 
 * @author Amr Khaled Dawood 
 */
@SuppressWarnings("serial")
public class CableSet implements Serializable
{
	
	/**
	 * The list of cables included in this CableSet.
	 */
	private LinkedList<Cable> cables;
	
	/**
	 * The case frame that determines how the CableSet should look like.
	 */
	private CaseFrame caseFrame;

	/**
	 * @param cables is a list of Cables
	 * @param caseFrame is a CaseFrame
	 */
	public CableSet(LinkedList<Cable> cables,CaseFrame caseFrame)
	{
		this.cables = cables;
		this.caseFrame = caseFrame;
	}

	/**
	 * @return the CaseFrame used in this CableSet.
	 */
	public CaseFrame getCaseFrame()
	{
		return caseFrame;
	}
	
	/**
	 * @param relationName the name of the relation of the cable that is to be retrieved
	 * @return the cable with the relation of this relation name, or null if it does not exist
	 */
	public Cable getCable(String relationName)
	{
		for(int i=0;i<cables.size();i++)
		{
			if(cables.get(i).getRelation().getName().equals(relationName))
				return cables.get(i);
		}
		
		return null;
	}
	
	/**
	 * gets the cable at the specified position
	 * 
	 * @param index an int representing the position of the Cable in the list of cables in 
	 * this cable set
	 * @return the cable at the specified position
	 */
	public Cable getCable(int index)
	{
		return this.cables.get(index); 
	}
	
	/**
	 * gets the size of the list of cables in this cable set
	 * 
	 * @return the size of the cable list of this cable set
	 */
	public int size()
	{
		return this.cables.size();
	}
	
	/**
	 * determines whether a cable with the relation having the specified name 
	 * exists in this cable set or not
	 * 
	 * @param relationName a String representing the name of the relation
	 * @return true if the cable with the specified relation exists in the cable set,
	 *  and false otherwise
	 */
	public boolean contains(String relationName)
	{
		for(int i=0;i<cables.size();i++)
		{
			if(cables.get(i).getRelation().getName().equals(relationName))
				return true;
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		String s = "";
		for(int i=0;i<this.cables.size();i++)
		{
			s += this.cables.get(i).toString();
			if(i<this.cables.size()-1)
				s += " ";
		}
		return s;
	}
	
}
