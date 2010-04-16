package snebr;

import java.util.Iterator;

public class SNeBR {
	public   ContextSet contextSet;
	public   Context currentContext;
	public SNeBR ()
	{
		contextSet = new ContextSet();
		currentContext = new Context();
	}
	public Context createContext()
	{
	    Context context  = new Context();
		contextSet.addContext(context);
		return context;
	}
	
	public  void assertProposition(Proposition proposition,String name) throws CloneNotSupportedException
	{
		 Context context  =   contextSet.getContext(name);
		 
		 if (context!=null)
		  { 
			 //context.print();
	  Context newContext =  new Context((PropositionSet)context.getHypSet().clone(),name);
	 
		 // (Context) context.clone();
//	  Context copy = (Context) context.;
//	    newContext.setHypSet(copy.getHypSet());
//	    newContext.addName(name);
		   
		 
			  proposition.makeItHyp();
	
			   context.RemoveName(name);
			 // contextSet.addContext(context);
			   newContext.addToContext(proposition);  
			   contextSet.addContext(newContext);
		  }
		  
	
		  
		
		
	}
	
	
	public  void assertProposition(Proposition proposition,Context context )
	{
	     context.addToContext(proposition);	
	}
	public  void assertPropositon(Proposition proposition)
	{
		currentContext.addToContext(proposition);
	
	}
	
	public  void print()
	{
	  contextSet.print();
	}
}
