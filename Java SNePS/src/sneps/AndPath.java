package sneps;

import java.util.LinkedList;

import snebr.Context;

/**
 * an AndPath is a path that contains a list of paths. an AndPath may lead us to a node 
 * if and only if all the paths it contains will lead us to that node
 * 
 * @author Amr Khaled Dawood
 */
public class AndPath extends Path
{
	
	/**
	 * the list of paths for this AndPath
	 */
	private LinkedList<Path> paths;

	/**
	 * @param paths the list of paths of this AndPath
	 */
	public AndPath(LinkedList<Path> paths)
	{
		this.paths = paths;
	}

	/**
	 * @return the list of Paths that are used to create this AndPath
	 */
	public LinkedList<Path> getPaths()
	{
		return paths;
	}

	/* (non-Javadoc)
	 * @see sneps.Path#follow(sneps.Node)
	 */
	@Override
	public NodeSet follow(Node node,Context context)
	{
		LinkedList<Path> rest = new LinkedList<Path>();
		rest.addAll(this.paths);
		Path p = rest.removeFirst();
		AndPath andPath = new AndPath(rest);
		return intersection(p.follow(node,context),andPath.follow(node,context));
	}

	/* (non-Javadoc)
	 * @see sneps.Path#followConverse(sneps.Node)
	 */
	@Override
	public NodeSet followConverse(Node node,Context context)
	{
		LinkedList<Path> rest = new LinkedList<Path>();
		rest.addAll(this.paths);
		Path p = rest.removeLast();
		AndPath andPath = new AndPath(rest);
		return intersection(p.follow(node,context),andPath.follow(node,context));
	}
	
	/**
	 * this method gets the intersection between two node sets
	 * 
	 * @param ns1 the first node set
	 * @param ns2 the second node set
	 * @return the intersection nodes of those two node sets
	 */
	private NodeSet intersection(NodeSet ns1,NodeSet ns2)
	{
		NodeSet result = new NodeSet();
		LinkedList<Node> n1 = ns1.getNodes();
		LinkedList<Node> n2 = ns2.getNodes();
		for(int i=0;i<n1.size();i++)
		{
			if(n2.contains(n1.get(i)))
				result.addNode(n1.get(i));
		}
		
		return result;
	}

}
