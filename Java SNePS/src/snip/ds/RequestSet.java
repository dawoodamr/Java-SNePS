/**
 * @(#)RequestSet.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/21
 */
package snip.ds;

import java.util.Vector;

public class RequestSet
{
	private Vector<Request> rs;
	
	/**
	 * Create new request set
	 */
	public RequestSet()
	{
		rs=new Vector<Request>();
	}
	
	/**
	 * Return the request number x in the set
	 * @param x
	 * @return Request
	 */
	public Request getRequest(int x)
	{
		return rs.get(x); 
	}
	
	/**
	 * Return the number of requests in the set
	 */
	public int cardinality()
	{
		return rs.size();
	}
}