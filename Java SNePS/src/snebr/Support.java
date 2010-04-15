package snebr;



import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import sneps.Node;
import sneps.NodeSet;
/**
 *  contain  support propositions for proposition 

 * @author Abdelrahman Elamin 
 */
public  class Support {
	/**
	support type which if the proposition is hypothesis or derived proposition or special derived proposition 
	 */
	private String Supporttype;
	/**
	 * Set contain hypothesis used in derivation of proposition if proposition is hypothesis origin set equal proposition itself
	 */
	private PropositionSet originSet;
	/**
	 *  set of set of Proposition which is inconsistence with this Proposition 
	 */
	
	private Set<PropositionSet> RestrictionSet ;
	/**
	 *  check consistency of Proposition
	 */
	


	public Support(String supporttype, PropositionSet originSet,
			Set<PropositionSet> restrictionSet) {
		super();
		Supporttype = supporttype;
		this.originSet =  originSet;	
		RestrictionSet = restrictionSet;
	
	}
	

	
	public Support() {
		// TODO Auto-generated constructor stub
		this.originSet = new PropositionSet();
		this .RestrictionSet	 = new HashSet<PropositionSet>();
	}

	public String getSupporttype() {
		return Supporttype;
	}

	public void setSupporttype(String supporttype) {
		Supporttype = supporttype;
	}

	public PropositionSet getOrginSet() {
		return originSet;	
	}
	
	public void addToOriginSet(Proposition Proposition)
	{
		this.originSet.addProposition(Proposition);
	}
	
	public boolean RemoveFromOrginSet(Proposition Proposition)
	{
	  return originSet.removeProposition(Proposition);
	}

	public void setoriginSet(PropositionSet originSet) {
		this.originSet = originSet;
	}
	
	public Set<PropositionSet> getRestrictionSet() {
		return RestrictionSet;
	}
     
	public void setRestrictionSet(Set<PropositionSet> restrictionSet) {
		RestrictionSet = restrictionSet;
	}

	public void addToRestrictionSet(PropositionSet restrictionSet )
   { 
	   this.RestrictionSet.add(restrictionSet);
	   
   }
 
	public int getRestrictionSetsSize()
  {
		return RestrictionSet.size();
  }
	public void setHyp(Proposition proposition)
	{
		Supporttype = "hyp";
		originSet.addProposition(proposition);
		
	}
	
	
	
	
	
	
	
	
   public void printSupportTag(){
	   System.out.print(this.Supporttype);
   }
   public void printOriginSet()
   { System.out.print("{");
   
	 originSet.print();
	 System.out.print("}"); 	 	
	 
	 
   }
   public void printRestrictionSets()
   {
      System.out.print("{");
      int i = 1;
	   for (Iterator iterator = this.RestrictionSet.iterator(); iterator.hasNext();) {
		PropositionSet type = (PropositionSet) iterator.next();
			type.print();	
			if(i==this.getRestrictionSetsSize())
			 System.out.print(" ");
			else
				System.out.print(",");
			i++;
		}
		
	   System.out.print("}");
	}
	 
   public void printSupport()
   
   {
	   System.out.print("{");
	   this.printSupportTag();
	   System.out.print(",");
	   this.printOriginSet();
	   System.out.print(",");
	   this.printRestrictionSets();
	   System.out.print("}");
	
   }
   }

	
