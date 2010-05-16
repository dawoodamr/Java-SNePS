package snactor;

import java.util.LinkedList;

import java.lang.reflect.*;

import sneps.*;

@SuppressWarnings("unused")
public class Example1
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
		
		Object[][] o90 = new Object[2][2]; 
		
		o90[0][0] = r3;
		o90[1][0] = r4;
		Node bn901 = m.build("A");
		Node bn902 = m.build("B");
		o90[0][1] = bn901;
		o90[1][1] = bn902;
		
		
		Node n90 = m.build(o90, cf2);
		
		Object[][] o91 = new Object[2][2]; 
		
		o91[0][0] = r1;
		o91[1][0] = r2;
		Node bn911 = m.build("PUT");
		o91[0][1] = bn911;
		o91[1][1] = n90;
		
		
		Node n91 = m.build(o91, cf1);
		
		Act.attach(bn911,"PUT");
		
		Act a91 = new Act((MolecularNode) n91);
		n91.setEntity(a91);
		
		a91.setPrimaction(true);
		
		Object[][] o92 = new Object[2][2]; 
		
		o92[0][0] = r3;
		o92[1][0] = r4;
		Node bn922 = m.build("C");
		o92[0][1] = bn902;
		o92[1][1] = bn922;
		
		
		Node n92 = m.build(o92, cf2);
		
		Object[][] o93 = new Object[2][2]; 
		
		o93[0][0] = r1;
		o93[1][0] = r2;
		Node bn961 = m.build("PUT#2");
		Act.attach(bn961,"PUT");
		o93[0][1] = bn961;
		o93[1][1] = n92;
		
		
		Node n93 = m.build(o93, cf1);
	
		
		Act a93 = new Act((MolecularNode) n93);
		n93.setEntity(a93);
		
		a93.setPrimaction(true);
		
		Object[][] o94 = new Object[2][2]; 
		
		o94[0][0] = r3;
		o94[1][0] = r4;
		Node bn942 = m.build("Table");
		o94[0][1] = bn922;
		o94[1][1] = bn942;
		
		
		Node n94 = m.build(o94, cf2);
		
		Object[][] o95 = new Object[2][2]; 
		
		o95[0][0] = r1;
		o95[1][0] = r2;
		Node bn9981 = m.build("PUT#3");
		Act.attach(bn9981,"PUT");
		o95[0][1] = bn9981;
		o95[1][1] = n94;
		
		
		Node n95 = m.build(o95, cf1);
		
		Act a95 = new Act((MolecularNode) n95);
		n95.setEntity(a95);
		
		a95.setPrimaction(true);
		
		Object[][] o96 = new Object[2][2]; 
		
		o96[0][0] = r3;
		o96[1][0] = r4;
		o96[0][1] = n93;
		o96[1][1] = n91;
		
		Node n96 = m.build(o96, cf2);
		
		Object[][] o97 = new Object[2][2]; 
		
		o97[0][0] = r3;
		o97[1][0] = r4;
		o97[0][1] = n95;
		o97[1][1] = n96;
		
		Node n97 = m.build(o97, cf2);
		
		Object[][] o98 = new Object[2][2]; 
		
		o98[0][0] = r1;
		o98[1][0] = r2;
		Node bn981 = m.build("SNSEQUENCE");
		o98[0][1] = bn981;
		o98[1][1] = n97;
		
		Node n98 = m.build(o98, cf1);
		
		Act.attach(bn981,"SNSEQUENCE");
		
		Act a98 = new Act((MolecularNode) n98);
		n98.setEntity(a98);
		
		a98.setPrimaction(true);
		
		
		
		Object[][] o104 = new Object[2][2]; 
		
		o104[0][0] = r1;
		o104[1][0] = r2;
		Node bn1041= m.build("PILE");
		Node bn1042= m.build("O");
		o104[0][1] = bn981;
		o104[1][1] = bn1042;
		
		Node n104 = m.build(o104, cf1);
		
		
		Act a104 = new Act((MolecularNode) n104);
		n104.setEntity(a104);
		
		a104.setPrimaction(false);
		
		Object[][] o103 = new Object[2][2];
		
		o103[0][0] = r5;
		o103[1][0] = r7;
		o103[0][1] = n104;
		o103[1][1] = n98;
		
		Node n103 = m.build(o103, plancf);
		
		
		
	//	test.conditions.add(bn991);
		
		a104.setAgenda("start");
		Queue.stackPush(n104);
		a104.performSNePS(n104, m);
		
	}
	
}