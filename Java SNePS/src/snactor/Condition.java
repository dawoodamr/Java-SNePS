package snactor;

import java.util.LinkedList;


import sneps.*;

public class Condition
{

	public static LinkedList<Node> conditions = new LinkedList<Node>();
	Network m = Network.getInstance();

public Condition()
	
	{ 
	
		
		
	}

public Boolean testConditions(Node s)

	{
		int x = conditions.size();
		for(int i=0;i<x;i++)
		{
			if(conditions.get(i).getIdentifier()==s.getIdentifier())
			{
				return true;
			}
			
		}
		return false;
	}

public void printem()
{
	int x = conditions.size();
	for(int i=0;i<x;i++)
	{
		System.out.println(((Node) conditions.pop()).getIdentifier());
		
	}
}
	
}