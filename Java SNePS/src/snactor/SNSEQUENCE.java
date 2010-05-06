package snactor;

import sneps.*;


import java.util.LinkedList;

public class SNSEQUENCE extends Action
{
	Queue temp;

	public SNSEQUENCE(Node node)
	{
		
		super(node);
	
	}
	
	@SuppressWarnings({ "static-access" })
	public void Perform()
	{
		Node n = getNode().getUpCableSet().getUpCable("action").getNodeSet().getNodes().getFirst();
		LinkedList<Node> nodes = ((Act) n.getEntity()).getArrangedObjects();
		
		for(int i=0;i<nodes.size();i++)
		{	
			((Act) nodes.get(i).getEntity()).setAgenda("start");
	
			temp.stackPush((Node) nodes.get(i));
		
		}
		
		for(int i=0;i<nodes.size();i++)
		{	
			
			queue.stackPush(temp.stackPop());
			
		}
	
	}

}