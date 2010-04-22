package snactor;

//import java.util.LinkedList;

import sneps.*;

public abstract class ActionTrial extends Entity
{
//	private LinkedList<Node> parameters;
	
	public ActionTrial(BaseNode node,Boolean defined)
	
	{
		setNode(node);
	}
	
	public Act getActNode(BaseNode node)
	{
		Node n = node.getUpCableSet().getUpCable("action").getNodeSet().getNodes().getFirst();
		return (Act) n.getEntity();
	}

	
	public void AttachPrimaction(Act node)
	{
		
	}
	
}