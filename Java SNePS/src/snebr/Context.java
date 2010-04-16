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
public class Context implements Cloneable {
private PropositionSet hypSet ;
private HashSet<PropositionSet> RestricitonSet ;
private HashSet<String> names;


public Context()
{
 this.hypSet =  new PropositionSet();
 this.names = new HashSet<String>();
 this.RestricitonSet = new HashSet<PropositionSet>();
 
}


public Context(PropositionSet hypSet, HashSet<String> names) {
	super();
	this.hypSet = hypSet;
	this.names = names;
}
public Context(PropositionSet hypSet, String name) {
	super();
	this.hypSet = hypSet;
	this.names = new HashSet<String>();
	names.add(name);
}



public HashSet<PropositionSet> getRestricitonSet() {
	return RestricitonSet;
}

public void setRestricitonSet(HashSet<PropositionSet> restricitonSet) {
	RestricitonSet = restricitonSet;
}

public HashSet<String> getNames() {
	return names;
}

public void setNames(HashSet<String> names) {
	this.names = names;
}
 

public PropositionSet getHypSet() {
	return hypSet;
}
public void setHypSet(PropositionSet hypSet) {
	this.hypSet = hypSet;
}
public HashSet<String> getIdentifier() {
	return names;
}
public void setIdentifier(HashSet<String> names) {
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
public Object clone() throws CloneNotSupportedException
{
	Context copy = (Context) super.clone();
	copy.hypSet =  (PropositionSet) hypSet.clone();
	copy.names = (HashSet<String>) names.clone();
	copy.RestricitonSet = (HashSet<PropositionSet>) RestricitonSet.clone();
	return copy;
}




}



