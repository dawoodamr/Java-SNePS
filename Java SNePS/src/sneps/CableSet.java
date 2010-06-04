package sneps;

import java.util.LinkedList;

/**
 * This class represents the cables going out of a Node along with the case frame of this
 * cable set. 
 * 
 * @author Amr Khaled Dawood 
 */
public class CableSet
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
	 * @param cables the list of Cables for this CableSet
	 * @param caseFrame the CaseFrame used for this CableSet
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
	 * @param relationName the name of the relation of the cable we need to retrieve
	 * @return the cable with the relation of this relation name
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
	 * @param index the position of the Cable in the list of cables in this cable set
	 * @return the cable at the specified position
	 */
	public Cable getCable(int index)
	{
		return this.cables.get(index); 
	}
	
	/**
	 * determines whether the cable set is empty or not
	 * 
	 * @return true if the list is empty and false if not
	 */
	public boolean isEmpty()
	{
		return this.cables.size() == 0;
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
	 * @param relationName the name of the relation
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

}
