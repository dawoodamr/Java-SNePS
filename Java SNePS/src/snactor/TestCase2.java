package snactor;

import java.util.LinkedList;

import java.lang.reflect.*;

import sneps.*;

@SuppressWarnings("unused")
public class TestCase2
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
		Relation r14 = m.defineRelation("property", "Entity", null, 1);
		Relation r15 = m.defineRelation("object", "Entity", null, 1);
		
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
		
		LinkedList<Relation> l9 = new LinkedList<Relation>();
		l9.add(r14);
		l9.add(r15);
		
		
		CaseFrame property = m.defineCaseFrame("Entity", l9);
		
	/*
	 * Start Network
	 */
		
		Object[][] o93 = new Object[2][2];
		
		o93[0][0] = r14;
		o93[1][0] = r15;
		Node bn931 = m.build("Clear");
		Node bn932 = m.build("Table");
		o93[0][1] = bn931;
		o93[1][1] = bn932;
		
		Node n93 = m.build(o93, property);
		
		Object[][] o92 = new Object[2][2];
		 
		
		o92[0][0] = r1;
		o92[1][0] = r2;
		Node bn921 = m.build("Believe#1");
		o92[0][1] = bn921;
		o92[1][1] = n93;
		
		Act.attach(bn921,"BELIEVE");
		
		Node n92 = m.build(o92, cf1);
		
		Act a92 = new Act((MolecularNode) n92);
		n92.setEntity(a92);
		
		a92.setPrimaction(true);
		
		a92.setAgenda("start");
		Queue.stackPush(n92);
		a92.performSNePS(n92, m);
		
		m.removeNode(n92);
		m.removeNode(n93);
		
	///////////////////////////////////////////////////////////////	
		
		
		Object[][] o90 = new Object[2][2]; 
		
		o90[0][0] = r1;
		o90[1][0] = r2;
		Node bn901 = m.build("testerNode");
		Node bn902 = m.build("X");
		o90[0][1] = bn901;
		o90[1][1] = bn902;
		
		Act.attach(bn901,"tester");
		
		Node n90 = m.build(o90, cf1);
		
		Act a90 = new Act((MolecularNode) n90);
		n90.setEntity(a90);
		
		
		
		Object[][] o95 = new Object[2][2]; 
		
		o95[0][0] = r14;
		o95[1][0] = r15;
		Node bn951 = m.build("Clear");
		o95[0][1] = bn951;
		o95[1][1] = bn902;
		
		Node n95 = m.build(o95, property);
		
		Object[][] o91 = new Object[4][2]; 
		
		o91[0][0] = r1;
		o91[1][0] = r11;
		o91[2][0] = r12;
		o91[3][0] = r13;
		Node bn911 = m.build("Withsome#1");
		o91[0][1] = bn911;
		o91[1][1] = n90;
		o91[2][1] = bn902;
		o91[3][1] = n95;
		
		Act.attach(bn911,"WITHSOME");
		
		Node n91 = m.build(o91, with);
		
		Act a91 = new Act((MolecularNode) n91);
		n91.setEntity(a91);
		
		a91.setPrimaction(true);
		
		Object[][] o97 = new Object[2][2]; 
		
		o97[0][0] = r5;
		o97[1][0] = r8;
		Node bn971 = m.build("condition");
		o97[0][1] = n91;
		o97[1][1] = bn971;
		
		Node n97 = m.build(o97, precf);
		
		Object[][] o98 = new Object[2][2];
		 
		
		o98[0][0] = r1;
		o98[1][0] = r2;
		Node bn981 = m.build("Believe#1");
		o98[0][1] = bn981;
		o98[1][1] = bn971;
		
		Act.attach(bn981,"BELIEVE");
		
		Node n98 = m.build(o98, cf1);
		
		Act a98 = new Act((MolecularNode) n98);
		n98.setEntity(a98);
		
		a98.setPrimaction(true);
		
		Object[][] o99 = new Object[2][2];
		
		o99[0][0] = r7;
		o99[1][0] = r10;
		o99[0][1] = n98;
		o99[1][1] = bn971;
		
		Node n99 = m.build(o99, planGoal);
		
		a91.setAgenda("start");
		Queue.stackPush(n91);
		a91.performSNePS(n91, m);
		
	}
	
}