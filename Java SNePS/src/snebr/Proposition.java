package snebr;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import sneps.Entity;
import sneps.Node;

public class Proposition extends Entity {
	
	private Set<Support> support ; //set of support 
	boolean flag ;

	
	public Proposition(Node node,Set<Support> support) {
		super();
		super.setNode(node);
		this.support = support;
	}
	public Proposition(Node node)
	{
		super();
		super.setNode(node);
		support = new HashSet<Support>();
		this.makeItHyp();
		
	}
   public Proposition(Node node ,Support newsupport)
   {
		super();
		super.setNode(node);
		support = new HashSet<Support>();
		support.add(newsupport);
	}
	public Set<Support> getSupport() {
		return support;
	}

	public void setSupport(Set<Support> support) {
		this.support = support;
	}

  public void addSupport (Support support)
  {
	  this.support.add(support);
  }
  public void makeItHyp()
  {
	  Support support = new Support();
	  support.addToOriginSet(this);
	  support.setSupporttype("hyp");
	  this.support.add(support);  
  }
  

 public void print()
 {    

 for (Iterator iterator = this.support.iterator(); iterator.hasNext();) {
	Support type = (Support) iterator.next();
	 System.out.print(this.getNode().getIdentifier() + "  "  );
	 type.printSupport();
	 System.out.println();
}


	
 }
	
}


	 
 



