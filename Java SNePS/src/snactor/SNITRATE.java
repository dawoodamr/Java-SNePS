package snactor;

import java.util.LinkedList;

import sneps.MolecularNode;

import sneps.Node;

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
		Node n = getNode().getUpCableSet().getUpCable("action").getNodeSet().getNodes().getFirst();
		LinkedList nodes = ((Act) n.getEntity()).getArrangedObjects();
		
		
		if(((Act) n.getEntity()).getAgenda().equals("start"))
		{
			((Act) n.getEntity()).setAgenda("test");
			queue.stackPush(n);
			for(int i=0;i<nodes.size();i++)
			{
				x =((MolecularNode) nodes.get(i)).getCableSet().getCable("condition").getNodeSet().getNodes().getFirst();
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
		y = ((MolecularNode) nodes.getFirst().getUpCableSet().getUpCable("condition").getNodeSet().getNodes().getFirst()).getCableSet().getCable("then").getNodeSet().getNodes().getFirst();
		Act a = new Act((MolecularNode) y);
		y.setEntity(a);
		a.setAgenda("start");
		a.setPriority("intend");
		queue.stackPush(y);
		//z = (MolecularNode) queue.stackPop();
	//	System.out.println(((Act) z.getEntity()).getAgenda());
	}

	public void testConditions(Node x)
	{
		if(test.testConditions(x))
		{
			ns.add(x);		
		}
	}

	
}