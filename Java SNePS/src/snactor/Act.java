package snactor;

import java.util.LinkedList;

import sneps.*;

public class Act extends Entity
{
	private LinkedList<Node> arrangedObjects;
	
	public Act(MolecularNode node)
	{
		
		setNode(node);
		
	}
	
	public Node getAction(MolecularNode node)
	{
		CableSet theCables = node.getCableSet();
		
		Cable a = theCables.getCable("action");
			
		NodeSet nodes = a.getNodeSet();
		
		Node theAction = nodes.getNodes().getFirst();
		
		return theAction;
	}
	
	public Node getMainObject(MolecularNode node)
	{
		CableSet theCables = node.getCableSet();
		
		Cable b = theCables.getCable("object");
		
		NodeSet nodes = b.getNodeSet();
		
		Node MainObject = nodes.getNodes().getFirst();
		
		return MainObject;
		
	}
	
	public LinkedList<Node> getObjects(Node mainNode)
	{
		
		
		MolecularNode m = (MolecularNode) mainNode;
		
		CableSet theCables = m.getCableSet();
		
	//	for(int i=0;i<theCables.getCables().size();i++)
	//	{
			arrangedObjects.add(theCables.getCable("object1").getNodeSet().getNodes().getFirst());
	
			if(theCables.getCable("object2").getNodeSet().getNodes().getFirst().getClass().getSimpleName().equals("BaseNode"))
			{
				arrangedObjects.add(theCables.getCable("object2").getNodeSet().getNodes().getFirst());
			}
			else
			{
				getObjects(theCables.getCable("object2").getNodeSet().getNodes().getFirst());
			}
			
	//	}
	
		
		return arrangedObjects;
		
		
	}
	
	
}