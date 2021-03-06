/**
 * @(#)Report.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/21
 */
package snip.ds;

import snebr.Context;
import sneps.Node;
import match.ds.Binding;
import match.ds.Substitutions;

public class Report
{
	private Substitutions s;
	private Object sup; //------------support-----------
	private boolean sign;
	private Node sig;
	private Node node;
	private Context ct;
	
	/**
	 * Create new report with the given arguments
	 * @param substitution Substitution
	 * @param support support
	 * @param sign positive of negative
	 * @param signature the node this report is from
	 * @param node the node this report is to
	 * @param context context
	 */
	public Report(Substitutions substitution,Object support,boolean sign,
			Node signature,Node node,Context context )
	{
		s=substitution;
		sup=support;
		this.sign=sign;
		sig=signature;
		this.node=node;
		ct=context;
	}
	
	/**
	 * return the substitutions list of the report
	 * @return Substitution
	 */
	public Substitutions getSubstitutions()
	{
		return s;
	}
	
	/**
	 * return the support of the report
	 * @return support
	 */
	public Object getSupport()
	{
		return sup;
	}
	
	/**
	 * return the sign of the report
	 * @return sign
	 */
	public boolean getSign()
	{
		return sign;
	}
	
	/**
	 * return the signature of the report
	 * @return Signature
	 */
	public Node getSignature()
	{
		return sig;
	}
	
	/**
	 * return the node of the report
	 * @return node
	 */
	public Node getNode()
	{
		return node;
	}
	
	/**
	 * return the context of the report
	 * @return context
	 */
	public Context getContext()
	{
		return ct;
	}
	
	/**
	 * Add the given binding to the substitutions list of the report
	 * @param m Binding
	 */
	public void addBinding(Binding m)
	{
		this.s.putIn(m);
	}
	
	/**
	 * Check if the report r is equal to this
	 * @param r report
	 * @return true or false
	 */
	public boolean isEqual(Report r)
	{
		if(this.s.isEqual(r.s)&& this.sup==r.sup && this.sign==r.sign)//---------support------
		{
			if(this.node==r.node&&this.ct==r.ct)
			{
				return true;
			}		
		}
		return false;
	}
	
	/**
	 * Check if the report is positive or not
	 * @return true or false
	 */
	public boolean isPositive()
	{
		return sign;
	}
	
	/**
	 * Check if the report is negative or not
	 * @return true or false
	 */
	public boolean isNegative()
	{
		return !sign;
	}
	
	/**
	 * Check if the report has context or not
	 * @return true or false
	 */
	public boolean hasContext()
	{
		if(ct==null)
			return false;
		return true;
	}
	
	/**
	 * Set the node of the report
	 * @param n Node
	 */
	public void setNode(Node n)
	{
		node=n;
	}

}