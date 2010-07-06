package sneps;

import java.util.LinkedList;

/**
 * A PatternNode is a MolecularNode that dominates free variables. A molecular node dominates 
 * a free variable if there is at least one down path from this molecular node to that variable
 * that does not contain any nodes that quantifies that variable.
 * It contains a list of those free variables.
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class PatternNode extends MolecularNode
{
	
	/**
	 * A LinkedList of VariableNodes that are dominated by this PatternNode
	 */
	private LinkedList<VariableNode> freeVariables;

	/**
	 * @param identifier a String representing the name of the PatternNode
	 * @param cableSet a CableSet of this PatternNode
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
	 * Updates the list of free variables
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
	 * @param node a pattern node that will be compared to this one
	 * @return true if this pattern node dominates the same free variables as the specified 
	 * pattern node, and false otherwise
	 */
	public boolean hasSameFreeVariablesAs(PatternNode patternNode)
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