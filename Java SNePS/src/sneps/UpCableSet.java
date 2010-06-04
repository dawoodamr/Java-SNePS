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
	 * A list of UpCables stored in a Node to keep information about the parent Nodes
	 */
	private LinkedList<UpCable> upCables;

	/**
	 * creates a new empty UpCableSet
	 */
	public UpCableSet()
	{
		this.upCables = new LinkedList<UpCable>();
	}

	/**
	 * adds the specified up cable to this up cable set
	 * 
	 * @param upCable an UpCable to be added to this UpCableSet
	 */
	public void addUpCable(UpCable upCable)
	{
		this.upCables.add(upCable);
	}
	
	/**
	 * removes the specified up cable from this up cable set
	 * 
	 * @param upCable an UpCable to be removed from this UpCableSet
	 */
	public void removeUpCable(UpCable upCable)
	{
		this.upCables.remove(upCable);
	}
	
	/**
	 * gets the up cable with the specified relation
	 * 
	 * @param relation the Relation that labels the arcs of an UpCable in this UpCableSet
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
	 * gets the up cable with the relation that its name is specified
	 * 
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
	 * gets the up cable at the specified position
	 * 
	 * @param index the position of the up cable
	 * @return the up cable at the specified position
	 */
	public UpCable getUpCable(int index)
	{
		return this.upCables.get(index);
	}
	
	/**
	 * checks whether this up cable set is empty or not
	 * 
	 * @return true if this cable set is empty
	 */
	public boolean isEmpty()
	{
		return upCables.size() == 0;
	}
	
	/**
	 * gets the size of this up cable set
	 * 
	 * @return the size of this up cable set
	 */
	public int size()
	{
		return this.upCables.size();
	}
	
	/**
	 * checks whether this up cable set contains an up cable with the specified relatin or not
	 * 
	 * @param relation the Relation that labels the arcs of this UpCableSet
	 * @return true if the UpCableSet contains an UpCable with this Relation, 
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

}
