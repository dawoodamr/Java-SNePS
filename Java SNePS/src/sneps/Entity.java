package sneps;

import java.io.Serializable;
import java.util.LinkedList;

import snip.ds.Process;

/**
 * The super class of all semantic classes of Nodes.
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class Entity implements Serializable
{

	/**
	 * the node that contains this entity
	 */
	private Node node;
	
	/**
	 * sets the node to null
	 */
	public Entity()
	{
		this.node = null;
	}
	
	/**
	 * @param node a Node that contains this entity
	 */
	public Entity(Node node)
	{
		this.node = node;
	}

	/**
	 * @return the Node that contains this entity
	 */
	public Node getNode()
	{
		return node;
	}
	
	/**
	 * sets the node in this entity to the given one
	 * 
	 * @param node a Node that contains this entity
	 */
	public void setNode(Node node)
	{
		this.node = node;
	}
	
	/**
	 * @return a Process
	 */
	public Process getProcess()
	{
		return null;
	}
	
	/**
	 * for running the process 
	 */
	public void run()
	{
		
	}
	
	/**
	 * gets a list of super classes simple names
	 * 
	 * @return a LinkedList of Strings representing the super classes of this Entity
	 */
	public LinkedList<String> getSuperClasses()
	{
		return getSuperClasses(this.getClass());
	}
	
	/**
	 * gets a list of super classes' simple names for a given class
	 * 
	 * @param c a Class to get the super classes for
	 * @return a LinkedList of Strings representing the simple names of the super classes 
	 * of the given class
	 */
	@SuppressWarnings("unchecked")
	private LinkedList<String> getSuperClasses(Class c)
	{
		LinkedList<String> list = new LinkedList<String>();
		updateSuperClasses(c,list);
		return list;
	}
	
	/**
	 * updates a list of strings by adding the simple names of super classes of a given class
	 * 
	 * @param c a Class to get super classes for
	 * @param list a LinkedList of Strings representing the names of super classes
	 */
	@SuppressWarnings("unchecked")
	private void updateSuperClasses(Class c,LinkedList<String> list)
	{
		Class superClass = c.getSuperclass();
		if(superClass == null)
			return;
		else
		{
			list.add(superClass.getSimpleName());
			updateSuperClasses(superClass,list);
		}
	}
	
}
