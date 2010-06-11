package snactor;

import sneps.*;


import java.util.LinkedList;

public class WITHSOME extends Action
{
	Condition test = new Condition();
	
	LinkedList<Node> reports = new LinkedList<Node>();

	Network m = Network.getInstance();

	Node n;
	
	Node suchThat;
	
	LinkedList<Node> vars;
	
	Node doAct;
	public WITHSOME(Node node)
	{
		
		super(node);
		
		
	}
	
	public void Perform()
	{
		n = getNode().getUpCableSet().getUpCable("action").getNodeSet().getNode(0);
		suchThat = ((MolecularNode) n).getCableSet().getCable("suchthat").getNodeSet().getNode(0);
		vars = ((MolecularNode) n).getCableSet().getCable("vars").getNodeSet().getNodes();
		doAct = ((MolecularNode) n).getCableSet().getCable("do").getNodeSet().getNode(0);
		
		
	
			identifyObjects();
			
			if(!reports.isEmpty()) 
			{
				try {
					scheduleWithsomeActs(doAct,reports);
				} catch (CustomException e) {
					
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println("suchthat's property can not be matched!");
			}
 
		}

	@SuppressWarnings("static-access")
	private void identifyObjects() 
	{
		
		for(int i=0;i<test.conditions.size();i++)
		{
   			if(!test.conditions.get(i).getClass().getSimpleName().equals("BaseNode"))
   			{
   				if(((MolecularNode) test.conditions.get(i)).getCableSet().contains("property")&&
   					  ((MolecularNode)test.conditions.get(i)).getCableSet().contains("object"))
   				   {
   				//	System.out.println(((MolecularNode) test.conditions.get(i)).getCableSet().getCable("property").getNodeSet().getNode(0).getIdentifier());
   			//		System.out.println(((MolecularNode) suchThat).getCableSet().getCable("property").getNodeSet().getNode(0).getIdentifier());
   					if(((MolecularNode) test.conditions.get(i)).getCableSet().getCable("property").getNodeSet().getNode(0).getIdentifier().equals(((MolecularNode) suchThat).getCableSet().getCable("property").getNodeSet().getNode(0).getIdentifier()))
   					   {	
   					   	reports.add(((MolecularNode) test.conditions.get(i)).getCableSet().getCable("object").getNodeSet().getNode(0));
  					   }
       			   }
   			}
   		}
	}

	private void scheduleWithsomeActs(Node doAct, LinkedList<Node> reports) throws CustomException 
	{
		if(!reports.isEmpty())
		{
			Relation r1 = m.getRelation("action");
			Relation r2 = m.getRelation("actObject");
			
			CaseFrame x = m.getCaseFrame("actObject,action");
			
			
				Object[][] o20 = new Object[2][2];

				o20[0][0] = r1;
				o20[1][0] = r2;
				Node bn21 = m.build((String)reports.getFirst().toString());
				o20[0][1] = bn21;
				o20[1][1] = reports.getFirst();
				
				try {
					Act.attach(bn21,((Act) doAct.getEntity()).getTheAction().getEntity().getClass().getSimpleName());
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} catch (InstantiationException e) {
				
					e.printStackTrace();
				} catch (IllegalAccessException e) {
				
					e.printStackTrace();
				}

				Node yy = m.build(o20,x);
				
				Act a21 = new Act((MolecularNode) yy);
				yy.setEntity(a21);

				
				a21.setPrimaction(true);
				a21.setAgenda("start");
				
				Queue.stackPush(yy);
				
			//	Act.performSNePS(yy,m);
			
		
				
		}
		else
		{
			System.out.println("No vars found to apply action!");
		}
		
		
	}
	}



