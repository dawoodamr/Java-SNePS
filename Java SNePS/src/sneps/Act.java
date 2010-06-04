package sneps;

import java.util.LinkedList;

import java.lang.reflect.*;

import snactor.*;

@SuppressWarnings("unused")
public class Act extends Entity {

	private LinkedList<Node> arrangedObjects;

	private Node theAction;

	private String Agenda;

	private String Priority;

	private boolean primaction;

	private static LinkedList<Node> preConditions;

	private static LinkedList<Node> unAchievedPreConditions;

	private static LinkedList<Node> reports;

	private static LinkedList<Node> effects;

	private static LinkedList<Node> plans;

	static int pretrialcount = 0;

	static Condition test = new Condition();
	
	
	public Act(Node node)
	{
		super(node);
	}

	
	
	@SuppressWarnings("static-access")
	public Act(MolecularNode node) {
		setNode(node);
		this.setAgenda(Agenda);
		this.setPriority(Priority);
		this.arrangedObjects = new LinkedList<Node>();
		this.preConditions = new LinkedList<Node>();
		this.reports = new LinkedList<Node>();
		getAction();
		getObjects(getMainObject());

	}

	public void getAction() {

		CableSet theCables = (((MolecularNode) getNode()).getCableSet());

		Cable a = theCables.getCable("action");

		NodeSet nodes = a.getNodeSet();

		setTheAction(nodes.getNode(0));

	}

	private Node getMainObject() {
		MolecularNode m = (MolecularNode) getNode();

		if (m.getCableSet().contains("actObject")) {
			Node MainObject = m.getCableSet().getCable("actObject")
					.getNodeSet().getNode(0);
			return MainObject;
		} else {
			return null;
		}

	}

	public void getObjects(Node mainNode)

	{
		if (mainNode.getClass().getSimpleName().equals("BaseNode")) {
			addNode(mainNode);
		} else {
			MolecularNode m2 = (MolecularNode) mainNode;

			if (m2.getCableSet().contains("object1")
					&& m2.getCableSet().contains("object2")) {

				addNode(m2.getCableSet().getCable("object1").getNodeSet()
						.getNode(0));
				getObjects(m2.getCableSet().getCable("object2").getNodeSet()
						.getNode(0));
			} else {
				addNode(mainNode);
			}

		}

	}

	public static void Intend(Node a,Network m) throws CustomException { 
		
		if (((Act) a.getEntity()).getAgenda().equals("start")) 
		{
			
			((Act) a.getEntity()).setAgenda("find-preconditions");
			Queue.stackPush(a);
			findPreconditions(a, m);
			
		} 
		else 
			
		if (((Act) a.getEntity()).getAgenda().equals("find-preconditions")) 
		{
			
			if (!reports.isEmpty()) 
			{
				
				((Act) a.getEntity()).setPreConditions(reports);
				((Act) a.getEntity()).setReports(new LinkedList<Node>());
				((Act) a.getEntity()).setUnAchievedPreConditions(new LinkedList<Node>());
				((Act) a.getEntity()).setAgenda("test-preconditions");
				Queue.stackPush(a);
				testPreconditions();
				
			} 
			else
			{
				
				((Act) a.getEntity()).setAgenda("find-effects");
				Queue.stackPush(a);
				findEffects(a,m);
				
			}
		} 
		else
		
		if (((Act) a.getEntity()).getAgenda().equals("test-preconditions")) {
			
			if (pretrialcount < 5) {
				if (tallyPreconditions(preConditions, reports)) 
				{
					((Act) a.getEntity()).setReports(new LinkedList<Node>());
					((Act) a.getEntity()).setAgenda("find-effects");
					Queue.stackPush(a);
					findEffects(a,m);
				} 
				else
				{
					((Act) a.getEntity()).setAgenda("start");
					((Act) a.getEntity()).setReports(new LinkedList<Node>());
					Queue.stackPush(a);
					schedulePreconditions(unAchievedPreConditions,m);
					pretrialcount++;
				
				}
			} 
			else
			{
				System.out.println("Preconditions Not Satisfied, Action Aborted!");
			}

		} 
		else
			
		if (((Act) a.getEntity()).getAgenda().equals("find-effects")) 
		{	
			pretrialcount = 0;
			if (!reports.isEmpty()) 
			{	
				((Act) a.getEntity()).setEffects(reports);
				((Act) a.getEntity()).setReports(new LinkedList<Node>());
				Queue.stackPush(a);
				scheduleBelieveEffects(((Act) a.getEntity()).getEffects(),m);
			}
			
				((Act) a.getEntity()).setAgenda("intend");
				Queue.stackPush(a);
				
		} 
		else 
			
		if (((Act) a.getEntity()).getAgenda().equals("intend")) 
		{
			
			if (((Act) a.getEntity()).getPrimaction()) 
			{
				excutePrimaction(((Act) a.getEntity()).getTheAction());
				((Act) a.getEntity()).setAgenda("done");
			} 
			else
			{
				((Act) a.getEntity()).setReports(new LinkedList<Node>());
				((Act) a.getEntity()).setAgenda("find-plans");
				Queue.stackPush(a);
				findPlans(a,m);
			}
		} 
		else 
		
		if (((Act) a.getEntity()).getAgenda().equals("find-plans")) {
			
			if (!reports.isEmpty())
			{
			((Act) a.getEntity()).setPlans(reports);
			((Act) a.getEntity()).setReports(new LinkedList<Node>());
			((Act) a.getEntity()).setAgenda("done");
			Queue.stackPush(a);
			schedulePlans(((Act) a.getEntity()).getPlans(),m);
			}
			else
			{
				System.out.println("No plans found, the act will be ignored!");
			}
		} 
		else 
			
		if (((Act) a.getEntity()).getAgenda().equals("done"))
		{
			Queue.stackPop();
		}
		
	}

	private static void schedulePlans(LinkedList<Node> planList, Network m) throws CustomException
	{
		
		Node plan = planList.getFirst();
		((Act) plan.getEntity()).setAgenda("start");
		((Act) plan.getEntity()).setPriority("intend");
		Queue.stackPush(plan);
		
		
	}

	@SuppressWarnings("unchecked")
	private static void findPlans(Node a,Network m) {
		LinkedList<Node> nodess = new LinkedList<Node>();
		java.util.Enumeration keys = m.getNodes().keys();
		while (keys.hasMoreElements())

		{
			Object aKey = keys.nextElement();
			Object aValue = m.getNodes().get(aKey);
			nodess.add((Node) aValue);

			if (!nodess.getLast().getClass().getSimpleName().equals("BaseNode")) {
				if (((MolecularNode) nodess.getLast()).getCableSet().contains(
						"plan")
						&& ((MolecularNode) nodess.getLast()).getCableSet()
								.contains("act")) {
					if (((MolecularNode) nodess.getLast()).getCableSet()
							.getCable("act").getNodeSet().getNode(0)
							.getIdentifier().equals(a.getIdentifier())) {
						//System.out.println(nodess.getLast().getIdentifier());
						reports.add(((MolecularNode) nodess.getLast())
								.getCableSet().getCable("plan").getNodeSet()
								.getNode(0));
					}
				}
			}
		}
	}

	private static void excutePrimaction(Node a) 
	{

		 ((Action) a.getEntity()).Perform();
	}

	private static void scheduleBelieveEffects(LinkedList<Node> effectsList,Network m) throws CustomException 
	{
		
		Relation r1 = m.getRelation("action");
		Relation r2 = m.getRelation("actObject");
		
		CaseFrame x = m.getCaseFrame("actObject,action");
		
		
			Object[][] o20 = new Object[2][2];

			o20[0][0] = r1;
			o20[1][0] = r2;
			Node bn21 = m.build((String)effects.getFirst().toString());
			o20[0][1] = bn21;
			o20[1][1] = effects.getFirst();

			try {
				Act.attach(bn21,"Achieve");
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

			performSNePS(yy,m);
			
			m.removeNode(yy);
			m.removeNode(bn21);
			}
	

	private static void schedulePreconditions(LinkedList<Node> preConditions,Network m)
			throws CustomException {

		
		Relation r1 = m.getRelation("action");
		Relation r2 = m.getRelation("actObject");
		
		CaseFrame x = m.getCaseFrame("actObject,action");
		
		
			Object[][] o20 = new Object[2][2];

			o20[0][0] = r1;
			o20[1][0] = r2;
			Node bn21 = m.build((String)preConditions.getFirst().toString());
			o20[0][1] = bn21;
			o20[1][1] = preConditions.getFirst();

			try {
				Act.attach(bn21,"Achieve");
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
			
			performSNePS(yy,m);
	
			m.removeNode(yy);
			m.removeNode(bn21);
		
	}

	@SuppressWarnings("unchecked")
	private static void findEffects(Node a,Network m) {

		LinkedList<Node> nodess = new LinkedList<Node>();
		java.util.Enumeration keys = m.getNodes().keys();
		while (keys.hasMoreElements())

		{
			Object aKey = keys.nextElement();
			Object aValue = m.getNodes().get(aKey);
			nodess.add((Node) aValue);

			if (!nodess.getLast().getClass().getSimpleName().equals("BaseNode")) {
				if (((MolecularNode) nodess.getLast()).getCableSet().contains(
						"effect")
						&& ((MolecularNode) nodess.getLast()).getCableSet()
								.contains("act")) {
					if (((MolecularNode) nodess.getLast()).getCableSet()
							.getCable("act").getNodeSet().getNode(0)
							.getIdentifier().equals(a.getIdentifier())) {
						reports.add(((MolecularNode) nodess.getLast())
								.getCableSet().getCable("effect").getNodeSet()
								.getNode(0));
					}
				}
			}
		}

	}

	private static boolean tallyPreconditions(LinkedList<Node> preConditions,
			LinkedList<Node> reports) {
		if (preConditions.size() == reports.size()) {
			return true;
		} else
			return false;

	}

	private static void testPreconditions() {

		for (int i = 0; i < preConditions.size(); i++) {

			if (testConditions(preConditions.get(i))) {
				reports.add(preConditions.get(i));

			} else {
				System.out.println("Pre-Condition :"
						+ preConditions.get(i).getIdentifier()
						+ " "+"is not Asserted");
				unAchievedPreConditions.add(preConditions.get(i));
			}
		}

	}

	@SuppressWarnings("unchecked")
	private static void findPreconditions(Node a, Network m) {
		
		LinkedList<Node> nodess = new LinkedList<Node>();
		java.util.Enumeration keys = m.getNodes().keys();
	
		while (keys.hasMoreElements())

		{
			Object aKey = keys.nextElement();
			Object aValue = m.getNodes().get(aKey);
			nodess.add((Node) aValue);

			if (!nodess.getLast().getClass().getSimpleName().equals("BaseNode")) {

				if (((MolecularNode) nodess.getLast()).getCableSet().contains(
						"precondition")
						&& ((MolecularNode) nodess.getLast()).getCableSet()
								.contains("act")) {
				
					if (((MolecularNode) nodess.getLast()).getCableSet()
							.getCable("act").getNodeSet().getNode(0)
							.getIdentifier().equals(a.getIdentifier())) {
						//System.out.println(nodess.getLast().getIdentifier());
						reports.add(((MolecularNode) nodess.getLast())
								.getCableSet().getCable("precondition")
								.getNodeSet().getNode(0));
					}
				}
			}
		}
	}

	public static boolean testConditions(Node x) {
		if (test.testConditions(x)) {
		
			return true;
		} else {

			return false;
		}
	}

	public void setTheAction(Node theAction) {
		this.theAction = theAction;

	}

	public void addNode(Node e) {
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

	@SuppressWarnings("static-access")
	public void setPreConditions(LinkedList<Node> preConditions) {
		this.preConditions = preConditions;
	}

	public LinkedList<Node> getPreConditions() {
		return preConditions;
	}

	@SuppressWarnings("static-access")
	public void setReports(LinkedList<Node> reports) {
		this.reports = reports;
	}

	public LinkedList<Node> getReports() {
		return reports;
	}

	@SuppressWarnings("static-access")
	public void setEffects(LinkedList<Node> effects) {
		this.effects = effects;
	}

	public LinkedList<Node> getEffects() {
		return effects;
	}

	@SuppressWarnings("static-access")
	public void setPlans(LinkedList<Node> plans) {
		this.plans = plans;
	}

	public LinkedList<Node> getPlans() {
		return plans;
	}

	public LinkedList<Node> getUnAchievedPreConditions() {
		return unAchievedPreConditions;
	}

	public void setPrimaction(boolean primaction) {
		this.primaction = primaction;
	}

	public boolean getPrimaction() {
		return primaction;
	}

	@SuppressWarnings("static-access")
	public void setUnAchievedPreConditions(
			LinkedList<Node> unAchievedPreConditions) {
		this.unAchievedPreConditions = unAchievedPreConditions;
	}

	public static void performSNePS(Node n,Network m) throws CustomException
	{

		while(!Queue.isEmpty())
				{	
				Intend(n,m);
				performSNePS(Queue.stackPop(),m);
					
			}
	}
	@SuppressWarnings("unchecked")
	public static Object attach(Node n,String s) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
	         
	try 
	{
	  Class cl = Class.forName("snactor."+s);  
	  java.lang.reflect.Constructor constructor =
          cl.getConstructor(new Class[] {Node.class});
	  Object x =
          constructor.newInstance
            (n);
	  n.setEntity((Entity) x);
	  return x;
	  }
	catch (Exception e) {
	  e.printStackTrace();
	 return null;
	}
	}

	
	}


