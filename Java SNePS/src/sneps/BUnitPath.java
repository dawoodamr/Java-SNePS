package sneps;

import java.util.Hashtable;
import java.util.LinkedList;

import snebr.Context;
import snebr.Support;

/**
 * the BUnitPath (backward unit path) class is the reverse of a path of only one relation. 
 * 
 * @author Amr Khaled Dawood
 */
public class BUnitPath extends Path
{

	/**
	 * the name of the relation that identifies this path
	 */
	private String relationName;

	/**
	 * @param relationName the name of the relation that specifies this path
	 */
	public BUnitPath(String relationName)
	{
		this.relationName = relationName;
	}
	
	/**
	 * @return the name of the relation that this BUnitPath is defined for
	 */
	public String getRelationName()
	{
		return relationName;
	}

	/* (non-Javadoc)
	 * @see ds.Path#follow(ds.Node)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> follow(Node node,LinkedList<Support> supports,Context context)
	{
		Hashtable<Node,LinkedList<Support>> h = new Hashtable<Node,LinkedList<Support>>();
		UpCableSet upCableSet = node.getUpCableSet();
		UpCable upCable = upCableSet.getUpCable(relationName);
		if(upCable != null)
		{
			for(int i=0;i<upCable.getNodeSet().getNodes().size();i++)
			{
					h.put(upCable.getNodeSet().getNodes().get(i),supports);
			}
		}
		
		return h;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node)
	 */
	@Override
	public Hashtable<Node,LinkedList<Support>> followConverse(Node node,LinkedList<Support> supports,Context context)
	{
			Hashtable<Node,LinkedList<Support>> h = new Hashtable<Node,LinkedList<Support>>();
		if(node.getClass().getSuperclass().getSimpleName().equals("MolecularNode"))
		{
			MolecularNode mNode = (MolecularNode) node;
			CableSet cableSet = mNode.getCableSet();
			Cable cable = cableSet.getCable(relationName);
			if(cable != null)
			{
				for(int i=0;i<cable.getNodeSet().getNodes().size();i++)
				{
						h.put(cable.getNodeSet().getNodes().get(i),supports);
				}
			}
		}
		return h;
	}

}