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
	
	public  void assertProposition(Proposition proposition,String name)
	{
		 Context context  =  contextSet.getContext(name);
		 context.print();
		
		 Context newContext = new Context();
	    	newContext.setHypSet(contextSet.getContext(name).getHypSet());
		    newContext.addName(name);
		   
		  if (context!=null)
		  { 
			  proposition.makeItHyp();
			  contextSet.removeContext(context);
			   context.RemoveName(name);
			  contextSet.addContext(context);
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
