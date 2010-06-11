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
		
		Relation r1 = m.defineRelation("action", "Action", "none", 1);
		Relation r2 = m.defineRelation("actObject", "Entity", "none", 0);
		Relation r3 = m.defineRelation("object1", "Entity", "none", 1);
		Relation r4 = m.defineRelation("object2", "Entity", "none", 1);
		Relation r5 = m.defineRelation("act", "Act", "none", 1);
		Relation r6 = m.defineRelation("condition", "Entity", "none", 1);
		Relation r7 = m.defineRelation("plan", "Entity", "none", 1);
		Relation r8 = m.defineRelation("precondition", "Entity", "none", 1);
		Relation r9 = m.defineRelation("effect", "Entity", "none", 1);
		Relation r10 = m.defineRelation("goal", "Entity", "none", 1);
		Relation r11 = m.defineRelation("do", "Entity", "none", 1);
		Relation r12 = m.defineRelation("vars", "Entity", "none", 1);
		Relation r13 = m.defineRelation("suchthat", "Entity", "none", 1);
		
		LinkedList<Relation> l = new LinkedList<Relation>();
		l.add(r1);
		l.add(r2);
		
		CaseFrame cf1 = m.defineCaseFrame("Act", l);
		
		LinkedList<Relation> l2 = new LinkedList<Relation>();
		l2.add(r3);
		l2.add(r4);
		
		CaseFrame cf2 = m.defineCaseFrame("Entity", l2);
		
		LinkedList<Relation> l3 = new LinkedList<Relation>();
		l3.add(r7);
		l3.add(r5);
		
		CaseFrame plancf = m.defineCaseFrame("Entity", l3);
		
		LinkedList<Relation> l4 = new LinkedList<Relation>();
		l4.add(r8);
		l4.add(r5);
		
		CaseFrame precf = m.defineCaseFrame("Entity", l4);
		
		LinkedList<Relation> l5 = new LinkedList<Relation>();
		l5.add(r9);
		l5.add(r5);
		
		CaseFrame effectcf = m.defineCaseFrame("Entity", l5);
		
		LinkedList<Relation> l6 = new LinkedList<Relation>();
		l6.add(r7);
		l6.add(r10);
		
		CaseFrame planGoal = m.defineCaseFrame("Entity", l6);
		
		LinkedList<Relation> l7 = new LinkedList<Relation>();
		l7.add(r11);
		l7.add(r12);
		l7.add(r13);
		
		CaseFrame with = m.defineCaseFrame("Entity", l7);
		
		LinkedList<Relation> l8 = new LinkedList<Relation>();
		l8.add(r5);
		l8.add(r6);
		
		
		CaseFrame guarded = m.defineCaseFrame("Entity", l8);
		
	/*
	 * Start Network
	 */
		
		
		Object[][] o99 = new Object[2][2];
		 
		o99[0][0] = r3;
		o99[1][0] = r4;
		Node bn991 = m.build("B");
		Node bn992 = m.build("A");
		o99[0][1] = bn991;
		o99[1][1] = bn992;
				
		Node n99 = m.build(o99, cf2);
		
		Object[][] o98 = new Object[2][2];
		 
		o98[0][0] = r1;
		o98[1][0] = r2;
		Node bn981 = m.build("PUT#1");
		o98[0][1] = bn981;
		o98[1][1] = n99;
		
		Act.attach(bn981,"PUT");
		
		Node n98 = m.build(o98, cf1);
		
		
		
		((Act) n98.getEntity()).setPrimaction(true);
		
		Object[][] o97 = new Object[2][2];
		 
		o97[0][0] = r3;
		o97[1][0] = r4;
		Node bn971 = m.build("Table");
		o97[0][1] = bn992;
		o97[1][1] = bn971;
				
		Node n97 = m.build(o97, cf2);
		
		Object[][] o96 = new Object[2][2];
		 
		o96[0][0] = r1;
		o96[1][0] = r2;
		Node bn961 = m.build("PUT#2");
		o96[0][1] = bn961;
		o96[1][1] = n97;
		Act.attach(bn961,"PUT");
		
		Node n96 = m.build(o96, cf1);
		
		
		((Act) n96.getEntity()).setPrimaction(true);
		
		Object[][] o95 = new Object[2][2];
		 
		o95[0][0] = r3;
		o95[1][0] = r4;
		o95[0][1] = n98;
		o95[1][1] = n96;
				
		Node n95 = m.build(o95, cf2);
		
		Object[][] o94= new Object[2][2];
		 
		o94[0][0] = r1;
		o94[1][0] = r2;
		Node bn941 = m.build("PILE(SNSEQUENCE)");
		o94[0][1] = bn941;
		o94[1][1] = n95;
		
		Act.attach(bn941,"SNSEQUENCE");
		
		Node n94 = m.build(o94, cf1);
		
	
		
		((Act) n94.getEntity()).setPrimaction(true);
		
		Object[][] o93 = new Object[2][2];
		
		o93[0][0] = r5;
		o93[1][0] = r8;
		Node bn931 = m.build("Table is Clear");
		o93[0][1] = n94;
		o93[1][1] = bn931;
		
		Node n93 = m.build(o93, precf);
		
		Object[][] o92 = new Object[2][2];
		 
		
		o92[0][0] = r1;
		o92[1][0] = r2;
		Node bn921 = m.build("Believe#1");
		o92[0][1] = bn921;
		o92[1][1] = bn931;
		
		Act.attach(bn921,"BELIEVE");
		
		Node n92 = m.build(o92, cf1);
		
		
		
		((Act) n92.getEntity()).setPrimaction(true);
		
		Object[][] o91 = new Object[2][2];
		
		o91[0][0] = r7;
		o91[1][0] = r10;
		o91[0][1] = n92;
		o91[1][1] = bn931;
		
		Node n91 = m.build(o91, planGoal);
		
		
		Object[][] o90 = new Object[2][2];
		
		o90[0][0] = r5;
		o90[1][0] = r9;
		Node bn901 = m.build("Table is not clear");
		o90[0][1] = n94;
		o90[1][1] = bn901;
		
		Node n90 = m.build(o90, effectcf);
		
		((Act) n94.getEntity()).setAgenda("start");
		Queue.stackPush(n94);
		((Act) n94.getEntity()).performSNePS(n94, m);
		
		
	/*	Object[][] o20 = new Object[2][2];
		 
		
		
		o20[0][0] = r1;
		o20[1][0] = r2;
		Node bn91 = m.build("k1");
		Node bn92 = m.build("ZZ");
		o20[0][1] = bn91;
		o20[1][1] = bn92;
		
		Act.attach(bn91,"DISBELIEVE");
		
		Node n20 = m.build(o20, cf1);
		
		Act a20 = new Act((MolecularNode) n20);
		n20.setEntity(a20);
		
		a20.setPrimaction(true);
		
		Object[][] o18 = new Object[2][2];
		 
		
		o18[0][0] = r5;
		o18[1][0] = r6;
		Node bn81 = m.build("Z");
		o18[0][1] = n20;
		o18[1][1] = bn81;
		
	//	Act.attach(bn71,"SNIF");
		
		Node n18 = m.build(o18, guarded);
		
		Object[][] o8 = new Object[2][2];
		 
		
		o8[0][0] = r1;
		o8[1][0] = r2;
		Node bn7 = m.build("mesh");
	//	Node bn8 = m.build("Printdah");
		o8[0][1] = bn7;
		o8[1][1] = n18;
		
		Act.attach(bn7,"SNIF");
		
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
		
		
		test.conditions.add(bn81);
	//	test.conditions.add(bn92);		
//		a5.setAgenda("start");
//		Queue.stackPush(n5);
//		a5.performSNePS(n5, m);
		
		a1.setAgenda("start");
		Queue.stackPush(n1);
		a1.performSNePS(n1, m);
		
		*/
		
		
	}
	
}