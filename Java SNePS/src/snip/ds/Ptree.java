/**
 * @(#)Ptree.java
 *
 *
 * @author Mohamed Karam Gabr
 * @version 1.00 2010/4/25
 */

package snip.ds;

import java.util.LinkedList;
import java.util.Vector;

import sneps.PatternNode;
import sneps.VariableNode;

public class Ptree
{
	/*private*/ TreeNode root;
	
	/**
	 * Create a new Ptree
	 */
	public Ptree()
	{
		root=null;
	}
	
	/**
	 * Build the ptree from nodes ids
	 * @param nodes
	 */
	public void buildTree(int [] nodes)
	{
		Vector<TreeNode> tn=new Vector<TreeNode>();
		for(int i=0;i<nodes.length;i++)
		{
			int []x=new int [1];
			x[0]=nodes[i];
			tn.add(new TreeNode(x,null));
		}
		buildTree(tn,true);
	}
	
	/**
	 * Check if the node lists share variables or not
	 * @param nodes1 nodes ids
	 * @param nodes2 nodes ids
	 * @return true or false
	 */
	public boolean shareVars(int[] nodes1,int[] nodes2)
	{
		for(int i=0;i<nodes1.length;i++)
		{
			for(int j=0;j<nodes2.length;j++)
			{
				if(nodes1[i]==nodes2[j])
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Union x1 and x2 and return the result
	 * @param x1 int[]
	 * @param x2 int[]
	 * @return int[]
	 */
	public int[] union(int [] x1,int []x2)
	{
		int [] res=new int [x1.length+x2.length];
		for(int i=0;i<res.length;i++)
		{
			if(i<x1.length)
				res[i]=x1[i];
			else
				res[i]=x2[i-x1.length];
		}
		return res;
	}
	
	/**
	 * Create the direction array
	 * @param size total number of nodes
	 * @param t number of nodes in the left side
	 * @return boolean[]
	 */
	public boolean[] makeDir(int size,int t)
	{
		boolean[] res=new boolean[size];
		for(int i=0;i<t;i++)
		{
			res[i]=true;
		}
		return res;
	}
	
	/**
	 * Build the tree with the TreeNode set tn, sharing is true when the patterns 
	 * may share variables, and when it is found that patterns don't share variables 
	 * sharing will be false every time
	 * @param tn TreeNode set
	 * @param sharing true or false
	 */
	private void buildTree(Vector<TreeNode> tn,boolean sharing)
	{
		if(tn.size()==2)
		{
			int []v1=tn.get(0).getVars();
			int []v2=tn.get(1).getVars();
			int []v=union(v1, v2);
			boolean[] d=makeDir(v.length, v1.length);
			root=new TreeNode(v,d);
			root.insertLeft(tn.get(0));
			root.insertRight(tn.get(1));
			return;
		}
		Vector<TreeNode> tntemp=new Vector<TreeNode>();
		int combined=0;
		boolean s=true;
		if(sharing)
		{
			for(int i=0;i<tn.size();i+=2)
			{
				if(i!=tn.size()-1)
				{
					if(shareVars(tn.get(i).getVars(), tn.get(i+1).getVars()))
					{
						int []v1=tn.get(i).getVars();
						int []v2=tn.get(i+1).getVars();
						int []v=union(v1, v2);
						boolean[] d=makeDir(v.length, v1.length);
						TreeNode tmp=new TreeNode(v,d);
						tmp.insertLeft(tn.get(i));
						tmp.insertRight(tn.get(i+1));
						tntemp.add(tmp);
						combined++;
					}
				}
				else
				{
					tntemp.add(tn.get(i));
					i--;
				}
			}
		}
		if(combined==0)
		{
			s=false;
			tntemp=new Vector<TreeNode>();
			for(int i=0;i<tn.size();i+=2)
			{
				if(i!=tn.size()-1)
				{
					int []v1=tn.get(i).getVars();
					int []v2=tn.get(i+1).getVars();
					int []v=union(v1, v2);
					boolean[] d=makeDir(v.length, v1.length);
					TreeNode tmp=new TreeNode(v,d);
					tmp.insertLeft(tn.get(i));
					tmp.insertRight(tn.get(i+1));
					tntemp.add(tmp);
				}
				else
				{
					tntemp.add(tn.get(i));
				}
			}
		}
		if(tntemp.size()==2)
		{
			int []v1=tntemp.get(0).getVars();
			int []v2=tntemp.get(1).getVars();
			int []v=union(v1, v2);
			boolean[] d=makeDir(v.length, v1.length);
			root=new TreeNode(v,d);
			root.insertLeft(tntemp.get(0));
			root.insertRight(tntemp.get(1));
		}
		else
		{
			buildTree(tntemp,s);
		}
	}
	
	/**
	 * Insert a new rule use info in the tree and return the rule use info set result
	 * from combining it with rule use infos in the way up
	 * @param rui RuleUseInfo
	 * @param pattern the pattern this rule use info is about 
	 * @return RuleUseInfoSet
	 */
	public RuleUseInfoSet insert(RuleUseInfo rui)
	{
		return insertInTree(rui,rui.getFlagNodeSet().getFlagNode(0).getNode().getId()
				,root);
	}
	
	/**
	 * Insert rui of the pattern pat in the TreeNode tn or in it's sub tree, and 
	 * return the rule use info set result from combining it with rule use infos in 
	 * the way up
	 * @param rui RuleUseInfo
	 * @param pat int
	 * @param tn TreeNode
	 * @return RuleUseInfoSet
	 */
	private RuleUseInfoSet insertInTree(RuleUseInfo rui,int pat,TreeNode tn)
	{
		int []vars=tn.getVars();
		if(vars.length==1)
		{
			tn.insertRUI(rui);
			return combine(rui,tn.getParent(),pat);
		}
		else
		{
			boolean d=tn.getPatDir(pat);
			if(d)
				return insertInTree(rui,pat,tn.getLeft());
			return insertInTree(rui,pat,tn.getRight());
		}
	}
	
	/**
	 * Combine rui with the rule use info set in tn. Pattern is the pattern this 
	 * rui is from.
	 * @param rui RuleUseInfo
	 * @param tn TreeNode
	 * @param pattern int
	 * @return RuleUseInfoSet
	 */
	private RuleUseInfoSet combine(RuleUseInfo rui,TreeNode tn,int pattern)
	{
		RuleUseInfoSet s=new RuleUseInfoSet();
		s.putIn(rui);
		return combine(s,tn,pattern);
	}
	
	/**
	 * Combine ruis with the rule use info set in tn. Pattern is the pattern this 
	 * ruis is from.
	 * @param ruis RuleUseInfoSet
	 * @param tn TreeNode
	 * @param pattern int
	 * @return RuleUseInfoSet
	 */
	private RuleUseInfoSet combine(RuleUseInfoSet ruis,TreeNode tn,int pattern)
	{
		if(tn==null)
		{
			return ruis;
		}
		else
		{
			boolean d =tn.getPatDir(pattern);
			RuleUseInfoSet temp;
			if(d)
			{
				temp=ruis.combine(tn.getRight().getRUIS());
			}
			else
			{
				temp=ruis.combine(tn.getLeft().getRUIS());
			}
			if(temp.isNew())
				return null;
			return combine(temp,tn.getParent(),pattern);
		}
	}
	
	/**
	 * Return the patvar given the pattern nodes set. For example, for antecedents 
	 * P(x,y), Q(y,z), and R(x,z),the input is (P Q R), and the output is 
	 * ((P x y) (Q y z) (R x z)).
	 * @param pns PatternNode []
	 * @return Vector<Vector<Integer>>
	 */
	public Vector<Vector<Integer>> getPatVar(PatternNode [] pns)
	{
		Vector<Vector<Integer>> res=new Vector<Vector<Integer>>();
		for(int i=0;i<pns.length;i++)
		{
			Vector<Integer> temp=new Vector<Integer>();
			temp.add(pns[i].getId());
			LinkedList<VariableNode> vars=pns[i].getFreeVariables();
			for(int j=0;j<vars.size();j++)
			{
				temp.add(vars.get(j).getId());
			}
			res.add(temp);
		}
		return res;
	}
	
	/**
	 * Return the varpat given the patvar. For example, ((P x y) (Q y z) (R x z)) 
	 * is converted to ((x P R) (y P Q) (z Q R)).
	 * @param patvar Vector<Vector<Integer>>
	 * @return Vector<Vector<Integer>>
	 */
	public Vector<Vector<Integer>> getVarPat(Vector<Vector<Integer>> patvar)
	{
		Vector<Vector<Integer>> res=new Vector<Vector<Integer>>();
		Vector<Integer> pats=new Vector<Integer>();
		Vector<Integer> vars=new Vector<Integer>();
		for(int i=0;i<patvar.size();i++)
		{
			pats.add(patvar.get(i).get(0));
			for(int j=1;j<patvar.get(i).size();j++)
			{
				vars.add(patvar.get(i).get(j));
			}
		}
		for(int i=0;i<vars.size();i++)
		{
			for(int j=i+1;j<vars.size();j++)
			{
				if(vars.get(i)==vars.get(j))
				{
					vars.remove(j);
					j--;
				}
			}
		}
		for(int i=0;i<vars.size();i++)
		{
			Vector<Integer> temp=new Vector<Integer>();
			int var=vars.get(i);
			temp.add(var);
			for(int j=0;j<patvar.size();j++)
			{
				for(int n=1;n<patvar.get(j).size();n++)
				{
					if(var== patvar.get(j).get(n))
					{
						temp.add(pats.get(j));
						break;
					}
				}
			}
			res.add(temp);
		}
		return res;
	}
	
	/**
	 * Return the pattern sequence given the patvar and varpat. For example, a 
	 * 'varpat-list' ((x P R) (y P Q) (z Q R))produces a 'patseq' (P R Q).
	 * @param pv Vector<Vector<Integer>>
	 * @param vp Vector<Vector<Integer>>
	 * @return int []
	 */
	public int[]getPatSeq(Vector<Vector<Integer>> pv,Vector<Vector<Integer>> vp)
	{
		Vector<Integer> vars=new Vector<Integer>();
		Vector<Integer> pats=new Vector<Integer>();
		for(int i=0;i<vp.size();i++)
		{
			vars.add(vp.get(i).get(0));
		}
		Vector<Integer> varuni=new Vector<Integer>();
		varuni.add(vars.get(0));
		int presize=0;
		int patinpats=0;
		boolean varsdone=false;
		for(int p=0;!vars.isEmpty();p++)
		{
			int var=-1;
			if(varuni.size()!=presize)
				var=varuni.get(p);
			else
				var=vars.get(0);
			int varpos;
			for(varpos=0;varpos<vp.size();varpos++)
			{
				if(vp.get(varpos).get(0)==var)
				{
					break;
				}
			}
			for(int i=1;i<vp.get(varpos).size();i++)
			{
				pats.add(vp.get(varpos).get(i));
			}
			for(int i=0;i<pats.size();i++)
			{
				for(int j=i+1;j<pats.size();j++)
				{
					if(pats.get(i)==pats.get(j))
					{
						pats.remove(j);
						j--;
					}
				}
			}
			presize=varuni.size();
			if(!varsdone)
			{
				for(;patinpats<pats.size();patinpats++)
				{
					int pat=pats.get(patinpats);
					int patpos;
					for(patpos=0;patpos<pv.size();patpos++)
					{
						if(pv.get(patpos).get(0)==pat)
							break;
					}
					for(int j=1;j<pv.get(patpos).size();j++)
					{
						varuni.add(pv.get(patpos).get(j));
					}
				}
				for(int i=0;i<varuni.size();i++)
				{
					for(int j=i+1;j<varuni.size();j++)
					{
						if(varuni.get(i)==varuni.get(j))
						{
							varuni.remove(j);
							j--;
						}
					}
				}
			}
			vars.removeElement(var);
			if(varuni.size()==vp.size())
				varsdone=true;
			if(pats.size()==vp.size())
				break;
		}
		int[] res=new int[pats.size()];
		for(int i=0;i<res.length;i++)
		{
			res[i]=pats.get(i);
		}
		return res;
	}
}