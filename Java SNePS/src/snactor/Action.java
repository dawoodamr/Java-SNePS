package snactor;

import java.util.LinkedList;

import sneps.*;

public abstract class Action extends Entity
{
	private LinkedList<Node> parameters;
	
	public Action(BaseNode node)
	
	{
		setNode(node);
	}
	
	public Act getActNode(BaseNode node)
	{
		Node n = node.getUpCableSet().getUpCable("action").getNodeSet().getNodes().getFirst();
		return (Act) n.getEntity();
	}
	

	
	public void Perform(Act node)
	{
		
		
		
	}
	
}