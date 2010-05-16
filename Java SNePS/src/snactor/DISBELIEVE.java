package snactor;

import java.util.LinkedList;

import sneps.Node;

public class DISBELIEVE extends Action
{
	Condition test = new Condition();

	
	public DISBELIEVE(Node node)
	{
		
		super(node);
		
	}
	
	public void Perform()
	{
		
		Node n = getNode().getUpCableSet().getUpCable("action").getNodeSet().getNodes().getFirst();
		LinkedList<Node> nodes = ((Act) n.getEntity()).getArrangedObjects();
		
		
			if(testConditions(nodes.getFirst()))
			{
				disbelieveProposition((Node) nodes.getFirst());
				((Act) n.getEntity()).setAgenda("done");
			}
			else
			{
				
				((Act) n.getEntity()).setAgenda("done");
			
			}
	
	}

	@SuppressWarnings("static-access")
	private void disbelieveProposition(Node n) 
	{
		
		test.conditions.remove(n);
		
	}

	public boolean testConditions(Node x)
	{
		if(test.testConditions(x))
		{
			return true;		
		}
		return false;
	}



}