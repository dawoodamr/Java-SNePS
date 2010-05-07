/**
 * @(#)Destination.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/14
 */
package snip.ds;

import sneps.Node;

public class Destination
{
	Node node;
	
	/**
	 *Creates new destination
	 */
	public Destination(Node n)
	{
		node = n;
	}
	
	/**
	 * Check if this and d pointing to the same node
	 * @param d destination
	 * @return true if equal false otherwise
	 */
	public boolean isEqual(Destination d)
	{
		return d.node==this.node;
	}
	
	/**
	 * Returns the node this destination is pointing to
	 * @return
	 */
	public Node getNode()
	{
		return node;
	}
}