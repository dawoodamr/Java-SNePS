package snactor;

import sneps.*;


import java.util.LinkedList;

public class Reload01a extends Action
{
	

	public Reload01a(Node node)
	{
		
		super(node);
	
	}
	
	@SuppressWarnings({ "static-access", "unchecked" })
	public void Perform()
	{
		Node n = getNode().getUpCableSet().getUpCable("action").getNodeSet().getNodes().getFirst();
		LinkedList nodes = ((Act) n.getEntity()).getArrangedObjects();
		for(int i=0;i<nodes.size();i++)
		{
			queue.stackPush((Node) nodes.get(i));
			System.out.println(queue.stackPop().getIdentifier());
		}
		
	
	}

}