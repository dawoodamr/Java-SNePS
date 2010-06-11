package snactor;

import java.util.LinkedList;

import sneps.*;

public class BELIEVE extends Action
{
	Condition test = new Condition();

	
	public BELIEVE(Node node)
	{
		
		super(node);
		
	}
	
	public void Perform()
	{
		
		Node n = getNode().getUpCableSet().getUpCable("action").getNodeSet().getNode(0);
		LinkedList<Node> nodes = ((Act) n.getEntity()).getArrangedObjects();
		
		
			if(testConditions(nodes.getFirst()))
			{
				((Act) n.getEntity()).setAgenda("done");
			}
			else
			{
				believeProposition((Node) nodes.getFirst());
				((Act) n.getEntity()).setAgenda("done");
			
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