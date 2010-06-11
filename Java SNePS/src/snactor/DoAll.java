package snactor;

import sneps.*;


import java.util.LinkedList;

public class DoAll extends Action
{
	

	public DoAll(Node node)
	{
		
		super(node);
	
	}
	
	@SuppressWarnings({ "static-access" })
	public void Perform()
	{
		Node n = getNode().getUpCableSet().getUpCable("action").getNodeSet().getNode(0);
		LinkedList<Node> nodes = ((Act) n.getEntity()).getArrangedObjects();
		
		for(int i=0;i<nodes.size();i++)
		{
			((Act) nodes.get(i).getEntity()).setAgenda("start");
			queue.stackPush((Node) nodes.get(i));
		}
		
	
	}

}