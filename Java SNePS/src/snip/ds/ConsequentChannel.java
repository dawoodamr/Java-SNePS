/**
 * @(#)ConsequentChannel.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/4/6
 */
package snip.ds;

import sneps.NodeSet;

public class ConsequentChannel
{
	private Channel ch;
	private NodeSet ns;
	private RuleUseInfoSet rs;
	
	/**
	 * Create a new consequent channel with the given arguments
	 * @param c channel
	 * @param n node set
	 * @param r rule use info set
	 */
	public ConsequentChannel(Channel c,NodeSet n,RuleUseInfoSet r)
	{
		ch=c;
		ns=n;
		rs=r;
	}
	
	/**
	 * Return the channel of the consequent channel
	 * @return Channel
	 */
	public Channel getChannel()
	{
		return ch;
	}
	
	/**
	 * Return a set of antecedent nodes
	 * @return NodeSet
	 */
	public NodeSet getAnts()
	{
		return ns;
	}
	
	/**
	 * Return rule use info set
	 * @return RuleUseInfoSet
	 */
	public RuleUseInfoSet getRUIS()
	{
		return rs;
	}
}