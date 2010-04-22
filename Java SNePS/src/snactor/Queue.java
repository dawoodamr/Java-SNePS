package snactor;

import sneps.*;

import java.util.Stack;

public class Queue
{
	
	static Stack<Node> theQueue = new Stack<Node>();
	
	
	public static void stackPush(Node n)
	{
		theQueue.push(n);
	}
	
	public static Node stackPop()
	{
		if(theQueue.empty())
		{
			
		return null;
		
		}
		else
		{
			return theQueue.pop();
		}
		
	}
	

}