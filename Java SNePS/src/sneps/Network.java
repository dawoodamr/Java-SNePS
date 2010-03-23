package sneps;

import java.util.Hashtable;
import java.util.LinkedList;

/**
 * The Network class is the main class for the network management system. this class 
 * contains relations, case frames as well as the nodes that the network consists of.
 * 
 * @author Amr Khaled Dawood
 */
public class Network
{
	
	/**
	 * the hash table containing nodes of the network indexed by the names.
	 */
	private Hashtable<String,Node> nodes;
	
	/**
	 * the hash table containing case frames that the user defined to use in this network
	 * indexed by a string resulted from concatenating the names of relations of the case
	 * frame ordered in lexicographic order.
	 */
	private Hashtable<String,CaseFrame> caseFrames;
	
	/**
	 * the hash table containing the relations that the user defined for using in building 
	 * this network indexed by their names.
	 */
	private Hashtable<String,Relation> relations;
	
	/**
	 * the counter used for the names of closed molecular nodes.each closed molecular node is 
	 * named by 'm' followed by a positive number, for example: "m12".
	 */
	private int molCounter;
	
	/**
	 * the counter used for names of pattern molecular nodes. each pattern molecular node is
	 * named by 'p' followed by a positive number, for example: "p10" 
	 */
	private int patCounter;
	
	/**
	 * a list of numbers that the user used in naming nodes he created that look like 
	 * names of closed molecular nodes names.this list is important in order to avoid 
	 * using them again.
	 */
	private LinkedList<Integer> userDefinedMolPostfix;
	
	/**
	 * a list of numbers that the user used in naming nodes he created that look like 
	 * names of pattern molecular nodes names.this list is important in order to avoid 
	 * using them again.
	 */
	private LinkedList<Integer> userDefinedPatPostfix;
	
	/**
	 * The constructor initializes all instance variables.
	 */
	public Network()
	{
		nodes = new Hashtable<String, Node>();
		caseFrames = new Hashtable<String, CaseFrame>();
		relations = new Hashtable<String, Relation>();
		userDefinedMolPostfix = new LinkedList<Integer>();
		userDefinedPatPostfix = new LinkedList<Integer>();
		molCounter = 0;
		patCounter = 0;
	}

	/**
	 * @return that hash table of the nodes
	 */
	public Hashtable<String,Node> getNodes()
	{
		return nodes;
	}

	/**
	 * @return the hash table of the case frames
	 */
	public Hashtable<String,CaseFrame> getCaseFrames()
	{
		return caseFrames;
	}

	/**
	 * @return the hash table of the relations in this network
	 */
	public Hashtable<String,Relation> getRelations()
	{
		return relations;
	}
	
	/**
	 * @return this method returns a list of numbers that the user used them as a 
	 * suffix to 'm' for naming the nodes he created 
	 */
	public LinkedList<Integer> getUserDefinedMolPostfix()
	{
		return userDefinedMolPostfix;
	}

	/**
	 * @return this method returns a list of numbers that the user used them as a 
	 * suffix to 'p' for naming the nodes he created
	 */
	public LinkedList<Integer> getUserDefinedPatPostfix()
	{
		return userDefinedPatPostfix;
	}
	
	/**
	 * @param name the name of the relation 
	 * @return the Relation with the specified name
	 * @throws CustomException an exception is thrown if there is no relation with
	 * the specified name
	 */
	public Relation getRelation(String name)throws CustomException
	{
		if(! relations.containsKey(name))
			throw new CustomException("there is no relation with this name");
		else
			return relations.get(name);
	}

	/**
	 * @param relString the id of the case frame
	 * @return the case frame with the relation set specified
	 * @throws CustomException an exception is thrown if there is no case frame
	 * with the specified relation set
	 */
	public CaseFrame getCaseFrame(String relString)throws CustomException
	{
		if(! caseFrames.containsKey(relString))
			throw new CustomException("there is no case frame with such set of relations");
		else
			return caseFrames.get(relString);
	}
	
	/**
	 * @param identifier the name of the node 
	 * @return the node with the specified name
	 * @throws CustomException an exception is thrown if there is no nodes
	 * with such a name
	 */
	public Node getNode(String identifier)throws CustomException
	{
		if(! nodes.containsKey(identifier))
			throw new CustomException("there is no node with such name");
		else
			return nodes.get(identifier);
	}
	
	/**
	 * @param name the name of the relation
	 * @param type the semantic type of the node that the relation could point to
	 * @param adjust the adjustability of the relation "reduce", "expand", or "none"
	 * @param limit the minimum value that the relation can be reduced to or expanded from
	 * @return the relation that was just created
	 * @throws CustomException an exception is thrown if the relation already exists
	 */
	public Relation defineRelation(String name,String type,
			String adjust,int limit)throws CustomException
	{
		if(relations.containsKey(name))
			throw new CustomException("the relation already exists");
		else
			relations.put(name,new Relation(name,type,adjust,limit));
		
		return relations.get(name);
	}
	
	/**
	 * @param name the name of the relation that we want to remove
	 */
	public void unDefineRelation(String name)
	{
		Relation r = relations.get(name);
		
		// removing case frames that contain this relation
		for(int i=0;i<caseFrames.size();i++)
		{
			while(caseFrames.elements().hasMoreElements())
			{
				CaseFrame caseFrame = caseFrames.elements().nextElement();
				if(caseFrame.getRelations().contains(r))
					caseFrames.remove(caseFrame.getId());
			}
		}

		// removing the relation
		relations.remove(name);
	}
	
	/**
	 * @param semanticType the semantic type of the node that can have such a case frame
	 * @param relationSet a list of relations that could connect the node containing 
	 * this relation to other child nodes
	 * @return the case frame that was just created
	 * @throws CustomException 
	 */
	public CaseFrame defineCaseFrame(String semanticType,
			LinkedList<Relation> relationSet)throws CustomException
	{
		CaseFrame caseFrame = new CaseFrame(semanticType,relationSet);
		String relString = caseFrame.getId();
		if(caseFrames.containsKey(relString))
			throw new CustomException("the case frame already exists");
		else
			caseFrames.put(relString,new CaseFrame(semanticType,relationSet));
		
		return caseFrames.get(relString);
	}
	
	/**
	 * @param id the id of the case frame that we want to remove
	 */
	public void unDefineCaseFrame(String id)
	{
		caseFrames.remove(id);
	}

	/**
	 * @param node the node that we want to remove from the network
	 */
	public void removeNode(Node node)
	{
		// removing the node from the hash table
		nodes.remove(node.getIdentifier());
		
		// removing child nodes of this node that have no other parents
		if(node.getClass().getSuperclass().getSimpleName().equals("MolecularNode"))
		{
			MolecularNode m = (MolecularNode) node;
			CableSet cableSet = m.getCableSet();
			// loop for cables
			for(int i=0;i<cableSet.getCables().size();i++)
			{
				Cable cable = cableSet.getCables().get(i);
				NodeSet ns = cable.getNodeSet();
				//loop for nodes in the node set
				for(int j=0;j<ns.getNodes().size();j++)
				{
					Node n = ns.getNodes().get(j);
					// loop for UpCables
					for(int k=0;k<n.getUpCableSet().getUpCables().size();k++)
					{
						UpCable upCable = n.getUpCableSet().getUpCables().get(k);
						upCable.removeNode(node);
						if(upCable.getNodeSet().isEmpty())
							n.getUpCableSet().removeUpCable(upCable);
					}
					// removing child nodes
					if(! n.getClass().getSuperclass().getSimpleName().equals("MolecularNode")
							&& n.getUpCableSet().isEmpty())
						removeNode(n);
				}
			}
		}
	}
	
	/**
	 * @param identifier the name of the variable node as the user specified
	 * @return the variable node that was just created
	 * @throws CustomException an exception is thrown if the node already exists
	 */
	public Node buildVariableNode(String identifier)throws CustomException
	{
		if(! nodes.containsKey(identifier))
		{
			nodes.put(identifier,new VariableNode(identifier));
			if(isMolName(identifier)>-1)
				userDefinedMolPostfix.add(new Integer(isMolName(identifier)));
			if(isPatName(identifier)>-1)
				userDefinedPatPostfix.add(new Integer(isPatName(identifier)));
		}else
			throw new CustomException("the node with this name already exists");
		
		return nodes.get(identifier);
	}
	
	/**
	 * @param identifier the name of the base node that the user specified
	 * @return the base node that was just created
	 * @throws CustomException an exception is thrown if the node with this name 
	 * already exists 
	 */
	public Node build(String identifier)throws CustomException
	{
		if(! nodes.containsKey(identifier))
		{
			nodes.put(identifier,new BaseNode(identifier));
			if(isMolName(identifier)>-1)
				userDefinedMolPostfix.add(new Integer(isMolName(identifier)));
			if(isPatName(identifier)>-1)
				userDefinedPatPostfix.add(new Integer(isPatName(identifier)));
		}else
			throw new CustomException("the node with this name already exists");
		
		return nodes.get(identifier);
	}
	
	/**
	 * @param array an n*2 array of Objects, the first column contains relations and the 
	 * second column contains the destination nodes.each row contains a relation and the
	 * destination node for this relation
	 * @param caseFrame the case frame that should be included in the molecular node that 
	 * will be built
	 * @return the node that was just created
	 * @throws CustomException an exception will be thrown if the semantic type of the 
	 * relation does not match the semantic type of the corresponding node in the array
	 */
	public Node build(Object[][] array,CaseFrame caseFrame)throws CustomException
	{
		if(! isSemanticallyConsistent(array))
			throw new CustomException("relations are not consistent with target nodes");
		if(isToBePattern(array))
		{
			PatternNode patternNode = createPatNode(array,caseFrame);
			patternNode.getFreeVariables().addAll(getFreeVars(array));
			return patternNode;
		}
		else
			return createMolNode(array,caseFrame);
	}
	
	/**
	 * this method determines whether the molecular node that contains a cable set 
	 * containing the relations and the nodes in the array should be a pattern node or not
	 * 
	 * @param array an n*2 array of relations and nodes each row consists of a relation
	 * and a node
	 * @return true if the molecular node that have this cable set of relations and nodes
	 * should be a pattern node and false if not
	 */
	public boolean isToBePattern(Object[][] array)
	{
		for(int i=0;i<array.length;i++)
		{
			Relation r = (Relation) array[i][0];
			Node node = (Node) array[i][1];
			if(node.getClass().getSimpleName().equals("VariableNode") && 
					!r.isQuantifier())
				return true;
			if(node.getClass().getSimpleName().equals("PatternNode"))
			{
				PatternNode patternNode = (PatternNode) node;
				LinkedList<VariableNode> varNodes = patternNode.getFreeVariables();
				for(int j=0;j<array.length;i++)
				{
					Relation re = (Relation) array[j][0];
					Node no = (Node) array[j][1];
					if(varNodes.contains(no) && !re.isQuantifier())
						return true;
				}
				
			}
		}
		
		return false;
	}

	/**
	 * @param array an n*2 array of relations and nodes.each row consists of two things
	 * a relation and a node pointed to by an arc labeled by this relation.
	 * @param caseFrame the case frame that should be included in the cable set
	 * in this molecular node
	 * @return the node that was just created
	 */
	public ClosedNode createMolNode(Object[][] array,CaseFrame caseFrame)
	{
		// creating the node
		Object[][] relNodeSet = turnIntoRelNodeSet(array);
		LinkedList<Cable> cables = new LinkedList<Cable>();
		for(int i=0;i<relNodeSet.length;i++)
		{
			cables.add(new Cable((Relation) relNodeSet[i][0],(NodeSet) relNodeSet[i][1]));
		}
		CableSet cableSet = new CableSet(cables,caseFrame);
		String molName = getNextMolName();
		ClosedNode closedNode = new ClosedNode(molName,cableSet);
		this.nodes.put(molName,closedNode);
		
		// adding UpCables
		for(int i=0;i<cableSet.getCables().size();i++)
		{
			Relation r = cableSet.getCables().get(i).getRelation();
			for(int j=0;j<cableSet.getCables().get(i).getNodeSet().getNodes().size();j++)
			{
				Node n = cableSet.getCables().get(i).getNodeSet().getNodes().get(j);
				if(! n.getUpCableSet().contains(r))
					n.getUpCableSet().addUpCable(new UpCable(r));
				n.getUpCableSet().getUpCable(r).addNode(closedNode);
			}
		}
		
		
		return closedNode;
	}
	
	/**
	 * @param array an n*2 array of relations and nodes.each row consists of two things
	 * a relation and a node pointed to by an arc labeled by this relation.
	 * @param caseFrame the case frame that should be included in the cable set 
	 * of this node
	 * @return the node that was just created
	 */
	public PatternNode createPatNode(Object[][] array,CaseFrame caseFrame)
	{
		// creating the node
		Object[][] relNodeSet = turnIntoRelNodeSet(array);
		LinkedList<Cable> cables = new LinkedList<Cable>();
		for(int i=0;i<relNodeSet.length;i++)
		{
			cables.add(new Cable((Relation) relNodeSet[i][0],(NodeSet) relNodeSet[i][1]));
		}
		CableSet cableSet = new CableSet(cables,caseFrame);
		String patName = getNextPatName();
		PatternNode patternNode = new PatternNode(patName,cableSet);
		this.nodes.put(patName,patternNode);
		
		// adding UpCables
		for(int i=0;i<cableSet.getCables().size();i++)
		{
			Relation r = cableSet.getCables().get(i).getRelation();
			for(int j=0;j<cableSet.getCables().get(i).getNodeSet().getNodes().size();j++)
			{
				Node n = cableSet.getCables().get(i).getNodeSet().getNodes().get(j);
				if(! n.getUpCableSet().contains(r))
					n.getUpCableSet().addUpCable(new UpCable(r));
				n.getUpCableSet().getUpCable(r).addNode(patternNode);
			}
		}
		
		return patternNode;
	}
	
	/**
	 * @param array an array of size n*2. each row of this array consists of a relation and 
	 * a node.
	 * @return an n*2 array of Objects each row consists of two things, a Relation and 
	 * a NodeSet that contains nodes that are pointed to by arcs labeled with this relation.
	 */
	public Object[][] turnIntoRelNodeSet(Object[][] array)
	{
		int relCount = 0;
		boolean exists = false;
		Object[][] tempResult = new Object[array.length][array[0].length];
		for(int i=0;i<array.length;i++)
		{
			Relation r = (Relation) array[i][0];
			Node n = (Node) array[i][1];
			for(int j=0;j<relCount;j++)
			{
				if(r.equals((Relation) tempResult[j][0]))
				{
					NodeSet ns = (NodeSet) tempResult[j][1];
					ns.getNodes().add(n);
					exists = true;
					break;
				}else
					exists = false;
			}
			if(! exists)
			{
				exists = false;
				NodeSet nodeSet = new NodeSet();
				nodeSet.getNodes().add(n);
				tempResult[relCount][0] = r;
				tempResult[relCount][1] = nodeSet;
				relCount++;
			}
		}
		
		Object[][] result = new Object[relCount][2];
		for(int i=0;i<relCount;i++)
		{
			result[i][0] = tempResult[i][0];
			result[i][1] = tempResult[i][1];
		}
		
		return result;
	}
	
	/**
	 * @param array an array of size n*2. each row consists of a Relation and a Node that
	 * is pointed to by an arc labeled by this relation
	 * @return a list of free variables dominated by the node that have such relations and nodes
	 * in its cable set.
	 */
	public LinkedList<VariableNode> getFreeVars(Object[][] array)
	{
		LinkedList<VariableNode> freeVars = new LinkedList<VariableNode>();
		for(int i=0;i<array.length;i++)
		{
			Node n = (Node) array[i][1];
			Relation r = (Relation) array[i][0];
			if(n.getClass().getSimpleName().equals("VariableNode") && 
					! r.isQuantifier())
				freeVars.add((VariableNode) n);
			if(n.getClass().getSimpleName().equals("PatternNode"))
			{
				PatternNode patNode = (PatternNode) n;
				LinkedList<VariableNode> inheritedFreeVars = new LinkedList<VariableNode>();
				inheritedFreeVars.addAll(patNode.getFreeVariables());
				for(int j=0;j<patNode.getFreeVariables().size();j++)
				{
					for(int k=0;k<array.length;k++)
					{
						Relation tempRel = (Relation) array[k][0];
						VariableNode var = patNode.getFreeVariables().get(j);
						if(array[k][1].equals(var) && tempRel.isQuantifier())
								inheritedFreeVars.remove(var);
					}
				}
				freeVars.addAll(inheritedFreeVars);
			}
		}
		
		return freeVars;
	}
	
	/**
	 * This method is used for making sure that each relation in the array can point 
	 * to the corresponding node -from the point of view of the semantic type-
	 * 
	 * @param array an n*2 array of Relations and Nodes
	 * @return true if the Relations can point to these nodes and false if not
	 */
	public boolean isSemanticallyConsistent(Object[][] array)
	{
		
		
		return true;
	}
	
	/**
	 * @return a String representing the closed node name that we can create with it
	 */
	public String getNextMolName()
	{
		molCounter++;
		String molName = "M";
		for(int i=0;i<userDefinedMolPostfix.size();i++)
		{
			if(userDefinedMolPostfix.get(i).intValue() == molCounter)
			{
				molCounter++;
				i=-1;
			}
		}
		molName += ""+molCounter;
		
		return molName;
	}
	
	/**
	 * @return a String representing the pattern node name that we can create with it
	 */
	public String getNextPatName()
	{
		patCounter++;
		String patName = "P";
		for(int i=0;i<userDefinedPatPostfix.size();i++)
		{
			if(userDefinedPatPostfix.get(i).intValue() == patCounter)
			{
				patCounter++;
				i=-1;
			}
		}
		patName += ""+patCounter;
		
		return patName;
	}
	
	/**
	 * this method is used to get the number that follows the 'm' character if this 
	 * string is similar to the name of a closed node in order to keep track of the 
	 * names that the user used in defining nodes to maintain uniqueness of nodes names
	 * 
	 * @param identifier the name of the node
	 * @return the suffix of the identifier after the 'm' if it is similar to
	 * a closed node name but if it is not it returns -1
	 */
	public int isMolName(String identifier)
	{
		if(identifier.length()==1)
			return -1;
		if(identifier.charAt(0) != 'm' && identifier.charAt(0) != 'M')
			return -1;
		for(int i=1;i<identifier.length();i++)
		{
			if(! isInt(identifier.charAt(i)))
				return -1;
		}
		return Integer.parseInt(identifier.substring(1,identifier.length()));
	}
	
	/**
	 * this method is used to get the number that follows the 'p' character if this 
	 * string is similar to the name of a pattern node in order to keep track of the 
	 * names that the user used in defining nodes to maintain uniqueness of nodes names
	 * 
	 * @param identifier the name of the node
	 * @return the suffix of the identifier after the 'p' if it is similar to
	 * a pattern node name but if it is not it returns -1
	 */
	public int isPatName(String identifier)
	{
		if(identifier.length()==1)
			return -1;
		if(identifier.charAt(0) != 'p' && identifier.charAt(0) != 'P')
			return -1;
		for(int i=1;i<identifier.length();i++)
		{
			if(! isInt(identifier.charAt(i)))
				return -1;
		}
		return Integer.parseInt(identifier.substring(1,identifier.length()));
	}
	
	/**
	 * @param c a char that we need to know if it is a number from '0' to '9' or not
	 * @return true if this char is a number and false if not
	 */
	public boolean isInt(char c)
	{
		switch(c)
		{
		case '0':;
		case '1':;
		case '2':;
		case '3':;
		case '4':;
		case '5':;
		case '6':;
		case '7':;
		case '8':;
		case '9':return true;
		default : return false;
		}
	}
	
	
	
	public static void main(String [] args)
	{
		Relation r1 = new Relation("member","entity","reduce",0);
		Relation r2 = new Relation("class","entity","reduce",0);
		Relation r3 = new Relation("object","entity","reduce",0);
		Relation r4 = new Relation("ability","entity","reduce",0);
		LinkedList<Relation> l = new LinkedList<Relation>();
		l.add(r1);
		l.add(r2);
		l.add(r3);
		l.add(r4);
		Object[][] o = new Object[5][2];
		o[0][0] = r1;
		o[1][0] = r1;
		o[2][0] = r1;
		o[3][0] = r2;
		o[4][0] = r1;
		o[0][1] = new BaseNode("amr");
		o[1][1] = new BaseNode("moh");
		o[2][1] = new BaseNode("zay");
		o[3][1] = new BaseNode("osa");
		o[4][1] = new BaseNode("ahm");
		Node m = new ClosedNode("A",null);
		System.out.println(m.getClass().getSuperclass().getSimpleName());
		Network n = new Network();
		Object[][] oo = n.turnIntoRelNodeSet(o);
		/*for(int i=0;i<oo.length;i++)
		{
			System.out.println(oo.length);
			Relation r = (Relation) oo[i][0];
			NodeSet ns = (NodeSet) oo[i][1];
			for(int j=0;j<ns.getNodes().size();j++)
			{
				Node node = ns.getNodes().get(j);
				System.out.println(r.getName()+ " " +node.getIdentifier());
			}
		}*/
		LinkedList<Relation> r = new LinkedList<Relation>();
		r.add(r1);
		r.add(r1);
		r.add(r1);
		r.addAll(r.subList(1,r.size()));
		for(int i=0;i<r.size();i++)
		{
			System.out.println(r.get(i).getName());
		}
	}
	
}