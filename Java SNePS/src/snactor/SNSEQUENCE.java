package snactor;

import sneps.*;


import java.util.LinkedList;
import java.util.Stack;

public class SNSEQUENCE extends Action
{
	Stack<Node> temp = new Stack<Node>();

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
	
			temp.push((Node) nodes.get(i));
		
		}
		
		for(int i=0;i<nodes.size();i++)
		{	
			
			queue.stackPush(temp.pop());
			
		}
	
	}

}