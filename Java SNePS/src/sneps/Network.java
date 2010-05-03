package sneps;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Stack;

import match.ds.*;

/**
 * The Network class is the main class for the network management system. this class 
 * contains relations, case frames as well as the nodes that the network consists of.
 * 
 * @author Amr Khaled Dawood
 */
@SuppressWarnings("serial")
public class Network implements Serializable
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
	 * the instance of the class Network(assuming a Network may have only one instance).
	 */
	private static Network instance = null;
	
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
		instance = this;
	}

	/**
	 * @return the instance of the class Network.
	 */
	public static Network getInstance()
	{
		return instance;
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
	public void undefineRelation(String name)
	{
		Relation r = relations.get(name);
		
		// removing case frames that contain this relation
		for (Enumeration<CaseFrame> e = caseFrames.elements(); e.hasMoreElements();)
		{
			CaseFrame caseFrame = e.nextElement();
			if(caseFrame.getRelations().contains(r))
				caseFrames.remove(caseFrame.getId());
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
		String relString = new CaseFrame(semanticType,relationSet).getId();
		if(caseFrames.containsKey(relString))
			throw new CustomException("the case frame already exists");
		else
			caseFrames.put(relString,new CaseFrame(semanticType,relationSet));
		
		return caseFrames.get(relString);
	}
	
	/**
	 * @param id the id of the case frame that we want to remove
	 */
	public void undefineCaseFrame(String id)
	{
		caseFrames.remove(id);
	}

	/**
	 * @param relation the relation that we need to define the path for
	 * @param path the path that we need to define
	 */
	public void definePath(Relation relation,Path path)
	{
		relation.setPath(path);
	}
	
	/**
	 * @param relation the relation that we need to undefine its path
	 */
	public void undefinePath(Relation relation)
	{
		relation.setPath(null);
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
		if(exists(array))
			throw new CustomException("cable set already exists");
		if(isToBePattern(array))
			return createPatNode(array,caseFrame);
		else
			return createMolNode(array,caseFrame);
	}
	
	public boolean match(Node u,Node t,Substitutions r)
	{
		System.out.println("match    >>>>> 1");
		if(hERe(u,t,r,true))
		{
			return true;
		}
		return false;
	}
	
	public boolean hERe(Node u,Node t,Substitutions rSub,boolean rightOrder)
	{
		System.out.println("here    >>>>> 1");
		// if not the same constants
		if((u.getClass().getSimpleName().equals("BaseNode") &&
				t.getClass().getSimpleName().equals("ClosedNode")) || 
				(u.getClass().getSimpleName().equals("ClosedNode") &&
				t.getClass().getSimpleName().equals("BaseNode")) ||
				(u.getClass().getSimpleName().equals("BaseNode") &&
				t.getClass().getSimpleName().equals("BaseNode") &&
				t != u)||(t != u && 
				u.getClass().getSimpleName().equals("ClosedNode") &&
				t.getClass().getSimpleName().equals("ClosedNode")))
		{
			System.out.println("here    >>>>> 2");
			return false;
		}
		// if one is variable
		if(u.getClass().getSimpleName().equals("VariableNode"))
		{
			System.out.println("here    >>>>> 3");
			varHERe((VariableNode) u,t,rSub,true);
		}else
		{
			if(t.getClass().getSimpleName().equals("VariableNode"))
			{
				System.out.println("here    >>>>> 4");
				varHERe((VariableNode) t,u,rSub,false);
			}else
			{
				// if both are molecular nodes
				if(u.getClass().getSimpleName().equals("PatternNode") ||
						t.getClass().getSimpleName().equals("PatternNode"))
				{
					System.out.println("here    >>>>> 5");
					MolecularNode t1 = (MolecularNode) u;
					MolecularNode t2 = (MolecularNode) t;
					if(t1.getCableSet().getCaseFrame() != t2.getCableSet().getCaseFrame())
						return false;
					// checking the node sets in the cables
					for(int i=0;i<t1.getCableSet().getCables().size();i++)
					{
						System.out.println("here    >>>>> 6");
						Relation r = t1.getCableSet().getCables().get(i).getRelation();
						NodeSet ns1 = t1.getCableSet().getCable(r.getName()).getNodeSet();
						NodeSet ns2 = t2.getCableSet().getCable(r.getName()).getNodeSet();
						if(rightOrder && ((r.getAdjust().equals("reduce") &&
							ns1.getNodes().size() > ns2.getNodes().size()) ||
							(r.getAdjust().equals("expand") &&
							ns1.getNodes().size() < ns2.getNodes().size()) ||
							(r.getAdjust().equals("none") &&
							ns1.getNodes().size() != ns2.getNodes().size())))
							return false;
						if((!rightOrder) && ((r.getAdjust().equals("expand") &&
								ns1.getNodes().size() > ns2.getNodes().size()) ||
								(r.getAdjust().equals("reduce") &&
								ns1.getNodes().size() < ns2.getNodes().size()) ||
								(r.getAdjust().equals("none") &&
								ns1.getNodes().size() != ns2.getNodes().size())))
								return false;
						if(setUnify(ns1,ns2,rSub,rightOrder))
						{
							System.out.println("here    >>>>> 7");
							continue;
						}
					}
				}
			}
		}
		return true;
	}
	
	public boolean setUnify(NodeSet ns1,NodeSet ns2,Substitutions rSub,boolean rightOrder)
	{
		System.out.println("setunify    >>>>> 1");
		if(ns1.getNodes().size() == 0 || ns2.getNodes().size() == 0)
			return true;
		// loop for ns1
		for(int i=0;i<ns1.getNodes().size();i++)
		{
			System.out.println("setunify    >>>>> 2");
			Node n1 = ns1.getNodes().get(i);
			NodeSet n1Others = new NodeSet();
			n1Others.getNodes().addAll(ns1.getNodes());
			n1Others.removeNode(n1);
			// loop for ns2
			for(int j=0;j<ns2.getNodes().size();j++)
			{
				System.out.println("setunify    >>>>> 3");
				Node n2 = ns2.getNodes().get(j);
				NodeSet n2Others = new NodeSet();
				n2Others.getNodes().addAll(ns2.getNodes());
				n2Others.removeNode(n2);
				Substitutions s = new Substitutions();
				s.getSub().addAll(rSub.getSub());
				if(match(n1,n2,s))
				{
					if(setUnify(n1Others,n2Others,s,rightOrder))
					{
						System.out.println("setunify    >>>>> 4");
						rSub.getSub().removeAllElements();
						rSub.getSub().addAll(s.getSub());
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean varHERe(VariableNode u,Node t,Substitutions rSub,boolean rightOrder)
	{
		System.out.println("varhere    >>>>> 1  " + u.getIdentifier() + t.getIdentifier());
		if(! rSub.contains(u))
		{
			System.out.println("varhere    >>>>> 2  " + u.getIdentifier());
			rSub.putIn(new Binding(u,t));
			System.out.println("size >>>>>>>>>>>>>>  "+rSub.getSub().size());
			return true;
		}
		if(! t.getClass().getSimpleName().equals("VariableNode"))
		{
			System.out.println("varhere    >>>>> 3");
			Stack<VariableNode> path = source(u,rSub);
			VariableNode v = path.pop();
			collapse(path,v,rSub);
			if(! rSub.contains(v))
			{
				rSub.putIn(new Binding(v,t));
				return true;
			}
			if(recurHERe(v,(Node) rSub.getBindingByMv(v).getMn(),t,rSub,rightOrder))
				return true;
		}else
		{
			System.out.println("varhere    >>>>> 4");
			VariableNode tt = (VariableNode) t;
			if(! rSub.contains(tt))
			{
				rSub.putIn(new Binding(t,u));
				return true;
			}
			System.out.println("varhere    >>>>> 5");
			Stack<VariableNode> path = source(u,rSub);
			VariableNode v = path.pop();
			if(! rSub.contains(v))
			{
				path.push(v);
				collapse(path,t,rSub);
				return true;
			}else
			{
				System.out.println("varhere    >>>>> 6");
				if(rSub.getBindingByMv(v).getMn().equals(v))
				{
					path.push(v);
					collapse(path,t,rSub);
					return true;
				}else
				{
					System.out.println("varhere    >>>>> 7");
					Stack<VariableNode> path2 = source(tt,rSub);
					VariableNode w = path2.pop();
					if(v.equals(w))
					{
						path.addAll(path2);
						collapse(path,v,rSub);
						return true;
					}else{
						System.out.println("varhere    >>>>> 8");
						Node z = (Node) rSub.getBindingByMv(w).getMn();
						path.push(w);
						collapse(path,v,rSub);
						if(rSub.contains(z))
						{
							if(! z.equals(w))
							{
								if(
							recurHERe(v,(Node)rSub.getBindingByMv(v).getMn(),z,rSub,rightOrder))
								{
									return true;
								}
							}
						}
					}
				}
			}
		}
		System.out.println("varhere    >>>>> 9");
		return false;
	}
	
	public Stack<VariableNode> source(VariableNode x,Substitutions rSub)
	{
		System.out.println("source    >>>>> 1");
		Stack<VariableNode> path = new Stack<VariableNode>();
		path.push(x);
		while(true)
		{
			VariableNode v = path.peek();
			if(rSub.contains(v) &&
			rSub.getBindingByMv(v).getMn().getClass().getSimpleName().equals("VariableNode") &&
			!rSub.getBindingByMv(v).getMn().equals(v))
			{
				path.push((VariableNode) rSub.getBindingByMv(v).getMn());
				rSub.getBindingByMv(v).setMn(v);
			}else
				break;
		}
		if(rSub.getBindingByMv(path.peek()).getMn().equals(path.peek()))
		{
			path.pop();
		}
		return path;
	}
	
	public void collapse(Stack<VariableNode> path,Node v,Substitutions rSub)
	{
		System.out.println("collapse    >>>>> 1");
		for(int i=0;i<path.size();i++)
		{
			if(rSub.contains(path.get(i)))
			{
				rSub.getBindingByMv(path.get(i)).setMn(v);
			}else
				rSub.putIn(new Binding(path.get(i),v));
		}
	}
	
	public boolean recurHERe(VariableNode v,Node y,Node t,Substitutions rSub,boolean rightOrder)
	{
		System.out.println("recurhere    >>>>> 1");
		boolean flag = false;
		v.setLoop(true);
		if(hERe(y,t,rSub,rightOrder))
		{
			if(rSub.contains(v))
			{
				rSub.getBindingByMv(v).setMn(y);
			}else
			{
				rSub.putIn(new Binding(v,y));
			}
			flag = true;
		}
		v.setLoop(false);
		return flag;
	}
	
	/**
	 * @param array the array that contains pairs of paths and node sets
	 * @return the node set of nodes that we can start following those paths in the array
	 * from, in order to reach at least one node at each node set in all entries of the 
	 * array 
	 */
	public NodeSet find(Object[][] array)
	{
		return findIntersection(array,0);
	}
	
	/**
	 * @param array the array that contains pairs of paths and node sets
	 * @return the node set of non-variable nodes that we can start following those paths 
	 * in the array from, in order to reach at least one node at each node set in all
	 * entries of the array 
	 */
	public NodeSet findConstant(Object[][] array)
	{
		NodeSet result = new NodeSet();
		NodeSet nodeSet = find(array);
		LinkedList<Node> nodes = nodeSet.getNodes();
		for(int i=0;i<nodes.size();i++)
		{
			if(! nodes.get(i).getClass().getSimpleName().equals("VariableNode"))
				result.addNode(nodes.get(i));
		}
		return result;
	}
	
	/**
	 * @param array the array that contains pairs of paths and node sets
	 * @return the node set of base nodes that we can start following those paths 
	 * in the array from, in order to reach at least one node at each node set in all
	 * entries of the array 
	 */
	public NodeSet findBase(Object[][] array)
	{
		NodeSet result = new NodeSet();
		NodeSet nodeSet = find(array);
		LinkedList<Node> nodes = nodeSet.getNodes();
		for(int i=0;i<nodes.size();i++)
		{
			if(nodes.get(i).getClass().getSimpleName().equals("BaseNode"))
				result.addNode(nodes.get(i));
		}
		return result;
	}
	
	/**
	 * @param array the array that contains pairs of paths and node sets
	 * @return the node set of variable nodes that we can start following those paths 
	 * in the array from, in order to reach at least one node at each node set in all
	 * entries of the array 
	 */
	public NodeSet findVariable(Object[][] array)
	{
		NodeSet result = new NodeSet();
		NodeSet nodeSet = find(array);
		LinkedList<Node> nodes = nodeSet.getNodes();
		for(int i=0;i<nodes.size();i++)
		{
			if(nodes.get(i).getClass().getSimpleName().equals("VariableNode"))
				result.addNode(nodes.get(i));
		}
		return result;
	}
	
	/**
	 * @param array the array that contains pairs of paths and node sets
	 * @return the node set of pattern nodes that we can start following those paths 
	 * in the array from, in order to reach at least one node at each node set in all
	 * entries of the array 
	 */
	public NodeSet findPattern(Object[][] array)
	{
		NodeSet result = new NodeSet();
		NodeSet nodeSet = find(array);
		LinkedList<Node> nodes = nodeSet.getNodes();
		for(int i=0;i<nodes.size();i++)
		{
			if(nodes.get(i).getClass().getSimpleName().equals("PatternNode"))
				result.addNode(nodes.get(i));
		}
		return result;
	}
	
	/**
	 * @param path the path that can be followed to get to one of the nodes specified
	 * @param nodeSet the nodes that can be reached by following the path
	 * @return a node set of nodes that we can start following the path from in order to
	 * get to one of the nodes in the specified node set
	 */
	@SuppressWarnings("unchecked")
	private NodeSet findUnion(Path path,NodeSet nodeSet)
	{
		LinkedList<Node> nodeList = (LinkedList<Node>) nodeSet.getNodes().clone();
		if(nodeList.isEmpty())
			return new NodeSet();
		
		Node node = nodeList.removeFirst();
		NodeSet ns = new NodeSet(nodeList);
		
		return Union(path.followConverse(node),findUnion(path,ns));
	}
	
	/**
	 * @param array the array that contains pairs of paths and node sets
	 * @param index the index of the array at which we should start traversing it
	 * @return the node set of nodes that we can start following those paths in the array
	 * from, in order to reach at least one node of node sets at each path-nodeset pair. 
	 */
	private NodeSet findIntersection(Object[][] array,int index)
	{
		if(index == array.length)
			return new NodeSet();
		
		Path path = (Path) array[index][0];
		NodeSet nodeSet = (NodeSet) array[index][1];
		
		return intersection(findUnion(path,nodeSet),findIntersection(array,++index));
	}
	
	/**
	 * this method checks whether a CableSet was build before in the notwork or not
	 * 
	 * @param array a 2D array of relations and nodes that we want to check it it represents
	 * a cable set that already exists in the network
	 * @return true if the CableSet exists and false otherwise
	 */
	private boolean exists(Object[][] array)
	{
		NodeSet result = new NodeSet();
		Node node = (Node) array[0][1];
		UpCableSet upCableSet = node.getUpCableSet();
		Relation relation = (Relation) array[0][0];
		if(! upCableSet.contains(relation))
			return false;
		UpCable upCable = upCableSet.getUpCable(relation);
		NodeSet ns = upCable.getNodeSet();
		result.getNodes().addAll(ns.getNodes());
		
		for(int i=1;i<array.length;i++)
		{
			node = (Node) array[i][1];
			upCableSet = node.getUpCableSet();
			relation = (Relation) array[i][0];
			if(! upCableSet.contains(relation))
				return false;
			upCable = upCableSet.getUpCable(relation);
			ns = upCable.getNodeSet();
			result = intersection(result,ns);
		}
		
		if(result.getNodes().size() == 1)
			return true;
		return false;
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
	private boolean isToBePattern(Object[][] array)
	{
		for(int i=0;i<array.length;i++)
		{
			if(array[i][1].getClass().getSimpleName().equals("NodeSet"))
				continue;
			Relation r = (Relation) array[i][0];
			Node node = (Node) array[i][1];
			if(node.getClass().getSimpleName().equals("VariableNode") && 
					! r.isQuantifier())
				return true;
			if(node.getClass().getSimpleName().equals("PatternNode"))
			{
				PatternNode patternNode = (PatternNode) node;
				LinkedList<VariableNode> varNodes = patternNode.getFreeVariables();
				for(int j=0;j<array.length;j++)
				{
					Relation re = (Relation) array[j][0];
					Node no = (Node) array[j][1];
					if(varNodes.contains(no) && !re.isQuantifier())
						{
						return true;}	
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
	private ClosedNode createMolNode(Object[][] array,CaseFrame caseFrame)
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
	private PatternNode createPatNode(Object[][] array,CaseFrame caseFrame)
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
	private Object[][] turnIntoRelNodeSet(Object[][] array)
	{
		int relCount = 0;
		boolean exists = false;
		Object[][] tempResult = new Object[array.length][array[0].length];
		for(int i=0;i<array.length;i++)
		{
			Relation r = (Relation) array[i][0];
			NodeSet nos = new NodeSet();
			if(! array[i][1].getClass().getSimpleName().equals("NodeSet"))
			{
				nos.addNode((Node) array[i][1]);
			}
			for(int j=0;j<relCount;j++)
			{
				if(r.equals((Relation) tempResult[j][0]))
				{
					NodeSet ns = (NodeSet) tempResult[j][1];
					ns.getNodes().addAll(nos.getNodes());
					exists = true;
					break;
				}else
					exists = false;
			}
			if(! exists)
			{
				exists = false;
				NodeSet nodeSet = new NodeSet();
				nodeSet.getNodes().addAll(nos.getNodes());
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
	 * @return a String representing the closed node name that we can create with it
	 */
	private String getNextMolName()
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
	private String getNextPatName()
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
	private int isMolName(String identifier)
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
	private int isPatName(String identifier)
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
	private boolean isInt(char c)
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
	
	/**
	 * this method gets the union of two node sets
	 * 
	 * @param ns1 the first node set
	 * @param ns2 the second node set
	 * @return the union of the two node sets
	 */
	private NodeSet Union(NodeSet ns1,NodeSet ns2)
	{
		NodeSet result = new NodeSet();
		result.getNodes().addAll(ns1.getNodes());
		addWithNoRepeation(ns2,result);
		return result;
	}
	
	/**
	 * this method gets the intersection between two node sets
	 * 
	 * @param ns1 the first node set
	 * @param ns2 the second node set
	 * @return the intersection nodes of those two node sets
	 */
	private NodeSet intersection(NodeSet ns1,NodeSet ns2)
	{
		NodeSet result = new NodeSet();
		LinkedList<Node> n1 = ns1.getNodes();
		LinkedList<Node> n2 = ns2.getNodes();
		for(int i=0;i<n1.size();i++)
		{
			if(n2.contains(n1.get(i)))
				result.addNode(n1.get(i));
		}
		
		return result;
	}
	
	/**
	 * @param source the node set containing nodes we want to add to the other node set
	 * @param destination the node set containing nodes we want to add the nodes to
	 */
	private void addWithNoRepeation(NodeSet source,NodeSet destination)
	{
		for(int i=0;i<source.getNodes().size();i++)
		{
			Node n = source.getNodes().get(i);
			if(! destination.getNodes().contains(n))
				destination.getNodes().add(n);
		}
	}
	
	public static void main(String [] args) throws Exception
	{
//		Relation r1 = new Relation("member","entity","reduce",0);
//		Relation r2 = new Relation("class","entity","reduce",0);
		Object[][] o4 = new Object[4][2];
		Object[][] o3 = new Object[3][2];
		Object[][] o2 = new Object[2][2];
		/*o[0][0] = r1;
		o[1][0] = r1;
		o[2][0] = r1;
		o[3][0] = r2;
		o[4][0] = r1;
		o[0][1] = new BaseNode("amr");
		o[1][1] = new BaseNode("moh");
		o[2][1] = new BaseNode("zay");
		o[0][1] = new BaseNode("amr");
		o[1][1] = new BaseNode("human");*/
		Network n = new Network();
		Node x1 = n.buildVariableNode("x1");
		Node x2 = n.buildVariableNode("x2");
		Node x3 = n.buildVariableNode("x3");
		Node x4 = n.buildVariableNode("x4");
		Node x5 = n.buildVariableNode("x5");
		Node x6 = n.buildVariableNode("x6");
		Node x7 = n.buildVariableNode("x7");
		Node x8 = n.buildVariableNode("x8");
		Node a = n.build("a");
		Relation rr1 = n.defineRelation("r1","entity","none",0);
		Relation rr2 = n.defineRelation("r2","entity","none",0);
		Relation rr3 = n.defineRelation("r3","entity","none",0);
		Relation rr4 = n.defineRelation("r4","entity","none",0);
		LinkedList<Relation> l2 = new LinkedList<Relation>();
		l2.add(rr1);
		l2.add(rr2);
		LinkedList<Relation> l3 = new LinkedList<Relation>();
		l3.add(rr1);
		l3.add(rr2);
		l3.add(rr3);
		LinkedList<Relation> l4 = new LinkedList<Relation>();
		l4.add(rr1);
		l4.add(rr2);
		l4.add(rr3);
		l4.add(rr4);
		CaseFrame caseFrame2 = n.defineCaseFrame("entity",l2);
		CaseFrame caseFrame3 = n.defineCaseFrame("entity",l3);
		CaseFrame caseFrame4 = n.defineCaseFrame("entity",l4);
		o3[0][0] = rr1;
		o3[1][0] = rr2;
		o3[2][0] = rr3;
		o3[0][1] = x1;
		o3[1][1] = x2;
		o3[2][1] = x3;
		Node h1 = n.build(o3,caseFrame3);
		o3[0][1] = x6;
		o3[1][1] = x7;
		o3[2][1] = x8;
		Node h2 = n.build(o3,caseFrame3);
		o4[0][0] = rr1;
		o4[1][0] = rr2;
		o4[2][0] = rr3;
		o4[3][0] = rr4;
		o4[0][1] = h1;
		o4[1][1] = h2;
		o4[2][1] = x3;
		o4[3][1] = x6;
		Node t = n.build(o4,caseFrame4);
		o2[0][0] = rr1;
		o2[1][0] = rr2;
		o2[0][1] = x4;
		o2[1][1] = x5;
		Node g1 = n.build(o2,caseFrame2);
		o3[0][1] = g1;
		o3[1][1] = x1;
		o3[2][1] = x2;
		Node h3 = n.build(o3,caseFrame3);
		o3[0][1] = x7;
		o3[1][1] = x8;
		o3[2][1] = x6;
		Node h4 = n.build(o3,caseFrame3);
		o2[0][1] = x5;
		o2[1][1] = a;
		Node g2 = n.build(o2,caseFrame2);
		o4[0][1] = h3;
		o4[1][1] = h4;
		o4[2][1] = g2;
		o4[3][1] = x5;
		Node tdash = n.build(o4,caseFrame4);
		Substitutions r = new Substitutions();
		if(n.match(t,tdash,r))
		{
			System.out.println(r.getSub().size());
			for(int i=0;i<r.getSub().size();i++)
			{
				System.out.print(((Node) r.getSub().get(i).getMv()).getIdentifier());
				System.out.println(" "+((Node) r.getSub().get(i).getMn()).getIdentifier());
			}
		}
		/*VariableNode v1 = new VariableNode("V1");
		VariableNode v2 = new VariableNode("V2");
		VariableNode v3 = new VariableNode("V3");
		VariableNode v4 = new VariableNode("V4");
		VariableNode v5 = new VariableNode("V5");
		Stack<VariableNode> s = new Stack<VariableNode>();
		s.push(v1);
		s.push(v2);
		s.push(v3);
		s.push(v4);
		s.push(v5);
		BaseNode b = new BaseNode("amr");
		Substitutions z = new Substitutions();
		z.putIn(new Binding(v1,v2));
		z.putIn(new Binding(v2,v1));
		z.putIn(new Binding(v3,v4));
		z.putIn(new Binding(v4,v5));
		z.putIn(new Binding(v5,v1));
		Stack<VariableNode> x = n.source(v3,z);
		for(int i=0;i<x.size();i++)
		{
			System.out.println(x.get(i).getIdentifier());
		}*/
	//	Node res = n.build(o,caseFrame);
		/*System.out.println(res.getIdentifier());
		System.out.println(n.getNodes().get(res.getIdentifier()).getIdentifier());
		System.out.println(((PatternNode) res1).getFreeVariables().size());*/
		/*for(Enumeration<Relation> e = n.relations.elements();e.hasMoreElements();)
			System.out.println("r:" +e.nextElement().getName());
		for(Enumeration<CaseFrame> e = n.caseFrames.elements();e.hasMoreElements();)
			System.out.println(e.nextElement().getId());
		n.undefineRelation("member");
		for(Enumeration<Relation> e = n.relations.elements();e.hasMoreElements();)
			System.out.println("r:" +e.nextElement().getName());
		for(Enumeration<CaseFrame> e = n.caseFrames.elements();e.hasMoreElements();)
			System.out.println(e.nextElement().getId());
		*/
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
		/*LinkedList<Relation> r = new LinkedList<Relation>();
		r.add(r1);
		r.add(r1);
		r.add(r1);
		r.addAll(r.subList(1,r.size()));
		for(int i=0;i<r.size();i++)
		{
			System.out.println(r.get(i).getName());
		}*/
	}
	
}