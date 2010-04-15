package snebr;

import java.util.Iterator;
import java.util.Set;

public class RestricitonSet {
	private Set<PropositionSet> RestrtictionSet;
	public RestricitonSet(Set<PropositionSet> restrtictionSet) {
		super();
		RestrtictionSet = restrtictionSet;
	}

	public RestricitonSet() {
		super();
	}
	


	public Set<PropositionSet> getRestrtictionSet() {
		return RestrtictionSet;
	}

	public void setRestrtictionSet(Set<PropositionSet> restrtictionSet) {
		RestrtictionSet = restrtictionSet;
	}

	public boolean addSet(PropositionSet prSet )
	{
		return RestrtictionSet.add(prSet);
	}
	public boolean isMember(PropositionSet pSet)
	{
		return RestrtictionSet.contains(pSet);
	}
	public boolean RemoveSet(PropositionSet pSet)
	{
		return RestrtictionSet.remove(pSet);
	}
	public boolean isEmpty()
	{
		return RestrtictionSet.isEmpty();
		
	}
	public boolean addToSet(PropositionSet pSet,Proposition proposition)
	{
		
	
	  for (Iterator iterator = RestrtictionSet.iterator(); iterator.hasNext();) {
		  PropositionSet type = (PropositionSet) iterator.next();
		if (pSet.equals(type)) {
		 type.addProposition(proposition); 
		 PropositionSet addedSet = type;
			this.RemoveSet(type);
			this.addSet(addedSet);
			return true;
		}
	}
		return false;
	}
	public int size()
	{
		return this.RestrtictionSet.size();
	}
}
