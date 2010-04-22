package snactor;

import sneps.*;


import java.util.LinkedList;

public class dummy extends Action
{

	
	public dummy(Node node) {
		
		super(node);
		
	}

	@SuppressWarnings("unchecked")
	public void Perform()
	{
		
		Node n = getNode().getUpCableSet().getUpCable("action").getNodeSet().getNodes().getFirst();
		LinkedList objs = ((Act) n.getEntity()).getArrangedObjects();
		for(int i=0;i<objs.size();i++)
		{
		//	System.out.println(((Node) objs.get(i)).getIdentifier());
		}
	
	}

}