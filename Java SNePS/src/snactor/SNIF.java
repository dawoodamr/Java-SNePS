package snactor;

import java.util.LinkedList;

import sneps.*;

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
		
		n = getNode().getUpCableSet().getUpCable("action").getNodeSet().getNode(0);
		LinkedList nodes = ((Act) n.getEntity()).getArrangedObjects();
		
	
			((Act) n.getEntity()).setAgenda("test");
			queue.stackPush(n);
			
			for(int i=0;i<nodes.size();i++)
			{
				x =((MolecularNode) nodes.get(i)).getCableSet().getCable("condition").getNodeSet().getNode(0);
				testConditions(x);
				
			}
			
	
			if(!ns.isEmpty())
			{
				
				queueThenActs(ns);
			}
		}
	
	
	@SuppressWarnings("static-access")
	private void queueThenActs(LinkedList<Node> nodes)
	{
		y = ((MolecularNode) nodes.getFirst().getUpCableSet()
				.getUpCable("condition").getNodeSet().getNode(0)).getCableSet().getCable("act")
				.getNodeSet().getNode(0);
		
		((Act) y.getEntity()).setAgenda("start");
		((Act) y.getEntity()).setPrimaction(true);

	
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