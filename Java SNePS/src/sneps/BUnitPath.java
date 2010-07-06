package sneps;

import java.util.LinkedList;

import snebr.Context;

/**
 * The BUnitPath (backward unit path) class represents the reverse of a path of only one relation. 
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class BUnitPath extends Path
{

	/**
	 * the relation that identifies this path
	 */
	private Relation relation;

	/**
	 * @param relation a Relation for the BUnitPath
	 */
	public BUnitPath(Relation relation)
	{
		this.relation = relation;
	}
	
	/**
	 * @return the relation of this path
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
		UpCableSet ucs = node.getUpCableSet();
		UpCable uc = ucs.getUpCable(this.relation.getName());
		if(uc != null)
		{
			NodeSet ns = uc.getNodeSet();
			for(int i=0;i<ns.size();i++)
			{
				Node n = ns.getNode(i);
				PathTrace t = trace.clone();
				t.compose(new BUnitPath(relation));
				Object[] o = new Object[2];
				o[0] = n;
				o[1] = t;
				result.add(o);
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
		return new FUnitPath(relation).follow(node,trace,context);
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#clone()
	 */
	@Override
	public Path clone()
	{
		return new BUnitPath(this.relation);
	}
	
	/* (non-Javadoc)
	 * @see sneps.Path#isEqual(sneps.Path)
	 */
	@Override
	public boolean isEqualTo(Path path)
	{
		if(! path.getClass().getSimpleName().equals("BUnitPath"))
			return false;
		if(! ((BUnitPath)path).getRelation().equals(this.relation))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return this.relation.toString() +"-";  
	}

}