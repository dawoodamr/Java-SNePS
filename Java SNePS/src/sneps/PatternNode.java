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
	 * this method is used to update the list of free variables while creating the pattern node
	 */
	public void updateFreeVariables()
	{
		CableSet cableSet = this.getCableSet();
		// looping over the cables
		for(int i=0;i<cableSet.getCables().size();i++)
		{
			// getting the i'th cable
			Cable cable = cableSet.getCables().get(i);
			NodeSet nodeSet = cable.getNodeSet();
			Relation relation = cable.getRelation();
			for(int j=0;j<nodeSet.getNodes().size();j++)
			{
				// if the node is variable
				if(nodeSet.getNodes().get(j).getClass().getSimpleName().equals("VariableNode")
						&& ! relation.isQuantifier())
					this.freeVariables.add((VariableNode) nodeSet.getNodes().get(j));
				// if the node is pattern
				if(nodeSet.getNodes().get(j).getClass().getSimpleName().equals("PatternNode"))
				{
					PatternNode patternNode = (PatternNode) nodeSet.getNodes().get(j);
					LinkedList<VariableNode> patFreeVars = new LinkedList<VariableNode>();
					patFreeVars.addAll(patternNode.getFreeVariables());
					// looping over free variables in the pattern node
					for(int k=0;k<patFreeVars.size();k++)
					{
						VariableNode varNode = patFreeVars.get(k);
						// looping over cables
						for(int z=0;z<cableSet.getCables().size();z++)
						{
							Cable c = cableSet.getCables().get(z);
							if(c.getNodeSet().getNodes().contains(varNode) 
									&& c.getRelation().isQuantifier())
								patFreeVars.remove(varNode);
						}
					}
					this.freeVariables.addAll(patFreeVars);
				}
			}
			
		}
		
	}
	
}
