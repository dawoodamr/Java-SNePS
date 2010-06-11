package snactor;

import sneps.*;


import java.util.LinkedList;

public class tester extends Action
{

	public tester(Node node)
	{
		
		super(node);
	
	}
	
	@SuppressWarnings({ "unchecked" })
	public void Perform()
	{
		Node n = getNode().getUpCableSet().getUpCable("action").getNodeSet().getNode(0);
		LinkedList nodes = ((Act) n.getEntity()).getArrangedObjects();
		
		System.out.println(((Node) nodes.getFirst()).getIdentifier());

	}

}