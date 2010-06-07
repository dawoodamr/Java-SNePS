package sneps;

import java.util.Hashtable;
import java.util.LinkedList;

import snebr.Context;
import snebr.Support;

/**
 * The FUnitPath (forward unit path) class is a path which is only a relation.
 * 
 * @author Amr Khaled Dawood
 */
public class FUnitPath extends Path
{

	/**
	 * the name of the relation that identifies this unit path
	 */
	private String relationName;
	
	/**
	 * @param relationName a String representing the name of the relation that identifies 
	 * this unit path
	 */
	public FUnitPath(String relationName)
	{
		this.relationName = relationName;
	}

	/**
	 * @return a String representing the name of the relation that this FUnitPath is defined for
	 */
	public String getRelationName()
	{
		return relationName;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node, java.util.LinkedList, snebr.Context)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> follow(Node node,LinkedList<Support> supports,Context context)
	{
		Hashtable<Node,LinkedList<Support>> h = new Hashtable<Node,LinkedList<Support>>();
		if(node.getClass().getSuperclass().getSimpleName().equals("MolecularNode"))
		{
			MolecularNode mNode = (MolecularNode) node;
			CableSet cableSet = mNode.getCableSet();
			Cable cable = cableSet.getCable(relationName);
			if(cable != null)
			{
				for(int i=0;i<cable.getNodeSet().size();i++)
				{
						h.put(cable.getNodeSet().getNode(i),supports);
				}
			}
		}
		return h;
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node, java.util.LinkedList, snebr.Context)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> followConverse(Node node,LinkedList<Support> supports,Context context)
	{
		Hashtable<Node,LinkedList<Support>> h = new Hashtable<Node,LinkedList<Support>>();
		UpCableSet upCableSet = node.getUpCableSet();
		UpCable upCable = upCableSet.getUpCable(relationName);
		if(upCable != null)
		{
			for(int i=0;i<upCable.getNodeSet().size();i++)
			{
					h.put(upCable.getNodeSet().getNode(i),supports);
			}
		}
		return h;
	}

}