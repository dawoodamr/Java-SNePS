package snactor;

import java.util.Stack;

import sneps.*;

public class Condition
{
	@SuppressWarnings("unchecked")
	public static Stack conditions = new Stack();
	
@SuppressWarnings("unchecked")
public Condition()
	
	{ 
		conditions.push("condition");
		conditions.push("D");	
	}

public Boolean testConditions(Node s)

	{
		int x = conditions.size();
		for(int i=0;i<x;i++)
		{
			if(conditions.pop()==s.getIdentifier())
			{
				return true;
			}
			
		}
		return false;
	}
	
}