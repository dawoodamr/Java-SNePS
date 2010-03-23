package sneps;

import java.util.LinkedList;

/**
 * An UpCableSet is a set of UpCables that stores information about parents of Nodes.
 * It can be modified after being created if needed.
 * 
 * @author Amr Khaled Dawood
 */
public class UpCableSet
{
	
	/**
	 * A list of UpCables to be added to a Node to get information about the parent Nodes
	 */
	private LinkedList<UpCable> upCables;

	/**
	 * This constructor creates a new UpCableSet with a new empty list of UpCables
	 */
	public UpCableSet()
	{
		this.upCables = new LinkedList<UpCable>();
	}

	/**
	 * @param upCables a list of UpCables to instantiate an UpCableSet with the elements 
	 * in this list.
	 */
	public UpCableSet(LinkedList<UpCable> upCables)
	{
		this.upCables = upCables;
	}
	
	/**
	 * @return the list of UpCables
	 */
	public LinkedList<UpCable> getUpCables()
	{
		return upCables;
	}

	/**
	 * @param upCable an UpCable to be added to the UpCableSet
	 */
	public void addUpCable(UpCable upCable)
	{
		this.upCables.add(upCable);
	}
	
	/**
	 * @param upCable an UpCable to be removed from the UpCableSet
	 */
	public void removeUpCable(UpCable upCable)
	{
		this.upCables.remove(upCable);
	}
	
	/**
	 * @param relation the Relation that labels the arcs of this UpCableSet
	 * @return true if the UpCableSet contains an UpCable with this Relation 
	 * and false if not.
	 */
	public boolean contains(Relation relation)
	{
		for(int i=0;i<this.upCables.size();i++)
		{
			if(this.upCables.get(i).getRelation().getName().equals(relation.getName()))
				return true;
		}
		return false;
	}
	
	/**
	 * @param relation the Relation that labels the arcs of an UpCable in the UpCableSet
	 * @return the UpCable that contains this Relation or null if not found.
	 */
	public UpCable getUpCable(Relation relation)
	{
		for(int i=0;i<this.upCables.size();i++)
		{
			UpCable upCable = this.upCables.get(i);
			if(upCable.getRelation().getName().equals(relation.getName()))
				return upCable;
		}
		return null;
	}
	
	/**
	 * @param relationName the name of the relation that labels the arcs of the required
	 * up cable
	 * @return the up cable of the specified relation name
	 */
	public UpCable getUpCable(String relationName)
	{
		for(int i=0;i<this.upCables.size();i++)
		{
			UpCable upCable = this.upCables.get(i);
			if(upCable.getRelation().getName().equals(relationName))
				return upCable;
		}
		return null;
	}
	
	/**
	 * @return true if the cable set has no cables inside 
	 */
	public boolean isEmpty()
	{
		return upCables.size() == 0;
	}

}
