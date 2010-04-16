/**
 * 
 */
package snebr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Abdelrahman Elamin
 *
 */
public class PropositionSet implements Cloneable {
private HashSet<Proposition>  propositionSet;
public PropositionSet(HashSet<Proposition> propositionSet) {
	super();
	this.propositionSet = propositionSet;
}
public PropositionSet() {
	super();
	this.propositionSet = new HashSet<Proposition>();
}
public Set<Proposition> getPropositionSet() {
	return propositionSet;
}
public void setPropositionSet(HashSet<Proposition> propositionSet) {
	this.propositionSet = propositionSet;
}
public void addProposition(Proposition newProposition)
{
	this.propositionSet.add(newProposition);
}
public boolean removeProposition(Proposition proposition)
{
	return this.propositionSet.remove(proposition);
}
public boolean  isEmpty()
{
	if(propositionSet.isEmpty())
		return true;
	else
		return false;
}
public void print()
{  
	String[] out = new  String[this.propositionSet.size()]; 
	int i = 0;
	for (Iterator iterator = this.propositionSet.iterator(); iterator.hasNext();) {
		Proposition type = (Proposition) iterator.next();
		out[i] = type.getNode().getIdentifier();
		i++;
		
	} 
	System.out.print(Arrays.toString(out));
}
public Object clone() throws CloneNotSupportedException
{
	PropositionSet copy = (PropositionSet) super.clone();
	copy.propositionSet = (HashSet<Proposition>) propositionSet.clone();
	return copy;
}
 
}
