package snactor;

import java.util.LinkedList;

import sneps.MolecularNode;

import sneps.Node;

public class SNIF extends Action
{
	Condition test = new Condition();
	
	Node x;
	Node y;
	Node z;
	Node n;
	LinkedList<Node> ns = new LinkedList<Node>();
	
	public SNIF(Node node)
	{
		
		super(node);
		
	}
	
	@SuppressWarnings({ "static-access", "unchecked" })
	public void Perform()
	{
		n = getNode().getUpCableSet().getUpCable("action").getNodeSet().getNodes().getFirst();
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
				((Act) n.getEntity()).setAgenda("done");
				System.out.println(((Act) n.getEntity()).getAgenda());
			}
		}
	
	}
	
	@SuppressWarnings("static-access")
	private void queueThenActs(LinkedList<Node> nodes)
	{
		y = ((MolecularNode) nodes.getFirst().getUpCableSet()
				.getUpCable("condition").getNodeSet().getNodes()
				.getFirst()).getCableSet().getCable("then")
				.getNodeSet().getNodes().getFirst();
		
		((Act) y.getEntity()).setAgenda("start");
		((Act) y.getEntity()).setPriority("intend");
		queue.stackPush(y);
		//z = (MolecularNode) queue.stackPop();
		//System.out.println(((Act) z.getEntity()).getAgenda());
		
	}

	public void testConditions(Node x)
	{
		if(test.testConditions(x))
		{
			ns.add(x);		
		}
	}

}