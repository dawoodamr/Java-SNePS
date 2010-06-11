package snactor;

import sneps.*;


import java.util.LinkedList;

public class PUT extends Action
{

	public PUT(Node node)
	{
		
		super(node);
	
	}
	
	@SuppressWarnings({ "unchecked" })
	public void Perform()
	{
		Node n = getNode().getUpCableSet().getUpCable("action").getNodeSet().getNode(0);
		LinkedList nodes = ((Act) n.getEntity()).getArrangedObjects();
	
		System.out.println(((Node) nodes.get(0)).getIdentifier()+" is put on "+ ((Node) nodes.get(1)).getIdentifier());
  
	}

}