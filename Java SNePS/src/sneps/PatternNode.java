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
	 * A LinkedList of VariableNodes that are dominated by this PatternNode
	 */
	private LinkedList<VariableNode> freeVariables;

	/**
	 * @param identifier the name of the PatternNode
	 * @param cableSet the CableSet of this PatternNode
	 */
	public PatternNode(String identifier,CableSet cableSet)
	{
		super(identifier,cableSet);
		this.freeVariables = new LinkedList<VariableNode>();
		this.updateFreeVariables();
	}

	/**
	 * @return the list of free variables dominated by this PatternNode
	 */
	public LinkedList<VariableNode> getFreeVariables()
	{
		return freeVariables;
	}
	
	/**
	 * Updates the list of free variables while creating the pattern node
	 */
	public void updateFreeVariables()
	{
		CableSet cableSet = this.getCableSet();
		// looping over the cables
		for(int i=0;i<cableSet.size();i++)
		{
			// getting the i'th cable
			Cable cable = cableSet.getCable(i);
			NodeSet nodeSet = cable.getNodeSet();
			Relation relation = cable.getRelation();
			for(int j=0;j<nodeSet.size();j++)
			{
				// if the node is variable
				if(nodeSet.getNode(j).getClass().getSimpleName().equals("VariableNode")
						&& ! relation.isQuantifier())
					this.freeVariables.add((VariableNode) nodeSet.getNode(j));
				// if the node is pattern
				if(nodeSet.getNode(j).getClass().getSimpleName().equals("PatternNode"))
				{
					PatternNode patternNode = (PatternNode) nodeSet.getNode(j);
					LinkedList<VariableNode> patFreeVars = new LinkedList<VariableNode>();
					patFreeVars.addAll(patternNode.getFreeVariables());
					// looping over free variables in the pattern node
					for(int k=0;k<patFreeVars.size();k++)
					{
						VariableNode varNode = patFreeVars.get(k);
						// looping over cables
						for(int z=0;z<cableSet.size();z++)
						{
							Cable c = cableSet.getCable(z);
							if(c.getNodeSet().contains(varNode) 
									&& c.getRelation().isQuantifier())
								patFreeVars.remove(varNode);
						}
					}
					this.freeVariables.addAll(patFreeVars);
				}
			}
			
		}
		
	}
	
	/**
	 * compares the free variables dominated by this node and the specified node
	 * 
	 * @param node the pattern node that will be compared to this one
	 * @return true if it shares the same free variables with this given pattern node, and false otherwise
	 */
	public boolean haveSameFreeVariables(PatternNode patternNode)
	{
		LinkedList<VariableNode> l1 = patternNode.getFreeVariables();
		if(l1.size() != this.freeVariables.size())
			return false;
		for(int i=0;i<l1.size();i++)
		{
			if(! this.freeVariables.contains(l1.get(i)))
				return false;
		}
		return true;
	}
}