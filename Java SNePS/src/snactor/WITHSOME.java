package snactor;

import sneps.*;


import java.util.LinkedList;

public class WITHSOME extends Action
{
	Condition test = new Condition();
	
	LinkedList<Node> designator = new LinkedList<Node>();
	
	public WITHSOME(Node node)
	{
		
		super(node);
	
	}
	
	@SuppressWarnings({ "static-access", "unchecked" })
	public void Perform()
	{
		Node n = getNode().getUpCableSet().getUpCable("action").getNodeSet().getNodes().getFirst();
		LinkedList nodes = ((Act) n.getEntity()).getArrangedObjects();
		
		if(((Act) n.getEntity()).getAgenda().equals("start"))
		{
			((Act) n.getEntity()).setAgenda("identify-objects");
			queue.stackPush(n);
			identifyObjects(n,nodes);
		}
		else
		if(((Act) n.getEntity()).getAgenda().equals("identify-objects"))
		{
			((Act) n.getEntity()).setAgenda("done");

		}
		if(!designator.isEmpty())
		{
			scheduleWithsomeActs();
		}
	}

	private void scheduleWithsomeActs() 
	{
	
		
	}

	@SuppressWarnings("unchecked")
	private void identifyObjects(Node n, LinkedList nodes) 
	{
		for(int i=0; i<nodes.size();i++)
		{
		if(test.testConditions((Node) nodes.get(i)))
		{
			designator.add((Node) nodes.get(i));		
		}
		}
	}

}