
package snebr;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.text.DefaultEditorKit.CutAction;

public class ContextSet {
private Set<Context> currentContext;
private String idetifire ;


public ContextSet(Set<Context> currentContext, String idetifire) {
	super();
	this.currentContext = currentContext;
	this.idetifire = idetifire;
}

public ContextSet() {
	super();
	this.currentContext = new HashSet<Context>();
	this.idetifire = new String();
}
public Context getContext(String identifire)
{
	for (Iterator iterator = currentContext.iterator(); iterator.hasNext();) {
		Context type = (Context) iterator.next();
		Set<String> names =  type.getNames();
		for (Iterator iterator2 = names.iterator(); iterator2.hasNext();) {
			String string = (String) iterator2.next();
			if(string.equalsIgnoreCase(identifire));
			return type;
		}
	
	  
	}
	return null;
}

public String getIdetifire() {
	return idetifire;
}

public void setIdetifire(String idetifire) {
	this.idetifire = idetifire;
}

public Set<Context> getCurrentContext() {
	return currentContext;
}

public void setCurrentContext(Set<Context> currentContext) {
	this.currentContext = currentContext;
}
public boolean isEmpty()
{
	return this.currentContext.isEmpty();
}
public void addContext(Context newContext)
{
	this.currentContext.add(newContext);
}
public void removeContext(Context context)
{
	this.currentContext.remove(context);
}
public boolean isMember(Context context)
{
	return this.currentContext.contains(context);
}
public void print()
{
	for (Iterator iterator = this.currentContext.iterator(); iterator.hasNext();) {
		Context type = (Context) iterator.next();
		type.print();
		System.out.println();
		
	}
}

}

