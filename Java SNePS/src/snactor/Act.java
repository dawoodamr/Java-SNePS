package snactor;

import java.util.LinkedList;

import sneps.*;

public class Act extends Entity
{
	
	private  LinkedList<Node> arrangedObjects;
	
	private Node theAction;
	
	private String Agenda;
	
	private String Priority;
	
	private LinkedList<Node> preConditions;
	
	private LinkedList<Node> reports;
	
	private LinkedList<Node> effects;
	
	private LinkedList<Node> plans;
	
	public Act(MolecularNode node)
	{
		setNode(node);
		this.setAgenda(Agenda);
		this.setPriority(Priority);
		this.arrangedObjects = new LinkedList<Node>();
		this.preConditions = new LinkedList<Node>();
		this.reports = new LinkedList<Node>();
		getAction();
		getObjects(getMainObject());
		
	}
	
	public void getAction()
	{
		
		CableSet theCables = (((MolecularNode) getNode()).getCableSet());
		
		Cable a = theCables.getCable("action");
			
		NodeSet nodes = a.getNodeSet();
		
		setTheAction(nodes.getNodes().getFirst());
		
		
	}
	
	private Node getMainObject()
	{
		MolecularNode m = (MolecularNode) getNode();
		
		if(m.getCableSet().contains("actObject"))
		{
		Node MainObject = m.getCableSet().getCable("actObject").getNodeSet().getNodes().getFirst();
		return MainObject;
		}
		else
		{
		return null;
		}

	}
	
	public void getObjects(Node mainNode)
	
	{
		if(mainNode.getClass().getSimpleName().equals("BaseNode"))
		{
			addNode(mainNode);
		}
		else
		{
		MolecularNode m2 = (MolecularNode) mainNode;
		
		if(m2.getCableSet().contains("object1")&&m2.getCableSet().contains("object2"))
		{	

			addNode(m2.getCableSet().getCable("object1").getNodeSet().getNodes().getFirst());	
			getObjects(m2.getCableSet().getCable("object2").getNodeSet().getNodes().getFirst());
		}
		else
		{
			addNode(mainNode);
		}
		
	}
		
}
	
	public void excute(Node a)
	{
		//((Action) theAction.getEntity()).Perform();
		if(((Act) a.getEntity()).getAgenda().equals("start"))
		{
			((Act) a.getEntity()).setAgenda("test");
			Queue.stackPush(a);
			findPreconditions(a);
		}
		else
		if(((Act) a.getEntity()).getAgenda().equals("find-preconditions"))
		{
			((Act) a.getEntity()).setPreConditions(reports);
			((Act) a.getEntity()).setReports(new LinkedList<Node>());
			((Act) a.getEntity()).setAgenda("test-preconditions");
			Queue.stackPush(a);
			testPreconditions(a);
			//I don't Understand why find effects is called here!!
			((Act) a.getEntity()).setAgenda("find-effects");
			Queue.stackPush(a);
			findEffects();
		}
		else
		if(((Act) a.getEntity()).getAgenda().equals("test-preconditions"))
		{
			((Act) a.getEntity()).setReports(new LinkedList<Node>());
			((Act) a.getEntity()).setAgenda("find-effects");
			Queue.stackPush(a);
			findEffects();
			//Same
			((Act) a.getEntity()).setAgenda("start");
			((Act) a.getEntity()).setReports(new LinkedList<Node>());
			Queue.stackPush(a);
			schedulePreconditions(preConditions);

		}
		else
		if(((Act) a.getEntity()).getAgenda().equals("find-effects"))
		{
			((Act) a.getEntity()).setEffects(reports);
			((Act) a.getEntity()).setReports(new LinkedList<Node>());
			scheduleBelieveEffects(	((Act) a.getEntity()).getEffects());
			((Act) a.getEntity()).setAgenda("excute");
			Queue.stackPush(a);
			
		}
		else
		if(((Act) a.getEntity()).getAgenda().equals("excute"))				
		{
			//This will change to primitive check
			if(((Act) a.getEntity()).getTheAction().getIdentifier().equals("sa"))
			{
				excutePrimaction(((Act) a.getEntity()).getTheAction());
				((Act) a.getEntity()).setAgenda("done");
				//Same Again
				((Act) a.getEntity()).setReports(new LinkedList<Node>());
				((Act) a.getEntity()).setAgenda("find-plans");
				Queue.stackPush(a);
				findPlans();
			}
		}
		else
		if(((Act) a.getEntity()).getAgenda().equals("find-plans"))
		{
			((Act) a.getEntity()).setPlans(plans);
			((Act) a.getEntity()).setReports(new LinkedList<Node>());
			((Act) a.getEntity()).setAgenda("done");
			schedulePlans(((Act) a.getEntity()).getPlans());
			//AGAIN
			((Act) a.getEntity()).setAgenda("done");
		}
	}

	private void schedulePlans(LinkedList<Node> plans2) {
	
	}

	private void findPlans() {
		
	}

	private void excutePrimaction(Node theAction2) {
		
		
	}

	private void scheduleBelieveEffects(LinkedList<Node> effects2) {
		
		
	}

	private void schedulePreconditions(LinkedList<Node> preConditions2) {
	
		
	}

	private void findEffects() {
		
		
	}

	private void testPreconditions(Node a) {
		
		
	}

	private void findPreconditions(Node a) 
	{
		
//		Relation r11 = new Relation("precondition", "single", null, 0);
//		Relation r12 = new Relation("act", "single", null, 0);
//
//		LinkedList ll = new LinkedList();
//		ll.add(r11);
//		ll.add(r12);
//		
//		CaseFrame cf = new CaseFrame("pre", ll);
//		
//		Object[][] o4 = new Object[2][2];
//		
//		o4[0][0] = r11;
//		o4[1][0] = r12;
//		VariableNode bn7 = new VariableNode("preconditions");
//		o4[0][1] = bn7;
//		o4[1][1] = a;
		
		
		
	}

	public void setTheAction(Node theAction) {
		this.theAction = theAction;
	
	}
	
	public void addNode(Node e)
	{
		this.arrangedObjects.add(e);
	}
	
	
	public LinkedList<Node> getArrangedObjects() {
		return arrangedObjects;
	}

	public void setArrangedObjects(LinkedList<Node> arrangedObjects) {
		this.arrangedObjects = arrangedObjects;
	}

	public Node getTheAction() {
		return theAction;
	}

	public void setAgenda(String agenda) {
		Agenda = agenda;
	}

	public String getAgenda() {
		return Agenda;
	}

	public void setPriority(String priority) {
		Priority = priority;
	}

	public String getPriority() {
		return Priority;
	}
	
	public void setPreConditions(LinkedList<Node> preConditions) {
		this.preConditions = preConditions;
	}

	public LinkedList<Node> getPreConditions() {
		return preConditions;
	}

	public void setReports(LinkedList<Node> reports) {
		this.reports = reports;
	}

	public LinkedList<Node> getReports() {
		return reports;
	}

	public void setEffects(LinkedList<Node> effects) {
		this.effects = effects;
	}

	public LinkedList<Node> getEffects() {
		return effects;
	}

	public void setPlans(LinkedList<Node> plans) {
		this.plans = plans;
	}

	public LinkedList<Node> getPlans() {
		return plans;
	}
	
	public static void main(String[] args) throws CustomException
	{
	//	Condition test = new Condition();
		
		Network m = new Network();
		
		Relation r1 = m.defineRelation("action", "single", null, 1);
		Relation r2 = m.defineRelation("actObject", "single", null, 0);
		Relation r3 = m.defineRelation("object1", "single", null, 1);
		Relation r4 = m.defineRelation("object2", "single", null, 0);
		Relation r5 = m.defineRelation("condition", "single", null, 1);
		Relation r6 = m.defineRelation("then", "single", null, 0);
			
		LinkedList<Relation> l = new LinkedList<Relation>();
		l.add(r1);
		l.add(r2);
		
		CaseFrame cf1 = m.defineCaseFrame("act", l);
		
		LinkedList<Relation> l2 = new LinkedList<Relation>();
		l2.add(r3);
		l2.add(r4);
		
		CaseFrame cf2 = m.defineCaseFrame("object", l2);
		
		Object[][] o4 = new Object[2][2];
		
		o4[0][0] = r1;
		o4[1][0] = r2;
		Node bn7 = m.build("SNACTION");
		Node bn8 = m.build("MainSN");
		o4[0][1] = bn7;
		o4[1][1] = bn8;

		Node n4 = m.build(o4, cf1);
		
		Act aaa = new Act((MolecularNode) n4);
		n4.setEntity(aaa);
		
		Object[][] o3 = new Object[2][2];
		
		o3[0][0] = r5;
		o3[1][0] = r6;
		Node bn5 = m.build("condition");
	//	Node bn6 = m.build("actnode");
		o3[0][1] = bn5;
		o3[1][1] = n4;
		
		
//
//		Action aa3  = new Action(bn6);
//		bn6.setEntity(aa3);
		
	
		
		Node n3 = m.build(o3, cf2);
		
		
		Object[][] o = new Object[2][2];
		

		o[0][0] = r1;
		o[1][0] = r2;
		Node bn1 = m.build("SNIF");	
		//Node bn2 = m.build("theMainObject");
		o[0][1] = bn1;
		o[1][1] = n3;
		
		SNIF aa  = new SNIF(bn1);
		bn1.setEntity(aa);
		
		Node n1 = m.build(o, cf1);
			
		Act a = new Act((MolecularNode) n1);
		n1.setEntity(a);
		a.setAgenda("start");
		for(int i=0;i<a.arrangedObjects.size();i++)
		{
			System.out.println(a.arrangedObjects.get(i).getIdentifier());
		}
		aa.Perform();
		a.setAgenda("test");
		aa.Perform();
		
	//	System.out.println(m.find(o4));
	//	System.out.println(test.testConditions("cond"));
	}


}
	
