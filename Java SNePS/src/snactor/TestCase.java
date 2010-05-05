package snactor;

import java.util.LinkedList;

import java.lang.reflect.*;

import sneps.*;

public class TestCase
{

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws CustomException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		// Condition test = new Condition();
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
		Object[][] o5 = new Object[2][2];
		 
		
		o5[0][0] = r1;
		o5[1][0] = r2;
		Node bn1 = m.build("Achieve#1");
		Node bn2 = m.build("X");
		o5[0][1] = bn1;
		o5[1][1] = bn2;
		
		Act.attach(bn1,"BELIEVE");
		
		Node n1 = m.build(o5, cf1);
		
		Act a1 = new Act((MolecularNode) n1);
		n1.setEntity(a1);
		
		a1.setPrimaction(true);
		
		Object[][] o7 = new Object[2][2];
		
		o7[0][0] = r7;
		o7[1][0] = r10;
		o7[0][1] = n1;
		o7[1][1] = bn2;
		
		Node n2 = m.build(o7, planGoal);

		Object[][] o6 = new Object[2][2];
//		 
//			
			o6[0][0] = r1;
			o6[1][0] = r2;
			Node bn4 = m.build("Achieve#2");
			o6[0][1] = bn4;
			o6[1][1] = bn2;
//			
			Act.attach(bn4,"Achieve");
//			
			Node n3 = m.build(o6, cf1);
//			
			Act a2 = new Act((MolecularNode) n3);
			n3.setEntity(a2);
//			
			a2.setPrimaction(true);
			
//			a5.setAgenda("start");
//			Queue.stackPush(n5);
//			a5.performSNePS(n5, m);
			
			a2.setAgenda("start");
			Queue.stackPush(n3);
			a2.performSNePS(n3, m); 
			
			test.printem(); End of working Achieve*/
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
		
		//test.conditions.add(bn3);
		
		
		
		
//		a5.setAgenda("start");
//		Queue.stackPush(n5);
//		a5.performSNePS(n5, m);
		
		a1.setAgenda("start");
		Queue.stackPush(n1);
		a1.performSNePS(n1, m);
	//  test.printem();
	/* Start
		Object[][] o8 = new Object[2][2];
		 
		
		o8[0][0] = r1;
		o8[1][0] = r2;
		Node bn7 = m.build("mesh");
		Node bn8 = m.build("Printdah");
		o8[0][1] = bn7;
		o8[1][1] = bn8;
		
		Act.attach(bn7,"tester");
		
		Node n7 = m.build(o8, cf1);
		
		Act a7 = new Act((MolecularNode) n7);
		n7.setEntity(a7);
		
		a7.setPrimaction(true);
		
		Object[][] o22 = new Object[2][2];
		 
		
		o22[0][0] = r1;
		o22[1][0] = r2;
		Node bn6 = m.build("DoAll");
		o22[0][1] = bn6;
		o22[1][1] = n7;
		
		Act.attach(bn6,"DoAll");
		
		Node n6 = m.build(o22, cf1);
		
		Act a6 = new Act((MolecularNode) n6);
		n6.setEntity(a6);
		
		a6.setPrimaction(true);
	  
	 
		Object[][] o5 = new Object[2][2];
		 
			
			o5[0][0] = r1;
			o5[1][0] = r2;
			Node bn1 = m.build("Achieve#1");
			Node bn2 = m.build("X");
			o5[0][1] = bn1;
			o5[1][1] = bn2;
			
			Act.attach(bn1,"BELIEVE");
			
			Node n1 = m.build(o5, cf1);
			
			Act a1 = new Act((MolecularNode) n1);
			n1.setEntity(a1);
			
			a1.setPrimaction(true);
			
			Object[][] o7 = new Object[2][2];
			
			o7[0][0] = r7;
			o7[1][0] = r10;
			o7[0][1] = n1;
			o7[1][1] = bn2;
			
			Node n2 = m.build(o7, planGoal);

			Object[][] o6 = new Object[2][2];
//			 
//				
				o6[0][0] = r1;
				o6[1][0] = r2;
				Node bn4 = m.build("Achieve#2");
				o6[0][1] = bn4;
				o6[1][1] = bn2;
//				
				Act.attach(bn4,"Achieve");
//				
				Node n3 = m.build(o6, cf1);
//				
				Act a2 = new Act((MolecularNode) n3);
				n3.setEntity(a2);
//				
				a2.setPrimaction(true);
			
				Object[][] o9 = new Object[2][2];
				
				o9[0][0] = r5;
				o9[1][0] = r8;
				o9[0][1] = n6;
				o9[1][1] = bn2;
				
				Node n5 = m.build(o9, precf);
	
			//	test.conditions.add(bn2);
				a2.setAgenda("start");
				Queue.stackPush(n3);
				a2.performSNePS(n3, m);
//				test.printem();
//				
				a6.setAgenda("start");
				Queue.stackPush(n6);
				a6.performSNePS(n6, m);
			End */
	/*
	 * Start here	Object[][] o5 = new Object[2][2];
	 *
		
		o5[0][0] = r1;
		o5[1][0] = r2;
		Node bn1 = m.build("Tester#1");
		Node bn2 = m.build("Test FTW!");
		o5[0][1] = bn1;
		o5[1][1] = bn2;
		
	//	tester x = new tester(bn1);
	//	bn1.setEntity(x);
		Act.attach(bn1,"BELIEVE");
		
		Node n5 = m.build(o5, cf1);
		
		Act a1 = new Act((MolecularNode) n5);
		n5.setEntity(a1);
		
		a1.setPrimaction(true);
	//	a1.setAgenda("start");
		
		Object[][] o4 = new Object[2][2];
		
		o4[0][0] = r1;
		o4[1][0] = r2;
		Node bn3 = m.build("Tester#2");
		Node bn4 = m.build("Test FTW2!");
		o4[0][1] = bn3;
		o4[1][1] = bn4;
		
//		tester x2 = new tester(bn3);
//		bn3.setEntity(x2);
		Act.attach(bn3,"tester");
		Node n4 = m.build(o4, cf1);
		
		
		Act a2 = new Act((MolecularNode) n4);
		n4.setEntity(a2);
		
		a2.setPrimaction(true);
		//a2.setAgenda("start");
		
		Object[][] o3 = new Object[2][2];
		
		o3[0][0] = r3;
		o3[1][0] = r4;
		o3[0][1] = n4;
		o3[1][1] = n5;
		
		Node n3 = m.build(o3, cf2);
		
		
		Object[][] o2 = new Object[2][2];
		
		o2[0][0] = r1;
		o2[1][0] = r2;
		Node bn5 = m.build("doall");
		o2[0][1] = bn5;
		o2[1][1] = n3;
		
//		SNSEQUENCE x3  = new SNSEQUENCE(bn5);
//		bn5.setEntity(x3);
		
		Act.attach(bn5,"DoAll");
		Node n2 = m.build(o2, cf1);
	
		Act a3 = new Act((MolecularNode) n2);
		n2.setEntity(a3);
		
		a3.setPrimaction(true);
		a3.setAgenda("start");
		Queue.stackPush(n2);
		a3.performSNePS(n2, m);
		
		End Here */
	//	test.printem();
	/*	Object[][] o9 = new Object[2][2];
		
		o9[0][0] = r5;
		o9[1][0] = r8;
		Node bn6 = m.build("Dxx");
		o9[0][1] = n2;
		o9[1][1] = bn6;
		
		
		Node pc9 = m.build(o9, precf);
	/*	
		Object[][] o8 = new Object[2][2];
		
		o8[0][0] = r1;
		o8[1][0] = r2;
		Node bn8 = m.build("thePlanTest");
		o8[0][1] = bn8;
		o8[1][1] = bn6;
		
		BELIEVE b1 = new BELIEVE(bn8);
		bn8.setEntity(b1);
		
		Node n6 = m.build(o8, cf1);
		
		Act a4 = new Act((MolecularNode) n6);
		n6.setEntity(a4);
		
		a4.setPrimaction(true);
		
		Object[][] o7 = new Object[2][2];
		
		o7[0][0] = r7;
		o7[1][0] = r10;
		o7[0][1] = n6;
		o7[1][1] = bn6;
		
		Node p2 = m.build(o7, planGoal);
		*/
		/*
		 * STart here
		 
		Object[][] o45 = new Object[2][2];
		
		o45[0][0] = r1;
		o45[1][0] = r2;
		Node bn31 = m.build("Tester#3");
		Node bn32 = m.build("Test FTW3!");
		o45[0][1] = bn31;
		o45[1][1] = bn32;
		
		Act.attach(bn31,"tester");
		
		Node a77 = m.build(o45, cf1);
		
		Act a41 = new Act((MolecularNode) a77);
		a77.setEntity(a41);
		
		a41.setPrimaction(true);
		Object[][] o42 = new Object[2][2];
		
		o42[0][0] = r5;
		o42[1][0] = r6;
		o42[0][1] = a77;
		o42[1][1] = bn2;
		
		
		Node n12 = m.build(o42, guarded);
		
		Object[][] o43 = new Object[2][2];
		
		o43[0][0] = r1;
		o43[1][0] = r2;
		Node bn33 = m.build("Snif test");
		o43[0][1] = bn33;
		o43[1][1] = n12;
		
		Act.attach(bn33,"SNIF");
		Node n11 = m.build(o43, cf1);
		
		Act a40 = new Act((MolecularNode) n11);
		n11.setEntity(a40);
		
		Object[][] o50 = new Object[2][2];
		
		o50[0][0] = r5;
		o50[1][0] = r8;
		Node bn69 = m.build("X");
		o50[0][1] = n11;
		o50[1][1] = bn69;
		
		//test.conditions.add(bn69);
		Node n13 = m.build(o50, precf);
		
		
		Object[][] o54 = new Object[2][2];
		
		o54[0][0] = r1;
		o54[1][0] = r2;
		Node bn79 = m.build("pretest");
		o54[0][1] = bn79;
		o54[1][1] = bn69;
		
	//	tester x = new tester(bn1);
	//	bn1.setEntity(x);
		Act.attach(bn1,"BELIEVE");
		
		Node n15 = m.build(o54, cf1);
		
		Act a44 = new Act((MolecularNode) n15);
		n15.setEntity(a44);
		
		a44.setPrimaction(true);
		Object[][] o55 = new Object[2][2];
		
		o55[0][0] = r7;
		o55[1][0] = r10;
		o55[0][1] = n15;
		o55[1][1] = bn69;
		
		Node n14 = m.build(o55, planGoal);
		
		a40.setPrimaction(true);
		a40.setAgenda("start");
		Queue.stackPush(n11);
		a40.performSNePS(n11, m);
		
		End Here
		*/
		
	//	test.printem();
/*		Object[][] o7 = new Object[2][2];
		
		o7[0][0] = r7;
		o7[1][0] = r10;
		Node g1 = m.build("achieveTest");
		o7[0][1] = g1;
		o7[1][1] = p1;

		BELIEVE b1  = new BELIEVE(g1);
		g1.setEntity(b1);
		
		Node a1 = m.build(o7, planGoal);	
		
		Object[][] o8 = new Object[2][2];
		
		o8[0][0] = r5;
		o8[1][0] = r9;
		Node e1 = m.build("effectTest");
		o8[0][1] = n1;
		o8[1][1] = e1;

		Node xe1 = m.build(o8, effectcf);	
		
		Object[][] o10 = new Object[2][2];
		
		o10[0][0] = r1;
		o10[1][0] = r2;
		Node pll1 = m.build("theBelievePlan");
		Node vv = m.build("DDD");
		o10[0][1] = n1;
		o10[1][1] = vv;
		

		Node pl1 = m.build(o10, cf1);
		
		Object[][] o9 = new Object[2][2];
		
		o9[0][0] = r5;
		o9[1][0] = r7;
		o9[0][1] = n1;
		o9[1][1] = pl1;

		Node xl1 = m.build(o9, plancf);	
	
		
		*/
	
	//	aa.Perform();
		
	}
	
}