/**
 * @(#)Request.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/21
 */
package snip.ds;

public class Request
{
	private Channel c;
	
	/**
	 * Create new request with the given channel
	 * @param c channel
	 */
	public Request(Channel c)
	{
		this.c=c;
	}
	
	/**
	 * return the channel of the request
	 * @return
	 */
	public Channel getChannel()
	{
		return c;
	}
}