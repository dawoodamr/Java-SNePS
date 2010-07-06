package sneps;

import java.util.LinkedList;

import snebr.Context;

/**
 * The FUnitPath (forward unit path) class is a path which is only a relation.
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class FUnitPath extends Path
{

	/**
	 * the relation that identifies this unit path
	 */
	private Relation relation;
	
	/**
	 * @param relation a Relation that identifies the unit path
	 */
	public FUnitPath(Relation relation)
	{
		this.relation = relation;
	}

	/**
	 * @return the relation of this FUnitPath
	 */
	public Relation getRelation()
	{
		return relation;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node, sneps.PathTrace, snebr.Context)
	 */
	@Override
	public LinkedList<Object[]> follow(Node node,PathTrace trace,Context context)
	{
		LinkedList<Object[]> result = new LinkedList<Object[]>();
		if(node.getClass().getSuperclass().getSimpleName().equals("MolecularNode"))
		{
			MolecularNode mNode = (MolecularNode) node;
			CableSet cs = mNode.getCableSet();
			Cable c = cs.getCable(this.relation.getName());
			{
				if(c != null)
				{
					NodeSet ns = c.getNodeSet();
					for(int i=0;i<ns.size();i++)
					{
						Node n = ns.getNode(i);
						PathTrace t = trace.clone();
						t.compose(new FUnitPath(relation));
						Object[] o = new Object[2];
						o[0] = n;
						o[1] = t;
						result.add(o);
					}
				}
			}
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node, sneps.PathTrace, snebr.Context)
	 */
	@Override
	public LinkedList<Object[]> followConverse(Node node,PathTrace trace,Context context)
	{
		return new BUnitPath(relation).follow(node,trace,context);
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#clone()
	 */
	@Override
	public Path clone()
	{
		return new FUnitPath(this.relation);
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#isEqual(sneps.Path)
	 */
	@Override
	public boolean isEqualTo(Path path)
	{
		if(! path.getClass().getSimpleName().equals("FUnitPath"))
			return false;
		if(! ((FUnitPath)path).getRelation().equals(this.relation))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return this.relation.toString();
	}

}