/**
 * @(#)RuleUseInfo.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/4/6
 */
package snip.ds;

import match.ds.Substitutions;

public class RuleUseInfo
{
	private Substitutions sub;
	private int pos;
	private int neg;
	private FlagNodeSet fns;
	
	/**
	 * Create new rule use info
	 * @param s substitutions list
	 * @param p number of positive substitutions
	 * @param n number of negative substitutions
	 * @param num number of antecedents
	 */
	public RuleUseInfo(Substitutions s,int p,int n,FlagNodeSet f)
	{
		sub=s;
		pos=p;
		neg=n;
		fns=f;
	}
	
	/**
	 * Return the substitutions list
	 * @return Substitutions
	 */
	public Substitutions getSub()
	{
		return sub;
	}
	
	/**
	 * Return the number of positive substitutions
	 * @return int
	 */
	public int getPosCount()
	{
		return pos;
	}
	
	/**
	 * Return the number of negative substitutions
	 * @return int
	 */
	public int getNegCount()
	{
		return neg;
	}
	
	/**
	 * Returns a flag node set with which antecedents are negative only
	 * @return FlagNodeSet
	 */
	public  FlagNodeSet getNegSubs()
	{
		FlagNodeSet res =new FlagNodeSet();
		for(int i=0;i<fns.cardinality();i++)
		{
			if(fns.getFlagNode(i).getFlag()==2)
				res.putIn(fns.getFlagNode(i));
		}
		return res;
	}
	
	/**
	 * Returns a flag node set with which antecedents are positive only
	 * @return FlagNodeSet
	 */
	public FlagNodeSet getPosSubs()
	{
		FlagNodeSet res =new FlagNodeSet();
		for(int i=0;i<fns.cardinality();i++)
		{
			if(fns.getFlagNode(i).getFlag()==1)
				res.putIn(fns.getFlagNode(i));
		}
		return res;
	}
	
	/**
	 * Check if this and r have no binding conflicts
	 * @param r rule use info
	 * @return true or false
	 */
	public boolean isVarsCompatible(RuleUseInfo r)
	{
		return sub.isCompatible(r.sub);
	}
	
	/**
	 * Check if this and r are disjoint
	 * @param r rule use info
	 * @return true or false
	 */
	public boolean isDisjoint(RuleUseInfo r)
	{
		for(int i=0;i<fns.cardinality();i++)
		{
			if(r.fns.getFlagNode(i).getFlag()!=0
					&&this.fns.getFlagNode(i).getFlag()!=0)
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Return the flag node set of the rule use info
	 * @return FlagNodeSet
	 */
	public FlagNodeSet getFlagNodeSet()
	{
		return fns;
	}
	
	/**
	 * combine rui with this rule use info
	 * @param rui RuleUseInfo
	 * @return RuleUseInfo
	 */
	public RuleUseInfo combine(RuleUseInfo rui)
	{
		if(this.isDisjoint(rui)&&this.isVarsCompatible(rui))
		{
			return new RuleUseInfo(this.getSub().union(rui.getSub())
					,this.pos+rui.pos,this.neg+rui.neg,this.fns.union(rui.fns));
		}
		return null;
	}
}