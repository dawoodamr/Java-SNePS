package SNePS.NetworkManagementSystem;

import java.util.LinkedList;

/**
 * This class represents the cables going out of a Node along with the case frame of this
 * cable set.So we can say that a Node is a Cable set.once a cable is created it can never 
 * be changed.
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
	 * @return the list of Cables in this CableSet.
	 */
	public LinkedList<Cable> getCables()
	{
		return cables;
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
	 * @return true if the list is empty and false if not
	 */
	public boolean isEmpty()
	{
		if(cables.size() == 0)
			return true;
		return false;
	}
	
	/**
	 * @param relationName the name of the relation that we need to know whether the 
	 * cable set contains or not
	 * @return true if the relation exists in the cable set
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
