package sneps;

import java.util.LinkedList;

/**
 * A PatternNode is a MolecularNode that dominates free variables, that is if there are 
 * VariableNodes in the subtree that this Node is considered to be its root, then at least
 * one of these VariableNodes is not quantified by any quantifier Relation(forall,...etc)
 * in this subtree.
 * It contains a list of those free variables.
 * 
 * @author Amr Khaled Dawood
 */
public class PatternNode extends MolecularNode
{
	
	/**
	 * A list of VariableNodes that are dominated by this PatternNode and are not quantified
	 */
	private LinkedList<VariableNode> freeVariables;

	/**
	 * This constructor creates a new PatternNode with the identifier and the CableSet 
	 * specified and initializes the list of VariableNodes as an empty list.
	 * 
	 * @param identifier the name of the PatternNode
	 * @param cableSet the CableSet of this PatternNode
	 */
	public PatternNode(String identifier,CableSet cableSet)
	{
		super(identifier,cableSet);
		this.freeVariables = new LinkedList<VariableNode>();
	}

	/**
	 * @return the list of free variables dominated by this PatternNode
	 */
	public LinkedList<VariableNode> getFreeVariables()
	{
		return freeVariables;
	}
	
}
