package snactor;

import sneps.*;


import java.util.LinkedList;

public class WITHALL extends Action
{
	Condition test = new Condition();
	
	LinkedList<Node> reports = new LinkedList<Node>();

	Network m;
	public WITHALL(Node node)
	{
		
		super(node);
	
	}
	
	@SuppressWarnings({ "static-access" })
	public void Perform()
	{
		Node n = getNode().getUpCableSet().getUpCable("action").getNodeSet().getNodes().getFirst();
		LinkedList<Node> vars = ((Act) n.getEntity()).getArrangedObjects();
		@SuppressWarnings("unused")
		Node doAct = ((MolecularNode) n).getCableSet().getCable("do").getNodeSet().getNodes().getFirst();
		Node suchThat = ((MolecularNode) n).getCableSet().getCable("suchthat").getNodeSet().getNodes().getFirst();
		if(((Act) n.getEntity()).getAgenda().equals("start"))
		{
			((Act) n.getEntity()).setAgenda("identify-objects");
			queue.stackPush(n);
			identifyObjects(n,suchThat, vars);
		}
		else
		if(((Act) n.getEntity()).getAgenda().equals("identify-objects"))
		{
			((Act) n.getEntity()).setAgenda("done");

		}
		if(!reports.isEmpty())
		{
			scheduleWithsomeActs();
		}
	}

	private void scheduleWithsomeActs() 
	{
	
		
	}

	
		@SuppressWarnings("unchecked")
		private void identifyObjects(Node n, Node suchThat, LinkedList<Node> vars) 
		{
			LinkedList<Node> nodess = new LinkedList<Node>();
			java.util.Enumeration keys = m.getNodes().keys();
			while( keys.hasMoreElements() )		
				
		    {
				Object aKey = keys.nextElement();
				Object aValue = m.getNodes().get(aKey);
				nodess.add((Node) aValue);
	   			test.testConditions(suchThat);
	   			if(!nodess.getLast().getClass().getSimpleName().equals("BaseNode"))
	{ 
	   if(((MolecularNode) nodess.getLast()).getCableSet().contains("property")&&
		  ((MolecularNode)nodess.getLast()).getCableSet().contains("object"))
	   	{
		   if(((MolecularNode) nodess.getLast()).getCableSet().getCable("property").getNodeSet().getNodes().getFirst().getIdentifier().equals(suchThat.getIdentifier()))
		   {	
			   for(int i=0; i<vars.size();i++)
			   {
				   if(((MolecularNode) nodess.getLast()).getCableSet().getCable("object").getNodeSet().getNodes().getFirst().equals(vars.get(i)))
				   {
				   
					   	reports.add(((MolecularNode) nodess.getLast()).getCableSet().getCable("object").getNodeSet().getNodes().getFirst());
		  
				   }
			   }
		   }
		   
	   	}
	}
	   		}
			
			((Act) n.getEntity()).setReports(reports);
		}
	}

