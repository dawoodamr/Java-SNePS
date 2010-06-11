package snactor;

import java.util.LinkedList;

import sneps.*;

public class SNITRATE extends Action
{
	Condition test = new Condition();
	
	Node x;
	Node y;
	Node z;
	
	LinkedList<Node> ns = new LinkedList<Node>();
	
	public SNITRATE(Node node)
	{
		
		super(node);
		
	}
	
	@SuppressWarnings({ "static-access", "unchecked" })
	public void Perform()
	{
		Node n = getNode().getUpCableSet().getUpCable("action").getNodeSet().getNode(0);
		LinkedList nodes = ((Act) n.getEntity()).getArrangedObjects();
		
		
		if(((Act) n.getEntity()).getAgenda().equals("start"))
		{
			((Act) n.getEntity()).setAgenda("test");
			queue.stackPush(n);
			for(int i=0;i<nodes.size();i++)
			{
				x =((MolecularNode) nodes.get(i)).getCableSet().getCable("condition").getNodeSet().getNode(0);
				testConditions(x);
			}
			
		}
		else if(((Act) n.getEntity()).getAgenda().equals("test"))
		{
			if(!ns.isEmpty())
			{
				queueThenActs(ns);
				((Act) n.getEntity()).setAgenda("start");
				queue.stackPush(n);
			}
			else
			{
				((Act) n.getEntity()).setAgenda("done");
				queuePlanB();
			}
		}
	
	}
	
	private void queuePlanB() 
	{
		
		System.out.println("No Else Defined");
		
	}

	@SuppressWarnings("static-access")
	private void queueThenActs(LinkedList<Node> nodes)
	{
		y = ((MolecularNode) nodes.getFirst().getUpCableSet().getUpCable("condition").getNodeSet().getNode(0)).getCableSet().getCable("then").getNodeSet().getNode(0);
		Act a = new Act((MolecularNode) y);
		y.setEntity(a);
		a.setAgenda("start");
		a.setPriority("intend");
		queue.stackPush(y);
	}

	public void testConditions(Node x)
	{
		if(test.testConditions(x))
		{
			ns.add(x);		
		}
	}

	
}