/**
 * @(#)ChannelSet.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/14
 */
package snip.ds;
import java.util.Vector;

public class ChannelsSet
{
	private Vector<Channel> ch;
	/**
	 * Create new empty channels list
	 */
	public ChannelsSet()
	{
		ch=new Vector<Channel>();
	}
	
	/**
	 * Check if the channel list is new
	 * @return true or false
	 */
	public boolean isNew()
	{
		return ch.isEmpty();
	}
	
	/**
	 * Insert channel c in the channels list
	 * @param c channel
	 */
	public void putIn(Channel c)
	{
			ch.add(c);
	}
	
	/**
	 * Return the first channel in the channels list
	 * @return channel
	 */
	public Channel choose()
	{
		return ch.get(1);
	}
	
	/**
	 * Return a channels list with all the channels in the channels list 
	 * except the first channel 
	 * @return ChannelSet
	 */
	public ChannelsSet others()
	{
		ChannelsSet ch1=new ChannelsSet();
		for(int i=1;i<this.ch.size();i++)
		{
			ch1.ch.add(this.ch.get(i));
		}
		return  ch1;
	}
	
	/**
	 * Remove the channel c from the channels list
	 * @param c channel
	 */
	public void remove(Channel c)
	{
		for(int i=0;i<ch.size();i++)
		{
			if(c.equivalent(ch.get(i)))
			{
				ch.remove(i);
			}
		}
	}
	
	/**
	 * Insert channel c in the channels list if it dosen't exist before else
	 * set the valve of the existing one to the valve of the new one
	 * @param c channel
	 */
	public void insert(Channel c)
	{
		boolean found=false;
		Channel c1=null;
		for(int i=0;i<ch.size();i++)
		{
			if(c.equivalent(ch.get(i)))
			{
				found = true;
				c1=ch.get(i);
			}
		}
		if (!found)
			ch.add(c);
		else
		{
			if(c.getValve())
				c1.open();
			else
				c1.close();
		}
	}
	
	/**
	 * Returns the number of channels in the channels list
	 * @return integer
	 */
	public int cardinality()
	{
		return ch.size();
	}
	
	/**
	 * Return the channel number x in the channels list
	 * @param x channel number
	 * @return
	 */
	public Channel getChannel(int x)
	{
		return ch.get(x);
	}
}