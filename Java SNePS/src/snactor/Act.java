package snactor;

import java.util.LinkedList;

import java.lang.reflect.*;

import sneps.*;

@SuppressWarnings("unused")
public class Act extends Entity {
//	private Network m;

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

		setTheAction(nodes.getNodes().getFirst());

	}

	private Node getMainObject() {
		MolecularNode m = (MolecularNode) getNode();

		if (m.getCableSet().contains("actObject")) {
			Node MainObject = m.getCableSet().getCable("actObject")
					.getNodeSet().getNodes().getFirst();
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
						.getNodes().getFirst());
				getObjects(m2.getCableSet().getCable("object2").getNodeSet()
						.getNodes().getFirst());
			} else {
				addNode(mainNode);
			}

		}

	}

	public static void excute(Node a,Network m) throws CustomException { 
		
		if (((Act) a.getEntity()).getAgenda().equals("start")) {
			Queue.stackPop();
			if(Queue.isEmpty())
			{System.out.println("mxmxmx");}
			((Act) a.getEntity()).setAgenda("find-preconditions");
			Queue.stackPush(a);
			findPreconditions(a, m);
			
		} else if (((Act) a.getEntity()).getAgenda().equals(
				"find-preconditions")) {
			Queue.stackPop();
			System.out.println("2");
			if (!reports.isEmpty()) {
				System.out.println("22");
				((Act) a.getEntity()).setPreConditions(reports);
			
				((Act) a.getEntity()).setReports(new LinkedList<Node>());
				((Act) a.getEntity())
						.setUnAchievedPreConditions(new LinkedList<Node>());
				((Act) a.getEntity()).setAgenda("test-preconditions");
				Queue.stackPush(a);
				testPreconditions();
				if(!preConditions.isEmpty())
				{
				System.out.println("a7a");
				}
			} else {
				((Act) a.getEntity()).setAgenda("find-effects");
				Queue.stackPush(a);
				findEffects(a,m);
			}
		} else if (((Act) a.getEntity()).getAgenda().equals(
				"test-preconditions")) {
			Queue.stackPop();
			
			if (pretrialcount < 5) {
				if (tallyPreconditions(preConditions, reports)) {
					System.out.println("a7a");

					((Act) a.getEntity()).setReports(new LinkedList<Node>());
					((Act) a.getEntity()).setAgenda("find-effects");
					Queue.stackPush(a);
					findEffects(a,m);
				} else {
					((Act) a.getEntity()).setAgenda("start");
					((Act) a.getEntity()).setReports(new LinkedList<Node>());
					Queue.stackPush(a);
					schedulePreconditions(unAchievedPreConditions,m);
					pretrialcount++;
				}
			} else {
				System.out
						.println("Preconditions Not Satisfied, Action Aborted!");
			}

		} else if (((Act) a.getEntity()).getAgenda().equals("find-effects")) {
			Queue.stackPop();
			System.out.println("3");
			pretrialcount = 0;
			if (!reports.isEmpty()) {
				((Act) a.getEntity()).setEffects(reports);
				((Act) a.getEntity()).setReports(new LinkedList<Node>());
				scheduleBelieveEffects(((Act) a.getEntity()).getEffects(),m);
			}
				((Act) a.getEntity()).setAgenda("excute");
				Queue.stackPush(a);
			
			
		} else if (((Act) a.getEntity()).getAgenda().equals("excute")) {
			Queue.stackPop();
			// This will change to primitive check
			if (((Act) a.getEntity()).getPrimaction()) {
				excutePrimaction(((Act) a.getEntity()).getTheAction());
				((Act) a.getEntity()).setAgenda("done");
			} else {
				((Act) a.getEntity()).setReports(new LinkedList<Node>());
				((Act) a.getEntity()).setAgenda("find-plans");
				Queue.stackPush(a);
				findPlans(a,m);
			}
		} else if (((Act) a.getEntity()).getAgenda().equals("find-plans")) {
			Queue.stackPop();
			if (!reports.isEmpty())
				((Act) a.getEntity()).setPlans(plans);
			((Act) a.getEntity()).setReports(new LinkedList<Node>());
			
		//	schedulePlans(((Act) a.getEntity()).getPlans());
		} else {
			System.out.println("No plans found!");
			
		}
	}

	private static void schedulePlans(LinkedList<Node> planList) throws CustomException
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
							.getCable("act").getNodeSet().getNodes().getFirst()
							.getIdentifier().equals(a.getIdentifier())) {
						//System.out.println(nodess.getLast().getIdentifier());
						reports.add(((MolecularNode) nodess.getLast())
								.getCableSet().getCable("plan").getNodeSet()
								.getNodes().getFirst());
					}
				}
			}
		}
	}

	private static void excutePrimaction(Node a) 
	{
		System.out.println(a.getIdentifier());
		 ((Action) a.getEntity()).Perform();
	}

	private static void scheduleBelieveEffects(LinkedList<Node> effectsList,Network m) throws CustomException 
	{
		
		Relation r1 = m.getRelation("action");
		Relation r2 = m.getRelation("actObject");

		CaseFrame cf1 = m.getCaseFrame("act");
		
			for (int i = 0; i < effects.size(); i++) {
				Object[][] o4 = new Object[2][2];

				o4[0][0] = r1;
				o4[1][0] = r2;
				Node bn7 = m.build("effectBTest");
				Node bn8 = effectsList.get(i);
				o4[0][1] = bn7;
				o4[1][1] = bn8;

				BELIEVE b1 = new BELIEVE(bn7);
				bn7.setEntity(b1);

				Node zz = m.build(o4, cf1);
				((Act) zz.getEntity()).setAgenda("start");
				((Act) zz.getEntity()).setPrimaction(true);
				performSNePS(zz,m);
			}
	}

	private static void schedulePreconditions(LinkedList<Node> preConditions,Network m)
			throws CustomException {


		Relation r1 = m.getRelation("action");
		Relation r2 = m.getRelation("actObject");

		CaseFrame cf1 = m.getCaseFrame("act");

		for (int i = 0; i < preConditions.size(); i++) 
		{
			Object[][] o4 = new Object[2][2];

			o4[0][0] = r1;
			o4[1][0] = r2;
			Node bn7 = m.build("AchievePreCond");
			Node bn8 = preConditions.get(i);
			o4[0][1] = bn7;
			o4[1][1] = bn8;

			Achieve aa = new Achieve(bn7);
			bn7.setEntity(aa);

			Node yy = m.build(o4, cf1);
			
			((Act) yy.getEntity()).setAgenda("start");
			((Act) yy.getEntity()).setPrimaction(true);
			performSNePS(yy,m);
		}
		/*LinkedList<Act> achieveActs = new LinkedList<Act>();
		Node achieveActProcess = null;

		@SuppressWarnings("unused")
		Node findActionNode;
		
		Relation r1 = m.defineRelation("action", "single", null, 1);
		Relation r2 = m.defineRelation("actObject", "single", null, 0);

	
		LinkedList<Relation> l = new LinkedList<Relation>();
		l.add(r1);
		l.add(r2);

		CaseFrame cf1 = m.defineCaseFrame("act", l);

		for (int i = 0; i < unAchievedPreConditions.size(); i++) {
			Object[][] o4 = new Object[2][2];

			o4[0][0] = r1;
			o4[1][0] = r2;
			Node bn7 = m.build("SNACTION");
			Node bn8 = unAchievedPreConditions.get(i);
			o4[0][1] = bn7;
			o4[1][1] = bn8;

			Achieve aa = new Achieve(bn8);
			bn8.setEntity(aa);

			Node a = m.build(o4, cf1);

			Act achieveAct = new Act((MolecularNode) a);
			a.setEntity(achieveAct);

			achieveActs.add(achieveAct);
		}

		Object[][] o4 = new Object[2][2];

		o4[0][0] = r1;
		o4[1][0] = r2;
		Node bn7 = m.build("SNACTION");
		Node bn8 = unAchievedPreConditions.getFirst();
		o4[0][1] = bn7;
		o4[1][1] = bn8;

		Achieve aa = new Achieve(bn8);
		bn8.setEntity(aa);

		Node a = m.build(o4, cf1);

		Act achieveAct = new Act((MolecularNode) a);
		a.setEntity(achieveAct);

		((Act) achieveActProcess.getEntity()).setPriority("intend");
		((Act) achieveActProcess.getEntity()).setAgenda("start");
		Queue.stackPush(achieveActProcess);
*/
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
							.getCable("act").getNodeSet().getNodes().getFirst()
							.getIdentifier().equals(a.getIdentifier())) {
						System.out.println(nodess.getLast().getIdentifier());
						reports.add(((MolecularNode) nodess.getLast())
								.getCableSet().getCable("effect").getNodeSet()
								.getNodes().getFirst());
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
						+ "is not Asserted");
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
							.getCable("act").getNodeSet().getNodes().getFirst()
							.getIdentifier().equals(a.getIdentifier())) {
						System.out.println(nodess.getLast().getIdentifier());
						reports.add(((MolecularNode) nodess.getLast())
								.getCableSet().getCable("precondition")
								.getNodeSet().getNodes().getFirst());
					}
				}
			}
		}
	}

	public static boolean testConditions(Node x) {
		if (test.testConditions(x)) {
			// reports.add(x);
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
//		excute(n,m);
//	
		while(!Queue.isEmpty())
				{	
				excute(n,m);
				performSNePS(n,m);
					
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
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws CustomException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		// Condition test = new Condition();
		Condition test = new Condition();
		
		Network m = new Network();
		
		Relation r1 = m.defineRelation("action", "single", null, 1);
		Relation r2 = m.defineRelation("actObject", "single", null, 0);
		Relation r3 = m.defineRelation("object1", "single", null, 1);
		Relation r4 = m.defineRelation("object2", "single", null, 0);
		
		
		LinkedList<Relation> l = new LinkedList<Relation>();
		l.add(r1);
		l.add(r2);
		
		CaseFrame cf1 = m.defineCaseFrame("act", l);
		
		LinkedList<Relation> l2 = new LinkedList<Relation>();
		l2.add(r3);
		l2.add(r4);
		
		CaseFrame cf2 = m.defineCaseFrame("object", l2);
		
		Object[][] o5 = new Object[2][2];
		
		o5[0][0] = r3;
		o5[1][0] = r4;
		Node bn9 = m.build("3");
		Node bn8 = m.build("4");
		o5[0][1] = bn9;
		o5[1][1] = bn8;

		Node n5 = m.build(o5, cf2);
		
		Object[][] o4 = new Object[2][2];
		
		o4[0][0] = r1;
		o4[1][0] = r2;
		Node bn7 = m.build("SNACTION");
	//	Node bn8 = m.build("MainSN");
		o4[0][1] = bn7;
		o4[1][1] = n5;

		Node n4 = m.build(o4, cf1);
		
		Object[][] o3 = new Object[2][2];
		
		o3[0][0] = r3;
		o3[1][0] = r4;
		Node bn5 = m.build("SecondObject");
	//	Node bn6 = m.build("ThirdObject");
		o3[0][1] = bn5;
		o3[1][1] = n4;
		
		
		SNSEQUENCE aa2  = new SNSEQUENCE(n4);
		n4.setEntity(aa2);
		
		Action aa3  = new Action(bn5);
		bn5.setEntity(aa3);
		
	
		
		Node n3 = m.build(o3, cf2);
		
		Object[][] o2 = new Object[2][2];
		
		o2[0][0] = r3;
		o2[1][0] = r4;
		Node bn3 = m.build("FirstObject");
		//Node bn4 = m.build("SecondObject");
		o2[0][1] = bn3;
		o2[1][1] = n3;
		
		Action aa1 = new Action(bn3);
		bn3.setEntity(aa1);
		
		Node n2 = m.build(o2, cf2);
	
		
		Object[][] o = new Object[2][2];
		
		o[0][0] = r1;
		o[1][0] = r2;
		Node bn1 = m.build("DoAll");
		
	//	Node bn2 = m.build("theMainObject");
		o[0][1] = bn1;
		o[1][1] = n2;
		DoAll aa  = new DoAll(bn1);
		bn1.setEntity(aa);
		
		Node n1 = m.build(o, cf1);
			
		Act a = new Act((MolecularNode) n1);
		n1.setEntity(a);
		a.setPrimaction(true);
		a.setAgenda("start");
		aa.Perform();
		//
		Queue.stackPush(n1);
		a.performSNePS(n1, m);
	/*	Network m = new Network();

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
		// Node bn6 = m.build("actnode");
		o3[0][1] = bn5;
		o3[1][1] = n4;

		//
		// Action aa3 = new Action(bn6);
		// bn6.setEntity(aa3);
	
		Node n3 = m.build(o3, cf2);

		Object[][] o = new Object[2][2];

		o[0][0] = r1;
		o[1][0] = r2;
		Node bn1 = m.build("SNIF");
		// Node bn2 = m.build("theMainObject");
		o[0][1] = bn1;
		o[1][1] = n3;

	//	BELIEVE aa = new BELIEVE(bn1);
	//	bn1.setEntity(aa);
		Action x = (Action) attach(bn1, "SNIF");
		
		
		Node n1 = m.build(o, cf1);*/

	/*	Act a = new Act((MolecularNode) n1);
		n1.setEntity(a);
		a.setAgenda("start");
		
		System.out.println(a.getAgenda());*/
		
	//	System.out.println(SNIF.class.getName());
	//	x.Perform();
	//	aa.Perform();
	//	a.findPreconditions(bn1, m);
		// traversing hash table!!

		/*
		 * LinkedList<Node> nodess = new LinkedList<Node>();
		 * java.util.Enumeration keys = m.getNodes().keys(); while(
		 * keys.hasMoreElements() )
		 * 
		 * { Object aKey = keys.nextElement(); Object aValue =
		 * m.getNodes().get(aKey); nodess.add((Node) aValue);
		 * if(!nodess.getLast().getClass().getSimpleName().equals("BaseNode")) {
		 * if(((MolecularNode)
		 * nodess.getLast()).getCableSet().contains("action")&&
		 * ((MolecularNode)nodess
		 * .getLast()).getCableSet().contains("actObject"))
		 * System.out.println(nodess.getLast().getIdentifier()); } }
		 */
	}

}
