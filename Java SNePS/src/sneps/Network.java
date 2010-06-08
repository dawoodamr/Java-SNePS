package sneps;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Stack;

import snebr.Context;
import snebr.Support;
import snip.ds.Channel;
import snip.ds.ReportSet;
import snip.ds.Request;
import snip.fns.*;

import match.ds.*;

/**
 * The Network class is the main class for the network management system. This class 
 * contains relations, case frames as well as the nodes that exist in the network.
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
	 * the hash table containing molecular nodes in the network indexed by the id's
	 * of their case frames
	 */
	private Hashtable<String,NodeSet> molecularNodes;
	
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
	 * named by 'M' followed by a positive number, for example: "M12".
	 */
	private int molCounter;
	
	/**
	 * the counter used for names of pattern molecular nodes. each pattern molecular node is
	 * named by 'P' followed by a positive number, for example: "P10" 
	 */
	private int patCounter;
	
	/**
	 * the counter used for names of variable nodes. each variable node is
	 * named by 'V' followed by a positive number, for example: "V4" 
	 */
	private int varCounter;
	
	/**
	 * a list of numbers that the user used in naming nodes he created that look like 
	 * names of closed molecular nodes' names.this list is important in order to avoid 
	 * using them again.
	 */
	private LinkedList<Integer> userDefinedMolPostfix;
	
	/**
	 * a list of numbers that the user used in naming nodes he created that look like 
	 * names of pattern molecular nodes' names.this list is important in order to avoid 
	 * using them again.
	 */
	private LinkedList<Integer> userDefinedPatPostfix;
	
	/**
	 * a list of numbers that the user used in naming nodes he created that look like 
	 * names of variable nodes' names.this list is important in order to avoid 
	 * using them again.
	 */
	private LinkedList<Integer> userDefinedVarPostfix;
	
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
		molecularNodes = new Hashtable<String,NodeSet>();
		caseFrames = new Hashtable<String, CaseFrame>();
		relations = new Hashtable<String, Relation>();
		userDefinedMolPostfix = new LinkedList<Integer>();
		userDefinedPatPostfix = new LinkedList<Integer>();
		userDefinedVarPostfix = new LinkedList<Integer>();
		molCounter = 0;
		patCounter = 0;
		varCounter = 0;
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
	 * @return the hash table of molecular nodes
	 */
	public Hashtable<String,NodeSet> getMolecularNodes()
	{
		return molecularNodes;
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
	 * @return a LinkedList of Integers that the user used to name nodes that 
	 * look like variable nodes' names
	 */
	public LinkedList<Integer> getUserDefinedVarPostfix()
	{
		return userDefinedVarPostfix;
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
	 * @param array the 2D array of Objects that represents relation-node pairs for the cable set
	 * @return the molecular node that have such a cable set
	 * @throws CustomException if the node does not exist
	 */
	public MolecularNode getMolecularNode(Object[][] array)throws CustomException
	{
		if(! cableSetExists(array))
			throw new CustomException("the node does not exist");
		else
		{
			int counter = 0;
			NodeSet intersection = new NodeSet();
			while(true)
			{
				if(array[counter][1].getClass().getSimpleName().equals("NodeSet"))
					counter++;
				else
				{
					String r = ((Relation) array[counter][0]).getName();
					NodeSet ns = ((Node) array[counter][1]).getUpCableSet().getUpCable(r).getNodeSet();
					intersection.addAll(ns);
					break;
				}
			}
			for(int i=counter;i<array.length;i++)
			{
				if(array[i][1].getClass().getSimpleName().equals("NodeSet"))
					continue;
				else
				{
					Relation r1 = (Relation) array[i][0];
					NodeSet ns1 = ((Node) array[i][1]).getUpCableSet().getUpCable(r1).getNodeSet();
					intersection = intersection.Intersection(ns1);
				}
			}
			return (MolecularNode) intersection.getNode(0);
		}
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
		CaseFrame caseFrame = new CaseFrame(semanticType,relationSet);
		if(caseFrames.containsKey(caseFrame.getId()))
			throw new CustomException("the case frame already exists");
		else
		{
			caseFrames.put(caseFrame.getId(),caseFrame);
			if(! this.molecularNodes.containsKey(caseFrame.getId()))
				this.molecularNodes.put(caseFrame.getId(),new NodeSet());
		}
		
		return caseFrames.get(caseFrame.getId());
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
			molecularNodes.get(m.getCableSet().getCaseFrame().getId()).removeNode(node);
			CableSet cableSet = m.getCableSet();
			// loop for cables
			for(int i=0;i<cableSet.size();i++)
			{
				Cable cable = cableSet.getCable(i);
				NodeSet ns = cable.getNodeSet();
				//loop for nodes in the node set
				for(int j=0;j<ns.size();j++)
				{
					Node n = ns.getNode(j);
					// loop for UpCables
					for(int k=0;k<n.getUpCableSet().size();k++)
					{
						UpCable upCable = n.getUpCableSet().getUpCable(k);
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
	 * this method checks whether a CableSet was build before in the network or not
	 * 
	 * @param array a 2D array of relations and nodes that we want to check it it represents
	 * a cable set that already exists in the network
	 * @return true if the CableSet exists and false otherwise
	 */
	public boolean cableSetExists(Object[][] array)
	{
		int counter = 0;
		NodeSet result = new NodeSet();
		while(true)
		{
			if(array[counter][1].getClass().getSimpleName().equals("NodeSet"))
				counter++;
			else
			{
				break;
			}
		}
		Node node = (Node) array[counter][1];
		UpCableSet upCableSet = node.getUpCableSet();
		Relation relation = (Relation) array[counter][0];
		if(! upCableSet.contains(relation))
			return false;
		UpCable upCable = upCableSet.getUpCable(relation);
		NodeSet ns = upCable.getNodeSet();
		result.addAll(ns);
		
		for(int i=counter;i<array.length;i++)
		{
			if(array[i][1].getClass().getSimpleName().equals("NodeSet"))
				continue;
			node = (Node) array[i][1];
			upCableSet = node.getUpCableSet();
			relation = (Relation) array[i][0];
			if(! upCableSet.contains(relation))
				return false;
			upCable = upCableSet.getUpCable(relation);
			ns = upCable.getNodeSet();
			result = result.Intersection(ns);
		}
		
		if(result.size() == 1)
			return true;
		return false;
	}
	
	/**
	 * @param identifier the name of the variable node as the user specified
	 * @return the variable node that was just created
	 * @throws CustomException an exception is thrown if the node already exists
	 */
	public Node buildVariableNode()
	{
		VariableNode node = new VariableNode(getNextVarName());
		return node;
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
			if(isVarName(identifier)>-1)
				userDefinedVarPostfix.add(new Integer(isVarName(identifier)));
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
	public MolecularNode build(Object[][] array,CaseFrame caseFrame)throws CustomException
	{
		if(cableSetExists(array))
			throw new CustomException("cable set already exists");
		if(isToBePattern(array))
			return createPatNode(array,caseFrame);
		else
			return createMolNode(array,caseFrame);
	}
	
	/**
	 * Matches a given molecular node with nodes in the network that have the same case frame 
	 * and returns a list of triples (the matching node, the source bindings and the target bindings
	 * 
	 * @param node a MolecularNode to match with nodes in the network
	 * @return a LinkedList of 1D array of Objects of size 3 each. Index 0 in the array is the source 
	 * node itself, index 1 is the source bindings, and index 2 is the target bindings.
	 */
	public LinkedList<Object[]> match(MolecularNode node)
	{
		LinkedList<Object[]> result = new LinkedList<Object[]>();
		
		NodeSet ns = this.getMolecularNodes().get(node.getCableSet().getCaseFrame().getId());
		for(int i=0;i<ns.size();i++)
		{
			MolecularNode m = (MolecularNode) ns.getNode(i);
			if(m.equals(node))
				continue;
			LinkedList<Substitutions> rList = new LinkedList<Substitutions>();
			rList.add(new Substitutions());
			if(hERe(m,node,rList,true))
			{
				// vere
				for(int j=0;j<rList.size();j++)
				{
					Substitutions r = rList.get(j);
					Substitutions s = new Substitutions();
					NodeSet ns1 = new NodeSet();
					NodeSet ns2 = new NodeSet();
					getVars(m,ns1);
					getVars(node,ns2);
					Substitutions sb = new Substitutions();
					Substitutions tb = new Substitutions();
					boolean flag = true;
					for(int k=0;k<ns1.size();k++)
					{
						Node n = vERe((VariableNode) ns1.getNode(k),r,s);
						if(n == null)
						{
							flag = false;
						}else
						{
							sb.putIn(new Binding((VariableNode) ns1.getNode(k),n));
						}
					}
					for(int k=0;k<ns2.size();k++)
					{
						Node n = vERe((VariableNode) ns2.getNode(k),r,s);
						if(n == null)
						{
							flag = false;
						}else
						{
							tb.putIn(new Binding((VariableNode) ns2.getNode(k),n));
						}
					}
					if(flag)
					{
						Object[] o = new Object[3];
						o[0] = m;
						o[1] = sb;
						o[2] = tb;
						result.add(o);
					}
					/*if(termVERe(m,r,s) != null && termVERe(node,r,s) != null)
					{
						for(int k=0;k<s.cardinality();k++)
						{
							Binding b = s.getBinding(k);
							if(ns1.contains(b.getVariable()))
							{
								sb.putIn(b);
							}else
							{
								if(ns2.contains(b.getVariable()))
								{
									tb.putIn(b);
								}
							}
						}
						System.out.println("                 amr");
						Object[] o = new Object[3];
						o[0] = m;
						o[1] = sb;
						o[2] = tb;
						result.add(o);
					}*/
				}
				// separate and add to list
			}
		}
		
		return result;
	}
	
	/**
	 * updates the nodeSet by adding to it the variables dominated by the given node
	 * 
	 * @param node a MolecularNode to get the Variables dominated by it
	 * @param nodeSet a NodeSet to put the variables in
	 */
	public void getVars(MolecularNode node,NodeSet nodeSet)
	{
		CableSet cs = node.getCableSet();
		for(int i=0;i<cs.size();i++)
		{
			Cable c = cs.getCable(i);
			NodeSet ns = c.getNodeSet();
			for(int j=0;j<ns.size();j++)
			{
				Node n = ns.getNode(j);
				if(n.getClass().getSimpleName().equals("VariableNode"))
				{
					nodeSet.addNode(n);
				}else
				{
					if(n.getClass().getSuperclass().getSimpleName().equals("MolecularNode"))
						getVars((MolecularNode) n,nodeSet);
				}
			}
		}
	}
	
	public boolean hERe(Node u,Node t,LinkedList<Substitutions> rList,boolean rightOrder)
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
				t.getClass().getSimpleName().equals("ClosedNode")) || 
				(u.getClass().getSimpleName().equals("PatternNode") && 
				t.getClass().getSimpleName().equals("BaseNode")) || 
				(t.getClass().getSimpleName().equals("PatternNode") && 
				u.getClass().getSimpleName().equals("BaseNode")))
		{
			System.out.println("here    >>>>> 2");
			return false;
		}
		// if one is variable
		if(u.getClass().getSimpleName().equals("VariableNode"))
		{
			System.out.println("here    >>>>> 3");
			if(! varHERe((VariableNode) u,t,rList,rightOrder))
				return false;
		}else
		{
			if(t.getClass().getSimpleName().equals("VariableNode"))
			{
				System.out.println("here    >>>>> 4");
				if(! varHERe((VariableNode) t,u,rList,!rightOrder))
				{
					return false;
				}
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
					for(int i=0;i<t1.getCableSet().size();i++)
					{
						System.out.println("here    >>>>> 6");
						Relation r = t1.getCableSet().getCable(i).getRelation();
						NodeSet ns1 = t1.getCableSet().getCable(r.getName()).getNodeSet();
						NodeSet ns2 = t2.getCableSet().getCable(r.getName()).getNodeSet();
						if(rightOrder && ((r.getAdjust().equals("reduce") &&
							ns2.size() > ns1.size()) ||
							(r.getAdjust().equals("expand") &&
							ns2.size() < ns1.size()) ||
							(r.getAdjust().equals("none") &&
							ns1.size() != ns2.size())))
							return false;
						if((!rightOrder) && ((r.getAdjust().equals("expand") &&
								ns2.size() > ns1.size()) ||
								(r.getAdjust().equals("reduce") &&
								ns2.size() < ns1.size()) ||
								(r.getAdjust().equals("none") &&
								ns1.size() != ns2.size())))
								return false;
						System.out.println("here    >>>>> 7");
						if(setUnify(ns1,ns2,rList,rightOrder))
						{
							System.out.println("here    >>>>> 8  size >>> "+rList.size());
							continue;
						}
						if(rList.size() == 0)
							return false;
					}
				}
			}
		}
		for(int i=0;i<rList.size();i++)
		{
			System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+i+" TEST :  ");
			Substitutions s = rList.get(i);
			System.out.print("( "+u.getIdentifier()+" "+t.getIdentifier()+" )  >>  ");
			for(int j=0;j<s.cardinality();j++)
			{
				System.out.print(s.getBinding(j).getVariable().getIdentifier()+"-"+s.getBinding(j).getNode().getIdentifier()+"    ");
			}
			System.out.println();
		}
		return true;
	}
	
	public boolean setUnify(NodeSet ns1,NodeSet ns2,LinkedList<Substitutions> r,boolean rightOrder)
	{
		System.out.println("setunify    >>>>> 1");
		if(ns1.size() == 0 || ns2.size() == 0)
			return true;
		boolean flag = false;
		// loop for ns1
		int size = r.size();
		for(int k=0;k<size;k++)
		{
			Substitutions rSub = new Substitutions();
			rSub.insert(r.get(0));
			r.remove(0);
			for(int i=0;i<ns1.size();i++)
			{
				System.out.println("setunify    >>>>> 2");
				Node n1 = ns1.getNode(i);
				NodeSet n1Others = new NodeSet();
				n1Others.addAll(ns1);
				n1Others.removeNode(n1);
				// loop for ns2
				for(int j=0;j<ns2.size();j++)
				{
					System.out.println("setunify    >>>>> 3");
					Node n2 = ns2.getNode(j);
					NodeSet n2Others = new NodeSet();
					n2Others.addAll(ns2);
					n2Others.removeNode(n2);
					Substitutions s = new Substitutions();
					for(int w=0;w<rSub.cardinality();w++)
					{
						s.putIn(new Binding(rSub.getBinding(w).getVariable(),rSub.getBinding(w).getNode()));
					}
					LinkedList<Substitutions> rr = new LinkedList<Substitutions>();
					rr.add(s);
					if(hERe(n1,n2,rr,rightOrder))
					{
						System.out.println("setunify    >>>>> 4");
						if(setUnify(n1Others,n2Others,rr,rightOrder))
						{
							System.out.println(r.size()+"setunify    >>>>> 5");
							for(int z=0;z<rr.size();z++)
							{
								r.add(rr.get(z));
							}

							System.out.println(r.size()+"setunify    >>>>> 6");
							flag = true;
						}
					}
				}
			}
		}
		return flag;
	}
	
	public boolean varHERe(VariableNode u,Node t,LinkedList<Substitutions> rList,boolean rightOrder)
	{
		System.out.println("varhere    >>>>> 1  " + u.getIdentifier() + t.getIdentifier());
		boolean flag = false;
		int size = rList.size();
		System.out.println(size);
		for(int i=0;i<size;i++)
		{
			Substitutions rSub = rList.get(0);
			rList.remove(0);
			if((! rSub.isBound(u)) && (!u.isRLoop()))
			{
				System.out.println(i+" varhere    >>>>> 2  " + u.getIdentifier());
				rSub.putIn(new Binding(u,t));
				u.setRLoop(false);
				System.out.println("size >>>>>>>>>>>>>>  "+rSub.cardinality());
				rList.add(rSub);
				flag = true;
			}else{
			if(! t.getClass().getSimpleName().equals("VariableNode"))
			{
				System.out.println(i+" varhere    >>>>> 3");
				Stack<VariableNode> path = source(u,rSub);
				if(! path.isEmpty())
				{
					VariableNode v = path.pop();
					collapse(path,v,rSub);
					if(((! rSub.isBound(v)) || (rSub.getBindingByVariable(v).getNode().equals(v))) && (! v.isRLoop()))
					{
						rSub.putIn(new Binding(v,t));
						v.setRLoop(false);
						rList.add(rSub);
						flag = true;
					}
					else{
					LinkedList<Substitutions> temp = new LinkedList<Substitutions>();
					temp.add(rSub);
					if(recurHERe(v,(Node) rSub.getBindingByVariable(v).getNode(),t,temp,rightOrder))
					{
						rList.addAll(temp);
						flag = true;
					}
					}
				}
			}else
			{
				System.out.println(i+" varhere    >>>>> 4");
				VariableNode tt = (VariableNode) t;
				if((! rSub.isBound(tt)) && (! tt.isRLoop()))
				{
					rSub.putIn(new Binding(tt,u));
					tt.setRLoop(false);
					rList.add(rSub);
					flag = true;
				}
				else
				{
				System.out.println(i+" varhere    >>>>> 5");
				Stack<VariableNode> path = source(u,rSub);
				if(! path.isEmpty())
				{
				VariableNode v = path.pop();
				if((! rSub.isBound(v)) && (! v.isRLoop()))
				{
					path.push(v);
					collapse(path,t,rSub);
					rList.add(rSub);
					flag = true;
				}else
				{
					System.out.println(i+" varhere    >>>>> 6");
					if(rSub.getBindingByVariable(v).getNode().equals(v) && (! v.isRLoop()))
					{
						path.push(v);
						collapse(path,t,rSub);
						rList.add(rSub);
						flag = true;
					}else
					{
						System.out.println(i+" varhere    >>>>> 7");
						Stack<VariableNode> path2 = source(tt,rSub);
						VariableNode w = path2.pop();
						path.addAll(path2);
						if(v.equals(w))
						{
							collapse(path,v,rSub);
							rList.add(rSub);
							flag = true;
						}else{
							System.out.println(i+" varhere    >>>>> 8");
							Node z = (Node) rSub.getBindingByVariable(w).getNode();
							path.push(w);
							collapse(path,v,rSub);
							if(z != null)
							{
								if((! z.equals(w)) && (! w.isRLoop()))
								{
									LinkedList<Substitutions> temp = new LinkedList<Substitutions>();
									temp.add(rSub);
									if(
								recurHERe(v,(Node)rSub.getBindingByVariable(v).getNode(),z,temp,rightOrder))
									{
										rList.addAll(temp);
										flag = true;
									}
								}
							}
						}
					}
				}
			}}}}
		}
		System.out.println("varhere    >>>>> 9");
		return flag;
	}
	
	public Stack<VariableNode> source(VariableNode x,Substitutions rSub)
	{
		System.out.println("source    >>>>> 1");
		Stack<VariableNode> path = new Stack<VariableNode>();
		path.push(x);
		while(true)
		{
			VariableNode v = path.peek();
			if(rSub.isBound(v) &&
			rSub.getBindingByVariable(v).getNode().getClass().getSimpleName().equals("VariableNode") &&
			!rSub.getBindingByVariable(v).getNode().equals(v))
			{
				path.push((VariableNode) rSub.getBindingByVariable(v).getNode());
				rSub.getBindingByVariable(v).setNode(v);
				v.setRLoop(false);
			}else
				break;
		}
		if(path.lastElement().equals(path.firstElement()) && path.size()>1)
		{
			path.pop();
		}
		System.out.print("             Path: ");
		for(int i=0;i<path.size()-1;i++)
		{
			System.out.print(path.get(i).getIdentifier()+" ");
		}
		for(int i=path.size()-1;i<path.size();i++)
		{
			System.out.println(path.get(i).getIdentifier());
		}
		return path;
	}
	
	public void collapse(Stack<VariableNode> path,Node v,Substitutions rSub)
	{
		System.out.println("collapse    >>>>> 1");
		for(int i=0;i<path.size();i++)
		{
			if(rSub.isBound(path.get(i)))
			{
				rSub.getBindingByVariable(path.get(i)).setNode(v);
				path.get(i).setRLoop(false);
			}else
				rSub.putIn(new Binding(path.get(i),v));
				path.get(i).setRLoop(false);
		}
		System.out.print("           collapse: ( ");
		for(int i=0;i<path.size();i++)
		{
			System.out.print(path.get(i).getIdentifier()+" ");
		}
		System.out.println(") > "+v.getIdentifier());
	}
	
	public boolean recurHERe(VariableNode v,Node y,Node t,LinkedList<Substitutions> rList,boolean rightOrder)
	{
		System.out.println("recurhere    >>>>> 1");
		boolean flag = false;
		v.setRLoop(true);
		if(hERe(y,t,rList,rightOrder))
		{
			for(int i=0;i<rList.size();i++)
			{
				Substitutions rSub = rList.get(i);
				if(rSub.isBound(v))
				{
					rSub.getBindingByVariable(v).setNode(y);
					v.setRLoop(false);
				}else
				{
					rSub.putIn(new Binding(v,y));
					v.setRLoop(false);
				}
			}
			flag = true;
		}
		v.setRLoop(false);
		return flag;
	}
	
	public Node vERe(VariableNode u,Substitutions r,Substitutions s)
	{
		//System.out.println("vere    >>>>> 1");
		Node z = null;
		Stack<VariableNode> path = source(u,r);
		VariableNode v = path.pop();
		if(! r.isBound(v))
		{
			//System.out.println("vere    >>>>> 2");
			z = v;
			r.putIn(new Binding(v,v)); // done
		}
		else
		{
			//System.out.println("vere    >>>>> 3");
			if(r.getBindingByVariable(v).getNode().getClass().getSimpleName().equals("BaseNode")
				|| r.getBindingByVariable(v).getNode().getClass().getSimpleName().equals("ClosedNode"))
			{
			//	System.out.println("vere    >>>>> 4");
				z = r.getBindingByVariable(v).getNode();
				if(! s.isBound(v))
					s.putIn(new Binding(v,z));
				s.getBindingByVariable(v).setNode(z);
				v.setSLoop(false);
				r.getBindingByVariable(v).setNode(v); // done
			}
			else
			{
				Node yy = r.getBindingByVariable(v).getNode();
				if(yy.getClass().getSimpleName().equals("PatternNode"))
				{
				//	System.out.println("vere    >>>>> 5");
					MolecularNode y = (MolecularNode) yy;
					r.getBindingByVariable(v).setNode(v); // done
					v.setSLoop(true);
					for(int i=0;i<path.size();i++)
					{
						path.get(i).setSLoop(true);
					}
					z = termVERe(y,r,s);
					if(z == null)
					{
						return null;
					}
					if(! s.isBound(v))
						s.putIn(new Binding(v,z));
					s.getBindingByVariable(v).setNode(z);
					v.setSLoop(false);
				}
				else
				{
					if(v.isSLoop())
					{
					//	System.out.println("vere    >>>>> 6");
						v.setSLoop(false);
						return null;
					}
					else
					{
						if(! s.isBound(v))
							z = v;
						else
							z = s.getBindingByVariable(v).getNode();
					}
				}
			}
		}
		for(int i=0;i<path.size();i++)
		{
			if(! s.isBound(path.get(i)))
				s.putIn(new Binding(path.get(i),z));
			s.getBindingByVariable(path.get(i)).setNode(z);
			v.setSLoop(false);
		}
		
		return z;
	}
	
	public MolecularNode termVERe(MolecularNode t,Substitutions r,Substitutions s)
	{
		//System.out.println("termvere    >>>>> 1");
		// list for pattern nodes substitutions
		LinkedList<LinkedList<Object>> temp = new LinkedList<LinkedList<Object>>();
		CableSet cs = t.getCableSet();
		for(int i=0;i<cs.size();i++)
		{
			Cable c = cs.getCable(i);
			Relation rel = c.getRelation();
			NodeSet ns = c.getNodeSet();
			if(ns.size() == 0)
			{
				LinkedList<Object> list = new LinkedList<Object>();
				list.add(rel);
				list.add(new NodeSet());
				temp.add(list);
				continue;
			}
			for(int j=0;j<ns.size();j++)
			{
				Node n = ns.getNode(j);
				if(n.getClass().getSimpleName().equals("BaseNode") 
						|| n.getClass().getSimpleName().equals("ClosedNode"))
				{
					LinkedList<Object> list = new LinkedList<Object>();
					list.add(rel);
					list.add(n);
					temp.add(list);
				}
				else
				{
					if(n.getClass().getSimpleName().equals("PatternNode"))
					{
						LinkedList<Object> list = new LinkedList<Object>();
						list.add(rel);
						list.add(termVERe((MolecularNode) n,r,s));
						temp.add(list);
					}
					else
					{
						if(n.getClass().getSimpleName().equals("VariableNode"))
						{
							VariableNode v = (VariableNode) n;
							// if done
							if(r.isBound(v) && r.getBindingByVariable(v).getNode().equals(v))
							{
								if(v.isSLoop())
								{
									return null;
								}
								else
								{
									// replace in t the subterm ti by s(ti)
									LinkedList<Object> list = new LinkedList<Object>();
									list.add(rel);
									list.add(s.getBindingByVariable(v).getNode());
									temp.add(list);
								}
							}
							else
							{
								if(r.isBound(v))
								{
									LinkedList<Object> list = new LinkedList<Object>();
									list.add(rel);
									list.add(vERe(v,r,s));
									temp.add(list);
								}
							}
						}
					}
				}
			}
		}
		// creating the array used in building
		Object[][] array = new Object[temp.size()][2];
		for(int i=0;i<array.length;i++)
		{
			LinkedList<Object> list = temp.get(i);
			array[i][0] = list.get(0);
			array[i][1] = list.get(1);
		}
		
		// building the substitution node
		MolecularNode molNode = null;
		try
		{
			molNode = build(array,t.getCableSet().getCaseFrame());
		} catch (CustomException e)
		{
			try
			{
				molNode = getMolecularNode(array);
			}catch (CustomException e1)
			{
				e1.printStackTrace();
			}
		}
		
		return molNode;
	}
	
	/**
	 * Added by Mohamed K. Gabr
	 * 
	 * Do the deduction process on the node n in the context c and return the 
	 * resulting reports set. 
	 * @param n MolecularNode
	 * @param c Context
	 * @return ReportSet 
	 */
	public ReportSet deduce(MolecularNode n,Context c)
	{
		QueuesProcessor qp=new QueuesProcessor();
		Request r=new Request(new Channel(null,null,c,null,true));
		n.getEntity().getProcess().setFirst(true);
		qp.addToLow(n.getEntity());
		n.getEntity().getProcess().setQueuesProcessor(qp);
		n.getEntity().getProcess().receiveRequest(r);
		qp.process();
		return n.getEntity().getProcess().getSentReports();
	}

	
	/**
	 * @param array the array that contains pairs of paths and node sets
	 * @return the node set of nodes that we can start following those paths in the array
	 * from, in order to reach at least one node at each node set in all entries of the 
	 * array 
	 */
	public Hashtable<Node,LinkedList<Support>> find(Object[][] array,Context context)
	{
		return findIntersection(array,context,0);
	}
	
	/**
	 * @param array the array that contains pairs of paths and node sets
	 * @return the node set of non-variable nodes that we can start following those paths 
	 * in the array from, in order to reach at least one node at each node set in all
	 * entries of the array 
	 */
	public Hashtable<Node,LinkedList<Support>> findConstant(Object[][] array,Context context)
	{
		Hashtable<Node,LinkedList<Support>> result = new Hashtable<Node,LinkedList<Support>>();
		Hashtable<Node,LinkedList<Support>> h = find(array,context);
		for(Enumeration<Node> e = h.keys();e.hasMoreElements();)
		{
			Node n = e.nextElement();
			if(n.getClass().getSimpleName().equals("BaseNode") || 
					n.getClass().getSimpleName().equals("ClosedNode"))
			{
				result.put(n,h.get(n));
			}
		}
		return result;
	}
	
	/**
	 * @param array the array that contains pairs of paths and node sets
	 * @return the node set of base nodes that we can start following those paths 
	 * in the array from, in order to reach at least one node at each node set in all
	 * entries of the array 
	 */
	public Hashtable<Node,LinkedList<Support>> findBase(Object[][] array,Context context)
	{
		Hashtable<Node,LinkedList<Support>> result = new Hashtable<Node,LinkedList<Support>>();
		Hashtable<Node,LinkedList<Support>> h = find(array,context);
		for(Enumeration<Node> e = h.keys();e.hasMoreElements();)
		{
			Node n = e.nextElement();
			if(n.getClass().getSimpleName().equals("BaseNode"))
			{
				result.put(n,h.get(n));
			}
		}
		return result;
	}
	
	/**
	 * @param array the array that contains pairs of paths and node sets
	 * @return the node set of variable nodes that we can start following those paths 
	 * in the array from, in order to reach at least one node at each node set in all
	 * entries of the array 
	 */
	public Hashtable<Node,LinkedList<Support>> findVariable(Object[][] array,Context context)
	{
		Hashtable<Node,LinkedList<Support>> result = new Hashtable<Node,LinkedList<Support>>();
		Hashtable<Node,LinkedList<Support>> h = find(array,context);
		for(Enumeration<Node> e = h.keys();e.hasMoreElements();)
		{
			Node n = e.nextElement();
			if(n.getClass().getSimpleName().equals("VariableNode"))
			{
				result.put(n,h.get(n));
			}
		}
		return result;
	}
	
	/**
	 * @param array the array that contains pairs of paths and node sets
	 * @return the node set of pattern nodes that we can start following those paths 
	 * in the array from, in order to reach at least one node at each node set in all
	 * entries of the array 
	 */
	public Hashtable<Node,LinkedList<Support>> findPattern(Object[][] array,Context context)
	{
		Hashtable<Node,LinkedList<Support>> result = new Hashtable<Node,LinkedList<Support>>();
		Hashtable<Node,LinkedList<Support>> h = find(array,context);
		for(Enumeration<Node> e = h.keys();e.hasMoreElements();)
		{
			Node n = e.nextElement();
			if(n.getClass().getSimpleName().equals("PatternNode"))
			{
				result.put(n,h.get(n));
			}
		}
		return result;
	}
	
	/**
	 * @param path the path that can be followed to get to one of the nodes specified
	 * @param nodeSet the nodes that can be reached by following the path
	 * @return a node set of nodes that we can start following the path from in order to
	 * get to one of the nodes in the specified node set
	 */
	private Hashtable<Node,LinkedList<Support>> findUnion(Path path,NodeSet nodeSet,Context context)
	{
		Hashtable<Node,LinkedList<Support>> result = new Hashtable<Node,LinkedList<Support>>();
		
		NodeSet nodeset= new NodeSet();
		nodeset.addAll(nodeSet);
		if(nodeset.isEmpty())
			return new Hashtable<Node,LinkedList<Support>>();
		
		Node node = nodeset.getNode(0);
		nodeset.removeNode(node);
		NodeSet ns = new NodeSet();
		ns.addAll(nodeset);
		
		Hashtable<Node,LinkedList<Support>> h1 = path.followConverse(node,new LinkedList<Support>(),context);
		Hashtable<Node,LinkedList<Support>> h2 = findUnion(path,ns,context);
		
		result.putAll(h1);
		for(Enumeration<Node> e = h2.keys();e.hasMoreElements();)
		{
			Node no = e.nextElement();
			if(result.containsKey(no))
			{
				result.get(no).addAll(h2.get(no));
			}
			else
				result.put(no,h2.get(no));
		}
		return result;
	}
	
	/**
	 * @param array the array that contains pairs of paths and node sets
	 * @param index the index of the array at which we should start traversing it
	 * @return the node set of nodes that we can start following those paths in the array
	 * from, in order to reach at least one node of node sets at each path-nodeset pair. 
	 */
	private Hashtable<Node,LinkedList<Support>> findIntersection(Object[][] array,Context context,int index)
	{
		Hashtable<Node,LinkedList<Support>> result = new Hashtable<Node,LinkedList<Support>>();
		if(index == array.length)
			return result;
		
		Path path = (Path) array[index][0];
		NodeSet nodeSet = (NodeSet) array[index][1];
		
		
		if(index < array.length-1)
		{
			Hashtable<Node,LinkedList<Support>> h1 = findUnion(path,nodeSet,context);
			Hashtable<Node,LinkedList<Support>> h2 = findIntersection(array,context,++index);
			for(Enumeration<Node> e = h1.keys();e.hasMoreElements();)
			{
				Node node = e.nextElement();
				if(h2.containsKey(node))
				{
					result.put(node,h1.get(node));
					result.get(node).addAll(h2.get(node));
				}
			}
		}
		else
			result = findUnion(path,nodeSet,context);
		return result;
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
				for(int j=0;j<varNodes.size();j++)
				{
					VariableNode v = varNodes.get(j);
					boolean flag = false;
					for(int k=0;k<array.length;k++)
					{
						if(array[k][1].getClass().getSimpleName().equals("NodeSet"))
							continue;
						if(array[k][1].equals(v))
							flag = true;
					}
					if(! flag)
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
	@SuppressWarnings("unchecked")
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
		try {
			Class c = Class.forName("sneps."+caseFrame.getSemanticClass());
			Constructor con = c.getConstructor(new Class[] {Node.class});
			Entity e = (Entity) con.newInstance(closedNode);
			closedNode.setEntity(e);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		this.nodes.put(molName,closedNode);
		this.molecularNodes.get(caseFrame.getId()).addNode(closedNode);
		
		// adding UpCables
		for(int i=0;i<cableSet.size();i++)
		{
			Relation r = cableSet.getCable(i).getRelation();
			for(int j=0;j<cableSet.getCable(i).getNodeSet().size();j++)
			{
				Node n = cableSet.getCable(i).getNodeSet().getNode(j);
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
		this.molecularNodes.get(caseFrame.getId()).addNode(patternNode);
		
		// adding UpCables
		for(int i=0;i<cableSet.size();i++)
		{
			Relation r = cableSet.getCable(i).getRelation();
			for(int j=0;j<cableSet.getCable(i).getNodeSet().size();j++)
			{
				Node n = cableSet.getCable(i).getNodeSet().getNode(j);
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
					ns.addAll(nos);
					exists = true;
					break;
				}else
					exists = false;
			}
			if(! exists)
			{
				exists = false;
				NodeSet nodeSet = new NodeSet();
				nodeSet.addAll(nos);
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
	 * @return a String representing the variable node name that we can create with it
	 */
	private String getNextVarName()
	{
		varCounter++;
		String varName = "V";
		for(int i=0;i<userDefinedVarPostfix.size();i++)
		{
			if(userDefinedVarPostfix.get(i).intValue() == varCounter)
			{
				varCounter++;
				i=-1;
			}
		}
		varName += ""+varCounter;
		
		return varName;
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
	 * @param identifier a String to check whether it looks like variable name
	 * @return an int of the number after the char 'v' if the identifier looks
	 * like the variable name, and -1 otherwise
	 */
	private int isVarName(String identifier)
	{
		if(identifier.length()==1)
			return -1;
		if(identifier.charAt(0) != 'v' && identifier.charAt(0) != 'V')
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
	
	public static void main(String [] args) throws Exception
	{
//		Relation r1 = new Relation("member","entity","reduce",0);
//		Relation r2 = new Relation("class","entity","reduce",0);
		Object[][] o4 = new Object[4][2];		   // creating the 2D arrays used in building nodes
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
		Network n = new Network();								    // creating a new network 
		Node x1 = n.buildVariableNode();                        // building variable nodes
		Node x2 = n.buildVariableNode();
		Node x3 = n.buildVariableNode();
		Node x4 = n.buildVariableNode();
		Node x5 = n.buildVariableNode();
		Node x6 = n.buildVariableNode();
		Node x7 = n.buildVariableNode();
		Node x8 = n.buildVariableNode();
		Node x9 = n.buildVariableNode();
		Node a = n.build("a");						                // building base node
		Relation rr1 = n.defineRelation("r1","Individual","none",0);
		Relation rr2 = n.defineRelation("r2","Act","none",0);	// defining relations
		Relation rr3 = n.defineRelation("r3","Act","none",0);
		Relation rr4 = n.defineRelation("r4","Act","none",0);
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
		CaseFrame caseFrame2 = n.defineCaseFrame("Entity",l2);		  // defining case frames
		CaseFrame caseFrame3 = n.defineCaseFrame("Entity",l3);
		CaseFrame caseFrame4 = n.defineCaseFrame("Entity",l4);
		o3[0][0] = rr1;
		o3[1][0] = rr2;
		o3[2][0] = rr3;
		o3[0][1] = x1;
		o3[1][1] = x2;
		o3[2][1] = x3;
		Node h1 = n.build(o3,caseFrame3);                            // building molecular nodes
		o4[0][0] = rr1;
		o4[1][0] = rr2;
		o4[2][0] = rr3;
		o4[3][0] = rr3;
		o4[0][1] = x6;
		o4[1][1] = x7;
		o4[2][1] = x8;
		o4[3][1] = x9;
		Node h2 = n.build(o4,caseFrame3);
		o4[0][0] = rr1;
		o4[1][0] = rr2;
		o4[2][0] = rr3;
		o4[3][0] = rr4;
		o4[0][1] = h1;
		o4[1][1] = h2;
		o4[2][1] = x3;
		o4[3][1] = x6;
		PatternNode t = (PatternNode) n.build(o4,caseFrame4);
		o2[0][0] = rr1;
		o2[1][0] = rr2;
		o2[0][1] = x4;
		o2[1][1] = x5;
		Node g1 = n.build(o2,caseFrame2);
		o3[0][1] = g1;
		o3[1][1] = x1;
		o3[2][1] = x2;
		Node h3 = n.build(o3,caseFrame3);
		o4[0][0] = rr1;
		o4[1][0] = rr2;
		o4[2][0] = rr3;
		o4[3][0] = rr3;
		o4[0][1] = x7;
		o4[1][1] = x8;
		o4[2][1] = x9;
		o4[3][1] = x6;
		Node h4 = n.build(o4,caseFrame3);
		o2[0][1] = x5;
		o2[1][1] = a;
		Node g2 = n.build(o2,caseFrame2);
		o4[0][0] = rr1;
		o4[1][0] = rr2;
		o4[2][0] = rr3;
		o4[3][0] = rr4;
		o4[0][1] = h3;
		o4[1][1] = h4;
		o4[2][1] = g2;
		o4[3][1] = x5;
		PatternNode tdash = (PatternNode) n.build(o4,caseFrame4);
		/*LinkedList<Object[]> l = n.match(tdash);
		for(int i=0;i<l.size();i++)
		{
			Object[] o = l.get(i);
			System.out.println(((Node)o[0]).getIdentifier()+ "   ");
			for(int j=0;j<((Substitutions)o[1]).cardinality();j++)
			{
				Binding b = ((Substitutions)o[1]).getBinding(j);
				System.out.println(b.getVariable().getIdentifier()+" "+b.getNode().getIdentifier());
			}
			for(int j=0;j<((Substitutions)o[2]).cardinality();j++)
			{
				Binding b = ((Substitutions)o[2]).getBinding(j);
				System.out.println(b.getVariable().getIdentifier()+" "+b.getNode().getIdentifier());
			}
		}*/
		t.getClass();
		tdash.getClass();
		
		
		/*Substitutions r = new Substitutions();
		LinkedList<Substitutions> rr = new LinkedList<Substitutions>();
		rr.add(r);
		System.out.println(x1.getEntity().getClass().getSimpleName());
		if(n.hERe(t,tdash,rr,true)) 
		{
			System.out.println("> "+rr.size());
			for(int i=0;i<rr.size();i++)
			{
				Substitutions x = rr.get(i);
				for(int j=0;j<x.cardinality();j++)
				{
					System.out.print(((Node) x.getBinding(j).getVariable()).getIdentifier());
					System.out.println(" "+((Node) x.getBinding(j).getNode()).getIdentifier());
				}
				System.out.println("----");
			}
		}*/
		/*for(int i=0;i<rr.size();i++)
		{
			LinkedList<VariableNode> list1 = t.getFreeVariables();
			LinkedList<VariableNode> list2 = tdash.getFreeVariables();
			if(n.termVERe(t,rr.get(i),s) != null)
			{
				for(int j=0;j<list1.size();j++)
				{
					System.out.println(list1.get(j).getIdentifier()+" "+ n.vERe(list1.get(j),rr.get(i),s).getIdentifier());
				}
				for(int j=0;j<list2.size();j++)
				{
					System.out.println(list2.get(j).getIdentifier()+" "+ n.vERe(list2.get(j),rr.get(i),s).getIdentifier());
				}
			}
		}*/
		
		/*Path f = new FUnitPath("r3");
		Path b = new BUnitPath("r1");
		Path kf = new KStarPath(f);
		Path kb = new KStarPath(b);
		LinkedList<Path> l = new LinkedList<Path>();
		l.add(kb);
		l.add(kf);
		Path c = new ComposePath(l);
		LinkedList<Path> l1 = new LinkedList<Path>();
		l1.add(c);
		l1.add(f);
		Path or = new AndPath(l1);
		Object[][] o = new Object[1][2];
		o[0][0] = kb;
		NodeSet ns = new NodeSet();
		ns.addNode(h1);
		o[0][1] = ns;
		Hashtable<Node,LinkedList<Support>> h = n.find(o,null);
		for(Enumeration<Node> e = h.keys();e.hasMoreElements();)
			System.out.println(e.nextElement().getIdentifier());*/
		/*Hashtable<String,LinkedList<String>> h = new Hashtable<String, LinkedList<String>>();
		LinkedList<String> l = new LinkedList<String>();
		h.put("amr",l);
		h.get("amr").add("AMR");
		for(Enumeration<String> e = h.keys();e.hasMoreElements();)
			System.out.println(e.nextElement());*/
		/*NodeSet ns1 = new NodeSet();
		NodeSet ns2 = new NodeSet();
		NodeSet ns3 = new NodeSet();
		ns1.addNode(x1);
		ns2.addNode(x2);
		ns3.addNode(x3);
		LinkedList<NodeSet> lll = new LinkedList<NodeSet>();
		lll.add(ns1);
		lll.add(ns2);
		lll.add(ns3);
		for(int i=0;i<lll.size();i++)
		{
			System.out.println(lll.get(i).getNodes().getFirst().getIdentifier());
			if(lll.get(i).getNodes().getFirst().getIdentifier().equals("x2"))
			{
				lll.remove(i);
				i--;
			}
		}*/
		/*MolecularNode m = (MolecularNode) n.vERe((VariableNode) x1,r,s);
		MolecularNode mm = (MolecularNode) n.vERe((VariableNode) x2,r,s);
		MolecularNode mmm = (MolecularNode) n.vERe((VariableNode) x3,r,s);
		System.out.println(m +"  "+mm+"  "+mmm);
		System.out.println((n.vERe((VariableNode) x7,r,s)).getIdentifier());
		System.out.println((n.vERe((VariableNode) x6,r,s)).getIdentifier());
		System.out.println((n.vERe((VariableNode) x5,r,s)).getIdentifier());
		System.out.println((n.vERe((VariableNode) x4,r,s)).getIdentifier());
		System.out.println((n.vERe((VariableNode) x8,r,s)).getIdentifier());
		System.out.println(mm.getCableSet().getCables().get(0).getNodeSet().getNodes().getFirst().getIdentifier());
		System.out.println(mm.getCableSet().getCables().get(1).getNodeSet().getNodes().getFirst().getIdentifier());*/
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