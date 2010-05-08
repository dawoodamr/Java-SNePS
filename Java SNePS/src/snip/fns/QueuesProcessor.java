/**
 * @(#)QueuesProcessor.java
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/5/8
 */

package snip.fns;

import sneps.Entity;
import snip.ds.HighQueue;
import snip.ds.LowQueue;

public class QueuesProcessor
{
	LowQueue l;
	HighQueue h;
	
	/**
	 * Create a new queue processor with a high and a low priority queues
	 * @param low LowQueue
	 * @param high HighQueue
	 */
	public QueuesProcessor(LowQueue low,HighQueue high)
	{
		l=low;
		high=h;
	}
	
	/**
	 * Keep looping on the queues running the processes tell they are empty.
	 */
	public void process()
	{
		if(!h.isEmpty())
		{
			h.dequeue();
			process();
		}
		else if(!l.isEmpty())
		{
			l.dequeue();
			process();
		}
	}
	
	/**
	 * Add a new process to the low priority queue
	 * @param e
	 */
	public void addToLow(Entity e)
	{
		l.add(e);
	}
	
	/**
	 * Add a new process to the high priority queue
	 * @param e
	 */
	public void addToHigh(Entity e)
	{
		h.add(e);
	}
}