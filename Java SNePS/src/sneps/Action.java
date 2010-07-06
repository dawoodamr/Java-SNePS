package sneps;


import snactor.*;

@SuppressWarnings("serial")
public class Action extends Entity
{
 
	public static Queue queue;
	
	
	public Action(Node node)
	
	{ 
		setNode(node);
	}


	public void Perform()
	{
		System.out.println("Sd");
	}
	
}