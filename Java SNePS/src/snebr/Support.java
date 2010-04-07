package snebr;



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
	private Set<Proposition> originSet;
	/**
	 *  set of set of Proposition which is inconsistence with this Proposition 
	 */
	
	private Set<Set<Proposition>> RestrictionSet ;
	/**
	 *  check consistency of Proposition
	 */
	


	public Support(String supporttype, Set<Proposition> originSet,
			Set<Set<Proposition>> restrictionSet) {
		super();
		Supporttype = supporttype;
		this.originSet =  originSet;	
		RestrictionSet = restrictionSet;
	
	}
	
	
	public Support() {
		// TODO Auto-generated constructor stub
	}

	public String getSupporttype() {
		return Supporttype;
	}

	public void setSupporttype(String supporttype) {
		Supporttype = supporttype;
	}

	public Set<Proposition> getOrginSet() {
		return originSet;	
	}
	
	public void addToOrginSet(Proposition Proposition)
	{
		this.originSet.add(Proposition);
	}
	
	public boolean RemoveFromOrginSet(Proposition Proposition)
	{
		for (Iterator iterator = this.originSet.iterator(); iterator.hasNext();) {
			Proposition type = (Proposition) iterator.next();
			if(type.equals(Proposition)){
				originSet.remove(Proposition);
				return true;
			}
			
		}
		return false; 
	}

	public void setoriginSet(Set<Proposition> originSet) {
		this.originSet = originSet;
	}
	

	public Set<Set<Proposition>> getRestrictionSet() {
		return RestrictionSet;
	}

	public void setRestrictionSet(Set<Set<Proposition>> restrictionSet) {
		RestrictionSet = restrictionSet;
	}
   public void addToRestrictionSet(Set<Proposition> restrictionSet )
   { 
	   this.RestrictionSet.add(restrictionSet);
	   
   }

	
}