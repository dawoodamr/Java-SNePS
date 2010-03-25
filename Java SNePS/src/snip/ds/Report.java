/**
 * @(#)Report.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/3/21
 */
package snip.ds;

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
	private Object ct;//------------context---------
	
	/**
	 * Create new report with the given arguments
	 * @param substitution Substitution
	 * @param support support
	 * @param sign positive of negative
	 * @param signature signature
	 * @param node node
	 * @param context context
	 */
	public Report(Substitutions substitution,Object support,boolean sign,
			Node signature,Node node,Object context )
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
	public Object getSignature()
	{
		return sig;
	}
	
	/**
	 * return the node of the report
	 * @return node
	 */
	public Object getNode()
	{
		return node;
	}
	
	/**
	 * return the context of the report
	 * @return context
	 */
	public Object getContext()
	{
		return ct;
	}
	
	/**
	 * Add the given binding to the substitutions list of the report
	 * @param m mbind
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
			if(this.node==null&&r.node==null&&this.ct==null&&r.ct==null)
			{
				return true;
			}
			else if(this.node==r.node&&this.ct==r.ct)//----------node context---------
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

}