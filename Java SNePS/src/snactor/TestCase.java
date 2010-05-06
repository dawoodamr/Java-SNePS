package snactor;

import java.util.LinkedList;

import java.lang.reflect.*;

import sneps.*;

@SuppressWarnings("unused")
public class TestCase
{

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws CustomException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		Condition test = new Condition();
		
		Network m = new Network();
		
		Relation r1 = m.defineRelation("action", "single", null, 1);
		Relation r2 = m.defineRelation("actObject", "single", null, 0);
		Relation r3 = m.defineRelation("object1", "single", null, 1);
		Relation r4 = m.defineRelation("object2", "single", null, 1);
		Relation r5 = m.defineRelation("act", "single", null, 1);
		Relation r6 = m.defineRelation("condition", "single", null, 1);
		Relation r7 = m.defineRelation("plan", "single", null, 1);
		Relation r8 = m.defineRelation("precondition", "single", null, 1);
		Relation r9 = m.defineRelation("effect", "single", null, 1);
		Relation r10 = m.defineRelation("goal", "single", null, 1);
		Relation r11 = m.defineRelation("do", "single", null, 1);
		Relation r12 = m.defineRelation("vars", "single", null, 1);
		Relation r13 = m.defineRelation("suchthat", "single", null, 1);
		
		LinkedList<Relation> l = new LinkedList<Relation>();
		l.add(r1);
		l.add(r2);
		
		CaseFrame cf1 = m.defineCaseFrame("act", l);
		
		LinkedList<Relation> l2 = new LinkedList<Relation>();
		l2.add(r3);
		l2.add(r4);
		
		CaseFrame cf2 = m.defineCaseFrame("object", l2);
		
		LinkedList<Relation> l3 = new LinkedList<Relation>();
		l3.add(r7);
		l3.add(r5);
		
		CaseFrame plancf = m.defineCaseFrame("plan", l3);
		
		LinkedList<Relation> l4 = new LinkedList<Relation>();
		l4.add(r8);
		l4.add(r5);
		
		CaseFrame precf = m.defineCaseFrame("precondition", l4);
		
		LinkedList<Relation> l5 = new LinkedList<Relation>();
		l5.add(r9);
		l5.add(r5);
		
		CaseFrame effectcf = m.defineCaseFrame("effect", l5);
		
		LinkedList<Relation> l6 = new LinkedList<Relation>();
		l6.add(r7);
		l6.add(r10);
		
		CaseFrame planGoal = m.defineCaseFrame("goal", l6);
		
		LinkedList<Relation> l7 = new LinkedList<Relation>();
		l7.add(r11);
		l7.add(r12);
		l7.add(r13);
		
		CaseFrame with = m.defineCaseFrame("with", l7);
		
		LinkedList<Relation> l8 = new LinkedList<Relation>();
		l8.add(r5);
		l8.add(r6);
		
		
		CaseFrame guarded = m.defineCaseFrame("guardedact", l8);
		
	/*
	 * Start Network
	 */
		
		
		Object[][] o8 = new Object[2][2];
		 
		
		o8[0][0] = r1;
		o8[1][0] = r2;
		Node bn7 = m.build("mesh");
		Node bn8 = m.build("Printdah");
		o8[0][1] = bn7;
		o8[1][1] = bn8;
		
		Act.attach(bn7,"tester");
		
		Node n6 = m.build(o8, cf1);
		
		Act a6 = new Act((MolecularNode) n6);
		n6.setEntity(a6);
		
		a6.setPrimaction(true);
		
		Object[][] o5 = new Object[2][2];
		 
		
		o5[0][0] = r1;
		o5[1][0] = r2;
		Node bn1 = m.build("DoAll");
		o5[0][1] = bn1;
		o5[1][1] = n6;
		
		Act.attach(bn1,"DoAll");
		
		Node n1 = m.build(o5, cf1);
		
		Act a1 = new Act((MolecularNode) n1);
		n1.setEntity(a1);
		
		a1.setPrimaction(true);
		
		Object[][] o9 = new Object[2][2];
		
		o9[0][0] = r5;
		o9[1][0] = r8;
		Node bn3 = m.build("X");
		o9[0][1] = n1;
		o9[1][1] = bn3;
		
		Node n2 = m.build(o9, precf);
		
		Object[][] o19 = new Object[2][2];
		
		o19[0][0] = r5;
		o19[1][0] = r8;
		Node bn31 = m.build("XX");
		o19[0][1] = n1;
		o19[1][1] = bn31;
		
		Node n21 = m.build(o19, precf);
		
		Object[][] o11 = new Object[2][2];
		
		o11[0][0] = r5;
		o11[1][0] = r9;
		Node bn13 = m.build("Y");
		o11[0][1] = n1;
		o11[1][1] = bn13;
		
		Node n8 = m.build(o11, effectcf);
		
		
		Object[][] o6 = new Object[2][2];
		 
		
		o6[0][0] = r1;
		o6[1][0] = r2;
		Node bn4 = m.build("believe");
		o6[0][1] = bn4;
		o6[1][1] = bn3;
		
		Act.attach(bn4,"BELIEVE");
		
		Node n4 = m.build(o6, cf1);
		
		Act a2 = new Act((MolecularNode) n4);
		n4.setEntity(a2);
		
		a2.setPrimaction(true);
		
		Object[][] o16 = new Object[2][2];
		 
		
		o16[0][0] = r1;
		o16[1][0] = r2;
		Node bn41 = m.build("believe#3");
		o16[0][1] = bn41;
		o16[1][1] = bn31;
		
		Act.attach(bn41,"BELIEVE");
		
		Node n14 = m.build(o16, cf1);
		
		Act a14 = new Act((MolecularNode) n14);
		n14.setEntity(a14);
		
		a14.setPrimaction(true);
		
		Object[][] o17 = new Object[2][2];
		
		o17[0][0] = r7;
		o17[1][0] = r10;
		o17[0][1] = n14;
		o17[1][1] = bn31;
		
		Node n13 = m.build(o17, planGoal);
		
		Object[][] o7 = new Object[2][2];
		
		o7[0][0] = r7;
		o7[1][0] = r10;
		o7[0][1] = n4;
		o7[1][1] = bn3;
		
		Node n3 = m.build(o7, planGoal);
		
		Object[][] o13 = new Object[2][2];
		 
		
		o13[0][0] = r1;
		o13[1][0] = r2;
		Node bn14 = m.build("believe#2");
		o13[0][1] = bn14;
		o13[1][1] = bn13;
		
		Act.attach(bn14,"BELIEVE");
		
		Node n10 = m.build(o13, cf1);
		
		Act a10 = new Act((MolecularNode) n10);
		n10.setEntity(a10);
		
		a10.setPrimaction(true);
		
		Object[][] o12 = new Object[2][2];
		
		o12[0][0] = r7;
		o12[1][0] = r10;
		o12[0][1] = n10;
		o12[1][1] = bn13;
		
		Node n9 = m.build(o12, planGoal);
		
		
	//	test.conditions.add(bn31);
				
//		a5.setAgenda("start");
//		Queue.stackPush(n5);
//		a5.performSNePS(n5, m);
		
		a1.setAgenda("start");
		Queue.stackPush(n1);
		a1.performSNePS(n1, m);
	
		
	}
	
}