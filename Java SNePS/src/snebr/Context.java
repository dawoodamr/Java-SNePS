package snebr;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import sneps.CaseFrame;
import sneps.Network;
import sneps.Node;
import sneps.NodeSet;
import sneps.Relation;

/**
 * 
 *context is class contain hypthesis  of network is defines by set of node
 * called the hypset and context identifier 
 *
 *@author Abdelrahman Elamin
 */
public class Context {
private PropositionSet hypSet ;
private Set<PropositionSet> RestricitonSet ;
private Set<String> names;


public Context()
{
 this.hypSet =  new PropositionSet();
 this.names = new HashSet<String>();
 this.RestricitonSet = new HashSet<PropositionSet>();
 
}

public Set<PropositionSet> getRestricitonSet() {
	return RestricitonSet;
}

public void setRestricitonSet(Set<PropositionSet> restricitonSet) {
	RestricitonSet = restricitonSet;
}

public Set<String> getNames() {
	return names;
}

public void setNames(Set<String> names) {
	this.names = names;
}
 

public PropositionSet getHypSet() {
	return hypSet;
}
public void setHypSet(PropositionSet hypSet) {
	this.hypSet = hypSet;
}
public Set<String> getIdentifier() {
	return names;
}
public void setIdentifier(Set<String> names) {
	this.names = names;
}

public void addToContext(Proposition  proposition)
{
	this.hypSet.addProposition(proposition);
}
public boolean RemoveFromContext(Proposition proposition)
{
	return this.hypSet.removeProposition(proposition);
}
public void addName(String string)
{
names.add(string);	
}
public boolean RemoveName(String string)
{
	return names.remove(string);
}
public void print()
{
	System.out.println();
	System.out.print("{");
	System.out.print(Arrays.toString(this.names.toArray())+",");
	
	this.hypSet.print();
	System.out.print("}");
	
	
	
	
	
}




}



