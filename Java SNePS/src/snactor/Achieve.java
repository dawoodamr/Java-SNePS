package snactor;

import java.util.LinkedList;

import sneps.*;

public class Achieve extends Action
{
	Condition test = new Condition();
	
	LinkedList<Node> reports = new LinkedList<Node>();

	Network m = Network.getInstance();
	public Achieve(Node node)
	{
		
		super(node);
		
	}
	
	@SuppressWarnings({ })
	public void Perform()
	{
		
		Node n = getNode().getUpCableSet().getUpCable("action").getNodeSet().getNodes().getFirst();
		LinkedList<Node> nodes = ((Act) n.getEntity()).getArrangedObjects();
		
		
	
			
			if(testConditions( nodes.getFirst()))
			{
				System.out.println("Already Achieved");
				((Act) n.getEntity()).setAgenda("done");
			}
			else
			{
				System.out.println("Want to find a plan to achieve" + " "+nodes.getFirst().getIdentifier());
				findGplans(n,(Node) nodes.getFirst());
				//System.out.println(reports.getLast().);
				((Act) n.getEntity()).setPlans(reports);
				((Act) n.getEntity()).setReports(new LinkedList<Node>());
			if(!((Act) n.getEntity()).getPlans().isEmpty())
			{
				System.out.println("Plan found to achieve" + " "+nodes.getFirst().getIdentifier() );
				//((Act) n.getEntity()).setAgenda("done");
				//System.out.println(((Act) ((Act) n.getEntity()).getPlans().getFirst().getEntity()).getTheAction().getIdentifier());
				schedulePlans(((Act) n.getEntity()).getPlans().getFirst());
				//Queue.stackPop();
			}
			else
			{
				System.out.println("DEAD END!");
			}
			}
			
			
		}
	
	
	
	private void schedulePlans(Node plan)
	{
		((Act) plan.getEntity()).setPrimaction(true);
		((Act) plan.getEntity()).setAgenda("start");
	//	System.out.println(((MolecularNode) plan).getCableSet().getCables().getFirst().getRelation().getName());
	//	System.out.println(((MolecularNode) plan).getCableSet().getCables().getLast().getNodeSet().getNodes().getLast().getIdentifier());

		Queue.stackPush(plan);
	try {
		//	System.out.println("mark");
			((Act) plan.getEntity()).performSNePS(plan, m);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			System.out.println("a7oo");
//			//((Act) plan.getEntity()).performSNePS(plan, m);
//			
//		} catch (CustomException e) {
//			System.out.println("a7a");
//			e.printStackTrace();
//		}
		
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
			  ((MolecularNode)nodess.getLast()).getCableSet().contains("goal"))
    	   			{
    		   if(((MolecularNode) nodess.getLast()).getCableSet().getCable("goal").getNodeSet().getNodes().getFirst().getIdentifier().equals(prop.getIdentifier()))
    		   {
    			  // System.out.println(((MolecularNode) nodess.getLast()).getCableSet().getCable("plan").getNodeSet().getNodes().getFirst().getIdentifier());
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