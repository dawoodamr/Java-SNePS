package snebr;

import java.util.Set;

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


}
