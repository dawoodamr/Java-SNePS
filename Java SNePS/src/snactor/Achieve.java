package snactor;

import java.util.LinkedList;

import sneps.*;

public class Achieve extends Action
{
	Condition test = new Condition();
	
	LinkedList<Node> reports = new LinkedList<Node>();

	Network m;
	public Achieve(Node node)
	{
		
		super(node);
		
	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public void Perform()
	{
		Node n = getNode().getUpCableSet().getUpCable("action").getNodeSet().getNodes().getFirst();
		LinkedList nodes = ((Act) n.getEntity()).getArrangedObjects();
		
		
		if(((Act) n.getEntity()).getAgenda().equals("start"))
		{
			if(testConditions((Node) nodes.getFirst()))
			{
				System.out.println("Already Achieved");
				((Act) n.getEntity()).setAgenda("done");
			}
			else
			{
				((Act) n.getEntity()).setAgenda("find-plans");
				queue.stackPush(n);
				System.out.println("Want to find a plan to achieve");
				findGplans(n,(Node) nodes.getFirst());
			}
			
		}
		else if(((Act) n.getEntity()).getAgenda().equals("find-plans"))
		{
			((Act) n.getEntity()).setPlans(((Act) n.getEntity()).getReports());
			if(!((Act) n.getEntity()).getPlans().isEmpty())
			{
				((Act) n.getEntity()).setAgenda("done");
				schedulePlans(((Act) n.getEntity()).getPlans());
			}
			else
			{
				System.out.println("DEAD END!");
			}
		}
	
	}
	
	private void schedulePlans(LinkedList<Node> plans) 
	{
		
		
	}

	@SuppressWarnings("unchecked")
	private void findGplans(Node n, Node prop) 
	{
		//the Network Problem!!
		LinkedList<Node> nodess = new LinkedList<Node>();
		java.util.Enumeration keys = m.getNodes().keys();
		while( keys.hasMoreElements() )		
		
		    {
	   Object aKey = keys.nextElement();
       Object aValue = m.getNodes().get(aKey);
       nodess.add((Node) aValue);
	       
       if(!nodess.getLast().getClass().getSimpleName().equals("BaseNode"))
	        	{ 
    	   if(((MolecularNode) nodess.getLast()).getCableSet().contains("plan")&&
			  ((MolecularNode)nodess.getLast()).getCableSet().contains("act"))
    	   			{
    		   if(((MolecularNode) nodess.getLast()).getCableSet().getCable("act").getNodeSet().getNodes().getFirst().getIdentifier().equals(n.getIdentifier()))
    		   {
    			   System.out.println(nodess.getLast().getIdentifier());
	        	   reports.add(((MolecularNode) nodess.getLast()).getCableSet().getCable("plan").getNodeSet().getNodes().getFirst());
    		   } 
    	   			}
	        	}
		    }
		((Act) n.getEntity()).setReports(reports);
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