package SNePS1.NetworkManagementSystem;

import java.util.LinkedList;

/**
 * A case frame describes the skeleton of the node along with the Relations 
 * going out of it. No two case frames may contain exactly the same list of 
 * Relations in a knowledge base but they might have the same semantic class.
 * Once a case frame is created it can never be changed.
 * 
 * @author Amr Khaled Dawood
 */
public class CaseFrame
{

	/**
	 * the name of the semantic class of the node that can have such a case frame
	 */
	private String semanticClass;
	
	/**
	 * a list of Relations that should be included in this case frame
	 */
	private LinkedList<Relation> relations;
	
	/**
	 * the id is a String obtained by concatenating all relations names in the case frame
	 * in lexicographic order and separated by commas. no two case frames may have the same id
	 */
	private String id;

	/**
	 * @param semanticClass the semantic type of the node that have this case frame
	 * @param relations a list of Relations that should be in the case frame
	 */
	public CaseFrame(String semanticClass,LinkedList<Relation> relations)
	{
		this.semanticClass = semanticClass;
		this.relations = relations;
		this.id = orderToString(relations);
	}

	/**
	 * @return the semantic class of the node that could have such a case frame
	 */
	public String getSemanticClass()
	{
		return semanticClass;
	}

	/**
	 * @return the list of Relations that should be going out of the node that 
	 * has such a case frame.
	 */
	public LinkedList<Relation> getRelations()
	{
		return relations;
	}
	
	/**
	 * @return the id of the case frame that consists of names of the relations in this
	 * case frame concatenated and separated by commas
	 */
	public String getId()
	{
		return id;
	}
	
	/**
	 * A case frame is adjustable to another case frame if the both have the same semantic 
	 * class, the difference between the list of Relations in the first one and the list 
	 * of Relations in the second one is a list of Relations that are reducible with limit=0
	 * , and the difference between the list of Relations in the second one and the first one 
	 * is a list of Relations that are expandable with limit=0
	 * 
	 * @param caseFrame another case frame
	 * @return true if this case frame is adjustable to the specified case frame 
	 * and false if it is not adjustable to it.
	 */
	public boolean isAdjustableTo(CaseFrame caseFrame)
	{
		if(! this.semanticClass.equals(caseFrame.getSemanticClass()))
			return false;
		
		LinkedList<Relation> oneDiffTwo = new LinkedList<Relation>();
		oneDiffTwo.addAll(this.relations);
		oneDiffTwo.removeAll(caseFrame.getRelations());
		for(int i=0;i<oneDiffTwo.size();i++)
		{
			if(! oneDiffTwo.get(i).getAdjust().equals("reduce"))
				return false;
			if(oneDiffTwo.get(i).getLimit() != 0)
				return false;
		}
		
		LinkedList<Relation> twoDiffOne = new LinkedList<Relation>();
		twoDiffOne.addAll(caseFrame.getRelations());
		twoDiffOne.removeAll(this.relations);
		for(int i=0;i<twoDiffOne.size();i++)
		{
			if(! twoDiffOne.get(i).getAdjust().equals("expand"))
				return false;
			if(twoDiffOne.get(i).getLimit() != 0)
				return false;
		}
		
		
		return true;
	}
	
	/**
	 * this method is used for quick-sorting the names of the relations in the list
	 * given as a parameter for the method and returns them as a String resulted 
	 * from concatenating these names separated by commas
	 *  
	 * @param r a linked list of relations that we want to sort its relation names and
	 * put them concatenated in a String
	 * @return a String of the result of sorting the names of the relations in the list
	 */
	private String orderToString(LinkedList<Relation> r)
	{
		LinkedList<Relation> relations = new LinkedList<Relation>();
		relations.addAll(r);
		String result = "";
		for(int i=0;i<relations.size();i++)
		{
			result += relations.get(i).getName();
			if(i < relations.size()-1)
				result += ",";
		}
		
		result = quickSortLexicographically(result.split(","));
		
		return result;
	}
	
	/**
	 * This method sorts the items in the String array in lexicographic order and puts them
	 * after sorting in a String separated by commas
	 * @param s an array of string that we want to sort in lexicographic order
	 * @return a String resulted from concatenating all items of the array after being sorted
	 */
	private String quickSortLexicographically(String[] s)
	{
		String [] string = s;
		quickSort(string,0,string.length-1);
		
		String result = "";
		for(int i=0;i<string.length;i++)
		{
			result += string[i];
			if(i < string.length-1)
				result += ",";
		}
		
		return result;
	}
	
	/**
	 * @param arr the array of Strings to be sorted by quick sort algorithm
	 * @param left the starting position of the items we want to sort
	 * @param right the ending position of the items we want to sort
	 */
	private void quickSort(String arr[],int left,int right)
	{
	      int index = partition(arr,left,right);
	      if (left < index - 1)
	            quickSort(arr,left,index - 1);
	      if (index < right)
	            quickSort(arr,index,right);
	}
	
	/**
	 * @param arr an array of Strings that we need to get the pivot for
	 * @param left the start position of the sorting
	 * @param right the end position of the sorting
	 * @return an int representing the pivot index in the array
	 */
	private int partition(String arr[],int left,int right)
	{
	      int i = left,j = right;
	      String tmp;
	      String pivot = arr[(left + right) / 2];
	     
	      while(i <= j)
	      {
	            while(arr[i].compareTo(pivot) < 0)
	            	i++;
	            while(arr[j].compareTo(pivot) > 0)
	            	j--;
	            if(i <= j)
	            {
	                  tmp = arr[i];
	                  arr[i] = arr[j];
	                  arr[j] = tmp;
	                  i++;
	                  j--;
	            }
	      };
	     
	      return i;
	}
	
}