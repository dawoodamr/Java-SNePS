package snebr;

import java.util.LinkedList;

import sneps.CaseFrame;
import sneps.CustomException;
import sneps.Network;
import sneps.Node;
import sneps.Relation;

public class test {

	
	ContextSet contextSet;
	Context currentContext;
	
	public static Proposition he(String x ,String y ,String z ,String k ,Network n )throws CustomException
	{

		 LinkedList<Relation> l = new LinkedList<Relation>();
		 l.add(n.getRelation(x));
		 l.add(n.getRelation(y));
		 CaseFrame c = n.defineCaseFrame("proposition", l);
		 Node n1 = n.build(z);
		 Node n2 = n.build(k);
		 Relation r1 = n.getRelation(x);
		 Relation r2 = n.getRelation(y);
		 Object[][] array = new Object[2][2];
		 array[0][0] =r1; array[0][1] = n1;
		 array [1][0] =r2 ; array[1][1] = n2;
		 Node result = n.build(array,c);
		 Proposition p1 = new Proposition(result);
		 System.out.println();
		 p1.print();
		 return p1;
	}
 public static void main(String [] args) throws CustomException
 {
	 Network n = new Network();
	 //create m1 (member class) 
	 n.defineRelation("member", "entity", "reduce", 0);
	 n.defineRelation("super", "entity", "reduce", 0);
	 n.defineRelation("sub", "entity", "reduce", 0);
	 n.defineRelation("element","entity", "reduce", 0);
	 n.defineRelation("class", "entity", "reduce", 0);
	 n.defineRelation("has-prop", "entity", "reduce", 0);
	Proposition p1= he("member", "class", "fido","dog",  n);
	Proposition p2=  he("super", "sub", "dog3", "mammal", n);
	Proposition p3=  he("super", "sub", "mammal3", "animal", n);
	Proposition p4=  he("element", "has-prop", "animal3", "has-skin", n);


//	 context.addToContext(p1);
//	 context.addToContext(p2);
	 SNeBR snebr = new SNeBR();
	 
	 Context context = snebr.createContext();
	 context.addName("animal");
	 context.addName("mammal");
	 snebr.assertProposition(p1, context);
	 snebr.assertProposition(p2, context);
	 snebr.assertProposition(p3, "mammal");
	 
//	context.print();
	 snebr.print();
	 
//	 n.defineRelation("member", "entity", "reduce", 0);
//	 n.defineRelation("class", "entity", "reduce", 0);
//	 LinkedList<Relation> l = new LinkedList<Relation>();
//	 l.add(n.getRelation("member"));
//	 l.add(n.getRelation("class"));
//	 CaseFrame c = n.defineCaseFrame("proposition", l);
//	 Node n1 = n.build("fido");
//	 Node n2 = n.build("dog");
//	 Relation r1 = n.getRelation("member");
//	 Relation r2 = n.getRelation("class");
//	 Object[][] array = new Object[2][2];
//	 array[0][0] =r1; array[0][1] = n1;
//	 array [1][0] =r2 ; array[1][1] = n2;
//	 Node result = n.build(array,c);
//	 Proposition p1 = new Proposition(result);
//	 Support s = new Support();
//	 s.addToOriginSet(p1);
//	 
//	 PropositionSet restrictionSet = new PropositionSet();
//	 restrictionSet.addProposition(p1);
//	 
//	s.addToRestrictionSet(restrictionSet );
//	 p1.addSupport(s);
//	 
//	 s.setSupporttype("hyp");
//	 p1.print();
	 //m2 (member
	
	 
	 
	 
	// System.out.print(p1.getNode().getIdentifier());
 }
}