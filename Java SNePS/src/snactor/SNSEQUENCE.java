package snactor;

import sneps.*;


import java.util.LinkedList;

public class SNSEQUENCE extends Action
{
	

	public SNSEQUENCE(Node node)
	{
		
		super(node);
	
	}
	
	@SuppressWarnings({ "static-access", "unchecked" })
	public void Perform()
	{
		Node n = getNode().getUpCableSet().getUpCable("action").getNodeSet().getNodes().getFirst();
		LinkedList nodes = ((Act) n.getEntity()).getArrangedObjects();
	//	if(((Act) n.getEntity()).getAgenda().equals("start"))
	//	{
		for(int i=0;i<nodes.size();i++)
		{	
//			if(((Node) nodes.get(i)).getEntity().getClass().getSimpleName().equals("SNSEQUENCE"))
//			{
//				System.out.println("seq");
//				((SNSEQUENCE) nodes.get(i)).Perform();
//			}
			((Act) nodes.get(i)).setAgenda("start");
			((Act) nodes.get(i)).setPriority("intend");
			queue.stackPush((Node) nodes.get(i));
			System.out.println(queue.stackPop().getIdentifier());
			}
	//	}
		
	
	}

}