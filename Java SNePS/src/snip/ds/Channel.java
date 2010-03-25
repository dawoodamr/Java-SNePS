/**
 * @(#)Channel.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/14
 */
package snip.ds;

public class Channel
{
	private Filter fil;
	private Switch swi;
	private Object con;
	private Destination des;
	private boolean valve;
	
	/**
	 * Create a new channel with the given arguments
	 * @param f filter
	 * @param s switch
	 * @param c context
	 * @param d destination
	 * @param v true for open false for closed
	 */
	public Channel(Filter f,Switch s,Object c,Destination d,boolean v)
	{
		fil=f;
		swi=s;
		con=c;
		des=d;
		valve=v;
	}
	
	/**
	 * Returns the filter of the channel
	 * @return filter
	 */
	public Filter getFilter()
	{
		return fil;
	}
	
	/**
	 * Return the switch of the channel
	 * @return switch
	 */
	public Switch getSwitch()
	{
		return swi;
	}
	
	/**
	 * Return the context of the channel
	 * @return context
	 */
	public Object getContext()
	{
		return con;
	}
	
	/**
	 * Return the destination of the channel
	 * @return destination
	 */
	public Destination getDestination()
	{
		return des;
	}
	
	/**
	 * Return the valve of the channel
	 * @return valve
	 */
	public boolean getValve() 
	{
		return valve;
	}
	
	/**
	 * Check if the channel is open or not
	 * @return true if open false otherwise
	 */
	public boolean isOpen() 
	{
		return valve;
	}
	
	/**
	 * Check if the channel is closed or not
	 * @return true if closed false otherwise
	 */
	public boolean isClosed() 
	{
		return !valve;
	}
	
	/**
	 * Check if the channel c is equivalent to this by checking the switches, filters,
	 * contexts and destinations
	 * @param c channel
	 * @return true or false
	 */
	public boolean equivalent(Channel c)
	{
		return this.fil.isEqual(c.fil)&&this.swi.isEqual(c.swi)&&
		this.des.isEqual(c.des)&&this.con==c.con;//--------context----------
	}
	
	/**
	 * Open the channel
	 */
	public void open()
	{
		valve =true;
	}
	
	/**
	 * close the channel
	 */
	public void close()
	{
		valve =false;
	}
	
	/**
	 * Sends the report r over this channel (not final)
	 * @param r report
	 */
	public void send(Report r)
	{
		
	}
}