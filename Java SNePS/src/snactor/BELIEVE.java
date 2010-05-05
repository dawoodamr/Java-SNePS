package snactor;

import java.util.LinkedList;

import sneps.Node;

public class BELIEVE extends Action
{
	Condition test = new Condition();

	
	public BELIEVE(Node node)
	{
		
		super(node);
		
	}
	
	@SuppressWarnings({ })
	public void Perform()
	{
		//System.out.println("7aram");
		Node n = getNode().getUpCableSet().getUpCable("action").getNodeSet().getNodes().getFirst();
		LinkedList<Node> nodes = ((Act) n.getEntity()).getArrangedObjects();
		
		
			if(testConditions(nodes.getFirst()))
			{
				((Act) n.getEntity()).setAgenda("done");
			}
			else
			{
				believeProposition((Node) nodes.getFirst());
				((Act) n.getEntity()).setAgenda("done");
			//	System.out.println(((Node) nodes.getFirst()).getIdentifier());
		}
			
		
	
	
	}

	@SuppressWarnings("static-access")
	private void believeProposition(Node n) 
	{
		
		test.conditions.add(n);
		
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