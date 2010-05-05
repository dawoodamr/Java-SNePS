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

	public static int getsize()
	{
		return theQueue.size();
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

	public static boolean isEmpty() 
	{
		if(theQueue.isEmpty())
		{
			return true;
		}
		return false;
	}
	

}