package snactor;

import sneps.*;


import java.util.LinkedList;

public class DoOne extends Action
{
	

	public DoOne(Node node)
	{
		
		super(node);
	
	}
	
	@SuppressWarnings({ "static-access" })
	public void Perform()
	{
		Node n = getNode().getUpCableSet().getUpCable("action").getNodeSet().getNodes().getFirst();
		LinkedList<Node> nodes = ((Act) n.getEntity()).getArrangedObjects();
		
		((Act) nodes.getFirst().getEntity()).setAgenda("start");
			queue.stackPush((Node) nodes.getFirst());
		//	System.out.println(queue.stackPop().getIdentifier());
		
		
	}

}