/**
 * @(#)HighQueue.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/5/8
 */
package snip.ds;

import java.util.LinkedList;
import java.util.Queue;

import sneps.Entity;

public class HighQueue
{
	Queue<Entity> q;
	
	/**
	 * Create a new queue
	 */
	public HighQueue()
	{
		q=new LinkedList<Entity>();
	}
	
	/**
	 * Check if the queue is empty or not
	 * @return true or false
	 */
	public boolean isEmpty()
	{
		return q.isEmpty();
	}
	
	/**
	 * Add a new Entity to the queue
	 * @param e Entity
	 */
	public void add(Entity e)
	{
		q.add(e);
	}
	
	/**
	 * Run the first Entity is the queue and remove it
	 */
	public void dequeue()
	{
		//q.poll().run();
	}
}